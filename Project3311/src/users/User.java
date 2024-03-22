package users;


import java.io.IOException;
import java.util.ArrayList;

import application.Database;
import application.Notifications;
import application.Subscriber;
import items.Item;


public abstract class User implements Subscriber{

	public String firstname;
	public String lastname;
	public int id;
	public String email;
	public String password;
	private String userType;
	private String status;
	public ArrayList<Item> rentedItems = new ArrayList<>();
	
	
	public User(String firstname,String lastname, int id, String email, String password, String userType, String status) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.id = id;
		this.email = email;
		this.password = password;
		this.userType= userType;
		this.status = status;
	}
	
	public User(){
		super();
	}
	
	public void loadRentedItemsList() throws IOException {
		for(Item item : Database.getInstance().items) {
			if(item.getUserEmail().equals(email) && !(rentedItems.contains(item))) { //changed contains to equals
				rentedItems.add(item);
			}
		}
	}
	
	
	
	public void rentItem(Item i) {
		rentedItems.add(i);
	}

	public void unrentItem(Item i) {
		rentedItems.remove(i);
	}
	
	
	public ArrayList<Item> getRentedItemsList() throws IOException{
		return rentedItems;
	}
	

	public String getFirstName() {
		return firstname;
	}
	
	public String getLastName() {
		return lastname;
	}

	public void setFirstName(String name) {
		this.firstname = name;
	}
	
	public void setLastName(String name) {
		this.lastname = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUserType() {
		return userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "User [name=" + firstname + " "+ lastname + ", id=" + id + ", email=" + email + ", password=" + password + "]";
	}
	
	public static void main(String [] args) throws IOException {

	}

	@Override
	public void accept(Notifications visitor) {
		rentItem(visitor.visit(this));
		
	}
	
}









