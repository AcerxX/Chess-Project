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
 * @version 1.0
 * @author alexa_000
 */
public class Logger {

    static BufferedWriter debug;
    static boolean allowed;

    /**
     * Creaza fisierul si seteaza permisiunea de a scrie in fisier.
     *
     * @param allowed
     * @throws IOException
     */
    static void create(boolean allowed) throws IOException {
        debug = new BufferedWriter(new FileWriter("log.txt", true));
        Logger.allowed = allowed;
    }

    /**
     * Scriere a stringului pe o linie noua.
     *
     * @param cmd
     * @throws IOException
     */
    static void write(String cmd) throws IOException {
        if (allowed) {
            debug.write(cmd);
            debug.newLine();
            debug.write("-------------");
            debug.newLine();
            debug.flush();
        }
    }

    /**
     * Scriere a stringului pe o singura linie.
     *
     * @param cmd
     * @throws IOException
     */
    static void writeNNL(String cmd) throws IOException {
        if (allowed) {
            debug.write(cmd);
        }
    }

    /**
     * Inchidere fisier.
     *
     * @throws IOException
     */
    static void close() throws IOException {
        debug.close();
    }

    /**
     * Inserare linie noua goala in fisier.
     *
     * @throws IOException
     */
    static void newLine() throws IOException {
        if (allowed) {
            debug.newLine();
            debug.flush();
        }
    }

}
