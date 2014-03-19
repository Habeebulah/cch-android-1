package activities;

import org.digitalcampus.oppia.activity.OppiaMobileActivity;
import org.digitalcampus.oppia.utils.UIUtils;

import models.User;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import applications.CCHDbHelper;


import com.example.cch2.R;
/*
 * This activity shows the login page 
 * it has a login and a sign up button
 * it has 2 text fields, one for taking the Staff Id and another for password entry
 */
public class CCHLogin extends Activity{

	public static final String TAG = CCHLogin.class.getSimpleName();
	private boolean IS_LOGGEDIN = false;
	private int MASTER_ID = 1234;	
	private EditText staffIdField;
	private EditText passwordField;	
	private ProgressDialog pDialog;
	String m_password;
	Intent myIntent;
	private String enteredId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);		
		staffIdField = (EditText) findViewById(R.id.staff_id_fields);
		passwordField = (EditText) findViewById(R.id.login_password_field);	
		m_password = getIntent().getStringExtra("GENERATED_PASSWORD"); 

	}

	/*
	 * Methods needed
	 * 
	 */

	public void onLoginClick(View view){

		CCHDbHelper db = new CCHDbHelper(getApplicationContext());		
		String password = passwordField.getText().toString();		
				
		if(staffIdField.getText().toString().length() == 0){
			UIUtils.showAlert(this,R.string.error,R.string.error_no_staff_id);
			return;
		}
		int staffid = Integer.valueOf(staffIdField.getText().toString());

		if(passwordField.getText().toString().length() == 0){
			UIUtils.showAlert(this,R.string.error,R.string.error_no_password);
			return;
		}		

		User u = new User(staffid, password);

		


		//decide whether this is a first time user or not

		if (password.equals(m_password)){
			db.addUser(u);
			myIntent = new Intent(getApplicationContext(), CCHProfilesPage.class);
			myIntent.putExtra("STAFF_ID",staffIdField.getText().toString()); 
			myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(myIntent);
			finish();
		}        
		else if(!db.checkUserExists(u)){
			Log.v("password", "Wrong");
			UIUtils.showAlert(this,R.string.error,R.string.error_password_combination);
			return;
		}		
		else{
			view.setId(R.id.dashboard_view);
			if (view.getId() == R.id.dashboard_view){
				myIntent = new Intent(getApplicationContext(), MainActivity.class);			
				startActivity(myIntent);
				finish();    
			}
			    
		}



	}
	
	//Sign up link page
	public void onSingUpClick(View view){
		Intent singUp = new Intent(getApplicationContext(), CCHSignUp.class);
		singUp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(singUp);
	}

	// for resetting forgotten password
	public void onForgetClick(View view){
		
		//Declarations
		final CCHDbHelper db = new CCHDbHelper(getApplicationContext());		
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		
		// allow user enter his/her staff id using the dialogue box
		alertDialog.setTitle("Staff ID");		
		
		final EditText S_ID = new EditText(this);
		final TextView title = new TextView(this);
		final TextView error = new TextView (this);
		S_ID.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
		title.setText("Please Enter Your Staff ID");
		
		final LinearLayout ll=new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.addView(title);
		ll.addView(S_ID);
		alertDialog.setView(ll);
		alertDialog.setCancelable(false);
		alertDialog.setPositiveButton("Done",  new DialogInterface.OnClickListener() { 

			public void onClick(DialogInterface dialog, int id) {
				//enteredId =S_ID.getText().toString();
				// validation for the dialogue box
				
				if (S_ID.getText().toString().length() == 0){
					S_ID.setText(null);
					Toast.makeText(getApplicationContext(), "Please enter a correct Staff ID.", Toast.LENGTH_SHORT).show();                
				}
				else if (!db.checkId(Integer.valueOf(S_ID.getText().toString()))){
					Toast.makeText(getApplicationContext(), "Please enter a correct Staff ID.", Toast.LENGTH_SHORT).show();
				}
				else{	
					Intent singUp = new Intent(getApplicationContext(), CCHAnswer.class);
					singUp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					singUp.putExtra("STAFF_ID",S_ID.getText().toString()); 
					startActivity(singUp);
				}
			}
		});

		AlertDialog alert = alertDialog.create();
		alert.show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
