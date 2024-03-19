package application;

import java.io.IOException;

import items.Item;
import users.User;

public interface Notifications {
	
	Item visit(User user);
	void notifyUsers();
	String subscribe(User user);
	void unsubscribe(User user) throws IOException;

}
