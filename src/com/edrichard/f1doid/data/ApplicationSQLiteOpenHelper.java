package com.edrichard.f1doid.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ApplicationSQLiteOpenHelper extends SQLiteOpenHelper {

	public static final String DATABASE_F1DROID = "DATABASEF1DROID";
	
	public ApplicationSQLiteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Connection data base
	 * @return helper
	 */
	public static ApplicationSQLiteOpenHelper connexionDataBase(Context ctx) {
		ApplicationSQLiteOpenHelper helper = new ApplicationSQLiteOpenHelper(
				ctx,
				ApplicationSQLiteOpenHelper.DATABASE_F1DROID,
				null,
				1);
		return helper;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//Create table Circuit
		String CREATE_CIRCUIT = DAOCircuit.getSchema();
		db.execSQL(CREATE_CIRCUIT);
		
		//Create table Pilote
		String CREATE_PILOT = DAOPilote.getSchema();
		db.execSQL(CREATE_PILOT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	

}
