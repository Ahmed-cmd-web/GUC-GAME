package exceptions;



/**
 *  Class representing a generic exception that can occur during the game play.
 */
public abstract class GameActionException extends Exception{

	public GameActionException() {
		// TODO Auto-generated constructor stub
		super();
	}
	public GameActionException(String message) {
		super(message);
	}

}
