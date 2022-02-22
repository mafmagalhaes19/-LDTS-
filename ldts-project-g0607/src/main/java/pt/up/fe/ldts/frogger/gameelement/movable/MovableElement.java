package pt.up.fe.ldts.frogger.gameelement.movable;

import pt.up.fe.ldts.frogger.Position;
import pt.up.fe.ldts.frogger.gameelement.Element;

public abstract class MovableElement implements Element {

    protected Position position;

    public MovableElement(int x, int y){
        position = new Position(x,y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract String getElementType();
}