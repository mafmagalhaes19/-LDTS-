package pt.up.fe.ldts.frogger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.frogger.gameelement.elementfactory.MovableElementsFactory;
import pt.up.fe.ldts.frogger.gameelement.movable.MovableElement;

import java.util.List;

public class MovableElementsFactoryTest {

    //TODO: documentar métodos extra

    @Test
    public void createFrogFactory() {
        MovableElementsFactory factory = new MovableElementsFactory(1, "Frog");
        List<MovableElement> frog = factory.create();
        Assertions.assertEquals(frog.get(0).getElementType(), "Frog");
    }

    @Test
    public void createCarFactory() {
        MovableElementsFactory factory = new MovableElementsFactory(1, "Car");
        List<MovableElement> cars = factory.create();
        Assertions.assertEquals(cars.get(0).getElementType(), "Car");
    }

    @Test
    public void createTreeTrunkFactory() {
        MovableElementsFactory factory = new MovableElementsFactory(1, "TreeTrunk");
        List<MovableElement> treeTrunks = factory.create();
        Assertions.assertEquals(treeTrunks.get(0).getElementType(), "TreeTrunk");
    }

    @Test
    public void createTurtleFactory() {
        MovableElementsFactory factory = new MovableElementsFactory(1, "Turtle");
        List<MovableElement> turtles = factory.create();
        Assertions.assertEquals(turtles.get(0).getElementType(), "Turtle");
    }
}
