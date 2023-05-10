package exceptions;



/**
 *  A subclass of GameActionException representing an exception that occurs when a
character tries take an action without the sufficient action points available.
 */
public class NotEnoughActionsException extends GameActionException {

	public NotEnoughActionsException() {
		// TODO Auto-generated constructor stub
	}

	public NotEnoughActionsException(String message) {
		super(message);
	}

}
