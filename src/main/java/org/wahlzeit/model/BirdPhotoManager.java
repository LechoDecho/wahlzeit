package org.wahlzeit.model;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

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

		/**
	 * @methodtype command
	 */
	public void loadPhotos(Collection<Photo> result) {
		try {
			PreparedStatement stmt = getReadingStatement("SELECT * FROM photos");
			readObjects(result, stmt);
			for (Iterator<Photo> i = result.iterator(); i.hasNext(); ) {
				BirdPhoto photo = (BirdPhoto)i.next();
				if (!doHasPhoto(photo.getId())) {
					doAddPhoto(photo);
				} else {
					SysLog.logSysInfo("photo", photo.getId().asString(), "photo had already been loaded");
				}
			}
		} catch (SQLException sex) {
			SysLog.logThrowable(sex);
		}
		
		SysLog.logSysInfo("loaded all photos");
	}

	/**
	 * @methodtype command
	 * @methodproperties primitive
	 */
	protected void doAddPhoto(Photo myPhoto) {
		photoCache.put(myPhoto.getId(), (BirdPhoto)myPhoto);
	}
}
