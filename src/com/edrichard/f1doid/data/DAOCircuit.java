package com.edrichard.f1doid.data;

import java.util.ArrayList;

import com.edrichard.f1droid.model.Circuit;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DAOCircuit {

	/** Table Circuit */
	public static final String CIRCUIT = "circuit";
	public static final String CIRCUIT_ID = "id";
	public static final String CIRCUIT_NAME = "name";
	public static final String CIRCUIT_LAT = "lat";
	public static final String CIRCUIT_LONG = "long";
	public static final String CIRCUIT_LOCALITY = "locality";
	public static final String CIRCUIT_COUNTRY = "country";
	public static final String CIRCUIT_URL = "url";
	
	/** SQLiteDatabase object */
	private SQLiteDatabase db;
	
	public DAOCircuit(SQLiteDatabase db) {
		this.db = db; 
	}
	
	/**
	 * Return the schema of table 'circuit'
	 * @return
	 */
	public static String getSchema(){
		return "create table " + CIRCUIT + " (" +
				CIRCUIT_ID + " VARCHAR(255) NOT NULL, " +
				CIRCUIT_NAME + " VARCHAR(255) NOT NULL, " +
				CIRCUIT_LAT + " DECIMAL(3,4) NOT NULL, " +
				CIRCUIT_LONG + " DECIMAL(3,4) NOT NULL, " +
				CIRCUIT_LOCALITY + " VARCHAR(255) NOT NULL, " +
				CIRCUIT_COUNTRY + " VARCHAR(255) NOT NULL, " +
				CIRCUIT_URL + " TEXT" +
				")";
	}
	
	/**
	 * Add Circuit.
	 * @param circuit
	 */
	public void addCircuit(Circuit circuit) {
		ContentValues item = new ContentValues();
		item.put(CIRCUIT_COUNTRY, circuit.getCountry());
		item.put(CIRCUIT_ID, circuit.getId());
		item.put(CIRCUIT_LAT, circuit.getLatitude());
		item.put(CIRCUIT_LOCALITY, circuit.getLocacity());
		item.put(CIRCUIT_LONG, circuit.getLongitude());
		item.put(CIRCUIT_NAME, circuit.getName());
		item.put(CIRCUIT_URL, circuit.getUrl());
		db.insert(CIRCUIT, null, item);
	}
	
	/**
	 * Return all circuit
	 * @return circuit
	 */
	public ArrayList<Circuit> getAllCircuit() {
		ArrayList<Circuit> circuits = new ArrayList<Circuit>();
		
		String[] COL = {CIRCUIT_ID, 
				CIRCUIT_NAME, 
				CIRCUIT_LAT, 
				CIRCUIT_LONG, 
				CIRCUIT_COUNTRY, 
				CIRCUIT_LOCALITY, 
				CIRCUIT_URL};
		
		String orderBy = CIRCUIT_NAME + " ASC";

		Cursor c = db.query(CIRCUIT, COL, null, null, null, null, orderBy);

		if (c.getCount()>0) {
			c.moveToFirst();
			Circuit itemCircuit;
			do {
				itemCircuit = new Circuit();
				itemCircuit.setId(c.getString(c.getColumnIndex(CIRCUIT_ID)));
				itemCircuit.setName(c.getString(c.getColumnIndex(CIRCUIT_NAME)));
				itemCircuit.setLatitude(c.getDouble(c.getColumnIndex(CIRCUIT_LAT)));
				itemCircuit.setLongitude(c.getDouble(c.getColumnIndex(CIRCUIT_LONG)));
				itemCircuit.setLocacity(c.getString(c.getColumnIndex(CIRCUIT_LOCALITY)));
				itemCircuit.setCountry(c.getString(c.getColumnIndex(CIRCUIT_COUNTRY)));
				itemCircuit.setUrl(c.getString(c.getColumnIndex(CIRCUIT_URL)));
				
				circuits.add(itemCircuit);
			} while (c.moveToNext());
		}
		
		return circuits;
	}
	
	/**
	 * Get Circuit by id.
	 * @param circuit
	 */
	public void getCircuit(Circuit circuit) {
		
		String[] COLS = {CIRCUIT_NAME, 
				CIRCUIT_LAT, 
				CIRCUIT_LONG, 
				CIRCUIT_COUNTRY, 
				CIRCUIT_LOCALITY, 
				CIRCUIT_URL};
		String whereClause = CIRCUIT_ID + " = ?";
		String[] VALUES = {String.valueOf(circuit.getId())};
		
		Cursor c = db.query(CIRCUIT, COLS, whereClause, VALUES, null, null, null);
		
		if (c.getCount()>0) {
			c.moveToFirst();
			circuit.setName(c.getString(c.getColumnIndex(CIRCUIT_NAME)));
			circuit.setLatitude(c.getDouble(c.getColumnIndex(CIRCUIT_LAT)));
			circuit.setLongitude(c.getDouble(c.getColumnIndex(CIRCUIT_LONG)));
			circuit.setLocacity(c.getString(c.getColumnIndex(CIRCUIT_LOCALITY)));
			circuit.setCountry(c.getString(c.getColumnIndex(CIRCUIT_COUNTRY)));
			circuit.setUrl(c.getString(c.getColumnIndex(CIRCUIT_URL)));
		}
	}
	
	public Boolean getCircuitExist(String circuitId) {
		String[] COLS = {CIRCUIT_ID};
		String whereClause = CIRCUIT_ID + " = ?";
		String[] VALUES = {String.valueOf(circuitId)};

		Cursor c = db.query(CIRCUIT, COLS, whereClause, VALUES, null, null, null);

		return (c.getCount() > 0);
	}

}
