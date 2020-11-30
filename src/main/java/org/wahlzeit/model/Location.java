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
        coordinate.asCartesianCoordinate().writeOn(rset);
    }

    @Override
    public String toString() {
        return coordinate.asCartesianCoordinate().getX() + "/" + coordinate.asCartesianCoordinate().getY() + "/"
                + coordinate.asCartesianCoordinate().getZ();
    }
}
