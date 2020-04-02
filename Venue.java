//This class is for the actual venue itself that contains one or more theaters
public class Venue {
  private String name;
  private String address;
  private Theater[] theaters;
  
  //Constructor with an additional paramater to determine how many theaters the venue has
  public Venue(String aName, String anAddress, int size) {
    this.name = aName;
    this.address = anAddress;
    this.theaters = new Theater[size];
    for(int i=0; i<size; i++) {
      theaters[i] = new Theater(i, 10);
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
}