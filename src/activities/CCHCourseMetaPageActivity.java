/* 
 * This file is part of OppiaMobile - http://oppia-mobile.org/
 * 
 * OppiaMobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * OppiaMobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with OppiaMobile. If not, see <http://www.gnu.org/licenses/>.
 */

package activities;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;

import utils.FileUtils;

import com.example.cch2.R;

import models.Course;
import models.CourseMetaPage;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.webkit.WebView;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class CCHCourseMetaPageActivity extends Activity {

	public static final String TAG = CCHCourseMetaPageActivity.class.getSimpleName();
	private Course course;
	private SharedPreferences prefs;
	private int pageid;
	private CourseMetaPage cmp;
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_metapage);
		getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		
		Bundle bundle = this.getIntent().getExtras();
		if (bundle != null) {
			course = (Course) bundle.getSerializable(Course.TAG);
			setTitle(course.getTitle(prefs.getString(getString(R.string.prefs_language), Locale.getDefault().getLanguage())));
			pageid = (Integer) bundle.getSerializable(CourseMetaPage.TAG);
			cmp = course.getMetaPage(pageid);
		}
		
		TextView titleTV = (TextView) findViewById(R.id.course_title);
		String title = cmp.getLang(prefs.getString(getString(R.string.prefs_language), Locale.getDefault().getLanguage())).getContent();
		titleTV.setText(title);
		
		TextView versionTV = (TextView) findViewById(R.id.course_versionid);
		BigDecimal big = new BigDecimal(course.getVersionId());
		versionTV.setText(big.toString());
		
		TextView shortnameTV = (TextView) findViewById(R.id.course_shortname);
		shortnameTV.setText(course.getShortname());
		
		WebView wv = (WebView) this.findViewById(R.id.metapage_webview);
		String url = course.getLocation() + "/" +cmp.getLang(prefs.getString(getString(R.string.prefs_language), Locale.getDefault().getLanguage())).getLocation();
		
		try {
			String content =  "<html><head>";
			content += "<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>";
			content += "<link href='file:///android_asset/www/style.css' rel='stylesheet' type='text/css'/>";
			content += "</head>";
			content += FileUtils.readFile(url);
			content += "</html>";
			wv.loadDataWithBaseURL("file://" + course.getLocation() + "/", content, "text/html", "utf-8", null);
		} catch (IOException e) {
			e.printStackTrace();
			wv.loadUrl("file://" + url);
		}
	    
	}
	
}