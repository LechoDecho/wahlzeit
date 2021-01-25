package org.wahlzeit.model;

import java.util.HashSet;
import java.util.Iterator;

public class BirdType {

    protected BirdType superType = null;
    protected HashSet<BirdType> subTypes = new HashSet<BirdType>();

    public String type;

    public BirdType(String type) {
        this.type = type;
    }

    public Bird createInstance(int span, String altName) {
        return new Bird(this, span, altName);
    }

    public BirdType getSuperType() {
        return superType;
    }

    public void setSuperType(BirdType superType) {
        this.superType = superType;
    }

    public Iterator<BirdType> getSubTypeIterator() {
        return subTypes.iterator();
    }

    public void addSubType(BirdType subType) {
        assert (subType != null) : "tried to set null sub-type";
        subType.setSuperType(this);
        subTypes.add(subType);
    }

    public boolean isSubType(BirdType subType) {
        Iterator<BirdType> itr = getSubTypeIterator();
        while (itr.hasNext()) {
            BirdType currentType = itr.next();
            if (currentType.equals(subType))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        BirdType other = (BirdType) obj;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
