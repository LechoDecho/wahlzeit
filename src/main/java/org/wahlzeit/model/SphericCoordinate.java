package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SphericCoordinate implements Coordinate {

    private double radius;
    private double theta;
    private double phi;

    private static final double THRESHOLD = 0.00001d;

    public SphericCoordinate(double radius, double theta, double phi) {

        this.radius = radius;
        this.theta = theta;
        this.phi = phi;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(radius);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(phi);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(theta);
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
        CartesianCoordinate coordinate = this.asCartesianCoordinate();
        coordinate.writeOn(rset);
    }

    @Override
    public boolean IsEqual(Coordinate coordinate) {

        SphericCoordinate that = coordinate.asSphericCoordinate();

        return !(Math.abs(this.radius - that.getRadius()) > THRESHOLD || Math.abs(this.phi - that.getPhi()) > THRESHOLD
                || Math.abs(this.theta - that.getTheta()) > THRESHOLD);
     
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {

        double x = this.radius * Math.sin(this.phi) * Math.cos(this.theta);
        double y = this.radius * Math.sin(this.phi) * Math.sin(this.theta);
        double z = this.radius * Math.cos(this.phi);
        return new CartesianCoordinate(x, y, z);
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    @Override
    public double getCartesianDistance(Coordinate coordinate) {

        CartesianCoordinate thisCoordinate = this.asCartesianCoordinate();
        return thisCoordinate.getCartesianDistance(coordinate);
    }

    @Override
    public double getCentralAngle(Coordinate coordinate) {

        if (coordinate == null) {
            throw new IllegalArgumentException("Null object! Can't calculate central angle!");
        }

        SphericCoordinate that = coordinate.asSphericCoordinate();

        double thatPhi = that.getPhi();
        double thatTheta = that.getTheta();

        double thisPhi = this.getPhi();
        double thisTheta = this.getTheta();

        double X = Math.cos(thatPhi) * Math.cos(thatTheta) - Math.cos(thisPhi) * Math.cos(thisTheta);
        double Y = Math.cos(thatPhi) * Math.sin(thatTheta) - Math.cos(thisPhi) * Math.sin(thisTheta);
        double Z = Math.sin(thatPhi) - Math.sin(thisPhi);

        double C = Math.sqrt(X * X + Y * Y + Z * Z);

        System.out.println(2 * Math.asin(C / 2));
        return 2 * Math.asin(C / 2);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public double getPhi() {
        return phi;
    }

    public void setPhi(double phi) {
        this.phi = phi;
    }
}
