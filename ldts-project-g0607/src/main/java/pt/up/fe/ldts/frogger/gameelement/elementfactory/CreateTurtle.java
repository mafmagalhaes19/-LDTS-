package pt.up.fe.ldts.frogger.gameelement.elementfactory;

import pt.up.fe.ldts.frogger.gameelement.movable.MovableElement;
import pt.up.fe.ldts.frogger.Position;
import pt.up.fe.ldts.frogger.gameelement.movable.Turtle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateTurtle extends MovableElementsFactory {

    //Turtles will be created after the tree trunks so an extra attribute is needed to check overlapping
    public CreateTurtle(int level, int row) {
        super(level, row);
    }

    //number of tree trunks decreases with the level
    public int numElements() {
        Random random = new Random();
        if (level == 1)
            return random.nextInt(12 - 9) + 9;
        if (level == 2)
            return random.nextInt(9 - 6) + 6;
        else //level == 3
            return random.nextInt(6 - 3) + 3;
    }

    //check if the random method isn't accidentally creating two turtles at the same position or in a position where there already is a tree trunk
    public boolean checkOverlapping(List<MovableElement> turtles, Position position) {
        for (MovableElement turtle: turtles)
            if (turtle.getPosition().equals(position))
                return true;
        return false;
    }

    @Override
    public List<MovableElement> create() {
        Random random = new Random();
        List<MovableElement> turtles = new ArrayList<>();

        int numElements = numElements();

        for (int i = 0; i < numElements; i++) {
            int x;
            do {
                x = random.nextInt(60);
            } while (checkOverlapping(turtles, new Position(x, row)));
            Turtle turtle = new Turtle(x, row, "right");
            turtles.add(turtle);
        }
        return turtles;
    }
}
