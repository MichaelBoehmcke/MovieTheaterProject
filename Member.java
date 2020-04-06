package TicketVendor;

import java.util.ArrayList;
import java.util.Scanner;

public class Member extends Account{
	public int rewards;
	public int age;
	public double discount;
	public ArrayList<Show> hasSeen;
	public Payment paymentInfo;
	
	public Member() {
		age =-1; //Age to to be set on addition to database
		rewards = 0;
		discount = -1;//Discount to be applied on addition to database
		
		
	}
	public void purchaseTicket(Show s) {
		hasSeen.add(s);
	}
	
	public Member(int age, String name, String pass) {
		this.paymentInfo=null;
		hasSeen = new ArrayList<Show>();
		super.role="Member";
		super.username =name;
		super.password =pass;
		this.age = age;
		//determine discount from age
		if (age > 65) discount = .85; //10% discount over 50
		else discount = 1;
	}
	public int getRewards() {
		return rewards;
	}
	public void setRewards(int newRewards) {
		rewards = newRewards;
	}
	public void addReward(int reward) {
		rewards += reward;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double newDiscount) {
		discount = newDiscount;
	}
	@Override
	public String toString() {
		if (this.paymentInfo != null) {
		String info =super.toString();
		info+="Age: "+age+"\nRewards: "+rewards+"\nDiscount: x"+discount+"\n"+paymentInfo.toString();
		return info;
		}
		else {
			String info =super.toString();
			info+="Age: "+age+"\nRewards: "+rewards+"\nDiscount: x"+discount;
			return info;
		}
	}
	public String viewHistory() {
		String temp ="";
		int counter =0;
		for (Show s : hasSeen) {
			temp+="ID: "+counter+" "+s.name+"\n";
			counter++;
		}
		return temp;
	}
	public void setPayment(Payment p) {
		this.paymentInfo = p;
	}
	public Payment getPayment() {
		return paymentInfo;
	}
	public void  setUpPayment() {
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
		paymentInfo= new Payment(type,name,exp,cardNum,cvv);
	}

}
