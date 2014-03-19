package activities;

import models.User;
import models.User_Profile;

import org.digitalcampus.oppia.utils.UIUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import applications.CCHDbHelper;

import com.example.cch2.R;


public class CCHProfilesPage extends Activity {
	SharedPreferences prefs;
	public static final String TAG = CCHProfilesPage.class.getSimpleName();
	private boolean IS_LOGGEDIN = false;
	private int MASTER_ID = 1234;	
	private TextView staffIdField;
	private EditText passwordField;		
	private EditText passwordAgainField;		
	private EditText firstNameField;	
	private EditText lastNameField;	
	private EditText facilityIdField;
	private Spinner typeField;	
	private Spinner supervisorField;	
	private String imei;	
	private EditText phoneNumberField;	
	private Spinner secretquestionField;
	private EditText answerField;	
	private ProgressDialog pDialog;
	String m_password;
	String staff_id;
	Intent myIntent;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		
		staffIdField = (TextView) findViewById(R.id.profile_staffId_field);
		staff_id = getIntent().getStringExtra("STAFF_ID");
		staffIdField.setText(staff_id);
		passwordField =(EditText) findViewById(R.id.profile_password_field);
		passwordAgainField = (EditText) findViewById(R.id.profile_passwordagain_field);			
		lastNameField = (EditText) findViewById(R.id.profile_lastname_field);	
		firstNameField = (EditText) findViewById(R.id.profile_first_name_field);	
		facilityIdField = (EditText) findViewById(R.id.profile_facilityid_field);
		typeField =  (Spinner) findViewById(R.id.profile_type_field);
		supervisorField = (Spinner) findViewById(R.id.profile_supervisor_field);
		imei= telephonyManager.getDeviceId(); 
		phoneNumberField = (EditText) findViewById(R.id.profile_number_field);
		secretquestionField = (Spinner) findViewById(R.id.profile_secretQuestion_field);
		answerField = (EditText) findViewById(R.id.profile_answer_field);
		
        
	}
	
	
	public void onRegisterClick(View view){
		
		User u;		
		CCHDbHelper db = new CCHDbHelper(getApplicationContext());
		String firstName = firstNameField.getText().toString();
		String lastName = lastNameField.getText().toString();
		String passWord =passwordField.getText().toString(); 
		String passWordAgain =passwordAgainField.getText().toString(); 
		String secretQuestion = secretquestionField.getSelectedItem().toString();
		String secretAnswer = answerField.getText().toString();
		String type = typeField.getSelectedItem().toString();
		String supervisor = supervisorField.getSelectedItem().toString();		
		//make sure the FacilityId feild is filled		
		if (checkIfEmpty(facilityIdField.getText().toString()) ){
			UIUtils.showAlert(this,R.string.error,R.string.error_fields_not_filled);
			return;
		}
		int facilityId = Integer.valueOf(facilityIdField.getText().toString());
		
		//make sure the phone number feild is filled
		if (checkIfEmpty(phoneNumberField.getText().toString())){
			UIUtils.showAlert(this,R.string.error,R.string.error_fields_not_filled);
			return;
		}
		
		int phoneNumber = Integer.valueOf(phoneNumberField.getText().toString());
		
		if (!passWord.equals(passWordAgain)){
			UIUtils.showAlert(this,R.string.error,R.string.error_fields_not_filled);
			return;
		}
		
		if(checkIfEmpty(firstName) || checkIfEmpty(lastName)|| checkIfEmpty(secretAnswer)|| checkIfEmpty(passWord) ){
			UIUtils.showAlert(this,R.string.error,R.string.error_fields_not_filled);
			return;
		}

		User_Profile p= new User_Profile(Integer.valueOf(staff_id),firstName,lastName,facilityId,type,supervisor,
				passWord,imei,phoneNumber,secretQuestion,secretAnswer);
					
			u = new User(Integer.valueOf(staff_id), passWord);

			if (db.checkUserExists(u)){
				UIUtils.showAlert(this,R.string.error,R.string.error_Id_in_use);
				return;
			}
			
			db.addUserProfile(p);
			db.updateUser(u);
		Intent reset = new Intent(getApplicationContext(), CCHHomePage.class);
		reset.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		reset.putExtra("STAFF_ID",staff_id); 
		
        startActivity(reset);
        finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	///support functions
	private boolean checkIfEmpty(Object e){
	   if (e.toString().length() ==0)
		   return true;
	   return false;
	}
}
