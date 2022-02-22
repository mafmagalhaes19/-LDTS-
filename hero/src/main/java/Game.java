import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {

    TerminalSize terminalSize = new TerminalSize(40, 20);
    DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
    Terminal terminal = terminalFactory.createTerminal();
    Screen screen = new TerminalScreen(terminal);
    TextGraphics graphics = screen.newTextGraphics();
    private Arena arena = new Arena(40, 20);


    public Game() throws IOException {
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary
    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(graphics);
        screen.refresh();
    }

    private void moveHero(Position position) {
        arena.moveHero(position);
    }

    private void processKey(KeyStroke key) {
        switch(key.getKeyType()){
            case ArrowRight:
                moveHero(arena.moveRight());
                break;
            case ArrowLeft:
                moveHero(arena.moveLeft());
                break;
            case ArrowUp:
                moveHero(arena.moveUp());
                break;
            case ArrowDown:
                moveHero(arena.moveDown());
                break;
            default:
                break;
        }
    }

    public void run() throws IOException {
        while(true) {
            this.draw();
            KeyStroke key = screen.readInput();
            this.processKey(key);
            if(arena.verifyMonsterCollisions()){
                screen.close();
                break;
            }

            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
                screen.close();
            }
            if (key.getKeyType() == KeyType.EOF){
                break;
            }
            arena.moveMonsters();
            if(arena.verifyMonsterCollisions()){
                screen.close();
                break;
            }
        }
    }

}
