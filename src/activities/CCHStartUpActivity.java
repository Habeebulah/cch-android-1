package activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import applications.CCHDbHelper;

import com.example.cch2.R;

/*
 * This Activity creates a splash screen and leads to the loging page
 * 
 */
public class CCHStartUpActivity extends Activity {
	
    private final int SPLASH_DISPLAY_LENGHT = 1000;
    public String GENERATED_PASSWORD = "cch";
    
    SharedPreferences prefs = null;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startup);
		/*CCHDbHelper db = new CCHDbHelper(getApplicationContext());
		db.resetTables();*/
		
		/* New Handler to start the Menu-Activity 
         * and close this Splash-Screen after some seconds.*/	
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
            	//and in your onCreate method:
        		prefs = getSharedPreferences("packageNameHere", MODE_PRIVATE);
        		Intent mainIntent;
        		
        			 mainIntent = new Intent(CCHStartUpActivity.this,CCHLogin.class);
        			 mainIntent.putExtra("GENERATED_PASSWORD",getGeneratedPassword()); 
                    CCHStartUpActivity.this.startActivity(mainIntent);               
                //send master password to login activity               
                CCHStartUpActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//set master password 
	public String getGeneratedPassword(String s){
		return this.GENERATED_PASSWORD;		
	}
	
	// get master password
	public String getGeneratedPassword(){
		return GENERATED_PASSWORD;		
	}

}
