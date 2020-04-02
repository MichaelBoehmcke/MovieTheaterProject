//Database class with an array of venues that allows the console app to access different venues when necessary
public class VenueDB {
  public static final int MAX_VENUES = 50;
  private Venue[] dataB;
  private Venue current;
  
  //Default construction of the database
  public VenueDB() {
    dataB = new Venue[MAX_VENUES];
  }
  
  //for administrator use: Adds the venue in the parameter to the first null element of the database
  public void addVenue(Venue aVenue) {
    for(int i=0;i<dataB.length;i++) {
      if(dataB[i] == null) {
        dataB[i] = aVenue;
        return;
      }
    }
  }
  
  //for administrator use: removes the venue with the matching name from the database
  public void removeVenue(String aName) {
    for(int i=0;i<dataB.length;i++) {
      if(dataB[i] != null && dataB[i].getName().equalsIgnoreCase(aName)) {
        dataB[i] = null;
        break;
      }
      System.out.println("The venue was not found");
    }
  }
  
  //to be used in driver or ticketing functionality, specifies one venue from the database
  public void setCurrent(String aName) {
    for(int i=0;i<dataB.length;i++) {
      if(dataB[i] != null && dataB[i].getName().equalsIgnoreCase(aName)) {
        this.current = dataB[i];
      }
      System.out.println("The venue was not found");
    }
  }
}