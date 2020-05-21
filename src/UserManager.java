public class UserManager
{
	private UndirectedGraph<User> allUsers;

	/** Constructor for an instance of a profile manager. **/
	public UserManager()
	{
		allUsers = new UndirectedGraph<>();
	}

	/*
	 * Add user to undirected graph
	 * @param user
	 */
	public void addProfile(User user)
	{
		allUsers.addVertex(user);
	}

	/*
	 * Removed user from undirected graph
	 * @param user
	 */
	public void deleteAccount(User user)
	{
		allUsers.removeVertex(user);
	}
	
	/*
	 * Search for a User
	 * @param searchName
	 */
	public User findUser(String searchName)
	{
		// Go through all users in network and return user if found
		for(User user : allUsers.getNetwork()) 
		{
			if(user.getName().equals(searchName)) 
			{
				return user;
			}
		}
		return null;
	}
	
	/*
	 * Login
	 * @param username
	 * @param password
	 */
	public User login(String username, String password)
	{
		// Go through all users in network and return user if username and password match
		for(User person : allUsers.getNetwork()) 
		{
			if(person.getUsername().equals(username) && person.getPassword().equals(password)) 
			{
				return person;
			} 
		}
		return null;
	}
	
}