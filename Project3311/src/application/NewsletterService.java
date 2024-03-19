package application;


import java.io.IOException;
import java.util.ArrayList;

import items.Item;
import items.Newsletter;
import users.Student;
import users.User;

public class NewsletterService implements Notifications{
	

	
	private ArrayList<User> subscribers = new ArrayList<>();
	public Newsletter newsletter;
	




	public void setNewsletter(Newsletter i) {
		this.newsletter=i;
	}
	
	@Override
	public Newsletter visit(User user) {
		
		return this.newsletter;
		
	}

	@Override
	public void notifyUsers() {
		for(User u: subscribers) {
			u.accept(this);
		}
		
	}
	
	public void add(User u) {
		subscribers.add(u);
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
	
	
	//added
	@Override
	public void unsubscribe(User user) throws IOException {
	
		user.unrentItem(newsletter);
		subscribers.remove(user);
	
		
		Database.getInstance().addNewsletterServiceSubscribers();
		
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
	
	//added
	public Newsletter getNewsletter() {
		return newsletter;
	}
	


}