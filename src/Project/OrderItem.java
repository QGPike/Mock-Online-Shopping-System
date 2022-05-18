package Project;

public class OrderItem {
    String itemID;
    String itemName;
    String itemPrice;
  
    // TODO put more fields here as needed
    OrderItem(String itemID, String itemName, String itemPrice) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }
   

    /// Outputs a semicolon delimitted string for .csv storage
    public String toString() {
        return itemID + "," + itemName+","+itemPrice;
    }
    /// Takes in a semicolon delimitted string from a .csv file, and creates an item
    public static OrderItem fromString(String s) {
        String line[] = s.split(",");
        return new OrderItem(line[0], line[1],line[2]);
    }
}