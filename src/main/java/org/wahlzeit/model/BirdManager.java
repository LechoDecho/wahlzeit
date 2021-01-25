package org.wahlzeit.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class BirdManager {
    
	protected static final BirdManager instance = new BirdManager();
    private HashSet<Bird> birds = new HashSet<Bird>();
    private HashMap<String, BirdType> birdTypes= new HashMap<String, BirdType>();

    private BirdManager(){}

    public static final BirdManager getInstance() {
		return instance;
	}
    
    public Bird createBird(String type, int span, String altName) {
        BirdType birdType = getBirdType(type);
        Bird bird = birdType.createInstance(span, altName);
        Iterator<Bird> itr = birds.iterator();
        while(itr.hasNext())
        {
            Bird br = itr.next();
            if(br.equals(bird)) {
                return br;
            }
        }
        birds.add(bird);
        return bird;
    }

    private BirdType getBirdType(String type) {
        if(!birdTypes.containsKey(type))
        {
            birdTypes.put(type, new BirdType(type));
        }
        return birdTypes.get(type);
    }
}
