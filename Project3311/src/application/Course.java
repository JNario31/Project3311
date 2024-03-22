package application;

import java.io.IOException;
import java.util.ArrayList;

import items.Item;
import users.Faculty;
import users.Student;

public class Course {

	String title;
	String id;
	String instructor;
	ArrayList<Student> students = new ArrayList<>();
	ArrayList<Faculty> faculty = new ArrayList<>();
	ArrayList<Item> eTextbooks = new ArrayList<>();
	ArrayList<Item> textbooks = new ArrayList<>();
	
	
	public Course(String title,String id) {
		super();
		this.title = title;
		this.id = id;
		this.id = id;
	}
	
	public Course(){
		super();
	}
	
	public void setTitle(String title) {
		this.title=title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setId(String id) {
		this.id=id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setInstructor(String name) {
		this.instructor=name;
	}
	
	public String getInstructor() {
		return instructor;
	}
	
	@Override
	public String toString() {
		return id;
	}
	
	public void addStudents(ArrayList<Student> students) throws IOException {
		this.students.addAll(students);
	}

	//added
	public void addFaculty(ArrayList<Faculty> faculty) throws IOException{
		this.faculty.addAll(faculty);
		
	}
	
	public void addETextbooks(Item eTextbooks) {
		this.eTextbooks.add(eTextbooks);
	}
	
	public ArrayList<Item> getETextbooks(){
		return this.eTextbooks;
	}
	public ArrayList<Item> getTextbooks(){
		return this.textbooks;
	}
	
	public ArrayList<Student> getStudents() {
		return students;
	}
	//added 
	public ArrayList<Faculty> getFaculty() {
		return faculty;
	}

	public void addTextbooks(Item textbook) {
		this.textbooks.add(textbook);
		
	}


	
}