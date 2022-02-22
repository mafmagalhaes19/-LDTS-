package pt.up.fe.ldts.frogger.state;

import pt.up.fe.ldts.frogger.Game;
import pt.up.fe.ldts.frogger.menus.Lose;

import java.io.IOException;

public class LoseState implements State {
    private boolean gameWasLost;

    public LoseState(Game game) throws IOException {
        new Lose(game);
        gameWasLost = true;
    }

    //for testing purposes only
    public boolean getGameWasLost(){
        return gameWasLost;
    }
}
