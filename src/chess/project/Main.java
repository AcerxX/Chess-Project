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
 * @version 0.1a
 * @author Alexandru MIHAI
 */
public class Main {
    
    /**General variables*/
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
     * Prints the Copywright at the begining of the game.
     */
    public static void printCopywright(){
        System.out.println("***THUNDER CHICKENS CHESS ENGINE***");
        System.out.println("***********Version 0.1a************");
    }
    
    public static void getCmd() throws IOException{
        while(true){
            cmd = reader.readLine();
            if(cmd.startsWith("quit"))
                //Should be replaced with resign or a nice announce!!!
                System.exit(0);
            else if(cmd.indexOf("setvalue") != -1)
                SetClopParams(cmd);
            else if(cmd.equals("RandomTest")){
                Board.newGame();
                Engine.resetHash();
            }
        }
    }
    
    /**
     * Method SetClopParams
     * 
     * Sets parameters for ALL pieces on board.
     * @param command 
     */
    public static void SetClopParams(String command){
           //TODO Code for params set
    }
}

