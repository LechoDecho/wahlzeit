package org.wahlzeit.model;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.wahlzeit.main.ServiceMain;
import org.wahlzeit.services.SysLog;

public class BirdPhotoManager extends PhotoManager {
    
    public BirdPhotoManager() {
        super();
    }

    /**
	 * 
	 */
	public Photo createPhoto(File file) throws Exception {
		PhotoId id = PhotoId.getNextId();
		Photo result = BirdPhotoUtil.createPhoto(file, id);
		addPhoto(result);
		return result;
	}
	
	/**
	 * @methodtype command
	 *
	 * Load all persisted photos. Executed when Wahlzeit is restarted.
	 */
	public void addPhoto(Photo photo) {
		PhotoId id = photo.getId();
		assertIsNewPhoto(id);
		doAddPhoto(photo);

		try {
			PreparedStatement stmt = getReadingStatement("INSERT INTO photos(id) VALUES(?)");
			createObject((BirdPhoto)photo, stmt, id.asInt());
			ServiceMain.getInstance().saveGlobals();
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
	}
    
    /**
	 * 
	 */
	public void savePhoto(Photo photo) {
		try {
			PreparedStatement stmt = getUpdatingStatement("SELECT * FROM photos WHERE id = ?");
			updateObject((BirdPhoto)photo, stmt);
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
	}
}
