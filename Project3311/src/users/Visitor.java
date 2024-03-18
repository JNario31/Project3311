package users;

public class Visitor extends User{
	
	public Visitor(String firstname,String lastname, int id, String email, String password, String userType, String status){
		super(firstname, lastname, id, email, password, userType, status);
	}
	
	public Visitor(){
		super();
	}
	

}
