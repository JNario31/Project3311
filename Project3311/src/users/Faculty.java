package users;

import java.io.IOException;
import java.util.ArrayList;

import application.Course;
import application.Database;

public class Faculty extends User{
	
	public ArrayList<Course> courses = new ArrayList<>();
	
	public Faculty(String firstname,String lastname, int id, String email, String password, String userType, String status){
		super(firstname, lastname, id, email, password, userType, status);
	}
	
	public Faculty(){
		super();
	}
	
	public void enrollCourse() throws IOException {
		for(Course course: Database.getInstance().getCourses()) {
//			if() {
//				
//			}
		}
	}
	
	
}
