package com.edrichard.f1droid.model;

import java.io.Serializable;

/**
 * Class Circuit.
 * @author edrichard.
 */
public class Circuit implements Serializable {

    /** Serialize id. */
    private static final long serialVersionUID = -7008467969647078905L;
    /** Circuit id. */
    private String id;
    /** Circuit name. */
    private String name;
    /** Circuit longitude. */
    private double longitude;
    /** Circuit latitude. */
    private double latitude;
    /** Circuit locacity. */
    private String locacity;
    /** Circuit country. */
    private String country;
    /**Circuit URL. */
    private String url;

    /**
     * Constructor is empty.
     */
    public Circuit() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Constructor.
     * @param idCircuit of circuit.
     * @param nameCircuit of circuit.
     * @param longitudeCircuit of circuit.
     * @param latitudeCircuit of circuit.
     * @param locacityCircuit of circuit.
     * @param countryCircuit of circuit.
     * @param urlCircuit of circuit.
     */
    public Circuit(
            final String idCircuit,
            final String nameCircuit,
            final Double longitudeCircuit,
            final Double latitudeCircuit,
            final String locacityCircuit,
            final String countryCircuit,
            final String urlCircuit) {
        super();
        this.id = idCircuit;
        this.name = nameCircuit;
        this.longitude = longitudeCircuit;
        this.latitude = latitudeCircuit;
        this.locacity = locacityCircuit;
        this.country = countryCircuit;
        this.url = urlCircuit;

    }

    /**
     * Get id Circuit.
     * @return the id
     */
    public final String getId() {
        return id;
    }

    /**
     * Set id Circuit.
     * @param circuitId the id to set
     */
    public final void setId(final String circuitId) {
        this.id = circuitId;
    }

    /**
     * Get name Circuit.
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * Set name Circuit.
     * @param nameCircuit the name to set
     */
    public final void setName(final String nameCircuit) {
        this.name = nameCircuit;
    }

    /**
     * Get longitude Circuit.
     * @return the longitude
     */
    public final double getLongitude() {
        return longitude;
    }

    /**
     * Set longitude Circuit.
     * @param longitudeCircuit the longitude to set
     */
    public final void setLongitude(final double longitudeCircuit) {
        this.longitude = longitudeCircuit;
    }

    /**
     * Get latitude Circuit.
     * @return the latitude
     */
    public final double getLatitude() {
        return latitude;
    }

    /**
     * Set latitude Circuit.
     * @param latCircuit the latitude to set
     */
    public final void setLatitude(final double latCircuit) {
        this.latitude = latCircuit;
    }

    /**
     * Get locacity Circuit.
     * @return the locacity
     */
    public final String getLocacity() {
        return locacity;
    }

    /**
     * Set locacity Circuit.
     * @param locacityCircuit the locacity to set
     */
    public final void setLocacity(final String locacityCircuit) {
        this.locacity = locacityCircuit;
    }

    /**
     * Get country Circuit.
     * @return the country
     */
    public final String getCountry() {
        return country;
    }

    /**
     * Set country Circuit.
     * @param countryCircuit the country to set
     */
    public final void setCountry(final String countryCircuit) {
        this.country = countryCircuit;
    }

    /**
     * Get url Circuit.
     * @return the url
     */
    public final String getUrl() {
        return url;
    }

    /**
     * Set url Circuit.
     * @param urlCircuit the url to set
     */
    public final void setUrl(final String urlCircuit) {
        this.url = urlCircuit;
    }
}
