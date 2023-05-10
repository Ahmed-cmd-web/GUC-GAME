package exceptions;



/**
 *  A subclass of GameActionException representing an exception that occurs when a
character tries to use a Collectible he does not have.
 */
public class NoAvailableResourcesException extends GameActionException {

	public NoAvailableResourcesException() {
		// TODO Auto-generated constructor stub
	}

	public NoAvailableResourcesException(String message) {
		super(message);
	}

}
