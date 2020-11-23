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

    private static final double THRESHOLD = 0.0000001d;

    private Coordinate coordinate1 = new Coordinate(1.0,2.0,3.0);
    private Coordinate coordinate2 = new Coordinate(1.000000001, 2.0, 3.0);
    private Coordinate coordinate3 = new Coordinate(5.0,6.0,7.0);

	/**
	 *
	 */
	@Test
	public void testCoordinateEquals() {

		assertTrue(coordinate1.equals(coordinate2));
		assertTrue(coordinate1.equals(coordinate1));
		assertFalse(coordinate1.equals(coordinate3));
    }

    @Test
    public void testCoordinateDistance() {

        assertEquals(Math.sqrt(48), coordinate1.getDistance(coordinate3), THRESHOLD);
        assertEquals(Math.sqrt(0), coordinate1.getDistance(coordinate2), THRESHOLD);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCoordinateDistanceNull() {
        coordinate1.getDistance(null);
    }
}
