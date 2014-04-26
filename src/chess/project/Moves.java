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
 * @version 2.1
 * @author Selennae
 */
public class Moves {

    /*General variables for handling moves*/
    static String theMove;
    static String receivedMove;
    static String specialMove;
    //static int specialPiece;

    /**
     * Verifica daca mutarea este legala. (BYPASSED)
     *
     * @param theMove
     * @return
     */
    static boolean isLegal(String theMove) {
        return true;
    }

    /**
     * Verifica daca mutarea se face in tura corecta. (BYPASSED)
     *
     * @return
     */
    static boolean isInTurn() {
        return true;
    }
    
    static int computeMove() throws IOException{
        Engine.actualColor = Engine.color; // Setam culoarea engineului pentur evaluare
        ArrayList move = Engine.NegaMax(3); // Preluam mutarea de la NegaMax
        System.out.println("Negamax a trimis pe poz 0:" + move.get(0) + ", iar pe pozitia 2:"  /*move.get(1)*/);
        recordMove((String) move.get(1)); // Inregistram mutarea finala
        Logger.write("LOGGER::"+ Engine.color + "::" + "Am facut mutarea: " + (String)move.get(1));
        System.out.println("move " + (String)move.get(1));
        return 1;        
    }

    /**
     * Verifica daca comanda primita este o mutare.
     *
     * @param move
     * @return
     */
    static boolean checkIfMove(String cmd) {
        for (int i = 2; i < 10; i++) {
            for (int j = 2; j < 10; j++) {
                for (int k = 2; k < 10; k++) {
                    for (int l = 2; l < 10; l++) {
                        if (cmd.indexOf(Board.boardOfMoves[i][j] + Board.boardOfMoves[k][l]) != -1) {
                            receivedMove = Board.boardOfMoves[i][j] + Board.boardOfMoves[k][l];
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    /**
     * Modifica matricea engineului dupa primirea unei mutari de la
     * Winboard.<BR>
     * <BR>
     * Trebuie apelata tot timpul inainte de a face o mutare!<BR>
     *
     * @param cmd
     */
    static void recordMove(String cmd) throws IOException {
        int v[][] = Board.translatePosition(cmd.charAt(0) + "" + cmd.charAt(1) + "" + cmd.charAt(2) + "" + cmd.charAt(3));
        //specialPiece = Board.board[v[1][0]][v[1][1]]; // Piesa ce urmeaza a fi inlocuita
        Board.board[v[1][0]][v[1][1]] = Board.board[v[0][0]][v[0][1]];
        Board.board[v[0][0]][v[0][1]] = 0;

        /* (ENGINE) Verificare pion ajuns la final pentru a fi transformat in regina */
        if ("black".equals(Engine.color)) {
            if ((v[1][0] == 9) && (Board.board[v[1][0]][v[1][1]] == 'p')) {
                specialMove = cmd;
                Board.board[v[1][0]][v[1][1]] = 'd';
            }
        } else {
            if ((v[1][0] == 2) && (Board.board[v[1][0]][v[1][1]] == 'P')) {
                specialMove = cmd;
                Board.board[v[1][0]][v[1][1]] = 'D';
            }
        }

        /* (WINBOARD) Verificare pion ajuns la final pentru a fi transformat in regina */
        if (cmd.length() == 5) {
            specialMove = cmd;
            if (Board.isBlackPiece(v[1][0], v[1][1])) {
                Board.board[v[1][0]][v[1][1]] = 'd';
            }
            if (Board.isWhitePiece(v[1][0], v[1][1])) {
                Board.board[v[1][0]][v[1][1]] = 'D';
            }
        }

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                Logger.writeNNL((char) Board.board[i][j] + " ");
            }
            Logger.newLine();
        }
    }

    /**
     * Modifica matricea engineului pentru a da undo la o mutare primisa ca
     * parametru.
     *
     * @param cmd
     * @throws IOException
     */
    static void revertMove(String cmd, int specialPiece) throws IOException {
        int v[][] = Board.translatePosition(cmd.charAt(0) + "" + cmd.charAt(1) + "" + cmd.charAt(2) + "" + cmd.charAt(3));
        Board.board[v[0][0]][v[0][1]] = Board.board[v[1][0]][v[1][1]];
        Board.board[v[1][0]][v[1][1]] = specialPiece;

        /* Verificam daca mutarea precedenta a transformat un pion in regina si schimbam totul cum trebuie */
        if (cmd.equals(specialMove)) {
            if (Board.isBlackPiece(v[0][0], v[0][1])) {
                Board.board[v[0][0]][v[0][1]] = 'p';
            }
            if (Board.isWhitePiece(v[0][0], v[0][1])) {
                Board.board[v[0][0]][v[0][1]] = 'P';
            }
        }

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                Logger.writeNNL((char) Board.board[i][j] + " ");
            }
            Logger.newLine();
        }
    }
}
