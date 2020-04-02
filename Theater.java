public class Theater {
  private int number; //Theater number for identification
  private Seat[][] seats; //2d array of Seats to represent the theater
  
  //constructor for theater, parameters for theater number and the size of the theater
  public Theater(int aNumber, int size) {
    this.number = aNumber;
    this.seats = new Seat[size][size];
    int id = 1;
    for(int i=0; i<size; i++) {
      for(int j=0; j<size; j++) {
        seats[i][j] = new Seat(id);
        id++;
      }
    }
  }
  
  //Prints out the entire theater
  public void printTheater() {
    System.out.println("     FRONT     ");
    System.out.println("***************");
    for(int i=0; i<seats.length; i++) {
      for(int j=0; j<seats.length; j++) {
        if(seats[i][j].getAvailable())
          System.out.print(" * ");
        else
          System.out.print(" X ");
      }
      System.out.println();
    }
  }
}