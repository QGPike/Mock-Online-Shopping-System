package Project;

import java.util.ArrayList;

/// Probably best use this as a singleton
class Cart {

	ArrayList<OrderItem> itemsInCart = new ArrayList<OrderItem>();
	float total=0;
  Cart() {

  }

  /// Adds item to cart
  /// Does not check to see if item is in stock
  public void addItemToCart(OrderItem item) {
      this.itemsInCart.add(item);
      //System.out.println(this.itemsInCart);
  }
  public void removeItemToCart(OrderItem item) {
	  boolean once=true;
	  int i=0;
	  
      while(once) {
    	  if(itemsInCart.get(i).itemID==item.itemID) {
    		  itemsInCart.remove(i);
    		  once=false;
    	  }
    	  i++;
      }
      
      
  }
  public float addTotalPrice(float itemPrice) {
	  
     
      return this.total=this.total+itemPrice;
      
  }
public float removeTotalPrice(float itemPrice) {
	  
      
      return this.total=this.total-itemPrice;
      
  }
  
public float totalPrice() {
	  
      
      return this.total;
      
  }
  /// Removes all items from cart
  public void clearCart() {
      this.itemsInCart.clear();
  }

  /// Returns `boolean` if cart is empty
  public boolean isCartEmpty() {
      return this.itemsInCart.isEmpty();
  }

  /// Removes last `OrderItem` added to cart
  /// Essentially pops like a stack
  public void removeLastItem() {
      this.itemsInCart.remove(this.itemsInCart.size() - 1);
  }


  /// Takes every item in cart and makes a long string. Each OrderItem seperated by a ';'
  /// If you split(';') this returned string, the last elem of the array may be an empty string
  public String toString() {
      String ret = "";
      for (OrderItem item : this.itemsInCart) {
          ret = ret + item.toString() + ",";
      }
      return ret;
  }
}