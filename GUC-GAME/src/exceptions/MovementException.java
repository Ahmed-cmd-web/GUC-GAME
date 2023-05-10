package exceptions;


/**
 * A subclass of GameActionException representing an exception that occurs when a
character tries to make an invalid movement.
 */
public class MovementException extends GameActionException {

	public MovementException() {
		// TODO Auto-generated constructor stub
	}
	public MovementException(String message) {
		super(message);
	}
}
