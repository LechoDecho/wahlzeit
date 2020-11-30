package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartesianCoordinate implements Coordinate {

    private double x;
    private double y;
    private double z;

    private static final double THRESHOLD = 0.00001d;

    public CartesianCoordinate(double x, double y, double z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof Coordinate)
            return IsEqual((Coordinate) o);
        else
            return false;
    }

    public void writeOn(ResultSet rset) throws SQLException {
        rset.updateDouble("x", x);
        rset.updateDouble("y", y);
        rset.updateDouble("z", z);
    }

    @Override
    public boolean IsEqual(Coordinate coordinate) {

        CartesianCoordinate that = coordinate.asCartesianCoordinate();

        return !(Math.abs(this.x - that.x) > THRESHOLD || Math.abs(this.y - that.y) > THRESHOLD
                || Math.abs(this.z - that.z) > THRESHOLD);
        
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {

        double radius = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        double theta = Math.atan(this.y / this.x);
        double phi = Math.atan(Math.sqrt(this.x * this.x + this.y * this.y) / this.z);

        return new SphericCoordinate(radius, theta, phi);
    }

    @Override
    public double getCartesianDistance(Coordinate coordinate) {

        if (coordinate == null) {
            throw new IllegalArgumentException("Null object! Can't calculate distance!");
        }

        CartesianCoordinate that = coordinate.asCartesianCoordinate();
        double x = that.getX() - this.x;
        double y = that.getY() - this.y;
        double z = that.getZ() - this.z;

        return Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) {
        SphericCoordinate thisCoordinate = this.asSphericCoordinate();
        return thisCoordinate.getCentralAngle(coordinate);
    }
}
