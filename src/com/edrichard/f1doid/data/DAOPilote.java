package com.edrichard.f1doid.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;

import com.edrichard.f1droid.model.Pilote;
import com.edrichard.f1droid.util.DateUtils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
/**
 * Classe DOAPilote.
 * @author edrichard
 */
@SuppressLint("SimpleDateFormat")
public class DAOPilote {

    /** Pilot table. */
    public static final String PILOT = "pilot";
    /** id pilot field. */
    public static final String PILOT_ID = "id";
    /** url pilot field. */
    public static final String PILOT_URL = "url";
    /** nameGiven pilot field. */
    public static final String PILOT_NAME_GIVEN = "nameGiven";
    /** nameFamily pilot field. */
    public static final String PILOT_NAME_FAMILY = "nameFamily";
    /** dateOfBirth pilot field. */
    public static final String PILOT_DATE_OF_BIRTH = "dateOfBirth";
    /** nationality pilot field. */
    public static final String PILOT_NATIONALITY = "nationality";

    /** SQLiteDatabase object. */
    private final SQLiteDatabase db;

    /**
     * Constructor.
     * @param database connection.
     */
    public DAOPilote(final SQLiteDatabase database) {
        this.db = database;
    }

    /**
     * Return the schema of table 'pilot'.
     * @return table of pilot.
     */
    public final String getSchema() {
        return "create table " + PILOT + " ("
                + PILOT_ID + " VARCHAR(255) NOT NULL, "
                + PILOT_URL + " TEXT NOT NULL, "
                + PILOT_NAME_GIVEN + " VARCHAR(255) NOT NULL, "
                + PILOT_NAME_FAMILY + " VARCHAR(3,4) NOT NULL, "
                + PILOT_DATE_OF_BIRTH + " DATETIME, "
                + PILOT_NATIONALITY + " VARCHAR(255) NOT NULL "
                + ")";
    }

    /**
     * Add pilot of database.
     * @param pilote object.
     * @param ctx of application.
     */
    public final void addPilote(final Pilote pilote, final Context ctx) {
        ContentValues item = new ContentValues();
        item.put(PILOT_DATE_OF_BIRTH,
                DateUtils.formatDateTimeToString(
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
     * @return pilote of database.
     */
    public final ArrayList<Pilote> getAllPilote() {
        ArrayList<Pilote> pilotes = new ArrayList<Pilote>();

        String[] cols = {PILOT_ID,
                PILOT_NAME_GIVEN,
                PILOT_NAME_FAMILY,
                PILOT_DATE_OF_BIRTH,
                PILOT_NATIONALITY,
                PILOT_URL};

        String orderBy = PILOT_NAME_GIVEN + " ASC";

        Cursor c = db.query(PILOT, cols, null, null, null, null, orderBy);

        if (c.getCount() > 0) {
            c.moveToFirst();
            Pilote itemPilote;
            do {
                SimpleDateFormat dateFormat =
                        new SimpleDateFormat(
                                "dd/MM/yyyy HH:mm",
                                new Locale("FR", "fr"));
                Date maDate = null;
                try {
                    maDate = dateFormat.parse(c.getString(
                            c.getColumnIndex(PILOT_DATE_OF_BIRTH)));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                DateTime dateTime = new DateTime(maDate);

                itemPilote = new Pilote();
                itemPilote.setId(c.getString(
                        c.getColumnIndex(PILOT_ID)));
                itemPilote.setGivenName(c.getString(
                        c.getColumnIndex(PILOT_NAME_GIVEN)));
                itemPilote.setFamilyName(c.getString(
                        c.getColumnIndex(PILOT_NAME_FAMILY)));
                itemPilote.setNationality(c.getString(
                        c.getColumnIndex(PILOT_NATIONALITY)));
                itemPilote.setUrl(c.getString(
                        c.getColumnIndex(PILOT_URL)));
                itemPilote.setDateOfBirth(dateTime);
                pilotes.add(itemPilote);
            } while (c.moveToNext());
        }

        return pilotes;
    }

    /**
     * Return one the pilot.
     * @param pilote of database.
     */
    public final void getPilote(final Pilote pilote) {

        String[] cols = {PILOT_NAME_GIVEN,
                PILOT_NAME_FAMILY,
                PILOT_DATE_OF_BIRTH,
                PILOT_NATIONALITY,
                PILOT_URL};
        String whereClause = PILOT_ID + " = ?";
        String[] values = {String.valueOf(pilote.getId())};

        Cursor c =
                db.query(PILOT, cols, whereClause, values, null, null, null);

        if (c.getCount() > 0) {
            c.moveToFirst();
            pilote.setGivenName(c.getString(
                    c.getColumnIndex(PILOT_NAME_GIVEN)));
            pilote.setFamilyName(c.getString(
                    c.getColumnIndex(PILOT_NAME_FAMILY)));
            pilote.setNationality(c.getString(
                    c.getColumnIndex(PILOT_NATIONALITY)));
            pilote.setUrl(c.getString(
                    c.getColumnIndex(PILOT_URL)));
            pilote.setDateOfBirth(
                    DateUtils.formatLocalISOStringToDateTime(
                            c.getString(
                                    c.getColumnIndex(PILOT_DATE_OF_BIRTH))));
        }
    }

    /**
     * Pilote of exist on the database.
     * @param piloteId on the pilot.
     * @return boolean if the pilot exist.
     */
    public final Boolean getPiloteExist(final String piloteId) {
        final String[] cols = {PILOT_ID};
        final String whereClause = PILOT_ID + " = ?";
        String[] values = {String.valueOf(piloteId)};

        Cursor c =
                db.query(PILOT, cols, whereClause, values, null, null, null);

        return (c.getCount() > 0);
    }
}
