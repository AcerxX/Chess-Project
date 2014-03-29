/*
 * Copywright (C) [2014] [Alexandru MIHAI] [Andreea ILIES] [Teodora TANASE] [Anca ROSCA]
 * 
 * This file is part of Thunder Chickens Chess Project.
 * 
 * Title: The Chess Project of Team <Thunder Chickens> @ CS, PUB, RO. 
 */

package chess.project;

/**
 * @version 0.4a
 * @author andre_000
 */
public class Pieces {
    private static String type;
    
    /**
     * Seteaza piesa. POSIBIL SA DISPARA IN VIITORUL APROPIAT!
     * 
     * LEGAL DECLARATIONS:
     * - pion
     * - dama
     * - rege
     * - nebun
     * - tura
     * - cal
     * 
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
     * Returneaza ca string posibilitatile de mutare a piesei de pe pozitia i si j sub forma de string. 
     * POSIBILITATI:
     *  - 1fata => poate fi mutata maxim o pozitie in fata;
     *  - 1fd => poate fi mutata maxim o pozitie pe diagonala in fata;
     *  - 7fata => poate fi mutata maxim 7 pozitii in fata;
     *  - 7spate => poate fi mutata maxim 7 pozitii in spate;
     *  - 7diag => poate fi mutata maxim 7 pozitii pe diagonala oriunde;
     *  - 7stanga => poate fi mutata maxim 7 pozitii in stanga;
     *  - 7dreapta => poate fi mutata maxim 7 pozitii in dreapta;
     *  - specialL => este cal si se muta in forma de L;
     *  - 1fata, 1diag, 1spate, 1stanga, 1dreapta => acelasi lucru ca cele de mai sus, difera doar numarul de pozitii.
     * 
     * @param i
     * @param j
     * @return 
     */
    static String getValid(int i, int j){
        
        setType(i,j);        
        switch (Pieces.type){
            case "pion": return "1fata, 1fd";
            case "dama": return "7fata, 7diag, 7spate, 7stanga, 7dreapta";
            case "rege": return "1fata, 1diag, 1spate, 1stanga, 1dreapta";
            case "nebun": return "7diag";
            case "tura": return "7stanga, 7dreapta, 7fata, 7spate";
            case "cal": return "specialL";
            //default: Logger.write("Wrong type: "+type); 
        }
        
        return "";
    }
    
}
