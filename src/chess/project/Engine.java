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
    
/*    static void resetHash() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
*/  static Board x;
    static Board y;
    static String theMove;
    static boolean isForced;
     
    static void resetHash()
    {
        
    }
    
    static void white()
    {
        Engine.move();
        Engine.setColorOnMove("black");
        Clock.stopped(true);
    }
    
    static void black()
    {
        Engine.move();
        Engine.setColorOnMove("white");
        Clock.stopped(true);
    }

    static void setColorOnMove(String turn) {
        if (turn.equals("white"))
            Engine.white();
        else
            if (turn.equals("black"))
                Engine.black();
    }

    static void setForced(boolean ok) {
        if (ok)
            isForced = true;
        else
            isForced = false;
    }

    static void resign() {
        
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
