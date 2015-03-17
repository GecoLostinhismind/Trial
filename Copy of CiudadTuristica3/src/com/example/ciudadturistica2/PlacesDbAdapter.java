package com.example.ciudadturistica2;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PlacesDbAdapter extends SQLiteOpenHelper {
	
	
	
	public static final String KEY_ROWID = "_id";
	//public static final String KEY_PIC = "pic";
	public static final String KEY_NAME = "name";
	public static final String KEY_LAT = "latitude";
	public static final String KEY_LNG = "longitude";
	
	 
	 private static final String TAG = "PlacesDbAdapter";
	 
	
	 
	 private static final String DATABASE_NAME = "Cangas";
	 private static final String SQLITE_TABLE = "Places";
	 private static final int DATABASE_VERSION = 1;
	 
	 
	 
	 private static final String DATABASE_CREATE =
	  "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
	  KEY_ROWID + " INTEGER PRIMARY KEY," +
	  KEY_NAME +" TEXT,"+KEY_LAT+" TEXT,"+KEY_LNG+" TEXT"+")";
	 
	 
	 PlacesDbAdapter(Context context) {
		 super(context, DATABASE_NAME, null, DATABASE_VERSION);
	 }
  
	 @Override
	 public void onCreate(SQLiteDatabase mdb) {
		 Log.w(TAG, DATABASE_CREATE);
		 mdb.execSQL(DATABASE_CREATE);
	 }
 
	 @Override
	 public void onUpgrade(SQLiteDatabase mdb, int oldVersion, int newVersion) {
		 Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
				 + newVersion + ", which will destroy all old data");
		 mdb.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE);
		 onCreate(mdb);
	 }
	 

		 
	 
	
	public void createPlace(Places p) { //aqui tengo escritos los initialValues de KEY_NAME, KEY_LAT, KEY_LNG pero no se introducir valores sobre estas variables!)
		SQLiteDatabase mdb = this.getWritableDatabase();		 
		ContentValues initialValues = new ContentValues();
			//initialValues.put(KEY_CODE, code);
			initialValues.put(KEY_NAME, p.getName());
			initialValues.put(KEY_LAT, p.getLat());
			initialValues.put(KEY_LNG, p.getLng());
		
				 
			try{	// Inserting Row
		        mdb.insert(SQLITE_TABLE, null, initialValues);
		        
		    }
		    catch(SQLiteException e){
		    	Log.v("tag","error sql"+e.getMessage());
		    }
		        mdb.close(); // Closing database connection 
			
			
				  
				  mdb.close();
	}
	
	//Esta lista es la que pretendo recuperar para que me muestre en la activity principal solo los nombres de los lugares (si al final es posible mostar icono al lado del nombre genial sino da igual)
	//La latitud y ubicación si fuese posible no mostrarla y utilizarla solo cuando se las requiera en los momentos de mostrar en el mapa, la brujula etc. vamos que se utilicen de manera interna y no en la intefaz no se si me he explicado bien
	public List<String> getPlaces() {
		List<String> places = new ArrayList<String>();
		
		String selectQuery = "SELECT" + KEY_NAME + "FROM" + SQLITE_TABLE + "ORDER BY" + KEY_NAME;
		
		SQLiteDatabase mdb = this.getWritableDatabase();
        Cursor mCursor = mdb.rawQuery(selectQuery, null);
        
        if (mCursor.moveToFirst()) {
            do {
            	places.add(mCursor.getString(0));
            }while(mCursor.moveToNext());
        }
        
        return places;
	}
	
	//Copie lo que me enseñaste con la idea de que este metodo sirviese para recuperar los datos  y usarlos en las tareas de ubicacion y AR, pero aaki me quedé...
	public List<String> getLocations() {
		List<String> places = new ArrayList<String>();
		
		String selectQuery = "SELECT" + KEY_LAT + KEY_LNG + "FROM" + SQLITE_TABLE + "ORDER BY" + KEY_LAT + KEY_LNG;
		
		SQLiteDatabase mdb = this.getWritableDatabase();
		Cursor mCursor = mdb.rawQuery(selectQuery, null);
		
		 if (mCursor.moveToFirst()) {
	            do {
	            	places.add(mCursor.getString(0));
	            }while(mCursor.moveToNext());
	        }
		
		return places;
		
	}
	
	//este era el metodo antiguo que usaba para incluir los lugares en la lista y de alguna manera funcionaba, pero al ir cambiando codigo la cosa dejo de rular.
	//public void insertSomePlaces() {
		 //LOS ITEMS (LUGARES) SON LOS DEFINITIVOS.
		  //createPlace("Puente Romano");
		  //createPlace("Ermita de la Cruz");
		  //createPlace("Dolmen");
		  //createPlace("Iglesia Parroquial");
		  //createPlace("Estatua de Don Pelayo");
		  //createPlace("Aula del Reino de Asturias");
		  //createPlace("Capilla de San Antonio");
		  //createPlace("Palacio Pintu");
		  //createPlace("Plaza del mercado");
		  //createPlace("Casa Dago");
		  
		 
		 //}
	
	
	
	
}
