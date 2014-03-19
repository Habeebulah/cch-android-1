package activities;

import org.digitalcampus.oppia.utils.UIUtils;

import models.User;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import applications.CCHDbHelper;


import com.example.cch2.R;

public class CCHSignUp extends Activity{
	private EditText staffIdField;
	private EditText passwordField;	
	private EditText passwordAgainField;
	private ProgressDialog pDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);	
		
		staffIdField = (EditText) findViewById(R.id.staff_id_field);
        passwordField = (EditText) findViewById(R.id.password_field);	
        passwordAgainField = (EditText) findViewById(R.id.password_field_again);			
	}
	
	public void onSignUpClick(View view){
		CCHDbHelper db = new CCHDbHelper(getApplicationContext());
		
		String password = passwordField.getText().toString();
		String passwordAgain = passwordAgainField.getText().toString();	
        
        if(staffIdField.getText().toString().length() == 0){
			UIUtils.showAlert(this,R.string.error,R.string.error_no_staff_id);
			return;
		}
        int staffid = Integer.valueOf(staffIdField.getText().toString());

		if(passwordField.getText().toString().length() == 0){
			UIUtils.showAlert(this,R.string.error,R.string.error_no_password);
			return;
		}
		
		if(!passwordAgain.equals(password)){
			UIUtils.showAlert(this,R.string.error,R.string.error_no_match);
			return;
		}
		
		User u = new User(staffid, password);  
		
		if (db.checkUserExists(u)){
			UIUtils.showAlert(this,R.string.error,R.string.error_Id_in_use);
			return;
		}
		
		// show progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setTitle(R.string.title_login);
        pDialog.setMessage(this.getString(R.string.login_process));
        pDialog.setCancelable(true);
        pDialog.show();
		
		db.addUser(u);
		Intent signup = new Intent(getApplicationContext(), CCHHomePage.class);
		signup.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(signup);
        // Closing dashboard screen
        finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
