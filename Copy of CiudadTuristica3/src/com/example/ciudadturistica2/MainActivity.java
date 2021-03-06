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
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	//variables para el options menu
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
	   
		//Estos son los lugares para el menu principal que he he estado intentando meter y no he conseguido.
		dbHelper.createPlace(new Places("Puente Romano"));
		dbHelper.createPlace(new Places("Ermita de la Cruz"));
		dbHelper.createPlace(new Places("Dolmen"));
		dbHelper.createPlace(new Places("Iglesia Parroquial"));
		dbHelper.createPlace(new Places("Estatua de DonPelayo"));
		dbHelper.createPlace(new Places("Aula del reino de Asturias"));
		dbHelper.createPlace(new Places("Capilla de San Antonio"));
		dbHelper.createPlace(new Places("Palacio Pintu"));
		dbHelper.createPlace(new Places("Plaza del mercado"));
		dbHelper.createPlace(new Places("Casa Dago"));
	    
	    
	     
		  
		  places = new ArrayList<String>();
		  
		  dataAdapter = new Adapter(this, R.layout.rowplaces, places);
		  
		  ListView listView = (ListView) findViewById(R.id.listView1);
		  // Assign adapter to ListView
		  listView.setAdapter(this.dataAdapter);
		  
		  //Aqu� necesitaria un OnItemClickListener que abriese un Context Menu para cada item.
		  //El Context Menu constar�a de 4 opciones:
		  	//-Mostrar en el mapa (muestra la ubicacion en el mapa, con su marker y con zoom sobre el marker (creo que se llama Camera Animate)
		   //-Usar Brujula (inicia la activity de AR con la camara y el icono que representa a la brujula indicandole en que direccion se encuentra en relaci�n a �l (la tipica brujula de videojuego vamos)
		  //-Galeria (Abre una galeria de fotos del lugar)
		  //-Me queda la opcion de que la camara reconozca que esta enfocando un lugar de la lista y haga la foto y quede almacenada en la galer�a del lugar correspondiente. Podr�a ser un bot�n al lado de la list, no lo se!

		  //Este boton es una prueba, ya que no consegu�a sacar la base de datos probe si era capaz 
		  //de iniciarme la activity por la buenas de mapa.java e incluir manualmente los markers en el mapa.		  
		  Button mapButton = (Button)findViewById(R.id.MapView);
		  mapButton.setOnClickListener(new OnClickListener() {
	        	
	        	public void onClick(View v) {
	        		Intent intentmapa = new Intent(MainActivity.this, Mapa.class);
	        		startActivity(intentmapa);
	        	}
	        });
		  
		  
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
	     	case INFOAPP: aboutDialog(); break; //Ventana que explica algunos datos de la app(Para que sirve, quien la ha hecho)
	        case SALIR: exitAppFunction(); break;//salir de la app
	        default:;
	            	  
	              }
	              
	     return true;
	       }

	 //El dialogo obtenido por INFOAPP
	 //S fuera posible dejar esto as� para que yo luego a�ada el texto necesario...
	 private void aboutDialog(){
		 
		 Dialog dial = new Dialog(MainActivity.this);
		
		 
		 dial.setTitle("Acerca de esta aplicaci�n");
		 dial.setContentView(R.layout.infoapp_dialog);
		 TextView text_intro = (TextView) dial.findViewById(R.id.info_intro);
		 
		 text_intro.setText("Ciudad Tur�stica es una app para no perderse por Cangas de On�s");
		 
		 dial.show();		 
	 }
	
	 private void exitAppFunction() {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("�Seguro que quieres salir de la aplicaci�n?")
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
	
	 //El adapter que me ense�aste de tu baase de datos. Con el pretendo recuperar la base datos y volcar los datos en la ListView de esta activity. 
	 //Supongo que al no tener items a�adidos correctamente en la base de datos pues no me muestra nada, que es el gran problema que llev� a mi desesperaci�n.
	 private class Adapter extends ArrayAdapter<String> {
		private ArrayList<String> mitems;
		
		public Adapter(Context context, int textViewResourceId, ArrayList<String> mitems) {
			super(context, textViewResourceId, mitems);
			this.mitems = mitems;
		}
		
		@Override
        public View getView(int position, View convertView, ViewGroup parent) {
                View v = convertView;
                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.rowplaces, null);//rowplaces es el layout de cada celda.
                }
                String places = mitems.get(position);
                if (places != null){
                	TextView row_textview = (TextView) v.findViewById(R.id.name);
                	if(row_textview !=null)
                		row_textview.setText(places);
                }
                return v;
        }
	}
}
