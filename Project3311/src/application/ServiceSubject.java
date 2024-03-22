package application;

import java.io.IOException;

import users.User;

public interface ServiceSubject {
	User requestUser(String email, String password) throws IOException;
}
