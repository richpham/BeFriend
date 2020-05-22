import java.awt.*;
import javax.swing.*;

/*
 * Home Page
 */
public class View {
	private UserManager userManager;
	private JFrame frame; // GUI window
	private JPanel panel; // panel to hold components
	private JButton login;
	private JButton createAccount;
	private JLabel beFriend;
	private JLabel user;
	private JLabel password;
	private JLabel image;
	private JTextField userText;
	private JTextField passText;

	/*
	 * Constructor to initialize Home Page GUI frame
	 * @param userManager  an instance of User Manager
	 */
	public View(UserManager userManager) {
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

		// Username & Password
		user = new JLabel("Username:");
		user.setFont(new Font("Century Gothic", Font.PLAIN, 18));

		password = new JLabel("Password:");
		password.setFont(new Font("Century Gothic", Font.PLAIN, 18));

		userText = new JTextField(20);
		passText = new JTextField(20);

		// Login Button
		login = new JButton("Login");
		login.setBackground(Color.DARK_GRAY);
		login.setForeground(Color.WHITE);
		login.setFont(new Font("Century Gothic", Font.BOLD, 14));

		// Create Account Button
		createAccount = new JButton("Create Account");
		createAccount.setBackground(Color.DARK_GRAY);
		createAccount.setForeground(Color.WHITE);
		createAccount.setFont(new Font("Century Gothic", Font.BOLD, 14));

		// Image
		image = new JLabel();
		image.setPreferredSize(new Dimension(450,350));

		ImageIcon defaultPic = new ImageIcon("Images/logo.png");
		Image fittedImage = defaultPic.getImage();
		Image img = fittedImage.getScaledInstance(450, 350, Image.SCALE_SMOOTH);
		ImageIcon fittedDefault = new ImageIcon(img);
		image.setIcon(fittedDefault);

		user.setBounds(10,20, 80, 25);
		password.setBounds(10,50, 80, 25);
		userText.setBounds(100,20, 165, 25);
		passText.setBounds(100,50, 165, 25);
		login.setBounds(10, 80, 80, 25);
		createAccount.setBounds(100, 80, 80, 25);

		// Add Components to Panel

		panel.add(image);
		panel.add(user);
		panel.add(userText);
		panel.add(password);
		panel.add(passText);
		panel.add(login);
		panel.add(createAccount);

		initializeButtons(); // adds action listener anonymous classes to the buttons

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel, BorderLayout.CENTER);
		frame.add(beFriend, BorderLayout.NORTH);
		frame.setVisible(true);
	}

	/*
	 * Helper method to initialize buttons
	 */
	private void initializeButtons() {

		// Login Button Action Listener
		login.addActionListener(event -> {
			String username = userText.getText(); // Get username input
			String password = passText.getText(); // Get password input
			User enteredUser = userManager.login(username, password); // Call login method

			if(enteredUser == null)
			{
				JOptionPane.showMessageDialog(null, "Incorrect Username or Password!"); // If invalid input, display error message
			}
			else
			{
				frame.dispose();
				new ProfilePage(enteredUser, userManager); // If valid input, created new profile page
			}
		});

		// Create Account Action Listener
		createAccount.addActionListener(event -> {
			frame.dispose();
			new CreateAccount(userManager); // Open new Create Account page
		});
	}
}