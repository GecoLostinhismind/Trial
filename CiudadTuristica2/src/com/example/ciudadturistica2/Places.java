package com.example.ciudadturistica2;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

public class Places {
		 String image = null; 
		 String code = null;
		 String name = null;
		 String stream = null;
		 //String continent = null;
		 //String region = null;
		  
		 public String getCode() {
		  return code;
		 }
		 public void setCode(String code) {
		  this.code = code;
		 }
		 public String getName() {
		  return name;
		 }
		 public void setName(String name) {
		  this.name = name;
		 }
		 
		 //Esto es para obtener la imagen
		 public static byte[] getBytes(Bitmap bitmap) {
			 ByteArrayOutputStream stream = new ByteArrayOutputStream();
			 bitmap.compress(CompressFormat.PNG, 0, stream);
			 return stream.toByteArray();
			 }

			 // Convierte el byte array en bitmap 
		 public static Bitmap getPhoto(byte[] image) {
			 return BitmapFactory.decodeByteArray(image, 0, image.length);
			 }
		 
		 //public String getContinent() {
		 // return continent;
		 //}
		 //public void setContinent(String continent) {
		  //this.continent = continent;
		 //}
		 //public String getRegion() {
		  //return region;
		 //}
		 //public void setRegion(String region) {
		  //this.region = region;
		 //}
			
	}
