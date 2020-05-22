import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/*
 * Create Account Page
 */
public class CreateAccount 
{
	private UserManager userManager;
	private User newUser;
	private JFrame frame; // GUI window
	private JPanel panel; // panel to hold components
	private JButton createAccount;
	private JLabel beFriend;
	private JLabel image;
	private JLabel user;
	private JLabel password;
	private JLabel firstName;
	private JLabel lastName;
	private JLabel age;
	private JLabel location;
	private JTextField userText;
	private JTextField passText;
	private JTextField firstNameText;
	private JTextField lastNameText;
	private JTextField ageText;
	private JTextField locationText;


	/**
	 * Constructor to initialize Create Account GUI frame
	 * @param userManager 
	 */
	public CreateAccount(UserManager userManager)
	{
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

		// Slogan
		JLabel message = new JLabel("Create an Account & Lets Be Friends!", SwingConstants.CENTER);
		message.setFont(new Font("Century Gothic", Font.BOLD, 16));
		message.setForeground(Color.DARK_GRAY);
		
		// Image
		image = new JLabel();
		image.setPreferredSize(new Dimension(450,350));

		ImageIcon defaultPic = new ImageIcon("Images/logo.png");
		Image fittedImage = defaultPic.getImage();
		Image img = fittedImage.getScaledInstance(450, 350, Image.SCALE_SMOOTH);
		ImageIcon fittedDefault = new ImageIcon(img);
		image.setIcon(fittedDefault);

		// Create Account Form
		user = new JLabel("Username:");
		user.setFont(new Font("Century Gothic", Font.PLAIN, 14));

		password = new JLabel("Password:");
		password.setFont(new Font("Century Gothic", Font.PLAIN, 14));

		firstName = new JLabel("First Name:");
		firstName.setFont(new Font("Century Gothic", Font.PLAIN, 14));

		lastName = new JLabel("Last Name:");
		lastName.setFont(new Font("Century Gothic", Font.PLAIN, 14));

		age = new JLabel("Age:");
		age.setPreferredSize(new Dimension(50,16));
		age.setFont(new Font("Century Gothic", Font.PLAIN, 14));

		location = new JLabel("Location:");
		location.setFont(new Font("Century Gothic", Font.PLAIN, 14));

		userText = new JTextField(20);
		passText = new JTextField(20);
		firstNameText = new JTextField(20);
		lastNameText = new JTextField(20);
		ageText = new JTextField(20);
		locationText = new JTextField(20);

		// Create Account Button
		createAccount = new JButton("Create Account");
		createAccount.setBackground(Color.DARK_GRAY);
		createAccount.setForeground(Color.WHITE);
		createAccount.setFont(new Font("Century Gothic", Font.BOLD, 14));

		message.setBounds(162, 0, 325, 35);
		user.setBounds(10,20, 80, 25);
		password.setBounds(10,50, 80, 25);
		firstName.setBounds(10,80, 80, 25);
		lastName.setBounds(10, 110, 80, 25);
		age.setBounds(10,140, 80, 25);
		location.setBounds(10, 170, 80, 25);
		userText.setBounds(100,20, 165, 25);
		passText.setBounds(100,50, 165, 25);
		firstNameText.setBounds(100,80, 80, 25);
		lastNameText.setBounds(100, 110, 80, 25);
		ageText.setBounds(100,140, 80, 25);
		locationText.setBounds(100, 170, 80, 25);
		createAccount.setBounds(162, 200, 80, 25);

		// Add Components to Panel
		panel.add(image);
		panel.add(message);
		panel.add(user);
		panel.add(userText);
		panel.add(password);
		panel.add(passText);
		panel.add(firstName);
		panel.add(firstNameText);
		panel.add(lastName);
		panel.add(lastNameText);
		panel.add(age);
		panel.add(ageText);
		panel.add(location);
		panel.add(locationText);
		panel.add(createAccount);


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

		// Create Account Action Listener
		createAccount.addActionListener(event -> {
			newUser = new User();
			if(firstNameText.getText() == null || lastNameText.getText() == null)
			{
				JOptionPane.showMessageDialog(null, "Enter a first and last name."); // If empty input, prompt user to enter necessary fields
			}
			else
			{
				// Set user's data and add user to Account Manager
				newUser.setUsername(userText.getText());
				newUser.setPassword(passText.getText());
				newUser.setName(firstNameText.getText(), lastNameText.getText());
				newUser.setFirstName(firstNameText.getText());
				newUser.setAge(ageText.getText());
				newUser.setLocation(locationText.getText());
				userManager.addProfile(newUser);

				frame.dispose();
				new ProfilePage(newUser, userManager); // Open profile page
			}
		});

	}
}
