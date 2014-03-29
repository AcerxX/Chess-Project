/*
 * Copywright (C) [2014] [Alexandru MIHAI] [Andreea ILIES] [Teodora TANASE] [Anca ROSCA]
 * 
 * This file is part of Thunder Chickens Chess Project.
 * 
 * Title: The Chess Project of Team <Thunder Chickens> @ CS, PUB, RO. 
 */

package chess.project;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @version 0.4.1a
 * @author Selennae
 */
public class Moves {
    
    /*General variables for handling moves*/
     static String theMove;
     static String receivedMove;
     static int iW = 8, jW = 5, kW = 6, lW = 5;
     
    /**
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
     * Verifica daca mutarea se face in tura corecta
     * 
     * @return 
     */
    static boolean isInTurn()
    {
        return true;
    }
    
    /**
     * Face mutarea efectiva a piesei in matricea engineului si trimite mutarea la Winboard.
     * 
     * @param cmd 
     */
    static void OLDcomputeMove(String cmd){
        
        if(Engine.color == "black"){
            
            int v[][] = Board.translatePosition(cmd);
            int recI = v[0][0], recJ = v[0][1], recK = v[1][0], recL = v[1][1];
            int i = 11 - recI, j = 11 - recJ, k = 11 - recK, l = 11 - recL;
            if(Board.board[k][l] == -1){
                Engine.resign();
            }else if(Board.board[i][j] != 'P' &&
                     Board.board[i][j] != 'T' &&
                     Board.board[i][j] != 'N' &&
                     Board.board[i][j] != 'C' &&
                     Board.board[i][j] != 'D' &&
                     Board.board[i][j] != 'R' &&
                     Board.board[recK][recL] != 'D'){
                        Board.board[k][l] = Board.board[i][j];
                        Board.board[i][j] = 0;
                        System.out.println("move "+ Board.translatePosition(i, j) + Board.translatePosition(k, l));
                    }else
                        Engine.resign();
        }else{
            
            if(Board.board[kW][lW] != -1 &&
               Board.board[iW][jW] != 'p' &&
               Board.board[iW][jW] != 't' &&
               Board.board[iW][jW] != 'n' &&
               Board.board[iW][jW] != 'c' &&
               Board.board[iW][jW] != 'd' &&
               Board.board[iW][jW] != 'r' &&
               Board.board[kW][lW] != 'p' &&
               Board.board[kW][lW] != 't' &&
               Board.board[kW][lW] != 'n' &&
               Board.board[kW][lW] != 'c' &&
               Board.board[kW][lW] != 'd' &&
               Board.board[kW][lW] != 'r'){
                Board.board[kW][lW] = Board.board[iW][jW];
                Board.board[iW][jW] = 0;                
                System.out.println("move "+ Board.translatePosition(iW, jW) + Board.translatePosition(kW, lW));
                iW=kW;
                kW--;
            }else
                Engine.resign();
        }
    }
    
    /**
     * [RANDOM] Face mutarea efectiva a piesei in matricea engineului si trimite mutarea la Winboard.
     */
    static int computeMove(){
        int[] randomPiece = Engine.getRandomPiece();
        ArrayList<String> moves;
        
        if(Pieces.getAllMoves(randomPiece[0], randomPiece[1]).isEmpty())
            return -1;
        moves = Pieces.getAllMoves(randomPiece[0], randomPiece[1]);
        
        Random generator = new Random();
        //System.out.println("LOGGER::SIZE::"+moves.size());
        int i = generator.nextInt(moves.size());
        //System.out.println("LOGGER::VALUE OF::"+i+"::"+moves.get(i));
        recordMove(moves.get(i));
        System.out.println("move "+moves.get(i));
        return 1;
    }
    
    /**
     * Verifica daca comanda primita este o mutare.
     * 
     * @param move
     * @return 
     */
    static boolean checkIfMove(String cmd){
        for(int i=2;i<10;i++)
            for(int j=2;j<10;j++)
                for(int k=2;k<10;k++)
                    for(int l=2;l<10;l++)
                        if(cmd.equals(Board.boardOfMoves[i][j]+Board.boardOfMoves[k][l])){
                            receivedMove = Board.boardOfMoves[i][j]+Board.boardOfMoves[k][l];
                            return true;
                        }
                            
        return false;
    }

    /**
     * Modifica matricea engineului dupa primirea unei mutari de la Winboard.<BR>
     * <BR>
     * Trebuie apelata tot timpul inainte de a face o mutare!<BR>
     * 
     * @param cmd 
     */
    static void recordMove(String cmd) {
        int v[][] = Board.translatePosition(cmd);
        Board.board[v[1][0]][v[1][1]] = Board.board[v[0][0]][v[0][1]];
        Board.board[v[0][0]][v[0][1]] = 0;
    }
}
