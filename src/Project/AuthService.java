package Project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AuthService{
	public HashMap<String, User> users;

	    /// Loads csv file lines into users 
		private void loadFile() {
			try {
				Scanner in = new Scanner(new File("./Users.csv"));
	
				while (in.hasNextLine()) {
					
					String line = in.nextLine().strip();

					if (line == "") { // Check for end of file
						break;
					}

					User userRef = User.fromString(line); // Create user
					this.users.put(userRef.id, userRef);  // Store into hashmap, with its ID as the key
				}
				in.close();
			} catch (Exception exception) {
				System.out.println(exception.toString());
	
			}
		}
		
		/// Saves the users in memory to file
		public void saveUsers() {
			try {   
				FileWriter fw = new FileWriter("./Users.csv"); // true appends data

				Iterator usersIterator = this.users.entrySet().iterator();
				while (usersIterator.hasNext()) {
					Map.Entry mapElement = (Map.Entry)usersIterator.next();
					User user = (User)mapElement.getValue();
					fw.write((String)mapElement.getKey() + "," + user.toString() + "\n");
				}

				fw.close();
			} catch(IOException e) {
				System.err.println("IOException: " + e.getMessage());
			}
		}

		/// refreshes file from memory
		/// will overwrite new users
		public void refreshUsers() {
			this.users.clear();
			this.loadFile();
		}

		// check to see if user exists
		public void addNewUser(User user) {
			this.users.put(user.id, user);
		}

		// check to see if user exists, forceUpdate will force a file save and load
		public void addNewUser(User user, boolean forceUpdate) {
			this.users.put(user.id, user);
			if (forceUpdate) {
				this.saveUsers();
				this.refreshUsers(); // Kinda redundant line, but if two instances are sharing the file, this will be better
			}	
		}

		/// Check to see if user exists
		public boolean userExists(String id) {
			return this.users.containsKey(id) ? true : false;
		}

		/// Check to see if user exists, forceUpdate will force a file load
		public boolean userExists(String id, boolean forceUpdate) {
			if (forceUpdate) {
				this.saveUsers();
				this.refreshUsers();
			}
			return this.users.containsKey(id) ? true : false;
		}
		
	
	AuthService() {
		this.loadFile();
	}
}