/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for a variety of value object classes.
 */
public class CoordinateTest {

    private static final double THRESHOLD = 0.00001d;
    private static final double ARCTANDIVBYZERO = 1.5707963267949d;

    private Coordinate coordinate1 = new CartesianCoordinate(1.0, 2.0, 3.0);
    private Coordinate coordinate2 = new CartesianCoordinate(1.000000001, 2.000000001, 3.000000001);
    private Coordinate coordinate3 = new CartesianCoordinate(5.0, 6.0, 7.0);
    private Coordinate coordinate7 = new CartesianCoordinate(0, 5.0, 0);

    private Coordinate coordinate4 = new SphericCoordinate(3.7416573867739, 1.1071487177941, 0.64052231267943);
    private Coordinate coordinate5 = new SphericCoordinate(3.741657386, 1.107148717, 0.640522312);
    private Coordinate coordinate6 = new SphericCoordinate(10.488088481702, 0.87605805059819, 0.8400523908062);
    private Coordinate coordinate8 = new SphericCoordinate(5.0, ARCTANDIVBYZERO, ARCTANDIVBYZERO);

    /**
     *
     */
    @Test
    public void testCoordinateEquals() {

        assertTrue(coordinate1.equals(coordinate2));
        assertTrue(coordinate1.equals(coordinate1));
        assertFalse(coordinate1.equals(coordinate3));
        assertTrue(coordinate1.equals(coordinate4));
        assertTrue(coordinate1.equals(coordinate5));
        assertTrue(coordinate5.equals(coordinate2));
        assertTrue(coordinate4.equals(coordinate2));
        assertTrue(coordinate3.equals(coordinate6));
        assertFalse(coordinate1.equals(null));
        assertFalse(coordinate4.equals(null));
        assertTrue(coordinate7.equals(coordinate8));
    }

    @Test
    public void testCoordinateDistance() {

        assertEquals(Math.sqrt(48), coordinate1.getCartesianDistance(coordinate3), THRESHOLD);
        assertEquals(Math.sqrt(0), coordinate1.getCartesianDistance(coordinate2), THRESHOLD);
        assertEquals(Math.sqrt(48), coordinate1.getCartesianDistance(coordinate6), THRESHOLD);
        assertEquals(Math.sqrt(0), coordinate1.getCartesianDistance(coordinate5), THRESHOLD);
    }

    @Test
    public void testCoordinateCentralAngle() {

        assertEquals(0.2617656028, coordinate1.getCentralAngle(coordinate3), THRESHOLD);
        assertEquals(2.0503853382E-10, coordinate1.getCentralAngle(coordinate2), THRESHOLD);
        assertEquals(0.2617656028, coordinate1.getCentralAngle(coordinate6), THRESHOLD);
        assertEquals(2.0503853382E-10, coordinate1.getCentralAngle(coordinate5), THRESHOLD);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCoordinateDistanceNull() {
        coordinate1.getCartesianDistance(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCoordinateCentralAngleNull() {
        coordinate1.getCentralAngle(null);
    }
}
