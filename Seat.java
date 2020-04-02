public class Seat {
  private int id; //identifies where the seat is in theater and to be printed on ticket
  private boolean available;
  
  //seat constructor, paramater is to set the id. seat is available unless changed
  public Seat(int anID) {
    this.id = anID;
     this.available = true;
  }
  
  //accesors
  public int getID() {
    return this.id;
  }
  public boolean getAvailable() {
    return this.available;
  }
  public void setID(int anID) {
    this.id = anID;
  }
  
  //changes the availability of a seat
  public void setAvailable(boolean anAvailable) {
    this.available = anAvailable;
  }
  public String toString() {
    return "Seat: "+this.id+" Available: "+this.available;
  }
}