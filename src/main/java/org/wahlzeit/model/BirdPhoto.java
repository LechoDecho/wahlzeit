package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BirdPhoto extends Photo {
    
    private Bird bird;

    public static final String SPAN = "span";
	public static final String ALTNAME = "altName";
	public static final String BIRDTYPE = "birdType";

    public BirdPhoto() {

        super();
    }

    public BirdPhoto(PhotoId myId) {

        super(myId);
    }

    public BirdPhoto(ResultSet rs) throws SQLException{

        super(rs);
    }

    public BirdPhoto(PhotoId myId, Bird bird) {

        super(myId);
        this.bird = bird;
    } 

    public void readFrom(ResultSet rset) throws SQLException {

        super.readFrom(rset);
        bird = BirdManager.getInstance().createBird(rset.getString(BIRDTYPE), rset.getInt(SPAN), rset.getString(ALTNAME));
    }

	public void writeOn(ResultSet rset) throws SQLException {

        super.writeOn(rset);
        rset.updateInt(SPAN, bird.getSpan());
		rset.updateString(ALTNAME, bird.getAltName());
		rset.updateString(BIRDTYPE, bird.getBirdType().getType());
    }

    public Bird getBird() {
        return bird;
    }

    public void setBird(Bird bird) {
        this.bird = bird;
        incWriteCount();
    }
}
