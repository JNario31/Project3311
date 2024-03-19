package items;

import java.time.LocalDate;
import java.util.Date;

public abstract class Item {

	public String type;
	public String name;
	public int id;
	public String location;
	public String purchase;
	public String email;
	public double price;
	public LocalDate dueDate;
	public LocalDate date;

	
		public Item(String type,String name, int id, String location, String purchase, String email, double price) {
			super();
			this.type = type;
			this.name = name;
			this.id = id;
			this.location = location;
			this.purchase = purchase;
			this.email = email;
			this.price = price;
		}
		
		public Item(){
			super();
		}
		
		public String getName() {
			return name;
		}
		
		public String getType() {
			return type;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		public void setType(String type) {
			this.type = type;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getPurchase() {
			return purchase;
		}

		public void setPurchase(String purchase) {
			this.purchase = purchase;
		}
		
		public String getUserEmail() {
			return email;
		}
		
		public void setUserEmail(String email) {
			this.email = email;
		}
		
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		
		public void setDueDate(LocalDate dueDate) {
			this.dueDate = dueDate;
		}
		
		public LocalDate getDueDate() {
			return dueDate;
		}
		
		public LocalDate getDate() {
			return date;
		}
		
		public void setDate(LocalDate date) {
			this.date = date;
		}

		
		public String toString() {
	        return this.getName();
	    }
	
}












