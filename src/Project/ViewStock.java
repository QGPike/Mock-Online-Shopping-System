
package Project;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;




//precondition: supplier is logged in

public class ViewStock {
	public void displayStock(User U){
		JFrame frame = new JFrame("Store R Us Orders to be Processed");
	    JList<Product> Stock_list = new JList<>();
	    
	    DefaultListModel<Product> model_stock = new DefaultListModel<>();
	    
	    JLabel label1 = new JLabel();
	    JLabel label2 = new JLabel();
	    JLabel label3= new JLabel();
	    JLabel label4= new JLabel();
	    JLabel label5 = new JLabel();
	    
	    
	    JButton exitButton = new JButton("Exit Catalog");
	    
		try {
			Stock_list.setModel(model_stock);
	        Scanner in = new Scanner(new File(".\\Stock.csv"));
	        while (in.hasNextLine()) {
	            String[] line = in.nextLine().strip().split(",");
	            
	            model_stock.addElement(new Product(line[0], line[1]));
	            
	            }
	        }
	        
		 catch (Exception exception) {
	        System.out.println(exception.toString());

	    }
		JScrollPane stockList = new JScrollPane(Stock_list);
	    
	    label1.setBounds(200, 10, 200, 100);
	    label2.setBounds(200, 50, 200, 100);
	    label3.setBounds(200, 90, 200, 100);
	    label4.setBounds(200, 130, 300, 100);
	    label5.setBounds(10, 10, 100, 20);
	    label5.setText("Catalog");
	    
	    stockList.setBounds(10, 30, 100, 425);
	    
	    
	    exitButton.setBounds(350, 400, 150, 50);
	    
	    
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.add(stockList);
	    frame.add(label1);
	    frame.add(label2);
	    frame.add(label3);
	    frame.add(label4);
	    frame.add(label5);
	    
	    
	    frame.add(exitButton);
	    
	    
	    frame.setSize(1000,500);
	        
	    frame.setLayout(null);    
	    frame.setVisible(true);	
	    
	    
		exitButton.addActionListener(e -> {			
			OSS_HUB OH = new OSS_HUB();
			OH.HUBforSupplier(U);
			frame.dispose();
	    }
		
		);
		Stock_list.getSelectionModel().addListSelectionListener(e -> {
            Product p = Stock_list.getSelectedValue();
            label1.setText("Item ID: " + p.getitemID());
            label2.setText("Total Stock: "+p.getTotalAmount());
            
        });
	}
	
	private class Product {
		String itemID;
	    String totalAmount;
	    
	    public Product(String itemID, String totalAmount) {
	    	this.itemID= itemID;
	        this.totalAmount = totalAmount;
	       
	        
	    }
	    
	    public String getitemID() {
	        return itemID;
	    }

	    

	    public String getTotalAmount() {
	        return totalAmount;
	    }
	    
	    

	   

	    @Override
	    public String toString() {
	        return itemID;
	    }
	}
}



