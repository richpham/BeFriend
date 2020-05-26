# BeFriend

### Created by Rich Pham and Ngoc Doan
Technologies used include Eclipse, Java, and GUI

## App Description
BeFriend is our final project for our Data Structures and Algorithm course that presents our understanding of undirected graphs and graphical user interface to successfully execute a fully functional social networking application.

The overall goal of this project is to implement an application that mimics the attributes of the well known social networking app, Facebook. Facebook was created by Mark Zuckerberg alongside his fellow classmates during his sophomore year at Harvard University. Zuckerberg and the contributing creators of Facebook are representative of how the fundamental computer science concepts taught in our Data Structures and Algorithms course are utilized in real life applications.
Our rendition of this social application is called BeFriend. BeFriend has the main aspects that all social networking applications contain to serve as an easily accessible means of human connection for users.


## App Walk-through

### Login Page
<img src="https://i.imgur.com/n0VNp33.gif" width=250><br>
- [x] Allows user to enter in required username and password to login
- [x] Prompts error message if invalid information is entered
- [x] Displays a 'Create Account' button if the user is not already registered

### Create Account Page
<img src="https://i.imgur.com/7Gz1aTk.gif" width=250><br>
- [x] Displays form in which user enters required information to create an account
- [x] Stores user into an undirected graph that represents the network
- [x] Opens default profile page after user successfully creates account

### Modify Profile Page
<img src="https://i.imgur.com/NFFcUkF.gif" width=250><br>
- [x] Allows user to post status and edit their age and location
- [x] Allows user to upload a personalized photo as a profile picture
- [x] Displays default profile picture and status if user has not modified

### Search Profiles and Befriend
<img src="https://i.imgur.com/mcWPUM3.gif" width=250><br>
- [x] Search for other users and prompts an error message if user is not found in network
- [x] Opens the searched profile if the user is found
- [x] Allows user to add searched user as friend and successfully updates both friends' list to show connection

### Logout
<img src="https://i.imgur.com/WdsHDbR.gif" width=250><br>
- [x] Displays logout button on profile page
- [x] Allows user to leave application without fully removing account from network
- [x] Reopens homepage after successfully logging out

### Delete Account
<img src="https://i.imgur.com/UVAWje9.gif" width=250><br>
- [x] Allows user to permanently delete account from network
- [x] Removes deleted account from any connections they previously had
- [x] Reopens homepage after successfully deleting out
