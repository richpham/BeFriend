import java.util.*;
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * User
 */
public class User {
	
	private String username;
	private String password;
	private String name;
	private String firstName;
	private String age;
	private String location;
	private String status = null;
	private ImageIcon picture = null;
	private ArrayList<User> friendProfiles;

	/** Constructor for an instance of a user. **/
	public User()
	{
		friendProfiles = new ArrayList<>();
	}
	
	/*
	 * Set User's Profile Picture
	 * @param profilePicture
	 */
	public void setProfilePicture(ImageIcon profilePicture)
	{
		picture = profilePicture;
	}

	/*
	 * Get User's Profile Picture
	 */
	public ImageIcon getProfilePicture()
	{
		// If no profile picture set, display default profile picture
		if(picture == null) {
			
			// Fit Image to JLabel
			ImageIcon defaultPic = new ImageIcon("Images/default-profile-picture.jpg");
			Image fittedImage = defaultPic.getImage();
			Image image = fittedImage.getScaledInstance(280, 280, Image.SCALE_SMOOTH);
			ImageIcon fittedDefault = new ImageIcon(image);
			return fittedDefault;		
		}else {
			return picture;
		}
	}
	
	/*
	 * Set Username
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/*
	 * Get Username
	 */
	public String getUsername() {
		return username;
	}

	/*
	 * Set Password
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/*
	 * Get Password
	 */
	public String getPassword() {
		return password;
	}
	
	/*
	 * Set User's Name
	 * @param firstName
	 * @param lastName
	 */
   public void setName(String firstName, String lastName)
   {
      name = firstName.replaceAll("\\s+","") + " " + lastName.replaceAll("\\s+","");
   }

	/*
	 * Get User's Name
	 */
   public String getName()
   {
      return name;
   }
   
	/*
	 * Set User's First Name
	 * @param firstName
	 */
   public void setFirstName(String firstName) {
	   this.firstName = firstName;
   }
   
	/*
	 * Get User's First Name
	 */
   public String getFirstName() {
	   return firstName;
   }
   
	/*
	 * Set User's Age
	 * @param age
	 */
   public void setAge(String age) {
	   this.age = age;
   }
   
	/*
	 * Get User's Age
	 */
   public String getAge() {
	   return age;
   }

	/*
	 * Set User's Location
	 * @param location
	 */
   public void setLocation(String location) {
	   this.location = location;
   }
   
	/*
	 * Get User's Location
	 */
   public String getLocation() {
	   return location;
   }
   
	/*
	 * Set User's Status
	 * @param status
	 */
   public void setStatus(String status) {
	   this.status = status;
   }
   
	/*
	 * Get User's Status
	 */
   public String getStatus() {
	   // If no status set, return default status
	   if(status == null) {
		   return "New to BeFriend";
	   } else {
		   return status;
	   }
   }
   
	/*
	 * Get List of User's Friends
	 */
	public ArrayList<User> getFriends()
	{
		return friendProfiles;
	}

	/*
	 * Adds friend into User's list of friends
	 * @param user
	 */
	public void beFriend(User user)
	{
		friendProfiles.add(user);
	}
	
	/*
	 * Removes friend from User's list of friends
	 * @param user
	 */
	public boolean unFriend(User user)
	{
		return friendProfiles.remove(user);
	}

}