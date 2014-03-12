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
 * @version 0.2.3a
 * @author Alexandru MIHAI
 */
public class Main {
    
    /**Variabile generale*/
    public static final String engineName = "Thunder Chickens Chess Engine v.01";
    public static BufferedReader reader;
    public static String cmd;
    public static Board board;
    private static final int allowDebug = 0;
    private static boolean fileCreated = false;
    
    /**Conditii end game*/
    public static int iCheckmate;
    public static int i50MovesRule;
    public static int iTimeOut;
    
    public static String latestMoves;
    
    
    
    public static void main(String args[]) throws IOException{
        Board.initAll();
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
     * Metoda printCopywright()
     * 
     * Afiseaza mesajul de copywright la rularea programului (nedetectabil in Winboard)
     */
    public static void printCopywright(){
        System.out.println("***THUNDER CHICKENS CHESS ENGINE***");
        System.out.println("**********Version 0.2.3a***********");
    }
    
    /**
     * Metoda getCmd()
     * 
     * Citeste comenzile trimise de Winboard si le redirectioneaza catre metoda corecta pentru a fi procesate.
     * Scrie toate comenzile primite in fisierul debug_engine.txt, prin modificarea variabilei allowDebug.
     * 
     * @throws IOException 
     */
    public static void getCmd() throws IOException{
        
            BufferedWriter debug = null;
            
        while(true){
            cmd = reader.readLine();
            
            //TODO Optimizations
            if(allowDebug == 1){
                if(!fileCreated){
                    debug = new BufferedWriter(new FileWriter("debug_engine.txt"));
                    fileCreated = true;
                }
                debug.write(cmd);
                debug.flush();
                debug.newLine();
                debug.flush();
                debug.write("\n"+"-------------"+"\n");
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
                System.out.println("feature san=0 time=0 draw=1 myname=\""+engineName+"\" colors=1 done=1");
            else if(cmd.indexOf("white") != -1)
                Engine.setColorOnMove("white");
            else if(cmd.indexOf("black") != -1)
                Engine.setColorOnMove("black");
            else if(cmd.indexOf("force") != -1)
                Engine.setForced(true);
            else if(cmd.indexOf("go") != -1)
                Engine.setForced(false);            
            else if(Moves.checkIfMove(cmd)){
                System.out.println("move e7e5");
                Engine.resign();
            } else {
            }
                    
        }
    }
}