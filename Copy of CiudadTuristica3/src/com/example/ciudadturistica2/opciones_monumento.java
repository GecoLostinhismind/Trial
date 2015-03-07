package com.example.ciudadturistica2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class opciones_monumento extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.opciones_monumento);
		
		Button home= (Button)findViewById(R.id.Home);
        home.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();
				
			}		
        });
        
        Button mapa=(Button)findViewById(R.id.Mapa);
        mapa.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View v) {
        		Intent intentmapa = new Intent(opciones_monumento.this, Mapa.class);
        		startActivity(intentmapa);
        	}
        });

		
	}
	
	

}
