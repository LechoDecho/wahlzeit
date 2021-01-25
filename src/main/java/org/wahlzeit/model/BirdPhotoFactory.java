package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.wahlzeit.tools.PatternInstance;

@PatternInstance(
    patternName = "Factory Method",
    participants = {"BirdPhotoFactory", "PhotoFactory"}
)

public class BirdPhotoFactory extends PhotoFactory {

    public BirdPhotoFactory() {
        super();
    }

    /**
	 * @methodtype factory
	 */
	public Photo createPhoto() {
		return new BirdPhoto();
	}
	
	/**
	 * 
	 */
	public Photo createPhoto(PhotoId id) {
		return new BirdPhoto(id);
	}
	
	/**
	 * 
	 */
	public Photo createPhoto(ResultSet rs) throws SQLException {
		return new BirdPhoto(rs);
    }
    
    /**
     * 
     * @param id
     * @param bird
     * @return
     */
    public BirdPhoto createPhoto(PhotoId id, Bird bird)
    {
        return new BirdPhoto(id, bird);
    }
}
