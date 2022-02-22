package pt.up.fe.ldts.frogger.state;

import pt.up.fe.ldts.frogger.Game;
import pt.up.fe.ldts.frogger.menus.Win;

import java.io.IOException;

public class WinState implements State {
    private boolean gameWasWon;

    public WinState(Game game) throws IOException {
        new Win(game);
        gameWasWon = true;
    }

    //for testing purposes only
    public boolean getGameWasWon(){
        return gameWasWon;
    }
}
