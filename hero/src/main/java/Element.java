import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {
    private Position position;

    public Element(int x, int y){
        position = new Position(x,y);
    }

    public Position getPosition() {
        return position;
    }

    public int getX(){
        return position.getX();
    }

    public int getY(){
        return position.getY();
    }

    public void setX( int new_x){
        position.setX(new_x);
    }

    public void setY( int new_y){
        position.setY(new_y);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract void draw(TextGraphics screen);
}

