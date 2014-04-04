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
 * @version 1.0
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
     *  - specialRege => (REGE) poate fi mutata maxim o pozitie in toate directiile<BR>
     * 
     * @param i
     * @param j
     * @return 
     */
    static String getValid(int i, int j){
        
        setType(i,j);        
        switch (Pieces.type){
            case "pion":
                if("black".equals(Engine.color) && (i==3))
                    return "p2f pd";
                else if("white".equals(Engine.color) && (i==8))
                    return "p2f pd";
                else
                    return "pf pd";
            case "dama": return "7fata 7diag 7spate 7stanga 7dreapta";
            case "rege": return "specialRege";
            case "nebun": return "7diag";
            case "tura": return "7stanga 7dreapta 7fata 7spate";
            case "cal": return "specialL";
        }
        
        return "";
    }
    
    /**
     * Returneaza o lista cu toate miscarile (in coord xboard) posibile SI LEGALE pentru o pozitie i si j.
     * 
     * @return 
     */
    static ArrayList<String> getAllMoves(int i, int j) throws IOException{
        
        /* Declarari generale */
        ArrayList<String> listOfMoves = new ArrayList<>();
        String howToMove;
        int fata = 0, spate = 0, diag = 0, pd = 0, stanga = 0, dreapta = 0, pf = 0, p2f = 0, specialL = 0, ifata, ispate, idiag, istanga, idreapta, specialRege = 0;
        
        /* Preluare miscari valide pentru piesa de la pozitia i si j */
        howToMove = getValid(i, j);
        Logger.write("LOGGER::Pieces.java::Am primit miscarile:"+howToMove+"::pentru piesa::"+(char)Board.board[i][j]);
        
        /* Setare pozitii mutare */
        if(howToMove.indexOf("specialRege") != -1){
            fata = 1;
            spate = 1;
            diag = 1;
            stanga = 1;
            dreapta = 1;
            specialRege = 1;
        }            
        if((ifata = howToMove.indexOf("fata")) != -1)
            fata = howToMove.charAt(ifata - 1);
        if((ispate = howToMove.indexOf("spate")) != -1)
            spate = howToMove.charAt(ispate - 1);
        if((idiag = howToMove.indexOf("diag")) != -1)
            diag = howToMove.charAt(idiag - 1);
        if((howToMove.indexOf("p2f")) != -1)
            p2f = 1;
        if((howToMove.indexOf("pd")) != -1)
            pd = 1;
        if((howToMove.indexOf("pf")) != -1)
            pf = 1;
        if((howToMove.indexOf("specialL")) != -1)
            specialL = 1;
        if((istanga = howToMove.indexOf("stanga")) != -1)
            stanga = howToMove.charAt(istanga - 1);
        if((idreapta = howToMove.indexOf("dreapta")) != -1)
            dreapta = howToMove.charAt(idreapta - 1);
        
        /* Generare mutari NEGRU*/
        if("black".equals(Engine.color)){
            
            /* Pentru mers in fata */
            if(fata != 0)
                for(int k = 1; k <= fata; k++)
                     if((!Board.isBlackPiece(i+k,j)) && (!Board.outOfBounds(i+k,j))){ // Verificare daca pe pozitia din fata este o piesa neagra sau se termina tabla
                           if((specialRege == 1)){  // Daca e rege
                              if((Board.board[i+2][j-1] != 'R') && (Board.board[i+2][j] != 'R') && (Board.board[i+2][j+1] != 'R'))// Verificam daca in cazul in care mutam in fata ne apropiem la mai putin de o patratica de regele alb
                                   listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+k, j)); // Daca totul e ok facem mutarea
                          }
                          else
                              listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+k, j)); // Daca nu e rege dar trece de primul if face mutarea
                            if(Board.isWhitePiece(i+k, j))// Daca locul unde a mutat avea o piesa alba inainte inseamna ca nu mai poate inainta pe pozitia aia si iesim din mersul in fata
                               break;
                            Logger.write("LOOGER::Pieces.java::Am adaugat in fata mutarea::"+Board.translatePosition(i, j) + Board.translatePosition(i+k, j));
                        }
                        else
                            break; // Daca avem o piesa alba pe drum nu avem cum sa mai continuam pe drumul ales deci break;
        
            /* TOATE DRUMURILE AU EXACT ACELEASI EXPLICATII */
            
            /* Pentru mers in spate */
            if(spate != 0)
                for(int k = 1; k <= spate; k++)
                        if((!Board.isBlackPiece(i-k,j)) && (!Board.outOfBounds(i-k,j))){
                            if((specialRege == 1)){ 
                                if((Board.board[i-2][j-1] != 'R') && (Board.board[i-2][j] != 'R') && (Board.board[i-2][j+1] != 'R'))
                                     listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-k, j));
                            }
                            else
                                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-k, j));
                            if(Board.isWhitePiece(i-k, j))
                                break;
                            Logger.write("LOOGER::Pieces.java::Am adaugat in spate mutarea::"+(Board.translatePosition(i, j) + Board.translatePosition(i-k, j)));
                        }
                        else
                            break;
            
            /* Pentru mers in stanga */
            if(stanga != 0)
                for(int k = 1; k <= stanga; k++)
                        if((!Board.isBlackPiece(i,j+k)) && (!Board.outOfBounds(i,j+k))){
                            if((specialRege == 1)){ 
                            if((Board.board[i-1][j+2] != 'R') && (Board.board[i][j+2] != 'R') && (Board.board[i+1][j+2] != 'R'))
                                     listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i, j+k));
                            }
                            else
                                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i, j+k));
                            if(Board.isWhitePiece(i, j+k))
                                break;
                            Logger.write("LOOGER::Pieces.java::Am adaugat in stanga mutarea::"+Board.translatePosition(i, j) + Board.translatePosition(i, j+k));
                        }
                        else
                            break;
        
            /* Pentru mers in dreapta */
            if(dreapta != 0)
                for(int k = 1; k <= dreapta; k++)
                        if((!Board.isBlackPiece(i,j-k)) && (!Board.outOfBounds(i,j-k))){
                            if((specialRege == 1)){ 
                                if((Board.board[i-1][j-2] != 'R') && (Board.board[i][j-2] != 'R') && (Board.board[i+1][j-2] != 'R'))
                                     listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i, j-k));
                            }
                            else
                                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i, j-k));
                            if(Board.isWhitePiece(i, j-k))
                                break;
                            Logger.write("LOOGER::Pieces.java::Am adaugat in dreapta mutarea::"+Board.translatePosition(i, j) + Board.translatePosition(i, j-k));
                        }
                        else
                            break;
            
            /* Pentru mers pe diagonala */
            if(diag != 0){
                for(int k = 1; k <= diag; k++)
                        if((!Board.isBlackPiece(i+k,j+k)) && (!Board.outOfBounds(i+k,j+k))){
                            if((specialRege == 1)){ 
                                if((Board.board[i][j+2] != 'R') && (Board.board[i+1][j+2] != 'R') && (Board.board[i+2][j+2] != 'R') && (Board.board[i+2][j+1] != 'R') && (Board.board[i+2][j] != 'R'))
                                     listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+k, j+k));
                            }
                            else
                                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+k, j+k));
                            if(Board.isWhitePiece(i+k, j+k))
                                break;
                            Logger.write("LOOGER::Pieces.java::Am adaugat in diag mutarea::"+Board.translatePosition(i, j) + Board.translatePosition(i+k, j+k));
                        }
                        else
                            break;
                for(int k = 1; k <= diag; k++)
                        if((!Board.isBlackPiece(i+k,j-k)) && (!Board.outOfBounds(i+k,j-k))){
                            if((specialRege == 1)){ 
                                if((Board.board[i][j-2] != 'R') && (Board.board[i+1][j-2] != 'R') && (Board.board[i+2][j-2] != 'R') && (Board.board[i+2][j-1] != 'R') && (Board.board[i+2][j] != 'R'))
                                     listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+k, j-k));
                            }
                            else
                                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+k, j-k));
                            if(Board.isWhitePiece(i+k, j-k))
                                break;
                            Logger.write("LOOGER::Pieces.java::Am adaugat in diag mutarea::"+Board.translatePosition(i, j) + Board.translatePosition(i+k, j-k));
                        }
                        else
                            break;
                for(int k = 1; k <= diag; k++)
                        if((!Board.isBlackPiece(i-k,j-k)) && (!Board.outOfBounds(i-k,j-k))){
                            if((specialRege == 1)){ 
                                if((Board.board[i][j-2] != 'R') && (Board.board[i-1][j-2] != 'R') && (Board.board[i-2][j-2] != 'R') && (Board.board[i-2][j-1] != 'R') && (Board.board[i-2][j] != 'R'))
                                     listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-k, j-k));
                            }
                            else
                                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-k, j-k));
                            if(Board.isWhitePiece(i-k, j-k))
                                break;
                            Logger.write("LOOGER::Pieces.java::Am adaugat in diag mutarea::"+Board.translatePosition(i, j) + Board.translatePosition(i-k, j-k));
                        }
                        else
                            break;
                for(int k = 1; k <= diag; k++)
                        if((!Board.isBlackPiece(i-k,j+k)) && (!Board.outOfBounds(i-k,j+k))){
                            if((specialRege == 1)){ 
                                if((Board.board[i][j+2] != 'R') && (Board.board[i-1][j+2] != 'R') && (Board.board[i-2][j+2] != 'R') && (Board.board[i-2][j+1] != 'R') && (Board.board[i-2][j] != 'R'))
                                     listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-k, j+k));
                            }
                            else
                                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-k, j+k));
                            if(Board.isWhitePiece(i-k, j+k))
                                break;
                            Logger.write("LOOGER::Pieces.java::Am adaugat in diag mutarea::"+Board.translatePosition(i, j) + Board.translatePosition(i-k, j+k));
                        }
                        else
                            break;                    
            }
        
            /* Mers in fata pentru pion */
            if(pf != 0)
                if((!Board.isBlackPiece(i+1,j)) && (!Board.isWhitePiece(i+1, j)) && (!Board.outOfBounds(i+1,j)) && (Board.board[i+1][j] != 'R')) // Daca in fata nu avem nicio piesa si nu iesim de pe tabla facem mutarea
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+1, j));
            
            if(p2f != 0){
                if((!Board.isWhitePiece(i+1,j)) && (!Board.isBlackPiece(i+1, j)) && (Board.board[i+1][j] != 'r')){
                        if((!Board.isWhitePiece(i+2,j)) && (!Board.isBlackPiece(i+2, j)) && (Board.board[i+2][j] != 'r')){
                            Random idk = new Random();
                            int ceva = idk.nextInt(2);
                            if( ceva == 1)
                                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+2, j));
                            else
                                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+1, j));
                        }
                        else 
                            listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+1, j));
                }
            }
        
            /* Mers pe diagonala pentru pion */
            if(pd != 0){
                /* Daca pe oricare diagonala in fata avem o piesa alba facem mutarea */
                if((Board.isWhitePiece(i+1, j+1)))
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+1, j+1));
                if((Board.isWhitePiece(i+1, j-1)))
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+1, j-1));
            }
        
            /* Mutare cal */
            if(specialL !=0){
                /* Astea sunt efectiv coordonatele unde poate merge calul la orice moment al jocului */
                if((!Board.isBlackPiece(i-2,j+1)) && (!Board.outOfBounds(i-2,j+1)))
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-2,j+1));
                if((!Board.isBlackPiece(i-1,j+2)) && (!Board.outOfBounds(i-1,j+2)))
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-1,j+2));
                if((!Board.isBlackPiece(i+1,j+2)) && (!Board.outOfBounds(i+1,j+2)))
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+1,j+2));
                if((!Board.isBlackPiece(i+2,j+1)) && (!Board.outOfBounds(i+2,j+1)))
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+2,j+1));
                if((!Board.isBlackPiece(i+2,j-1)) && (!Board.outOfBounds(i+2,j-1)))
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+2,j-1));
                if((!Board.isBlackPiece(i+1,j-2)) && (!Board.outOfBounds(i+1,j-2)))
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+1,j-2));
                if((!Board.isBlackPiece(i-1,j-2)) && (!Board.outOfBounds(i-1,j-2)))
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-1,j-2));
                if((!Board.isBlackPiece(i-2,j-1)) && (!Board.outOfBounds(i-2,j-1)))
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-2,j-1));
            }
        }
        else{
            /* Generare mutari ALB*/
            /* ACELEASI LUCRURI CA LA NEGRU DOAR CA PE DOS */
            if(fata != 0)
                for(int k = 1; k <= fata; k++)
                     if((!Board.isWhitePiece(i-k,j)) && (!Board.outOfBounds(i-k,j))){
                           if((specialRege == 1)){ 
                              if((Board.board[i-2][j+1] != 'r') && (Board.board[i-2][j] != 'r') && (Board.board[i-2][j-1] != 'r'))
                                   listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-k, j));
                          }
                          else
                              listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-k, j));
                            if(Board.isBlackPiece(i-k, j))
                               break;
                            Logger.write("LOOGER::Pieces.java::Am adaugat in fata mutarea::"+Board.translatePosition(i, j) + Board.translatePosition(i-k, j));
                        }
                        else
                            break;
        
            if(spate != 0)
                for(int k = 1; k <= spate; k++)
                        if((!Board.isWhitePiece(i+k,j)) && (!Board.outOfBounds(i+k,j))){
                            if((specialRege == 1)){ 
                                if((Board.board[i+2][j+1] != 'r') && (Board.board[i+2][j] != 'r') && (Board.board[i+2][j-1] != 'r'))
                                     listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+k, j));
                            }
                            else
                                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+k, j));
                            if(Board.isBlackPiece(i+k, j))
                                break;
                            Logger.write("LOOGER::Pieces.java::Am adaugat in spate mutarea::"+(Board.translatePosition(i, j) + Board.translatePosition(i+k, j)));
                        }
                        else
                            break;
            
            if(stanga != 0)
                for(int k = 1; k <= stanga; k++)
                        if((!Board.isWhitePiece(i,j-k)) && (!Board.outOfBounds(i,j-k))){
                            if((specialRege == 1)){ 
                            if((Board.board[i+1][j-2] != 'r') && (Board.board[i][j-2] != 'r') && (Board.board[i-1][j-2] != 'r'))
                                     listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i, j-k));
                            }
                            else
                                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i, j-k));
                            if(Board.isBlackPiece(i, j-k))
                                break;
                            Logger.write("LOOGER::Pieces.java::Am adaugat in stanga mutarea::"+Board.translatePosition(i, j) + Board.translatePosition(i, j-k));
                        }
                        else
                            break;
        
            if(dreapta != 0)
                for(int k = 1; k <= dreapta; k++)
                        if((!Board.isWhitePiece(i,j+k)) && (!Board.outOfBounds(i,j+k))){
                            if((specialRege == 1)){ 
                                if((Board.board[i+1][j+2] != 'r') && (Board.board[i][j+2] != 'r') && (Board.board[i-1][j+2] != 'r'))
                                     listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i, j+k));
                            }
                            else
                                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i, j+k));
                            if(Board.isBlackPiece(i, j+k))
                                break;
                            Logger.write("LOOGER::Pieces.java::Am adaugat in dreapta mutarea::"+Board.translatePosition(i, j) + Board.translatePosition(i, j+k));
                        }
                        else
                            break;
            
            if(diag != 0){
                for(int k = 1; k <= diag; k++)
                        if((!Board.isWhitePiece(i+k,j+k)) && (!Board.outOfBounds(i+k,j+k))){
                            if((specialRege == 1)){ 
                                if((Board.board[i][j+2] != 'r') && (Board.board[i+1][j+2] != 'r') && (Board.board[i+2][j+2] != 'r') && (Board.board[i+2][j+1] != 'r') && (Board.board[i+2][j] != 'r'))
                                     listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+k, j+k));
                            }
                            else
                                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+k, j+k));
                            if(Board.isBlackPiece(i+k, j+k))
                                break;
                            Logger.write("LOOGER::Pieces.java::Am adaugat in diag mutarea::"+Board.translatePosition(i, j) + Board.translatePosition(i+k, j+k));
                        }
                        else
                            break;
                for(int k = 1; k <= diag; k++)
                        if((!Board.isWhitePiece(i+k,j-k)) && (!Board.outOfBounds(i+k,j-k))){
                            if((specialRege == 1)){ 
                                if((Board.board[i][j-2] != 'r') && (Board.board[i+1][j-2] != 'r') && (Board.board[i+2][j-2] != 'r') && (Board.board[i+2][j-1] != 'r') && (Board.board[i+2][j] != 'r'))
                                     listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+k, j-k));
                            }
                            else
                                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+k, j-k));
                            if(Board.isBlackPiece(i+k, j-k))
                                break;
                            Logger.write("LOOGER::Pieces.java::Am adaugat in diag mutarea::"+Board.translatePosition(i, j) + Board.translatePosition(i+k, j-k));
                        }
                        else
                            break;
                for(int k = 1; k <= diag; k++)
                        if((!Board.isWhitePiece(i-k,j-k)) && (!Board.outOfBounds(i-k,j-k))){
                            if((specialRege == 1)){ 
                                if((Board.board[i][j-2] != 'r') && (Board.board[i-1][j-2] != 'r') && (Board.board[i-2][j-2] != 'r') && (Board.board[i-2][j-1] != 'r') && (Board.board[i-2][j] != 'r'))
                                     listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-k, j-k));
                            }
                            else
                                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-k, j-k));
                            if(Board.isBlackPiece(i-k, j-k))
                                break;
                            Logger.write("LOOGER::Pieces.java::Am adaugat in diag mutarea::"+Board.translatePosition(i, j) + Board.translatePosition(i-k, j-k));
                        }
                        else
                            break;
                for(int k = 1; k <= diag; k++)
                        if((!Board.isWhitePiece(i-k,j+k)) && (!Board.outOfBounds(i-k,j+k))){
                            if((specialRege == 1)){ 
                                if((Board.board[i][j+2] != 'r') && (Board.board[i-1][j+2] != 'r') && (Board.board[i-2][j+2] != 'r') && (Board.board[i-2][j+1] != 'r') && (Board.board[i-2][j] != 'r'))
                                     listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-k, j+k));
                            }
                            else
                                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-k, j+k));
                            if(Board.isBlackPiece(i-k, j+k))
                                break;
                            Logger.write("LOOGER::Pieces.java::Am adaugat in diag mutarea::"+Board.translatePosition(i, j) + Board.translatePosition(i-k, j+k));
                        }
                        else
                            break;                    
            }
        
            if(pf != 0)
                if((!Board.isWhitePiece(i-1,j)) && (!Board.isBlackPiece(i-1, j)) && (!Board.outOfBounds(i-1,j)) && (Board.board[i-1][j] != 'r'))
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-1, j));
            
            if(p2f != 0){
                if((!Board.isWhitePiece(i-1,j)) && (!Board.isBlackPiece(i-1, j)) && (Board.board[i-1][j] != 'r')){
                        if((!Board.isWhitePiece(i-2,j)) && (!Board.isBlackPiece(i-2, j)) && (Board.board[i-2][j] != 'r')){
                            Random idk = new Random();
                            int ceva = idk.nextInt(2);
                            if( ceva == 1)
                                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-2, j));
                            else
                                listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-1, j));
                        }
                        else 
                            listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-1, j));
                }
            }
                    
            if(pd != 0){
                if((Board.isBlackPiece(i-1, j-1)))
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-1, j-1));
                if((Board.isBlackPiece(i-1, j+1)))
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-1, j+1));
            }
        
            if(specialL !=0){
                if((!Board.isWhitePiece(i-2,j+1)) && (!Board.outOfBounds(i-2,j+1)))
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-2,j+1));
                if((!Board.isWhitePiece(i-1,j+2)) && (!Board.outOfBounds(i-1,j+2)))
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-1,j+2));
                if((!Board.isWhitePiece(i+1,j+2)) && (!Board.outOfBounds(i+1,j+2)))
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+1,j+2));
                if((!Board.isWhitePiece(i+2,j+1)) && (!Board.outOfBounds(i+2,j+1)))
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+2,j+1));
                if((!Board.isWhitePiece(i+2,j-1)) && (!Board.outOfBounds(i+2,j-1)))
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+2,j-1));
                if((!Board.isWhitePiece(i+1,j-2)) && (!Board.outOfBounds(i+1,j-2)))
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i+1,j-2));
                if((!Board.isWhitePiece(i-1,j-2)) && (!Board.outOfBounds(i-1,j-2)))
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-1,j-2));
                if((!Board.isWhitePiece(i-2,j-1)) && (!Board.outOfBounds(i-2,j-1)))
                    listOfMoves.add(Board.translatePosition(i, j) + Board.translatePosition(i-2,j-1));
            } 
        }
        
        /* Returnam lista cu mutari posibile */
        return listOfMoves;        
    }
    
}
