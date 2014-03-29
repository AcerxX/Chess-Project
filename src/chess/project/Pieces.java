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
     * LEGAL DECLARATIONS:
     * - pion
     * - dama
     * - rege
     * - nebun
     * - tura
     * - cal
     * 
     * @param pc 
     */    
    public static void setType(String pc){
        Pieces.type = pc;
    }
    
    /**
     * Metoda getValid()
     * 
     * Returneaza ca string posibilitatile de mutare a fiecarei piese sub forma de string. 
     * 
     * @return 
     */
    public static String getValid(){
        
        //setType()        
        switch (Pieces.type){
            case "pion": return "1fata, 1fdiag!";
            case "dama": return "7fata, 7diag, 7spate, 7stanga, 7dreapta";
            case "rege": return "1fata, 1diag, 1spate, 1stanga, 1dreapta";
            case "nebun": return "7diag";
            case "tura": return "7stanga, 7dreapta, 7fata, 7spate";
            case "cal": return "specialL";
            //default: Logger.write("Wrong type: "+this.type); 
        }
        
        return "";
    }
    
}
