package Main;

public abstract class Entity {

	private double posX;
	private double posY;
	private double width;
	private double height;
	private int hP;
	private boolean alive = true;
	private boolean shooting = false;
	
	
	public int gethP() {
		return hP;
	}

	public void sethP(int hP) {
		this.hP = hP;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isShooting() {
		return shooting;
	}

	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}

	public Entity(double posX, double posY, double width, double height, int hP) {
	
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.hP = hP;
	}
	
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
	
	public void shoot(){
		
	}
	
	
}
