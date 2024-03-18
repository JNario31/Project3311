package application;


import java.io.IOException;
import java.util.ArrayList;

import items.Item;
import users.Student;
import users.User;

public class NewsletterService implements Notifications{
	

	
	private ArrayList<User> subscribers = new ArrayList<>();
	public Item newsletter;
	




	public void setNewsletter(Item i) {
		this.newsletter=i;
	}
	
	@Override
	public Item visit(User user) {
		
		return this.newsletter;
		
	}

	@Override
	public void notifyUsers() {
		for(User u: subscribers) {
			u.accept(this);
		}
		
	}

	@Override
	public String subscribe(User user) {
		if(Database.getInstance().checkSubscribed(user)) {
			return "Already Subscribed";
		}else {
			subscribers.add(user);
			user.rentItem(newsletter);
			Database.getInstance().addNewsletterServiceSubscribers();
			return "Successfully Subscribed";
		}
		
	}

	@Override
	public void unsubscribe(User user) {
		subscribers.remove(user);
		
	}
	
	public ArrayList<User> getSubscribers(){
		return subscribers;
	}
	
	public static void main(String[] args) throws IOException {
		Database.getInstance().loadItems();
		System.out.println(Database.getInstance().getItems());
		Database.getInstance().loadNewsletterService();
		User st = new Student();
		Database.getInstance().getNewsletterService().subscribe(st);
		System.out.println(Database.getInstance().getNewsletterService().subscribers);
		Database.getInstance().getNewsletterService().notifyUsers();
		System.out.println(st.getRentedItemsList());
		
	}
	
	public Item getNewsletter() {
		return newsletter;
	}
	


}