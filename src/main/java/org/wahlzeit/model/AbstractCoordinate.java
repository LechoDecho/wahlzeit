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

        double x = that.getX() - this_.getX();
        double y = that.getY() - this_.getY();
        double z = that.getZ() - this_.getZ();

        return Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) {

        if (coordinate == null) {
            throw new IllegalArgumentException("Null object! Can't calculate central angle!");
        }

        SphericCoordinate that = coordinate.asSphericCoordinate();
        SphericCoordinate this_ = this.asSphericCoordinate();

        double thatPhi = that.getPhi();
        double thatTheta = that.getTheta();

        double thisPhi = this_.getPhi();
        double thisTheta = this_.getTheta();

        double X = Math.cos(thatPhi) * Math.cos(thatTheta) - Math.cos(thisPhi) * Math.cos(thisTheta);
        double Y = Math.cos(thatPhi) * Math.sin(thatTheta) - Math.cos(thisPhi) * Math.sin(thisTheta);
        double Z = Math.sin(thatPhi) - Math.sin(thisPhi);

        double C = Math.sqrt(X * X + Y * Y + Z * Z);

        return 2 * Math.asin(C / 2);
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
}
