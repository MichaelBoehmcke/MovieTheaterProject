
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseFrontEnd {
	public static Account user;
	public static Database db;
	public static boolean isGuest;

	public static void main(String[] args) {
		File tmpDir = new File("accounts.ser");
		boolean exists = tmpDir.exists();
		if (exists) {
		db = new Database("Load");//use after first bootup
		db.lastID = db.venues.size()-1;
		}
		else {
			System.out.println("Performing first time setup.\n");
				db = new Database();
				db.accounts.add(generateUser());
				db.venues.add(generateVenue());
				db.shows.add(generateF2());
				db.accounts.add(adminGen());
		
				
		}
		isGuest =!(logIn());
		while (true & !isGuest) {
			homeScreenHelper(user);
		}
		while (true) {
			guestHome();
		}
		
	}

	public static void venueEditor() {
		Scanner sc = new Scanner(System.in);
		boolean loop=true;
		String input="";
		while (loop) {
			db.printVenues();
			int maxInput = db.venues.size()-1;
			System.out.println("Welcome to the Venue Editor! Enter the Access ID for the Venue you would like to edit.\ni.e 3 to access the third venue. Note the acceptable values are 0-"+maxInput+".\nOr type e to go back.");
			if (sc.hasNextLine()) input = sc.nextLine();
			else input="e";
			if (input.equalsIgnoreCase("e")) {
				System.out.println("Going back");
				//sc.close();
				return;
			}
			int accessID = Integer.parseInt(input);
			if (accessID<0 ||accessID>maxInput) {
				System.out.println("Invalid Entry. Starting over");
			}
			else {
				boolean loop2 =true;
				String input2="";
				while (loop2) {
					Venue temp = db.venues.get(accessID);
					System.out.println(temp.toString());
					temp.showTheatres();
					int maxInput2= temp.theaters.length-1;
					System.out.println("Enter Theatre number to edit. Or type e to go back.\nNote the input range is 0-"+maxInput2);
					if (sc.hasNextLine()) input2 = sc.nextLine();
					else input2="e";
					if (input2.equalsIgnoreCase("e")) {
						System.out.println("Going back");
						//sc.close();
						return;
					}
					int theatreID = Integer.parseInt(input2);
					if (theatreID <0 ||theatreID>maxInput2) {
						System.out.println("Invalid Entry. Starting over");
					}
					else {
						Theater th = temp.theaters[theatreID];
						theatreEditor(th);
						return;
					}

				}
			}
		}
	}
	public static void theatreEditor(Theater th) {
		String input="";
		boolean loop =true;
		while (loop) {
			Scanner kbd = new Scanner(System.in);
			System.out.println();
			System.out.print("\n\nWelcome to the theatre editor! What would you like to do"
					+ "\n1. Add a movie, play, or event"
					+ "\n2. Remove a movie, play, or event"
					+ "\n3. go back\n"
					+ "Enter the number of your choice, i.e. 2\n");
			if(kbd.hasNext()) input = kbd.next();
			else input="3";
			if (input.equalsIgnoreCase("e") || input.equalsIgnoreCase("3")) {
				System.out.println("Going back");
				//kbd.close();
				return;
			}
			else if(input.equals("1")) {
				addShow(th);
				//kbd.close();
				return;
			}
		}		
	}

	public static void addShow(Theater th) {
		Show temp;
		Scanner sc = new Scanner(System.in);
		boolean loop=true;
		while (loop) {
			System.out.println("Would you like to:\n1. Add an existing show\n2. Create and add a new show\n3. go back");
			String input = sc.nextLine();
			if (input.equalsIgnoreCase("e") || input.equalsIgnoreCase("3")) {
				System.out.println("Going back");
				//sc.close();
				return;
			}
			else if(input.equals("1")) {
				boolean loop2 =true;
				while (loop2) {
					db.printShows();
					int maxInput= db.shows.size()-1;
					System.out.println("Enter the number of the show you would like to add.\nNote the range is 0-"+maxInput+"\nor type e to go back");
					String input2 = sc.nextLine();
					if (input2.equalsIgnoreCase("e")) {
						System.out.println("Going back");
						//sc.close();
						return;
					}
					int showID = Integer.parseInt(input2);
					if (showID <0 ||showID>maxInput) {
						System.out.println("Invalid Entry. Starting over");
					}
					else {
						th.show=db.shows.get(showID);
						System.out.println("Show added! ");
						//sc.close();
						return;
					}
				}
			}
			else if (input.equals("2")) {
				showBuilder(th);
				return;
			}
		}
	}

	public static void showBuilder(Theater th) {
		boolean loop = true;
		Scanner sc = new Scanner(System.in);
		while (loop) {
			System.out.println("Welcome to the Show Builder!\nFirst, enter the name of the Show.");
			String name = sc.nextLine();
			System.out.println("Now enter the type of show. i.e. play, movie, or other type of event");
			String type = sc.nextLine();
			System.out.println("Enter the genre of the show, it can be more than one.");
			String genre = sc.nextLine();
			System.out.println("Great! Enter a short description of the movie.");
			String description = sc.nextLine();
			System.out.println("Enter the ESRB rating of the movie. i.e. PG-13");
			String ESRB = sc.nextLine();
			System.out.println("Enter the runtime in minutes.");
			int runtime = Integer.parseInt(sc.nextLine());
			System.out.println("Enter the showtime for this show.");
			String showTime= sc.nextLine();
			Show temp = new Show(name, type, description, genre, ESRB, runtime,showTime);
			System.out.println(temp.toString()+"\nThis is the show you created. Confirm addition (Y), start over(N), or exit without saving(E).");
			String input = sc.nextLine();
			if (input.equalsIgnoreCase("y")) {
				db.shows.add(temp);
				th.show=temp;
				System.out.println(temp.name+" Show added.");
				loop=false;
			}
			else if(input.equalsIgnoreCase("n")) {
				System.out.println("Starting over.");
			}
			else if(input.equalsIgnoreCase("e")) {
				System.out.println("Exiting without saving.");
				loop=false;
			}
		}
	}

	public static void showBuilder() {
		boolean loop = true;
		Scanner sc = new Scanner(System.in);
		while (loop) {
			System.out.println("Welcome to the Show Builder!\nFirst, enter the name of the Show.");
			String name = sc.nextLine();
			System.out.println("Now enter the type of show. i.e. play, movie, or other type of event");
			String type = sc.nextLine();
			System.out.println("Enter the genre of the movie, it can be more than one.");
			String genre = sc.nextLine();
			System.out.println("Great! Enter a short description of the movie.");
			String description = sc.nextLine();
			System.out.println("Enter the ESRB rating of the movie. i.e. PG-13");
			String ESRB = sc.nextLine();
			System.out.println("Enter the runtime in minutes.");
			int runtime = Integer.parseInt(sc.nextLine());
			System.out.println("Enter the showtime for this show.");
			String showTime= sc.nextLine();
			Show temp = new Show(name, type, description, genre, ESRB, runtime,showTime);
			System.out.println(temp.toString()+"\nThis is the show you created. Confirm addition (Y), start over(N), or exit without saving(E).");
			String input = sc.nextLine();
			if (input.equalsIgnoreCase("y")) {
				db.shows.add(temp);
				//th.show=temp;
				System.out.println(temp.name+" Show added.");
				loop=false;
			}
			else if(input.equalsIgnoreCase("n")) {
				System.out.println("Starting over.");
			}
			else if(input.equalsIgnoreCase("e")) {
				System.out.println("Exiting without saving.");
				loop=false;
			}
		}
	}

	public static void homeScreenHelper(Account usr) {
		//String role = usr.getRole();
		if (isGuest) {
			guestHome();

		}
		String role = usr.getRole();
		if (role.equalsIgnoreCase("member")) {
			memberHome();
		}
		else if (role.equalsIgnoreCase("Admin")) {
			adminHome();
		}
		else System.out.println("Login Machine Broke");
	}
	public static void guestHome() {
		Scanner sc = new Scanner(System.in);
		//System.out.println("Welcome back, "+user.getUsername()+"!");
		String options ="";
		options+= "1. View Shows\n"
				+ "2. View Venues\n"
				+ "3. logout\n"
				+ "Enter the number corresponding to your desired action ";
		System.out.println(options);
		String input=sc.nextLine();
		if (input.equals("1")) {
			showViewer();
		}
		else if (input.equals("2")) {
			venueViewer();
		}
		else if (input.equals("3")) {
			System.out.println("goodbye");
			save();
			System.exit(0);
		}
	}
	public static void memberHome() {
		Scanner sc = new Scanner(System.in);
		//System.out.println("Welcome back, "+user.getUsername()+"!");
		String options ="";
		options+= "1. View Shows\n"
				+ "2. View Venues\n"
				+ "3. View Account Info\n"
				+ "4. logout\n"
				+ "Enter the number corresponding to your desired action ";
		System.out.println(options);
		String input=sc.nextLine();
		if (input.equals("1")) {
			showViewer();
		}
		else if (input.equals("2")) {
			venueViewer();
		}
		else if(input.equals("3")) {
			Member temp = (Member) user;
			memberInfo(temp);
			//System.out.println(temp.toString());
			//System.out.println(temp.viewHistory());
		}
		else if (input.equals("4")) {
			System.out.println("goodbye");
			save();
			System.exit(0);
		}
	}

	public static void memberInfo(Member m) {
		Scanner sc = new Scanner(System.in);
		System.out.println(m.toString());
		System.out.println(m.viewHistory());
		if (m.hasSeen.size()>0) {
			System.out.println("To review or rate a show you've seen, enter the access number. Or enter e to go back");
			String input= sc.nextLine();
			if (input.equalsIgnoreCase("e")) return;
			int index=Integer.parseInt(input);
			if((index >=0 && index <m.hasSeen.size()-1)||index==0){
				comment(m, m.hasSeen.get(index));
			}
			else System.out.println("Something went wrong...");
		}
	}
	public static void comment(Member m, Show s) {
		Scanner sc = new Scanner(System.in);
		System.out.println(s.toString());
		System.out.println("Would you like to: \n"
				+ "1. Review this show\n"
				+ "2. Rate this show\n"
				+ "3. go back");
		String input= sc.nextLine();
		if (input.equalsIgnoreCase("e") || input.equals("3")) {
			return;
		}
		else if (input.equals("1")) {
			System.out.println("Type your review below do not hit enter until the review is finalized:");
			String review = sc.nextLine();
			String finalized= adjustDescription(review);
			s.reviews.add(finalized);
		}
		else if (input.equals("2")) {
			s.addRating();
		}
		else {
			System.out.println("Command not found. Try again.");
			comment(m,s);
		}
	}
	public static void adminHome() {
		Scanner sc = new Scanner(System.in);
		//System.out.println("Welcome back, "+user.getUsername()+"!");
		String options ="";
		options+= "1. Show builder\n"
				+ "2. Venue Builder\n"
				+ "3. View Account Info\n"
				+ "4. Venue Editor\n"
				+ "5. logout\n"
				+ "Enter the number corresponding to your desired action ";
		System.out.println(options);
		String input=sc.nextLine();
		if (input.equals("1")) {
			showBuilder();
		}
		else if (input.equals("2")) {
			venueBuilder();
		}
		else if(input.equals("3")) {
			Administrator temp = (Administrator) user;
			System.out.println(temp.toString());
		}
		else if(input.equals("4")) {
			venueEditor();
		}
		else if (input.equals("5")) {
			System.out.println("goodbye");
			save();
			System.exit(0);
		}
	}
	public static void venueViewer() {
		db.printVenues();
		String input="";
		Scanner sc = new Scanner(System.in);
		int maxInput = db.venues.size()-1;
		System.out.println("Enter the Access ID for the Venue you would like to know more about.\ni.e 3 to access the third venue. Note the acceptable values are 0-"+maxInput+".\nOr type e to go back.");
		if (sc.hasNextLine()) input = sc.nextLine();
		else input="e";
		if (input.equalsIgnoreCase("e")) {
			System.out.println("Going back");
			//sc.close();
			return;
		}
		int accessID = Integer.parseInt(input);
		if (accessID<0 ||accessID>maxInput) {
			System.out.println("Invalid Entry. Starting over");
		}
		else {
			boolean loop2 =true;
			String input2="";
			while (loop2) {
				Venue temp = db.venues.get(accessID);
				System.out.println(temp.toString());
				System.out.println(temp.showTheatres());
				int maxInput2= temp.theaters.length-1;
				System.out.println("Enter Theatre to view. Or type e to go back.\nNote the input range is 0-"+maxInput2);
				if (sc.hasNextLine()) input2 = sc.nextLine();
				else input2="e";
				if (input2.equalsIgnoreCase("e")) {
					System.out.println("Going back");
					//sc.close();
					return;
				}
				int theatreID = Integer.parseInt(input2);
				if (theatreID <0 ||theatreID>maxInput2) {
					System.out.println("Invalid Entry. Starting over");
				}
				else {
					Theater th = temp.theaters[theatreID];
					viewTickets(th);
					return;
				}

			}
		}
	}
	public static void showViewer() {
		Scanner sc = new Scanner(System.in);
		db.printShows();
		System.out.println("Enter the Access ID of a show if you want more info. Or enter E to go back");
		String input = sc.nextLine();
		if (input.equalsIgnoreCase("E")) {
			//sc.close();
			return;
		}
		int accessID=Integer.parseInt(input);
		Show temp = db.shows.get(accessID);
		System.out.println(temp.toString());
		ArrayList<Theater> theaterList =db.showFinder(temp);
		System.out.println("Enter access ID of the theatre you'd like to view more. Or enter E to go back.");
		String input2 = sc.nextLine();
		if (input2.equalsIgnoreCase("e")) {
			//sc.close();
			//System.out.println("here");
			return;
		}
		int accessID2=Integer.parseInt(input2);
		viewTickets(theaterList.get(accessID2));
		//sc.close();


	}
	public static void viewTickets(Theater th) {
		th.printTheater();
		System.out.println("X means unavilable, * means available. \nWould you like to buy a ticket?Y/N");
		Scanner sc =new Scanner(System.in);
		String input = sc.nextLine();
		if (input.equalsIgnoreCase("y")) {
			System.out.println("How many would you like?");
			String strTix = sc.nextLine();
			int numTix = Integer.parseInt(strTix);
			purchase(numTix, th);
			//sc.close();
		}
	}
	public static void purchase(int n, Theater th) {
		if (!isGuest) {
			Member temp = (Member) user;
		Scanner sc = new Scanner(System.in);
		for (int i=0;i<n;i++) {
			th.printTheater();
			System.out.println("Enter row for ticket #"+(i+1));
			int row = sc.nextInt();
			System.out.println("Enter column for ticket #"+(i+1));
			int col = sc.nextInt();
			th.getTicket(row, col, temp);
			temp.purchaseTicket(th.show);

		}
		//th.printTheater();
		}
		else {
			Payment p = guestPayment();
			Scanner sc = new Scanner(System.in);
			for (int i=0;i<n;i++) {
				th.printTheater();
				System.out.println("Enter row for ticket #"+(i+1));
				int row = sc.nextInt();
				System.out.println("Enter column for ticket #"+(i+1));
				int col = sc.nextInt();
				
				th.getTicket(row, col, p);
				//temp.purchaseTicket(th.show);

			}
			th.printTheater();
			
		}

	}

	public static void venueBuilder() {
		boolean loop = true;
		Scanner sc = new Scanner(System.in);
		while (loop) {
			System.out.println("Welcome to the Venue Builder!\nFirst, enter the name of the venue.");
			String name = sc.nextLine();
			System.out.println("Now enter the address.");
			String address = sc.nextLine();
			System.out.println("Great! Enter the number of theatres in this venue.");
			int numTheatres = Integer.parseInt(sc.nextLine());
			System.out.println("Enter the number of rows of seats in each theatre");
			int rows = Integer.parseInt(sc.nextLine());
			System.out.println("Enter the number of length of the rows in each theatre");
			int cols = Integer.parseInt(sc.nextLine());

			Venue temp = new Venue(name, address, numTheatres, rows, cols);
			System.out.println(temp.toString());
			temp.theaters[0].printTheater();
			System.out.println("There is the venue info and an example of one of the "+numTheatres+" theatres."
					+ "\nConfirm addition (Y), Start over (N), Exit without saving(E)");
			String input = sc.nextLine();
			if (input.equalsIgnoreCase("y")) {
				db.venues.add(temp);
				System.out.println(temp.getName()+" venue added.");
				loop=false;
			}
			else if(input.equalsIgnoreCase("n")) {
				System.out.println("Starting over.");
			}
			else if(input.equalsIgnoreCase("e")) {
				System.out.println("Exiting without saving.");
				loop=false;
			}
		}
	}
   //GET TESTED HERE
	public static boolean logIn() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to MockBuster!! Would you like to log in?\nY/N\nor Create account C");
		String input = sc.nextLine();
		if (input.equalsIgnoreCase("n")) {
			//sc.close();
			System.out.println("Continuing as guest");
			return false;
		}
		else if (input.equalsIgnoreCase("c")) {
			System.out.println("What is your username?");
			String name = sc.nextLine();
			System.out.println("What is your password?");
			String pass = sc.nextLine();
			System.out.println("How old are you?");
			int age = Integer.parseInt(sc.nextLine());
			Member newMember= new Member(age,name,pass);
			db.addAccount(newMember);
			user = newMember;
			return true;

		}
		else if (input.equalsIgnoreCase("y")) {
			boolean usrname =false;
			while (!usrname) {
				System.out.println("Enter username below:");
				String usr = sc.nextLine();
				if (db.findUsername(usr)) {
					System.out.println("Enter password below:");
					String pass = sc.nextLine();
					if (db.verifyLogin(usr, pass)) {
						user = db.returnLoggedIn(usr, pass);
						//sc.close();
						System.out.println("Logged in! Welcome "+usr);
						return true;
					}
					else {
						System.out.println("Password does not match. Try again (Y) or continue as guest(N)?");
						String tryAgain = sc.nextLine();
						if (tryAgain.equalsIgnoreCase("N")) {
							System.out.println("Continuing as guest");
							//sc.close();
							return false;
						}
						//else passFail=true;
					}
				}
				else {
					System.out.println("Username does not match. Try again (Y) or continue as guest(N)?");
					String tryAgain = sc.nextLine();
					if (tryAgain.equalsIgnoreCase("N")) {
						System.out.println("Continuing as guest");
						//sc.close();
						return false;
					}
				}
			}
		}
		System.out.println("thought this was unreachable...error");
		//sc.close();
		return false;
	}

	public static Payment guestPayment() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Setting up Payment. Enter card type");
		String type = sc.nextLine();
		System.out.println("Enter cardholder name");
		String name = sc.nextLine();
		System.out.println("Enter expiration date");
		String exp = sc.nextLine();
		System.out.println("Enter card number");
		long cardNum = Long.parseLong(sc.nextLine());
		System.out.println("Enter cvv");
		int cvv = Integer.parseInt(sc.nextLine());
		return new Payment(type,name,exp,cardNum,cvv);
	}
	public static Payment memberPayment(Member m) {
		if (m.getPayment()==null) {
			m.setPayment(guestPayment());
			return m.getPayment();		
			}
		else return m.getPayment();
	}



	//default objects for testing and stuff
	public static Show generateF2() {
		String showTime="7:30pm";
		Show Frozen2;
		String name ="Frozen II";
		String type ="Movie";
		String genre="Animation, Advenure, Comedy";
		String ESRB="PG";
		int runtime=103;
		String description ="Anna, Elsa, Kristoff, Olaf and Sven leave Arendelle to travel to an ancient, autumn-bound forest of an enchanted land. They set out to find the origin of Elsa's powers in order to save their kingdom."; 

		Frozen2 = new Show(name, type, description, genre, ESRB, runtime,showTime);
		return Frozen2;
	}
	public static Venue generateVenue() {
		return new Venue("CinnamonMark", "1000 Palladium Dr Ottawa, ON K2V 1A4", 6,10,12);

	}
	public static Administrator adminGen() {
		return new Administrator();
	}
	public static Member generateUser() {
		return new Member(20,"joebhoi","Cocksby90");
	}

	public static void save() {
		//System.out.println("Saved");
		db.saveAccounts();
		db.saveShows();
		db.saveVenues();
	}
	public static String adjustDescription(String desc) {
		String temp = desc;
		String adjusted="";
		for(int i=0;i<temp.length();i++) {
			adjusted+=temp.charAt(i);
			if(adjusted.length() %100==0) adjusted+="\n";
		}
		return adjusted;
	}
}
