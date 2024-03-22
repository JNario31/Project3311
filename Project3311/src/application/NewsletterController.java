package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import users.User;

public class NewsletterController implements Initializable{
	
	private User user;
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private WebView webView;
	
	private WebEngine engine;
	
	private String link;
	
	@FXML
	private Button returnButton;
	private String email;
	private String password;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		engine=webView.getEngine();
		this.link =Database.getInstance().getNewsletterService().getNewsletter().getLink();
		loadPage();
		
	}
	
	public void loadPage() {
		engine.load("http://"+link);
	}
	
	@FXML
	private void returnButtonOnAction(ActionEvent e) throws IOException{
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
	
	public void setUser() throws IOException {
		this.user=StoreUser.getInstance().getUser();
		this.email=user.getEmail();
		this.password=user.getPassword();
		
	}

}
