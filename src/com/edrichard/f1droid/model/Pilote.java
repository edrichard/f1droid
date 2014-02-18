package com.edrichard.f1droid.model;

import java.io.Serializable;

import org.joda.time.DateTime;

public class Pilote implements Serializable {

	/** Serialize id */
	private static final long serialVersionUID = 144673748850663153L;
	/** Pilot id */
	private String id;
	/** Pilot URL */
	private String url;
	/** Pilot given name */
	private String givenName;
	/** Pilot family name */
	private String familyName;
	/** Pilot date of birthday */
	private DateTime dateOfBirth;
	/** Pilot nationality */
	private String nationality;
	
	public Pilote() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Get id Pilot.
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set id Pilot.
	 * @param driverId the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Get url Pilot.
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Set url Pilot.
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Get given name Pilot.
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * Set given name Pilot.
	 * @param givenName the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * Get family name Pilot.
	 * @return the familyName
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * Set family name Pilot.
	 * @param familyName the familyName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * Get date of birthday Pilot.
	 * @return the dateOfBirth
	 */
	public DateTime getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Set date of birthday Pilot.
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(DateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Get nationality Pilot.
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * Set nationality Pilot.
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

}
