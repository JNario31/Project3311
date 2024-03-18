package items;

public class Textbook extends Item{
	
	public Textbook(String type,String name, int ID, String location, String purchase, String email, double Price) {
		super(type, name, ID, location, purchase, email, Price);
		
	}
	public Textbook(){
		super();
	}

}
