
package chess.project;

/**
 *
 * @author alexa_000
 */
public class Color {
    int color;
    
    public Color(){
        this.color = 1;
    }
    
    public Color(String color){
        if(color == "white")
            this.color = 1;
        else
            this.color = 2;
    }
}
