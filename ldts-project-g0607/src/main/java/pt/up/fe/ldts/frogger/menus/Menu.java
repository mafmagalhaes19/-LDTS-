package pt.up.fe.ldts.frogger.menus;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import pt.up.fe.ldts.frogger.Game;
import pt.up.fe.ldts.frogger.menus.Instructions;
import pt.up.fe.ldts.frogger.menus.Level;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private Game game;
    private Screen screen;
    private TextGraphics graphics;
    private List<String> options = new ArrayList<>();
    private int option = 1;

    public Menu(Game newGame) throws IOException {
        game = newGame;
        screen = game.getScreen();

        graphics = game.getGraphics();
        options.add("PLAY");
        options.add("INSTRUCTIONS");
        options.add("LEVELS");
        options.add("EXIT");

        this.draw();
    }

    public void draw() throws IOException {
        screen.clear();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(game.getWidth(), game.getHeight()), ' ');

        int positionY = 10;
        graphics.putString(26, positionY, options.get(0));
        positionY = positionY+5;
        graphics.putString(22, positionY, options.get(1));
        positionY = positionY+5;
        graphics.putString(25, positionY, options.get(2));
        positionY = positionY+5;
        graphics.putString(26, positionY, options.get(3));

        screen.refresh();
        this.choosingOption();
    }

    public void choosingOption() throws IOException {
        graphics.putString(23, 10, "f");
        graphics.putString(32, 10, "f");
        screen.refresh();

        while(true) {
            KeyStroke key = screen.readInput();
            this.processKey(key);
            if (key != null && key.getKeyType() == KeyType.Character && (key.getCharacter() == 'q' || key.getCharacter() == 'Q')){
                screen.close();
                break;
            }
            if (key.getKeyType() == KeyType.EOF)
                break;
        }
    }

    public void cleanOptions() throws IOException {
        graphics.putString(23, 10, " ");
        graphics.putString(32, 10, " ");

        graphics.putString(19, 15, " ");
        graphics.putString(36, 15, " ");

        graphics.putString(23, 20, " ");
        graphics.putString(32, 20, " ");

        graphics.putString(24, 25, " ");
        graphics.putString(31, 25, " ");
        screen.refresh();
    }

    public void showOption(int option) throws IOException {
        if(option == 1) {
            this.cleanOptions();
            graphics.putString(23, 10, "f");
            graphics.putString(32, 10, "f");
            screen.refresh();
        }
        else if (option == 2) {
            this.cleanOptions();
            graphics.putString(19, 15, "f");
            graphics.putString(36, 15, "f");
            screen.refresh();
        }
        else if (option == 3) {
            this.cleanOptions();
            graphics.putString(23, 20, "f");
            graphics.putString(32, 20, "f");
            screen.refresh();
        }
        else if (option == 4) {
            this.cleanOptions();
            graphics.putString(24, 25, "f");
            graphics.putString(31, 25, "f");
            screen.refresh();
        }
    }

    public void processKey(KeyStroke key) throws IOException {
        switch (key.getKeyType()) {
            case ArrowUp:
                if (option > 1)
                    option--;
                this.showOption(option);
                break;
            case ArrowDown:
                if (option < 4)
                    option++;
                this.showOption(option);
                break;
            case Enter:
                if (option == 1)
                    game.getState().onPlay(game);
                else if (option == 2) {
                    Instructions instruction = new Instructions(game);
                    instruction.show();
                }
                else if (option == 3) {
                    Level level = new Level(game);
                    level.show();
                }
                else if (option == 4)
                    screen.close();
                break;
            default:
                break;
        }
    }
}