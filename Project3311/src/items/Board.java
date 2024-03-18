package items;

public class Board extends Item{
	
	private int boardID;
	private String boardName;
	private double boardPrice;
	
	public Board(String type,String name, int ID, String location, String purchase, String email, double Price) {
		super(type, name, ID, location, purchase, email, Price);
		
	}
	public Board(){
		super();
	}
	
	public int getBoardID() {
		return boardID;
	}
	
	public void setBoardID(int boardID) {
		this.boardID = boardID;
	}
	
	public String getBoardName() {
		return boardName;
	}
	
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	
	public double getBoardPrice() {
		return boardPrice;
	}
	
	public void setBoardPrice(double boardPrice) {
		this.boardPrice = boardPrice;
	}
	
	public Board duplicate() {
		Board duplicatedBoard = new Board(null, this.boardName, this.boardID, null, null, null, this.boardPrice);
		return duplicatedBoard;
	}

}

