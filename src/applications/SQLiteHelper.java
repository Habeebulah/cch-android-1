package applications;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {
	public static final String TABLE_CONTENTS = "message";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_MESSAGE = "message";
	public static final String COLUMN_CATEGORY = "category";
	public static final String COLUMN_CREATED = "created";
	public static final String COLUMN_VIEWED = "viewed";
	public static final String COLUMN_SENT = "sent";
	public static final String COLUMN_ACCESSED = "accessed";


	  private static final String DATABASE_NAME = "contents.db";
	  private static final int DATABASE_VERSION = 1;
	  private static final String DATABASE_CREATE = "create table "
	  	      + TABLE_CONTENTS + "(" + COLUMN_ID
	  	      + " integer primary key autoincrement, "
	  	      + COLUMN_MESSAGE + " text not null, "
	  	      + COLUMN_CATEGORY + " text not null, "
	  	      + COLUMN_CREATED + " text not null, "
	  	      + COLUMN_VIEWED + " text not null, "
	  	      + COLUMN_SENT + " text not null, "
	  	      + COLUMN_ACCESSED + " text not null "
	  	      + ");";

		  public SQLiteHelper(Context context) {
		    super(context, DATABASE_NAME, null, DATABASE_VERSION);
		  }
		  
		  
		  @Override
		  public void onCreate(SQLiteDatabase database) {
		    database.execSQL(DATABASE_CREATE);
		  }

		  @Override
		  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		    Log.w(SQLiteHelper.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTENTS);
		    onCreate(db);
		  }
		  
		  
}
