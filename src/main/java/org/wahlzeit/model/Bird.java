package org.wahlzeit.model;

public class Bird {
    
    public BirdType birdType;
    public int span;
    public String altName;

    public Bird(BirdType type, int span, String altName) {
        this.birdType = type;
        this.span = span;
        this.altName = altName;
    }

    public BirdType getBirdType() {
        return birdType;
    }

    public void setBirdType(BirdType birdType) {
        this.birdType = birdType;
    }

    public int getSpan() {
        return span;
    }

    public void setSpan(int span) {
        this.span = span;
    }

    public String getAltName() {
        return altName;
    }

    public void setAltName(String altName) {
        this.altName = altName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((altName == null) ? 0 : altName.hashCode());
        result = prime * result + ((birdType == null) ? 0 : birdType.hashCode());
        result = prime * result + span;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Bird other = (Bird) obj;
        if (altName == null) {
            if (other.altName != null)
                return false;
        } else if (!altName.equals(other.altName))
            return false;
        if (birdType == null) {
            if (other.birdType != null)
                return false;
        } else if (!birdType.equals(other.birdType))
            return false;
        if (span != other.span)
            return false;
        return true;
    }
}
