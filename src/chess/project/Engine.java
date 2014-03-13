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
 * @version 0.2.3a
 * @author Alexandru MIHAI
 */
public class Engine {
    
    static Board x;
    static Board y;
    static String theMove;
    static boolean isForced;
    static String color = "black";
    
    /**
     * Metoda white()
     * 
     * Proceseaza comanda "white" de la Winboard.
     */
    static void white()
    {
        color = "white";
        Moves.computeMove("WE ARE THUNDER!");
    }
    
    /**
     * Metoda black()
     * 
     * Proceseaza comanda "black" de la Winboard.
     */
    static void black()
    {
        color = "black";
    }

    /**
     * Metoda setEngineColor(String)
     * 
     * Seteaza ce culoare sa mute.
     * 
     * @param turn 
     */
    static void setEngineColor(String turn) {
        switch (turn) {
            case "white":
                Engine.white();
                break;
            case "black":
                Engine.black();
                break;
        }
    }

    /**
     * Metoda setForced(boolean)
     * 
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
     * Metoda resign()
     * 
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
                System.out.println("Go home! You drunk");
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
    
}
