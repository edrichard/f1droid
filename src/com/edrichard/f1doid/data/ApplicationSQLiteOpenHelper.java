package com.edrichard.f1doid.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Create database.
 * @author edrichard
 */
public class ApplicationSQLiteOpenHelper extends SQLiteOpenHelper {

    /** Name of database. */
    public static final String DATABASE_F1DROID = "DATABASEF1DROID";
    /** Name of database. */
    public static final Integer VERSION_DB = 1;
    
    /**
     * Constructor.
     * @param context of the application.
     * @param name of the application.
     * @param factory of the application.
     * @param version of the application.
     */
    public ApplicationSQLiteOpenHelper(
            final Context context,
            final String name,
            final CursorFactory factory,
            final int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Constructor.
     * @param context of application.
     */
    public ApplicationSQLiteOpenHelper(final Context context) {
        super(context, DATABASE_F1DROID, null, VERSION_DB);
        // TODO Auto-generated constructor stub
    }

    /**
     * Connection data base.
     * @param ctx of application.
     * @return helper.
     */
    public static ApplicationSQLiteOpenHelper connexionDataBase(
            final Context ctx) {
        ApplicationSQLiteOpenHelper helper = new ApplicationSQLiteOpenHelper(
                ctx,
                ApplicationSQLiteOpenHelper.DATABASE_F1DROID,
                null,
                VERSION_DB);
        return helper;
    }

    /**
     * on create database.
     * @param db SQLiteDatabase.
     */
    @Override
    public final void onCreate(final SQLiteDatabase db) {
        String createCircuit = DAOCircuit.getSchema();
        db.execSQL(createCircuit);

        String createPilot = DAOPilote.getSchema();
        db.execSQL(createPilot);
    }

    /**
     * on upgrade database.
     * @param db SQLiteDatabase.
     * @param oldVersion old version of application.
     * @param newVersion new version of application.
     */
    @Override
    public final void onUpgrade(
            final SQLiteDatabase db,
            final int oldVersion,
            final int newVersion) {
        // TODO Auto-generated method stub

    }
}
