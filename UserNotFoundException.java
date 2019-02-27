package targil5;

public class UserNotFoundException extends Exception{
	
	// class representing an error of user doesn't exists
	public UserNotFoundException(String msg) {
		super(msg);
	}
}

