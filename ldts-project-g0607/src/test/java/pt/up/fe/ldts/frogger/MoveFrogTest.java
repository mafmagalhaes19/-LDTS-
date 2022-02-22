package pt.up.fe.ldts.frogger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.frogger.gameelement.movable.Frog;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveFrogTest {
    private Frog frog;
    private Position position;

    @BeforeEach
    public void setUp() {
        frog = new Frog(1, 1);
        position = frog.getPosition();
    }

    @Test
    void moveUpTest() {
        Assertions.assertEquals(position.getY()-1, frog.moveUp().getY()); //y diminui
        Assertions.assertEquals(position.getX(), frog.moveUp().getX());   //x mantém-se
    }

    @Test
    void moveDownTest() {
        assertEquals(position.getY()+1, frog.moveDown().getY()); //y aumenta
        assertEquals(position.getX(), frog.moveDown().getX());   //x mantém-se
    }

    @Test
    void moveRight() {
        assertEquals(position.getY(), frog.moveRight().getY());   //y mantém-se
        assertEquals(position.getX()+1, frog.moveRight().getX()); //x aumenta
    }

    @Test
    void moveLeft() {
        assertEquals(position.getY(), frog.moveLeft().getY());   //y mantém-se
        assertEquals(position.getX()-1, frog.moveLeft().getX()); //x diminui
    }
}
