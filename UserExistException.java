package targil5;

public class UserExistException extends Exception{
	
	// class representing an error of user does exists
	public UserExistException(String msg) {
		super(msg);
	}
}

