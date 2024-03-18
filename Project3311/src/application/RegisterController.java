package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.Faculty;
import users.NonFaculty;
import users.Student;
import users.User;
import users.Visitor;

public class RegisterController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML 
	Button cancelRegisterButton;
	
	@FXML
	Button registerRegisterButton;
	
	@FXML
	TextField firstnameRegisterTextField;
	
	@FXML
	TextField lastnameRegisterTextField;
	
	@FXML
	TextField emailRegisterTextField;
	
	@FXML
	TextField passwordRegisterPasswordField;
	
	@FXML
	Label errorMessageTextField;
	
	@FXML 
	CheckBox facultyCheckBox;
	
	@FXML
	CheckBox visitorCheckBox;
	
	@FXML 
	CheckBox studentCheckBox;
	
	@FXML
	CheckBox nonFacultyCheckBox;
	
	/*
	 * On cancel button click return to login window
	 */
	public void cancelButtonOnAction(ActionEvent e) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	/*
	 * On register button click registers user and transitions to Login window depending on Boolean cases
	 * Displays error messages accordingly
	 */
	public void registerRegisterButtonOnAction(ActionEvent e) throws Exception {
		
		String status = setVerifiedStatus();	//creates status for registering user
		String userType = getUserType();		//creates type for registering user
		if(firstnameRegisterTextField.getText().isBlank() || lastnameRegisterTextField.getText().isBlank() || passwordRegisterPasswordField.getText().isBlank() || emailRegisterTextField.getText().isBlank()) {
			errorMessageTextField.setText("Fill in all Areas");
		}
		else if(passwordRegisterPasswordField.getText().length()<6){
			errorMessageTextField.setText("Password must have 6 or more characters");
		}
		else if(!passwordRegisterPasswordField.getText().matches(".*[a-z].*")){
			errorMessageTextField.setText("Password must have a lowercase");
		}else if(!passwordRegisterPasswordField.getText().matches(".*[A-Z].*")){
			errorMessageTextField.setText("Password must have a uppercase");
		}else if(!passwordRegisterPasswordField.getText().matches(".*[0-9].*")){
			errorMessageTextField.setText("Password must have a number");
		}else if(!passwordRegisterPasswordField.getText().matches(".*[!@#$].*")){
			errorMessageTextField.setText("Password must have a Special Character [!@#$]");
		}else if(!validSelection()){
			errorMessageTextField.setText("Select registration type");
		}
		else if(isValidPassword(passwordRegisterPasswordField.getText())){
			
			//registers user
			if(getUserType().equals("student")) {
				Database.getInstance().registerUser(new Student(firstnameRegisterTextField.getText(), lastnameRegisterTextField.getText(), newId(), emailRegisterTextField.getText(), passwordRegisterPasswordField.getText(),userType ,status));
			}else if(getUserType().equals("visitor")){
				Database.getInstance().registerUser(new Visitor(firstnameRegisterTextField.getText(), lastnameRegisterTextField.getText(), newId(), emailRegisterTextField.getText(), passwordRegisterPasswordField.getText(),userType ,status));
			}else if(getUserType().equals("faculty")){
				Database.getInstance().registerUser(new Faculty(firstnameRegisterTextField.getText(), lastnameRegisterTextField.getText(), newId(), emailRegisterTextField.getText(), passwordRegisterPasswordField.getText(),userType ,status));
			}else if(getUserType().equals("nonfaculty")){
				Database.getInstance().registerUser(new NonFaculty(firstnameRegisterTextField.getText(), lastnameRegisterTextField.getText(), newId(), emailRegisterTextField.getText(), passwordRegisterPasswordField.getText(),userType ,status));
			}
			

			//returns to login stage
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}

	/*
	 * Prevents multiple check boxes from being selected
	 */
	@FXML
	private void handleCheckBoxSelection(ActionEvent event) {
	    CheckBox selectedCheckBox = (CheckBox) event.getSource();

	    if (selectedCheckBox.isSelected()) {
	        if (selectedCheckBox != facultyCheckBox) {
	            facultyCheckBox.setSelected(false);
	        }
	        if (selectedCheckBox != visitorCheckBox) {
	            visitorCheckBox.setSelected(false);
	        }
	        if (selectedCheckBox != studentCheckBox) {
	            studentCheckBox.setSelected(false);
	        }
	        if (selectedCheckBox != nonFacultyCheckBox) {
	            nonFacultyCheckBox.setSelected(false);
	        }
	    }
	}
	
	/*
	 * returns user type depending on checkbox
	 */
	public String getUserType() {
		if(facultyCheckBox.isSelected()) {
			return "faculty";
		}
		else if (studentCheckBox.isSelected()) {
			return "student";
		}
		else if (visitorCheckBox.isSelected()) {
			return "visitor";
		}
		else if (nonFacultyCheckBox.isSelected()) {
			return "nonfaculty";
		}
		else {
			return "";
		}
	}
	
	/*
	 * returns verified status depending on checkbox
	 */
	public String setVerifiedStatus() {
		if(facultyCheckBox.isSelected()) {
			return "pending";
		}
		else if (studentCheckBox.isSelected()) {
			return "pending";
		}
		else if (nonFacultyCheckBox.isSelected()) {
			return "pending";
		}
		return "yes";
		
	}
	
	/*
	 * to ensure a check box selection was made
	 */
	public boolean validSelection() {
		return facultyCheckBox.isSelected() || studentCheckBox.isSelected() || nonFacultyCheckBox.isSelected() ||visitorCheckBox.isSelected();
	}
	
	/*
	 * Generates new Id for registered user
	 */
	public int newId() throws IOException {
		return Database.getInstance().latestId()+1;
	}
	
	/*
	 * To determine if a valid password was entered
	 */
	private boolean isValidPassword(String password) {
		if(password.length()<6) {
			return false;
		}
		 boolean hasLower = password.matches(".*[a-z].*"); 
		 boolean hasUpper = password.matches(".*[A-Z].*"); 
		 boolean hasNumber = password.matches(".*[0-9].*"); 
		 boolean hasSpecial = password.matches(".*[!@#$].*"); 
		    
		 return hasLower && hasUpper && hasNumber && hasSpecial; 
	}

}