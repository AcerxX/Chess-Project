/*
 * Copywright (C) [2014] [Alexandru MIHAI] [Andreea ILIES] [Teodora TANASE] [Anca ROSCA]
 * 
 * This file is part of Thunder Chickens Chess Project.
 * 
 * Title: The Chess Project of Team <Thunder Chickens> @ CS, PUB, RO. 
 */

package chess.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @version 0.3.2a Etapa 1 FINAL
 * @author Alexandru MIHAI
 */
public class Main {
    
    /**Variabile generale*/
    public static final String engineName = "Thunder Chickens Chess Engine v.01";
    public static BufferedReader reader;
    public static String cmd;
    public static Board board;
    private static final int allowDebug = 1;
    private static boolean fileCreated = false;
    
    /**Conditii end game*/
    public static int iCheckmate;
    public static int i50MovesRule;
    public static int iTimeOut;
    
    
    
    public static void main(String args[]) throws IOException{
        Board.initAll();
        reader = new BufferedReader(new InputStreamReader(System.in));
        printCopywright();
        getCmd();        
    }
    
    /**
     * Afiseaza mesajul de copywright la rularea programului (nedetectabil in Winboard)
     */
    public static void printCopywright(){
        System.out.println("***THUNDER CHICKENS CHESS ENGINE***");
        System.out.println("**********Version 0.2.3a***********");
    }
    
    /**
     * Citeste comenzile trimise de Winboard si le redirectioneaza catre metoda corecta pentru a fi procesate.
     * Scrie toate comenzile primite in fisierul debug_engine.txt, prin modificarea variabilei allowDebug.
     * 
     * @throws IOException 
     */
    public static void getCmd() throws IOException{
        
            BufferedWriter debug = null;
            int m = 0;
            
        while(true){
            cmd = reader.readLine();
            
            
            //TODO Optimizations
            if(allowDebug == 1){
                if(!fileCreated){
                    debug = new BufferedWriter(new FileWriter("debug_engine.txt"));
                    fileCreated = true;
                }
                debug.write(cmd);
                debug.newLine();
                debug.flush();
                debug.write("-------------");
                debug.newLine();
                debug.flush();
            }
            
            if(cmd.startsWith("quit")){
                //debug.close();
                System.exit(0);
            }            
            else if(cmd.equals("new"))
                Board.newGame();
            else if(cmd.indexOf("xboard") != -1)
                System.out.println("feature san=0 time=1 draw=1 myname=\""+engineName+"\" colors=1 done=1");
            else if(cmd.indexOf("white") != -1)
                Engine.setEngineColor("white");
            else if(cmd.indexOf("black") != -1)
                Engine.setEngineColor("black");
            else if(cmd.indexOf("force") != -1)
                Engine.setForced(true);
            else if(cmd.indexOf("go") != -1)
                Engine.setForced(false);            
            else if(Moves.checkIfMove(cmd)){
                Moves.recordMove(cmd);
                Moves.computeMove(cmd);                     
            }
            else if(cmd.indexOf("time") != -1){
                //TODO Clock
            }
        }
    }
}