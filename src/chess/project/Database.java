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
 *
 * @author alexa_000
 */
public class Database {
    
    private String lastMove;
    private ArrayList<String> whiteOpening = new ArrayList<>();
    private ArrayList<String> blackOpening = new ArrayList<>();
    
    private Database(){
        lastMove = new String();
        whiteOpening.add("d2d4");
        whiteOpening.add("e2e3");
        whiteOpening.add("f1d3");
        whiteOpening.add("f2f4");
        whiteOpening.add("");
    }
    
    private String nextBlackMove(){
        return null;
        
    }
    
    private String nextWhiteMove(){
        return null;
        
    }
    
}
