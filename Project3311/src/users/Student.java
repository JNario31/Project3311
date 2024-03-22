package users;

import java.io.IOException;
import java.util.ArrayList;

import application.Course;
import application.Database;
import items.Item;

public class Student extends User{
	
	public ArrayList<Course> courses = new ArrayList<>();
	public ArrayList<Item> eTextbooks = new ArrayList<>();
	
	public Student(String firstname,String lastname, int id, String email, String password, String userType, String status){
		super(firstname, lastname, id, email, password, userType, status);
	}
	
	public Student(){
		super();
	}
	
	
	public ArrayList<Course> getCourses() {
		return courses;
	}
	
	public void enrollCourse() throws IOException {
		for(Course course: Database.getInstance().getCourses()) {
			for(Student s: course.getStudents()) {
				if(s.getId()==id && !(courses.contains(course))) {
					courses.add(course);
				}
			}
		}
	}
	
	public void loadETextbooksList() {
		for(Course course : courses) {
			if(!(eTextbooks.containsAll(course.getETextbooks()))) {
				eTextbooks.addAll(course.getETextbooks());
			}
		}
	}
	
	public ArrayList<Item> getETextbooksList(){
		return eTextbooks;
	}
	
	
}
