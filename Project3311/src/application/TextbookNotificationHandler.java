package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TextbookNotificationHandler implements TextbookListener {

    //concrete Observer
    @Override
    public void onReadingChange() {
        //Notify the user about the new edition of the textbooks
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("New edition of textbook available!");
        alert.showAndWait();

    }

}