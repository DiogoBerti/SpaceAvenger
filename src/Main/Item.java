package Main;

import java.util.Random;

public class Item extends Entity {

	private int itemType;
	Random r = new Random();
	
	
	public int getItemType() {
		return itemType;
	}


	public void setItemType(int itemType) {
		this.itemType = itemType;
	}


	public Item(double posX, double posY, double width, double height, int hP) {
		super(posX, posY, width, height, hP);
		this.itemType = r.nextInt(3)+1;
	}

	
	
}
