package com.edrichard.f1droid.model;

import java.io.Serializable;

import org.joda.time.DateTime;

/**
 * Class of pilote.
 * @author edrichard.
 */
public class Pilote implements Serializable {

    /** Serialize id. */
    private static final long serialVersionUID = 144673748850663153L;
    /** Pilot id. */
    private String id;
    /** Pilot URL. */
    private String url;
    /** Pilot given name. */
    private String givenName;
    /** Pilot family name. */
    private String familyName;
    /** Pilot date of birthday. */
    private DateTime dateOfBirth;
    /** Pilot nationality. */
    private String nationality;

    /**
     * Constructor is empty.
     */
    public Pilote() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Get id Pilot.
     * @return the id
     */
    public final String getId() {
        return id;
    }

    /**
     * Set id Pilot.
     * @param idPilote the id to set
     */
    public final void setId(final String idPilote) {
        this.id = idPilote;
    }

    /**
     * Get url Pilot.
     * @return the url
     */
    public final String getUrl() {
        return url;
    }

    /**
     * Set url Pilot.
     * @param urlPilote the url to set
     */
    public final  void setUrl(final String urlPilote) {
        this.url = urlPilote;
    }

    /**
     * Get given name Pilot.
     * @return the givenName
     */
    public final String getGivenName() {
        return givenName;
    }

    /**
     * Set given name Pilot.
     * @param givenNamePilote the givenName to set
     */
    public final void setGivenName(final String givenNamePilote) {
        this.givenName = givenNamePilote;
    }

    /**
     * Get family name Pilot.
     * @return the familyName
     */
    public final String getFamilyName() {
        return familyName;
    }

    /**
     * Set family name Pilot.
     * @param familyNamePilote the familyName to set
     */
    public final void setFamilyName(final String familyNamePilote) {
        this.familyName = familyNamePilote;
    }

    /**
     * Get date of birthday Pilot.
     * @return the dateOfBirth
     */
    public final DateTime getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Set date of birthday Pilot.
     * @param dateOfBirthPilote the dateOfBirth to set
     */
    public final void setDateOfBirth(final DateTime dateOfBirthPilote) {
        this.dateOfBirth = dateOfBirthPilote;
    }

    /**
     * Get nationality Pilot.
     * @return the nationality
     */
    public final String getNationality() {
        return nationality;
    }

    /**
     * Set nationality Pilot.
     * @param nationalityPilote the nationality to set
     */
    public final void setNationality(final String nationalityPilote) {
        this.nationality = nationalityPilote;
    }

}
