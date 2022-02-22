package pt.up.fe.ldts.frogger.gameelement.elementcommand;

import pt.up.fe.ldts.frogger.Position;

public abstract class Command {
    public abstract Position execute(Position position);
}
