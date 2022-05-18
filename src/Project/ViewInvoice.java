  
package Project;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.io.File;
import java.awt.event.*; 

public class ViewInvoice {
	
	//customer requests all order information
	public ArrayList<Invoice> customerInvoices;
	int orderInput;
	
	//system displays the customers orders
	public void displayOrders(User U) {
		JFrame frame = new JFrame("Store R Us Invoices");
	    JList<Product> Order_list = new JList<>();
	    DefaultListModel<Product> model_orders = new DefaultListModel<>();
	    JLabel label1 = new JLabel();
	    JLabel label2 = new JLabel();
	    JLabel label3= new JLabel();
	    JLabel label4= new JLabel();
	    JLabel label5 = new JLabel();

	    
	    JButton backButton = new JButton("Back");
	    
	   
		try {
			Order_list.setModel(model_orders);
            Scanner in = new Scanner(new File(".\\Invoices.csv"));
            while (in.hasNextLine()) {
            	
                String[] line = in.nextLine().strip().split(",");
                
                if(line[0].equals(U.id)) {
                	model_orders.addElement(new Product( line[1], line[2],line[3],line[4],U.creditNum));
                }

                
                
                }
            }
            
    	 catch (Exception exception) {
            System.out.println(exception.toString());

        }
		JScrollPane orderList = new JScrollPane(Order_list);
        label1.setBounds(200, 10, 200, 100);
        label2.setBounds(200, 50, 200, 100);
        label3.setBounds(200, 90, 500, 100);
        label4.setBounds(200, 130, 300, 100);
        label5.setBounds(10, 10, 100, 20);
        label5.setText("Invoices");
        orderList.setBounds(10, 30, 100, 425);
        
        backButton.setBounds(150,400,150,50);
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(orderList);
        frame.add(label1);
        frame.add(label2);
        frame.add(label3);
        frame.add(label4);
        frame.add(label5);

        frame.add(backButton);
        
        
        frame.setSize(1000,500);
            
	    frame.setLayout(null);    
        frame.setVisible(true);	
        Order_list.getSelectionModel().addListSelectionListener(e -> {
            Product p = Order_list.getSelectedValue();
            label1.setText("Order Date: " + p.getOrderDate());
            label2.setText("Total Amount of Order: "+p.getTotalAmount());
            label3.setText("Items in Order: "+p.getItems().replace(';',' '));
            
            label4.setText("Credit Card Used: "+p.getcreditNum());
        });
        
        backButton.addActionListener(e -> {			
			OSS_HUB OH= new OSS_HUB();
			OH.HUB(U);
			frame.dispose();
        }
    	
    	);
	}
	
	
	
	 private class Product {
	    	String orderDate;
	        String totalAmount;
	        String items;
	        String status;
	        String creditNum;
	        public Product(String orderDate, String totalAmount, String items, String status, String creditNum) {
	        	this.orderDate= orderDate;
	            this.totalAmount = totalAmount;
	            this.items = items;
	            this.status = status;
	            this.creditNum = creditNum;
	            
	        }
	        public String getcreditNum(){
	        	return creditNum;
	        }
	        public String getItems() {
	            return items;
	        }

	        public String getOrderDate() {
	            return orderDate;
	        }

	        public String getTotalAmount() {
	            return totalAmount;
	        }
	        
	        

	       

	        @Override
	        public String toString() {
	            return orderDate;
	        }
	    }
	}
