package dto;

public class Item {
	private static int num;
	private int itemNum;
	private String categoryName;
	private String itemName;
	private int price;
	
	public static int getNum() {
		return num;
	}
	public static void setNum(int num) {
		Item.num = num;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Item(int itemNum, String categoryName, String itemName, int price) {
		super();
		this.itemNum = itemNum;
		this.categoryName = categoryName;
		this.itemName = itemName;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "%d %d %s %s %d".formatted(num,itemNum,categoryName,itemName,price);
	}
	
}
