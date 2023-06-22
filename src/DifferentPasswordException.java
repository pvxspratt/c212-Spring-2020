
/**
 * @author Dylan Herthoge
 * Used in validating that the user knows their password when registering.
 */
@SuppressWarnings("serial")
public class DifferentPasswordException extends RuntimeException {

	public DifferentPasswordException() {
		
	}
	
	public DifferentPasswordException(String message) {
		
		super(message);
	}
}
