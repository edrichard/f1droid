package com.edrichard.f1doid.data;

import java.util.ArrayList;

import com.edrichard.f1droid.model.Pilote;
import com.edrichard.f1droid.util.DateUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DAOPilote {

	/** Pilot table */
	public static final String PILOT = "pilot";
	public static final String PILOT_ID = "id";
	public static final String PILOT_URL = "url";
	public static final String PILOT_NAME_GIVEN = "nameGiven";
	public static final String PILOT_NAME_FAMILY = "nameFamily";
	public static final String PILOT_DATE_OF_BIRTH = "dateOfBirth";
	public static final String PILOT_NATIONALITY = "nationality";
	
	/** SQLiteDatabase object */
	private SQLiteDatabase db;
	
	public DAOPilote(SQLiteDatabase db) {
		this.db = db; 
	}
	
	/**
	 * Return the schema of table 'pilot'
	 * @return
	 */
	public static String getSchema(){
		return "create table " + PILOT + " (" +
				PILOT_ID + " VARCHAR(255) NOT NULL, " +
				PILOT_URL + " TEXT NOT NULL, " +
				PILOT_NAME_GIVEN + " VARCHAR(255) NOT NULL, " +
				PILOT_NAME_FAMILY + " VARCHAR(3,4) NOT NULL, " +
				PILOT_DATE_OF_BIRTH + " DATETIME NOT NULL, " +
				PILOT_NATIONALITY + " VARCHAR(255) NOT NULL " +
				")";
	}
	
	/**
	 * Add Circuit.
	 * @param circuit
	 */
	public void addPilote(Pilote pilote, Context ctx) {
		ContentValues item = new ContentValues();
		item.put(PILOT_DATE_OF_BIRTH, 
				DateUtils.formatTimeToString(
						pilote.getDateOfBirth(), ctx));
		item.put(PILOT_ID, pilote.getId());
		item.put(PILOT_NAME_FAMILY, pilote.getFamilyName());
		item.put(PILOT_NAME_GIVEN, pilote.getGivenName());
		item.put(PILOT_NATIONALITY, pilote.getNationality());
		item.put(PILOT_URL, pilote.getUrl());
		db.insert(PILOT, null, item);
	}

	/**
	 * Return all Pilot.
	 * @return pilote
	 */
	public ArrayList<Pilote> getAllPilote() {
		ArrayList<Pilote> pilotes = new ArrayList<Pilote>();
		
		String[] COL = {PILOT_ID,
				PILOT_NAME_GIVEN,
				PILOT_NAME_FAMILY,
				PILOT_DATE_OF_BIRTH,
				PILOT_NATIONALITY,
				PILOT_URL};
		
		String orderBy = PILOT_NAME_GIVEN + " ASC";

		Cursor c = db.query(PILOT, COL, null, null, null, null, orderBy);

		if (c.getCount()>0) {
			c.moveToFirst();
			Pilote itemPilote;
			do {
				itemPilote = new Pilote();
				itemPilote.setId(c.getString(c.getColumnIndex(PILOT_ID)));
				itemPilote.setGivenName(c.getString(c.getColumnIndex(PILOT_NAME_GIVEN)));
				itemPilote.setFamilyName(c.getString(c.getColumnIndex(PILOT_NAME_FAMILY)));
				itemPilote.setNationality(c.getString(c.getColumnIndex(PILOT_NATIONALITY)));
				itemPilote.setUrl(c.getString(c.getColumnIndex(PILOT_URL)));
				itemPilote.setDateOfBirth(
						DateUtils.formatLocalISOStringToDateTime(
								c.getString(c.getColumnIndex(PILOT_DATE_OF_BIRTH))));
				
				pilotes.add(itemPilote);
			} while (c.moveToNext());
		}
		
		return pilotes;
	}
	
	/**
	 * Get Circuit by id.
	 * @param circuit
	 */
	public void getPilote(Pilote pilote) {
		
		String[] COLS = {PILOT_NAME_GIVEN,
				PILOT_NAME_FAMILY,
				PILOT_DATE_OF_BIRTH,
				PILOT_NATIONALITY,
				PILOT_URL};
		String whereClause = PILOT_ID + " = ?";
		String[] VALUES = {String.valueOf(pilote.getId())};
		
		Cursor c = db.query(PILOT, COLS, whereClause, VALUES, null, null, null);
		
		if (c.getCount()>0) {
			c.moveToFirst();
			pilote.setGivenName(c.getString(c.getColumnIndex(PILOT_NAME_GIVEN)));
			pilote.setFamilyName(c.getString(c.getColumnIndex(PILOT_NAME_FAMILY)));
			pilote.setNationality(c.getString(c.getColumnIndex(PILOT_NATIONALITY)));
			pilote.setUrl(c.getString(c.getColumnIndex(PILOT_URL)));
			pilote.setDateOfBirth(
					DateUtils.formatLocalISOStringToDateTime(
							c.getString(c.getColumnIndex(PILOT_DATE_OF_BIRTH))));
		}
	}
	
	public Boolean getPiloteExist(String piloteId) {
		String[] COLS = {PILOT_ID};
		String whereClause = PILOT_ID + " = ?";
		String[] VALUES = {String.valueOf(piloteId)};

		Cursor c = db.query(PILOT, COLS, whereClause, VALUES, null, null, null);

		return (c.getCount() > 0);
	}
}
