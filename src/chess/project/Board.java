
package chess.project;

/**
 *
 * @author alexa_000
 */
public class Board {
    int pieces;
    boolean fiftyMove; 
    boolean enPas; //dafuq is this???
    Color side;
    
    public Board(){
        pieces = 0;
        fiftyMove = false;
        enPas = false;
        side = new Color("white");        
   }
    
    public void fiftyMoveCheck(int rounds){
        if(rounds > 50){
            fiftyMove = true;
            //request DRAW result;
        }            
    }
}
