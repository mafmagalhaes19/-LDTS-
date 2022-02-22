package pt.up.fe.ldts.frogger;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.frogger.gameelement.movable.Frog;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class  GameTest {
    private static Screen screen;
    private static Game game;
    private static Arena arena;
    private static TextGraphics graphics;
    private static Frog frog;

    @BeforeAll
    public static void setUp() throws IOException, FontFormatException, URISyntaxException {
        game = new Game();
        arena = Mockito.mock(Arena.class);
        screen = Mockito.mock(Screen.class);
        graphics = Mockito.mock(TextGraphics.class);
        game.setScreen(screen);
        game.setArena(arena);
        game.setGraphics(graphics);

        frog = new Frog(1, 1);
        Mockito.when(arena.getFrog()).thenReturn(frog);
    }

   @Test
    public void drawTest() throws IOException {
        game.draw();

        Mockito.verify(screen, Mockito.times(1)).clear();
        Mockito.verify(arena, Mockito.times(1)).draw(graphics);
        try {
            Mockito.verify(screen, Mockito.times(1)).refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processKeyArrowUp() {
        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowUp);

        game.processKey(key);
        Mockito.verify(arena, Mockito.times(1)).moveFrog(frog.moveUp());
    }

    @Test
    public void processKeyArrowDown() {
        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowDown);

        game.processKey(key);
        Mockito.verify( arena, Mockito.times(1)).moveFrog(frog.moveDown());
    }

    @Test
    public void processKeyArrowRight() {
        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowRight);

        game.processKey(key);
        Mockito.verify( arena, Mockito.times(1)).moveFrog(frog.moveRight());
    }

    @Test
    public void processKeyArrowLeft() {
        KeyStroke key = Mockito.mock(KeyStroke.class);
        Mockito.when(key.getKeyType()).thenReturn(KeyType.ArrowLeft);

        game.processKey(key);
        Mockito.verify( arena, Mockito.times(1)).moveFrog(frog.moveLeft());
    }
}
