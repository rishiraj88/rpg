package gam.model.base;

import java.io.Serializable;

public abstract class BasePlayerToken implements Token, Cloneable, Serializable {
    protected long score = 0L;
    protected byte health = 100; // in percentage. To decrease upon combat, to increase upon adrenaline syringe application
    protected boolean armour = false; //??

    public void incrementScore(int i) { score++; }

    public long getScore() {
        return score;
    }
    //protected Equipment equipment = new Equipment(); //equipment item, quantity //??
}
