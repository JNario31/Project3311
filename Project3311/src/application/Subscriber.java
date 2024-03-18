package application;

public interface Subscriber {
	void accept(Notifications visitor);
}