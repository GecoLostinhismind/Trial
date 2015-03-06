package com.example.ciudadturistica2;





import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	private static final int INFOAPP = Menu.FIRST;
    private static final int SALIR = Menu.FIRST+1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	
	String[] values = new String [] {"Estatua", "Puente", "Parque",
	        "Bar", "Río", "Roca", "Plaza", "Teatro",
	        "Ruinas", "KFC"};
		
		//**Este Array usa un adapter que usa por defecto el android.R.layout.simple_list_item_1, sin un layout customizado.
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
		//**
	}
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
