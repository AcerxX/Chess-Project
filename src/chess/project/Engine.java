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
 * @author Alexandru MIHAI
 */
public class Engine {

   // static int clock;
    
    static Board x;
    static Board y;
    static String theMove;
    static boolean isForced;
    
    /**
     * Method white()
     * 
     * Handles white command from Winboard
     */
    static void white()
    {
        Engine.move();
        Engine.setColorOnMove("black");
        Clock.stopped(true);
    }
    
    /**
     * Method black()
     * 
     * Handles black command from Winboard
     */
    static void black()
    {
        Engine.move();
        Engine.setColorOnMove("white");
        Clock.stopped(true);
    }

    /**
     * Method setColorOnMove(String)
     * 
     * Sets the right color to move
     * 
     * @param turn 
     */
    static void setColorOnMove(String turn) {
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
     * Method setForced(boolean)
     * 
     * Sets whenever the engine is in force mode or not
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
     * Method resign()
     * 
     * Resigns xD.
     */
    static void resign() {
        System.out.println("resign");
    }

    
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
            // x si y sunt coordonatele de pe tabla
            if (Moves.isLegal(theMove))
                System.out.println("move " + theMove);
        }
    }
    
}
