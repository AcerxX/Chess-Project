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
 * @author Alexandru MIHAI
 */
public class Board {    
    
    /*Variables needed for board implementation*/
    public static int[][] board;
    public static String[][] boardOfMoves;
    
    /**
     * newGame()
     * 
     * Reset the board to the standard chess starting position.
     * Set White on move.
     * Leave force mode and set the engine to play Black.
     * Associate the engine's clock with Black and the opponent's clock with White.
     * Reset clocks and time controls to the start of a new game.
     * Use wall clock for time measurement.
     * Stop clocks.
     */
    @SuppressWarnings("empty-statement")
    static void newGame() {
        
        /**/
        int[][] newBoard = { {-1, -1, -1, -1, -1, -1, -1, -1, -1,-1},
                          {-1, -1, -1, -1, -1, -1, -1, -1, -1,-1},
                          {-1,'t','c','n','d','r','n','c','t',-1},
                          {-1,'p','p','p','p','p','p','p','p',-1},
                          {-1, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,-1},
                          {-1, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,-1},
                          {-1, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,-1},
                          {-1, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,-1},
                          {-1,'P','P','P','P','P','P','P','P',-1},
                          {-1,'T','C','N','D','R','N','C','T',-1},
                          {-1, -1, -1, -1, -1, -1, -1, -1, -1,-1},
                          {-1, -1, -1, -1, -1, -1, -1, -1, -1,-1}
                        };
        String[][] newBoardOfMoves = { {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"},
                          {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"},
                          {"-1","a8","b8","c8","d8","e8","f8","g8","h8","-1"},
                          {"-1","a7","b7","c7","d7","e7","f7","g7","h7","-1"},
                          {"-1","a6","b6","c6","d6","e6","f6","g6","h6","-1"},
                          {"-1","a5","b5","c5","d5","e5","f5","g5","h5","-1"},
                          {"-1","a4","b4","c4","d4","e4","f4","g4","h4","-1"},
                          {"-1","a3","b3","c3","d3","e3","f3","g3","h3","-1"},
                          {"-1","a2","b2","c2","d2","e2","f2","g2","h2","-1"},
                          {"-1","a1","b1","c1","d1","e1","f1","g1","h1","-1"},
                          {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"},
                          {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"}
                        };
        board = new int[12][10];
        board = newBoard;
        //boardOfMoves = new String[12][10];
        boardOfMoves = newBoardOfMoves;
    }

    static void initAll() {
        int[][] newBoard = { {-1, -1, -1, -1, -1, -1, -1, -1, -1,-1},
                          {-1, -1, -1, -1, -1, -1, -1, -1, -1,-1},
                          {-1,'t','c','n','d','r','n','c','t',-1},
                          {-1,'p','p','p','p','p','p','p','p',-1},
                          {-1, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,-1},
                          {-1, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,-1},
                          {-1, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,-1},
                          {-1, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ,-1},
                          {-1,'P','P','P','P','P','P','P','P',-1},
                          {-1,'T','C','N','D','R','N','C','T',-1},
                          {-1, -1, -1, -1, -1, -1, -1, -1, -1,-1},
                          {-1, -1, -1, -1, -1, -1, -1, -1, -1,-1}
                        };
        String[][] newBoardOfMoves = { {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"},
                          {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"},
                          {"-1","a8","b8","c8","d8","e8","f8","g8","h8","-1"},
                          {"-1","a7","b7","c7","d7","e7","f7","g7","h7","-1"},
                          {"-1","a6","b6","c6","d6","e6","f6","g6","h6","-1"},
                          {"-1","a5","b5","c5","d5","e5","f5","g5","h5","-1"},
                          {"-1","a4","b4","c4","d4","e4","f4","g4","h4","-1"},
                          {"-1","a3","b3","c3","d3","e3","f3","g3","h3","-1"},
                          {"-1","a2","b2","c2","d2","e2","f2","g2","h2","-1"},
                          {"-1","a1","b1","c1","d1","e1","f1","g1","h1","-1"},
                          {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"},
                          {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"}
                        };
        //board = new int[12][10];
        board = newBoard;
        //boardOfMoves = new String[12][10];
        boardOfMoves = newBoardOfMoves;
    }
    
    public String translatePosition(int i, int j){
        return Board.boardOfMoves[i][j];
    }
    
    public String translatePosition(String position){
        return "not implemented";
    }

}
