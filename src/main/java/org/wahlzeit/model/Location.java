package org.wahlzeit.model;

public class Location {
    
    private Coordinate coordinate;

    public Location(Coordinate coordinate)
    {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return coordinate.getX() + "/" + coordinate.getY() + "/" + coordinate.getZ();
    }
}
