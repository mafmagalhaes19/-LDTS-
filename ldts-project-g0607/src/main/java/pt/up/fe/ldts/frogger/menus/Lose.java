package pt.up.fe.ldts.frogger.menus;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import pt.up.fe.ldts.frogger.Game;
import pt.up.fe.ldts.frogger.state.MenuState;
import pt.up.fe.ldts.frogger.state.State;

import java.io.IOException;

public class Lose {
    private Screen screen;
    private TextGraphics graphics;
    private int width= 60;
    private int height = 30;
    private Game game;
    private String gameOver = "GAME OVER";

    public Lose(Game newGame) throws IOException {
        game = newGame;
        screen = game.getScreen();

        screen.setCursorPosition(null);
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary

        graphics = game.getGraphics();

        this.draw();
    }

    public void draw() throws IOException {
        screen.clear();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        graphics.putString(26, 15, gameOver);

        screen.refresh();
        this.returnToMenu();
    }

    public void returnToMenu() throws IOException {
        KeyStroke key = screen.readInput();
        if (key.getKeyType() == KeyType.Enter) {
            State newState = new MenuState(game);
            newState.onMenu(game);
        }
    }
}
