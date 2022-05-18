package Project;

//TODO add fields for this
//The system displays the order invoice, including the order date, items, total amount, and payment information
public class Invoice {
 
 String orderDate;
 String totalAmount;
 PaymentInformation paymentInfo;
 String items; // Stringified cart
 String status;

 Invoice( String orderDate, String totalAmount, String items, String status) {
     
     this.orderDate = orderDate;
     this.totalAmount = totalAmount;
     this.items = items;
     this.status = status;
 }

 /// Creates a comma seperated String from current object
public String toString() {
     return  this.orderDate + "," + this.totalAmount + "," + this.items + "," + this.status;
 }
 /// Creates a `Invoice` object from a comma seperated string
 static Invoice fromString(String s) {	
    String[] line = s.split(",");
    return new Invoice(line[0], line[1], line[2], line[3]);
 }
}