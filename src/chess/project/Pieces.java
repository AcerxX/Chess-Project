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
    
}
