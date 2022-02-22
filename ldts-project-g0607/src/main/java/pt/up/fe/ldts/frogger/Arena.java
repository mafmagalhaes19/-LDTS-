package pt.up.fe.ldts.frogger;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.frogger.gameelement.elementcommand.MoveLeft;
import pt.up.fe.ldts.frogger.gameelement.elementcommand.MoveRight;
import pt.up.fe.ldts.frogger.gameelement.elementfactory.MovableElementsFactory;
import pt.up.fe.ldts.frogger.gameelement.movable.*;
import pt.up.fe.ldts.frogger.gameelement.nonmovable.Grass;
import pt.up.fe.ldts.frogger.gameelement.nonmovable.Road;
import pt.up.fe.ldts.frogger.gameelement.nonmovable.Sidewalk;
import pt.up.fe.ldts.frogger.gameelement.nonmovable.Water;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int level;
    private int width;
    private int height;
    private Frog frog;
    private List<Car> cars = new ArrayList<>();
    private List<TreeTrunk> treeTrunks = new ArrayList<>();
    private List<Turtle> turtles = new ArrayList<>();
    private Road road;
    private Water water;
    private Grass grass;
    private Sidewalk firstSidewalk;
    private Sidewalk secondSidewalk;

    private int FPSElements = 2;
    private int frameTimeElements = 1000/FPSElements;
    private long startTime;

    public Arena (int level, int width, int height) {
        this.level = level;
        this.width = width;
        this.height = height;

        //non-movable elements have fixed positions in the arena
        this.road = new Road(17, 26);
        this.water = new Water(4, 13);
        this.grass = new Grass(0, 3);
        this.firstSidewalk = new Sidewalk(27, 29);
        this.secondSidewalk = new Sidewalk(14, 16);
        refresh();

        startTime = System.currentTimeMillis();
    }

    public void setLevel(int newLevel){
        level = newLevel;
        refresh();
    }

    public void refresh() {
        createFrog();
        createCars();
        createTreeTrunks();
        createTurtles();
    }

    public void createFrog() {
        this.frog = (Frog) new MovableElementsFactory(level, "Frog").create().get(0);
    }

    public void createCars() {
        cars.clear();
        for (int row = secondSidewalk.getPosition().getYMax()+1; row < firstSidewalk.getPosition().getYMin(); row++) {
            List<MovableElement> m = new MovableElementsFactory(level, row, "Car").create();
            if (cars.isEmpty())
                cars = new ArrayList<Car>((List) m);
            else
                cars.addAll(new ArrayList<Car>((List) m));
        }
    }

    public void createTreeTrunks() {
        treeTrunks.clear();
        for (int row = water.getPosition().getYMin()+1; row <= water.getPosition().getYMax(); row++) {
            List<MovableElement> m = new MovableElementsFactory(level, row, "TreeTrunk").create();
            if (treeTrunks.isEmpty())
                treeTrunks = new ArrayList<TreeTrunk>((List) m);
            else
                this.treeTrunks.addAll(new ArrayList<TreeTrunk>((List) m));
            row++;
        }
    }

    public void createTurtles() {
        turtles.clear();
        for (int row = water.getPosition().getYMin(); row <= water.getPosition().getYMax(); row++) {
            List<MovableElement> m = new MovableElementsFactory(level, row, "Turtle").create();
            if (turtles.isEmpty())
                turtles = new ArrayList<Turtle>((List) m);
            else
                this.turtles.addAll(new ArrayList<Turtle>((List) m));
            row++;
        }
    }

    public Frog getFrog(){
        return frog;
    }

    public List<Car> getCars(){
        return cars;
    }

    public List<TreeTrunk> getTreeTrunks(){
        return treeTrunks;
    }

    public List<Turtle> getTurtles(){
        return turtles;
    }

    public void setFrog(Frog frog) {
        this.frog = frog;
    }

    //for testing purposes only
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    //for testing purposes only
    public void setTreeTrunks(List<TreeTrunk> treeTrunks) {
        this.treeTrunks = treeTrunks;
    }

    //for testing purposes only
    public void setTurtles(List<Turtle> turtles) {
        this.turtles = turtles;
    }

    public void setRoad(Road road) {
        this.road = road;
    }

    public void setWater(Water water) {
        this.water = water;
    }

    public void setGrass(Grass grass) {
        this.grass = grass;
    }

    public void setFirstSidewalk(Sidewalk firstSidewalk) {
        this.firstSidewalk = firstSidewalk;
    }

    public void setSecondSidewalk(Sidewalk secondSidewalk) {
        this.secondSidewalk = secondSidewalk;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        road.draw(graphics);
        water.draw(graphics);
        firstSidewalk.draw(graphics);
        secondSidewalk.draw(graphics);
        grass.draw(graphics);
        for (Car car: cars)
            car.draw(graphics);
        for(TreeTrunk treeTrunk: treeTrunks)
            treeTrunk.draw(graphics);
        for(Turtle turtle: turtles)
            turtle.draw(graphics);
        frog.draw(graphics);
    }

    //Possibly to change after implementing the state pattern
    public boolean verifyCarCollision(Position frogNewPosition) {
        for(Car car: cars) {
            if (car.getPosition().equals(frogNewPosition)) {
                System.out.println("YOU WERE HIT BY A CAR");
                return true;
            }
        }
        return false;
    }

    //Possibly to change after implementing the state pattern
    public boolean verifyTreeTrunkCollision(Position frogNewPosition) {
        for (TreeTrunk treeTrunk : treeTrunks) {
            if (treeTrunk.getPosition().equals(frogNewPosition))
                return true;
        }
        return false;
    }

    //Possibly to change after implementing the state pattern
    public boolean verifyTurtleCollision(Position frogNewPosition) {
        for(Turtle turtle : turtles) {
            if (turtle.getPosition().equals(frogNewPosition))
                return true;
        }
        return false;
    }

    //Possibly to change after implementing the state pattern
    public boolean verifyWaterCollision(Position frogNewPosition) {
        if (frogNewPosition.getY() >= water.getPosition().getYMin() && frogNewPosition.getY() <= water.getPosition().getYMax()
                && !verifyTreeTrunkCollision(frogNewPosition) && !verifyTurtleCollision(frogNewPosition)) {
            System.out.println("YOU FELL INTO THE RIVER");
            return true;
        }
        return false;
    }

    //Possibly to change after implementing the state pattern
    public boolean verifyGrassCollision(Position frogNewPosition) {
        if (frogNewPosition.getY() >= grass.getPosition().getYMin() && frogNewPosition.getY() <= grass.getPosition().getYMax()) {
            System.out.println("YOU WON");
            return true;
        }
        return false;
    }

    public int verifyFrogCollision(Position position) {
        if (position.getX() < 0 || position.getX() >= width ||
            position.getY() < 0 || position.getY() >= height)
            return 3;

        if (verifyCarCollision(position))
            return 2;
        if (verifyWaterCollision(position))
            return 2;
        if (verifyGrassCollision(position))
            return 1;
        return 0;
    }

    public int moveFrog(Position position) {
        int value = verifyFrogCollision(position);
        if (value != 3)
            frog.setPosition(position);
        return value;
    }

    public int moveCars() {
        for (Car car: cars) {
            if (car.getMovementDirection() == "left")
                car.move(new MoveLeft());
            else //car.getMovementDirection() == "right"
                car.move(new MoveRight());
            if (car.getPosition().equals(frog.getPosition())){
                System.out.println("YOU WERE HIT BY A CAR");
                return 2;
            }
        }
        return 0;
    }

    public void moveTreeTrunks() {
        for (TreeTrunk treeTrunk: treeTrunks) {
            if (treeTrunk.getMovementDirection() == "left") {
                if (treeTrunk.getPosition().equals(frog.getPosition()))
                    frog.move(new MoveLeft());
                treeTrunk.move(new MoveLeft());
            }
            else { //treeTrunk.getMovementDirection() == "right"
                if (treeTrunk.getPosition().equals(frog.getPosition()))
                    frog.move(new MoveRight());
                treeTrunk.move(new MoveRight());
            }
        }
    }

    public void moveTurtles() {
        for (Turtle turtle: turtles) {
            if (turtle.getMovementDirection() == "left") {
                if (turtle.getPosition().equals(frog.getPosition()))
                    frog.move(new MoveLeft());
                turtle.move(new MoveLeft());
            }
            else { //turtle.getMovementDirection() == "right"
                if (turtle.getPosition().equals(frog.getPosition()))
                    frog.move(new MoveRight());
                turtle.move(new MoveRight());
            }
        }
    }

    public int moveMovableElements() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (elapsedTime < frameTimeElements)
            return 0;
        startTime = System.currentTimeMillis();

        int value = moveCars();
        moveTreeTrunks();
        moveTurtles();
        return value;
    }
}
