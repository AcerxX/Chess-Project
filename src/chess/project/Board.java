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
public class Board {

    /**
     * newGame()
     * 
     * Reset the board to the standard chess starting position.
     * Set White on move.
     * Leave force mode and set the engine to play Black.
     * Associate the engine's clock with Black and the opponent's clock with White.
     * Reset clocks and time controls to the start of a new game.
     * Use wall clock for time measurement.
     * Stop clocks.
     */
    
    /*Variables needed for board implementation*/
    public static int[][] board;
    
    @SuppressWarnings("empty-statement")
    static void newGame() {
        int i,j;
       
        //TODO setarea pieselor pe tabla    
        int[][] newBoard = { {-1, -1, -1, -1, -1, -1, -1, -1, -1,-1},
                          {-1, -1, -1, -1, -1, -1, -1, -1, -1,-1},
                          {-1,'t','c','n','d','r','n','c','t',-1},
                          {-1,'p','p','p','p','p','p','p','p',-1},
                          {-1, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,-1},
                          {-1, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,-1},
                          {-1, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,-1},
                          {-1, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,-1},
                          {-1,'P','P','P','P','P','P','P','P',-1},
                          {-1,'T','C','N','D','R','N','C','T',-1},
                          {-1, -1, -1, -1, -1, -1, -1, -1, -1,-1},
                          {-1, -1, -1, -1, -1, -1, -1, -1, -1,-1}
                        };
        board = new int[12][10];
        board = newBoard;
        
    }

}
