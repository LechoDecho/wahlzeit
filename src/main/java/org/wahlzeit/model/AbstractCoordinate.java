package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractCoordinate implements Coordinate {

    protected static final double THRESHOLD = 0.00001d;

    @Override
    public double getCartesianDistance(Coordinate coordinate) {

        if (coordinate == null) {
            throw new IllegalArgumentException("Null object! Can't calculate distance!");
        }

        CartesianCoordinate that = coordinate.asCartesianCoordinate();
        CartesianCoordinate this_ = this.asCartesianCoordinate();

        return this_.getCartesianDistance(that);
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) {

        if (coordinate == null) {
            throw new IllegalArgumentException("Null object! Can't calculate central angle!");
        }

        SphericCoordinate that = coordinate.asSphericCoordinate();
        SphericCoordinate this_ = this.asSphericCoordinate();

        return this_.getCentralAngle(that);
    }

    @Override
    public void writeOn(ResultSet rset) throws SQLException {
        CartesianCoordinate coord = this.asCartesianCoordinate();
        rset.updateDouble("x", coord.getX());
        rset.updateDouble("y", coord.getY());
        rset.updateDouble("z", coord.getZ());
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof Coordinate)
            return IsEqual((Coordinate) o);
        else
            return false;
    }

    @Override
    public boolean IsEqual(Coordinate coordinate) {

        CartesianCoordinate thatC = coordinate.asCartesianCoordinate();
        CartesianCoordinate thisC = this.asCartesianCoordinate();

        SphericCoordinate thatS = coordinate.asSphericCoordinate();
        SphericCoordinate thisS = coordinate.asSphericCoordinate();

        return !(Math.abs(thisC.getX() - thatC.getX()) > THRESHOLD || Math.abs(thisC.getY() - thatC.getY()) > THRESHOLD
                || Math.abs(thisC.getY() - thatC.getY()) > THRESHOLD)
                && !(Math.abs(thisS.getRadius() - thatS.getRadius()) > THRESHOLD
                        || Math.abs(thisS.getPhi() - thatS.getPhi()) > THRESHOLD
                        || Math.abs(thisS.getTheta() - thatS.getTheta()) > THRESHOLD);

    }

    @Override
    public abstract CartesianCoordinate asCartesianCoordinate();

    @Override
    public abstract SphericCoordinate asSphericCoordinate();

    @Override
    public String toString() {

        CartesianCoordinate coordinate = this.asCartesianCoordinate();

        return coordinate.asCartesianCoordinate().getX() + "/" + coordinate.asCartesianCoordinate().getY() + "/"
                + coordinate.asCartesianCoordinate().getZ();
    }

    @Override
    public int hashCode() {
        CartesianCoordinate coordinate = this.asCartesianCoordinate();
        return coordinate.hashCode();
    }

    public abstract void assertClassInvariants();
}
