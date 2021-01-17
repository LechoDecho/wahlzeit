package org.wahlzeit.model;

import java.util.HashMap;
import java.util.Vector;

import org.wahlzeit.tools.PatternInstance;

@PatternInstance(
    patternName = "Value Object",
    participants = {"CoodinateValueObject", "Coordinate"}
)
public class CoordinateValueObject {

    private static CoordinateValueObject instance;
    private HashMap<Vector, Coordinate> valueObjects = new HashMap<>();

    private CoordinateValueObject() {
    }

    public static synchronized CoordinateValueObject getInstance() {

        if (CoordinateValueObject.instance == null)
            CoordinateValueObject.instance = new CoordinateValueObject();

        return CoordinateValueObject.instance;
    }

    public Coordinate getCoordinate(Coordinate coordinate) {
        if (coordinate instanceof CartesianCoordinate)
            return getCartesianCooridnate(coordinate.asCartesianCoordinate());
        else if (coordinate instanceof SphericCoordinate)
            return getSphericCooridnate(coordinate.asSphericCoordinate());
        else
            throw new IllegalArgumentException("Can not get a coordinate object from a not coordinate class");
    }

    public Coordinate getCartesianCooridnate(CartesianCoordinate coordinate) {
        Vector key = new Vector<>();
        key.add(coordinate.getX());
        key.add(coordinate.getY());
        key.add(coordinate.getZ());

        if (!valueObjects.containsKey(key)) {
            valueObjects.put(key, coordinate);
        }

        return valueObjects.get(key);
    }

    public Coordinate getSphericCooridnate(SphericCoordinate coordinate) {

        Vector key = new Vector<>();
        key.add(coordinate.getRadius());
        key.add(coordinate.getPhi());
        key.add(coordinate.getTheta());

        if (!valueObjects.containsKey(key)) {
            valueObjects.put(key, coordinate);
        }

        return valueObjects.get(key);
    }

}
