package targil5;

public class SocialNetwork {
	
	private Graph<String> network;
	
	// constructor
	public SocialNetwork() {
		network = new Graph<String>();
	}
	
	// the method add new user to graph and if the use already exists the method throw UserExistException.
	public void addUser(String user) throws UserExistException {
		try {
			this.network.addVertex(user);
		}catch (VertexExistException e) {
			throw new UserExistException("Already exists");
		}
	}
	
	// the method create connections between 2 users, if one of the user doesn't exists the method throw UserNotFoundException.
	public void addFriends(String user1, String user2) throws UserExistException, UserNotFoundException {
		try {
			this.network.addEdge(user1, user2);
		}catch (VertexNotExistException e) {
			throw new UserNotFoundException("Not exists");
		}
	}
	
	// the method get 2 users and check if there is connections between them.
	// if one of the user doesn't exists the method throw UserNotFoundException.
	public boolean knows(String user1, String user2) throws UserNotFoundException {
		if(!this.network.getVertices().contains(user1)) {
			throw new UserNotFoundException("Not found");
		}
		if(!this.network.getVertices().contains(user2)) {
			throw new UserNotFoundException("Not found");
		}
		if(!this.network.bfs(user1, user2).isEmpty()) {
			return true;
		}
		return false;	
	}	
}
