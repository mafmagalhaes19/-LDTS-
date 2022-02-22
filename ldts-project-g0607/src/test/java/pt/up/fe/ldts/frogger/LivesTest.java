package pt.up.fe.ldts.frogger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.frogger.gameelement.movable.Car;
import pt.up.fe.ldts.frogger.gameelement.movable.Frog;
import pt.up.fe.ldts.frogger.state.GameState;
import pt.up.fe.ldts.frogger.state.LoseState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class LivesTest {

    @Test
    public void NewGameLivesTest() throws IOException, URISyntaxException, FontFormatException {
        Game game = new Game();
        int lives = game.getLives();
        Assertions.assertEquals(3, lives);
    }

    @Test
    public void LostOneLife() throws IOException, URISyntaxException, FontFormatException {
        Game game = new Game();
        game.setState(new GameState(game));

        Arena newArena = new Arena(1, 60, 30);
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(30, 28, "left"));
        newArena.setCars(cars);

        Frog frog = new Frog(1,1);
        newArena.setFrog(frog);
        game.setArena(newArena);
        game.processExitValue(newArena.moveFrog(new Position(30, 28)));

        int lives = game.getLives();
        Assertions.assertEquals(2, lives);
    }

    @Test
    public void LostTwoLives() throws IOException, URISyntaxException, FontFormatException {
        Game game = new Game();
        game.setState(new GameState(game));

        Arena newArena = new Arena(1, 60, 30);
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(30, 28, "left"));
        newArena.setCars(cars);

        Frog frog = new Frog(1,1);
        newArena.setFrog(frog);
        game.setArena(newArena);
        game.processExitValue(newArena.moveFrog(new Position(30, 28)));
        game.processExitValue(newArena.moveFrog(new Position(30, 28)));

        int lives = game.getLives();
        Assertions.assertEquals(1, lives);
    }

    //We couldn´t force the screen to close (it needed user input)
    // Although, if closed manually, all Tests pass

    @Test
    public void LostTheGame() throws IOException, URISyntaxException, FontFormatException {
        Game game = new Game();
        game.setState(new GameState(game));

        Arena newArena = new Arena(1, 60, 30);
        java.util.List<Car> cars = new ArrayList<>();
        cars.add(new Car(30, 28, "left"));
        newArena.setCars(cars);

        Frog frog = new Frog(1,1);
        newArena.setFrog(frog);
        game.setArena(newArena);
        game.processExitValue(newArena.moveFrog(new Position(30, 28)));
        game.processExitValue(newArena.moveFrog(new Position(30, 28)));
        game.processExitValue(newArena.moveFrog(new Position(30, 28)));

        int lives = game.getLives();

        //when all lives are lost the number return to 3 for the new game
        Assertions.assertEquals(3, lives);
        LoseState lostGame = (LoseState) game.getState();
        Assertions.assertTrue(lostGame.getGameWasLost());
    }
}
