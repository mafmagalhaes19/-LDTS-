import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Hero extends Element{

    //Constructor
    public Hero(int new_x, int new_y){
       super(new_x, new_y);
    }


    //Move the hero
    public Position moveUp() {
        return new Position(this.getX(), this.getY() - 1);
    }

    public Position moveDown() {
        return new Position(this.getX(), this.getY() + 1);
    }

    public Position moveRight() {
        return new Position(this.getX() + 1, this.getY());
    }

    public Position moveLeft() {
        return new Position(this.getX() - 1, this.getY());
    }

    public void setPosition(Position new_position){
        this.setX(new_position.getX());
        this.setY(new_position.getY());
    }

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(this.getX(), this.getY()),"X");
        //graphics.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('X')[0]);
    }

}
