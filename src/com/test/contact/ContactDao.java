package com.test.contact;

import java.sql.*;

public class ContactDao {

	private Connection con;

	public ContactDao(Connection con) throws SQLException {
		
		this.con = con;
		
		String query="create table if not exists contact(id int primary key auto_increment, name varchar(100) not null,phone varchar(10) not null, email varchar(100), description varchar(400))";
		Statement stmt=this.con.createStatement();
		stmt.executeUpdate(query);
		System.out.println("Table created");
		
	}
	
	public boolean addContact(Contact c1) {
		boolean flag=false;
		try {
			//System.out.println("---------------");
			String query="insert into contact(name,phone,email,description) values(?,?,?,?)";
			PreparedStatement pstmt=this.con.prepareStatement(query);
			pstmt.setString(1,c1.getName());
			pstmt.setString(2,c1.getPhone());
			pstmt.setString(3,c1.getEmail());
			pstmt.setString(4,c1.getDescription());
			int row=pstmt.executeUpdate();
			System.out.println("Contact added");
			flag =true;
			
		} catch (Exception e) {
			
		}
		
		return flag;
	}
	
	//display method
	public boolean displayContact() {
		boolean flag=false;
		try {
			String query="Select * from contact";
			Statement stmt=this.con.createStatement();
			ResultSet set =stmt.executeQuery(query);
			while(set.next()) {
				
				int id=set.getInt("id");
				String name=set.getString("name");
				String phone=set.getString("phone");
				String email=set.getString("email");
				String description=set.getString("description");
				//System.out.println("___________________________________________");
				System.out.println(id);
				System.out.println(name);
				System.out.println(phone);
				System.out.println(email);
				System.out.println(description);
				System.out.println("___________________________________________");
				flag =true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
		
	}

	//delete contact here
	public boolean deleteContact(Contact c2) {
		boolean flag=false;
		try {
			
			String query="delete from contact where id=?";
			PreparedStatement pstm=this.con.prepareStatement(query);
			pstm.setInt(1, c2.getId());
			pstm.executeUpdate();
			System.out.println("Data deleted sucessfully");
			flag=true;
			
		} catch (Exception e) {
		e.printStackTrace();
		}
		return flag;
	}
	
	
	public void updateContact(Contact c3) {
        
		try {
			//System.out.println("---______update contact="+c3.getName()+"desc"+c3.getDescription()+"id here"+c3.getId());
			String query="update contact set name=?,phone=?,email=?,description=? where id=?";

			PreparedStatement pass=this.con.prepareStatement(query);
			pass.setString(1, c3.getName());
			pass.setString(2, c3.getPhone());
			pass.setString(3, c3.getEmail());
			pass.setString(4, c3.getDescription());
			pass.setInt(5, c3.getId());
			int ind=pass.executeUpdate();
			
			System.out.println("data updated sucessfully"+ind);
			
		} catch (Exception e) {
			
		}

		
		
	}

	public Contact getSingle(int contactid) {
		Contact cd=null;
		try {
			
			 cd=new Contact();
			String query="Select * from contact where id=?";
			PreparedStatement stm=this.con.prepareStatement(query);
			stm.setInt(1, contactid);
			ResultSet data =stm.executeQuery();
			while(data.next()) {
				cd.setName(data.getString("name"));
				cd.setPhone(data.getString("phone"));
				cd.setEmail(data.getString("email"));
				cd.setDescription(data.getString("description"));
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cd;
	}

	public void searchKey(String key) {
		 
		try {
			String query="select * from contact where name like ? or description like ?";
			PreparedStatement stmm=this.con.prepareStatement(query);
			stmm.setString(1,"%" + key + "%" );
			stmm.setString(2,"%" + key + "%" );
			ResultSet ds =stmm.executeQuery();
			while(ds.next()) {
				System.out.println("id"+ds.getInt("id"));
				System.out.println("name"+ds.getString("name"));
				System.out.println("phone"+ds.getString("phone"));
				System.out.println("email"+ds.getString("email"));
				System.out.println("description"+ds.getString("description"));
				System.out.println("___________________________________________");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
