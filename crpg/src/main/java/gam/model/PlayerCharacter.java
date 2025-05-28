package gam.model;

import gam.model.base.BasePlayerToken;
import gam.model.geo.Scene;
import gam.util.IOUtil;

public class PlayerCharacter extends BasePlayerToken {
    private String name = null;

// not an ideal placement for scene here. Yet for quick development, and more convenient game save and game load, it is here.
    private final Scene scene = new Scene(); //scene: Map Block (world, ground) + Geospatial Coordinates (x,y,z)
    //private final Equipment equipment = new Equipment();//??

    @Override
    public PlayerCharacter clone() throws CloneNotSupportedException {
        return (PlayerCharacter) super.clone();
    }

/*    public void locateIn(GameMap fullGameMap) {
        GameMapVisitor.setMapGroundsVisited(scene.getNsIndex(), scene.getEwIndex(), this.name);
    }*/ //?? for exploring across grounds

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Scene getScene() {
        return scene;
    }

    /*public void addWield(String wieldName, short strength) {
        this.equipment.add(wieldName, strength);
    }*/ //??

    /** Decorator pattern may be used for exploration, but implementing in dirty fashion for now
     * @param y positive = due North, negative = due South
     * @param x positive = due East, negative = due West
     *          z-movement not implemented for now
     */
    public void moveNorthSouthAndEastWest(short y, short x) {
        if (0 != y) { // ns: North <--> South
            this.getScene().getGeospatialCoordinates().setY(this.getScene().getGeospatialCoordinates().getY() + y);
        }
        if (0 != x) { // es: East <--> West
            this.getScene().getGeospatialCoordinates().setX(this.getScene().getGeospatialCoordinates().getX() + x);
        }
        IOUtil.display(String.format("Player now at: [%d,%d].%n",this.getScene().getGeospatialCoordinates().getY(),this.getScene().getGeospatialCoordinates().getX()));
    }

    public void fight() {
        score++;
        IOUtil.display("score: " + score);
    }
}
