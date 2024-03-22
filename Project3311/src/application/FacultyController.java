package application;

import java.io.IOException;
import java.time.LocalDate;

import items.Board;
import items.Book;
import items.CD;
import items.Item;
import items.Magazine;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import users.Faculty;
import users.Student;

public class FacultyController extends AppController{

	String email;
	String password;
	Faculty user;
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
	private Button coursesButton;

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
    private Button readNewsletterButton; //added
    
    @FXML
    private Button unsubscribeButton;    //added 


	@FXML
	private Button readNowButton;

	@FXML 
	private Button rentButton;

	@FXML
	private Button purchaseButton;

	@FXML
	private Button requestButton;

	@FXML
	private Button requestItemButton;

	@FXML
	private VBox requestFormVBox;

	@FXML
	private TextField firstnameRequestTextField;

	@FXML
	private TextField lastnameRequestTextField;

	@FXML
	private TextField booknameRequestTextField;

	@FXML
	private Board selectedBoard;
	

	@FXML
	private ComboBox<String> requestTypeComboBox;

	@FXML 
	private TableView<Item> itemTableView;
	
	@FXML
    private Button subscribeButton; 

	@FXML
	private TableColumn<Item,String> itemNameTableColumn;

	@FXML
	private TableColumn<Item,String> itemTypeTableColumn;

	@FXML
	private TableColumn<Item,String> itemIdTableColumn;

	@FXML
	private TableView<Course> courseTableView;

	@FXML
	private TableColumn<Course,String> courseNameColumn;

	@FXML
	private TableColumn<Course,String> courseInstructorColumn;

	@FXML
	private TableColumn<Course,String> courseDateColumn;
 

	ObservableList<Item> itemSearchObservableList = FXCollections.observableArrayList();
	ObservableList<Course> courseObservableList=FXCollections.observableArrayList();
	
	//Register faculty user as a listener for textbook notifications on a new version 
	private final NewTextbookEdition newTextbookEdition = new NewTextbookEdition();
	private final TextbookListener textbookListener = new TextbookNotificationHandler(); 

	Image yorku = new Image(getClass().getResourceAsStream("YorkU.png"));

	public void displayImage() {
		yorkuLabel.setImage(yorku);
	}

	@FXML
	void initialize() {
		newTextbookEdition.addListener(textbookListener); //making a new listener to watch the change
		readNowButton.setVisible(false);
		rentButton.setVisible(false);
		courseTableView.setVisible(false);
		itemTableView.setVisible(true);
		readNewsletterButton.setVisible(false); //added
        unsubscribeButton.setVisible(false);    //added
        requestItemButton.setVisible(false);    //added
        subscribeButton.setVisible(false);        //added 
		removeTextbookDetails();

		requestFormVBox.setVisible(false);
		purchaseButton.setVisible(false);
		requestTypeComboBox.setItems(FXCollections.observableArrayList("Course Teaching","Self-Improvment"));
		
		
	}

		
	/*On home button click add user rentedItemsList() to observable list
	 *Displays on TableView
	 */
	@FXML
	private void switchToHomeView(ActionEvent event) throws IOException {
		itemSearchObservableList.clear();
		itemTableView.setVisible(true);
		courseTableView.setVisible(false);
		requestFormVBox.setVisible(false);
		requestItemButton.setVisible(false);
		itemSearchObservableList.addAll(user.getRentedItemsList());
		sectionLabel.setText("Home");
	}	
	
	//show an alert when the user is in the facculty
	//Notice that the NewTextbookEdition knows nothing about the ui, it only knows that there 
	// are things watching it.
	//We register a listerner with a version, and vertime the version changes, it sends an event
	//that event makes the Ui chnage (makes switchToTextbook update iteself).
//	private void updateTextbookLabel() {
//		Alert alert = new Alert(AlertType.INFORMATION);
//		alert.setContentText("New Edition is availible");
//		alert.setTitle("New Edition");
//		alert.setHeaderText(null);
//		alert.showAndWait();
//	}
	
	
	//added
    @FXML
    private void readNewsletterButtonButtonOnAction(ActionEvent e) throws IOException {
       	FXMLLoader loader = new FXMLLoader(getClass().getResource("Newsletter.fxml"));
        Parent root = loader.load();
        NewsletterController newsletter = loader.getController();
        newsletter.setUser();
        
        if (root != null) {
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } 
    }
    
    @FXML
    private void unsubscribeButtonOnAction(ActionEvent e) throws IOException {
        Database.getInstance().getNewsletterService().unsubscribe(user);
        
    } 
	
	 
	@FXML
    void subscribeButtonOnAction(ActionEvent e) {
        Database.getInstance().getNewsletterService().subscribe(user);
    } 


	
	@FXML
	void switchToETextbooksView(ActionEvent event) throws IOException {
		itemSearchObservableList.clear();
		itemTableView.setVisible(true);
		courseTableView.setVisible(false);
		requestFormVBox.setVisible(false);
		requestItemButton.setVisible(false);
		readNewsletterButton.setVisible(false); //added
        unsubscribeButton.setVisible(false);
		itemSearchObservableList.addAll(user.getTextbooksList());
		//When user clicks on textbook section it will check for new editions in the database. 
		Database.getInstance().checkForNewTextbookEdition(newTextbookEdition);

		sectionLabel.setText("Textbooks");
	}
		

	@FXML
	private void switchToStoreView(ActionEvent event) throws IOException {
		itemSearchObservableList.clear();
		itemTableView.setVisible(true);
		courseTableView.setVisible(false);
		requestFormVBox.setVisible(false);
		requestItemButton.setVisible(false);
		readNewsletterButton.setVisible(false); //added
        unsubscribeButton.setVisible(false);
		itemSearchObservableList.addAll(Database.getInstance().getStock());
		sectionLabel.setText("Store");

		//When the rent Button is clicked 
		rentButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Item selectedItem = itemTableView.getSelectionModel().getSelectedItem();//get the item selected from tableView

				user.rentItem(selectedItem); // Rent a item from rentItem in User
				itemSearchObservableList.remove(selectedItem); // Remove the Object from the observable list 
				
				selectedItem.setDate(LocalDate.now()); //set the rental date (today's Date)
				LocalDate dueDate = LocalDate.now().plusDays(1); //Set the due date to 30 days from today
				selectedItem.setDueDate(dueDate);
				
				//Compare the rented date and the due date (less than 24hours for warning)
				//For demo due date has been set to 1 day 
				LocalDate rentedDate = LocalDate.now(); 
				LocalDate approchingDueDate = selectedItem.getDueDate().minusDays(1);
				if(rentedDate.isEqual(approchingDueDate)) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setContentText("Item is due in less than 24 hours!!!!!!");
					alert.setTitle("Warning");
					alert.setHeaderText(null);
					alert.showAndWait();
					
				}

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("Item rented successfully!");
				alert.showAndWait();

				// Update the users email in item.CSV file, depending on the item type 
				Item rentedItem = null;
				if (selectedItem instanceof CD) {
					rentedItem = new CD();
				} else if (selectedItem instanceof Book) {
					rentedItem = new Book();
				} else if (selectedItem instanceof Magazine) {
					rentedItem = new Magazine();
				}

				//Update the email for the item using it's Id
				rentedItem.setId(selectedItem.getId());
				rentedItem.setUserEmail(user.getEmail());

				try {
					Database.getInstance().updateItem(rentedItem);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
	}
	
	



	@FXML
	void textbookSelection(MouseEvent event) throws IOException {
		Item selectedTextbook = itemTableView.getSelectionModel().getSelectedItem();
		if ("Textbooks".equals(sectionLabel.getText())) { // Check if user is in "Textbooks" section
			if (selectedTextbook != null && "textbook".equals(selectedTextbook.getType())) { // Check if the item is a item textbook
				readNowButton.setVisible(true);
				readNewsletterButton.setVisible(false);    //added
		        unsubscribeButton.setVisible(false);    //added 
				rentButton.setVisible(false);
				purchaseButton.setVisible(false);
				itemDescription(selectedTextbook);
				textbookNameLabel.setText(selectedTextbook.getName());
				descriptionLabel.setVisible(true);
				textbookNameLabel.setVisible(true);
				Image textbookCoverImage = new Image(getClass().getResourceAsStream("TextbookPicture.png"));
				textbookCover.setImage(textbookCoverImage);
				textbookCover.setVisible(true); 
			} else {
				removeTextbookDetails();
			}
		} else if ("Store".equals(sectionLabel.getText())) { // Check if user is in "Store" section
			if (selectedTextbook != null) { // Check if any item is selected
				rentButton.setVisible(true);
				readNewsletterButton.setVisible(false);    //added
		        unsubscribeButton.setVisible(false);    //added 
				readNowButton.setVisible(false);
				itemDescription(selectedTextbook);
				textbookNameLabel.setText(selectedTextbook.getName());
				descriptionLabel.setVisible(true);
				if (selectedTextbook.getType().contains("board")) {
					purchaseButton.setVisible(true);
					rentButton.setVisible(false);
					subscribeButton.setVisible(false);
				} else {
					purchaseButton.setVisible(false);
				}
				
				if(selectedTextbook.getType().contains("newsletter")) {
					rentButton.setVisible(false);
					purchaseButton.setVisible(false);
					subscribeButton.setVisible(true);
				}
				
				else {
					subscribeButton.setVisible(false);
				}
			} else {
				rentButton.setVisible(false);
			}
		} else if("Home".equals(sectionLabel.getText())) {                //added
            if(selectedTextbook != null && "newsletter".contains(selectedTextbook.getType())) {    //added
                readNewsletterButton.setVisible(true);    //added
                unsubscribeButton.setVisible(true);    //added
            }else {
                removeTextbookDetails();
            }
            
        } 
		
		
		else {
			removeTextbookDetails();
		}
	}
	

	private void itemDescription(Item item) {
		String description = String.format("Name: %s\nType: %s\nID: %s", 
				item.getName(), 
				item.getType(), 
				item.getId());

		if(item.getType().equals("board")) {
			description = String.format("Name: %s\nType: %s\nID: %s\nPirce: $ %s", 
					item.getName(), 
					item.getType(), 
					item.getId(),
					item.getPrice());
		}
		descriptionLabel.setText(description);
		textbookNameLabel.setText(item.getName());
		descriptionLabel.setVisible(true);
	}

	private void removeTextbookDetails() {
		textbookNameLabel.setText("");
		descriptionLabel.setText("");
		textbookCover.setImage(null);
		textbookCover.setVisible(false);
		readNowButton.setVisible(false);
		rentButton.setVisible(false);
		subscribeButton.setVisible(false);        //added
        unsubscribeButton.setVisible(false);     //added
        readNewsletterButton.setVisible(false);    //added 
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
	void switchToCoursesView(ActionEvent event) {
		removeTextbookDetails();
		sectionLabel.setText("Courses");
		if("Courses".equals(sectionLabel.getText())) {
			courseTableView.setVisible(true);
			itemTableView.setVisible(false);
			requestFormVBox.setVisible(false);
			requestItemButton.setVisible(false);

			courseObservableList.clear();
			courseObservableList.addAll(user.getCourses());
		}else {
			courseTableView.setVisible(false);
			itemTableView.setVisible(true);
		}
	}

	@FXML
	void switchToRequestView(ActionEvent event) {
		sectionLabel.setText("Request");
		if("Request".equals(sectionLabel.getText())) {
			requestItemButton.setVisible(true);
			requestFormVBox.setVisible(true);

			if(event.getSource() == requestItemButton) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("Request Submitted!");
				alert.setTitle("Request Submission");
				alert.setHeaderText(null);
				alert.showAndWait();
			}
		} else {

			requestFormVBox.setVisible(false);
			requestItemButton.setVisible(false);
		}
	}


	@FXML
	void switchToPurchaseView(ActionEvent e) throws IOException {
		sectionLabel.setText("Purchase");
        Item selectedBoard = itemTableView.getSelectionModel().getSelectedItem();//Get the orginal board the user clicked on in store
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Purchase.fxml"));
        Parent root = loader.load();
        PurchaseController purchaseController = loader.getController();
        purchaseController.setUser();
        purchaseController.addBoardToShoppingCart(selectedBoard); //add the item to the shopping cart 
        
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show(); 
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
	 public void setFaculty() throws IOException {
		 	this.user=(Faculty) StoreUser.getInstance().getUser();
			this.email=user.getEmail();
			this.password=user.getPassword();
	        displayUser();

	        sectionLabel.setText("Home");    //added 
	            //Loads library stock in database class
	        user.loadRentedItemsList();            //Loads user rented items in User class
	        user.enrollCourse();                //enrolls students courses
	        user.loadTextbooksList();         //Loads user eTextbooks items in the user class 
	        
	        
	        

	        itemSearchObservableList.addAll(user.getRentedItemsList());
	        itemNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
	        itemTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
	        itemIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
	        
	        courseObservableList.addAll(user.getCourses());
	        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
	        courseInstructorColumn.setCellValueFactory(new PropertyValueFactory<>("instructor"));
	        
	        courseTableView.setItems(courseObservableList);
	        itemTableView.setItems(itemSearchObservableList);    //sets itemTableView to itemSearchObservableList

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