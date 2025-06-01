package gam.model.geo;

import gam.config.PlayerConfig;
import gam.util.IOUtil;

import java.io.Serializable;
import java.util.Random;

public final class Scene implements Serializable {
    private final int nsIndex = new Random().nextInt(0, 5);
    private final int ewIndex = new Random().nextInt(0, 5);
    private final Geolocation geospatialCoordinates = new Geolocation(new Random().nextInt(-400, 400), new Random().nextInt(-400, 400), new Random().nextInt(-400, 400));

    public int getNsIndex() {
        return nsIndex;
    }

    public int getEwIndex() {
        return ewIndex;
    }

    public Geolocation getGeospatialCoordinates() {
        return geospatialCoordinates;
    }

    /**
     * Decorator pattern may be used for exploration, but implementing in dirty fashion for now
     *
     * @param y positive = due North, negative = due South
     * @param x positive = due East, negative = due West
     *          z-movement not implemented for now
     */
    public void moveNorthSouthAndEastWest(PlayerConfig playerConfig, short y, short x) {
        if (0 != y) { // ns: North <--> South
            playerConfig.getPlayerPosition().getGeospatialCoordinates().setY(playerConfig.getPlayerPosition().getGeospatialCoordinates().getY() + y);
        }
        if (0 != x) { // es: East <--> West
            playerConfig.getPlayerPosition().getGeospatialCoordinates().setX(playerConfig.getPlayerPosition().getGeospatialCoordinates().getX() + x);
        }
        IOUtil.display(String.format("Player now at: [%d,%d].%n", playerConfig.getPlayerPosition().getGeospatialCoordinates().getY(), playerConfig.getPlayerPosition().getGeospatialCoordinates().getX()));
    }
}
