package exceptions;

//representing a generic exception that can occur during the game play.

public abstract class GameActionException extends Exception {
    
    public GameActionException(){
        super();
    }
    public GameActionException(String s){
        super(s);
    }

}
