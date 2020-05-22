import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

/*
 * Searched Profile Page
 */
public class SearchedProfile extends JFrame
{
	private UserManager userManager;
	private User currentUser; // current logged in user
	private User searchedUser; // searched up user
	private JFrame frame; // GUI window
	private JPanel panel; // panel to hold three buttons
	private JLabel friendsLabel;
	private JLabel beFriend;
	private JLabel name;
	private JLabel age;
	private JLabel location;
	private JLabel profilePicture;
	private JLabel statusLabel;
	private JList friends;
	private JTextArea statusArea;
	private JTextField searchField;
	private JButton search;
	private JButton add;
	private JButton goBack;
	private ArrayList<User> friendsList;

	/*
	 * Constructor to initialize Searched Profile Page GUI frame
	 * @param currentUser  
	 * @param searchedUser 
	 * @param userManager
	 */
	public SearchedProfile(User currentUser, User searchedUser, UserManager userManager) {
		this.currentUser = currentUser;
		this.searchedUser = searchedUser;
		this.userManager = userManager;
		frame = new JFrame("BeFriend");
		frame.setSize(350, 700);
		frame.getContentPane().setBackground(Color.BLUE);

		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
	
		panel = new JPanel();	

		// BeFriend Logo
		beFriend = new JLabel("BeFriend", SwingConstants.CENTER);
		beFriend.setFont(new Font("Century Gothic", Font.BOLD, 36));
		beFriend.setForeground(Color.WHITE);

		// Name
		name = new JLabel(searchedUser.getName(), SwingConstants.CENTER);
		name.setFont(new Font("Century Gothic", Font.BOLD, 30));

		// Age
		age = new JLabel("Age: " + searchedUser.getAge(), SwingConstants.CENTER);
		age.setPreferredSize(new Dimension(220,15));
		age.setFont(new Font("Century Gothic", Font.BOLD, 14));

		// Location
		location = new JLabel("Location: " + searchedUser.getLocation(), SwingConstants.CENTER);
		location.setPreferredSize(new Dimension(300,15));
		location.setFont(new Font("Century Gothic", Font.BOLD, 14));

		// Status
		statusLabel = new JLabel("Status: ");
		statusLabel.setFont(new Font("Century Gothic", Font.BOLD, 14));
		statusArea = new JTextArea(searchedUser.getStatus());
		statusArea.setFont(new Font("Century Gothic", Font.PLAIN, 14));

		// Profile Picture
		profilePicture = new JLabel();
		profilePicture.setPreferredSize(new Dimension(280,280));
		profilePicture.setIcon(searchedUser.getProfilePicture());

		// Search Bar & Button
		searchField = new JTextField(20);
		search = new JButton("Search");
		search.setBackground(Color.LIGHT_GRAY);
		search.setForeground(Color.WHITE);
		search.setFont(new Font("Century Gothic", Font.BOLD, 14));

		// Add Button
		add = new JButton("Add Friend");
		add.setBackground(Color.LIGHT_GRAY);
		add.setForeground(Color.WHITE);
		add.setFont(new Font("Century Gothic", Font.BOLD, 14));

		// Return Button
		goBack = new JButton("Return to Your Profile");
		goBack.setBackground(Color.LIGHT_GRAY);
		goBack.setForeground(Color.WHITE);
		goBack.setFont(new Font("Century Gothic", Font.BOLD, 14));

		// Friends List
		friendsList = searchedUser.getFriends();
		friendsLabel = new JLabel(searchedUser.getFirstName() + "'s Friends", SwingConstants.CENTER);
		friendsLabel.setPreferredSize(new Dimension(220,30));
		friendsLabel.setFont(new Font("Century Gothic", Font.BOLD, 18));

		// Convert ArrayList into Array
		String friendsListArray[] = new String[friendsList.size()];
		for(int i = 0; i < friendsList.size(); i++)
		{
			friendsListArray[i] =  friendsList.get(i).getName();
		}

		friends = new JList(friendsListArray);
		friends.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// List selection listener anonymous class
		friends.addListSelectionListener(event -> {

			String friendSelection = (String) friends.getSelectedValue();
			User friend = userManager.findUser(friendSelection);
			if (friend.getName().equals(currentUser.getName())) {
				frame.dispose();
				new ProfilePage(currentUser, userManager);
			}
			else {
				frame.dispose();
				new SearchedProfile(currentUser, friend, userManager);	
			}
		});

		JLabel blank = new JLabel("", SwingConstants.CENTER);
		blank.setPreferredSize(new Dimension(350,30));
		JLabel blank2= new JLabel("", SwingConstants.CENTER);
		blank2.setPreferredSize(new Dimension(350,30));

		search.setBounds(10, 10,80, 25);
		search.setBounds(100, 10,80, 25);
		name.setBounds(162, 40, 325, 35);
		statusLabel.setBounds(10, 70, 80, 25);
		statusArea.setBounds(100, 70, 80, 25);
		profilePicture.setBounds(162, 130, 100, 100);
		add.setBounds(162, 150, 80, 25);
		goBack.setBounds(162, 180, 80, 25);

		// Add Components to Panel
		panel.add(searchField);
		panel.add(search);
		panel.add(name);
		panel.add(age);
		panel.add(location);
		panel.add(statusLabel);
		panel.add(statusArea);
		panel.add(profilePicture);
		panel.add(friendsLabel);
		panel.add(blank);
		panel.add(friends);
		panel.add(blank2);
		panel.add(add);
		panel.add(goBack);

		initializeButtons(); // adds action listener anonymous classes to the buttons

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel, BorderLayout.CENTER);
		frame.add(beFriend, BorderLayout.NORTH);
		frame.setVisible(true);
	}

	/**
	 * Helper method to initialize buttons
	 */
	private void initializeButtons() {

		// Search Button Action Listener
		search.addActionListener(event -> {
			String searched = searchField.getText(); 

			User searchedUpPerson = userManager.findUser(searched);

			if(searchedUpPerson == null)
			{
				JOptionPane.showMessageDialog(null, searched + " was not found."); // Profile not found
			}
			else
			{
				frame.dispose();
				new SearchedProfile(currentUser, searchedUpPerson, userManager);
			}

		});


		// Add Friend Button Action Listener
		add.addActionListener(event -> {
			User personProfile = searchedUser;
			friendsList = currentUser.getFriends();

			if(personProfile == currentUser) // If user attempts to add themselves, display error message
			{
				JOptionPane.showMessageDialog(null, "You cannot befriend yourself!");
			}
			else if (friendsList.size() > 0)
			{
				for(int i = 0 ; i < friendsList.size() ; i++) 
				{
					if(friendsList.get(i).getName().equals(personProfile.getName())) // If user is already friends with the searched user, display error message
					{
						JOptionPane.showMessageDialog(null, "You and " + searchedUser.getName() + " are already friends!");
					}
					else {
						currentUser.beFriend(personProfile);
						personProfile.beFriend(currentUser);
						JOptionPane.showMessageDialog(null, "You befriended " + searchedUser.getName() + "!");
					}
				}
			}
			else{
				currentUser.beFriend(personProfile);
				personProfile.beFriend(currentUser);
				JOptionPane.showMessageDialog(null, "You befriended " + searchedUser.getName() + "!");
			}
		});

		// Back Button Action Listener
		goBack.addActionListener(event -> {
			frame.dispose();
			new ProfilePage(currentUser, userManager);
		});

	}

} 