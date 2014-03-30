/*
 * Copywright (C) [2014] [Alexandru MIHAI] [Andreea ILIES] [Teodora TANASE] [Anca ROSCA]
 * 
 * This file is part of Thunder Chickens Chess Project.
 * 
 * Title: The Chess Project of Team <Thunder Chickens> @ CS, PUB, RO. 
 */

package chess.project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author alexa_000
 */
public class Logger {

    static BufferedWriter debug;
    
   /* public Logger() throws IOException{
        debug = new BufferedWriter(new FileWriter("log.txt"));
    }
    
    public Logger(String path) throws IOException{
        debug = new BufferedWriter(new FileWriter(path));
    }*/
    
    static void create() throws IOException{
        debug = new BufferedWriter(new FileWriter("log.txt"));
    }

    static void write(String cmd) throws IOException {
        debug.write(cmd);
        debug.newLine();
        debug.write("-------------");
        debug.newLine();
        debug.flush();
    }

    static void close() throws IOException {
        debug.close();
    }
    
}