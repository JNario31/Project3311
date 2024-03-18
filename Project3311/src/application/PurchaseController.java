package application;

import java.io.IOException;
import java.util.List;

import items.Board;
import items.Item;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PurchaseController {
	
    @FXML
    private CheckBox academicCheckBox;

    @FXML
    private TextField cardNumberTextField;

    @FXML
    private TextField cardholderNameTextField;

    @FXML
    private CheckBox creditCheckBox;

    @FXML
    private TextField cvvTextField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private CheckBox debitCheckBox;

    @FXML
    private CheckBox mobileWalletCheckBox;

    @FXML
    private CheckBox nonAcademicCheckBox;

    @FXML
    private Button placeOrderButton;
    
    @FXML
    private Button goBackButton;
    
    @FXML
    private Label totalAmount;
    
    @FXML
    private TableColumn<Item, String> boardID;

    @FXML
    private TableColumn<Item, String> boardName;

    @FXML
    private TableColumn<Item, String> boardPrice;

    @FXML
    private TableView<Item> shoppingCartTableView;
    
    public void initialize() {
    	boardID.setCellValueFactory(new PropertyValueFactory<>("id"));
    	boardName.setCellValueFactory(new PropertyValueFactory<>("Name"));
    	boardPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
    	academicCheckBox.setSelected(false);
    	nonAcademicCheckBox.setSelected(false);
    	debitCheckBox.setSelected(false);
    	creditCheckBox.setSelected(false);
    	mobileWalletCheckBox.setSelected(false);
    	}
    
    public void addBoardToShoppingCart(Item board) throws IOException{
    	//shoppingCartTableView.getItems().add(board);
    	
    	double discountedPrice = (int) (board.getPrice() * 0.7);//30% discounts for students on boards 
    	Board discountedBoard = new Board(board.getType(), board.getName(), board.getId(), board.getLocation(), board.getPurchase(), board.getUserEmail(), discountedPrice);
    	
    	shoppingCartTableView.getItems().addAll(board, discountedBoard);//add the board with its discounted price
    	
    	if(academicCheckBox.isSelected()) {
    		nonAcademicCheckBox.setSelected(false);
    		totalAmount.setText(String.format("$%.2f", board.getPrice()));
    	}
    	if(nonAcademicCheckBox.isSelected()) {
    		academicCheckBox.setSelected(false);
    		totalAmount.setText(String.format("$%.2f", discountedBoard.getPrice()));
    	}
    	
    
    }
    
    
    public void totalAmount(ActionEvent event) throws IOException{
    	double totalPrice = 0.0;
    	
    	if(academicCheckBox.isSelected()) {
    		nonAcademicCheckBox.setSelected(false);
    		totalPrice = shoppingCartTableView.getItems().get(1).getPrice();
    	} else if(nonAcademicCheckBox.isSelected()) {
    		academicCheckBox.setSelected(false);
    		totalPrice = shoppingCartTableView.getItems().get(0).getPrice();
    	}
    	totalAmount.setText(String.format("$%.2f", totalPrice));
    	
    	if(event.getSource().equals(placeOrderButton) && checkTextFields() == false) {
			
		}else if (event.getSource().equals(placeOrderButton) && checkTextFields() == true && (debitCheckBox.isSelected() || creditCheckBox.isSelected() || mobileWalletCheckBox.isSelected())){
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("You Have Succsessfully purchased your Item, Yay!");
		alert.setTitle("Order");
		alert.setHeaderText(null);
		alert.showAndWait();
    }
    }
    
    
    public void payment(ActionEvent event) throws IOException{
    	CheckBox selectedCheckBox = (CheckBox) event.getSource();
    	
    	if (selectedCheckBox.isSelected()) {
            if (selectedCheckBox.equals(debitCheckBox)) {
                creditCheckBox.setSelected(false);
                mobileWalletCheckBox.setSelected(false);
            } else if (selectedCheckBox.equals(creditCheckBox)) {
                debitCheckBox.setSelected(false);
                mobileWalletCheckBox.setSelected(false);
            } else if (selectedCheckBox.equals(mobileWalletCheckBox)) {
                debitCheckBox.setSelected(false);
                creditCheckBox.setSelected(false);
            }
        }
    }
    
    
    public boolean checkTextFields() {
    	if(cardNumberTextField.getText().isEmpty()|| cardholderNameTextField.getText().isEmpty()||cvvTextField.getText().isEmpty()|| datePicker.getValue() == null) {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Please fill out all areas");
			alert.setTitle("ERROR");
			alert.setHeaderText(null);
			alert.showAndWait(); 
			return false;
    	}
    	return true;
    }
    
    public void onGoBackButtonAction(ActionEvent e) {

    }
}































