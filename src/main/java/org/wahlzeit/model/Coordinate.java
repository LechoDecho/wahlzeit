package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Coordinate {

    private double x;
    private double y;
    private double z;

    private static final double THRESHOLD = 0.0000001d;

    public Coordinate(double x, double y, double z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getDistance(Coordinate that) {

        if(that == null)
            throw new IllegalArgumentException("Can not get Distance from null object");
        double x = that.x - this.x;
        double y = that.y - this.y;
        double z = that.z - this.z;

        return Math.sqrt(x*x + y*y + z*z);
    }

    public boolean isEqual(Coordinate that) {

        if (Math.abs(this.x - that.x) > THRESHOLD || 
            Math.abs(this.y - that.y) > THRESHOLD || 
            Math.abs(this.z - that.z) > THRESHOLD )
            return false;
        else
            return true;
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
            return isEqual((Coordinate)o);
        else return false;
    }

    public void writeOn(ResultSet rset) throws SQLException {
        rset.updateDouble("x", x);
        rset.updateDouble("y", y);
        rset.updateDouble("z", z);
    }
}
