package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	/*Start method for application
	 * Database.getInstance().load(); Loads users from users.csv
	 * Database.getInstance().load2(); Loads items from items.csv
	 * */
	 @Override
	    public void start(Stage primaryStage) {
	        try {
	            
	            
	            Database.getInstance().loadUsers();
	            Database.getInstance().loadStudents();
	            
	            Database.getInstance().loadItems();
	            Database.getInstance().loadCourses();
	            Database.getInstance().loadEnroll();
	            Database.getInstance().loadCoursesETextbooks();
	            Database.getInstance().loadNewsletterService();
	            Database.getInstance().loadNewsletterServiceSubscribers();
	         
	            
	            
	            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
	            Scene scene = new Scene(root);
	            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	            primaryStage.setScene(scene);
	            primaryStage.show();
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	    } 
	
	public static void main(String[] args) {
		launch(args);
	}
}
