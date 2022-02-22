package pt.up.fe.ldts.frogger.state;

import pt.up.fe.ldts.frogger.Game;
import pt.up.fe.ldts.frogger.menus.Menu;

import java.io.IOException;

public class MenuState implements State {
    private boolean menuWasCreated;

    public MenuState(Game game) throws IOException {
        game.setState(this);
        new Menu(game);
        menuWasCreated = true;
    }

    //for testing purposes only
    public boolean getMenuWasCreated(){
        return menuWasCreated;
    }
}
