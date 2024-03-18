package application;

import items.Item;
import users.User;

public interface Notifications {
	
	Item visit(User user);
	void notifyUsers();
	String subscribe(User user);
	void unsubscribe(User user);

}
