package Main;

import java.util.Random;

public class Enemy extends Entity {

	Random r = new Random();
	private boolean toRight;
	private int enemyPos;
	private boolean enemyAttacking;
	private int enemySetMov = 0;
	private long countShoot;
	private int hasItem = 0;
	private boolean enemyMoveOn;
	private boolean enemyFirstMove;
	
	
	
	
	public boolean isEnemyFirstMove() {
		return enemyFirstMove;
	}

	public void setEnemyFirstMove(boolean enemyFirstMove) {
		this.enemyFirstMove = enemyFirstMove;
	}

	public boolean isEnemyMoveOn() {
		return enemyMoveOn;
	}

	public void setEnemyMoveOn(boolean enemyMoveOn) {
		this.enemyMoveOn = enemyMoveOn;
	}

	public int getHasItem() {
		return hasItem;
	}

	public void setHasItem(int hasItem) {
		this.hasItem = hasItem;
	}

	public long getCountShoot() {
		return countShoot;
	}

	public void setCountShoot(long countShoot) {
		this.countShoot = countShoot;
	}

	public int getEnemySetMov() {
		return enemySetMov;
	}

	public void setEnemySetMov(int enemySetMov) {
		this.enemySetMov = enemySetMov;
	}

	public boolean isEnemyAttacking() {
		return enemyAttacking;
	}

	public void setEnemyAttacking(boolean enemyAttacking) {
		this.enemyAttacking = enemyAttacking;
	}

	public int getEnemyPos() {
		return enemyPos;
	}

	public void setEnemyPos(int enemyPos) {
		this.enemyPos = enemyPos;
	}

	public boolean isToRight() {
		return toRight;
	}

	public void setToRight(boolean toRight) {
		this.toRight = toRight;
	}

	public Enemy(double posX, double posY, double width, double height, int hP, int enemySetMov) {
		
		super(posX, posY, width, height, hP);
		this.enemySetMov = enemySetMov;
		this.enemyAttacking = false;
		this.hasItem = r.nextInt(100)+1;
		this.enemyMoveOn = true;
		this.enemyFirstMove = false;
		
		if(this.enemySetMov >2){
			this.toRight = true;
		}else{
			this.toRight = false;
		}
		
	}
	
	public void move(){
		if(r.nextInt(5)>=2){
			this.setToRight(true);
		}else{
			this.setToRight(false);
		}
		
		if(this.isToRight()){
			this.setPosX(this.getPosX() + r.nextInt(6));
		}else{
			this.setPosX(this.getPosX() - r.nextInt(6));
		}
						
	}
	
	
	
}
