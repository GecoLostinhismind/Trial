package com.example.ciudadturistica2;

public class Places {
	
	//private variables
	int _id;
	String _name;
	String _lat;
	String _lng;
	
	//Empty constructor
	public Places() {
		
	}
	
	// constructor
	public Places(int id, String name, String lat, String lng){
		this._id = id;
		this._name = name;
		this._lat = lat;
		_lng = lng;
		
		}
	
	// constructor
	public Places(String name){
		this._name = name;
			
		}
	
	// getting ID
	public int getID(){
		return this._id;
		}
	
	// setting id
	public void setID(int id){
		this._id = id;
		}
		
	// getting name
	public String getName(){
		return this._name;
		}
	
	// setting name
		public void setName(String name){
			this._name = name;
		}


		public String getLng() {
			return _lng;
		}

		public void setLng(String _lng) {
			this._lng = _lng;
		}

		public String getLat() {
			return _lat;
		}

		public void setLat(String _lat) {
			this._lat = _lat;
		}
			
	}
