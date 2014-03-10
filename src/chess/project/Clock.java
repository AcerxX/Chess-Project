package chess.project;

/**
 *
 * @author Selennae
 */
public class Clock {
     static int clock;
    
    static int getClock()
    {
        return clock;
    }
    
    static void setClock(int clock)
    {
        Clock.clock = clock;
    }
    
    static void stopped(boolean ok)
    {
        
    }
}
