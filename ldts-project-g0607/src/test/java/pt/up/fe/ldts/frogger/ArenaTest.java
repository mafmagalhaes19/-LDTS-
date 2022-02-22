package pt.up.fe.ldts.frogger;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.frogger.gameelement.movable.Car;
import pt.up.fe.ldts.frogger.gameelement.movable.Frog;
import pt.up.fe.ldts.frogger.gameelement.movable.TreeTrunk;
import pt.up.fe.ldts.frogger.gameelement.movable.Turtle;
import pt.up.fe.ldts.frogger.gameelement.nonmovable.Grass;
import pt.up.fe.ldts.frogger.gameelement.nonmovable.Road;
import pt.up.fe.ldts.frogger.gameelement.nonmovable.Sidewalk;
import pt.up.fe.ldts.frogger.gameelement.nonmovable.Water;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArenaTest {
    private TextGraphics graphics;
    private Arena arena = new Arena(1, 60, 30);
    private Frog frog;
    private List<Car> cars = new ArrayList<>();
    private List<TreeTrunk> treeTrunks = new ArrayList<>();
    private List<Turtle> turtles = new ArrayList<>();
    private Road road;
    private Water water;
    private Grass grass;
    private Sidewalk firstSidewalk, secondSidewalk;

    @BeforeEach
    public void setUp() throws IOException {
        TerminalSize terminalSize = new TerminalSize(60, 30);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();
        Screen screen = new TerminalScreen(terminal);
        graphics = screen.newTextGraphics();

        frog = Mockito.mock(Frog.class);
        arena.setFrog(frog);
        road = Mockito.mock(Road.class);
        arena.setRoad(road);
        water = Mockito.mock(Water.class);
        arena.setWater(water);
        grass = Mockito.mock(Grass.class);
        arena.setGrass(grass);
        firstSidewalk = Mockito.mock(Sidewalk.class);
        arena.setFirstSidewalk(firstSidewalk);
        secondSidewalk = Mockito.mock(Sidewalk.class);
        arena.setSecondSidewalk(secondSidewalk);

        Car car1 = Mockito.mock(Car.class);
        Car car2 = Mockito.mock(Car.class);
        Car car3 = Mockito.mock(Car.class);
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        arena.setCars(cars);

        TreeTrunk treeTrunk1 = Mockito.mock(TreeTrunk.class);
        TreeTrunk treeTrunk2 = Mockito.mock(TreeTrunk.class);
        TreeTrunk treeTrunk3 = Mockito.mock(TreeTrunk.class);
        treeTrunks.add(treeTrunk1);
        treeTrunks.add(treeTrunk2);
        treeTrunks.add(treeTrunk3);
        arena.setTreeTrunks(treeTrunks);

        Turtle turtle1 = Mockito.mock(Turtle.class);
        Turtle turtle2 = Mockito.mock(Turtle.class);
        Turtle turtle3 = Mockito.mock(Turtle.class);
        turtles.add(turtle1);
        turtles.add(turtle2);
        turtles.add(turtle3);
        arena.setTurtles(turtles);
    }

    @Test
    public void drawArenaTest() {
        arena.draw(graphics);

        Mockito.verify(water, Mockito.times(1)).draw(graphics);
        Mockito.verify(grass, Mockito.times(1)).draw(graphics);
        Mockito.verify(firstSidewalk, Mockito.times(1)).draw(graphics);
        Mockito.verify(secondSidewalk, Mockito.times(1)).draw(graphics);
        Mockito.verify(frog, Mockito.times(1)).draw(graphics);

      for (Car car: cars)
            Mockito.verify(car, Mockito.times(1)).draw(graphics);
        for (Turtle turtle: turtles)
            Mockito.verify(turtle, Mockito.times(1)).draw(graphics);
        for (TreeTrunk treeTrunk: treeTrunks)
            Mockito.verify(treeTrunk, Mockito.times(1)).draw(graphics);
    }
}