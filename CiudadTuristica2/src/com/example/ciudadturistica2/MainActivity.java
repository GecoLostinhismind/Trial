package com.example.ciudadturistica2;


import java.util.List;








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
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	private static final int INFOAPP = Menu.FIRST;
    private static final int SALIR = Menu.FIRST+1;
    private SimpleCursorAdapter dataAdapter;
    
    //prueba
    private DatabaseHandler db;
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	//DatabaseHandler db = new DatabaseHandler(this);Esto o lo de abajo.
	db = new DatabaseHandler(this);
	
	/**
     * CRUD Operations
     * */
    // Inserting Contacts
    Log.d("Insert: ", "Inserting ..");
    db.addPlaces(new Places("Puente Romano"));
    db.addPlaces(new Places("Ermita de la Cruz"));
    db.addPlaces(new Places("Dolmen"));
    db.addPlaces(new Places("Iglesia Parroquial"));
    db.addPlaces(new Places("Estatua de Don Pelayo"));
    db.addPlaces(new Places("Aula del Reino de Asturias"));
    db.addPlaces(new Places("Capilla de SanAntonio"));
    db.addPlaces(new Places("Palacio Pintu"));
    db.addPlaces(new Places("Casa Dago"));
    
    
    
    //PRUEBA
    displayListView();
    
   
    
    // Reading all contacts
   // Log.d("Reading: ", "Reading all contacts..");
   // List<Places> places = db.getAllPlaces();       

   // for (Places pl : places) {
     //   String log = "Id: "+pl.getID()+" ,Name: " + pl.getName();
            // Writing Contacts to log
    //Log.d("Name: ", log);
    
   // }
	
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
	
	
	//PRUEBA
	 public void displayListView() {
    	 
   	  Cursor cursor = db.getAllPlaces();
   	 
   	  // The desired columns to be bound
   	  String[] columns = new String[] {
   	    DatabaseHandler.KEY_NAME
   	  };
   	 
   	  // the XML defined views which the data will be bound to
   	  int[] to = new int[] {
   	    R.id.label,
   	  };
   	 
   	  // create the adapter using the cursor pointing to the desired data
   	  //as well as the layout information
   	  dataAdapter = new SimpleCursorAdapter(
   	    this, R.layout.rowplaces,
   	    cursor,
   	    columns,
   	    to,
   	    0);
   	 
   	  
   	  // Assign adapter to ListView
   	  setListAdapter(dataAdapter);
   }
   //PRUEBA
	
	
	 protected void onListItemClick(ListView l, View v, int position, long id) {		 		 
				 
		Intent intent = new Intent(this, opciones_monumento.class);
		startActivity(intent);
		 			 
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
