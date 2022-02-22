package pt.up.fe.ldts.frogger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.frogger.gameelement.movable.Car;
import pt.up.fe.ldts.frogger.gameelement.movable.TreeTrunk;
import pt.up.fe.ldts.frogger.gameelement.movable.Turtle;

import java.util.ArrayList;
import java.util.List;

public class MoveMovableElementsTest {
    private static Arena arena = new Arena(1,60, 30);

    @BeforeEach
    public void setUp() {
        long sleepTime = 1000/2;
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
        }
    }

    @Test
    public void moveCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(0, 1, "left"));
        cars.add(new Car(1, 1, "left"));
        cars.add(new Car(2, 1, "left"));
        cars.add(new Car(3, 1, "left"));
        cars.add(new Car(59, 1, "left"));

        cars.add(new Car(0, 5, "right"));
        cars.add(new Car(1, 5, "right"));
        cars.add(new Car(2, 5, "right"));
        cars.add(new Car(3, 5, "right"));
        cars.add(new Car(59, 5, "right"));

        arena.setCars(cars);
        arena.moveMovableElements();

        Assertions.assertEquals(arena.getCars().get(0).getPosition().getX(),59);
        Assertions.assertEquals(arena.getCars().get(0).getPosition().getY(),1);
        Assertions.assertEquals(arena.getCars().get(1).getPosition().getX(),0);
        Assertions.assertEquals(arena.getCars().get(1).getPosition().getY(),1);
        Assertions.assertEquals(arena.getCars().get(2).getPosition().getX(),1);
        Assertions.assertEquals(arena.getCars().get(2).getPosition().getY(),1);
        Assertions.assertEquals(arena.getCars().get(3).getPosition().getX(),2);
        Assertions.assertEquals(arena.getCars().get(3).getPosition().getY(),1);
        Assertions.assertEquals(arena.getCars().get(4).getPosition().getX(),58);
        Assertions.assertEquals(arena.getCars().get(4).getPosition().getY(),1);

        Assertions.assertEquals(arena.getCars().get(5).getPosition().getX(), 1);
        Assertions.assertEquals(arena.getCars().get(5).getPosition().getY(), 5);
        Assertions.assertEquals(arena.getCars().get(6).getPosition().getX(), 2);
        Assertions.assertEquals(arena.getCars().get(6).getPosition().getY(), 5);
        Assertions.assertEquals(arena.getCars().get(7).getPosition().getX(), 3);
        Assertions.assertEquals(arena.getCars().get(7).getPosition().getY(), 5);
        Assertions.assertEquals(arena.getCars().get(8).getPosition().getX(), 4);
        Assertions.assertEquals(arena.getCars().get(8).getPosition().getY(), 5);
        Assertions.assertEquals(arena.getCars().get(9).getPosition().getX(), 0);
        Assertions.assertEquals(arena.getCars().get(9).getPosition().getY(), 5);
    }

    @Test
    public void moveTreeTrunks() {
        List<TreeTrunk> treeTrunks = new ArrayList<>();
        treeTrunks.add(new TreeTrunk(0, 1, "left"));
        treeTrunks.add(new TreeTrunk(1, 1, "left"));
        treeTrunks.add(new TreeTrunk(2, 1, "left"));
        treeTrunks.add(new TreeTrunk(3, 1, "left"));
        treeTrunks.add(new TreeTrunk(59, 1, "left"));

        treeTrunks.add(new TreeTrunk(0, 5, "right"));
        treeTrunks.add(new TreeTrunk(1, 5, "right"));
        treeTrunks.add(new TreeTrunk(2, 5, "right"));
        treeTrunks.add(new TreeTrunk(3, 5, "right"));
        treeTrunks.add(new TreeTrunk(59, 5, "right"));

        arena.setTreeTrunks(treeTrunks);
        arena.moveMovableElements();

        Assertions.assertEquals(arena.getTreeTrunks().get(0).getPosition().getX(),59);
        Assertions.assertEquals(arena.getTreeTrunks().get(0).getPosition().getY(),1);
        Assertions.assertEquals(arena.getTreeTrunks().get(1).getPosition().getX(),0);
        Assertions.assertEquals(arena.getTreeTrunks().get(1).getPosition().getY(),1);
        Assertions.assertEquals(arena.getTreeTrunks().get(2).getPosition().getX(),1);
        Assertions.assertEquals(arena.getTreeTrunks().get(2).getPosition().getY(),1);
        Assertions.assertEquals(arena.getTreeTrunks().get(3).getPosition().getX(),2);
        Assertions.assertEquals(arena.getTreeTrunks().get(3).getPosition().getY(),1);
        Assertions.assertEquals(arena.getTreeTrunks().get(4).getPosition().getX(),58);
        Assertions.assertEquals(arena.getTreeTrunks().get(4).getPosition().getY(),1);

        Assertions.assertEquals(arena.getTreeTrunks().get(5).getPosition().getX(), 1);
        Assertions.assertEquals(arena.getTreeTrunks().get(5).getPosition().getY(), 5);
        Assertions.assertEquals(arena.getTreeTrunks().get(6).getPosition().getX(), 2);
        Assertions.assertEquals(arena.getTreeTrunks().get(6).getPosition().getY(), 5);
        Assertions.assertEquals(arena.getTreeTrunks().get(7).getPosition().getX(), 3);
        Assertions.assertEquals(arena.getTreeTrunks().get(7).getPosition().getY(), 5);
        Assertions.assertEquals(arena.getTreeTrunks().get(8).getPosition().getX(), 4);
        Assertions.assertEquals(arena.getTreeTrunks().get(8).getPosition().getY(), 5);
        Assertions.assertEquals(arena.getTreeTrunks().get(9).getPosition().getX(), 0);
        Assertions.assertEquals(arena.getTreeTrunks().get(9).getPosition().getY(), 5);
    }

    @Test
    public void moveTurtles() {
        List<Turtle> turtles = new ArrayList<>();
        turtles.add(new Turtle(0, 1, "left"));
        turtles.add(new Turtle(1, 1, "left"));
        turtles.add(new Turtle(2, 1, "left"));
        turtles.add(new Turtle(3, 1, "left"));
        turtles.add(new Turtle(59, 1, "left"));

        turtles.add(new Turtle(0, 5, "right"));
        turtles.add(new Turtle(1, 5, "right"));
        turtles.add(new Turtle(2, 5, "right"));
        turtles.add(new Turtle(3, 5, "right"));
        turtles.add(new Turtle(59, 5, "right"));

        arena.setTurtles(turtles);
        arena.moveMovableElements();

        Assertions.assertEquals(arena.getTurtles().get(0).getPosition().getX(),59);
        Assertions.assertEquals(arena.getTurtles().get(0).getPosition().getY(),1);
        Assertions.assertEquals(arena.getTurtles().get(1).getPosition().getX(),0);
        Assertions.assertEquals(arena.getTurtles().get(1).getPosition().getY(),1);
        Assertions.assertEquals(arena.getTurtles().get(2).getPosition().getX(),1);
        Assertions.assertEquals(arena.getTurtles().get(2).getPosition().getY(),1);
        Assertions.assertEquals(arena.getTurtles().get(3).getPosition().getX(),2);
        Assertions.assertEquals(arena.getTurtles().get(3).getPosition().getY(),1);
        Assertions.assertEquals(arena.getTurtles().get(4).getPosition().getX(),58);
        Assertions.assertEquals(arena.getTurtles().get(4).getPosition().getY(),1);

        Assertions.assertEquals(arena.getTurtles().get(5).getPosition().getX(), 1);
        Assertions.assertEquals(arena.getTurtles().get(5).getPosition().getY(), 5);
        Assertions.assertEquals(arena.getTurtles().get(6).getPosition().getX(), 2);
        Assertions.assertEquals(arena.getTurtles().get(6).getPosition().getY(), 5);
        Assertions.assertEquals(arena.getTurtles().get(7).getPosition().getX(), 3);
        Assertions.assertEquals(arena.getTurtles().get(7).getPosition().getY(), 5);
        Assertions.assertEquals(arena.getTurtles().get(8).getPosition().getX(), 4);
        Assertions.assertEquals(arena.getTurtles().get(8).getPosition().getY(), 5);
        Assertions.assertEquals(arena.getTurtles().get(9).getPosition().getX(), 0);
        Assertions.assertEquals(arena.getTurtles().get(9).getPosition().getY(), 5);
    }
}
