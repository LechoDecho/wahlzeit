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

import org.junit.Before;

/**
 * Test cases for a variety of value object classes.
 */
public class BirdPhotoFactoryTest {

    BirdPhoto birdPhoto;

    BirdPhoto testPhoto;

    BirdPhotoFactory bpf = new BirdPhotoFactory();

    @Before public void initialize(){
        birdPhoto = new BirdPhoto(new PhotoId(0), 23, "latino name");

        testPhoto = bpf.createPhoto(new PhotoId(0), 23, "latino name");
    }

    @Test
    public void BirdPhotoInitialization() {

        assertEquals(birdPhoto.getSpan(), testPhoto.getSpan());
        assertEquals(birdPhoto.getAltName(), testPhoto.getAltName());
        assertEquals(birdPhoto.getId(), testPhoto.getId());
    }
    
}
