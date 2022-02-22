package pt.up.fe.ldts.frogger.state;

import pt.up.fe.ldts.frogger.Game;

import java.io.IOException;

public interface State {

    default void onPlay(Game game) throws IOException {
        State state = new GameState(game);
        game.setState(state);
        game.playGame();
    }

    default void onMenu(Game game) throws IOException {
        State state = new MenuState(game);
        game.setState(state);
    }

    default void onWin(Game game) throws IOException {
        State state = new WinState(game);
        game.setState(state);
    }

    default void onLose(Game game) throws IOException {
        State state = new LoseState(game);
        game.setState(state);
    }
}
