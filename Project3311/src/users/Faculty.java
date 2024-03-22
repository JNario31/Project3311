package users;

import java.io.IOException;
import java.util.ArrayList;

import application.Course;
import application.Database;
import application.Observer;
import items.Item;

public class Faculty extends User implements Observer{
	
	public ArrayList<Course> courses = new ArrayList<>();
	public ArrayList<Item> Textbooks = new ArrayList<>();
	private String alert;
	
	public Faculty(String firstname,String lastname, int id, String email, String password, String userType, String status){
		super(firstname, lastname, id, email, password, userType, status);
	}
	
	public Faculty(){
		super();
	}
	
	
	public ArrayList<Course> getCourses() {
		return courses;
	}
	
	public void enrollCourse() throws IOException {
		for(Course course: Database.getInstance().getCourses()) {
			for(Faculty s: course.getFaculty()) {
				if(s.getId()==id && !(courses.contains(course))) {
					courses.add(course);
				}
			}
		}
	}
	
	public void loadTextbooksList() {
		for(Course course : courses) {
			if(!(Textbooks.containsAll(course.getTextbooks()))) {
				Textbooks.addAll(course.getTextbooks());
			}
		}
	}
	
	public ArrayList<Item> getTextbooksList(){
		return Textbooks;
	}

	@Override
	public void update(String message) {
		this.alert=message;
		
	}


	
	
}