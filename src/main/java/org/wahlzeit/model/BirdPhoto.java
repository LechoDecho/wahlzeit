package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BirdPhoto extends Photo {
    
    private int span;
    private String altName;

    public static final String SPAN = "span";
	public static final String ALTNAME = "altName";

    public BirdPhoto() {

        super();
    }

    public BirdPhoto(PhotoId myId) {

        super(myId);
    }

    public BirdPhoto(ResultSet rs) throws SQLException{

        super(rs);
    }

    public BirdPhoto(PhotoId myId, int span, String altName) {

        super(myId);
        this.span = span;
        this.altName = altName;
    }

    public int getSpan() {

        return span;
    }

    public void setSpan(int span) {
        
        this.span = span;
        incWriteCount();
    }

    public String getAltName() {

        return altName;
    }

    public void setAltName(String altName) {
        
        this.altName = altName;
        incWriteCount();
    }   

    public void readFrom(ResultSet rset) throws SQLException {

        super.readFrom(rset);   
		span = rset.getInt("span");
		altName = rset.getString("tags");
    }

	public void writeOn(ResultSet rset) throws SQLException {

        super.writeOn(rset);
        rset.updateInt("span", span);
		rset.updateString("altName", altName);
    }
}
