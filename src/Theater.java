
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Theater implements Serializable{
	//private long id;
	//private String title;
	//private ArrayList<String> tags;
	//Serialization stuff ^^

	public Show show;
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
	public Theater(int aNumber, int size1, int size2) {
		this.show = new Show();
		this.number = aNumber;
		this.seats = new Seat[size1][size2];
		int id = 1;
		for(int i=0; i<size1; i++) {
			for(int j=0; j<size2; j++) {
				seats[i][j] = new Seat(id);
				id++;
			}
		}
	}

	public int getNumber() {
		return this.number;
	}

	//Prints out the entire theater
	//	public void printTheater() {
	//		System.out.println("     FRONT     ");
	//		System.out.println("***************");
	//		for(int i=0; i<seats.length; i++) {
	//			for(int j=0; j<seats[0].length; j++) {
	//				if(seats[i][j].getAvailable())
	//					System.out.print(" * ");
	//				else
	//					System.out.print(" X ");
	//			}
	//			System.out.println();
	//			
	//		}
	//		System.out.println("Dimensions are: " +seats.length +"x"+seats[0].length);
	//	}
	
	public void printTheater() {
	    for(int i=0; i<(seats.length*3+3)/2; i++) {
	      System.out.print(" ");
	    }
	    System.out.println("FRONT");
	    for(int i=0; i<(seats[0].length*3+3); i++) {
	      System.out.print("*");
	    }
	    
	    System.out.println();
	    System.out.print("   ");
	    for(int i = 0; i<seats[0].length; i++) {
	      if(i<10)
	        System.out.print(" "+i+" ");
	      else
	        System.out.print(" "+i);
	    }
	    
	    System.out.println();
	    for(int i = 0; i<seats.length; i++) {
	      if(i<10)
	        System.out.print(i+"  ");
	      else
	        System.out.print(i+" ");
	      for(int j = 0; j<seats[0].length; j++) {
	        if(seats[i][j].getAvailable())
	          System.out.print(" * ");
	        else
	          System.out.print(" X ");
	      }
	      System.out.println();
	    }
	    System.out.println("Theatre showing: "+this.show.name);
	  }



	public void getTicket(int row, int col, Payment p) {
		
		if (seats[row][col].getAvailable()) {
			System.out.println("here");
			printReciept(p, this.show, seats[row][col]);
			seats[row][col].setAvailable(false);
		}
		else System.out.println("Ticket unavailable");
		//DO REWARDS AND DISCOUNT

	}
	public void getTicket(int row, int col, Member m) {
		if (seats[row][col].getAvailable()) {
			if (m.getPayment() == null) m.setUpPayment();
			printReciept(m.getPayment(), this.show, seats[row][col]);
			seats[row][col].setAvailable(false);
		}
		else System.out.println("Ticket unavailable");
		//DO REWARDS AND DISCOUNT

	}
	/*
	public void printReciept(Payment payment, Show show, Seat seat) {
		String filename="";
		try {
			String fileContent = "Reciept ID: "+java.time.LocalDate.now()+"\nPayment Information: "+payment.toString()+
					"\nTheatre #"+this.number+" Seat: "+seat.getID()+" to the show: "+show.name+"\n THANK YOU FOR YOUR PURCHASE!";
			filename+=this.number+seat.getID()+show.name+"reciept.txt";
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			writer.write(fileContent);
			writer.close();
			System.out.println("Receipt saved as: "+filename+"\n\n");
		}
		catch (IOException ex) {
			System.out.println(ex);
		}
	
	}
	
	*/
	public void printReciept(Payment payment, Show show, Seat seat) {
		String filename="";
		try {
			String fileContent = "Reciept ID: "+java.time.LocalDate.now()+"\nPayment Information: "+payment.toString()+
					"\nTheatre #"+this.number+" Seat: "+seat.getID()+" to the show: "+show.name+"\n THANK YOU FOR YOUR PURCHASE!";
			filename+=this.number+seat.getID()+show.name+"reciept.txt";
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			writer.write(fileContent);
			writer.close();
			System.out.println("Receipt saved as: "+filename+"\n\n");
		}
		catch (IOException ex) {
			System.out.println(ex);
		}
	}



}