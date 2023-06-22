
/**
 * @author Dylan Herthoge
 * Used when a user tries to register with a taken username.
 */
@SuppressWarnings("serial")
public class DuplicateUsernameException extends RuntimeException {

	public DuplicateUsernameException() {
		
	}
	
	public DuplicateUsernameException(String message) {
		
		super(message);
	}
}
