package Project;
import javax.swing.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.awt.event.*; 

public class OrderManager {
	
	public void selectItem(User U)
	{
		JFrame frame = new JFrame("Store R Us Catalog");
	    JList<Product> Catalog_list = new JList<>();
	    JList<Product> Cart_list = new JList<>();
	    DefaultListModel<Product> model_cat = new DefaultListModel<>();
	    DefaultListModel<Product> model_cart = new DefaultListModel<>();
	    JLabel label1 = new JLabel();
	    JLabel label2 = new JLabel();
	    JLabel label3= new JLabel();
	    JLabel label4= new JLabel();
	    JLabel label5 = new JLabel();
	    JLabel label6 = new JLabel();
	    JLabel label7 = new JLabel();
	    JButton addCartButton = new JButton("Add to Cart");
	    JButton removeCartButton = new JButton("Remove from Cart");
	    JButton makeOrderButton = new JButton("Make Order");
	    JButton exitButton = new JButton("Exit Catalog");
	    Cart Cart = new Cart();
		try {
    		Catalog_list.setModel(model_cat);
            Scanner in = new Scanner(new File(".\\Catalog.csv"));
            while (in.hasNextLine()) {
                String[] line = in.nextLine().strip().split(",");
                
                model_cat.addElement(new Product(line[0], line[1], line[2],line[3]));
                
                }
            }
            
    	 catch (Exception exception) {
            System.out.println(exception.toString());

        }
		JScrollPane catList = new JScrollPane(Catalog_list);
        JScrollPane cartList = new JScrollPane(Cart_list);
        label1.setBounds(200, 10, 200, 100);
        label2.setBounds(200, 50, 200, 100);
        label3.setBounds(200, 90, 200, 100);
        label4.setBounds(200, 130, 300, 100);
        label5.setBounds(10, 10, 100, 20);
        label5.setText("Catalog");
        label6.setBounds(875,10,100,20);
        label6.setText("Cart");
        label7.setBounds(725,275,200,20);
        label7.setText("Total Cost of Items in Cart: ");
        catList.setBounds(10, 30, 100, 425);
        cartList.setBounds(875, 30, 100, 225);
        makeOrderButton.setBounds(150,400,150,50);
        addCartButton.setBounds(350, 400, 150, 50);
        removeCartButton.setBounds(550, 400, 150, 50);
        exitButton.setBounds(750, 400, 150, 50);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(catList);
        frame.add(cartList);
        frame.add(label1);
        frame.add(label2);
        frame.add(label3);
        frame.add(label4);
        frame.add(label5);
        frame.add(label6);
        frame.add(label7);
        frame.add(makeOrderButton);
        frame.add(addCartButton);
        frame.add(exitButton);
        frame.add(removeCartButton);
        
        frame.setSize(1000,500);
            
	    frame.setLayout(null);    
        frame.setVisible(true);	
        Catalog_list.getSelectionModel().addListSelectionListener(e -> {
            Product p = Catalog_list.getSelectedValue();
            label1.setText("Store: " + p.getName());
            label2.setText("Regular price: "+p.getPrice());
            label3.setText("Premium price: "+p.getPricePremium());
            label4.setText("Description: "+p.getDesc());
        });
        addCartButton.addActionListener(e -> {
    		Product c = Catalog_list.getSelectedValue();
    		if(c!=null) {
    			
    			Cart_list.setModel(model_cart);
    			model_cart.addElement(new Product(c.getID(), c.getName(),c.getPrice(),c.getDesc()));
    			OrderItem item = new OrderItem(c.getID(),c.getName(),c.getPrice());
    			Cart.addItemToCart(item);
    			label7.setText("Total Cost of Items in Cart: "+Cart.addTotalPrice(Float.parseFloat(item.itemPrice)));
    			
    		}
            
           
        }
    	
    	);
        removeCartButton.addActionListener(e -> {
    		Product c = Cart_list.getSelectedValue();
    		if(c!=null) {
    			
    			Cart_list.setModel(model_cart);
    			model_cart.removeElement(c);
    			OrderItem item = new OrderItem(c.getID(),c.getName(),c.getPrice());
    			Cart.removeItemToCart(item);

    			label7.setText("Total Cost of Items in Cart: "+Cart.removeTotalPrice(Float.parseFloat(item.itemPrice)));
    			
    		}
            
           
        }
    	
    	);
		makeOrderButton.addActionListener(e -> {
    		if(Cart.isCartEmpty()) {
    			label1.setText("Please add store to cart.");
    			label2.setText("");
    			label3.setText("");
    			label4.setText("");
    		}else {
    			frame.dispose();
    			MakeOrder(Cart,model_cart,U);
    		}
			
			
           
        }
    	
    	);
		exitButton.addActionListener(e -> {			
			OSS_HUB OH= new OSS_HUB();
			OH.HUB(U);
			frame.dispose();
        }
    	
    	);
	} 
	@SuppressWarnings("unchecked")
	public void MakeOrder(Cart Cart,DefaultListModel model_cart,User U)
	{
		JFrame frame = new JFrame("Store R Us Make Order");	    
	    JList<Product> Cart_list = new JList<>();
	    String[] choices = { "Mail($3.00 charge)","In Store Pickup"};
	    JComboBox<String> deliveryOptions = new JComboBox<String>(choices);
	   
	    float totalPrice= Cart.addTotalPrice(3);
	    
	    JLabel label1 = new JLabel();
	    JLabel label2 = new JLabel();
	    JLabel label3= new JLabel();
	    JLabel label4= new JLabel();
	    JLabel label5 = new JLabel();
	    JLabel label6 = new JLabel();
	    JButton confirmButton = new JButton("Confirm Order");
	    JButton removeCartButton = new JButton("Remove from Cart");
	    JButton exitButton = new JButton("Back to Catalog");
		Cart_list.setModel(model_cart);
        JScrollPane cartList = new JScrollPane(Cart_list);
        label1.setBounds(200, 10, 200, 100);
        label2.setBounds(150, 140, 200, 100);
        label3.setBounds(150, 90, 500, 100);
        label3.setText("Total Cost of Items in Cart: "+totalPrice);
        label4.setBounds(150, 10, 100, 20);
        label4.setText("Delivery Options:");
        label5.setBounds(10, 10, 100, 20);
        label5.setText("Cart");
        cartList.setBounds(10, 30, 100, 425);
        deliveryOptions.setBounds(150,50,200,50);
       
        confirmButton.setBounds(350, 400, 150, 50);
        removeCartButton.setBounds(550, 400, 150, 50);
        exitButton.setBounds(750, 400, 150, 50);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(deliveryOptions);
        frame.add(cartList);
        frame.add(label1);
        frame.add(label2);
        frame.add(label3);
        frame.add(label4);
        frame.add(label5);
        frame.add(label6);
        
        frame.add(confirmButton);
        frame.add(exitButton);
        frame.add(removeCartButton);
        
        frame.setSize(1000,500);
            
	    frame.setLayout(null);    
        frame.setVisible(true);	
       
        deliveryOptions.addItemListener(new ItemListener() {
            @Override
            
            public void itemStateChanged(ItemEvent e) {
            	float totalPrice;
            	String p = (String) deliveryOptions.getSelectedItem();
            	
            	
            	if(e.getStateChange()==2) {
            		if(p.equals("Mail($3.00 charge)")) {
                		
                		totalPrice= Cart.addTotalPrice(3);
                		
                	}else {
                		
                		totalPrice= Cart.removeTotalPrice(3);
                	}
                	label3.setText("Total Cost:"+totalPrice);
            	}
            	
            }
        });
        
        
        removeCartButton.addActionListener(e -> {
    		Product c = Cart_list.getSelectedValue();
    		if(model_cart.size()!=1 &&c!=null) {
    			Cart_list.setModel(model_cart);
    			model_cart.removeElement(c);
    			OrderItem item = new OrderItem(c.getID(),c.getName(),c.getPrice());
    			Cart.removeItemToCart(item);
    			label3.setText("Total Cost of Items in Cart: "+Cart.removeTotalPrice(Float.parseFloat(item.itemPrice)));
    			
    		}else if(c!=null) {
    			label2.setText("Can't remove last item from cart while on ordering screen. Please return to Catalog.");
    		}
            
           
        }
    	
    	);
        /// TODO push to the buffer
        confirmButton.addActionListener(e -> {
        	String orderDate = new SimpleDateFormat("MM/dd/yy").format(new java.util.Date());
        	String allItems = Cart.itemsInCart.get(0).itemName;
        	for(int i=1; i <= Cart.itemsInCart.size()-1;i++) {
        		allItems= allItems+";" + Cart.itemsInCart.get(i).itemName;
        	}
            
            Invoice I = new Invoice(orderDate,String.valueOf(Cart.total),allItems,"Ordered");
            InvoiceService IS = new InvoiceService();
            IS.addInvoice(U.id,I);
            confirmButton.setVisible(false);
            removeCartButton.setVisible(false);
            label3.setText("Your Order has been place! Thank you for your business, "+U.name+"!");
            label4.setVisible(false);
            label5.setVisible(false);

            /// USER U
            /// Cart.totalAmount()
            MessageBuffer buffer = MessageBuffer.getBuffer();
            buffer.send(U.paymentCard, Cart.total);
        }
    	
    	);
        exitButton.addActionListener(e -> {
        	frame.dispose();
        	selectItem(U);           
        }
    	
    	);
	} 
 
    private class Product {
    	String id;
        String name;
        String price;
        String desc;
        String priceprem;
        public Product(String id, String name, String price, String desc) {
        	this.id= id;
            this.name = name;
            this.price = price;
            this.desc = desc;
            Float Fnum = Float.parseFloat(price)-1;
        	this.priceprem=String.valueOf(Fnum);
        }

        public String getName() {
            return name;
        }

        public String getID() {
            return id;
        }

        public String getPrice() {
            return price;
        }
        public String getPricePremium() {
        	 
            return priceprem;
        }
       
        public String getDesc() {
            return desc;
        }

       

        @Override
        public String toString() {
            return name;
        }
    }
}