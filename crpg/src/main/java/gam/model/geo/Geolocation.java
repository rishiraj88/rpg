package gam.model.geo;

import java.io.Serializable;

public class Geolocation implements Serializable {
    int xPosition, yPosition, zPosition; //?? z-position for climbing and diving
    public Geolocation(int xPosition, int yPosition, int zPosition) {
        this.xPosition = xPosition; this.yPosition = yPosition; this.zPosition = zPosition;
    }

    public int getX() {
        return xPosition;
    }

    public int getY() {
        return yPosition;
    }

    public int getZ() {
        return zPosition;
    }

    public void setX(int x) {
        this.xPosition = x;
    }

    public void setY(int y) {
        this.yPosition = y;
    }

    public void setZ(int z) {
        this.zPosition = z;
    }
}
