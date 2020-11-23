package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

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
     * @param span
     * @param altName
     * @return
     */
    public BirdPhoto createPhoto(PhotoId id, int span, String altName)
    {
        return new BirdPhoto(id, span, altName);
    }
}
