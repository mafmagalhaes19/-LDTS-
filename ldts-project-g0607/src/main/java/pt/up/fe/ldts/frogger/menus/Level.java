package pt.up.fe.ldts.frogger.menus;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import pt.up.fe.ldts.frogger.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Level {
    private int level;
    private int option;
    private Game game;
    private Screen screen;
    private TextGraphics graphics;
    private List<String> levels = new ArrayList<>();

    public Level(Game newGame) throws IOException {
        game = newGame;
        option = 1;
        level = 1;
        screen = game.getScreen();

        screen.setCursorPosition(null);
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary

        graphics = game.getGraphics();
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int newLevel){
        level = newLevel;
    }

    public void show() throws IOException {
        levels.add("LEVEL 1");
        levels.add("LEVEL 2");
        levels.add("LEVEL 3");
        levels.add("RETURN TO MENU");

        screen.clear();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        //graphics.setForegroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(game.getWidth(), game.getHeight()), ' ');

        int positionY = 5;
        graphics.putString(26, positionY, levels.get(0));
        positionY = positionY+5;
        graphics.putString(26, positionY, levels.get(1));
        positionY = positionY+5;
        graphics.putString(26, positionY, levels.get(2));
        positionY = positionY+5;
        graphics.putString(22, positionY, levels.get(3));

        screen.refresh();
        this.choosingOption();
    }

    public void choosingOption() throws IOException {
        graphics.putString(24, 5, "f");
        graphics.putString(34, 5, "f");
        screen.refresh();

        while(true) {
            KeyStroke key = screen.readInput();
            this.processKey(key);
            if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'q' || key.getCharacter() == 'Q'))
                screen.close();
            if (key.getKeyType() == KeyType.EOF)
                break;
        }
    }

    public void cleanOptions() throws IOException {
        graphics.putString(24, 5, " ");
        graphics.putString(34, 5, " ");

        graphics.putString(24, 10, " ");
        graphics.putString(34, 10, " ");

        graphics.putString(24, 15, " ");
        graphics.putString(34, 15, " ");

        graphics.putString(20, 20, " ");
        graphics.putString(37, 20, " ");
        screen.refresh();
    }

    public void showOption(int option) throws IOException {
        if (option == 1) {
            this.cleanOptions();
            graphics.putString(24, 5, "f");
            graphics.putString(34, 5, "f");
            screen.refresh();
        }
        else if (option == 2) {
            this.cleanOptions();
            graphics.putString(24, 10, "f");
            graphics.putString(34, 10, "f");
            screen.refresh();
        }
        else if (option == 3) {
            this.cleanOptions();
            graphics.putString(24, 15, "f");
            graphics.putString(34, 15, "f");
            screen.refresh();
        }
        else if (option == 4) {
            this.cleanOptions();
            graphics.putString(20, 20, "f");
            graphics.putString(37, 20, "f");
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
                if (option == 1) {
                    level = 1;
                    game.setLevel(level);
                    game.getState().onMenu(game);
                }
                else if (option == 2) {
                    level = 2;
                    game.setLevel(level);
                    game.getState().onMenu(game);
                }
                else if (option == 3) {
                    level = 3;
                    game.setLevel(level);
                    game.getState().onMenu(game);
                }
                else if (option == 4) {
                    game.getState().onMenu(game);
                }
                break;
            default:
                break;
        }
    }
}
