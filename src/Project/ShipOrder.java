package Project;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class ShipOrder {

	//request orders that are "ready"
	public ArrayList<Invoice> customerInvoices;
	public HashMap<String, Integer> stock;
	int orderInput;
	int i = 0;
	
	//look through array of all orders, find the orders that have the status "ready", print them
	public int displayReadyOrders() {
		for(i = 0; i < customerInvoices.size(); i++) {
			if(customerInvoices.get(i).status == "ready") {
				System.out.println("Order: " + i + ", Items: " + customerInvoices.get(i).items);
			}
		}
		return i; //returns i which is used in selectReadyOrder for range control in user input 
	}
	
	//user then will select one of the orders and the input goes into orderInput
	public int selectReadyOrder(int i) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			try {
				System.out.println("Please select an order number to ship: ");
				orderInput = sc.nextInt();
				if((orderInput < 0) || (orderInput > i)) {
					throw new Exception("Number must be within range.");
				}
				break;
			} catch (Exception e) {
				String message = e.getMessage();
				System.out.println(message);
				System.exit(0);
			}
		}
		sc.close();
		return orderInput;
	}
	
    public void removeStock(String itemID, int amountToRemove) {
        int amount = this.stock.get(itemID);
        amount -= amountToRemove;
        this.stock.put(itemID, amount);
    }
    
    public boolean isOutOfStock(String itemID) {
        // If not in system, its out of stock
        if (!this.stock.containsKey(itemID)) {
            return false;
        }
        // Else we check to see if its amount is 0
        return this.stock.get(itemID) == 0 ? true : false;
    }
	
    //splits items, checks the stock count of each and deducts the number from the stock reserve, changes the status to shipped
	public void reserveAndShipOrder(int amountToRemove, int orderInput) {
		String[] itemParts = customerInvoices.get(orderInput).items.split(",");
		for(int j = 0; j < itemParts.length; j++) {
			try {
				int tempStock = stock.get(itemParts[j]) - amountToRemove;
				if(!isOutOfStock(String.valueOf(tempStock))) {
					removeStock(String.valueOf(stock.get(itemParts[j])), amountToRemove);
				}
				else {
					throw new Exception("Item " + itemParts[j] + " is out of stock");
				}
			} catch (Exception e) {
				String message = e.getMessage();
				System.out.println(message);
				System.exit(0);
			}
		}
		customerInvoices.get(orderInput).status = "shipped";
		System.out.println("The order has shipped.");
	}
}
