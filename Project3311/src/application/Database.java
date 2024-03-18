package application;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import items.Board;
import items.Book;
import items.CD;
import items.Item;
import items.Magazine;
import items.Newsletter;
import items.Textbook;
import javafx.fxml.Initializable;
import users.Faculty;
import users.NonFaculty;
import users.Student;
import users.User;
import users.Visitor;

import java.util.ArrayList;

public class Database {

	//private static volatile Database instance;
	private static  Database instance;
	public ArrayList<User> users = new ArrayList<User>();
	public ArrayList<Student> students = new ArrayList<>();
	public ArrayList<Item> items = new ArrayList<Item>();
	public ArrayList<Item> stock = new ArrayList<>();
	public ArrayList<Course> courses = new ArrayList<>();
	public NewsletterService  service = new NewsletterService();
	public String userPath="C:\\Users\\john_\\OneDrive\\Desktop\\user.csv";
	public String itemPath= "C:\\Users\\john_\\OneDrive\\Desktop\\items.csv";
	public String coursePath=  "C:\\Users\\john_\\OneDrive\\Desktop\\courses.csv";
	public String subscribersPath= "C:\\Users\\john_\\OneDrive\\Desktop\\subscribers.csv";
	
	private Database() {
		   
	}
	
	/*
	 * Singleton Static method to retrieve instance of database
	 */
	public static Database getInstance() {
	Database result = instance;
	if(result == null) {
		synchronized (Database.class){
			result = instance;
			if(result == null) {
				instance=result = new Database();
			}
		}
	}
		return result;
		
	}
	


	/*
	 * Loads user.csv to users ArrayList
	 */
	public void loadUsers() throws IOException {
		CsvReader reader = new CsvReader(userPath);
		reader.readHeaders();
		
		//Loads Users from csv file to users arraylist
		while(reader.readRecord()){ 
		
			if(reader.get("userType").equals("visitor")) {
				User user = new Visitor();
				user.setFirstName(reader.get("firstname"));
				user.setLastName(reader.get("lastname"));
				user.setId(Integer.valueOf(reader.get("id")));
				user.setEmail(reader.get("email"));
				user.setPassword(reader.get("password"));
				user.setUserType(reader.get("userType"));
				user.setStatus(reader.get("status"));
				users.add(user);
			} else if(reader.get("userType").equals("student")) {
				User user = new Student();
				user.setFirstName(reader.get("firstname"));
				user.setLastName(reader.get("lastname"));
				user.setId(Integer.valueOf(reader.get("id")));
				user.setEmail(reader.get("email"));
				user.setPassword(reader.get("password"));
				user.setUserType(reader.get("userType"));
				user.setStatus(reader.get("status"));
				users.add(user);
			} else if(reader.get("userType").equals("faculty")) {
				User user = new Faculty();
				user.setFirstName(reader.get("firstname"));
				user.setLastName(reader.get("lastname"));
				user.setId(Integer.valueOf(reader.get("id")));
				user.setEmail(reader.get("email"));
				user.setPassword(reader.get("password"));
				user.setUserType(reader.get("userType"));
				user.setStatus(reader.get("status"));
				users.add(user);
			}
			else if(reader.get("userType").equals("nonfaculty")) {
				User user = new NonFaculty();
				user.setFirstName(reader.get("firstname"));
				user.setLastName(reader.get("lastname"));
				user.setId(Integer.valueOf(reader.get("id")));
				user.setEmail(reader.get("email"));
				user.setPassword(reader.get("password"));
				user.setUserType(reader.get("userType"));
				user.setStatus(reader.get("status"));
				users.add(user);
			}
			
		}
	}
	
	public ArrayList<Student> loadStudents(String path) throws IOException {
		ArrayList<Student> students = new ArrayList<>();
		CsvReader reader = new CsvReader(path);
		reader.readHeaders();
		
		//Loads Users from csv file to users arraylist
		while(reader.readRecord()){ 
			Student student = new Student();
			//name,id,email,password
			student.setFirstName(reader.get("firstname"));
			student.setLastName(reader.get("lastname"));
			student.setId(Integer.valueOf(reader.get("id")));
			student.setEmail(reader.get("email"));
			students.add(student);
		}
		return students;
	}
	

	
	public void loadStudents() throws IOException {
		for(User u: users) {
			if(u.getUserType().equals("student")) {
				students.add(new Student(u.getFirstName(),u.getLastName(),u.getId(),u.getEmail(),u.getPassword(),u.getUserType(),u.getStatus()));
			}
		}
	}
	
	/*
	 * Loads items.csv to items ArrayList
	 */
	public void loadItems() throws IOException { 
		CsvReader reader = new CsvReader(itemPath); 
		reader.readHeaders(); 
		//Loads Users from csv file to items arraylist 
		while(reader.readRecord()){ 
			
			if(reader.get("type").equals("cd")) {
				Item item = new CD();
				item.setType(reader.get("type"));
				item.setName(reader.get("name")); 
				item.setId(Integer.valueOf(reader.get("id"))); 
				item.setLocation(reader.get("location")); 
				item.setPurchase(reader.get("purchase")); 
				item.setUserEmail(reader.get("email"));
				item.setPrice(Double.valueOf(reader.get("price")));
				items.add(item); 
			} else if(reader.get("type").equals("book")) {
				Item item = new Book();
				item.setType(reader.get("type"));
				item.setName(reader.get("name")); 
				item.setId(Integer.valueOf(reader.get("id"))); 
				item.setLocation(reader.get("location")); 
				item.setPurchase(reader.get("purchase")); 
				item.setUserEmail(reader.get("email"));
				item.setPrice(Double.valueOf(reader.get("price")));
				items.add(item); 
			}else if(reader.get("type").equals("magazine")) {
				Item item = new Magazine();
				item.setType(reader.get("type"));
				item.setName(reader.get("name")); 
				item.setId(Integer.valueOf(reader.get("id"))); 
				item.setLocation(reader.get("location")); 
				item.setPurchase(reader.get("purchase")); 
				item.setUserEmail(reader.get("email"));
				item.setPrice(Double.valueOf(reader.get("price")));
				items.add(item); 
			}else if(reader.get("type").equals("textbook")) {
				Item item = new Textbook();
				item.setType(reader.get("type"));
				item.setName(reader.get("name")); 
				item.setId(Integer.valueOf(reader.get("id"))); 
				item.setLocation(reader.get("location")); 
				item.setPurchase(reader.get("purchase")); 
				item.setUserEmail(reader.get("email"));
				item.setPrice(Double.valueOf(reader.get("price")));
				items.add(item); 
			}else if(reader.get("type").equals("board")) {
				Item item = new Board();
				item.setType(reader.get("type"));
				item.setName(reader.get("name")); 
				item.setId(Integer.valueOf(reader.get("id"))); 
				item.setLocation(reader.get("location")); 
				item.setPurchase(reader.get("purchase")); 
				item.setUserEmail(reader.get("email"));
				item.setPrice(Double.valueOf(reader.get("price")));
				items.add(item); 
			}else if(reader.get("type").equals("newsletter")) {
		        Item item = new Newsletter();
		        item.setType(reader.get("type"));
		        item.setName(reader.get("name")); 
		        item.setId(Integer.valueOf(reader.get("id"))); 
		        item.setLocation(reader.get("location")); 
		        item.setPurchase(reader.get("purchase")); 
		        item.setUserEmail(reader.get("email"));
		        item.setPrice(Double.valueOf(reader.get("price")));
		        items.add(item); 
		    }
			} 
		
		}
	
	/*
	 * When rent button is clicked, the item rented will be updated with its new users email.
	 * Reads the CSV file and once it finds the item with the ID that needs to be updated, the email will be updated.
	 */
	public void updateItem(Item item) throws IOException {
	    ArrayList<Item> updatedItems = new ArrayList<>();

	    // modifying the rented item by reading the csv file and copying all items to updatedItems 
	    CsvReader reader = new CsvReader(itemPath);
	    reader.readHeaders();
	    while (reader.readRecord()) {
	        Item currentItem = null;
	        String itemType = reader.get("type");
	        if (itemType.equals("cd")) {
	            currentItem = new CD();
	        } else if (itemType.equals("book")) {
	            currentItem = new Book();
	        } else if (itemType.equals("magazine")) {
	            currentItem = new Magazine();
	        }
	        
	        if(currentItem!=null) {
	            currentItem.setType(itemType);
	            currentItem.setName(reader.get("name"));
	            currentItem.setId(Integer.valueOf(reader.get("id")));
	            currentItem.setLocation(reader.get("location"));
	            currentItem.setPurchase(reader.get("purchase"));
	            currentItem.setUserEmail(reader.get("email"));
	            currentItem.setPrice(Double.valueOf(reader.get("price")));

	            // Update the item's email if it matches the ID of the item being rented
	            if (currentItem.getId() == item.getId()) {
	                currentItem.setUserEmail(item.getUserEmail());
	            }

	            updatedItems.add(currentItem);
	        }
	    }
	    reader.close();

	    // Write the modified email back to the CSV file
	    CsvWriter writer = new CsvWriter(new FileWriter(itemPath), ',');
	    writer.write("type");
	    writer.write("name");
	    writer.write("id");
	    writer.write("location");
	    writer.write("purchase");
	    writer.write("email");
	    writer.write("price");
	    writer.endRecord();
	    for (Item updatedItem : updatedItems) {
	        writer.write(updatedItem.getType());
	        writer.write(updatedItem.getName());
	        writer.write(String.valueOf(updatedItem.getId()));
	        writer.write(updatedItem.getLocation());
	        writer.write(updatedItem.getPurchase());
	        writer.write(updatedItem.getUserEmail());
	        writer.write(String.valueOf(updatedItem.getPrice()));
	        writer.endRecord();
	    }
	    writer.close();
	}

	public void loadNewsletterServiceSubscribers() throws IOException {
		CsvReader reader = new CsvReader(subscribersPath);
		reader.readHeaders();
			while (reader.readRecord()) {
				for(User u : users) {
					if(reader.get("id").equals(String.valueOf(u.getId()))) {
						service.subscribe(u);
					}
				}
				
			}
	}
	
	public boolean checkSubscribed(User user) {
		for(User u: service.getSubscribers()) {
			if(u.getId() == user.getId()) {
				return true;
			}
		}
		return false;
	}
	
	public void loadSubscription(User user) {
		if(checkSubscribed(user)) {
			user.rentItem(service.getNewsletter());
		}
	}
	
	public void addNewsletterServiceSubscribers() {
		try {		
			CsvWriter csvOutput = new CsvWriter(new FileWriter(subscribersPath, false), ',');
			//name,id,email,password
			csvOutput.write("firstname");
			csvOutput.write("lastname");
			csvOutput.write("id");
	    	csvOutput.write("email");
			csvOutput.write("userType");
			csvOutput.endRecord();

			// else assume that the file already has the correct header line
			// write out a few records
			for(User u: service.getSubscribers()){
				csvOutput.write(u.getFirstName());
				csvOutput.write(u.getLastName());
				csvOutput.write(String.valueOf(u.getId()));
				csvOutput.write(u.getEmail());
				csvOutput.write(u.getUserType());
				csvOutput.endRecord();
			}
			csvOutput.close();
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadNewsletterService() {
		for (Item i: items) {
			if(i.getType().equals("newsletter")) {
				service.setNewsletter(i);
			}
		}
	}
	
	
	public NewsletterService getNewsletterService() {
		return service;
	}

	

	
	
	/*
	 * Loads courses.csv to courses ArrayList
	 */
	public void loadCourses() throws IOException { 
		CsvReader reader = new CsvReader(coursePath); 
		reader.readHeaders(); 
		//Loads Users from csv file to items arraylist 
		while(reader.readRecord()){ 
			Course course = new Course(); 
			course.setTitle(reader.get("title"));
			course.setId(reader.get("id")); 
			course.setInstructor(reader.get("instructor"));
			courses.add(course);
		} 
	}
	
	
	/*
	 * Updates user.csv from users ArrayList
	 */
	public void update() throws Exception{
		try {		
				CsvWriter csvOutput = new CsvWriter(new FileWriter(userPath, false), ',');
				//name,id,email,password
				csvOutput.write("firstname");
				csvOutput.write("lastname");
				csvOutput.write("id");
		    	csvOutput.write("email");
				csvOutput.write("password");
				csvOutput.write("userType");
				csvOutput.write("status");
				csvOutput.endRecord();

				// else assume that the file already has the correct header line
				// write out a few records
				for(User u: users){
					csvOutput.write(u.getFirstName());
					csvOutput.write(u.getLastName());
					csvOutput.write(String.valueOf(u.getId()));
					csvOutput.write(u.getEmail());
					csvOutput.write(u.getPassword());
					csvOutput.write(u.getUserType());
					csvOutput.write(u.getStatus());
					csvOutput.endRecord();
				}
				csvOutput.close();
			
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	
	
	/*
	 * Iterates through users ArrayList checks if user is in database returns user if found
	 * otherwise returns null
	 */
	public User getUser(String email, String password) throws IOException {
		for(User u: users) {
			if(u.getEmail().equals(email) && u.getPassword().equals(password)) {
				return u;
			}
		}
		return null;
	}
	
	
	public Student getStudent(String email, String password) throws IOException {
		for(Student s: students) {
			if(s.getEmail().equals(email) && s.getPassword().equals(password)) {
				return s;
			}
		}
		return null;
	}
	
	
	/*
	 * Iterates through users to checks if registered if found return true
	 * otherwise false
	 */
	public boolean search(String email, String password) throws Exception {

		boolean result =false;
		for(User u: users) {
			if(u.getEmail().equals(email) && u.getPassword().equals(password)) {
				 result= true;
			}
		}
		return result;

	}
	/*
	 * Iterates through users to checks if status is "yes" if found return true
	 * otherwise false
	 */
	public boolean searchStatus(String email, String password) throws Exception {
		boolean result = false;
		for(User u: users) {
			if(u.getEmail().equals(email) && u.getPassword().equals(password)) {
				if(u.getStatus().contains("yes")) {
					result=true;
				}
			}
		}
		return result;	
	}
	
	/*
	 * Gets latest registered users id
	 */
	public int latestId() throws IOException {
		if(users.isEmpty()) {
			return 0;
		}else {
			return users.get(users.size()-1).getId();
		}
		
	}
	
	/*
	 * Registers user
	 * Updates user.csv
	 */
	public void registerUser(User user) throws Exception {
		users.add(user);
		update();
		
	}
	
	/*
	 * Loads stock of library into ArrayList stock
	 */
	public void loadStock() throws IOException {
		for(Item item: items) {
			if(item.getUserEmail().contains("library")) {
				stock.add(item);
			}
		}
	}
	
	public ArrayList<Item> getStock(){
		return stock;
	}
	
	public ArrayList<Course> getCourses(){
		return courses;
	}
	

	
	public ArrayList<Item> getItems(){
		return items;
	}
	
	public void loadEnroll() throws IOException{
		String path;
		for(Course course: courses) {
			path = "C:\\Users\\john_\\OneDrive\\Desktop\\" + course.getId() + ".csv";
			course.addStudents(loadStudents(path));
		}
		
	}
	
	public void loadCoursesETextbooks() throws IOException{
		for(Course course: courses) {
			for(Item eTextbook: items) {
				if(eTextbook.getType().equals("textbook") && eTextbook.getUserEmail().equals(course.getId())) {
					course.addETextbooks(eTextbook);
				}
			}
		}
		
	}
	
	/*
	 * Returns arraylist of student
	 */
	public ArrayList<Student> getCourseStudentList(String id) {
		for(Course course: courses) {
			if(course.getId().equals(id)) {
				return course.getStudents();
			}
		}
		return null;
	}
	
	public ArrayList<User> getUsers(){
		return users;
	}
	
	public static void main(String[] args) throws IOException {
        Database.getInstance().loadUsers();
        Database.getInstance().loadStudents();
        Database.getInstance().loadItems();
        Database.getInstance().loadCourses();
        Database.getInstance().loadEnroll();
        Database.getInstance().loadCoursesETextbooks();
        Database.getInstance().loadNewsletterService();
        Database.getInstance().loadNewsletterServiceSubscribers();
        System.out.println(Database.getInstance().service.getSubscribers());
        System.out.println(Database.getInstance().service.getSubscribers().get(2).rentedItems);
    } 

	

}