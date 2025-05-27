package gam.model;

import gam.client.GameMapVisitor;
import gam.model.base.BasePlayerToken;
import gam.model.geo.GameMap;
import gam.model.geo.Scene;

public class PlayerCharacter extends BasePlayerToken {

    //extrinsic properties/property
    private String name = null;
    private Scene scene = new Scene(); //scene: Map Block (world, ground) + Geospatial Coordinates (x,y,z)
    private String explorationHistory = null;
    //private final Equipment equipment = new Equipment();//??

    @Override
    public PlayerCharacter clone() throws CloneNotSupportedException {
        return (PlayerCharacter) super.clone();
    }

    public void locateIn(GameMap fullGameMap) {
        GameMapVisitor.setMapGroundsVisited(scene.getNsIndex(), scene.getEwIndex(), this.name);
    }

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
    //Decorator pattern may be used for exploration, but implementing in dirty fashion for now

    /**
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
        System.out.println(String.format("Player now at: [%d,%d].",this.getScene().getGeospatialCoordinates().getY(),this.getScene().getGeospatialCoordinates().getX()));
    }

    public void fight() {
        score++;
        System.out.println("score: " + score);
    }
}
