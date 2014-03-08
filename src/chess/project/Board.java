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
    
    void newGame() {
        
        board = new int[15][15];
        //TODO setarea pieselor pe tabla
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }
    
}
