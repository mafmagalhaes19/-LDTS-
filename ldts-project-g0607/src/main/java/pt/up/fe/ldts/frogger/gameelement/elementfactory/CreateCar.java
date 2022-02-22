package pt.up.fe.ldts.frogger.gameelement.elementfactory;

import pt.up.fe.ldts.frogger.gameelement.movable.Car;
import pt.up.fe.ldts.frogger.gameelement.movable.MovableElement;
import pt.up.fe.ldts.frogger.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateCar extends MovableElementsFactory {

    public CreateCar(int level, int row) {
        super(level, row);
    }

    //number of obstacles in each traffic lane increases with the level
    public int numElements() {
        Random random = new Random();
        if (level == 1)
            return random.nextInt(7 - 4) + 4; //for level 1 the number of cars in a traffic lane will be between 4 and 6
        if (level == 2)
            return random.nextInt(10 - 7) + 7; //for level 2 the number of cars in a traffic lane will be between 7 and 9
        else //level == 3
            return random.nextInt(14 - 10) + 10; //for level 3 the number of cars in a traffic lane will be between 10 and 13
    }

    public String generateMovementDirection() {
        Random random = new Random();
        if (random.nextInt(2) == 0)
            return "left";
        else
            return "right";
    }

    //check if the random method isn't accidentally creating two cars at the same position
    public boolean checkOverlapping(List<MovableElement> cars, Position position) {
        for (MovableElement car: cars)
            if (car.getPosition().equals(position))
                return true;
        return false;
    }

    @Override
    public List<MovableElement> create() {
        Random random = new Random();
        List<MovableElement> cars = new ArrayList<>();

        int numElements = numElements();
        String movementDirection = generateMovementDirection();

        for (int i = 0; i < numElements; i++) {
            int x;
            do {
                x = random.nextInt(60);
            } while (checkOverlapping(cars, new Position(x, row)));
            Car car = new Car(x, row, movementDirection);
            cars.add(car);
        }
        return cars;
    }
}
