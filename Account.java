
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Account implements Serializable{
	//private long id;
	//private String title;
	//private ArrayList<String> tags;
	//Serialization stuff ^^
	
	
	public String role;
	public String username;
	public String password;
	
	//default set to -1.  ID to be added when account is added to the database and should always be positive
	public int accountID =-1;
	public String getRole() {
		System.out.println(role);
		return role;
	}
	public void changeUsername(String newname) {
		System.out.println("Old Username: "+username);
		username=newname;
		System.out.println("Changed username to: "+username);
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void changePassword(String newpass) {
		System.out.println("Old password: "+password);
		password=newpass;
		System.out.println("Changed password to: "+password);
	}
	public int getID() {
		return accountID;
	}
	public void setID(int newid) {
		//System.out.println("Old ID: ");
		accountID = newid;
		//System.out.println("New ID: "+id);
	}
	public String toString() {
		String info ="";
		info+= "Role: "+role +"\n";
		info+="Username: "+username+"\nPassword: "+password+"\nID: "+accountID+"\n";
		return info;
	}
}
