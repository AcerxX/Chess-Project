/*
 * Copywright (C) [2014] [Alexandru MIHAI] [Andreea ILIES] [Teodora TANASE] [Anca ROSCA]
 * 
 * This file is part of Thunder Chickens Chess Project.
 * 
 * Title: The Chess Project of Team <Thunder Chickens> @ CS, PUB, RO. 
 */

package chess.project;

/**
 *
 * @version 0.3a Etapa 1 FINAL
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
    static void setEngineColor(String turn) {
        switch (turn) {
            case "white":
                color = "white";
                Moves.computeMove("WE ARE THUNDER!");
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
        if (ok)
            isForced = true;
        else
            isForced = false;
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
            theMove = Moves.computeMove( x, y);
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
    
}
