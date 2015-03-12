package com.example.ciudadturistica2;

public class Places {
	
	//private variables
	int _id;
	String _name;
	String _lat;
	String _long;
	
	//Empty constructor
	public Places() {
		
	}
	
	// constructor
	public Places(int id, String name, String lat, String lon){
		this._id = id;
		this._name = name;
		this._lat = lat;
		_long = lon;
		
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

		public String get_name() {
			return _name;
		}

		public void set_name(String _name) {
			this._name = _name;
		}

		public String get_long() {
			return _long;
		}

		public void set_long(String _long) {
			this._long = _long;
		}

		public String get_lat() {
			return _lat;
		}

		public void set_lat(String _lat) {
			this._lat = _lat;
		}
			
	}
