import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/*
 * Edit Pop Up
 */
public class Edit 
{
	private UserManager userManager;
	private User currentUser; // current logged in user
	private JFrame frame; // GUI window
	private JPanel panel; // panel to components
	private JButton save;
	private JLabel beFriend;
	private JLabel age;
	private JLabel location;
	private JTextField ageText;
	private JTextField locationText;


	/**
	 * Constructor to initialize Edit GUI frame
	 * @param currentUser given user
	 * @param userManager AccountManager
	 */
	public Edit(User currentUser, UserManager userManager)
	{
		this.currentUser = currentUser;
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

		// Message
		JLabel message = new JLabel("Edit Profile Information", SwingConstants.CENTER);
		message.setPreferredSize(new Dimension(325,25));
		message.setFont(new Font("Century Gothic", Font.BOLD, 16));
		message.setForeground(Color.DARK_GRAY);

		// Edit Form		
		age = new JLabel("Age:", SwingConstants.CENTER);
		age.setFont(new Font("Century Gothic", Font.PLAIN, 14));

		location = new JLabel("Location:", SwingConstants.CENTER);
		location.setFont(new Font("Century Gothic", Font.PLAIN, 14));

		ageText = new JTextField(25);
		locationText = new JTextField(20);

		// Save Changes Button
		save = new JButton("Save Changes");
		save.setBackground(Color.DARK_GRAY);
		save.setForeground(Color.WHITE);
		save.setFont(new Font("Century Gothic", Font.BOLD, 14));

		message.setBounds(162, 0, 325, 35);
		age.setBounds(10, 20, 80, 25);
		location.setBounds(10, 50, 80, 25);
		ageText.setBounds(100,20, 80, 25);
		locationText.setBounds(100, 50, 80, 25);
		save.setBounds(162, 80, 80, 25);

		// Add Components to Panel
		panel.add(message);
		panel.add(age);
		panel.add(ageText);
		panel.add(location);
		panel.add(locationText);
		panel.add(save);

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
		save.addActionListener(event -> {
			if(ageText.getText() == null || locationText.getText() == null)
			{
				JOptionPane.showMessageDialog(null, "Enter your changes"); // If empty input, prompt user to enter necessary fields
			}
			else
			{
				// Set user's new changed data
				currentUser.setAge(ageText.getText());
				currentUser.setLocation(locationText.getText());

				frame.dispose();
				new ProfilePage(currentUser, userManager);
			}
		});

	}
}
