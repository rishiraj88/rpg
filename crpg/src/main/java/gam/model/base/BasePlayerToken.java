package gam.model.base;

import java.io.Serializable;

public abstract class BasePlayerToken implements Token,Cloneable, Serializable {
    //intrinsic properties
    protected long score = 0L;
    protected byte health = 100; // in percentage
    protected boolean armour = false;
    //protected Equipment equipment = new Equipment(); //equipment item, quantity //??
}
