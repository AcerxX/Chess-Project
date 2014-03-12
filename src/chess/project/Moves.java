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
public class Moves {
    
    /*General variables for handling moves*/
     static String theMove;
     static String receivedMove;
     
    /**
     * Method isLegal(String)
     * 
     * Verifica daca mutarea este legala
     * 
     * @param theMove
     * @return 
     */
    static boolean isLegal(String theMove)
    {
        return true;
    }
    
    /**
     * Method isInTurn()
     * 
     * Verifica daca mutarea se face in tura corecta
     * 
     * @return 
     */
    static boolean isInTurn()
    {
        return true;
    }
    
    /**
     * Method computeMove(Board, Board)
     * 
     * Cauta mutari potrivite
     * 
     * @param x
     * @param y
     * @return 
     */
    static String computeMove(Board x, Board y)
    {
        return theMove;
    }
    
    /**
     * Method checkIfMove(String)
     * 
     * Checks if the read command is a move
     * 
     * @param move
     * @return 
     */
    static boolean checkIfMove(String move){
        for(int i=2;i<10;i++)
            for(int j=1;j<9;j++)
                for(int k=2;k<10;k++)
                    for(int l=1;l<9;l++)
                        if(move.equals(Board.boardOfMoves[i][j]+Board.boardOfMoves[k][l])){
                            receivedMove = Board.boardOfMoves[i][j]+Board.boardOfMoves[k][l];
                            return true;
                        }
                            
        return false;
    }
}
