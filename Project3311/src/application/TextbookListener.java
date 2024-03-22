package application;

public interface TextbookListener {
	//Observer
    //Interface for observer pattern to watch the NewTxtbookEdition for any changes
    //Handle notifications for new textbook editions.
    void onReadingChange();
}
