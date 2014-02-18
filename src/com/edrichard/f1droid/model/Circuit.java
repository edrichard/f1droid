package com.edrichard.f1droid.model;

import java.io.Serializable;

public class Circuit implements Serializable {

	/** Serialize id */
	private static final long serialVersionUID = -7008467969647078905L;
	/** Circuit id */
	private String id;
	/** Circuit name */
	private String name;
	/** Circuit longitude */
	private double longitude;
	/** Circuit latitude */
	private double latitude;
	/** Circuit locacity */
	private String locacity;
	/** Circuit country */
	private String country;
	/**Circuit URL */
	private String url; 
	
	public Circuit() {
		// TODO Auto-generated constructor stub
	}

	public Circuit(String id, String name, Double longitude, Double latitude,
			String locacity, String country, String url) {
		super();
		this.id = id;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.locacity = locacity;
		this.country = country;
		this.url = url;
		
	}

	/**
	 * Get id Circuit.
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set id Circuit.
	 * @param circuitId the id to set
	 */
	public void setId(String circuitId) {
		this.id = circuitId;
	}

	/**
	 * Get name Circuit.
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name Circuit.
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get longitude Circuit.
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Set longitude Circuit.
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Get latitude Circuit.
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Set latitude Circuit.
	 * @param lat the latitude to set
	 */
	public void setLatitude(double lat) {
		this.latitude = lat;
	}

	/**
	 * Get locacity Circuit.
	 * @return the locacity
	 */
	public String getLocacity() {
		return locacity;
	}

	/**
	 * Set locacity Circuit.
	 * @param locacity the locacity to set
	 */
	public void setLocacity(String locacity) {
		this.locacity = locacity;
	}

	/**
	 * Get country Circuit.
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Set country Circuit.
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Get url Circuit.
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Set url Circuit.
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

}
