package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Location {

    private Coordinate coordinate;

    public Location(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void writeOn(ResultSet rset) throws SQLException {
        coordinate.writeOn(rset);
    }

    @Override
    public String toString() {
        return coordinate.toString();
    }
}
