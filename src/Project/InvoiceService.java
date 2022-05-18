package Project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InvoiceService {
    String customerID;
    public ArrayList<Invoice> customerInvoices;

    /// Loads csv file lines into Invoice 
    private void loadFile() {
        try {
            Scanner in = new Scanner(new File("./Invoices.csv"));

            while (in.hasNextLine()) {
                String lineStr = in.nextLine().strip();
                if (lineStr == "") { // End of file
                    break;
                }
                String[] line = lineStr.split(";");
                
                if (line[0] == this.customerID) {
                    this.customerInvoices.add(Invoice.fromString(line[1])); // TODO edit this line so that we can see load line[]'s elems into the Invoice constructor
                }
            }
            in.close();
        } catch (Exception exception) {
            System.out.println(exception.toString());
        }
    }

    ///Add Invoice to File. Make `customerID` the User.id field
    public void addInvoice(String customerID, Invoice invoice) {
        try {
            FileWriter fw = new FileWriter("./Invoices.csv", true); // true appends data
            fw.write(customerID+","+invoice.toString() + "\n");
            fw.close();
        } catch(IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    void InvoiceSystem(String customerID) {
        this.customerID = customerID;
        this.loadFile();
    }

    /// Refreshes `customerInvoices` from the .csv file
    public void refreshInvoices() {
        this.customerInvoices.clear(); // empty it
        this.loadFile();
    }
}