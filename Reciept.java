import java.util.Random;
import java.io.*;

public class Reciept {
  private Payment payment;
  private int id;
  private Random r = new Random();
  public Reciept(Payment aPayment) {
    this.payment = aPayment;
    this.id = r.nextInt(10000);
  }
  public String toString() {
    return "Reciept ID: "+this.id+"\nPayment Information: "+this.payment.toString();
  }
  public void printReciept() {
    try {
    String fileContent = "Reciept ID: "+this.id+"\nPayment Information: "+payment.toString()+
      "\n THANK YOU FOR YOUR PURCHASE!";
    BufferedWriter writer = new BufferedWriter(new FileWriter("c:/Users/mason/CSCE 247/MOVIE/reciept.txt"));
    writer.write(fileContent);
    writer.close();
    }
    catch (IOException ex) {
      System.out.println(ex);
    }
  }
}