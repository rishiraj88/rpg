package gam.model.geo;

import java.io.Serializable;
import java.util.Random;

public final class Scene implements Serializable {
    private int nsIndex = new Random().nextInt(0,5);
    private int ewIndex = new Random().nextInt(0,5);
    private Geolocation geospatialCoordinates = new Geolocation(
            new Random().nextInt(-400, 400)
            , new Random().nextInt(-400, 400)
            , new Random().nextInt(-400, 400)
    );

    public int getNsIndex() {
        return nsIndex;
    }

    public int getEwIndex() {
        return ewIndex;
    }

    public Geolocation getGeospatialCoordinates() {
        return geospatialCoordinates;
    }

}
