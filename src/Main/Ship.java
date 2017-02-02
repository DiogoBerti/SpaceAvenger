package Main;

public class Ship extends Entity {
	
	private Bullet bullet;
	private int shootCount;
	private int maxHp;
	
	
	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public Ship(double posX, double posY, double width, double height, int hP) {
		super(posX, posY, width, height, hP);
		this.maxHp = 10;
	}

	public Bullet getBullet() {
		return bullet;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}

	public int getShootCount() {
		return shootCount;
	}

	public void setShootCount(int shootCount) {
		this.shootCount = shootCount;
	}
	
	
	public void shoot(){
		
		
				
	}
	

}
