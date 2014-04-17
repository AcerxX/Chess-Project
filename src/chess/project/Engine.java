/*
 * Copywright (C) [2014] [Alexandru MIHAI] [Andreea ILIES] [Teodora TANASE] [Anca ROSCA]
 * 
 * This file is part of Thunder Chickens Chess Project.
 * 
 * Title: The Chess Project of Team <Thunder Chickens> @ CS, PUB, RO. 
 */
package chess.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @version 1.0
 * @author Alexandru MIHAI
 */
public class Engine {

    static String theMove;
    static boolean isForced;
    static String color = "black";

    /**
     * Seteaza ce culoare sa mute.
     *
     * @param turn
     */
    static void setEngineColor(String turn) throws IOException {
        switch (turn) {
            case "white":
                color = "white";
                isForced = false;
                while (true) {
                    if (Moves.computeMove() == 1) {
                        break;
                    }
                }
                break;
            case "black":
                isForced = false;
                color = "black";
                break;
        }
    }

    /**
     * Seteaza rularea sau oprirea din rulare in modul forced a engineului.
     *
     * @param ok
     */
    static void setForced(boolean ok) {
        isForced = ok;
    }

    /**
     * Resigns xD.
     */
    static void resign() {
        System.out.println("resign");
    }

    /**
     * x si y sunt coordonatele de pe tabla
     */
    static void move() {
        if (isForced) {
            if (!Moves.isLegal(theMove)) {
                System.out.println("Go home! You're drunk");
            } else if (Moves.isInTurn()) {
                System.out.println("move " + theMove);
            }
        } else {
            //theMove = Moves.computeMove( x, y); //si un while pe aici etapa viitoare
            if (Moves.isLegal(theMove)) {
                System.out.println("move " + theMove);
            }
        }
    }

    /**
     * Verifica daca este sah.
     *
     * @return
     */
    static boolean checkIfCheck() throws IOException {
        int[][] v;
        ArrayList<String> moves;

        /* Verificam pentru fiecare piesa de culoare diferita decat a engineului daca exista vreo mutare care sa cada pe regele nostru */
        for (int i = 2; i < 10; i++) {
            for (int j = 2; j < 10; j++) {
                if ("black".equals(color)) {
                    color = "white";
                    if (Board.isWhitePiece(i, j)) {
                        Logger.write("LOGGER::" + Engine.color + "::" + "Engige.java::checkIfCheck verifica sah pentru pozitia " + i + " " + j);
                        moves = Pieces.getAllMoves(i, j);

                        if (moves.isEmpty()) {
                            color = "black";
                            continue;
                        }
                        for (int k = 0; k < moves.size(); k++) {
                            v = Board.translatePosition(moves.get(k));
                            if (Board.board[v[1][0]][v[1][1]] == 'r') {
                                Logger.write("LOGGER::" + Engine.color + "::" + "Engine.java::Am gasit mutarea " + moves.get(k) + "::care da sah");
                                color = "black";
                                return true;
                            }
                        }
                    }
                    color = "black";
                } else {
                    color = "black";
                    if (Board.isBlackPiece(i, j)) {
                        Logger.write("LOGGER::" + Engine.color + "::" + "Engige.java::checkIfCheck verifica sah pentru pozitia " + i + " " + j);
                        moves = Pieces.getAllMoves(i, j);
                        if (moves.isEmpty()) {
                            color = "white";
                            continue;
                        }
                        for (int k = 0; k < moves.size(); k++) {
                            v = Board.translatePosition(moves.get(k));
                            if (Board.board[v[1][0]][v[1][1]] == 'R') {
                                Logger.write("LOGGER::" + Engine.color + "::" + "Engine.java::Am gasit mutarea " + moves.get(k) + "::care da sah");
                                color = "white";
                                return true;
                            }

                        }
                    }
                    color = "white";
                }
            }
        }
        Logger.write("LOGGER::" + Engine.color + "::" + "Nu am gasit sah!");
        return false;
    }

    /**
     * Returneaza coordonatele i si j ale unei piese random de pe tabla.
     *
     * @return
     */
    static int[] getRandomPiece() throws IOException {

        Random generator = new Random();
        int[][] pairs = new int[16][2];
        int k = 0;

        for (int i = 2; i < 10; i++) {
            for (int j = 2; j < 10; j++) {
                if ("black".equals(color)) {
                    if (Board.isBlackPiece(i, j)) {
                        pairs[k][0] = i;
                        pairs[k][1] = j;
                        k++;
                    }
                } else {
                    if (Board.isWhitePiece(i, j)) {
                        pairs[k][0] = i;
                        pairs[k][1] = j;
                        k++;
                    }
                }
            }
        }

        int l = generator.nextInt(k);
        int[] ret = new int[2];
        ret[0] = pairs[l][0];
        ret[1] = pairs[l][1];
        Logger.write("LOGGER::" + Engine.color + "::" + "Engine.java::Am ales piesa::" + (char) Board.board[ret[0]][ret[1]]);
        return ret;

    }

}
