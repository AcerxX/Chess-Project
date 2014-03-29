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
 * @version 0.3.1a Etapa 1 FINAL
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
    /*static void computeMove(String cmd){
        
        if(Engine.color == "black"){
            
            int v[][] = Board.translatePosition(cmd);
            int recI = v[0][0], recJ = v[0][1], recK = v[1][0], recL = v[1][1];
            int i = 11 - recI, j = 9 - recJ, k = 11 - recK, l = 9 - recL;
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
    }*/
    
    /**
     * [RANDOM] Face mutarea efectiva a piesei in matricea engineului si trimite mutarea la Winboard.
     */
    static void computeMove(){
        int[] randomPiece = getRandomPiece();
        ArrayList<String> moves = getAllMoves(randomPiece[0], randomPiece[1]);
        Random generator = new Random();
        int i = generator.nextInt(moves.size()+1);
        recordMove(moves.get(i));
        System.out.println("move "+moves.get(i));
    }
    
    /**
     * Returneaza coordonatele i si j ale unei piese NEAGRE random de pe tabla. 
     * @return 
     */
    static int[] getRandomPiece(){
        
        Random generator = new Random();
        int[][] pairs = new int[16][2];
        int k=0;
        
        for(int i = 0; i < 12; i++)
            for(int j = 0; j < 10; j++)
                if(Board.isBlackPiece(i,j)){
                    pairs[k][0] = i;
                    pairs[k][1] = j;
                    k++;
                }
        
        k = generator.nextInt(17);
        int[] ret = new int[2];
        ret[0] = pairs[k][0];
        ret[1] = pairs[k][1];
        
        return ret;
        
    }
    
    /**
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
    
    /**
     * Returneaza o lista cu toate miscarile (in coord xboard) posibile SI LEGALE pentru o pozitie i si j.
     * 
     * @return 
     */
    static ArrayList<String> getAllMoves(int i, int j){
        
        /* Declarari generale */
        ArrayList<String> listOfMoves = new ArrayList<>();
        String howToMove;
        howToMove = Pieces.getValid(i, j);
        int fata = 0, spate = 0, diag = 0, pd = 0, stanga = 0, dreapta = 0, pf = 0, ifata, ispate, idiag, ipd, ipf, istanga, idreapta;
        
        /* Setare pozitii mutare */
        if((ifata = howToMove.indexOf("fata")) != -1)
            fata = howToMove.charAt(ifata - 1);
        if((ispate = howToMove.indexOf("spate")) != -1)
            spate = howToMove.charAt(ispate - 1);
        if((idiag = howToMove.indexOf("diag")) != -1)
            diag = howToMove.charAt(idiag - 1);
        if((ipd = howToMove.indexOf("pd")) != -1)
            pd = 1;
        if((ipf = howToMove.indexOf("pf")) != -1)
            pf = 1;
        if((istanga = howToMove.indexOf("stanga")) != -1)
            stanga = howToMove.charAt(istanga - 1);
        if((idreapta = howToMove.indexOf("dreapta")) != -1)
            dreapta = howToMove.charAt(idreapta - 1);
        
        /* Generare mutari NEGRU*/
        if(fata != 0)
            for(int k = 0; k < ifata; k++)
                if(Board.isBlackPiece(i+k,j))
                    break;
                else
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+k, j));
        
        if(spate != 0)
            for(int k = 0; k < ispate; k++)
                if(Board.isBlackPiece(i-k,j) || (Board.board[i-k][j] == 'R'))
                    break;
                else
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-k, j));
        
        if(stanga != 0)
            for(int k = 0; k < istanga; k++)
                if(Board.isBlackPiece(i,j-k) || (Board.board[i][j-k] == 'R'))
                    break;
                else
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i, j-k));
        
        if(dreapta != 0)
            for(int k = 0; k < idreapta; k++)
                if(Board.isBlackPiece(i,j+k) || (Board.board[i][j+k] == 'R'))
                    break;
                else
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i, j+k));
        
        if(diag != 0){
            for(int k = 0; k < idiag; k++)
                if(Board.isBlackPiece(i+k,j+k) || (Board.board[i+k][j+k] == 'R'))
                    break;
                else
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+k, j+k));
            for(int k = 0; k < idiag; k++)
                if(Board.isBlackPiece(i+k,j-k) || (Board.board[i+k][j-k] == 'R'))
                    break;
                else
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+k, j-k));
            for(int k = 0; k < idiag; k++)
                if(Board.isBlackPiece(i-k,j-k) || (Board.board[i-k][j-k] == 'R'))
                    break;
                else
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-k, j-k));
            for(int k = 0; k < idiag; k++)
                if(Board.isBlackPiece(i-k,j+k) || (Board.board[i-k][j+k] == 'R'))
                    break;
                else
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-k, j+k));
        }
        
        if(pf != 0)
            if(!Board.isBlackPiece(i+1,j) && !Board.isWhitePiece(i+1, j))
                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+1, j));
        
        if(pd != 0){
            if(Board.isWhitePiece(i+1, j+1) || (Board.board[i+1][j+1] != 'R'))
                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+1, j+1));
            if(Board.isWhitePiece(i+1, j-1) || (Board.board[i+1][j-1] != 'R'))
                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+1, j-1));
        }
        
        return listOfMoves;        
    }
}
