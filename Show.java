package TicketVendor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Show implements Serializable{
	//private long id;
	//private String title;
	//private ArrayList<String> tags;
	//Serialization stuff ^^
	
	public String name;
	public String type;
	public String description;
	public String genre;
	public String ESRB;
	public double rating;
	public int ratingCount;
	public int runtime;
	public boolean adult;
	public String showTime;
	public ArrayList<String> reviews;//=new ArrayList<String>();
	
	public Show(String name, String type, String description, String genre, String ESRB, int runtime, String showTime) {
		this.showTime=showTime;
		this.name=name;
		this.type=type;
		this.description=adjustDescription(description);
		this.genre=genre;
		if (genre.equalsIgnoreCase("r") || genre.equalsIgnoreCase("NC-17")) adult = true;
		else adult =false;
		this.ESRB=ESRB;
		this.runtime=runtime;
		this.rating=0;
		this.ratingCount=0;
		this.reviews= new ArrayList<String>();
	}
	public Show() {
		this.name="No show yet.";
		this.type="N/A";
		this.description="Nothing to say about nothing";
		this.genre="N/A";
		this.ESRB="N/A";
		this.adult = false;
		this.runtime=0;
		this.rating=0;
		this.ratingCount=0;
		this.reviews = null;
	}
	public String toString() {
		String temp ="|* ";
		temp+="~"+name+"~ || "+genre+" || "+ESRB+" || "+checkRating()+" || Runtime: "+runtime+
				"min || "+type+" || "+" ShowTime: "+this.showTime+" *|\n"+description+"\n"+readReviews();
		return temp;
				
	}
	public String checkRating() {
		if (ratingCount == 0) {
			return "No ratings yet";
		}
		else return String.valueOf(rating)+" Stars";
	}
	public void addRating() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of stars (1-5) you wish to give the movie: "+this.name);
		double userRating = Double.parseDouble(sc.nextLine());
		if (userRating < 1) userRating=1;
		else if(userRating > 5) userRating=5;
		double totalPoints = this.rating * this.ratingCount;
		totalPoints+=userRating;
		this.ratingCount++;
		this.rating= totalPoints/this.ratingCount;
	}
	public String adjustDescription(String desc) {
		String temp = desc;
		String adjusted="";
		for(int i=0;i<temp.length();i++) {
			adjusted+=temp.charAt(i);
			if(adjusted.length() %100==0) adjusted+="\n";
		}
		return adjusted;
	}
	public String readReviews() {
		String temp ="";
		for(String s: reviews) {
			temp+= adjustDescription(s)+"\n";
		}
		return temp;
	}
}
