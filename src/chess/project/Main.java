/*
 * Copywright (C) [2014] [Alexandru MIHAI] [Andreea ILIES] [Teodora TANASE] [Anca ROSCA]
 * 
 * This file is part of Thunder Chickens Chess Project.
 * 
 * Title: The Chess Project of Team <Thunder Chickens> @ CS, PUB, RO. 
 */

package chess.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @version 0.5b
 * @author Alexandru MIHAI
 */
public class Main {
    
    /**Variabile generale*/
    public static final String engineName = "Thunder Chickens Chess Engine v0.5b";
    public static BufferedReader reader;
    public static String cmd;
    public static Board board;
    
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
        System.out.println("***********Version 0.5b************");
    }
    
    /**
     * Citeste comenzile trimise de Winboard si le redirectioneaza catre metoda corecta pentru a fi procesate.<BR>
     * Scrie toate comenzile primite in fisierul debug_engine.txt, prin modificarea variabilei allowDebug.<BR>
     * 
     * @throws IOException 
     */
    public static void getCmd() throws IOException{ 
        
            Logger.create();
            
        while(true){
            cmd = reader.readLine();       
            Logger.write("WINBOARD::"+cmd);                
            
            /*Quit Game*/
            if(cmd.startsWith("quit")){
                Logger.close();
                System.exit(0);
            }
            /*New Game*/
            else if(cmd.equals("new"))
                Board.newGame();
            /*XBoard*/
            else if(cmd.indexOf("xboard") != -1)
                System.out.println("feature san=0 time=1 draw=1 myname=\""+engineName+"\" colors=1 done=1");
            /*White*/
            else if(cmd.indexOf("white") != -1)
                Engine.setEngineColor("white");
            /*Black*/
            else if(cmd.indexOf("black") != -1)
                Engine.setEngineColor("black");
            /*Force*/
            else if(cmd.indexOf("force") != -1)
                Engine.setForced(true);
            /*Go*/
            else if(cmd.indexOf("go") != -1)
                Engine.setForced(false); 
            /*Move Received*/
            else if(Moves.checkIfMove(cmd)){
                Moves.recordMove(cmd);
                while(true){
                    if(Moves.computeMove() == 1)
                        break;
                }
            }
            /*Time*/
            else if(cmd.indexOf("time") != -1){
                //TODO Clock
            }
            /* Private test command */
            else if(cmd.indexOf("ptest") != -1)
                Board.testInit();
        }
    }
}