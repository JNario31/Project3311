package users;

public class NonFaculty extends User{
	
	public NonFaculty(String firstname,String lastname, int id, String email, String password, String userType, String status){
		super(firstname, lastname, id, email, password, userType, status);
	}
	
	public NonFaculty(){
		super();
	}
	

}
