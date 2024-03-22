package application;

import java.io.IOException;

import users.User;

public class StoreUser implements ServiceSubject{

	private static  StoreUser instance;
	Database service = Database.getInstance();
	User user;
	
	private StoreUser() {

	}
	
	public static StoreUser getInstance() {
		StoreUser result = instance;
		if(result == null) {
			synchronized (StoreUser.class){
				result = instance;
				if(result == null) {
					instance=result = new StoreUser();
				}
			}
		}
		return result;

	}
	
	@Override
	public User requestUser(String email, String password) throws IOException {
		
		this.user =service.requestUser(email, password);
		return this.user;
		
	}
	
	public User getUser() {
		return this.user;
	}

}
