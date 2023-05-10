package exceptions;


/**
 * A subclass of GameActionException representing an exception that occurs upon trying
to target a wrong character with an action.
 */
public class InvalidTargetException extends GameActionException {

	public InvalidTargetException() {
		// TODO Auto-generated constructor stub
	}
	public InvalidTargetException(String message) {
		super(message);
	}
}
