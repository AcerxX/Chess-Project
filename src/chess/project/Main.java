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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @version 0.2.2a
 * @author Alexandru MIHAI
 */
public class Main {
    
    /**General variables*/
    public static final String engineName = "Thunder Chickens Chess Engine v.01";
    public static BufferedReader reader;
    public static String cmd;
    public static Board Board;
    
    /**End Game conditions*/
    public static int iCheckmate;
    public static int i50MovesRule;
    public static int iTimeOut;
    
    public static String latestMoves;
    
    public static void main(String args[]) throws IOException{
        try{
            reader = new BufferedReader(new InputStreamReader(System.in));
            latestMoves = "none";
            printCopywright();
            getCmd();
        } catch(IOException ex){
            File f = new File("error");
            System.out.println(f.getCanonicalPath());
            f.createNewFile();
            FileWriter fileWriter;
            BufferedWriter buffWriter;
            try{
                fileWriter = new FileWriter(f);
                buffWriter = new BufferedWriter(fileWriter);
                buffWriter.write(latestMoves + "\n");
                buffWriter.write(ex.toString()+"\n");
                StackTraceElement[] arrTrace = ex.getStackTrace();
                int stackSize = arrTrace.length;
                for(int i=0; i<stackSize; i++)
                {
                        buffWriter.write(arrTrace[i].toString()+"\n");
                }
                buffWriter.close();
            } catch(IOException ex2) {ex2.printStackTrace(System.out);}

            System.out.println(ex);
        }            
    }
    
    /**
     * Method printCopywright()
     * 
     * Prints the copyright at the beginning of the game.
     */
    public static void printCopywright(){
        System.out.println("***THUNDER CHICKENS CHESS ENGINE***");
        System.out.println("***********Version 0.1a************");
    }
    
    /**
     * Method getCmd()
     * 
     * Reads commands sent by Winboard and redirects them to the correct method to be handled.
     * Writes all received command in debug_engine.txt.
     * 
     * @throws IOException 
     */
    public static void getCmd() throws IOException{
        BufferedWriter debug = new BufferedWriter(new FileWriter("debug_engine.txt"));
        while(true){
            cmd = reader.readLine();
            
            //TODO Optimizations
            debug.write(cmd);
            debug.flush();
            debug.newLine();
            debug.flush();
            debug.write("\n"+"-------------"+"\n");
            debug.newLine();
            debug.flush();
            
            if(cmd.startsWith("quit")){
                debug.close();
                System.exit(0);
                 //break;
            }            
            else if(cmd.equals("new"))
                Board.newGame();
            else if(cmd.indexOf("xboard") != -1)
                System.out.println("feature san=0 time=0 draw=1 myname=\""+engineName+"\" colors=1 done=1");
            else if(cmd.indexOf("white") != -1)
                Engine.setColorOnMove("white");
            else if(cmd.indexOf("black") != -1)
                Engine.setColorOnMove("black");
            else if(cmd.indexOf("force") != -1)
                Engine.setForced(true);
            else if(cmd.indexOf("go") != -1)
                Engine.setForced(false);
            else if(cmd.indexOf("resign") != -1)
                Engine.resign();            
            else if(cmd.equals("e2e4")/*WORKS LOL*/){
                System.out.println("move e7e5");
                Engine.resign();
            }
                    
        }
    }
}