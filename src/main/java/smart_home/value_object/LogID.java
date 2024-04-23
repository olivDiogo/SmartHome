package smart_home.value_object;

import smart_home.ddd.IDomainID;

public class LogID implements IDomainID {

    private  String _logID;

    /**
     * Constructor for LogID
     * @param logID String
     */
    public LogID(String logID) {
        validateLogID(logID);
        this._logID = logID.trim();
    }

    private void validateLogID(String logID) {
        if (logID == null || logID.isBlank())
            throw new IllegalArgumentException("The value of 'logID' should not null, blank, or empty.");
    }

    /**
     * Getter for ID
     * @return the logID
     */
    @Override
    public String getID() {
        return _logID;
    }

    /**
     * Equals method for LogID
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o instanceof LogID objectLogID) {

            return this._logID.equals(objectLogID._logID);
        }
        return false;
    }

    /**
     * HashCode method for LogID
     * @return the hashcode as an int
     */
    @Override
    public int hashCode() {
        return _logID.hashCode();
    }

    /**
     * toString method for LogID
     * @return the logID as a string
     */
    @Override
    public String toString() {
        return _logID;
    }

}
