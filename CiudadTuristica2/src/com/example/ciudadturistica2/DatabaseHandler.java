package com.example.ciudadturistica2;

import java.util.ArrayList;
import java.util.List;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;




public class DatabaseHandler  extends SQLiteOpenHelper{
	
	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "sherpaManager";

	// Places table name
	private static final String TABLE_PLACES = "places";
	
	
	
	// Places Table Columns names
	static final String KEY_ID = "id";
	static final String KEY_NAME = "name";
	private static final String KEY_PH_NO = "phone_number";//Variable que por ahora no uso, seguramente la usare para otras como localizacion.
		
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
		
	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_PLACES_TABLE = "CREATE TABLE " + TABLE_PLACES + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT" +")";
		db.execSQL(CREATE_PLACES_TABLE);
	}
		
	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACES);
			
		// Create tables again
		onCreate(db);
	}
		
		/**
		 * All CRUD(Create, Read, Update, Delete) Operations
		 */

	// Adding new place
	void addPlaces(Places places) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, places.getName()); // Place Name
			

		// Inserting Row
		db.insert(TABLE_PLACES, null, values);
		db.close(); // Closing database connection
	}
		
	// Getting single place
	Places getPlace(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_PLACES, new String[] { KEY_ID,
					KEY_NAME }, KEY_ID + "=?",
					new String[] { String.valueOf(id) }, null, null, null);//A lo mejor hay que quitar un null si no rula.
		if (cursor != null)
			cursor.moveToFirst();

		Places places = new Places(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1));//le he quitado un cursor.getString(2)) porque me daba error, se referira al phone number
		// return contact
		return places;
	}
		
	
	
	//Getting All Contacts 2ºoption (new way)
	public Cursor getAllPlaces() {
		  
		  SQLiteDatabase db = this.getWritableDatabase();
		  Cursor mCursor = db.rawQuery(TABLE_PLACES, new String[] { KEY_NAME },
		    null);
		 
		  if (mCursor != null) {
		   mCursor.moveToFirst();
		  }
		  return mCursor;
		 }
	
	
	// Getting All Contacts 1ºoption (old way) Con este método si me funciona la app, pero no me deja hacer display de la Listview y volcarla en el row.
	//public List<Places> getAllPlaces() {
		//List<Places> placesList = new ArrayList<Places>();
		// Select All Query
		//String selectQuery = "SELECT  * FROM " + TABLE_PLACES;

		//SQLiteDatabase db = this.getWritableDatabase();
		//Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		//if (cursor.moveToFirst()) {
			//do {
				//Places places = new Places();
				//places.setID(Integer.parseInt(cursor.getString(0)));
				//places.setName(cursor.getString(1));
					
				// Adding contact to list
				//placesList.add(places);
			//} while (cursor.moveToNext());
		//}

		// return contact list
		//return placesList;
	//}
		
	// Updating single place
	public int updatePlaces(Places places) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, places.getName());
		

		// updating row
		return db.update(TABLE_PLACES, values, KEY_ID + " = ?",
					new String[] { String.valueOf(places.getID()) });
	}
	
	// Deleting single place
	public void deletePlaces(Places places) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PLACES, KEY_ID + " = ?",
				new String[] { String.valueOf(places.getID()) });
		db.close();
	}
	
	// Getting places Count
	public int getPlacesCount() {
		String countQuery = "SELECT  * FROM " + TABLE_PLACES;
		SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(countQuery, null);
			cursor.close();

		// return count
		return cursor.getCount();
	}	

}
