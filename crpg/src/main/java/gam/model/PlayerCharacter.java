package gam.model;

import gam.config.PlayerConfig;
import gam.model.base.BasePlayerToken;

public class PlayerCharacter extends BasePlayerToken {
    // not an ideal placement for scene here. Yet for quick development, and more convenient game save and game load, it is here.
    private String name = null;
    //private final Equipment equipment = new Equipment();//??
    PlayerConfig playerConfig = PlayerConfig.getActivePlayerConfig(this.name);

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

    /*public void addWield(String wieldName, short strength) {
        this.equipment.add(wieldName, strength);
    }*/ //??
}
