package application;

import java.io.IOException;

import users.User;

public interface Subject1 {
	
	void notifyUsers();
	String subscribe(User user);
	void unsubscribe(User user) throws IOException;

}
