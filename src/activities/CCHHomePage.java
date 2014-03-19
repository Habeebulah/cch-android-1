package activities;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.apache.cordova.Config;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.api.CordovaInterface;
import org.apache.cordova.api.CordovaPlugin;
import org.digitalcampus.oppia.activity.OppiaMobileActivity;
import org.digitalcampus.oppia.activity.test;

import com.example.cch2.R;



public class CCHHomePage extends Activity implements CordovaInterface{
	
	
	private CordovaWebView mainView;
	private String TAG = "CORDOVA_ACTIVITY";
	private final ExecutorService threadPool = Executors.newCachedThreadPool();
	//private CordovaFragment mFragment;

	@Override	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		mainView = (CordovaWebView) findViewById(R.id.dashboard_view);
		Config.init(this);
		String url = "file:///android_asset/www/index.html";
		mainView.loadUrl(url, 5000);
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "onPause");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (this.mainView != null) {
			this.mainView
					.loadUrl("javascript:try{cordova.require('cordova/channel').onDestroy.fire();}catch(e){console.log('exception firing destroy event from native');};");
			this.mainView.loadUrl("about:blank");
			mainView.handleDestroy();
		}
	}



	@Override
	public Activity getActivity() {
		// TODO Auto-generated method stub
		return this;
	}




	@Override
	public ExecutorService getThreadPool() {
		// TODO Auto-generated method stub
		return threadPool;
	}




	@Override
	public Object onMessage(String message, Object arg1) {
		// TODO Auto-generated method stub
		Log.d(TAG, message);
		if (message.equalsIgnoreCase("exit")) {
			super.finish();
		}
		return null;
	}




	@Override
	public void setActivityResultCallback(CordovaPlugin arg0) {
		// TODO Auto-generated method stub
		Log.d(TAG, "setActivityResultCallback is unimplemented");
	}




	@Override
	public void startActivityForResult(CordovaPlugin arg0, Intent arg1, int arg2) {
		// TODO Auto-generated method stub
		Log.d(TAG, "startActivityForResult is unimplemented");
	}
	
	
	
	
	
	
	

}

