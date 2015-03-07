package com.example.ciudadturistica2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PlacesDbAdapter {
	
	public static final String KEY_ROWID = "_id";
	//public static final String KEY_CODE = "code";
	public static final String KEY_NAME = "name";
	//public static final String KEY_ICON = "continent";
	//public static final String KEY_LOCATION = "region";
	 
	 private static final String TAG = "PlacesDbAdapter";
	 private DatabaseHelper mDbHelper;
	 private SQLiteDatabase mDb;
	 
	 private static final String DATABASE_NAME = "Cangas";
	 private static final String SQLITE_TABLE = "Places";
	 private static final int DATABASE_VERSION = 1;
	 
	 private final Context mCtx;
	 
	 private static final String DATABASE_CREATE =
	  "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
	  KEY_ROWID + " integer PRIMARY KEY autoincrement," +
	  KEY_NAME +");";
	  //KEY_CONTINENT + "," +
	  //KEY_REGION + "," +
	  //" UNIQUE (" + KEY_CODE +"));";
	 
	 private static class DatabaseHelper extends SQLiteOpenHelper {
	 
		 DatabaseHelper(Context context) {
			 super(context, DATABASE_NAME, null, DATABASE_VERSION);
		 }
	  
		 @Override
		 public void onCreate(SQLiteDatabase db) {
			 Log.w(TAG, DATABASE_CREATE);
			 db.execSQL(DATABASE_CREATE);
		 }
	 
		 @Override
		 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			 Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					 + newVersion + ", which will destroy all old data");
			 db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
			 onCreate(db);
		 }

	 }
	 
	 public PlacesDbAdapter(Context ctx) {
		  this.mCtx = ctx;
		 }
		 
		 public PlacesDbAdapter open() throws SQLException {
		  mDbHelper = new DatabaseHelper(mCtx);
		  mDb = mDbHelper.getWritableDatabase();
		  return this;
		 }
		 
		 public void close() {
		  if (mDbHelper != null) {
		   mDbHelper.close();
		  }
		 }
	
	public long createPlace(String name) { //Podemos ponerle todas las string que queramos con String code, String Localitation y asi!
				 
		ContentValues initialValues = new ContentValues();
			//initialValues.put(KEY_CODE, code);
			initialValues.put(KEY_NAME, name);
			//initialValues.put(KEY_CONTINENT, continent);
			//initialValues.put(KEY_REGION, region);
				 
				  return mDb.insert(SQLITE_TABLE, null, initialValues);
	}
	
	public boolean deleteAllPlaces() {
		 
		int doneDelete = 0;
		doneDelete = mDb.delete(SQLITE_TABLE, null , null);
		Log.w(TAG, Integer.toString(doneDelete));
		return doneDelete > 0;
		 
	}
	
	public Cursor fetchPlacesByName(String inputText) throws SQLException {
		Log.w(TAG, inputText);
		Cursor mCursor = null;
		if (inputText == null  ||  inputText.length () == 0)  {
		mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ROWID,
		   KEY_NAME},
		   null, null, null, null, null);
		 
		  }
		  else {
		   mCursor = mDb.query(true, SQLITE_TABLE, new String[] {KEY_ROWID,
		     KEY_NAME},
		     KEY_NAME + " like '%" + inputText + "%'", null,
		     null, null, null, null);
		  }
		  if (mCursor != null) {
		   mCursor.moveToFirst();
		  }
		  return mCursor;
		 
	}
	
	public Cursor fetchAllPlaces() {
		 
		  Cursor mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ROWID,
		    KEY_NAME},
		    null, null, null, null, null);
		 
		  if (mCursor != null) {
		   mCursor.moveToFirst();
		  }
		  return mCursor;
	}
	
	public void insertSomePlaces() {
		 
		  createPlace("Puente Romano");
		  createPlace("Ermita de la Cruz");
		  createPlace("Dolmen");
		  createPlace("Iglesia Parroquial");
		  createPlace("Estatua de Don Pelayo");
		  createPlace("Aula del Reino de Asturias");
		  createPlace("Capilla de SanAntonio");
		  createPlace("Palacio Pintu");
		  createPlace("Casa Drago");
		  
		 
		 }
}
