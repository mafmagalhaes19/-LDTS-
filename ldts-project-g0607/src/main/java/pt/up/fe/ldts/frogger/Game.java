package pt.up.fe.ldts.frogger;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import pt.up.fe.ldts.frogger.gameelement.elementfactory.MovableElementsFactory;
import pt.up.fe.ldts.frogger.gameelement.movable.Frog;
import pt.up.fe.ldts.frogger.menus.Level;
import pt.up.fe.ldts.frogger.state.MenuState;
import pt.up.fe.ldts.frogger.state.State;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Game {
    private int width = 60, height = 30;
    private Screen screen;
    private TextGraphics graphics;
    private Arena arena;
    private State state;
    private Level level;
    private int lives;

    public Game() throws IOException, FontFormatException, URISyntaxException {
        createScreen();

        level = new Level(this); //sets default level to 1
        lives = 3;
        arena = new Arena(level.getLevel(), width, height);
    }

    public void createScreen() throws IOException, FontFormatException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("Frogger.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        DefaultTerminalFactory factory = new DefaultTerminalFactory();

        Font loadedFont = font.deriveFont(Font.PLAIN, 20);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        factory.setTerminalEmulatorFontConfiguration(fontConfig);
        factory.setForceAWTOverSwing(true);
        factory.setInitialTerminalSize(new TerminalSize(width, height));

        Terminal terminal = factory.createTerminal();
        ((AWTTerminalFrame) terminal).addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
            }
        });

        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        graphics = screen.newTextGraphics();
    }

    public void initializeNewGame() throws IOException {
        state = new MenuState(this);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Screen getScreen() {
        return screen;
    }

    public TextGraphics getGraphics() {
        return graphics;
    }

    public State getState() {
        return state;
    }

    public int getLives() {
        return lives;
    }

    public void setState(State newState) {
        state = newState;
    }

    public void setLevel(int newLevel) {
        level.setLevel(newLevel);
        arena.setLevel(newLevel);
    }

    //for testing purposes only
    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    //for testing purposes only
    public void setArena(Arena arena) {
        this.arena = arena;
    }

    //for testing purposes only
    public void setGraphics(TextGraphics graphics) {
        this.graphics = graphics;
    }

    public void drawLevel() {
        String levelStr = "LEVEL";
        graphics.putString(53, 0, levelStr);
        graphics.putString(59, 0, Integer.toString(level.getLevel()));
    }

    public void drawLives() {
        int positionX = 58;
        for (int i = 0; i < lives; i++) {
            graphics.putString(positionX, 1, "h");
            positionX = positionX - 2;
        }

    }

    public void draw() throws IOException {
        screen.clear();
        arena.draw(graphics);
        drawLevel();
        drawLives();
        screen.refresh();
    }

    public int processKey(KeyStroke key) {
        int value = 0;
        if (key == null)
            return value;

        switch (key.getKeyType()) {
            case ArrowRight:
                value = arena.moveFrog(arena.getFrog().moveRight());
                break;
            case ArrowLeft:
                value = arena.moveFrog(arena.getFrog().moveLeft());
                break;
            case ArrowUp:
                value = arena.moveFrog(arena.getFrog().moveUp());
                break;
            case ArrowDown:
                value = arena.moveFrog(arena.getFrog().moveDown());
                break;
            default:
                break;
        }
        return value;
    }

    public void processExitValue(int value) throws IOException {
        if (value == 0 || value == 3)
            return;
        if (value == 1) {
            lives = 3;
            arena.setFrog((Frog) new MovableElementsFactory(level.getLevel(), "Frog").create().get(0));
            state.onWin(this);
        }
        if (value == 2) {
            lives--;
            arena.setFrog((Frog) new MovableElementsFactory(level.getLevel(), "Frog").create().get(0));
            if (lives == 0) {
                lives = 3;
                state.onLose(this);
            }
        }
    }

    public void playGame() throws IOException {
        int FPSGame = 5;
        int frameTimeGame = 1000/FPSGame;
        int value = 0;

        while (true) {
            long startTime = System.currentTimeMillis();

            this.draw();
            KeyStroke key = screen.pollInput();

            value = this.processKey(key);
            processExitValue(value);

            if (key != null && key.getKeyType() == KeyType.Character && (key.getCharacter() == 'q' || key.getCharacter() == 'Q')) {
                screen.close();
                break;
            }
            else if (key != null && key.getKeyType() == KeyType.EOF)
                break;

            value = arena.moveMovableElements();
            processExitValue(value);

            long elapsedTimeGame = System.currentTimeMillis() - startTime;
            long sleepTime = frameTimeGame - elapsedTimeGame;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
