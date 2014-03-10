package chess.project;

/**
 *
 * @author Selennae
 */
public class Clock {
    
    /*Variables nned to handle the clock*/
     static int clock;
    
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
