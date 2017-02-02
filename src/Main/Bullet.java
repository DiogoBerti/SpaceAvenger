package Main;

public class Bullet {
	
	private double posX;
	private double posY;
	private double width;
	private double height;
	private double bulletVel;
	private double bulletDmg;
	
	public double getPosX() {
		return posX;
	}
	public void setPosX(double posX) {
		this.posX = posX;
	}
	public double getPosY() {
		return posY;
	}
	public void setPosY(double posY) {
		this.posY = posY;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getBulletVel() {
		return bulletVel;
	}
	public void setBulletVel(double bulletVel) {
		this.bulletVel = bulletVel;
	}
	public Bullet(double posX, double posY, double width, double height, double bulletVel) {
		
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.bulletVel = bulletVel;
		this.bulletDmg = 1;
	}
	
	public double getBulletDmg() {
		return bulletDmg;
	}
	public void setBulletDmg(double bulletDmg) {
		this.bulletDmg = bulletDmg;
	}
	public void bulletShoot(Entity e){
		e.setShooting(true);
		this.posY += bulletVel;
		if(this.getPosY()<0){
			e.setShooting(false);
		}
		
	}
	
	
	
}
