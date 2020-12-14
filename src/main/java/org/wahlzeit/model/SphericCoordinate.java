package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SphericCoordinate extends AbstractCoordinate {

    private double radius;
    private double theta;
    private double phi;

    public SphericCoordinate(double radius, double theta, double phi) {
        this.radius = radius;
        this.theta = theta;
        this.phi = phi;

        assertClassInvariants();
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

    public double getRadius() {
        return radius;
    }

    public double getTheta() {
        return theta;
    }

    public double getPhi() {
        return phi;
    }

    public double getCentralAngle(SphericCoordinate coordinate) {
        double thatPhi = coordinate.getPhi();
        double thatTheta = coordinate.getTheta();

        double thisPhi = this.getPhi();
        double thisTheta = this.getTheta();

        double X = Math.cos(thatPhi) * Math.cos(thatTheta) - Math.cos(thisPhi) * Math.cos(thisTheta);
        double Y = Math.cos(thatPhi) * Math.sin(thatTheta) - Math.cos(thisPhi) * Math.sin(thisTheta);
        double Z = Math.sin(thatPhi) - Math.sin(thisPhi);

        double C = Math.sqrt(X * X + Y * Y + Z * Z);
        double D = 2 * Math.asin(C / 2);

        assert Double.isFinite(D) : "Calculated central angle resultet in NaN or infitie";

        return D;
    }

    @Override
    public void assertClassInvariants() {
        assert Double.isFinite(radius) : "radius was NaN or Infinite";
        assert Double.isFinite(phi) : "phi was NaN or Infinite";
        assert Double.isFinite(theta) : "theta was NaN or Infinite";
    }
}
