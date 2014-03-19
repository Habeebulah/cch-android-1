package applications;

import java.util.ArrayList;
import java.util.List;

import models.Content;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataAccessObject {
private SQLiteDatabase db;
private SQLiteHelper dbsHelper;
String[] columns = {
		SQLiteHelper.COLUMN_MESSAGE
		};






	public DataAccessObject(Context context){
		dbsHelper = new SQLiteHelper(context);
	}

	public void open() throws SQLException{
		this.db = dbsHelper.getWritableDatabase();
	}
	
	public void close(){
		dbsHelper.close();
	}

	public Content createContent(Content content) {
		// TODO Auto-generated method stub
		//put content into db
		//get it back out and return it
		
		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.COLUMN_MESSAGE, content.getMessage());
		values.put(SQLiteHelper.COLUMN_CATEGORY, "");
		values.put(SQLiteHelper.COLUMN_CREATED, "");
		values.put(SQLiteHelper.COLUMN_VIEWED, "");
		values.put(SQLiteHelper.COLUMN_SENT, "");
		values.put(SQLiteHelper.COLUMN_ACCESSED, "");
		long insertId = db.insert(SQLiteHelper.TABLE_CONTENTS, null, values);
		String selection = SQLiteHelper.COLUMN_ID + " = " + insertId;
		Cursor cursor = db.query(SQLiteHelper.TABLE_CONTENTS, columns, selection, null, null, null, null, null);
		cursor.moveToFirst();
		Content insertedContent = cursorToContent(cursor);
		cursor.close();
		return insertedContent;
	}
	
	public List<Content> getAllMessages() {
	    List<Content> contents = new ArrayList<Content>();

	    Cursor cursor = db.query(SQLiteHelper.TABLE_CONTENTS,
	        columns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Content content = cursorToContent(cursor);
	      contents.add(content);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return contents;
	  }
	
	public List<Content> getAllJokes(){
		
		List<Content> allContent = null;
		
		return allContent;
	}
	
	
	
	public List<Content> getAllTips(){
		List<Content> allContent = null;
		
		return allContent;	}
	
	public List<Content> getAllBusinessContent(){
		List<Content> allContent = null;
		
		return allContent;	}

	public List<Content> getAllReligiousContent(){
		List<Content> allContent = null;
		
		return allContent;	}
	
	public List<Content> getAllNewsContent(){
		List<Content> allContent = null;
		
		return allContent;	}
	
	public List<Content> getAllInspirationalContent(){
		List<Content> allContent = null;
		
		return allContent;	}
	
	private Content cursorToContent(Cursor cursor) {
		// TODO Auto-generated method stub
		Content content = new Content("", "");
		content.setId(cursor.getLong(0));
		content.setMessage(cursor.getString(0));
		return content;
	}

	public void deleteContent(Content content){
		long id = content.getId();
		System.out.println("Comment deleted with id: " + id);
		db.delete(SQLiteHelper.TABLE_CONTENTS, SQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	}
	

	public List<Content> getAll(String category){
		//
		
		List<Content>fetchedData  =  null;
		
		return fetchedData;
	}
	
	
	
	
}
