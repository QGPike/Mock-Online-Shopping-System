package Project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StockService {     
    public HashMap<String, Integer> stock; // itemid -> amount in stock
    public static StockService stockService = null;
    /// Loads csv file lines into Stock 
    private void loadFile() {
        try {
            Scanner in = new Scanner(new File("./Stock.csv"));

            while (in.hasNextLine()) {
                String lineStr = in.nextLine().strip();
                if (lineStr == "") { // End of file
                    break;
                }
                String[] line = lineStr.split(",");
                
                // Get item ID and its stock, and load into hashmap
                this.stock.put(line[0], Integer.parseInt(line[1]));
                
            }
            in.close();
        } catch (Exception exception) {
            System.out.println(exception.toString());

        }
    }



    /// Saves the stock count in memory to file
    public void saveStock() {
        try {   
            FileWriter fw = new FileWriter("./Stock.csv"); // true appends data
            for ( Map.Entry<String,Integer> entry  : stock.entrySet()) {
                fw.write((String)entry.getKey() + "," +String.valueOf(entry.getValue()) + "\n");
            }
            fw.close();
        } catch(IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }

    /// A central singleton so you can sync stock across all views
    public StockService getSingleton() {
        if (stockService == null) {
            stockService = new StockService();
        }
        return stockService;
    }

    StockService() {
        this.loadFile();
    }

    /// Refreshes `stock` from the .csv file
    public void refreshInvoices() {
        this.stock.clear(); // empty it
        this.loadFile();
    }

     /// Subtract from amount
     public void removeStock(String itemID, int amountToRemove) {
        int amount = this.stock.get(itemID);
        amount -= amountToRemove;
        this.stock.put(itemID, amount);

    }
    /// Add to amount
    public void addStock(String itemID, int amountToAdd) {
        int amount = this.stock.get(itemID);
        amount += amountToAdd;
        this.stock.put(itemID, amount);
    }
    /// Returns true if out of stock
    public boolean isOutOfStock(String itemID) {
        // If not in system, its out of stock
        if (!this.stock.containsKey(itemID)) {
            return false;
        }
        // Else we check to see if its amount is 0
        return this.stock.get(itemID) == 0 ? true : false;
    }

//    public boolean isEnoughInStock(String itemID, int amount) {
//        // If not in system, its out of stock
//        if (!this.stock.containsKey(itemID)) {
//            return false;
//        }
//        int amount =
//    }
}
