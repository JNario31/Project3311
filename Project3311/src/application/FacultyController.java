package application;

import java.io.IOException;

import items.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import users.User;

public class FacultyController {

	String email;
	String password;
	User user;
	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	ImageView yorkuLabel;
	
	@FXML
	ImageView textbookCover;

	@FXML
	private Button eTextbooksButton;

	@FXML
	private Button homeButton;

	@FXML
	private Button logoutButton;

	@FXML
	private Button newsletterButton;

	@FXML
	private Button notificationsButton;

	@FXML
	private Button purchaseButton;

	@FXML
	private TextField searchField;

	@FXML
	private Label sectionLabel;

	@FXML
	private Button storeButton;

	@FXML
	private Label userLabel;
	
	@FXML
	private Label descriptionLabel;
	
	@FXML
	private Label textbookNameLabel;
	
	@FXML
	private Button readNowButton;

	@FXML 
	private TableView<Item> itemTableView;

	@FXML
	private TableColumn<Item,String> itemNameTableColumn;

	@FXML
	private TableColumn<Item,String> itemTypeTableColumn;

	@FXML
	private TableColumn<Item,String> itemIdTableColumn;
	ObservableList<Item> itemSearchObservableList = FXCollections.observableArrayList();

	Image yorku = new Image(getClass().getResourceAsStream("YorkU.png"));

	public void displayImage() {
		yorkuLabel.setImage(yorku);
	}
	
	/*On home button click add user rentedItemsList() to observable list
	 *Displays on TableView
	 */
	@FXML
	private void switchToHomeView(ActionEvent event) throws IOException {
		itemSearchObservableList.clear();
		itemSearchObservableList.addAll(user.getRentedItemsList());
		sectionLabel.setText("Rented Items");
	}

	/*On store button click add item stock to observable list
	 * Displays on TableView
	 */
	@FXML
	private void switchToStoreView(ActionEvent event) throws IOException {
		itemSearchObservableList.clear();
		itemSearchObservableList.addAll(Database.getInstance().getStock());
		sectionLabel.setText("Store");
	}

	@FXML
	void switchToETextbooksView(ActionEvent event) throws IOException {
		itemSearchObservableList.clear();
		//itemSearchObservableList.addAll(user.getETextbooksList());
		sectionLabel.setText("Textbooks");

	}
	@FXML
	void initialize() {
		readNowButton.setVisible(false);
	}
	
	/*
	 * On eTextbooks button click the user is able to click a textbook form the 
	 * observable list, displayed in Table view. 
	 * A description of each textbook will be displayed when selected.
	 */
	@FXML
	void textbookSelection(MouseEvent event) throws IOException {
	    if ("eTextbooks".equals(sectionLabel.getText())) { // Check if the user is on the "eTextbooks" section
	        Item selectedTextbook = itemTableView.getSelectionModel().getSelectedItem();
	        if (selectedTextbook != null && "textbook".equals(selectedTextbook.getType())) { //only show the textbook items from rented list
	        	readNowButton.setVisible(true);
	        	String description = String.format("Name: %s\nType: %s\nID: %s", 
	                                                selectedTextbook.getName(), 
	                                                selectedTextbook.getType(), 
	                                                selectedTextbook.getId());
	            descriptionLabel.setText(description);
	            textbookNameLabel.setText(selectedTextbook.getName());
	            descriptionLabel.setVisible(true);
	            textbookNameLabel.setVisible(true);
	           
	            
	            Image textbookCoverImage = new Image(getClass().getResourceAsStream("TextbookPicture.png"));
	            textbookCover.setImage(textbookCoverImage);
	            textbookCover.setVisible(true); 
	        } else {
	            
	            textbookNameLabel.setText("");
	            descriptionLabel.setText("");
	            textbookCover.setImage(null);
	            textbookCover.setVisible(false);
	            readNowButton.setVisible(false);
	        }
	    } else {
	        
	        textbookNameLabel.setText("");
	        descriptionLabel.setText("");
	        textbookCover.setImage(null);
	        textbookCover.setVisible(false); 
	        readNowButton.setVisible(false);
	    }
	}

	/*On Log-out button click, the user is logged out and brought back to
	 * the Login page.
	 */
	@FXML
	void switchToLoginView(ActionEvent e) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void switchToNewsletterView(ActionEvent event) {
		sectionLabel.setText("Newsletter");

	}

	@FXML
	void switchToNotificationsView(ActionEvent event) {
		sectionLabel.setText("Notifications");

	}

	@FXML
	void switchToPurchaseView(ActionEvent event) {
		sectionLabel.setText("Purchase");

	}

	


	/*
	 *Displays user firstname on side bar
	 */
	public void displayUser() {
		userLabel.setText(user.getFirstName());
	}

	/*
	 * On transition from Login scene initializes local variables  
	 *Initializes cells for TableView
	 *Initializes TableView for user rented items
	 *Sets up search bar
	 */
	public void setUser(String email, String password) throws IOException {
		this.email = email;
		this.password=password;
		this.user = Database.getInstance().getUser(email,password);
		displayUser();


		Database.getInstance().loadStock();	//Loads library stock in database class
		user.loadRentedItemsList();			//Loads user rented items in User class
		//user.loadETextbooksList();         //Loads user eTextbooks items in the user class 

		itemSearchObservableList.addAll(user.getRentedItemsList());
		//itemSearchObservableList.addAll(user.getETextbooksList());
		itemNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		itemTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		itemIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

		itemTableView.setItems(itemSearchObservableList);	//sets itemTableView to itemSearchObservableList

		setupSearch();

	}

	/*
	 * Method sets up search bar for searchField and itemSearchObservableList
	 * Searches for item name, type or id
	 */
	private void setupSearch() {
		FilteredList<Item> filteredData = new FilteredList<>(itemSearchObservableList,b -> true);

		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Item ->{
				if(newValue.isEmpty() || newValue.isBlank() || newValue ==null) {
					return true;
				}
				String searchKeyword = newValue.toLowerCase();
				if(Item.getName().toLowerCase().indexOf(searchKeyword) >-1) {
					return true;
				}else if(Item.getType().toLowerCase().indexOf(searchKeyword) >-1) {
					return true;
				}else if(String.valueOf(Item.getId()).indexOf(searchKeyword) >-1) {
					return true;
				} else
					return false;
			});
		});

		SortedList<Item> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(itemTableView.comparatorProperty());	//binds TableView with sorted data
		itemTableView.setItems(sortedData);
	}



}