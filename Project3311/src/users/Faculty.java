package users;

public class Faculty extends User{
	
	public Faculty(String firstname,String lastname, int id, String email, String password, String userType, String status){
		super(firstname, lastname, id, email, password, userType, status);
	}
	
	public Faculty(){
		super();
	}
	
	
}
