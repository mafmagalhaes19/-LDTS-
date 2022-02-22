package pt.up.fe.ldts.frogger.menus;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import pt.up.fe.ldts.frogger.Game;

import java.io.*;
import java.util.Scanner;

public class Instructions {
    private Game game;
    private Screen screen;
    private TextGraphics graphics;
    private File instructionsFile;
    private Scanner reader;

    public Instructions(Game game) throws FileNotFoundException {
        this.instructionsFile =  new File("src/main/resources/Instructions.txt");
        this.reader = new Scanner(instructionsFile);
        this.game = game;
        this.screen = game.getScreen();
        this.graphics = game.getGraphics();
    }

    public void show() throws IOException {
        screen.clear();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#336699"));

        String Title = reader.nextLine();
        String r1 = reader.nextLine();
        String r2 = reader.nextLine();
        String r3 = reader.nextLine();
        String r4 = reader.nextLine();
        String r5 = reader.nextLine();
        String r6 = reader.nextLine();
        String r7 = reader.nextLine();
        String r8 = reader.nextLine();
        String r9 = reader.nextLine();
        String r10 = reader.nextLine();
        String r11 = reader.nextLine();
        String r12 = reader.nextLine();
        String r13 = reader.nextLine();
        String r14 = reader.nextLine();
        String r15 = reader.nextLine();
        String empty = "                                                                            ";
        reader.close();

        graphics.putString(new TerminalPosition(0, 0), empty);
        graphics.putString(new TerminalPosition(0, 1), Title);
        graphics.putString(new TerminalPosition(0, 2), empty);
        graphics.putString(new TerminalPosition(0, 3), r1);
        graphics.putString(new TerminalPosition(0, 4), r2);
        graphics.putString(new TerminalPosition(0, 5), empty);
        graphics.putString(new TerminalPosition(0, 6), r3);
        graphics.putString(new TerminalPosition(0, 7), empty);
        graphics.putString(new TerminalPosition(0, 8), r4);
        graphics.putString(new TerminalPosition(0, 9), r5);
        graphics.putString(new TerminalPosition(0, 10), empty);
        graphics.putString(new TerminalPosition(0, 11), r6);
        graphics.putString(new TerminalPosition(0, 12), r7);
        graphics.putString(new TerminalPosition(0, 13), empty);
        graphics.putString(new TerminalPosition(0, 14), r8);
        graphics.putString(new TerminalPosition(0, 15), r9);
        graphics.putString(new TerminalPosition(0, 16), empty);
        graphics.putString(new TerminalPosition(0, 17), r10);
        graphics.putString(new TerminalPosition(0, 18), empty);
        graphics.putString(new TerminalPosition(0, 19), r11);
        graphics.putString(new TerminalPosition(0, 20), r12);
        graphics.putString(new TerminalPosition(0, 21), r13);
        graphics.putString(new TerminalPosition(0, 22), empty);
        graphics.putString(new TerminalPosition(0, 23), r14);
        graphics.putString(new TerminalPosition(0, 24), empty);
        graphics.putString(new TerminalPosition(0, 25), r15);

        for (int y = 23; y < game.getHeight(); y++)
            graphics.putString(new TerminalPosition(0, y), empty);
        screen.refresh();
        this.choosingOption();
    }

    public void choosingOption() throws IOException {
        screen.refresh();
        while(true) {
            KeyStroke key = screen.readInput();
            //this.processKey(key);
            if (key.getKeyType() == KeyType.Character && (key.getCharacter() == 'q' || key.getCharacter() == 'Q'))
                screen.close();
            if (key.getKeyType() == KeyType.EOF)
                break;
            if(key.getKeyType() == KeyType.Enter ){
                game.getState().onMenu(game);
            }
        }
    }

}

