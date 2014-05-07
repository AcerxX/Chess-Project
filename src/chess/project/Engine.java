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

/**
 *
 * @version 2.3
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

    static ArrayList alfaBeta(int alpha, int beta, int depth) throws IOException { //Fail-Soft Alpha-Beta

        /* Declarari */
        ArrayList<String> moves = new ArrayList<>(); // Lista de mutari ce va contine TOATE mutarile posibile intr-o tura
        ArrayList<String> auxMoves = new ArrayList<>();
        ArrayList eval = new ArrayList();
        ArrayList result = new ArrayList();
        ArrayList bestscore = new ArrayList();
        int specialPiece;


        /* Implementare */
        if (depth == 0) { // Daca adancimea e 0 se evalueaza tabla 
            eval.clear();
            eval.add(Quiesce(alpha, beta, false));
            return eval;
        }

        result.clear();
        bestscore.clear();
        bestscore.add(Integer.MIN_VALUE);

        /* Preluam toate mutarile pentru toate piesele de pe tabla de culoarea noastra */
        for (int i = 2; i < 10; i++) {
            for (int j = 2; j < 10; j++) {
                auxMoves.clear();
                if ("black".equals(color)) { // Daca suntem playerul negru
                    if (Board.isBlackPiece(i, j)) { // Daca piesa de pe tabla e neagra 
                        auxMoves = Pieces.getAllMoves(i, j); // Preluam toate mutarile pentru piesa de pe pozitia i si j
                        for (int k = 0; k < auxMoves.size(); k++) { // Pentru fiecare mutare

                            int v[][] = Board.translatePosition(auxMoves.get(k).charAt(0) + "" + auxMoves.get(k).charAt(1) + "" + auxMoves.get(k).charAt(2) + "" + auxMoves.get(k).charAt(3));
                            specialPiece = Board.board[v[1][0]][v[1][1]];

                            Moves.recordMove(auxMoves.get(k)); // Facem mutarea
                            if (!checkIfCheck()) { // Daca nu este sah
                                moves.add(auxMoves.get(k));// Adaugam mutarea in lista finala cu mutari
                            }
                            Moves.revertMove(auxMoves.get(k), specialPiece); // Dam undo la mutare pentru a putea verifica si urmatoarele piese pe aceiasi tabla
                        }
                    }
                } else { // Daca suntem playerul alb
                    if (Board.isWhitePiece(i, j)) { // Daca piesa de pe tabla e alba
                        auxMoves = Pieces.getAllMoves(i, j); // Preluam toate mutarile pentru piesa de pe pozitia i si j
                        for (int k = 0; k < auxMoves.size(); k++) { // Pentru fiecare mutare

                            int v[][] = Board.translatePosition(auxMoves.get(k).charAt(0) + "" + auxMoves.get(k).charAt(1) + "" + auxMoves.get(k).charAt(2) + "" + auxMoves.get(k).charAt(3));
                            specialPiece = Board.board[v[1][0]][v[1][1]];

                            Moves.recordMove(auxMoves.get(k)); // Facem mutarea
                            if (!checkIfCheck()) { // Daca nu este sah
                                moves.add(auxMoves.get(k));// Adaugam mutarea in lista finala cu mutari
                            }
                            Moves.revertMove(auxMoves.get(k), specialPiece); // Dam undo la mutare pentru a putea verifica si urmatoarele piese pe aceiasi tabla
                        }
                    }
                }
            }
        }

        for (int i = 0; i < moves.size(); i++) { // Pentru fiecare mutare din lista finala de mutari

            int v[][] = Board.translatePosition(moves.get(i).charAt(0) + "" + moves.get(i).charAt(1) + "" + moves.get(i).charAt(2) + "" + moves.get(i).charAt(3));
            specialPiece = Board.board[v[1][0]][v[1][1]];

            Moves.recordMove(moves.get(i)); // Inregistram mutarea
            swapEngineColor(); // Schimbam culoarea engineului pentru a simula continuarea partidei
            int score = -((int) alfaBeta(-beta, -alpha, depth - 1).get(0)); // Apelam recursiv functia NegaMax pentru a ne intoarce un punctaj

            if (score >= beta) { // Daca punctajul intors e mai mare sau egal decat beta
                result.clear(); // Curatam rezultatul
                result.add((int) score); // Adaugam pe prima pozitie a rezultatului scorul
                result.add(moves.get(i)); // Iar pe a doua pozitie mutarea
                swapEngineColor(); // Schimbam culoarea engineului la loc
                Moves.revertMove(moves.get(i), specialPiece); // Dam undo la mutare pentru a putea verifica si urmatoarele scoruri pe aceiasi tabla
                return result; // fail-soft beta-cutoff
            }

            if (score >= (int) bestscore.get(0)) {
                if(score >= (int) bestscore.get(0)){
                    bestscore.clear(); // Curatam rezultatul
                    bestscore.add(score); // Adaugam pe prima pozitie a rezultatului scorul
                    bestscore.add(moves.get(i)); // Iar pe a doua pozitie mutarea
                }else{
                    bestscore.add(moves.get(i));
                }
                
                if (score > alpha) {
                    alpha = score;
                }
            }

            swapEngineColor();
            Moves.revertMove(moves.get(i), specialPiece);
        }

        return bestscore;
    }

    /**
     * Functie de evaluare a tablei.<BR>
     * Se foloseste formula score = valoareaPiesei * (numarulDePieseAlbe -
     * numarulDePieseNegre) * culoareaEngineului
     *
     * @return
     */
    private static int Evaluate() throws IOException {
        /* Declarari */
        int score;
        int piecesScore = 0;
        int extraScore = 0;
        boolean colorChanged = false;

        /**
         * Evaluarea trebuie facuta pe culoarea engineului. Astfel, cum metoda
         * recordMove() depinde de Engine.color, aceasta trebuie schimbata in
         * Engine.actualColor, in caz ca difera una de cealalta pentru a
         * inregistra mutarile asa cum trebuie.
         */
        if (!Engine.color.equals(Engine.actualColor)) {
            swapEngineColor();
            colorChanged = true;
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

                                ArrayList<String> t = Pieces.getAllMoves(i, j); // Preluam toate mutarile piesei
                                if (!t.isEmpty()) { // Daca sunt mutari
                                    for (String y : t) { // Pentru fiecare mutare
                                        int v[][] = Board.translatePosition(y.charAt(0) + "" + y.charAt(1) + "" + y.charAt(2) + "" + y.charAt(3));
                                        if (Board.isWhitePiece(v[1][0], v[1][1])) { // Daca mutarea pica pe vreo piesa de culoare adversa
                                            int takenPiece = Board.board[v[1][0]][v[1][1]];
                                            Moves.recordMove(y); // Inregistram mutarea pentru a verifica daca este sah
                                            if (!Engine.checkIfCheck()) { // Daca nu e sah
                                                extraScore++; // Punctam in plus                                                
                                            }
                                            Moves.revertMove(y, takenPiece); // Facem undo la mutare
                                        }
                                    }
                                }

                                break;
                            case 'p':
                                piecesScore += 1;

                                ArrayList<String> p = Pieces.getAllMoves(i, j); // Preluam toate mutarile piesei
                                if (!p.isEmpty()) { // Daca sunt mutari
                                    for (String y : p) { // Pentru fiecare mutare
                                        int v[][] = Board.translatePosition(y.charAt(0) + "" + y.charAt(1) + "" + y.charAt(2) + "" + y.charAt(3));
                                        if (Board.isWhitePiece(v[1][0], v[1][1])) { // Daca mutarea pica pe vreo piesa de culoare adversa 
                                            int takenPiece = Board.board[v[1][0]][v[1][1]];
                                            Moves.recordMove(y); // Inregistram mutarea pentru a verifica daca este sah
                                            if (!Engine.checkIfCheck()) { // Daca nu e sah
                                                extraScore++; // Punctam in plus                                                
                                            }
                                            Moves.revertMove(y, takenPiece); // Facem undo la mutare
                                        }
                                    }
                                }

                                break;
                            case 'c':
                                piecesScore += 5;

                                ArrayList<String> c = Pieces.getAllMoves(i, j); // Preluam toate mutarile piesei
                                if (!c.isEmpty()) { // Daca sunt mutari
                                    for (String y : c) { // Pentru fiecare mutare
                                        int v[][] = Board.translatePosition(y.charAt(0) + "" + y.charAt(1) + "" + y.charAt(2) + "" + y.charAt(3));
                                        if (Board.isWhitePiece(v[1][0], v[1][1])) { // Daca mutarea pica pe vreo piesa de culoare adversa 
                                            int takenPiece = Board.board[v[1][0]][v[1][1]];
                                            Moves.recordMove(y); // Inregistram mutarea pentru a verifica daca este sah
                                            if (!Engine.checkIfCheck()) { // Daca nu e sah
                                                extraScore++; // Punctam in plus                                                
                                            }
                                            Moves.revertMove(y, takenPiece); // Facem undo la mutare
                                        }
                                    }
                                }

                                break;
                            case 'n':
                                piecesScore += 15;

                                ArrayList<String> n = Pieces.getAllMoves(i, j); // Preluam toate mutarile piesei
                                if (!n.isEmpty()) { // Daca sunt mutari
                                    for (String y : n) { // Pentru fiecare mutare
                                        int v[][] = Board.translatePosition(y.charAt(0) + "" + y.charAt(1) + "" + y.charAt(2) + "" + y.charAt(3));
                                        if (Board.isWhitePiece(v[1][0], v[1][1])) { // Daca mutarea pica pe vreo piesa de culoare adversa 
                                            int takenPiece = Board.board[v[1][0]][v[1][1]];
                                            Moves.recordMove(y); // Inregistram mutarea pentru a verifica daca este sah
                                            if (!Engine.checkIfCheck()) { // Daca nu e sah
                                                extraScore++; // Punctam in plus                                                
                                            }
                                            Moves.revertMove(y, takenPiece); // Facem undo la mutare
                                        }
                                    }
                                }

                                break;
                            case 'd':
                                piecesScore += 25;

                                ArrayList<String> d = Pieces.getAllMoves(i, j); // Preluam toate mutarile piesei
                                if (!d.isEmpty()) { // Daca sunt mutari
                                    for (String y : d) { // Pentru fiecare mutare
                                        int v[][] = Board.translatePosition(y.charAt(0) + "" + y.charAt(1) + "" + y.charAt(2) + "" + y.charAt(3));
                                        if (Board.isWhitePiece(v[1][0], v[1][1])) { // Daca mutarea pica pe vreo piesa de culoare adversa 
                                            int takenPiece = Board.board[v[1][0]][v[1][1]];
                                            Moves.recordMove(y); // Inregistram mutarea pentru a verifica daca este sah
                                            if (!Engine.checkIfCheck()) { // Daca nu e sah
                                                extraScore++; // Punctam in plus                                                
                                            }
                                            Moves.revertMove(y, takenPiece); // Facem undo la mutare
                                        }
                                    }
                                }

                                break;
                            case 'r':
                                piecesScore += 50;

                                ArrayList<String> r = Pieces.getAllMoves(i, j); // Preluam toate mutarile piesei
                                if (!r.isEmpty()) { // Daca sunt mutari
                                    for (String y : r) { // Pentru fiecare mutare
                                        int v[][] = Board.translatePosition(y.charAt(0) + "" + y.charAt(1) + "" + y.charAt(2) + "" + y.charAt(3));
                                        if (Board.isWhitePiece(v[1][0], v[1][1])) { // Daca mutarea pica pe vreo piesa de culoare adversa 
                                            int takenPiece = Board.board[v[1][0]][v[1][1]];
                                            Moves.recordMove(y); // Inregistram mutarea pentru a verifica daca este sah
                                            if (!Engine.checkIfCheck()) { // Daca nu e sah
                                                extraScore++; // Punctam in plus                                                
                                            }
                                            Moves.revertMove(y, takenPiece); // Facem undo la mutare
                                        }
                                    }
                                }

                                break;
                        }
                    }
                } else {
                    if (Board.isWhitePiece(i, j)) {
                        switch (Board.board[i][j]) {
                            case 'T':
                                piecesScore += 10;

                                ArrayList<String> T = Pieces.getAllMoves(i, j); // Preluam toate mutarile piesei
                                if (!T.isEmpty()) { // Daca sunt mutari
                                    for (String y : T) { // Pentru fiecare mutare
                                        int v[][] = Board.translatePosition(y.charAt(0) + "" + y.charAt(1) + "" + y.charAt(2) + "" + y.charAt(3));
                                        if (Board.isBlackPiece(v[1][0], v[1][1])) { // Daca mutarea pica pe vreo piesa de culoare adversa 
                                            int takenPiece = Board.board[v[1][0]][v[1][1]];
                                            Moves.recordMove(y); // Inregistram mutarea pentru a verifica daca este sah
                                            if (!Engine.checkIfCheck()) { // Daca nu e sah
                                                extraScore++; // Punctam in plus                                                
                                            }
                                            Moves.revertMove(y, takenPiece); // Facem undo la mutare
                                        }
                                    }
                                }

                                break;
                            case 'P':
                                piecesScore += 1;

                                ArrayList<String> P = Pieces.getAllMoves(i, j); // Preluam toate mutarile piesei
                                if (!P.isEmpty()) { // Daca sunt mutari
                                    for (String y : P) { // Pentru fiecare mutare
                                        int v[][] = Board.translatePosition(y.charAt(0) + "" + y.charAt(1) + "" + y.charAt(2) + "" + y.charAt(3));
                                        if (Board.isBlackPiece(v[1][0], v[1][1])) { // Daca mutarea pica pe vreo piesa de culoare adversa 
                                            int takenPiece = Board.board[v[1][0]][v[1][1]];
                                            Moves.recordMove(y); // Inregistram mutarea pentru a verifica daca este sah
                                            if (!Engine.checkIfCheck()) { // Daca nu e sah
                                                extraScore++; // Punctam in plus                                                
                                            }
                                            Moves.revertMove(y, takenPiece); // Facem undo la mutare
                                        }
                                    }
                                }

                                break;
                            case 'C':
                                piecesScore += 5;

                                ArrayList<String> C = Pieces.getAllMoves(i, j); // Preluam toate mutarile piesei
                                if (!C.isEmpty()) { // Daca sunt mutari
                                    for (String y : C) { // Pentru fiecare mutare
                                        int v[][] = Board.translatePosition(y.charAt(0) + "" + y.charAt(1) + "" + y.charAt(2) + "" + y.charAt(3));
                                        if (Board.isBlackPiece(v[1][0], v[1][1])) { // Daca mutarea pica pe vreo piesa de culoare adversa 
                                            int takenPiece = Board.board[v[1][0]][v[1][1]];
                                            Moves.recordMove(y); // Inregistram mutarea pentru a verifica daca este sah
                                            if (!Engine.checkIfCheck()) { // Daca nu e sah
                                                extraScore++; // Punctam in plus                                                
                                            }
                                            Moves.revertMove(y, takenPiece); // Facem undo la mutare
                                        }
                                    }
                                }

                                break;
                            case 'N':
                                piecesScore += 15;

                                ArrayList<String> N = Pieces.getAllMoves(i, j); // Preluam toate mutarile piesei
                                if (!N.isEmpty()) { // Daca sunt mutari
                                    for (String y : N) { // Pentru fiecare mutare
                                        int v[][] = Board.translatePosition(y.charAt(0) + "" + y.charAt(1) + "" + y.charAt(2) + "" + y.charAt(3));
                                        if (Board.isBlackPiece(v[1][0], v[1][1])) { // Daca mutarea pica pe vreo piesa de culoare adversa 
                                            int takenPiece = Board.board[v[1][0]][v[1][1]];
                                            Moves.recordMove(y); // Inregistram mutarea pentru a verifica daca este sah
                                            if (!Engine.checkIfCheck()) { // Daca nu e sah
                                                extraScore++; // Punctam in plus                                                
                                            }
                                            Moves.revertMove(y, takenPiece); // Facem undo la mutare
                                        }
                                    }
                                }

                                break;
                            case 'D':
                                piecesScore += 25;

                                ArrayList<String> D = Pieces.getAllMoves(i, j); // Preluam toate mutarile piesei
                                if (!D.isEmpty()) { // Daca sunt mutari
                                    for (String y : D) { // Pentru fiecare mutare
                                        int v[][] = Board.translatePosition(y.charAt(0) + "" + y.charAt(1) + "" + y.charAt(2) + "" + y.charAt(3));
                                        if (Board.isBlackPiece(v[1][0], v[1][1])) { // Daca mutarea pica pe vreo piesa de culoare adversa 
                                            int takenPiece = Board.board[v[1][0]][v[1][1]];
                                            Moves.recordMove(y); // Inregistram mutarea pentru a verifica daca este sah
                                            if (!Engine.checkIfCheck()) { // Daca nu e sah
                                                extraScore++; // Punctam in plus                                                
                                            }
                                            Moves.revertMove(y, takenPiece); // Facem undo la mutare
                                        }
                                    }
                                }

                                break;
                            case 'R':
                                piecesScore += 50;

                                ArrayList<String> R = Pieces.getAllMoves(i, j); // Preluam toate mutarile piesei
                                if (!R.isEmpty()) { // Daca sunt mutari
                                    for (String y : R) { // Pentru fiecare mutare
                                        int v[][] = Board.translatePosition(y.charAt(0) + "" + y.charAt(1) + "" + y.charAt(2) + "" + y.charAt(3));
                                        if (Board.isBlackPiece(v[1][0], v[1][1])) { // Daca mutarea pica pe vreo piesa de culoare adversa 
                                            int takenPiece = Board.board[v[1][0]][v[1][1]];
                                            Moves.recordMove(y); // Inregistram mutarea pentru a verifica daca este sah
                                            if (!Engine.checkIfCheck()) { // Daca nu e sah
                                                extraScore++; // Punctam in plus                                                
                                            }
                                            Moves.revertMove(y, takenPiece); // Facem undo la mutare
                                        }
                                    }
                                }

                                break;
                        }
                    }
                }
            }
        }

        /**
         * Schimbam inapoi culoarea pentru a nu influenta NegaMax
         */
        if (colorChanged) {
            swapEngineColor();
        }

        /* Calcularea scorului */
        score = piecesScore + extraScore;

        return score;
    }

    private static void swapEngineColor() throws IOException {
        if ("black".equals(color)) {
            color = "white";
        } else {
            color = "black";
        }
    }

    private static int Quiesce(int alpha, int beta, boolean swapped) throws IOException {
        boolean colorChanged = false;
        int score = Integer.MIN_VALUE;
        int standPat = Evaluate();

        if (standPat >= beta) {
            return beta;
        }

        if (alpha < standPat) {
            alpha = standPat;
        }

        /**
         * Evaluarea trebuie facuta pe culoarea engineului. Astfel, cum metoda
         * recordMove() depinde de Engine.color, aceasta trebuie schimbata in
         * Engine.actualColor, in caz ca difera una de cealalta pentru a
         * inregistra mutarile asa cum trebuie.
         */
        if (!Engine.color.equals(Engine.actualColor) && (!swapped)) {
            swapEngineColor();
            colorChanged = true;
        }

        for (int i = 2; i < 10; i++) {
            for (int j = 2; j < 10; j++) {
                if ("black".equals(actualColor)) {
                    if (Board.isBlackPiece(i, j)) {
                        switch (Board.board[i][j]) {
                            case 't':
                                ArrayList<String> t = Pieces.getAllMoves(i, j); // Preluam toate mutarile piesei
                                if (!t.isEmpty()) { // Daca sunt mutari
                                    for (String y : t) { // Pentru fiecare mutare
                                        int v[][] = Board.translatePosition(y.charAt(0) + "" + y.charAt(1) + "" + y.charAt(2) + "" + y.charAt(3));
                                        if (Board.isWhitePiece(v[1][0], v[1][1])) { // Daca mutarea pica pe vreo piesa de culoare adversa
                                            int takenPiece = Board.board[v[1][0]][v[1][1]];
                                            Moves.recordMove(y); // Inregistram mutarea pentru a verifica daca este sah
                                            if (!checkIfCheck()) {
                                                swapEngineColor(); // schimbam pentru urmatoarea mutare
                                                score = -Quiesce(-beta, -alpha, true);
                                                swapEngineColor(); // dam undo la culoare pentru a nu influenta totul
                                            }
                                            Moves.revertMove(y, takenPiece); // Facem undo la mutare

                                            if (score >= beta) {
                                                return beta;
                                            }

                                            if (score > alpha) {
                                                alpha = score;
                                            }
                                        }
                                    }
                                }

                                break;
                            case 'p':
                                ArrayList<String> p = Pieces.getAllMoves(i, j); // Preluam toate mutarile piesei
                                if (!p.isEmpty()) { // Daca sunt mutari
                                    for (String y : p) { // Pentru fiecare mutare
                                        int v[][] = Board.translatePosition(y.charAt(0) + "" + y.charAt(1) + "" + y.charAt(2) + "" + y.charAt(3));
                                        if (Board.isWhitePiece(v[1][0], v[1][1])) { // Daca mutarea pica pe vreo piesa de culoare adversa 
                                            int takenPiece = Board.board[v[1][0]][v[1][1]];
                                            Moves.recordMove(y); // Inregistram mutarea pentru a verifica daca este sah
                                            if (!checkIfCheck()) {
                                                swapEngineColor(); // schimbam pentru urmatoarea mutare
                                                score = -Quiesce(-beta, -alpha, true);
                                                swapEngineColor(); // dam undo la culoare pentru a nu influenta totul
                                            }
                                            Moves.revertMove(y, takenPiece); // Facem undo la mutare

                                            if (score >= beta) {
                                                return beta;
                                            }

                                            if (score > alpha) {
                                                alpha = score;
                                            }
                                        }
                                    }
                                }

                                break;
                            case 'c':
                                ArrayList<String> c = Pieces.getAllMoves(i, j); // Preluam toate mutarile piesei
                                if (!c.isEmpty()) { // Daca sunt mutari
                                    for (String y : c) { // Pentru fiecare mutare
                                        int v[][] = Board.translatePosition(y.charAt(0) + "" + y.charAt(1) + "" + y.charAt(2) + "" + y.charAt(3));
                                        if (Board.isWhitePiece(v[1][0], v[1][1])) { // Daca mutarea pica pe vreo piesa de culoare adversa 
                                            int takenPiece = Board.board[v[1][0]][v[1][1]];
                                            Moves.recordMove(y); // Inregistram mutarea pentru a verifica daca este sah
                                            if (!checkIfCheck()) {
                                                swapEngineColor(); // schimbam pentru urmatoarea mutare
                                                score = -Quiesce(-beta, -alpha, true);
                                                swapEngineColor(); // dam undo la culoare pentru a nu influenta totul
                                            }
                                            Moves.revertMove(y, takenPiece); // Facem undo la mutare

                                            if (score >= beta) {
                                                return beta;
                                            }

                                            if (score > alpha) {
                                                alpha = score;
                                            }
                                        }
                                    }
                                }

                                break;
                            case 'n':
                                ArrayList<String> n = Pieces.getAllMoves(i, j); // Preluam toate mutarile piesei
                                if (!n.isEmpty()) { // Daca sunt mutari
                                    for (String y : n) { // Pentru fiecare mutare
                                        int v[][] = Board.translatePosition(y.charAt(0) + "" + y.charAt(1) + "" + y.charAt(2) + "" + y.charAt(3));
                                        if (Board.isWhitePiece(v[1][0], v[1][1])) { // Daca mutarea pica pe vreo piesa de culoare adversa 
                                            int takenPiece = Board.board[v[1][0]][v[1][1]];
                                            Moves.recordMove(y); // Inregistram mutarea pentru a verifica daca este sah
                                            if (!checkIfCheck()) {
                                                swapEngineColor(); // schimbam pentru urmatoarea mutare
                                                score = -Quiesce(-beta, -alpha, true);
                                                swapEngineColor(); // dam undo la culoare pentru a nu influenta totul
                                            }
                                            Moves.revertMove(y, takenPiece); // Facem undo la mutare

                                            if (score >= beta) {
                                                return beta;
                                            }

                                            if (score > alpha) {
                                                alpha = score;
                                            }
                                        }
                                    }
                                }

                                break;
                            case 'd':
                                ArrayList<String> d = Pieces.getAllMoves(i, j); // Preluam toate mutarile piesei
                                if (!d.isEmpty()) { // Daca sunt mutari
                                    for (String y : d) { // Pentru fiecare mutare
                                        int v[][] = Board.translatePosition(y.charAt(0) + "" + y.charAt(1) + "" + y.charAt(2) + "" + y.charAt(3));
                                        if (Board.isWhitePiece(v[1][0], v[1][1])) { // Daca mutarea pica pe vreo piesa de culoare adversa 
                                            int takenPiece = Board.board[v[1][0]][v[1][1]];
                                            Moves.recordMove(y); // Inregistram mutarea pentru a verifica daca este sah
                                            if (!checkIfCheck()) {
                                                swapEngineColor(); // schimbam pentru urmatoarea mutare
                                                score = -Quiesce(-beta, -alpha, true);
                                                swapEngineColor(); // dam undo la culoare pentru a nu influenta totul
                                            }
                                            Moves.revertMove(y, takenPiece); // Facem undo la mutare

                                            if (score >= beta) {
                                                return beta;
                                            }

                                            if (score > alpha) {
                                                alpha = score;
                                            }
                                        }
                                    }
                                }

                                break;
                            case 'r':
                                ArrayList<String> r = Pieces.getAllMoves(i, j); // Preluam toate mutarile piesei
                                if (!r.isEmpty()) { // Daca sunt mutari
                                    for (String y : r) { // Pentru fiecare mutare
                                        int v[][] = Board.translatePosition(y.charAt(0) + "" + y.charAt(1) + "" + y.charAt(2) + "" + y.charAt(3));
                                        if (Board.isWhitePiece(v[1][0], v[1][1])) { // Daca mutarea pica pe vreo piesa de culoare adversa 
                                            int takenPiece = Board.board[v[1][0]][v[1][1]];
                                            Moves.recordMove(y); // Inregistram mutarea pentru a verifica daca este sah
                                            if (!checkIfCheck()) {
                                                swapEngineColor(); // schimbam pentru urmatoarea mutare
                                                score = -Quiesce(-beta, -alpha, true);
                                                swapEngineColor(); // dam undo la culoare pentru a nu influenta totul
                                            }
                                            Moves.revertMove(y, takenPiece); // Facem undo la mutare

                                            if (score >= beta) {
                                                return beta;
                                            }

                                            if (score > alpha) {
                                                alpha = score;
                                            }
                                        }
                                    }
                                }

                                break;
                        }
                    }
                } else {
                    if (Board.isWhitePiece(i, j)) {
                        switch (Board.board[i][j]) {
                            case 'T':
                                ArrayList<String> T = Pieces.getAllMoves(i, j); // Preluam toate mutarile piesei
                                if (!T.isEmpty()) { // Daca sunt mutari
                                    for (String y : T) { // Pentru fiecare mutare
                                        int v[][] = Board.translatePosition(y.charAt(0) + "" + y.charAt(1) + "" + y.charAt(2) + "" + y.charAt(3));
                                        if (Board.isBlackPiece(v[1][0], v[1][1])) { // Daca mutarea pica pe vreo piesa de culoare adversa 
                                            int takenPiece = Board.board[v[1][0]][v[1][1]];
                                            Moves.recordMove(y); // Inregistram mutarea pentru a verifica daca este sah
                                            if (!checkIfCheck()) {
                                                swapEngineColor(); // schimbam pentru urmatoarea mutare
                                                score = -Quiesce(-beta, -alpha, true);
                                                swapEngineColor(); // dam undo la culoare pentru a nu influenta totul
                                            }
                                            Moves.revertMove(y, takenPiece); // Facem undo la mutare

                                            if (score >= beta) {
                                                return beta;
                                            }

                                            if (score > alpha) {
                                                alpha = score;
                                            }
                                        }
                                    }
                                }

                                break;
                            case 'P':
                                ArrayList<String> P = Pieces.getAllMoves(i, j); // Preluam toate mutarile piesei
                                if (!P.isEmpty()) { // Daca sunt mutari
                                    for (String y : P) { // Pentru fiecare mutare
                                        int v[][] = Board.translatePosition(y.charAt(0) + "" + y.charAt(1) + "" + y.charAt(2) + "" + y.charAt(3));
                                        if (Board.isBlackPiece(v[1][0], v[1][1])) { // Daca mutarea pica pe vreo piesa de culoare adversa 
                                            int takenPiece = Board.board[v[1][0]][v[1][1]];
                                            Moves.recordMove(y); // Inregistram mutarea pentru a verifica daca este sah
                                            if (!checkIfCheck()) {
                                                swapEngineColor(); // schimbam pentru urmatoarea mutare
                                                score = -Quiesce(-beta, -alpha, true);
                                                swapEngineColor(); // dam undo la culoare pentru a nu influenta totul
                                            }
                                            Moves.revertMove(y, takenPiece); // Facem undo la mutare

                                            if (score >= beta) {
                                                return beta;
                                            }

                                            if (score > alpha) {
                                                alpha = score;
                                            }
                                        }
                                    }
                                }

                                break;
                            case 'C':
                                ArrayList<String> C = Pieces.getAllMoves(i, j); // Preluam toate mutarile piesei
                                if (!C.isEmpty()) { // Daca sunt mutari
                                    for (String y : C) { // Pentru fiecare mutare
                                        int v[][] = Board.translatePosition(y.charAt(0) + "" + y.charAt(1) + "" + y.charAt(2) + "" + y.charAt(3));
                                        if (Board.isBlackPiece(v[1][0], v[1][1])) { // Daca mutarea pica pe vreo piesa de culoare adversa 
                                            int takenPiece = Board.board[v[1][0]][v[1][1]];
                                            Moves.recordMove(y); // Inregistram mutarea pentru a verifica daca este sah
                                            if (!checkIfCheck()) {
                                                swapEngineColor(); // schimbam pentru urmatoarea mutare
                                                score = -Quiesce(-beta, -alpha, true);
                                                swapEngineColor(); // dam undo la culoare pentru a nu influenta totul
                                            }
                                            Moves.revertMove(y, takenPiece); // Facem undo la mutare

                                            if (score >= beta) {
                                                return beta;
                                            }

                                            if (score > alpha) {
                                                alpha = score;
                                            }
                                        }
                                    }
                                }

                                break;
                            case 'N':
                                ArrayList<String> N = Pieces.getAllMoves(i, j); // Preluam toate mutarile piesei
                                if (!N.isEmpty()) { // Daca sunt mutari
                                    for (String y : N) { // Pentru fiecare mutare
                                        int v[][] = Board.translatePosition(y.charAt(0) + "" + y.charAt(1) + "" + y.charAt(2) + "" + y.charAt(3));
                                        if (Board.isBlackPiece(v[1][0], v[1][1])) { // Daca mutarea pica pe vreo piesa de culoare adversa 
                                            int takenPiece = Board.board[v[1][0]][v[1][1]];
                                            Moves.recordMove(y); // Inregistram mutarea pentru a verifica daca este sah
                                            if (!checkIfCheck()) {
                                                swapEngineColor(); // schimbam pentru urmatoarea mutare
                                                score = -Quiesce(-beta, -alpha, true);
                                                swapEngineColor(); // dam undo la culoare pentru a nu influenta totul
                                            }
                                            Moves.revertMove(y, takenPiece); // Facem undo la mutare

                                            if (score >= beta) {
                                                return beta;
                                            }

                                            if (score > alpha) {
                                                alpha = score;
                                            }
                                        }
                                    }
                                }

                                break;
                            case 'D':
                                ArrayList<String> D = Pieces.getAllMoves(i, j); // Preluam toate mutarile piesei
                                if (!D.isEmpty()) { // Daca sunt mutari
                                    for (String y : D) { // Pentru fiecare mutare
                                        int v[][] = Board.translatePosition(y.charAt(0) + "" + y.charAt(1) + "" + y.charAt(2) + "" + y.charAt(3));
                                        if (Board.isBlackPiece(v[1][0], v[1][1])) { // Daca mutarea pica pe vreo piesa de culoare adversa 
                                            int takenPiece = Board.board[v[1][0]][v[1][1]];
                                            Moves.recordMove(y); // Inregistram mutarea pentru a verifica daca este sah
                                            if (!checkIfCheck()) {
                                                swapEngineColor(); // schimbam pentru urmatoarea mutare
                                                score = -Quiesce(-beta, -alpha, true);
                                                swapEngineColor(); // dam undo la culoare pentru a nu influenta totul
                                            }
                                            Moves.revertMove(y, takenPiece); // Facem undo la mutare

                                            if (score >= beta) {
                                                return beta;
                                            }

                                            if (score > alpha) {
                                                alpha = score;
                                            }
                                        }
                                    }
                                }

                                break;
                            case 'R':
                                ArrayList<String> R = Pieces.getAllMoves(i, j); // Preluam toate mutarile piesei
                                if (!R.isEmpty()) { // Daca sunt mutari
                                    for (String y : R) { // Pentru fiecare mutare
                                        int v[][] = Board.translatePosition(y.charAt(0) + "" + y.charAt(1) + "" + y.charAt(2) + "" + y.charAt(3));
                                        if (Board.isBlackPiece(v[1][0], v[1][1])) { // Daca mutarea pica pe vreo piesa de culoare adversa 
                                            int takenPiece = Board.board[v[1][0]][v[1][1]];
                                            Moves.recordMove(y); // Inregistram mutarea pentru a verifica daca este sah
                                            if (!checkIfCheck()) {
                                                swapEngineColor(); // schimbam pentru urmatoarea mutare
                                                score = -Quiesce(-beta, -alpha, true);
                                                swapEngineColor(); // dam undo la culoare pentru a nu influenta totul
                                            }
                                            Moves.revertMove(y, takenPiece); // Facem undo la mutare

                                            if (score >= beta) {
                                                return beta;
                                            }

                                            if (score > alpha) {
                                                alpha = score;
                                            }
                                        }
                                    }
                                }

                                break;
                        }
                    }
                }
            }
        }

        /**
         * Schimbam inapoi culoarea pentru a nu influenta alfa-beta
         */
        if (colorChanged) {
            swapEngineColor();
        }

        return alpha;
    }
}
