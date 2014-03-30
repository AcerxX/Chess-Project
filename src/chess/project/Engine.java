/*
 * Copywright (C) [2014] [Alexandru MIHAI] [Andreea ILIES] [Teodora TANASE] [Anca ROSCA]
 * 
 * This file is part of Thunder Chickens Chess Project.
 * 
 * Title: The Chess Project of Team <Thunder Chickens> @ CS, PUB, RO. 
 */

package chess.project;

import java.io.IOException;
import java.util.Random;

/**
 *
 * @version 0.4.3a
 * @author Alexandru MIHAI
 */
public class Engine {
    
    static Board x;
    static Board y;
    static String theMove;
    static boolean isForced;
    static String color = "black";

    /**
     * Seteaza ce culoare sa mute.
     * 
     * @param turn 
     */
    static void setEngineColor(String turn) throws IOException {
        switch (turn) {
            case "white":
                color = "white";
                Moves.computeMove();
                break;
            case "black":
                color = "black";
                break;
        }
    }

    /**
     * Seteaza rularea sau oprirea din rulare in modul forced a engineului.
     * 
     * @param ok 
     */
    static void setForced(boolean ok) {
        isForced = ok;
    }

    /**
     * Resigns xD.
     */
    static void resign() {
        System.out.println("resign");
    }

    /**
     * x si y sunt coordonatele de pe tabla
     */
    static void move() {
        if (isForced)
        {    
            if (!Moves.isLegal(theMove))
                System.out.println("Go home! You're drunk");
            else
                if (Moves.isInTurn())
                {    
                    System.out.println("move " + theMove);
                }
        }
        else
        {
            Clock.stopped(false);
            //theMove = Moves.computeMove( x, y);
            if (Moves.isLegal(theMove))
                System.out.println("move " + theMove);
        }
    }
    
    /**
     * Verifica daca este sah.
     * 
     * @return 
     */
    static boolean checkIfCheck(){
        
        return false;
    }
    
    /**
     * Returneaza coordonatele i si j ale unei piese NEAGRE random de pe tabla. 
     * @return 
     */
    static int[] getRandomPiece() throws IOException{
        
        Random generator = new Random();
        int[][] pairs = new int[16][2];
        int k=0;
        
        for(int i = 2; i < 10; i++)
            for(int j = 2; j < 10; j++)
                if(Board.isBlackPiece(i,j)){
                    pairs[k][0] = i;
                    pairs[k][1] = j;
                    k++;
                }
        
        int l = generator.nextInt(k);
        int[] ret = new int[2];
        ret[0] = pairs[l][0];
        ret[1] = pairs[l][1];
        Logger.write("LOGGER::Engine.java::Am ales piesa::"+(char)Board.board[ret[0]][ret[1]]);
        return ret;
        
    }
    
}
