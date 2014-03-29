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
 * @author Selennae
 */
public class Clock {
    
    /*Variables needed to handle the clock*/
     static int clock;
     static int whiteClock;
     static int blackClock;
    
     /**
      * Returneaza valoarea ceasului.
      * 
      * @return 
      */
    static int getClock()
    {
        return clock;
    }
    
    /**
     * Seteaza ceasul.
     * 
     * @param clock 
     */
    static void setClock(int clock)
    {
        Clock.clock = clock;
    }
    
    /**
     * @param ok 
     */
    static void stopped(boolean ok)
    {
        
    }
}
