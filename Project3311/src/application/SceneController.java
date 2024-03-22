package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.User;

public class SceneController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private Button loginButton;
	@FXML
	private Button registerButton;
	@FXML
	private Label loginMessageLabel;
	@FXML
	private TextField emailTextField;
	@FXML
	private PasswordField passwordPasswordField;
	
	public static void main(String[] args) throws IOException {
		 Database.getInstance().loadUsers();
         Database.getInstance().loadStudents();
         Database.getInstance().loadFaculty();
         Database.getInstance().loadItems(); //added
         Database.getInstance().loadStock(); //added2
         Database.getInstance().loadCourses();
         Database.getInstance().loadEnroll();
         Database.getInstance().loadCoursesETextbooks();
         Database.getInstance().loadCoursesTextbooks(); //added
         Database.getInstance().loadNewsletterService();
         Database.getInstance().loadNewsletterServiceSubscribers();
         User user = StoreUser.getInstance().requestUser("123456", "123A#a");
         System.out.println(user);
	}

	
	/*
	 * On login button click checks if all fields are entered if not send error message accordingly
	 * If email and password is registered and status is verified transition to corresponding app page.
	 * The user is retrieved from the Database to determine which scene to load using the User's userType. 
	 */
	public void loginButtonOnAction(ActionEvent e) throws Exception {
		if (!emailTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()) {
			if (validateLogin(emailTextField.getText(), passwordPasswordField.getText()) == false) {
				loginMessageLabel.setText("User is not registered.");
			} else if (validateStatus(emailTextField.getText(), passwordPasswordField.getText()) == false) {
				loginMessageLabel.setText("User registration is pending for authorization.");
			} else {
				String email = emailTextField.getText();
				String password = passwordPasswordField.getText();

				User user = StoreUser.getInstance().requestUser(email, password);
				if (user != null) {
					String userType = user.getUserType();
					FXMLLoader loader = null;
					Parent root = null;

					if (userType.equals("student")) {
						loader = new FXMLLoader(getClass().getResource("Student.fxml"));
						root = loader.load();
						StudentController student = loader.getController();
						student.setStudent();
						
					} else if (userType.equals("faculty")) {
						loader = new FXMLLoader(getClass().getResource("Faculty.fxml"));
						root = loader.load();
						FacultyController faculty = loader.getController();
						faculty.setFaculty(); //added
					} else {
						loader = new FXMLLoader(getClass().getResource("App.fxml"));
						root = loader.load();
						AppController app = loader.getController();
						app.setUser();
					}

					if (root != null) {
						stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
						scene = new Scene(root);
						stage.setScene(scene);
						stage.show();
					}
				}
			}
		} else {
			if (emailTextField.getText().isBlank() && passwordPasswordField.getText().isBlank()) {
				loginMessageLabel.setText("Enter a valid email and password.");
			} else if (emailTextField.getText().isBlank()) {
				loginMessageLabel.setText("Enter a valid email.");
			} else {
				loginMessageLabel.setText("Enter valid password.");
			}
		}
	}



	/*
	 * On register button click transition to register stage
	 */
	public void registerButtonOnAction(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/*
	 * returns boolean depending on client registration
	 */
	public boolean validateLogin(String email, String password) throws Exception {
		return Database.getInstance().search(email, password);
	}

	/*
	 * returns boolean depending on client registration status
	 */
	public boolean validateStatus(String email, String password) throws Exception {
		return Database.getInstance().searchStatus(email, password);
	}




}