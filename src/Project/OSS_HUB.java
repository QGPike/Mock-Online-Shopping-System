package Project;



import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class OSS_HUB {
	public void HUBforSupplier(User U)
	{
		JFrame frame = new JFrame("Store R Us Hub");
	    
	    JLabel label1 = new JLabel();
	    
	    label1.setText("Welcome "+U.id);
	    label1.setBounds(100, 10, 200, 100);
	    
	    JButton processOrderButton = new JButton("Process Orders");
	    JButton viewStockButton = new JButton("View Stock");
	    JButton logOutButton = new JButton("Log Out");
	    processOrderButton.setBounds(75, 100, 150, 50);
	    viewStockButton.setBounds(75,200,150,50);
	    
	    logOutButton.setBounds(75,400,150,50);
	    frame.add(label1);
	    
	    frame.add(processOrderButton);
	    frame.add(viewStockButton);
	    frame.add(logOutButton);
	    frame.setSize(300,600);    
	    frame.setLayout(null);    
        frame.setVisible(true);
        
        processOrderButton.addActionListener(e -> {
            
            frame.dispose();
            ProcessOrder VO = new ProcessOrder();
            VO.processingOrders(U);

            }
        	
        	);
        viewStockButton.addActionListener(e -> {
            
            frame.dispose();
            ViewStock VI = new ViewStock();
            VI.displayStock(U);

            }
        	
        	);
        logOutButton.addActionListener(e -> {
            
            frame.dispose();
            AccountManager aM = new AccountManager();
    		aM.logout(); 
            

            }
        	
        	);
	}
	public void HUB(User U)
	{
		JFrame frame = new JFrame("Store R Us Supplier Hub");
	    
	    JLabel label1 = new JLabel();
	    
	    label1.setText("Welcome "+U.id);
	    label1.setBounds(100, 10, 200, 100);
	    JButton catButton = new JButton("Catalog");
	    JButton viewOrderButton = new JButton("View Orders");
	    JButton viewInvoiceButton = new JButton("View Invoices");
	    JButton logOutButton = new JButton("Log Out");
	    catButton.setBounds(75, 100, 150, 50);
	    viewOrderButton.setBounds(75,200,150,50);
	    viewInvoiceButton.setBounds(75,300,150,50);
	    logOutButton.setBounds(75,400,150,50);
	    frame.add(label1);
	    frame.add(catButton);
	    frame.add(viewOrderButton);
	    frame.add(viewInvoiceButton);
	    frame.add(logOutButton);
	    frame.setSize(300,600);    
	    frame.setLayout(null);    
        frame.setVisible(true);
        catButton.addActionListener(e -> {
    
        frame.dispose();
        OrderManager OM = new OrderManager();
        OM.selectItem(U);

        }
    	
    	);
        viewOrderButton.addActionListener(e -> {
            
            frame.dispose();
            ViewOrder VO = new ViewOrder();
            VO.displayOrders(U);

            }
        	
        	);
        viewInvoiceButton.addActionListener(e -> {
            
            frame.dispose();
            ViewInvoice VI = new ViewInvoice();
            VI.displayOrders(U);

            }
        	
        	);
        logOutButton.addActionListener(e -> {
            
            frame.dispose();
            AccountManager aM = new AccountManager();
    		aM.logout(); 
            

            }
        	
        	);
	}

    
}
