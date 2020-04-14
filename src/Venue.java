

import java.io.Serializable;
import java.util.ArrayList;

//This class is for the actual venue itself that contains one or more theaters
public class Venue implements Serializable{
	//private long id;
	//private String title;
	//private ArrayList<String> tags;
	//Serialization stuff ^^	

	private String name;
	private String address;
	public Theater[] theaters;

	//Constructor with an additional paramater to determine how many theaters the venue has
	public Venue(String aName, String anAddress, int numTheatres, int rows, int cols) {
		this.name = aName;
		this.address = anAddress;
		this.theaters = new Theater[numTheatres];
		for(int i=0; i<numTheatres; i++) {
			theaters[i] = new Theater(i, cols, rows);
		}
	}

	//Accessors
	public String getName() {
		return this.name;
	}
	public String getAddress() {
		return this.address;
	}
	public String toString() {
		return "Venue: "+this.name+"\nAddress: "+this.address+"\nNumber of Theaters: "+this.theaters.length;
	}
	
	public String showTheatres() {
		String temp="";
		temp+="Theatres in this venue: "+this.name+"\n";
		for (Theater th: theaters) {
			temp+="Theatre Number "+th.getNumber()+" Showing "+th.show.name+"\n";
		}
		return temp;
	}

}