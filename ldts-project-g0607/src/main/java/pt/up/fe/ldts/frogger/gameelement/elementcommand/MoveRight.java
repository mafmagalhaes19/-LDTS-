package pt.up.fe.ldts.frogger.gameelement.elementcommand;

import pt.up.fe.ldts.frogger.Position;
import pt.up.fe.ldts.frogger.gameelement.elementcommand.Command;

public class MoveRight extends Command {

    public Position execute(Position position) {
        if (position.getX() == 59)
            return new Position(0, position.getY());
        return new Position(position.getX() + 1, position.getY());
    }
}
