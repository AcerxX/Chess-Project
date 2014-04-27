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
 * @version 2.2
 * @author alexa_000
 */
public class Database {
    
    static String lastMove;
    static ArrayList<String> whiteOpening = new ArrayList<>();
    static ArrayList<String> blackOpening = new ArrayList<>();
    
    static void populateDatabase(){
        lastMove = new String();
        whiteOpening.clear();
        blackOpening.clear();
        
        whiteOpening.add("d2d4");
        whiteOpening.add("e2e3");
        whiteOpening.add("f1d3");
        whiteOpening.add("f2f4");
        whiteOpening.add("g1f3");
        
        blackOpening.add("d7d5");
        blackOpening.add("e7e6");
        blackOpening.add("g8f6");
        blackOpening.add("f8d6");
        blackOpening.add("b7b6");
    }
    
    static String nextBlackMove(){
        if(!blackOpening.isEmpty())
            return blackOpening.remove(0);
        else
            return null;
    }
    
    static String nextWhiteMove(){
        if(!whiteOpening.isEmpty())
            return whiteOpening.remove(0);
        else
            return null;
    }    
}
