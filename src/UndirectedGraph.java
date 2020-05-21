import java.util.ArrayList;

public class UndirectedGraph<T extends User>
{
	private ArrayList<T> network;
	
	/** Constructor for an instance of an undirected graph. **/
	public UndirectedGraph()
	{
		network = new ArrayList<T>();
	}

	/*
	 * Add vertex to undirected graph
	 * @param vertex
	 */
	public void addVertex(T vertex)
	{
		network.add(vertex);
	}

	/*
	 * Remove vertex from undirected graph
	 * @param vertex
	 */
	public void removeVertex(T vertex)
	{
		User removeUser = (User) vertex;
		ArrayList<User> friendList = removeUser.getFriends();

		// Go through user's friend list and remove user from friend's lists
		for(int i = 0; i < friendList.size(); i++)
		{
			User person = friendList.get(i); 
			person.unFriend(removeUser); 
		}
		network.remove(removeUser);
	}
	
	/*
	 * Get network of undirected graph
	 */
	public ArrayList<T> getNetwork()
	{
		return network;
	}
	
}