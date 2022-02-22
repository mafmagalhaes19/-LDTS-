import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.*;

import java.util.Random;

public class Monster extends Element{

    public Monster(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#006400"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "M");
    }

    public Position move(Arena arena){
        Random random = new Random();
        while(true) {
            Position updated = new Position(this.getPosition().getX() + random.nextInt(3) - 1, this.getPosition().getY() + random.nextInt(3) - 1);
            if(updated.getX() > 0 && updated.getX() < arena.getWidth()-1 && updated.getY() > 0 && updated.getY() < arena.getHeight()-1) {
                return updated;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Position p = (Position) o;
        return this.getX() == p.getX() && this.getY() == p.getY();
    }
}
