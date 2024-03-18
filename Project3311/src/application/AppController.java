package application;


import java.io.IOException;
import java.net.URL;
import java.util.stream.Collectors;

import items.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import users.User;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


public class AppController{
	
	String email;
	String password;
	User user;
	
	@FXML
	Label label;
	@FXML
	Label userLabel;
	@FXML
	Button homeButton;
	@FXML
	Button storeButton;
	@FXML
	private TextField searchField;
	@FXML
	Button button;
	@FXML 
	private TableView<Item> itemTableView;
	@FXML
	private TableColumn<Item,String> itemNameTableColumn;
	@FXML
	private TableColumn<Item,String> itemTypeTableColumn;
	@FXML
	private TableColumn<Item,String> itemIdTableColumn;
	ObservableList<Item> itemSearchObservableList = FXCollections.observableArrayList();
	
	

	
	/*On home button click add user rentedItemsList() to observable list
	 *Displays on TableView
	 */
	@FXML
	private void switchToHomeView(ActionEvent event) throws IOException {
		itemSearchObservableList.clear();
		itemSearchObservableList.addAll(user.getRentedItemsList());
	}
	
	/*On store button click add item stock to observable list
	 * Displays on TableView
	 */
	@FXML
    private void switchToStoreView(ActionEvent event) throws IOException {
		itemSearchObservableList.clear();
		itemSearchObservableList.addAll(Database.getInstance().getStock());
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
		
		itemSearchObservableList.addAll(user.getRentedItemsList());
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