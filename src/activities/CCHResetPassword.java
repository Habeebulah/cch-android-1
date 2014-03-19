package activities;

import org.digitalcampus.oppia.utils.UIUtils;

import models.User;

import com.example.cch2.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import applications.CCHDbHelper;


public class CCHResetPassword extends Activity{
	
	private EditText passwordField;	
	private EditText passwordAgainField;
	private String Staff_ID;
	private ProgressDialog pDialog;
	CCHDbHelper db;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reset_password);
		db = new CCHDbHelper(getApplicationContext());
		Staff_ID = getIntent().getStringExtra("STAFF_ID");
		passwordField = (EditText) findViewById(R.id.reset_password_field);
		passwordAgainField = (EditText) findViewById(R.id.reset_confirm_password_field);
		
		
        
	}
	public void onResetClick(View view){
		String passWord = passwordField.getText().toString();
		String passWordAgain = passwordAgainField.getText().toString();
		
		if (passWord.length() ==0 || passWordAgain.length() ==0){
			UIUtils.showAlert(this,R.string.error,R.string.error_password_combination);
			return;
		}
		
		else if (!passWord.equals(passWordAgain)){
			UIUtils.showAlert(this,R.string.error,R.string.error_password_combination);
			return;
		}
		
		User u = new User(Integer.valueOf(Staff_ID), passWord);
		db.updateUser(u);		
		Intent home = new Intent(getApplicationContext(), CCHHomePage.class);		
		home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home);
		
	}
}
