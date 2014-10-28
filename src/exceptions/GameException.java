package exceptions;

/**
 * Classe GameException
 * @author Yannis M'RAD, Vincent AUNAI
 * 
 * Exceptions personnalisees
 *
 */
public class GameException extends Exception{
	private String message;
	
	/**
	 * Constructeur de GameException
	 * @param message
	 */
	public GameException(String message)
	{
		super();
		this.message = message;
	}
	
	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
