package items;

public class Newsletter extends Item{
	
	//added
	private String link="myaccount.nytimes.com/";
	
	public Newsletter(String type,String name, int ID, String location, String purchase, String email, double Price) {
		super(type, name, ID, location, purchase, email, Price);
		
	}
	public Newsletter(){
		super();
	}
	
	//added
	public void setLink(String link) {
		this.link=link;
	}
	
	//added
	public String getLink() {
		return link;
	}

}

