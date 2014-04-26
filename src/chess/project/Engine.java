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
 * @version 2.0
 * @author Alexandru MIHAI
 */
public class Engine {

    static String theMove;
    static boolean isForced;
    static String color = "black";
    static String actualColor = "black";

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

    /**
     * Algoritm de predictie a celei mai bune mutari.<BR>
     *
     * @param depth
     * @return
     * @throws IOException
     */
    static ArrayList NegaMax(int depth) throws IOException {

        /* Declarari */
        ArrayList<String> moves = new ArrayList<>(); // Lista de mutari ce va contine TOATE mutarile posibile intr-o tura
        ArrayList<String> auxMoves = new ArrayList<>();
        ArrayList result = new ArrayList();
        ArrayList eval = new ArrayList();

        /* Implementare */
        if (depth == 0) { // Daca adancimea e 0 se evalueaza tabla 
            eval.add(Evaluate());
            return eval;
        }

        result.add(Integer.MIN_VALUE);

        /* Preluam toate mutarile pentru toate piesele de pe tabla de culoarea noastra */
        for (int i = 2; i < 10; i++) {
            for (int j = 2; j < 10; j++) {
                if ("black".equals(color)) { // Daca suntem playerul negru
                    if (Board.isBlackPiece(i, j)) { // Daca piesa de pe tabla e neagra   
                        auxMoves.clear(); // Golim lista de mutari
                        auxMoves = Pieces.getAllMoves(i, j); // Preluam toate mutarile pentru piesa de pe pozitia i si j
                        for (int k = 0; k < auxMoves.size(); k++) { // Pentru fiecare mutare
                            Moves.recordMove(auxMoves.get(k)); // Facem mutarea
                            if (!checkIfCheck()) { // Daca nu este sah
                                moves.add(auxMoves.get(k));// Adaugam mutarea in lista finala cu mutari
                            }
                            Moves.revertMove(auxMoves.get(k)); // Dam undo la mutare pentru a putea verifica si urmatoarele piese pe aceiasi tabla
                        }
                    }
                } else {
                    if (Board.isWhitePiece(i, j)) { // Daca piesa de pe tabla e alba   
                        auxMoves.clear(); // Golim lista de mutari
                        auxMoves = Pieces.getAllMoves(i, j); // Preluam toate mutarile pentru piesa de pe pozitia i si j
                        for (int k = 0; k < auxMoves.size(); k++) { // Pentru fiecare mutare
                            Moves.recordMove(auxMoves.get(k)); // Facem mutarea
                            if (!checkIfCheck()) { // Daca nu este sah
                                moves.add(auxMoves.get(k));// Adaugam mutarea in lista finala cu mutari
                            }
                            Moves.revertMove(auxMoves.get(k)); // Dam undo la mutare pentru a putea verifica si urmatoarele piese pe aceiasi tabla
                        }
                    }
                }
            }
        }

        for (int i = 0; i < moves.size(); i++) { // Pentru fiecare mutare din lista finala de mutari
            Moves.recordMove(moves.get(i)); // Inregistram mutarea
            swapEngineColor(); // Schimbam culoarea engineului pentru a simula continuarea partidei
            int score = -(int) NegaMax(depth - 1).get(0); // Apelam recursiv functia NegaMax pentru a ne intoarce un punctaj
            if (score > ((int) result.get(0))) { // Daca punctajul intors e mai mare decat cel maxim
                result.clear(); // Curatam rezultatul
                result.add((int) score); // Adaugam pe prima pozitie a rezultatului scorul
                result.add(moves.get(i)); // Iar pe a doua pozitie mutarea
            }
            swapEngineColor(); // Schimbam culoarea engineului la loc
            Moves.revertMove(moves.get(i)); // Dam undo la mutare pentru a putea verifica si urmatoarele scoruri pe aceiasi tabla
        }

        return result;
    }

    /**
     * Functie de evaluare a tablei.<BR>
     * Se foloseste formula score = valoareaPiesei * (numarulDePieseAlbe -
     * numarulDePieseNegre) * culoareaEngineului
     *
     * @return
     */
    private static int Evaluate() {

        /* Declarari */
        int score = 0;
        int piecesScore = 0;
        int numberOfWhitePieces = 0;
        int numberOfBlackPieces = 0;
        int EngineColor;

        if ("black".equals(actualColor)) {
            EngineColor = -1;
        } else {
            EngineColor = 1;
        }


        /* Implementare */
        /* Insumarea valorilor pieselor de pe tabla */
        for (int i = 2; i < 10; i++) {
            for (int j = 2; j < 10; j++) {
                if ("black".equals(actualColor)) {
                    if (Board.isBlackPiece(i, j)) {
                        switch (Board.board[i][j]) {
                            case 't':
                                piecesScore += 10;
                                break;
                            case 'p':
                                piecesScore += 1;
                                break;
                            case 'c':
                                piecesScore += 5;
                                break;
                            case 'n':
                                piecesScore += 15;
                                break;
                            case 'd':
                                piecesScore += 25;
                                break;
                            case 'r':
                                piecesScore += 50;
                                break;
                        }
                        numberOfBlackPieces++; // Incrementam numarul de piese negre
                    } else {
                        if (Board.isWhitePiece(i, j)) {
                            numberOfWhitePieces++; //Incrementam numarul de piese albe
                        }
                    }
                } else {
                    if (Board.isWhitePiece(i, j)) {
                        switch (Board.board[i][j]) {
                            case 'T':
                                piecesScore += 10;
                                break;
                            case 'P':
                                piecesScore += 1;
                                break;
                            case 'C':
                                piecesScore += 5;
                                break;
                            case 'N':
                                piecesScore += 15;
                                break;
                            case 'D':
                                piecesScore += 25;
                                break;
                            case 'R':
                                piecesScore += 50;
                                break;
                        }
                        numberOfWhitePieces++; //Incrementam numarul de piese albe
                    } else {
                        if (Board.isBlackPiece(i, j)) {
                            numberOfBlackPieces++; // Incrementam numarul de piese negre
                        }
                    }
                }
            }
        }

        /* Calcularea scorului */
        score = piecesScore * (numberOfWhitePieces - numberOfBlackPieces) * EngineColor;

        return score;
    }

    private static void swapEngineColor() throws IOException {
        if ("black".equals(color)) {
            setEngineColor("white");
        } else {
            setEngineColor("black");
        }
    }

}
