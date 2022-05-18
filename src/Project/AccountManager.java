/*
 * 
Use Cases for AccountManager
*
**
***
**
*
Use case name: Log On
Summary: Customer logs on into the system.
Actor: Customer (or supplier)
Precondition: None.

Main sequence:
1. The customer enters the ID and password.
2. The system checks if the customer ID and password are valid.
3. The system displays a welcome message if the ID and password are valid.

Alternative sequence:

Step 3: If the customer's ID does not exist in the system, the system displays no account.

Postcondition: Customer has logged on into the system.
*
**
***
**
*
Use case name: Log Out
Summary: Customer logs out system.
Actor: Customer (or Supplier)
Precondition: Customer logged on into the system.

Main sequence:
1. The customer selects "log out."
2. The system makes customers log out.

Alternative sequence: None.

Postcondition: Customer has logged out.
*
**
***
**
*
TODO Use case name: Create Account
Summary: Customer creates an account.
Actor: Customer (or Supplier)
Precondition: None

Main sequence:
1. The customer enters the id, password, name, address, phone number, and credit card number.
2. The system creates a customer account and stores account information.
3. The system displays account type � regular or premium to the customer.
4. If the customer selects premium, the system updates the account type and charges the membership fee (i.e., $40/year) in the customer account. (The customer must pay the membership fee when he/she purchases the first item in the year.)
5. The system displays an account created.

Alternative sequence:
Step 1: Supplier inputs only id and password to the system.

Step 2: If the same id exists in the system, the system displays an error message and requests a different id from the customer (or supplier).

Step 4: If the customer selects regular, the system does not charge the membership fee.

Postcondition: The customer has created an account.
 *
 */

package Project;

import javax.swing.*;    
import java.awt.event.*;  

//This class should make it simple to create and keep track of all login info
public class AccountManager
{
	//Varible area, the account information variables are grouped by position in their respective arrays. id[0] goes with password[0], name[0], etc.
	String[] id = new String[11]; //Pretty sure this is enough space for Account storage for now. subject to change.
	String[] password = new String[11];	
	String[] name = new String[11];
	String[] address = new String[11];
	String[] phoneNum = new String[11];
	String[] creditNum = new String[11];
	boolean[] premium = new boolean[11];
	boolean[] supplier = new boolean[11];
	boolean verify = false; //used to flag a verified User/Pass pair
	boolean isPrem = false; //used to check if premium is toggled on
	int nAccounts = 0;
	
	
	
	public void login()
	{
		//d[0]="TheD";
		//password[0]="321";
		//name[0]="Diego";
		//address[0]="2524 20th";
		//phoneNum[0]="1234567890";
		//creditNum[0]="1234567890987654";
		//premium[0]=false;
		//supplier[0]=true;
		//verify = false; //reset the variable for use
		
		
	    JFrame f =new JFrame("Stores R Us Log On page!"); //We sell Stores   
	    final JLabel label = new JLabel();            
	    label.setBounds(75,190, 300,50);  
	    final JTextField pass = new JTextField();  
	    pass.setBounds(100,75,100,30);   
	    JLabel l1=new JLabel("Username:");    
        l1.setBounds(20,20, 80,30);    //position of Label 1
	    JLabel l2=new JLabel("Password:");    
	    l2.setBounds(20,75, 80,30);  //position of Label 2
        JButton b = new JButton("Login");  
	    b.setBounds(100,120, 80,30); 
	    JButton b2 = new JButton("Register Account");  
	    b2.setBounds(50,165, 180,30);
	     
	    final JTextField text = new JTextField();
	    text.setBounds(100,20, 100,30);    
	    f.add(pass); 
	    f.add(l1); 
	    f.add(label); 
	    f.add(l2); 
	    f.add(b); 
	    f.add(b2);
	    f.add(text);  
	    f.setSize(300,300);    
	    f.setLayout(null);    
        f.setVisible(true);  
         
        ///////////////////////////////////////////////////////         
       ////////////////UI Listeners and Events////////////////
      ///////////////////////////////////////////////////////
        
        //Login Button event//
	    b.addActionListener(new ActionListener() 
	    {  
			public void actionPerformed(ActionEvent e)
	    	 {       
				String data = "";
				
				if(text.getText().equals("") || pass.getText().equals("")) //Will catch an empty text field
				{
					 data = "Please enter a username and password";
					 label.setBounds(40,190, 300,50); 
				}
				else
				{
					int position=0;
					for(int i = 0; i <= nAccounts; i++) //This loop checks through the entire ID/Pass pairings for a match
					{
						if(id[i] == null) break;

						if(id[i].equals(text.getText()) && password[i].equals(pass.getText()) ) 
						{
							verify = true;
							position=i;
							break; //No reason to continue loop if true
						}
						else verify = false;
					} 
					
					if(verify == true)
					{
						PaymentInformation p = new PaymentInformation(creditNum[position],"321","12/21",300);
						
						User U = new User(id[position],password[position],name[position],address[position],phoneNum[position],creditNum[position],String.valueOf(premium[position]),String.valueOf(supplier[position]),p);
						
						data = "Login Successful!";
						label.setBounds(90,190, 300,50);
						if(supplier[position]) {
							OSS_HUB OH= new OSS_HUB();
							OH.HUBforSupplier(U);
							f.dispose();
						}else {
							OSS_HUB OH= new OSS_HUB();
							OH.HUB(U);
							f.dispose();
						}
						
						//TODO call store page and close login
					}
					else
					{
						for(int i = 0; i <= nAccounts; i++) //Checks for existance of username
						{
							if(id[i] == null) 
								{
									data = "Username does not exist";
									break;
								}

							if(id[i].equals(text.getText())) //if username exists, notify incorrect password
							{
								data = "Password Incorrect. Try again";
								break;
							}
							else data = "Username does not exist"; //If it doesn't exist, notify user
							label.setBounds(65,190, 300,50); 
					    }
				  }
			    }
				 label.setText(data); 
	         }
	    });
	     
	     //Register Button Event//
	     b2.addActionListener(new ActionListener()
		 {
			public void actionPerformed(ActionEvent e)
			{
				queryAccount();
				f.dispose();
			}
		 }); 
    }
	     
	
	//TODO Logout needs a seperate class to call it from here through a button event
	//it's main function should be to close whatever is up and open the Login page
	public void logout()
	{
		//The window calling this needs to use the dispose() method
		this.login();
	}
	
	
	
	public void displayAccounts() //debug
	{
		System.out.println("nAccounts: "+nAccounts+"\n");
	}

	public void queryAccount()
	{
		//Query fram GUI Setup
		 JFrame qF = new JFrame("Stores R Us Registration page!"); //We sell Stores    
		 JLabel label = new JLabel("Choose Account Type");            
	     label.setBounds(80,10, 500,50);
		 JButton c1 = new JButton("Customer");  
	     c1.setBounds(10,50, 100,30);  
	     JButton c2 = new JButton("Supplier");  
	     c2.setBounds(170,50, 85,30);
	     JButton bak = new JButton("Back");  
	     bak.setBounds(98,90, 85,30);  
	     
	     
	     
	   //Show and organize GUI Elements
	     qF.add(label);
	     qF.add(c1);
	     qF.add(c2);
	     qF.add(bak);
	     qF.setSize(300,180);    //WxH
	     qF.setLayout(null);    
         qF.setVisible(true);
        
        ///////////////////////////////////////////////////////         
       ////////////////UI Listeners and Events////////////////
      ///////////////////////////////////////////////////////
        
        c1.addActionListener(new ActionListener() //choice 1 button listener
       	     {  
       			public void actionPerformed(ActionEvent e) //choice 1 event (if pushed)
       	    	 {
       				supplier[nAccounts] = false;
       				createCustomerAccount();
       				qF.dispose();
       	    	 }
       	    });
        
        c2.addActionListener(new ActionListener() //choice 2 listener
       	     {  
       			public void actionPerformed(ActionEvent e) //choice 2 event (if pushed)
       	    	 {
       				supplier[nAccounts] = true;
       				createSupplierAccount();
       				qF.dispose();
       	    	 }
       	    });
        
        bak.addActionListener(new ActionListener() //choice 2 listener
          	     {  
          			public void actionPerformed(ActionEvent e) //choice 2 event (if pushed)
          	    	 {
          				login();
          				qF.dispose();
          	    	 }
          	    });
	}
	
	public void createCustomerAccount()
	{
		isPrem = false;
		
		//GUI Setup
	     JFrame f = new JFrame("Stores R Us Registration page!"); //We sell Stores    
	     final JTextField text = new JTextField();   //ID field
	     text.setBounds(100,20, 120,30);  
	     final JTextField pass = new JTextField(); //Pass field
	     pass.setBounds(100,75,120,30);  
	     final JTextField n = new JTextField();  //Name field
	     n.setBounds(290,20, 150,30);  
	     final JTextField a = new JTextField();  //Address field
	     a.setBounds(300,75,250,30);
	     final JTextField phone = new JTextField(); //phone # field
	     phone.setBounds(100,125,120,30);
	     final JTextField CC = new JTextField();  //CC # field
	     CC.setBounds(330,125,250,30);
	     JLabel l1=new JLabel("Username:");    
         l1.setBounds(20,20, 80,30);    
	     JLabel l2=new JLabel("Password:");    
	     l2.setBounds(20,75, 80,30);  
	     JLabel l3=new JLabel("Name:");    
         l3.setBounds(250,20, 80,30);    
	     JLabel l4=new JLabel("Adress:");    
	     l4.setBounds(250,75, 80,30);
	     JLabel l5=new JLabel("Phone:"); 
	     l5.setBounds(20,125, 80,30);
	     JLabel l6=new JLabel("Credit Card #:");   
	     l6.setBounds(250,125, 80,30);  
	     JLabel label = new JLabel();            
	     label.setBounds(250,200, 500,50);
	     JLabel pl = new JLabel("This Account is NOT Premium yet!");            
	     pl.setBounds(430,160, 500,50);
         JButton b = new JButton("Register");  
	     b.setBounds(100,170, 85,30);  
	     JButton b2 = new JButton("Subscribe Premium!");  
	     b2.setBounds(250,170, 175,30); 
	     JButton bak = new JButton("Back");  
	     bak.setBounds(100,210, 85,30);
	     
	     //Show and organize GUI Elements
	     f.add(text);
	     f.add(pass); 
	     f.add(n);
	     f.add(a);
	     f.add(phone);
	     f.add(CC);
	     f.add(l1); 
	     f.add(pl);
	     f.add(label); 
	     f.add(l2);
	     f.add(l3);
	     f.add(l4);
	     f.add(l5);
	     f.add(l6);
	     f.add(b); 
	     f.add(b2); 
	     f.add(bak);
	     f.setSize(650,300);    //WxH
	     f.setLayout(null);    
         f.setVisible(true);
         
          ///////////////////////////////////////////////////////         
         ////////////////UI Listeners and Events////////////////
        ///////////////////////////////////////////////////////
         
         
         
	     b2.addActionListener(new ActionListener() //Premium button listener
	     {  
			public void actionPerformed(ActionEvent e) //Premium button event (if pushed)
	    	 {
				isPrem = !isPrem;
				if(isPrem == true)
				{
					b2.setText("Unsubscribe Premium");
					b2.setBounds(250,170, 190,30);
					pl.setText("This Account is Premium!");
					pl.setBounds(445,160, 500,50);
					premium[nAccounts] = true;
				}
				else
				{
					b2.setText("Subscribe Premium!");
					b2.setBounds(250,170, 175,30);
					pl.setText("This Account is NOT Premium yet!"); 
					pl.setBounds(430,160, 500,50);
					premium[nAccounts] = false;
				}
	    	 }
	     });
	     
	     
	     b.addActionListener(new ActionListener() //Register button listener
	     {  
			public void actionPerformed(ActionEvent e) //Register button event (if pushed)
	    	 {
				if(CC.getText().length() == 16) //CC#s are 16 digits long
				{
					if(phone.getText().length() == 10 || phone.getText().length() == 11) //Phone #s are 10 to 11 digits long
					{
						if(text.getText().equals("") || pass.getText().equals("")|| n.getText().equals("") || a.getText().equals("") || CC.getText().equals("") || phone.getText().equals("")) //should catch an empty text field
						 {
							 label.setText("Please complete all required entries.");
						 }
						 else
						 {
							 for(int i = 0; i <= nAccounts; i++) //This loop checks through the entire ID/Pass pairings for a match
								{
									if(id[i] == null) break;

									if(id[i].equals(text.getText()))  
									{
										verify = true;
										break; //No reason to continue loop if true
									}
									else verify = false;
								} 
								
								if(verify == true)
								{
									label.setText("Username exists, try again");
								}
								else
								{	
									 //If everything is valid, save information to the same position in each respective array
									 id[nAccounts] = text.getText();
						    		 password[nAccounts] = pass.getText(); 
						    		 name[nAccounts] = n.getText();
						    		 address[nAccounts] = a.getText(); 
						    		 phoneNum[nAccounts] = phone.getText();
						    		 creditNum[nAccounts] = CC.getText(); 
						    		 label.setText("Submitted!");
						    		 if(nAccounts < 10) nAccounts++; //Will replace last account if max reached, there is probably a better solution but this works for now
						    		 login(); //Upon successful registration, close page and open Login
						    		 f.dispose();
								}
						 }
					}
					else label.setText("Invalid Phone # length, there must be 10 or 11 digits"); 
				}
				else label.setText("Invalid Credit Card # length, there must be 16 digits");
	         }  
			});
	     
	             bak.addActionListener(new ActionListener() //choice 2 listener
          	     {  
          			public void actionPerformed(ActionEvent e) //choice 2 event (if pushed)
          	    	 {
          				queryAccount();
          				f.dispose();
          	    	 }
          	    });

	     
	   //these two blocks of code check every key press to make sure only digits are entered into the CC and phone fields//
			CC.addKeyListener(new KeyAdapter() //Checks in the CC field
			{
				public void keyPressed(KeyEvent ke) 
				{
					if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == '\b') 
					{
						CC.setEditable(true);
						label.setText("");
					} 
					else 
					{
						CC.setEditable(false);
						label.setText("Only digits 0-9 allowed in this field");
					}
				}
			});

			phone.addKeyListener(new KeyAdapter() //Checks in the phone field
			{
				public void keyPressed(KeyEvent ke) 
				{
					if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == '\b')
					{
						phone.setEditable(true);
						label.setText("");
					} 
					else 
					{
						phone.setEditable(false);
						label.setText("Only digits 0-9 allowed in this field");
					}
				}
			});
		}
	
	public void createSupplierAccount()
	{
		//GUI Setup
	     JFrame f =new JFrame("Store R Us Registration page!"); //We sell Stores   
	     final JLabel label = new JLabel();            
	     label.setBounds(30,180, 500,50);  
	     final JTextField pass = new JTextField(); 
	     pass.setBounds(100,75,100,30);   
	     JLabel l1=new JLabel("Username:");    
         l1.setBounds(20,20, 80,30);    
	     JLabel l2=new JLabel("Password:");    
	     l2.setBounds(20,75, 80,30);    
         JButton b = new JButton("Register");  
	     b.setBounds(100,120, 85,30);  
	     JButton bak = new JButton("Back");  
	     bak.setBounds(100,160, 85,30); 

	     //Show and organize GUI Elements
	     final JTextField text = new JTextField();  
	     text.setBounds(100,20, 100,30);    
	     f.add(pass); 
	     f.add(l1); 
	     f.add(label); 
	     f.add(l2); 
	     f.add(b); 
	     f.add(bak);
	     f.add(text);  
	     f.setSize(300,270);    
	     f.setLayout(null);    
         f.setVisible(true);


	     b.addActionListener(new ActionListener() //Button events
	     {  

			public void actionPerformed(ActionEvent e) //Register button event
	    	 {   
				String data = "";

				 if(text.getText().equals("") || pass.getText().equals("")) //should catch an empty text field
				 {
					 data = "Please enter a username and password";
				 }
				 
				 else if(text.getText() != "" || pass.getText() != "")
				 {
					 for(int i = 0; i <= nAccounts; i++) //This loop checks through the entire ID/Pass pairings for a match
						{
							if(id[i] == null) break;

							if(id[i].equals(text.getText())) 
							{
								verify = true;
								break; //No reason to continue loop if true
							}
							else verify = false;
						} 
						
						if(verify == true)
						{
							data = "Username exists, try again";
						}
						else
						{
							 id[nAccounts] = text.getText();
				    		 password[nAccounts] = pass.getText(); 
				    		 premium[nAccounts] = false;
				    		 data = "Submitted!";
				    		 if(nAccounts < 10) nAccounts++; //Will replace last account if max reached, there is probably a better solution but this works for now
				    		 login(); //Upon successful registration, close page and open Login
				    		 f.dispose();
						}
				 }
				 
	    		 label.setText(data);       	 
	         }  
	   }); 
	     
	     bak.addActionListener(new ActionListener() //choice 2 listener
          	     {  
          			public void actionPerformed(ActionEvent e) //choice 2 event (if pushed)
          	    	 {
          				queryAccount();
          				f.dispose();
          	    	 }
          	    });
	}
}