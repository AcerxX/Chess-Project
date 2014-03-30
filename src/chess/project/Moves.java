/*
 * Copywright (C) [2014] [Alexandru MIHAI] [Andreea ILIES] [Teodora TANASE] [Anca ROSCA]
 * 
 * This file is part of Thunder Chickens Chess Project.
 * 
 * Title: The Chess Project of Team <Thunder Chickens> @ CS, PUB, RO. 
 */

package chess.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @version 0.4.4a
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
     * [RANDOM] Face mutarea efectiva a piesei in matricea engineului si trimite mutarea la Winboard.
     */
    static int computeMove() throws IOException{
        int[] randomPiece = Engine.getRandomPiece();
        ArrayList<String> moves;
        
        if(Pieces.getAllMoves(randomPiece[0], randomPiece[1]).isEmpty())
            return -1;
        moves = Pieces.getAllMoves(randomPiece[0], randomPiece[1]);
        
        Random generator = new Random(System.currentTimeMillis());        
        int i = generator.nextInt(moves.size());        
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
                        if(cmd.indexOf(Board.boardOfMoves[i][j]+Board.boardOfMoves[k][l]) != -1){
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
        int v[][] = Board.translatePosition(cmd.charAt(0)+""+cmd.charAt(1)+""+cmd.charAt(2)+""+cmd.charAt(3));
        Board.board[v[1][0]][v[1][1]] = Board.board[v[0][0]][v[0][1]];
        Board.board[v[0][0]][v[0][1]] = 0;
        if(cmd.length() == 5){
            if(Board.isBlackPiece(v[1][0], v[1][1]))
                Board.board[v[1][0]][v[1][1]] = 'q';
            if(Board.isWhitePiece(v[1][0], v[1][1]))
                Board.board[v[1][0]][v[1][1]] = 'Q';
        }
        for(int i=0;i<12;i++){
            for(int j=0; j<12;j++)
                System.out.print((char)Board.board[i][j]+ " ");
            System.out.println();
        }
    }
}
