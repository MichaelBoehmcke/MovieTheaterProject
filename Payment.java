import java.io.*;

public class Payment {
  private String cardType;
  private String name;
  private String expDate;
  private long cardNum;
  private int cvv;
  
  public Payment(String aCardType, String aName, String anExpDate, long aCardNum, int aCvv) {
    this.cardType = aCardType;
    this.name = aName;
    this.expDate = anExpDate;
    this.cardNum = aCardNum;
    this.cvv = aCvv;
  }
  
  public String toString() {
    return "Card Type: "+this.cardType+"\nName: "+this.name+"\nExp Date: "+this.expDate+
      "\nCard Number: "+this.cardNum+"\nCVV: "+this.cvv;
  }
}