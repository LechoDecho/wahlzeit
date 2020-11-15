package org.wahlzeit.model;

public class Coordinate {

    private double x;
    private double y;
    private double z;

    public Coordinate(double x, double y, double z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Coordinate(String coordinate) {

        String[] coords = coordinate.split("/");

        this.x = Double.parseDouble(coords[0]);
        this.y = Double.parseDouble(coords[1]);
        this.z = Double.parseDouble(coords[2]);
    }

    public double getDistance(Coordinate that) {

        double x = that.x - this.x;
        double y = that.y - this.y;
        double z = that.z - this.z;

        return Math.sqrt(x*x + y*y + z*z);
    }

    public boolean isEqual(Coordinate that) {

        if (Double.compare(this.x, that.x) == 0 && 
            Double.compare(this.y,that.y) == 0 && 
            Double.compare(this.z, that.z) == 0 )
            return true;
        else
            return false;
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof Coordinate)
            return isEqual((Coordinate)o);
        else return false;
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
}
