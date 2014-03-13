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
 * @version 0.2.3a
 * @author Selennae
 */
public class Moves {
    
    /*General variables for handling moves*/
     static String theMove;
     static String receivedMove;
     
    /**
     * Metoda isLegal(String)
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
     * Metoda isInTurn()
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
     * Metoda computeMove(Board, Board)
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
     * Metoda computeMove(String)
     * 
     * Face mutarea efectiva a piesei in matricea engineului si trimite mutarea la Winboard.
     * 
     * @param cmd 
     */
    static void computeMove(String cmd){
        int v[][] = Board.translatePosition(cmd);
        int recI = v[0][0], recJ = v[0][1], recK = v[1][0], recL = v[1][1];
        int i = 11 - recI, j = 9 - recJ, k = 11 - recK, l = 9 - recL;
        int aux;
        if(Board.board[k][l] != 0)
            Engine.resign();
        else if(Board.board[i][j] != -1 &&
                Board.board[i][j] != 'P' &&
                Board.board[i][j] != 'T' &&
                Board.board[i][j] != 'N' &&
                Board.board[i][j] != 'C' &&
                Board.board[i][j] != 'D' &&
                Board.board[i][j] != 'R'){
            aux = Board.board[k][l];
            Board.board[k][l] = Board.board[i][j];
            Board.board[i][j] = 0;
            System.out.println("move "+ Board.translatePosition(i, j) + Board.translatePosition(k, l));
        }else
            Engine.resign();
            
    }
    
    /**
     * Metoda checkIfMove(String)
     * 
     * Verifica daca comanda primita este o mutare.
     * 
     * @param move
     * @return 
     */
    static boolean checkIfMove(String cmd){
        for(int i=2;i<10;i++)
            for(int j=1;j<9;j++)
                for(int k=2;k<10;k++)
                    for(int l=1;l<9;l++)
                        if(cmd.equals(Board.boardOfMoves[i][j]+Board.boardOfMoves[k][l])){
                            receivedMove = Board.boardOfMoves[i][j]+Board.boardOfMoves[k][l];
                            return true;
                        }
                            
        return false;
    }

    /**
     * Metoda recordMove(String)
     * 
     * Modifica matricea engineului dupa primirea unei mutari de la Winboard.
     * 
     * @param cmd 
     */
    static void recordMove(String cmd) {
        int v[][] = Board.translatePosition(cmd);
        int aux;
        aux = Board.board[v[1][0]][v[1][1]];
        Board.board[v[1][0]][v[1][1]] = Board.board[v[0][0]][v[0][1]];
        Board.board[v[0][0]][v[0][1]] = 0;
    }
}
