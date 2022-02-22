import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width;
    private int height;
    private Hero hero = new Hero(10, 10);
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    public Arena (int new_width, int new_height){
        width = new_width;
        height = new_height;
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            Coin new_coin = new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1);
            if((!positions.contains(new_coin.getPosition())) && new_coin.getPosition()!=hero.getPosition()){
                coins.add(new_coin);
                positions.add(new_coin.getPosition());
            }
        }
        return coins;
    }

    private void retrieveCoins(){
        for(Coin coin : coins){
            if(hero.getPosition().equals(coin.getPosition())) {
                coins.remove(coin);
                break;
            }
        }
    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for(int i=0; i<5; i++){
            Monster new_monster = new Monster(random.nextInt(width-2) + 1, random.nextInt(height-2)+1);
            if(!monsters.contains(new_monster) && !new_monster.getPosition().equals(hero.getPosition()))
                monsters.add(new_monster);
        }
        return monsters;
    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(graphics);
        for (Wall wall : walls)
            wall.draw(graphics);
        for(Coin coin : coins){
            coin.draw(graphics);
        }
        for(Monster monster : monsters)
            monster.draw(graphics);
    }

    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

    public Position moveUp(){
        int new_y = hero.getY() -1;
        int new_x = hero.getX();
        Position position = new Position(new_x, new_y);
        return position;
    }

    public Position moveDown(){
        int new_y = hero.getY() +1;
        int new_x = hero.getX();
        Position position = new Position(new_x, new_y);
        return position;
    }

    public Position moveRight(){
        int new_x = hero.getX() +1;
        int new_y = hero.getY();
        Position position = new Position(new_x, new_y);
        return position;
    }

    public Position moveLeft(){
        int new_x = hero.getX() -1;
        int new_y = hero.getY();
        Position position = new Position(new_x, new_y);
        return position;
    }

    private boolean canHeroMove(Position position) {
        int x = position.getX();
        int y = position.getY();
        if((x >= 0 && x < width) && ( y >= 0 && y < height)){
                for(int i = 0; i < walls.size(); i++){
                    if (walls.get(i).getPosition().equals(position)){
                        return false;
                    }
                }
                return true;
        }
        return false;
    }

    public void moveMonsters(){
        for(Monster monster : monsters){
            monster.setPosition(monster.move(this));
        }
    }

    public boolean verifyMonsterCollisions(){
        for(Monster monster : monsters){
            if(monster.getPosition().equals(hero.getPosition())){
                System.out.println("GAME OVER");
                return true;
            }
        }
        return false;
    }

}

