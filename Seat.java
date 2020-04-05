package TicketVendor;

import java.io.Serializable;
import java.util.ArrayList;

public class Seat implements Serializable{
	//private long id;
	//private String title;
	//private ArrayList<String> tags;
	//Serialization stuff ^^
	
	
  private int seatID; //identifies where the seat is in theater and to be printed on ticket
  private boolean available;
  
  //seat constructor, paramater is to set the id. seat is available unless changed
  public Seat(int anID) {
    this.seatID = anID;
     this.available = true;
  }
  
  //accesors
  public int getID() {
    return this.seatID;
  }
  public boolean getAvailable() {
    return this.available;
  }
  public void setID(int anID) {
    this.seatID = anID;
  }
  
  //changes the availability of a seat
  public void setAvailable(boolean anAvailable) {
    this.available = anAvailable;
  }
  public String toString() {
    return "Seat: "+this.seatID+" Available: "+this.available;
  }
}