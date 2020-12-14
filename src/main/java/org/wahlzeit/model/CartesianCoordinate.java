package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartesianCoordinate extends AbstractCoordinate {

    private static final double ARCTANDIVBYZERO = 1.5707963267949d;

    private double x;
    private double y;
    private double z;

    public CartesianCoordinate(double x, double y, double z) {

        this.x = x;
        this.y = y;
        this.z = z;

        assertClassInvariants();
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
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {

        double radius = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);

        double theta = ARCTANDIVBYZERO;
        if (this.x >= THRESHOLD)
            theta = Math.atan(this.y / this.x);
        double phi = ARCTANDIVBYZERO;
        if (this.z >= THRESHOLD)
            phi = Math.atan(Math.sqrt(this.x * this.x + this.y * this.y) / this.z);

        return new SphericCoordinate(radius, theta, phi);
    }

    public double getCartesianDistance(CartesianCoordinate coordinate) {

        double x = coordinate.getX() - this.getX();
        double y = coordinate.getY() - this.getY();
        double z = coordinate.getZ() - this.getZ();

        double D = Math.sqrt(x * x + y * y + z * z);

        assert Double.isFinite(D) : "Calculation resulted in NaN or infinite";

        return D;

    }

    @Override
    public void assertClassInvariants() {
        assert Double.isFinite(x) : "X coordinate was NaN or Infinite";
        assert Double.isFinite(y) : "Y coordinate was NaN or Infinite";
        assert Double.isFinite(z) : "Z coordinate was NaN or Infinite";
    }

}
