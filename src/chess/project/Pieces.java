/*
 * Copywright (C) [2014] [Alexandru MIHAI] [Andreea ILIES] [Teodora TANASE] [Anca ROSCA]
 * 
 * This file is part of Thunder Chickens Chess Project.
 * 
 * Title: The Chess Project of Team <Thunder Chickens> @ CS, PUB, RO. 
 */

package chess.project;

import java.util.ArrayList;

/**
 * @version 0.4.1a
 * @author andre_000
 */
public class Pieces {
    private static String type;
    
    /**
     * Seteaza piesa. POSIBIL SA DISPARA IN VIITORUL APROPIAT!<BR>
     * <BR>
     * LEGAL DECLARATIONS:<BR>
     * - pion<BR>
     * - dama<BR>
     * - rege<BR>
     * - nebun<BR>
     * - tura<BR>
     * - cal<BR>
     * @param i
     * @param j 
     */    
    static void setType(int i, int j){
        switch(Board.board[i][j]){
            case 't': type = "tura";
                break;
            case 'c': type = "cal";
                break;
            case 'n': type = "nebun";
                break;
            case 'd': type = "dama";
                break;
            case 'r': type = "rege";
                break;
            case 'p': type = "pion";
                break;
            case 'T': type = "tura";
                break;
            case 'C': type = "cal";
                break;
            case 'N': type = "nebun";
                break;
            case 'D': type = "dama";
                break;
            case 'R': type = "rege";
                break;
            case 'P': type = "pion";
                break;
        }
    }
    
    /**
     * Returneaza ca string posibilitatile de mutare a piesei de pe pozitia i si j sub forma de string. <BR>
     * 
     * POSIBILITATI:<BR>
     *  - pf => (PION) poate fi mutata maxim o pozitie in fata;<BR>
     *  - fd => (PION) poate fi mutata maxim o pozitie pe diagonala in fata;<BR>
     *  - 7fata => poate fi mutata maxim 7 pozitii in fata;<BR>
     *  - 7spate => poate fi mutata maxim 7 pozitii in spate;<BR>
     *  - 7diag => poate fi mutata maxim 7 pozitii pe diagonala oriunde;<BR>
     *  - 7stanga => poate fi mutata maxim 7 pozitii in stanga;<BR>
     *  - 7dreapta => poate fi mutata maxim 7 pozitii in dreapta;<BR>
     *  - specialL => este cal si se muta in forma de L;<BR>
     *  - 1fata, 1diag, 1spate, 1stanga, 1dreapta => acelasi lucru ca cele de mai sus, difera doar numarul de pozitii.<BR>
     * 
     * @param i
     * @param j
     * @return 
     */
    static String getValid(int i, int j){
        
        setType(i,j);        
        switch (Pieces.type){
            case "pion": return "pf, pd";
            case "dama": return "7fata, 7diag, 7spate, 7stanga, 7dreapta";
            case "rege": return "1fata, 1diag, 1spate, 1stanga, 1dreapta";
            case "nebun": return "7diag";
            case "tura": return "7stanga, 7dreapta, 7fata, 7spate";
            case "cal": return "specialL";
            //default: Logger.write("Wrong type: "+type); 
        }
        
        return "";
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
        howToMove = getValid(i, j);
        System.out.println("LOGGER::HOW TO MOVE::"+howToMove);
        int fata = 0, spate = 0, diag = 0, pd = 0, stanga = 0, dreapta = 0, pf = 0, specialL = 0, ifata, ispate, idiag, ipd, ipf, istanga, idreapta, ispecL;
        
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
        if((ispecL = howToMove.indexOf("specialL")) != -1)
            specialL = 1;
        if((istanga = howToMove.indexOf("stanga")) != -1)
            stanga = howToMove.charAt(istanga - 1);
        if((idreapta = howToMove.indexOf("dreapta")) != -1)
            dreapta = howToMove.charAt(idreapta - 1);
        
        /* Generare mutari NEGRU*/
        if(fata != 0)
            for(int k = 0; k < fata; k++)
                if(Board.isBlackPiece(i+k,j) || (Board.board[i+k][j] == 'R') || Board.outOfBounds(i+k,j))
                    break;
                else
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+k, j));
        
        if(spate != 0)
            for(int k = 0; k < spate; k++)
                if(Board.isBlackPiece(i-k,j) || (Board.board[i-k][j] == 'R') || Board.outOfBounds(i-k,j))
                    break;
                else
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-k, j));
        
        if(stanga != 0)
            for(int k = 0; k < stanga; k++)
                if(Board.isBlackPiece(i,j-k) || (Board.board[i][j-k] == 'R') || Board.outOfBounds(i,j-k))
                    break;
                else
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i, j-k));
        
        if(dreapta != 0)
            for(int k = 0; k < dreapta; k++)
                if(Board.isBlackPiece(i,j+k) || (Board.board[i][j+k] == 'R') || Board.outOfBounds(i,j+k))
                    break;
                else
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i, j+k));
        
        if(diag != 0){
            for(int k = 0; k < diag; k++)
                if(Board.isBlackPiece(i+k,j+k) || (Board.board[i+k][j+k] == 'R') || Board.outOfBounds(i+k,j+k))
                    break;
                else
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+k, j+k));
            for(int k = 0; k < diag; k++)
                if(Board.isBlackPiece(i+k,j-k) || (Board.board[i+k][j-k] == 'R') || Board.outOfBounds(i+k,j-k))
                    break;
                else
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+k, j-k));
            for(int k = 0; k < diag; k++)
                if(Board.isBlackPiece(i-k,j-k) || (Board.board[i-k][j-k] == 'R') || Board.outOfBounds(i-k,j-k))
                    break;
                else
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-k, j-k));
            for(int k = 0; k < diag; k++)
                if(Board.isBlackPiece(i-k,j+k) || (Board.board[i-k][j+k] == 'R') || Board.outOfBounds(i-k,j+k))
                    break;
                else
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-k, j+k));
        }
        
        if(pf != 0)
            if(!Board.isBlackPiece(i+1,j) && !Board.isWhitePiece(i+1, j) && !Board.outOfBounds(i+1,j) && Board.board[i+1][j+1] != 'R')
                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+1, j));
        
        if(pd != 0){
            if(Board.isWhitePiece(i+1, j+1) && (Board.board[i+1][j+1] != 'R'))
                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+1, j+1));
            if(Board.isWhitePiece(i+1, j-1) && (Board.board[i+1][j-1] != 'R'))
                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+1, j-1));
        }
        
        if(specialL !=0){
            if(!Board.isBlackPiece(i-2,j+1) && !Board.outOfBounds(i-2,j+1) && Board.board[i-2][j+1] != 'R')
                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-2,j+1));
            if(!Board.isBlackPiece(i-1,j+2) && !Board.outOfBounds(i-1,j+2) && Board.board[i-1][j+2] != 'R')
                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-1,j+2));
            if(!Board.isBlackPiece(i+1,j+2) && !Board.outOfBounds(i+1,j+2) && Board.board[i+1][j+2] != 'R')
                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+1,j+2));
            if(!Board.isBlackPiece(i+2,j+1) && !Board.outOfBounds(i+2,j+1) && Board.board[i+2][j+1] != 'R')
                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+2,j+1));
            if(!Board.isBlackPiece(i+2,j-1) && !Board.outOfBounds(i+2,j-1) && Board.board[i+2][j-1] != 'R')
                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+2,j-1));
            if(!Board.isBlackPiece(i+1,j-2) && !Board.outOfBounds(i+1,j-2) && Board.board[i+1][j-2] != 'R')
                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+1,j-2));
            if(!Board.isBlackPiece(i-1,j-2) && !Board.outOfBounds(i-1,j-2) && Board.board[i-1][j-2] != 'R')
                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-1,j-2));
            if(!Board.isBlackPiece(i-2,j-1) && !Board.outOfBounds(i-2,j-1) && Board.board[i-2][j-1] != 'R')
                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-2,j-1));
        }
       
            return listOfMoves;        
    }
    
}
