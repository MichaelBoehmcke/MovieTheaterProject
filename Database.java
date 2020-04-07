
import java.util.ArrayList;
import java.io.*;

public class Database {
	int lastID;
	ArrayList<Account> accounts;
	ArrayList<Venue> venues;
	ArrayList<Show> shows;
	
	public Database() {
		lastID=-1;
		accounts = new ArrayList<Account>();
		venues = new ArrayList<Venue>();
		shows = new ArrayList<Show>();
		
	}
	public ArrayList<Theater> showFinder(Show s) {
		ArrayList<Theater> location = new ArrayList<Theater>();
		int counter =0;
		String temp ="Theatres with the show: "+s.name+"\n";
		for (Venue v: venues) {
			for (Theater th: v.theaters) {
				if (th.show.name.equals(s.name)) {
					location.add(th);
					temp+="Venue: "+v.getName()+" Theatre #"+th.getNumber()+" Access ID: "+counter+"\n";
					counter++;
				}
			}
		}
		
		System.out.println(temp);
		if (counter==0) System.out.println("No Theatres showing.");
		return location;
	}
	public Database(String i) {
		lastID=-1;
		accounts = loadAccounts();
		venues = loadVenues();
		shows = loadShows();
		
	}
	public void printVenues() {
		int counter=0;
		for (Venue v: venues) {
			System.out.println("Venue Access ID: "+ counter+"  Venue: "+v.getName());
		}
	}
	public void printAccounts() {
		for (Account acc:accounts) {
			System.out.println(acc.toString());
		}
	}
	public void printShows() {
		int counter=0;
		for (Show s:shows) {
			System.out.println("Access ID: "+counter+" Show: "+s.name);
			counter++;
		}
	}
	public void addAccount(Account acc) {
		acc.setID(lastID+1);
		lastID++;
		accounts.add(acc);
	}
	public void deleteAccount(Account acc) {
		accounts.remove(acc);
	}
	public void saveAccounts() {
		//System.out.println("here");
		try{
	         FileOutputStream fos= new FileOutputStream("accounts.ser");
	         ObjectOutputStream oos= new ObjectOutputStream(fos);
	         oos.writeObject(accounts);
	         oos.close();
	         fos.close();
	       }catch(IOException ioe){
	            ioe.printStackTrace();
	        }
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Account> loadAccounts() {
		ArrayList<Account> temp = null;
		
		 try {
	         FileInputStream fileIn = new FileInputStream("accounts.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         temp = (ArrayList<Account>) in.readObject();
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	      } catch (ClassNotFoundException c) {
	         System.out.println("Account class not found");
	         c.printStackTrace();
	      }
		
		
		return temp;
	}
	
	public void saveVenues() {
		//System.out.println("here");
		try{
	         FileOutputStream fos= new FileOutputStream("venues.ser");
	         ObjectOutputStream oos= new ObjectOutputStream(fos);
	         oos.writeObject(venues);
	         oos.close();
	         fos.close();
	       }catch(IOException ioe){
	            ioe.printStackTrace();
	        }
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Venue> loadVenues() {
		ArrayList<Venue> temp = null;
		
		 try {
	         FileInputStream fileIn = new FileInputStream("venues.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         temp = (ArrayList<Venue>) in.readObject();
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	      } catch (ClassNotFoundException c) {
	         System.out.println("Venue class not found");
	         c.printStackTrace();
	      }
		
		
		return temp;
	}
	
	public void saveShows() {
		//System.out.println("here");
		try{
	         FileOutputStream fos= new FileOutputStream("shows.ser");
	         ObjectOutputStream oos= new ObjectOutputStream(fos);
	         oos.writeObject(shows);
	         oos.close();
	         fos.close();
	       }catch(IOException ioe){
	            ioe.printStackTrace();
	        }
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Show> loadShows() {
		ArrayList<Show> temp = null;
		
		 try {
	         FileInputStream fileIn = new FileInputStream("shows.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         temp = (ArrayList<Show>) in.readObject();
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	      } catch (ClassNotFoundException c) {
	         System.out.println("Show class not found");
	         c.printStackTrace();
	      }
		
		
		return temp;
	}
	
	public boolean findUsername(String usr) {
		for(Account acc: accounts) {
			if (acc.getUsername().equals(usr)) return true;
		}
		return false;
	}
	public boolean verifyLogin(String usr,String pass) {
		for(Account acc: accounts) {
			if (acc.getUsername().equals(usr) && acc.getPassword().equals(pass)) return true;
		}
		return false;
	}
	public Account returnLoggedIn(String usr,String pass) {
		for(Account acc: accounts) {
			if (acc.getUsername().equals(usr) && acc.getPassword().equals(pass)) return acc;
		}
		return null;//this should never happen, check verifyLogin method first
	}
	
}
