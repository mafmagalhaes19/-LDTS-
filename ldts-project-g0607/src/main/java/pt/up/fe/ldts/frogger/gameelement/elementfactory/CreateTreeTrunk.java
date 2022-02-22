package pt.up.fe.ldts.frogger.gameelement.elementfactory;

import pt.up.fe.ldts.frogger.gameelement.movable.MovableElement;
import pt.up.fe.ldts.frogger.Position;
import pt.up.fe.ldts.frogger.gameelement.movable.TreeTrunk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateTreeTrunk extends MovableElementsFactory {

    public CreateTreeTrunk(int level, int row) {
        super(level, row);
    }

    //number of tree trunks in each section (row) of the water decreases with the level
    public int numElements() {
        Random random = new Random();
        if (level == 1)
            return random.nextInt(11 - 8) + 8;
        if (level == 2)
            return random.nextInt(8 - 5) + 5;
        else //level == 3
            return random.nextInt(5 - 2) + 2;
    }

    //check if the random method isn't accidentally creating two tree trunks at the same position
    public boolean checkOverlapping(List<MovableElement> treeTrunks, Position position) {
        for (MovableElement treeTrunk: treeTrunks)
            if (treeTrunk.getPosition().equals(position))
                return true;
        return false;
    }

    @Override
    public List<MovableElement> create() {
        Random random = new Random();
        List<MovableElement> treeTrunks = new ArrayList<>();

        int numElements = numElements();

        for (int i = 0; i < numElements; i++) {
            int x;
            do {
                x = random.nextInt(58);
            } while (checkOverlapping(treeTrunks, new Position(x, row)));
            TreeTrunk treeTrunk1 = new TreeTrunk(x, row, "left");
            TreeTrunk treeTrunk2 = new TreeTrunk(x+1, row, "left");
            TreeTrunk treeTrunk3 = new TreeTrunk(x+2, row, "left");
            treeTrunks.add(treeTrunk1);
            treeTrunks.add(treeTrunk2);
            treeTrunks.add(treeTrunk3);
        }
        return treeTrunks;
    }
}
