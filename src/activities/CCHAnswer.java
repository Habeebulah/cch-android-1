package activities;

import com.example.cch2.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import applications.CCHDbHelper;

public class CCHAnswer extends Activity {
	
	private TextView questionField;
	private EditText answerField;
	private EditText newPasswordField;
	private EditText newPasswordAgainField;
	private String Staff_ID;
	CCHDbHelper db;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reset);
		db = new CCHDbHelper(getApplicationContext());		
		Staff_ID = getIntent().getStringExtra("STAFF_ID"); 
		//get the different fields
		questionField = (TextView) findViewById(R.id.reset_qstn_title);
		answerField = (EditText) findViewById(R.id.reset_answer_field);
		// getthe users secrete qstn from the database
		String Qstn = db.getSecreteQstn(Integer.valueOf(Staff_ID));
		questionField.setText(Qstn);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onResetClick(View view){
		if (!answerField.getText().toString().equalsIgnoreCase(db.getSecreteAns(Integer.valueOf(Staff_ID)))){
			Toast.makeText(getApplicationContext(), "Your answer is wrong.", Toast.LENGTH_SHORT).show();                

		}
		else{			
			
		Intent reset = new Intent(getApplicationContext(), CCHResetPassword.class);
		reset.putExtra("STAFF_ID",Staff_ID);
		reset.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(reset);
		}
    }
	
}