package com.example.ciudadturistica2;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final int INFOAPP = Menu.FIRST;
    private static final int SALIR = Menu.FIRST+1;
    
    private PlacesDbAdapter dbHelper;
    private Adapter dataAdapter;
    private ArrayList<String> places;
    
     
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbHelper = new PlacesDbAdapter(this);
	   
	    
	   
	     //Add some data
	     //dbHelper.insertSomePlaces();
	    
	    
	     
		  
		  places = new ArrayList<String>();
		  // create the adapter using the cursor pointing to the desired data
		  //as well as the layout information
		  dataAdapter = new Adapter(this, R.layout.rowplaces, places);
		 
		  ListView listView = (ListView) findViewById(R.id.listView1);
		  // Assign adapter to ListView
		  listView.setAdapter(this.dataAdapter);


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
	
	
		
	
			  
		  
		  
		  //listView.setOnItemClickListener(new OnItemClickListener() {
			//   @Override
			  // public void onItemClick(AdapterView<?> listView, View view,
			    // int position, long id) {
				  // Intent intent = new Intent(this, opciones_monumento.class);
					//startActivity(intent);
			   		//}			   
		  	//});
		  
	
  
	
	
	 
		 			 
	 

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
	     	case INFOAPP: aboutDialog(); break;
	        case SALIR: exitAppFunction(); break;
	        default:;
	            	  
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
	 
	 private class Adapter extends ArrayAdapter<String> {
		private ArrayList<String> mitems;
		
		public Adapter(Context context, int textViewResourceId, ArrayList<String> mitems) {
			super(context, textViewResourceId, mitems);
			this.mitems = mitems;
		}
		
		//@Override
		//public View getView(int position, View convertView, ViewGroup parent) {
				//LayoutInflater inflater =  (LayoutInflater).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				//View rowView = inflater.inflate(R.layout.rowplaces, null);
				//TextView textView = (TextView) rowView.findViewById(R.id.name);
				//ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
				//textView.setText(mitems.get(position));
				//Change the icon for windows and iphone. 
				//imageView.setImageResource(R.drawable.icon);			
			//	return rowView;		
		//}
		
		@Override
        public View getView(int position, View convertView, ViewGroup parent) {
                View v = convertView;
                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.rowplaces, null);
                }
                String place = mitems.get(position);
                if (place != null){
                	TextView row_textview = (TextView) v.findViewById(R.id.name);
                	if(row_textview !=null)
                		row_textview.setText(place);
                }
                return v;
        }
	}
}
