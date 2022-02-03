package model;

/**
 * Represents an entry in a log.
 */
public interface Entry {

    String getLocation();

    String getDate();

    int getSeverity();

    int getDuration();

    String getSensation();

    String getRemedy();


}
