package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewTextbookEdition  implements Subject2{

    //Concrete Subject
    //notify its listeners when a new edition is available.

    private List<TextbookListener> listeners  = new ArrayList<>();

    //Listerners can watch the NewTextBookEdition for changes
    @Override
    public void addListener(TextbookListener listener) {
        listeners.add(listener);
    }
    @Override
    public void removeListener(TextbookListener listener) {
        listeners.add(listener);

    }

    //When the Version of the textbook changes we will notify all the listiners
    @Override
    public void notifyListeners() {
        for(TextbookListener listener: listeners) {
            listener.onReadingChange();
        }
    }
}