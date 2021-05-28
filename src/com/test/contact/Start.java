package com.test.contact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Start {

	public static void main(String[] args) throws NumberFormatException, IOException, SQLException {
	 
	   
	   Connection connection=ConnectionProvider.getConnection();
	   ContactDao contactdao=new ContactDao(connection);
	  BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	  
	  System.out.println("*******Contact Managment*******");
	   while(true) {
		   System.out.println("press 1 for Add");
		   System.out.println("Press 2 for display all contact");
		   System.out.println("Press 3 to delete Contact");
		   System.out.println("Press 4 to update contact");
		   System.out.println("Press 5 to search contact");
		   System.out.println("Press 6 to exit");
		   
		   try {
			   int choice=Integer.parseInt(br.readLine());
			   switch(choice) {
			   case 1:
				   Contact contact =new Contact();
				   System.out.println("Enter your name");
				   contact.setName(br.readLine());
				   
				   System.out.println("Enter your phone num");
				   contact.setPhone(br.readLine());
				   
				   System.out.println("Enter your email");
				   contact.setEmail(br.readLine());
				   
				   System.out.println("Enter your description");
				   contact.setDescription(br.readLine());
				   boolean f=contactdao.addContact(contact);
				   if(f) {
					   System.out.println("added sucessfully");
				   }
				   else {
					   System.out.println("something went in adding");
				   }
				   break;
			   case 2: boolean g=contactdao.displayContact();
				   break;
				   
			   case 3:
				   Contact conn =new Contact();
				   System.out.println("Enter the id that you want to delete");
			          conn.setId(Integer.parseInt(br.readLine()));
			          boolean h=contactdao.deleteContact(conn);
				   break;
				   
			   case 4: 
				   
				   System.out.println("Enter the id  please");
				   int contactid=Integer.parseInt(br.readLine());
				   Contact cs=contactdao.getSingle(contactid);
				  cs.setId(contactid);
				   
				 
				   System.out.println("Enter the contact name");
				   String Cname=br.readLine();
				   if(!Cname.trim().equals("")) {
					   
					  cs.setName(Cname); 
				   } 
				   
				   System.out.println("Enter your phone num");
				   String Cphone=br.readLine();
				   if(!Cphone.trim().equals("")) {
					   
						  cs.setPhone(Cphone); 
					   } 
				   
				   System.out.println("Enter your email");
				   String Cemail=br.readLine();
				   
				   if(!Cemail.trim().equals("")) {
					   
						  cs.setEmail(Cemail);
					   } 
				   
				   
				   System.out.println("Enter your description");
				   String Cdescription=br.readLine();
				   
				   if(!Cdescription.trim().equals("")) {
					   
						  cs.setDescription(Cdescription);
					   } 
				   
				   contactdao.updateContact(cs);
				   break;
				   
			   case 5:
				      System.out.println("Enter the keyword value");
				      String key=br.readLine();
				      contactdao.searchKey(key);
				      
				   break;
				   
			   case 6:System.out.println("bye bye....");
			   connection.close();
			   System.exit(12);
				   break;
				   
			   default:
				   System.out.println("Invalid choice");
				   
			   
			   }
		} catch (NumberFormatException e) {
			  System.out.println("not a number"); 
		}
		 
		   //System.out.println(choice);
		   
	
		   
	   }
	   
	   
	
	   
	   
	   

	}

}
