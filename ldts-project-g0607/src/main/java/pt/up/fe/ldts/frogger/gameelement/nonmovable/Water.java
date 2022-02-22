package pt.up.fe.ldts.frogger.gameelement.nonmovable;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.frogger.gameelement.nonmovable.NonMovableElement;

public class Water extends NonMovableElement {
    public Water(int min, int max) {
        super(min, max);
    }

    public void draw(TextGraphics graphics){
        for (int y = position.getYMin(); y <= position.getYMax(); y++) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#5dade2"));
            for (int x = 0; x <= 59; x++)
                graphics.putString(new TerminalPosition(x, y), String.valueOf(' '));
        }
    }
}
