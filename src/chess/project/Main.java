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
 * @version 0.5.3b
 * @author Alexandru MIHAI
 */
public class Main {
    
    /**Variabile generale*/
    public static final String engineName = "Thunder Chickens Chess Engine v0.5 beta";
    public static BufferedReader reader;
    public static String cmd;
    public static Board board;
    public static boolean isComputer = false;
    
    /**Conditii end game*/
    public static int iCheckmate;
    public static int i50MovesRule;
    public static int iTimeOut;
    
    
    
    public static void main(String args[]) throws IOException, InterruptedException{
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
        System.out.println("***********Version 0.5.3b************");
    }
    
    /**
     * Citeste comenzile trimise de Winboard si le redirectioneaza catre metoda corecta pentru a fi procesate.<BR>
     * Scrie toate comenzile primite in fisierul debug_engine.txt, prin modificarea variabilei allowDebug.<BR>
     * 
     * @throws IOException 
     * @throws java.lang.InterruptedException 
     */
    public static void getCmd() throws IOException, InterruptedException{ 
        
            Logger.create();
            
        while(true){
            cmd = reader.readLine();       
            Logger.write("WINBOARD::"+Engine.color+"::"+cmd);                
            
            /*Quit Game*/
            if(cmd.startsWith("quit")){
                Logger.close();
                System.exit(0);
            }
            /*New Game*/
            if(cmd.equals("new")){
                Logger.write("LOGGER::"+Engine.color+"::"+"Am setat force FALSE!");
                Board.newGame();
            }
                
            /*XBoard*/
            if(cmd.indexOf("xboard") != -1)
                System.out.println("feature san=0 time=1 draw=1 myname=\""+engineName+"\" colors=1 done=1");
            
            /*White*/
            if(cmd.indexOf("white") != -1)
                Engine.setEngineColor("white");
            
            /*Black*/
            if(cmd.indexOf("black") != -1)
                Engine.setEngineColor("black");
            
            /*Force*/
            if(cmd.indexOf("force") != -1)
                Engine.setForced(true);
            
            /*Go*/
            if(cmd.indexOf("go") != -1){
                Logger.write("LOGGER::"+Engine.color+"::"+"Am primit go::");
                Engine.setForced(false);
            }
            
            /*Move Received*/
            if(Moves.checkIfMove(cmd)){
                Moves.recordMove(cmd);
                Logger.write("LOGGER::"+Engine.color+"::"+"Am scris comanda in matrice::");
                for(int i = 1; i <= 900; i++){ 
                    if((Moves.computeMove() == 1))                        
                        break;
                }
            }
            
            /*Time*/
            if(cmd.indexOf("time") != -1){
                //TODO Clock
            }
            
            /* Computer Enemy */
            if(cmd.indexOf("computer") != -1){
                isComputer = true;
                Engine.isForced = false;
                Logger.write("LOGGER::"+Engine.color+"::"+"Am setat isComputer TRUE::");
            }
            
        }
    }
}