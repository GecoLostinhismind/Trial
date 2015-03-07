package com.example.ciudadturistica2;


import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final int INFOAPP = Menu.FIRST;
    private static final int SALIR = Menu.FIRST+1;
    
    private PlacesDbAdapter dbHelper;
    private SimpleCursorAdapter dataAdapter;
    
     
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbHelper = new PlacesDbAdapter(this);
	     dbHelper.open();
	    
	     //Clean all data
	     dbHelper.deleteAllPlaces();
	     //Add some data
	     dbHelper.insertSomePlaces();
	    
	     //Generate ListView from SQLite Database
	     displayListView();
	     

	 	//String[] values = new String [] {"Estatua", "Puente", "Parque",
	 	  //      "Bar", "Río", "Roca", "Plaza", "Teatro",
	 	    //    "Ruinas", "KFC"};
	 		
	 		//**Este Array usa un adapter que usa por defecto el android.R.layout.simple_list_item_1, sin un layout customizado.
	 		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
	 		//setListAdapter(adapter);
	 		//**
	 		
	 		//PlacesArray adapter = new PlacesArray (this,values);
	 		//setListAdapter(adapter);
	}
	
	private void displayListView() {
		 
		 
		  Cursor cursor = dbHelper.fetchAllPlaces();
		 
		  // The desired columns to be bound
		  String[] columns = new String[] {
			//PlacesDbAdapter.KEY_CODE,
		    PlacesDbAdapter.KEY_NAME,
		   // CountriesDbAdapter.KEY_CONTINENT,
		    //CountriesDbAdapter.KEY_REGION
		  };
		 
		  // the XML defined views which the data will be bound to
		  int[] to = new int[] {
		    //R.id.code,
		    R.id.name,
		    //R.id.continent,
		    //R.id.region,
		  };
		 
		  // create the adapter using the cursor pointing to the desired data
		  //as well as the layout information
		  dataAdapter = new SimpleCursorAdapter(
		    this, R.layout.rowplaces,
		    cursor,
		    columns,
		    to,
		    0);
		 
		  ListView listView = (ListView) findViewById(R.id.listView1);
		  // Assign adapter to ListView
		  listView.setAdapter(dataAdapter);
    
		  
		  //listView.setOnItemClickListener(new OnItemClickListener() {
			//   @Override
			  // public void onItemClick(AdapterView<?> listView, View view,
			    // int position, long id) {
				  // Intent intent = new Intent(this, opciones_monumento.class);
					//startActivity(intent);
			   		//}			   
		  	//});
		  
	}
  
	
	
	 
		 			 
	 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(Menu.NONE, INFOAPP, 3, R.string.about);
		menu.add(Menu.NONE,SALIR, 4, R.string.exit);
		return true;
		
	}
	
	 public boolean onMenuItemSelected(int featureId, MenuItem item) {
	        super.onMenuItemSelected(featureId, item);
	              switch(item.getItemId()) {
	              case INFOAPP:
	            	  aboutDialog();
	            	  break;
	              case SALIR:
	            	  exitAppFunction();
	            	  break;
	              }
	              
	              return true;
	       }
	 
	 private void aboutDialog(){
		 
		 Dialog dial = new Dialog(MainActivity.this);
		
		 
		 dial.setTitle("Acerca de esta aplicación");
		 dial.setContentView(R.layout.infoapp_dialog);
		 TextView text_intro = (TextView) dial.findViewById(R.id.info_intro);
		 
		 text_intro.setText("Ciudad Turística es una app para no perderse por Cangas de Onís");
		 
		 dial.show();		 
	 }
	
	 private void exitAppFunction() {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("¿Seguro que quieres salir de la aplicación?")
					.setCancelable(false)
					.setPositiveButton("Si", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							MainActivity.this.finish();		                  
						}
					})
					.setNegativeButton("No", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();		           
						}
					});
			AlertDialog alert = builder.create(); 	
			alert.show();
		}
}
