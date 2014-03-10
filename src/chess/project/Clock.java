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
 * @author Selennae
 */
public class Clock {
    
    /*Variables nned to handle the clock*/
     static int clock;
     static int whiteClock;
     static int blackClock;
    
     /**
      * Method getClock()
      * 
      * Returns clock variable.
      * 
      * @return 
      */
    static int getClock()
    {
        return clock;
    }
    
    /**
     * Method setClock(int)
     * 
     * Sets clock value.
     * 
     * @param clock 
     */
    static void setClock(int clock)
    {
        Clock.clock = clock;
    }
    
    /**
     * Method stopped(boolean)
     * 
     * @param ok 
     */
    static void stopped(boolean ok)
    {
        
    }
}
