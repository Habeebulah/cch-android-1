package activities;

import java.util.List;
import java.util.Random;

import com.example.cch2.R;

import models.Content;
 
import android.os.Bundle;
import android.app.ListActivity;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import applications.DataAccessObject;

public class MainActivity extends ListActivity {
	private DataAccessObject dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("RAARRRRr", "CONTENT LOADED");
		this.dao = new DataAccessObject(this);
		dao.open();
		List<Content> values = dao.getAllMessages();
		
		//use the SimpleCursorAdapter to show the elements in a ListView
		ArrayAdapter<Content> adapter = new ArrayAdapter<Content>(this,android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
		
	}
	//Will be called via the onClick attribute
		//of the buttons in main.xml
		
		@SuppressWarnings("unchecked")
		public void onClick(View view){	
			String msg = "INSIDE onClick(View view)";
			String tag="ERRR";
			Log.e(tag, msg);
			ArrayAdapter<Content>adapter = (ArrayAdapter<Content>) getListAdapter();
			Content content = null;
			switch (view.getId()){
			case R.id.add:
				String[] content1 = {"123", "joke", "A bagpipe player was asked \"How do you play that thing?\" his answer was \"Well.\""};
				String[] content2 = {"124", "tip", "Did you know that you can check the time of the day by asking google.com?"};
				String[] content3 = {"125", "business", "Todays inflation rate is at 15.5%"};
				String[] content4 = {"126", "religion", "John 3:16 God so loved the world, he gave..."};
				String[] content5 = {"127", "news", "14th February, valentines day is a holiday in Ghana."};
				String[] content6 = {"128", "inspiration", "I think one\'s feelings waste themselves in words; they ought all to be distilled into actions which bring results. -Florence Nightingale"};
				String[][] contentList = new String[][] {content1, content2, content3, content4, content5, content6};
				int limit = contentList.length - 1;

				int nextInt = new Random().nextInt(limit);
			
				
				
				//save the new content to the db
				//then display the new messages in console
				String message =contentList[nextInt][2];
				String category = contentList[nextInt][1];
				content = dao.createContent(new Content(message, category));
				adapter.add(content);
				break;
				
			case R.id.delete:
				if( getListAdapter().getCount() > 0){
					content = (Content) getListAdapter().getItem(0);
					dao.deleteContent(content);
					adapter.remove(content);
				}
				break;
			}
			adapter.notifyDataSetChanged();
		}
		
		@Override
		protected void onResume(){
			Log.e("ERRR","RESUME");
			dao.open();
			super.onResume();
		}
		
		protected void onPause(){
			dao.close();
			super.onPause();
		}
	
	

}
