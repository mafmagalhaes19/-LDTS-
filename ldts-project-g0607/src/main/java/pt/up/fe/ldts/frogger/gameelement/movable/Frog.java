package pt.up.fe.ldts.frogger.gameelement.movable;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.frogger.Position;
import pt.up.fe.ldts.frogger.gameelement.elementcommand.Command;

public class Frog extends MovableElement {
    public Frog(int x, int y){
        super(x,y);
    }

    //for testing purposes only
    public String getElementType() {
        return "Frog";
    }

    public Position moveUp(){
        return new Position(position.getX(), position.getY() - 1);
    }

    public Position moveDown(){
        return new Position(position.getX(), position.getY() + 1);
    }

    public Position moveLeft(){
        return new Position(position.getX() - 1, position.getY());
    }

    public Position moveRight() {
        return new Position(position.getX() + 1, position.getY());
    }

    //this method is used when frog is on top of a treeTrunk or a turtle in order to move alongside with it
    public void move(Command command) {
        this.position = command.execute(position);
    }

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#33cc33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()),"f");
    }
}