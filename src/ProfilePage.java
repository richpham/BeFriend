import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
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
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * Profile Page
 */
public class ProfilePage extends JFrame
{
	private UserManager userManager;
	private User currentUser; // current logged in user
	private JFrame frame; // GUI window
	private JPanel panel; // panel to hold components
	private JLabel friendsLabel;
	private JLabel beFriend;
	private JLabel name;
	private JLabel age;
	private JLabel location;
	private JLabel profilePicture;
	private JLabel statusLabel;
	private JList friends;
	private JTextArea statusArea;
	private JTextField status;
	private JTextField searchField;
	private JButton search;
	private JButton edit;
	private JButton upload;
	private JButton update;
	private JButton delete;
	private JButton logout;
	private ArrayList<User> friendsList;

	/*
	 * Constructor to initialize Profile Page GUI frame
	 * @param user 
	 * @param userManager
	 */
	public ProfilePage(User user, UserManager userManager) {
		this.userManager = userManager;
		frame = new JFrame("BeFriend");
		frame.setSize(350, 700);
		frame.getContentPane().setBackground(Color.BLUE);
		panel = new JPanel();	
		currentUser = user;

		// BeFriend Logo
		beFriend = new JLabel("BeFriend", SwingConstants.CENTER);
		beFriend.setFont(new Font("Century Gothic", Font.BOLD, 36));
		beFriend.setForeground(Color.WHITE);

		// Name
		name = new JLabel(user.getName(), SwingConstants.CENTER);
		name.setPreferredSize(new Dimension(300,35));
		name.setFont(new Font("Century Gothic", Font.BOLD, 30));

		// Age
		age = new JLabel("Age: " + user.getAge(), SwingConstants.CENTER);
		age.setPreferredSize(new Dimension(220,15));
		age.setFont(new Font("Century Gothic", Font.BOLD, 14));

		// Location
		location = new JLabel("Location: " + user.getLocation(), SwingConstants.CENTER);
		location.setPreferredSize(new Dimension(300,15));
		location.setFont(new Font("Century Gothic", Font.BOLD, 14));

		// Status
		statusLabel = new JLabel("Status: ");
		statusLabel.setFont(new Font("Century Gothic", Font.BOLD, 14));
		statusArea = new JTextArea(currentUser.getStatus());
		statusArea.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		status = new JTextField(20);

		// Profile Picture
		profilePicture = new JLabel();
		profilePicture.setPreferredSize(new Dimension(280,280));
		profilePicture.setIcon(user.getProfilePicture());

		// Change Profile Picture Button
		upload = new JButton("Change Profile Picture");
		upload.setBackground(Color.LIGHT_GRAY);
		upload.setForeground(Color.WHITE);
		upload.setFont(new Font("Century Gothic", Font.BOLD, 14));

		// Edit Profile Button
		edit = new JButton("Edit Account");
		edit.setBackground(Color.LIGHT_GRAY);
		edit.setForeground(Color.WHITE);
		edit.setFont(new Font("Century Gothic", Font.BOLD, 14));

		// Update Status Button
		update = new JButton("Post");
		update.setBackground(Color.LIGHT_GRAY);
		update.setForeground(Color.WHITE);
		update.setFont(new Font("Century Gothic", Font.BOLD, 14));

		// Search Bar & Button
		searchField = new JTextField(20);
		search = new JButton("Search");
		search.setBackground(Color.LIGHT_GRAY);
		search.setForeground(Color.WHITE);
		search.setFont(new Font("Century Gothic", Font.BOLD, 14));

		// Delete Account Button
		delete = new JButton("Delete Account");
		delete.setBackground(Color.LIGHT_GRAY);
		delete.setForeground(Color.WHITE);
		delete.setFont(new Font("Century Gothic", Font.BOLD, 14));

		// Logout Button
		logout = new JButton("Logout");
		logout.setBackground(Color.LIGHT_GRAY);
		logout.setForeground(Color.WHITE);
		logout.setFont(new Font("Century Gothic", Font.BOLD, 14));

		// Friends List
		friendsList = user.getFriends();
		friendsLabel = new JLabel("Friends", SwingConstants.CENTER);
		friendsLabel.setPreferredSize(new Dimension(275,30));
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

			frame.dispose();
			new SearchedProfile(currentUser, friend, userManager);	
		});

		JLabel blank = new JLabel("", SwingConstants.CENTER);
		blank.setPreferredSize(new Dimension(275,10));

		searchField.setBounds(10, 10,80, 25);
		search.setBounds(100, 10,80, 25);
		name.setBounds(162, 40, 325, 35);
		statusLabel.setBounds(10, 70, 80, 25);
		statusArea.setBounds(100, 70, 80, 25);
		status.setBounds(10, 100, 80, 25);
		update.setBounds(100, 100, 80, 25);
		profilePicture.setBounds(162, 130, 100, 100);
		upload.setBounds(10, 150, 80, 25);
		edit.setBounds(10, 180, 80, 25);
		delete.setBounds(100, 180, 80, 25);
		logout.setBounds(200, 210, 80, 25);

		// Add Components to Panel
		panel.add(searchField);
		panel.add(search);
		panel.add(name);
		panel.add(age);
		panel.add(location);
		panel.add(statusLabel);
		panel.add(statusArea);
		panel.add(status);
		panel.add(update);
		panel.add(profilePicture);
		panel.add(upload);
		panel.add(edit);
		panel.add(delete);
		panel.add(friendsLabel);
		panel.add(friends);
		panel.add(blank);
		panel.add(logout);

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
			String searched = searchField.getText(); // Get search input
			User searchedUpPerson = userManager.findUser(searched); // Call findPerson method

			if(searchedUpPerson == null)
			{
				JOptionPane.showMessageDialog(null, searched + " was not found."); // Profile not found
			}
			else
			{
				frame.dispose();
				new SearchedProfile(currentUser, searchedUpPerson, userManager); // If profile found, open searched profile page
			}

		});

		// Change Profile Picture Button Action Listener
		upload.addActionListener(event -> {
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("4 Extensions Supported", "jpg", "png", "jpeg", "gif");
			fileChooser.setFileFilter(filter);

			// Check if button is selected
			int selected = fileChooser.showOpenDialog(null);
			if (selected == JFileChooser.APPROVE_OPTION) {

				File file = fileChooser.getSelectedFile();
				String getImage = file.getAbsolutePath();

				// Show Image Path Message Dialog
				JOptionPane.showMessageDialog(null, getImage);
				ImageIcon imageIcon = new ImageIcon(getImage);

				// Fit Image to JLabel
				Image fittedImage = imageIcon.getImage();
				Image image = fittedImage.getScaledInstance(profilePicture.getWidth(), profilePicture.getHeight(), Image.SCALE_SMOOTH);

				// Set Image to JLabel and update User's data
				profilePicture.setIcon(new ImageIcon(image));
				currentUser.setProfilePicture(new ImageIcon(image));
			}
		});

		// Update Status Button Action Listener
		update.addActionListener(event -> {
			currentUser.setStatus(status.getText());
			statusArea.setText(currentUser.getStatus());
		});

		// Edit Profile Button Action Listener
		edit.addActionListener(event -> {
			frame.dispose();
			new Edit(currentUser, userManager);
		});

		// Delete Profile Button Action Listener
		delete.addActionListener(event -> {
			userManager.deleteAccount(currentUser);
			frame.dispose();
			JOptionPane.showMessageDialog(null, "Account deleted");
			new View(userManager);
		});

		// Logout Button Action Listener
		logout.addActionListener(event -> {
			frame.dispose();
			new View(userManager);
		});

	}

}