package pt.up.fe.ldts.frogger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.frogger.state.GameState;
import pt.up.fe.ldts.frogger.state.LoseState;
import pt.up.fe.ldts.frogger.state.MenuState;
import pt.up.fe.ldts.frogger.state.WinState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class StateTest {

    //We couldn´t force the screen to close (it needed user input)
    // Although, if closed manually, all Tests pass
    @Test
    public void MenuStateTest() throws IOException, URISyntaxException, FontFormatException {
        Game game = new Game();
        game.initializeNewGame();
        MenuState menuState = (MenuState) game.getState();
        Assertions.assertTrue(menuState.getMenuWasCreated());
    }

    @Test
    public void fromMenuToGame() throws IOException, URISyntaxException, FontFormatException {
        Game game = new Game();
        MenuState state = new MenuState(game);
        state.onPlay(game);
        GameState gameState = (GameState) game.getState();
        Assertions.assertTrue(gameState.getGameWasPlayed());
    }

    @Test
    public void GameStateTest() throws IOException, URISyntaxException, FontFormatException {
        Game game = new Game();
        GameState gameState = new GameState(game);
        Assertions.assertTrue(gameState.getGameWasPlayed());
    }

    @Test
    public void fromGameToMenu() throws IOException, URISyntaxException, FontFormatException {
        Game game = new Game();
        GameState gameState = new GameState(game);
        gameState.onMenu(game);
        MenuState menuState = (MenuState) game.getState();
        Assertions.assertTrue(menuState.getMenuWasCreated());
    }

    @Test
    public void fromGameToWin() throws IOException, URISyntaxException, FontFormatException {
        Game game = new Game();
        GameState gameState = new GameState(game);
        gameState.onWin(game);
        WinState winState = (WinState) game.getState();
        Assertions.assertTrue(winState.getGameWasWon());
    }

    @Test
    public void fromGameToLose() throws IOException, URISyntaxException, FontFormatException {
        Game game = new Game();
        GameState gameState = new GameState(game);
        gameState.onLose(game);
        LoseState loseState = (LoseState) game.getState();
        Assertions.assertTrue(loseState.getGameWasLost());
    }

    @Test
    public void LoseStateTest() throws IOException, URISyntaxException, FontFormatException {
        Game game = new Game();
        LoseState loseState = new LoseState(game);
        Assertions.assertTrue(loseState.getGameWasLost());
    }

    @Test
    public void fromLoseToMenu() throws IOException, URISyntaxException, FontFormatException {
        Game game = new Game();
        LoseState loseState = new LoseState(game);
        loseState.onMenu(game);
        MenuState menuState = (MenuState) game.getState();
        Assertions.assertTrue(menuState.getMenuWasCreated());
    }

    @Test
    public void WinStateTest() throws IOException, URISyntaxException, FontFormatException {
        Game game = new Game();
        WinState winState = new WinState(game);
        Assertions.assertTrue(winState.getGameWasWon());
    }

    @Test
    public void fromWinToMenu() throws IOException, URISyntaxException, FontFormatException {
        Game game = new Game();
        WinState winState = new WinState(game);
        winState.onMenu(game);
        MenuState menuState = (MenuState) game.getState();
        Assertions.assertTrue(menuState.getMenuWasCreated());
    }
}
