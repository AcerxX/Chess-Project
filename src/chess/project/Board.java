/*
 * Copywright (C) [2014] [Alexandru MIHAI] [Andreea ILIES] [Teodora TANASE] [Anca ROSCA]
 * 
 * This file is part of Thunder Chickens Chess Project.
 * 
 * Title: The Chess Project of Team <Thunder Chickens> @ CS, PUB, RO. 
 */

package chess.project;

/**
 *
 * @version 0.4.1a
 * @author Alexandru MIHAI
 */
public class Board {    
    
    /*Variables needed for board implementation*/
    public static int[][] board;
    public static String[][] boardOfMoves;
    
    /**
     * Reseteaza matricea pieselor.<BR>
     * TODO MORE in etapa 2
     */
    @SuppressWarnings("empty-statement")
    static void newGame() {
        
        /**/
        int[][] newBoard = { {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,-1, -1},
                          {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,-1, -1},
                          {-1, -1,'t','c','n','d','r','n','c','t',-1, -1},
                          {-1, -1,'p','p','p','p','p','p','p','p',-1, -1},
                          {-1, -1, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,-1, -1},
                          {-1, -1, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,-1, -1},
                          {-1, -1, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,-1, -1},
                          {-1, -1, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,-1, -1},
                          {-1, -1,'P','P','P','P','P','P','P','P',-1, -1},
                          {-1, -1,'T','C','N','D','R','N','C','T',-1, -1},
                          {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,-1, -1},
                          {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,-1, -1}
                        };
        board = new int[12][12];
        board = newBoard;
    }

    /**
     * Initializeaza toate componentele.<BR>
     * Cauza principala in cazul intalnirii erorii de null pointer exception!
     */
    static void initAll() {
        int[][] newBoard = { 
                          {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,-1, -1},
                          {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,-1, -1},
                          {-1, -1,'t','c','n','d','r','n','c','t',-1, -1},
                          {-1, -1,'p','p','p','p','p','p','p','p',-1, -1},
                          {-1, -1, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,-1, -1},
                          {-1, -1, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,-1, -1},
                          {-1, -1, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,-1, -1},
                          {-1, -1, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,-1, -1},
                          {-1, -1,'P','P','P','P','P','P','P','P',-1, -1},
                          {-1, -1,'T','C','N','D','R','N','C','T',-1, -1},
                          {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,-1, -1},
                          {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,-1, -1}
                        };
        String[][] newBoardOfMoves = { 
                          {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"},
                          {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"},
                          {"-1", "-1", "a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8", "-1", "-1"},
                          {"-1", "-1", "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7", "-1", "-1"},
                          {"-1", "-1", "a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6", "-1", "-1"},
                          {"-1", "-1", "a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5" ,"-1", "-1"},
                          {"-1", "-1", "a4" ,"b4", "c4", "d4", "e4", "f4", "g4", "h4", "-1", "-1"},
                          {"-1", "-1", "a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3", "-1", "-1"},
                          {"-1", "-1", "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2", "-1", "-1"},
                          {"-1", "-1", "a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1", "-1", "-1"},
                          {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"},
                          {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"}
                        };
        
        board = newBoard;        
        boardOfMoves = newBoardOfMoves;
    }
    
    /**
     * Returneaza coordonatele xboard pentru un i si j ale matricii
     * 
     * @param i
     * @param j
     * @return 
     */
    public static String translatePosition(int i, int j){
        return Board.boardOfMoves[i][j];
    }
    
   /**
    * Returneaza coordonatele mutarii primite ca parametru:<BR>
    * - v[0][0] --> coordonata i de pe care a plecat;<BR>
    * - v[0][1] --> coordonata j de pe care a plecat;<BR>
    * - v[1][0] --> coordonata i pe care a ajuns;<BR>
    * - v[1][1] --> coordonata j pe care a ajuns;<BR>
    * 
    * @param position
    * @return int[][]
    */
    public static int[][] translatePosition(String position){
        
        int[][] v = new int[2][2];
        for(int i=2;i<10;i++)
            for(int j=2;j<10;j++)
                for(int k=2;k<10;k++)
                    for(int l=2;l<10;l++)
                        if(position.equals(boardOfMoves[i][j]+boardOfMoves[k][l])){
                            v[0][0] = i;
                            v[0][1] = j;
                            v[1][0] = k;
                            v[1][1] = l;
                        }
        return v;                            
    }
    
    /**
     * Returneaza un vector ce are pe prima pozitie continutul locului de unde a plecat piesa,<BR>
     * iar pe a doua pozitie continutul locului unde a ajuns piesa.<BR>
     * 
     * @param position
     * @return 
     */
    public static int[] contains(String position){
        
        int[] v = new int[2];
        for(int i=2;i<10;i++)
            for(int j=2;j<10;j++)
                for(int k=2;k<10;k++)
                    for(int l=2;l<10;l++)
                        if(position.equals(boardOfMoves[i][j]+boardOfMoves[k][l])){
                            v[0] = board[i][j];
                            v[1] = board[k][l];
                        }
        return v;                            
    }

    static boolean isWhitePiece(int i, int j) {
        return (board[i][j] == 'P') ||
               (board[i][j] == 'T') ||
               (board[i][j] == 'N') ||
               (board[i][j] == 'C') ||
               (board[i][j] == 'D') ||
               (board[i][j] == 'R');
    }

    static boolean isPiece(int i, int j) {
        return isWhitePiece(i,j) || isBlackPiece(i,j);
    }

    static boolean isBlackPiece(int i, int j) {
        if((i<0) || (j<0))
            return true;
        if((i>11) || (j>11))
            return true;
        return (board[i][j] == 'p') ||
               (board[i][j] == 't') ||
               (board[i][j] == 'n') ||
               (board[i][j] == 'c') ||
               (board[i][j] == 'd') ||
               (board[i][j] == 'r');
    }

    static boolean outOfBounds(int i, int j) {
        return (board[i][j] == -1);
    }

}
