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
import static org.junit.Assert.assertNotEquals;

/**
 * Test cases for a variety of value object classes.
 */
public class BirdPhotoTest {

    @Test
    public void BirdPhotoInitialization() {
        BirdPhoto testBird = new BirdPhoto(new PhotoId(0), 23, "latino name");
        BirdPhoto testBird2 = new BirdPhoto();

        testBird2.setAltName("latino name");
        testBird2.setSpan(23);

        assertEquals(23, testBird.getSpan());
        assertEquals("latino name", testBird.getAltName());

        assertNotEquals(testBird, testBird2);
    }
    
}
