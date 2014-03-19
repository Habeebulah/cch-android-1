package applications;

import java.util.ArrayList;



import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;



import com.bugsense.trace.BugSenseHandler;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import models.Activity;
import models.ActivitySchedule;
import models.Course;
import models.TrackerLog;

import models.User;
import models.User_Profile;


public class CCHDbHelper extends SQLiteOpenHelper{
	
	// All Static variables
	static final String TAG = CCHDbHelper.class.getSimpleName();
	 // Database Name
    private static final String DB_NAME = "cch_local_db";
	// Database Version
    private static final int DB_VERSION = 1;

	private SQLiteDatabase db;
	
	// table name
    private static final String USER_TABLE = "login";
    private static final String USER_PROFILE_TABLE="profile";
    
    // Login Table Columns names
	private static final String USER_ID = BaseColumns._ID;
	private static final String STAFF_ID = "staff_id";
	private static final String PASSWORD = "password";
	
	// Profile Table Column names	
	private static final String PROFILE_STAFF_ID  = "staff_Id";	
	private static final String FIRST_NAME = "first_Name";
	private static final String LAST_NAME = "last_Name";
	private static final String FACILITY_ID = "facilityId";
	private static final String TYPE = "type";
	private static final String SUPERVISOR = "supervisor";
	private static final String PROFILE_PASSWORD = "password";
	private static final String IMEI = "imei";
	private static final String PHONE_NUMBER = "phoneNumber";
	private static final String QUESTION = "secreteQuestion";
	private static final String ANSWER = "answer";
	
	
	
	
	// Constructor
	public CCHDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		createUserTable(db);
		createProfileTable(db);
		
	}
	
	public void createUserTable(SQLiteDatabase db){
		String m_sql = "create table IF NOT EXISTS " + USER_TABLE + " (" + USER_ID + " integer primary key autoincrement, "
				+ STAFF_ID + " text, " + PASSWORD + " text)";		
			
			db.execSQL(m_sql);
			
	}

	public void createProfileTable(SQLiteDatabase db){
		String m_sql = "create table IF NOT EXISTS " + USER_PROFILE_TABLE + " (" + USER_ID + " integer primary key autoincrement, "
				+ PROFILE_STAFF_ID + " integer, " + FIRST_NAME + " text, " + LAST_NAME +" text, " + FACILITY_ID 
				+ " integer, " + TYPE + " text, " + SUPERVISOR + " text, " + PROFILE_PASSWORD + " text, " + IMEI+ " integer," 
				+ PHONE_NUMBER + " integer, " + QUESTION + " text," + ANSWER +" text , foreign Key ("+PROFILE_STAFF_ID+") REFERENCES "+ USER_TABLE+"("+STAFF_ID+")  )";		
			db.execSQL(m_sql);
	}
	
	
	
	public void addUser(User u) {
        SQLiteDatabase db = this.getWritableDatabase(); 
        ContentValues values = new ContentValues();
        values.put(STAFF_ID, u.getStaffId()); // StaffId
        values.put(PASSWORD, u.getPassword()); // Password        
        
        // Inserting Row
        //db.update(USER_TABLE, values, whereClause, whereArgs)
        db.insert(USER_TABLE, null, values);
        db.close(); // Closing database connection       
    }
	
	public void updateUser(User u) {
        SQLiteDatabase db = this.getWritableDatabase(); 
        ContentValues values = new ContentValues();
        values.put(STAFF_ID, u.getStaffId()); // StaffId
        values.put(PASSWORD, u.getPassword()); // Password        
        
        // Inserting Row
        //db.update(USER_TABLE, values, whereClause, whereArgs)
        
        db.update(USER_TABLE, values, STAFF_ID + "="+u.getStaffId(), null);
        db.close(); // Closing database connection        
    }
	
	public void addUserProfile(User_Profile u) {
        SQLiteDatabase db = this.getWritableDatabase(); 
        ContentValues values = new ContentValues();
        values.put(PROFILE_STAFF_ID, u.getStaffId()); // StaffId
        values.put(PROFILE_PASSWORD, u.getPassword()); // Password
        values.put(FIRST_NAME, u.getFirstName()); // FirstName
        values.put(LAST_NAME, u.getLastName()); // LastName
        values.put(FACILITY_ID, u.getFacilityId()); // LastName
        values.put(TYPE, u.getType()); // Type
        values.put(SUPERVISOR, u.getSupervisor()); // Supervisor
        values.put(IMEI, u.getImei()); // Imei
        values.put(PHONE_NUMBER, u.getPhoneNumber()); // Number
        values.put(QUESTION, u.getSecreteQuestion()); // Question
        values.put(ANSWER, u.getAnswer()); // Answer
        db.insert(USER_PROFILE_TABLE, null, values);
        db.close(); // Closing database connection
    }
	
	public void updatUserProfile(User_Profile u) {
        SQLiteDatabase db = this.getWritableDatabase(); 
        ContentValues values = new ContentValues();
        values.put(PROFILE_STAFF_ID, u.getStaffId()); // StaffId
        values.put(PROFILE_PASSWORD, u.getPassword()); // Password
        values.put(FIRST_NAME, u.getFirstName()); // FirstName
        values.put(LAST_NAME, u.getLastName()); // LastName
        values.put(FACILITY_ID, u.getFacilityId());
        values.put(TYPE, u.getType()); // Type
        values.put(SUPERVISOR, u.getSupervisor()); // Supervisor
        values.put(IMEI, u.getImei()); // Imei
        values.put(PHONE_NUMBER, u.getPhoneNumber()); // Number
        values.put(QUESTION, u.getSecreteQuestion()); // Question
        values.put(ANSWER, u.getAnswer()); // Answer
        db.update(USER_PROFILE_TABLE, values, PROFILE_STAFF_ID + "="+u.getStaffId(), null);
        db.close(); // Closing database connection
    }
	
	//check if Staff Id exists	
	public boolean checkId(int user_sid){
		SQLiteDatabase db = this.getReadableDatabase();		 
		/*Cursor cursor = db.query(USER_TABLE, new String[] { USER_ID,
	    		STAFF_ID, PASSWORD }, STAFF_ID + "=?",
	            new String[] { String.valueOf(user_sid) }, null, null, null, null); 
		if (cursor == null || cursor.getCount()==0)
	        return false;*/	    	
	        		        
	        return true;
	}
	
	// Get user question 
	public String getSecreteQstn(int user_sid ){
		SQLiteDatabase db = this.getReadableDatabase();		 
		Cursor cursor = db.query(USER_PROFILE_TABLE, new String[] {USER_ID, 	
				PROFILE_STAFF_ID, PROFILE_PASSWORD, FIRST_NAME, LAST_NAME, TYPE,FACILITY_ID,
				SUPERVISOR, IMEI, PHONE_NUMBER, QUESTION, ANSWER}, PROFILE_STAFF_ID + "=?",
	            new String[] { String.valueOf(user_sid) }, null, null, null, null); 
		 if (cursor == null || cursor.getCount()==0)
		        return null;    	
		        cursor.moveToFirst();	
		return cursor.getString(10);
		
	}
	
		
	// Get user Answer 
		public String getSecreteAns(int user_sid ){
			SQLiteDatabase db = this.getReadableDatabase();		 
			Cursor cursor = db.query(USER_PROFILE_TABLE, new String[] {USER_ID, 	
					PROFILE_STAFF_ID, PROFILE_PASSWORD, FIRST_NAME, LAST_NAME, TYPE, FACILITY_ID,
					SUPERVISOR, IMEI, PHONE_NUMBER, QUESTION, ANSWER}, PROFILE_STAFF_ID + "=?",
		            new String[] { String.valueOf(user_sid) }, null, null, null, null); 
			 if (cursor == null || cursor.getCount()==0)
			        return null;    	
			        cursor.moveToFirst();	
			return cursor.getString(11);
			
		}
		
	//get User profile from the User
		/*public User_Profile getProfile(User u){
			SQLiteDatabase db = this.getReadableDatabase();	
			Cursor cursor = db.query(USER_PROFILE_TABLE, new String[] {USER_ID, 	
					PROFILE_STAFF_ID, PROFILE_PASSWORD, FIRST_NAME, LAST_NAME, FACILITY_ID, TYPE,
					SUPERVISOR, IMEI, PHONE_NUMBER, QUESTION, ANSWER}, PROFILE_STAFF_ID + "=?",
		            new String[] { String.valueOf(u.getStaffId()) }, null, null, null, null);
			if (cursor.moveToFirst()){
				User_Profile  x =  new User_Profile(Integer.valueOf(cursor.getString(0)), cursor.getString(3),cursor.getString(4),Integer.valueOf(cursor.getString(5)),
						cursor.getString(6), cursor.getString(7), cursor.getString(2), Integer.valueOf(cursor.getString(8)), Integer.valueOf(cursor.getString(9))
						, cursor.getString(10), cursor.getString(11));
			    		 
			}
		}*/
	 
	// check if User exists
	public boolean checkUserExists(User u) {
		
		SQLiteDatabase db = this.getReadableDatabase();		 
	    Cursor cursor = db.query(USER_TABLE, new String[] { USER_ID,
	    		STAFF_ID, PASSWORD }, STAFF_ID + "=?",
	            new String[] { String.valueOf(u.getStaffId()) }, null, null, null, null); 	        
	   if (cursor == null || cursor.getCount()==0)
	        return false;	    	
	        cursor.moveToFirst();	 
	    User demoUser = new User(Integer.parseInt(cursor.getString(0)),
	    		Integer.parseInt(cursor.getString(1)), cursor.getString(2));
	    if (!(u.getStaffId() ==(demoUser.getStaffId())))
	    	return false;	    
	    if (!(u.getPassword().equals(demoUser.getPassword())))
	    	return false;	   
	    return true;
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + USER_PROFILE_TABLE);
        // Create tables again
        onCreate(db);
		
	}
	
	 public void resetTables(){
	        SQLiteDatabase db = this.getWritableDatabase();
	        // Delete All Rows
	        db.delete(USER_TABLE, null, null);
	        db.delete(USER_PROFILE_TABLE, null, null);
	        db.close();
	    }
	
	/*DatabaseHandler.java
	package com.example.androidhive.library;     
	    


	    public HashMap<String, String> getUserDetails(){
	        HashMap<String,String> user = new HashMap<String,String>();
	        String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;
	          
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor cursor = db.rawQuery(selectQuery, null);
	        // Move to first row
	        cursor.moveToFirst();
	        if(cursor.getCount() > 0){
	            user.put("name", cursor.getString(1));
	            user.put("email", cursor.getString(2));
	            user.put("uid", cursor.getString(3));
	            user.put("created_at", cursor.getString(4));
	        }
	        cursor.close();
	        db.close();
	        // return user
	        return user;
	    }
	 
	    *//**
	     * Getting user login status
	     * return true if rows are there in table
	     * *//*
	    public int getRowCount() {
	        String countQuery = "SELECT  * FROM " + TABLE_LOGIN;
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor cursor = db.rawQuery(countQuery, null);
	        int rowCount = cursor.getCount();
	        db.close();
	        cursor.close();
	         
	        // return row count
	        return rowCount;
	    }
	     
	    *//**
	     * Re crate database
	     * Delete all tables and create them again
	     * *//*
	    public void resetTables(){
	        SQLiteDatabase db = this.getWritableDatabase();
	        // Delete All Rows
	        db.delete(TABLE_LOGIN, null, null);
	        db.close();
	    }
	 
	}*/
	
	
	
	
	
}
