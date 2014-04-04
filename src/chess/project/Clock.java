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
 * @version 1.0
 * @author Selennae
 */
public class Clock {
    
    /*Variables needed to handle the clock*/
     static int whiteClock = 0;
     static int blackClock = 0;
    
     /**
      * Returneaza valoarea ceasului.
      * 
      * @return 
      */
    static int getClock(boolean opponent)
    {
        if(opponent)
            if("black".equals(Engine.color))
                return whiteClock;
            else
                return blackClock;
        else
            if("black".equals(Engine.color))
                return blackClock;
            else
                return whiteClock;
    }
    
    /**
     * Seteaza ceasul.
     * 
     * @param clock
     * @param oTime
     */
    static void setClock(int clock, boolean oTime)
    {
        if(oTime)
            if("black".equals(Engine.color))
                whiteClock = clock;
            else
                blackClock = clock;
        else
            if("black".equals(Engine.color))
                blackClock = clock;
            else
                whiteClock = clock;
    }
}
