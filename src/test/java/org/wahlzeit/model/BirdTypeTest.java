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
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for a variety of value object classes.
 */
public class BirdTypeTest {

    @Test
    public void BirdTypeInitialization() {
        BirdType birdSuperType = new BirdType("Greifvogel");
        BirdType birdSuperType2 = new BirdType("Wasservogel");
        BirdType subType1 = new BirdType("Adler");
        BirdType subType2 = new BirdType("Falke");
        BirdType subType3 = new BirdType("Ente");
        BirdType subType4 = new BirdType("Ente");


        birdSuperType.addSubType(subType1);
        birdSuperType.addSubType(subType2);
        birdSuperType2.addSubType(subType3);
        birdSuperType2.addSubType(subType4);
        
        assertTrue(birdSuperType.isSubType(subType1));
        assertTrue(birdSuperType.isSubType(subType2));
        assertFalse(birdSuperType.isSubType(subType3));
        assertEquals(subType3, subType4);
 
    }
    
}
