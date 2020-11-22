package org.wahlzeit.model;

import java.io.*;
import java.awt.*;

public class BirdPhotoUtil extends PhotoUtil {
    
    public static Photo createPhoto(File source, PhotoId id) throws Exception {
		BirdPhoto result = (BirdPhoto) PhotoFactory.getInstance().createPhoto(id);
		
		Image sourceImage = createImageFiles(source, id);

		int sourceWidth = sourceImage.getWidth(null);
		int sourceHeight = sourceImage.getHeight(null);
		result.setWidthAndHeight(sourceWidth, sourceHeight);

		return result;
	}

}
