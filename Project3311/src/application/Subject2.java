package application;

public interface Subject2 {
	//Subject
    void addListener(TextbookListener listener);
    void removeListener(TextbookListener listener);
    void notifyListeners();
}
