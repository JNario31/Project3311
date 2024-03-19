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

public class NewsletterController implements Initializable{
	
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private WebView webView;
	
	private WebEngine engine;
	
	private String link;
	
	@FXML
	private Button returnButton;

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
		Parent root = FXMLLoader.load(getClass().getResource("Student.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
