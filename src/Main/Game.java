package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Game extends BasicGameState{
	
	public static final int ID = 0; 
	
	Ship ship = new Ship(360,470,20,70,10);
	private long counter = 0;
	private long starCount = 0;
	
	private long countToMeteor = 0;
	List<Bullet> bullets = new ArrayList<>();
	List<Bullet> eBullets = new ArrayList<>();
	List<Bullet> toRemove = new ArrayList<>();
	List<Enemy> enemies = new ArrayList<>();
	List<Enemy> enemieToRemove = new ArrayList<>();
	List<Meteor> meteors = new ArrayList<>();
	List<Meteor> meteorsToRemove = new ArrayList<>();
	List<Star> stars = new ArrayList<>();
	List<Star> starsToRemove = new ArrayList<>();
	
	List<Enemy> boss = new ArrayList<>();
	List<Enemy> bossToRemove = new ArrayList<>();
	
	List<Item> itens = new ArrayList<>();
	List<Item> itensToRemove = new ArrayList<>();
	Image hero;  
	Image enemyAvatar;
	
	//public int bX;
	//public int bY;
	//public int bArea;
	//public int bulletCount;
	public float enemyVelYY;
	private boolean hit = false;
	private int points = 0;
	private String strPoints = "";
	private String strLevel = "";
	private int level = 0;
	private float enemyVelY = 0;
	private float enemyVelX = 0;
	
	
	
	Random r = new Random();
	
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		ship.sethP(10);
		bullets.clear();
		enemies.clear();
		eBullets.clear();
		toRemove.clear();
		boss.clear();
		points = 0;
		level = 4;
		enemyVelY = 0.5f;
		enemyVelX = 1.0f;
		ship.setAlive(true);
		hit = false;
		ship.setPosX(360);
		meteors.clear();
		meteorsToRemove.clear();
		enemieToRemove.clear();
		hero = new Image("src/test.png");
		enemyAvatar = new Image("src/enemyspace.png");
	}
	

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		g.setBackground(Color.darkGray);
		g.setColor(Color.white);
		g.setColor(Color.darkGray );
		g.drawRect((int)ship.getPosX(), (int)ship.getPosY(), (int)ship.getWidth(), (int)ship.getHeight());
		hero.draw((int)ship.getPosX() - 22, (int)ship.getPosY() - 10, 64, 100);
		
		
		for(Star s : stars){
			g.setColor(Color.white);
			g.drawRect((float)s.getPosX(), (float)s.getPosY(), (float)s.getWidth(), (float)s.getHeight());
		}
		
		
		g.setColor(Color.white);
		g.drawString("Points:", 280, 565);
		g.drawString(strPoints, 350, 565);
		
		g.drawString("Level:", 500, 565);
		g.drawString(strLevel, 570, 565);
		
				
		if(ship.isShooting()==true){
			g.setColor(Color.red);
			for(Bullet i : bullets){
				if(i.getPosY() >= 0){
				g.drawRect((float)i.getPosX(),(float) i.getPosY(), (float)i.getWidth(),(float) i.getHeight());
				if(i.getPosY() <= 0){
					toRemove.add(i);
				}
				}
			}
			
		}
		
		
		for(Meteor m : meteors){
			g.setColor(Color.orange);
			g.drawRect((float)m.getPosX(), (float)m.getPosY(), 64, 64);
		}
		
		
		for(Bullet j : eBullets){
			g.setColor(Color.pink);
			g.drawRect((float)j.getPosX(),(float) j.getPosY(),(float) j.getWidth(),(float) j.getHeight());
		}
		
		for(Bullet i : toRemove){
			bullets.remove(i);
		}
		toRemove.clear();
		
		for(Enemy e : enemies){
			g.setColor(Color.cyan);
			//g.fillRect((float)e.getPosX(),(float) e.getPosY(),(float) e.getWidth(),(float) e.getHeight());
			enemyAvatar.draw((float)e.getPosX(),(float) e.getPosY(),(float) e.getWidth(),(float) e.getHeight());
		}
		
		for(Enemy b : boss){
			g.setColor(Color.red);
			g.fillRect((float)b.getPosX(),(float) b.getPosY(),(float) b.getWidth(),(float) b.getHeight());
			
	}
		
		
		if(hit == true){
			g.setColor(Color.red);
			g.fillRect((float)ship.getPosX(), (float) ship.getPosY(), (float)ship.getWidth(), (float) ship.getHeight());
			
		}
		
		
		for(Star s : stars){
			g.setColor(Color.white);
			
			g.drawRect((float)s.getPosX(), (float)s.getPosY(), (float)s.getWidth(), (float)s.getHeight());
		}
		
		
		for(Item i : itens){
			g.setColor(Color.red);
			g.fillRect((float)i.getPosX(), (float)i.getPosY(), (float)i.getWidth(), (float)i.getHeight());
		}
		
		
		g.setColor(Color.black);
		g.fillRect(0, 550, 800, 50);
		g.setColor(Color.white);
		g.drawString("Energy:", 10, 565);
		g.drawRect(80, 570, 100, 10);
		if(ship.gethP()>0){
			g.setColor(Color.green);
			g.fillRect(80, 570, 10*ship.gethP(), 10);
			
		}
		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		starCount += delta;
		counter += delta;
		strPoints = String.valueOf(points);
		strLevel = String.valueOf(level);
		countToMeteor +=delta;
		
		
		if(starCount >= 300){
			stars.add(new Star(r.nextInt(700)+32, -50, 1, 1, 2));
			starCount = 0;
		}
		
		for(Star s : stars)	{
			s.setPosY(s.getPosY() + 5);
			if(s.getPosY() >= 590){
				starsToRemove.add(s);
			}
		}
		
		for(Star sR : starsToRemove){
			stars.remove(sR);
		}
		
		starsToRemove.clear();
		
		if(level == 1){
			if(counter>=3000){
			enemies.add(new Enemy(r.nextInt(700)+32, -50, 32, 32, 2, r.nextInt(4)+1));
			counter = 0;
			}
			
			if(countToMeteor >= 15000){
				meteors.add(new Meteor(r.nextInt(720) + 1, -50, 64, 64, 1));
				countToMeteor = 0;
			}
		}
		
		
		if(level == 2){
			if(counter>=2300){
				enemies.add(new Enemy(r.nextInt(700)+32, -50, 32, 32, 2, r.nextInt(4)+1));
				counter = 0;
				enemyVelY = 1.0f;
				enemyVelX = 1.75f;
				enemyVelYY = 12.0f;
			}
			
			if(countToMeteor >= 13000){
				meteors.add(new Meteor(r.nextInt(720) + 1, -50, 64, 64, 1));
				countToMeteor = 0;
			}
			
		}
			
		if (level == 5){
			
			if(boss.size() <= 1){
				if (enemies.size() <= 0){
					boss.add(new Enemy(300,-50, 100, 50,40,r.nextInt(4)-1));
				}
			}
		}
		
		
		if(level == 3){
			if(counter>=2300){
				enemies.add(new Enemy(r.nextInt(700)+32, -50, 32, 32, 2, r.nextInt(4)+1));
				counter = 0;
				
				if(counter >= 900){
					enemies.add(new Enemy(r.nextInt(700)+32, -50, 32, 32, 2, r.nextInt(4)+1));
				}
				enemyVelY = 1.5f;
				enemyVelX = 2.5f;
				enemyVelYY = 12.5f;
			}
			
			if(countToMeteor >= 9000 && countToMeteor <= 9030 ){
				meteors.add(new Meteor(r.nextInt(720) + 1, -50, 64, 64, 1));
				
			}
			
			if(countToMeteor >= 12000){
				meteors.add(new Meteor(r.nextInt(720) + 1, -50, 64, 64, 1));
				countToMeteor = 0;
			}
		}
		
		
		if(level == 4){
			if(counter>=1900){
				enemies.add(new Enemy(r.nextInt(700)+32, -50, 32, 32, 2, r.nextInt(4)+1));
				enemies.add(new Enemy(r.nextInt(700)+32, -50, 32, 32, 2, r.nextInt(4)+1));
				counter = 0;
				
				if(counter >= 900){
					enemies.add(new Enemy(r.nextInt(700)+32, -50, 32, 32, 2, r.nextInt(4)+1));
					enemies.add(new Enemy(r.nextInt(700)+32, -50, 32, 32, 2, r.nextInt(4)+1));
				}
				enemyVelY = 2.0f;
				enemyVelX = 3.5f;
				enemyVelYY = 13.0f;
			}
			
			if(countToMeteor >= 9000 && countToMeteor <= 9030 ){
				meteors.add(new Meteor(r.nextInt(720) + 1, -50, 64, 64, 1));
				
			}
			
			if(countToMeteor >= 12000){
				meteors.add(new Meteor(r.nextInt(720) + 1, -50, 64, 64, 1));
				countToMeteor = 0;
			}
			
		}
		
		
		
		
		
		Input input = gc.getInput();
		
		if(input.isKeyDown(input.KEY_D)){
			ship.setPosX(ship.getPosX() + 5);
			if(hit == true){
				hit = false;
			}
		}
		
		if(input.isKeyDown(input.KEY_A)){
			ship.setPosX(ship.getPosX() - 5);
			if(hit == true){
				hit = false;
			}
		}
		
		
		if(input.isKeyPressed(input.KEY_F)){
			ship.setShooting(true);
			
			if(bullets.size()<=2){
				bullets.add(new Bullet(ship.getPosX()+28, ship.getPosY(),10,10,12));
				
			}
		
		}		
		
		
		// sobe o level e dificuldade conforme ganha pontos.
		if(points >= 10 * level){
			level++;
		}
		
		
		//ajusta meteoros:
		
		for(Meteor m : meteors){
			
			m.setPosY(m.getPosY() + 5);
			
			if(m.getPosY() >= 570){
				meteorsToRemove.add(m);
			}
			
			if(((m.getPosY() + m.getHeight() >= ship.getPosY())||(m.getPosY() >= ship.getPosY() + ship.getHeight())) && 
					((m.getPosX()+m.getWidth() >= ship.getPosX() &&
					m.getPosX()<= ship.getPosX()+ship.getWidth()))){
				
				meteorsToRemove.add(m);
				ship.sethP(ship.gethP() - 8);
				hit = true;
				if(ship.gethP() <= 0){
					ship.setAlive(false);
					
				}
			}
			
			
		}
		
		for(Meteor rM : meteorsToRemove){
			meteors.remove(rM);
		}
		
		meteorsToRemove.clear();
		
		//Verifica se o tiro atingiu um inimigo ou passou da linha superior. 
		
		for(Bullet i : bullets){
			i.setPosY(i.getPosY() - i.getBulletVel());
			if(i.getPosY() <= 0){
				toRemove.add(i);
			}
			
			for(Enemy e : enemies){
				if(i.getPosY()<=(e.getPosY()+e.getHeight()) && (i.getPosX() + i.getWidth() >= e.getPosX() && i.getPosX() <= e.getPosX()+e.getWidth())){	
					e.sethP((e.gethP() - 1));
					toRemove.add(i);
					if(e.gethP()<= 0){
						
						if(e.getHasItem() >90){
							itens.add(new Item(r.nextInt(700)+32, -50, 20, 20, 2));
						}
						
						e.setAlive(false);
						points++;
						
					}
					if(e.isAlive() == false){
						enemieToRemove.add(e);
					}
				}
			}
						
		}
		
		
		for(Item i : itens){
			
			i.setPosY(i.getPosY() + 5.0f);
			if(((i.getPosY() + i.getHeight() >= ship.getPosY())||(i.getPosY() >= ship.getPosY() + ship.getHeight())) && 
					((i.getPosX()+ i.getWidth() >= ship.getPosX() &&
					i.getPosX()<= ship.getPosX()+ship.getWidth()))){
				
				itensToRemove.add(i);
				if(ship.gethP() <5){
					ship.sethP(ship.gethP() + 5);
				}else{
					ship.sethP(ship.getMaxHp());
				}
			}
			
		}
		
		
		for(Item iR : itensToRemove){
			itens.remove(iR);
		}
		
		itensToRemove.clear();
		
		// verifica o que ocorre com os tiros dos inimigos
		
		for(Bullet i : eBullets){
			
			if(i.getPosY() >= 550){
				toRemove.add(i);
			}
					
			
			if(i.getPosY() >= ship.getPosY() &&
					((i.getPosX()+5 >= ship.getPosX() &&
					(i.getPosX()+ i.getWidth()<= ship.getPosX()+ship.getWidth()))||
					(i.getPosX()+5 >= ship.getPosX() &&		
					i.getPosX()<= ship.getPosX()+ship.getWidth()))){
				toRemove.add(i);
				ship.sethP(ship.gethP() - 1);
				hit = true;
				if(ship.gethP() <= 0){
					ship.setAlive(false);
					
				}
				
			}
		}
		
		
		
		if(ship.isAlive() == false){
			sbg.enterState(GameOver.ID, new FadeOutTransition(Color.blue), new FadeInTransition(Color.white));
		}
		
		// remove os inimigos que entrarão na lista para remover
				
		for(Bullet i : toRemove){
			bullets.remove(i);
			eBullets.remove(i);
		}
		
		for(Enemy e : enemieToRemove){
			enemies.remove(e);
			
		}
		
		toRemove.clear();
		enemieToRemove.clear();
		
		//movimentação dos Inimigos:		
		for(Enemy b : boss){
			if(b.isEnemyAttacking() == false){
				if(b.getPosY()<71){
				b.setPosY(b.getPosY() + 3);
				}
				
				if(b.isEnemyFirstMove() == false){
					if(b.getPosY() >= 70.1f ){
						b.setPosY(73.0f);
						b.setEnemyFirstMove(true);
					}
				}else{
					
					b.setCountShoot(b.getCountShoot() + delta);
				
					
											
				if(b.isToRight() == true){
					
					b.setPosX(b.getPosX() + 3);
					
					if(b.isEnemyMoveOn() == true){
						
						b.setPosY(b.getPosY() + 0.5f);
						
							if(b.getPosY() > 100.0f){
								b.setEnemyMoveOn(false);
							}
						
					}
					
					
					
					if (b.isEnemyMoveOn() == false){ 
						
						b.setPosY(b.getPosY() - 0.5f);
						if(b.getPosY() < 73.0f){
							b.setEnemyMoveOn(true);
						}
						
													
					}
					
					if(b.getPosX() > 770){
						b.setToRight(false);
					}
					
									
				}else{
					
					b.setPosX(b.getPosX() - 3);
					
					if (b.isEnemyMoveOn() == false){ 
						b.setPosY(b.getPosY() - 0.5f);
						if(b.getPosY() < 73.0f){
							b.setEnemyMoveOn(true);
						}
					}
					
					
					
					if(b.isEnemyMoveOn() == true){
						b.setPosY(b.getPosY() + 0.5f);
						if(b.getPosY() > 100.0f){
							b.setEnemyMoveOn(false);
						}
					}
					
					
					
					
					if(b.getPosX() < 2 ){
						b.setToRight(true);
					}
				}
				
				if((b.getCountShoot() >= 2000)&&(b.getCountShoot()<=2008) || 
					(b.getCountShoot() >= 4000)&&(b.getCountShoot()<=4008) ||
					(b.getCountShoot() >= 6000)&&(b.getCountShoot()<=6008)){
				
														
				eBullets.add(new Bullet(b.getPosX()+30,b.getPosY()+b.getHeight(),10,10,-5));
				
			
				}
			
				
				if(b.getCountShoot() >= 8000){
					
					b.setEnemyAttacking(true);
											
					
				}
				
			}
			
						
			
	}else{
		
		if(b.isToRight() == true){
			
			b.setPosX(b.getPosX() + 3);
			b.setPosY(b.getPosY() + 3 * 8);
			if(b.getPosX() >= 800 - b.getWidth()){
				b.setToRight(false);
			}
			
							
		}else{
			b.setPosX(b.getPosX() - 3);
			b.setPosY(b.getPosY() + 3 * 8);
			if(b.getPosX() <= 2 ){
				b.setToRight(true);
			}
		}

	}
		
				
}

for(Enemy b : boss){
		
			
			if(b.getPosY()>=530){
				bossToRemove.add(b);
			}
			
			if(((b.getPosY() + b.getHeight() >= ship.getPosY())||(b.getPosY() >= ship.getPosY())) && 
					((b.getPosX()+b.getWidth() >= ship.getPosX() &&
					b.getPosX()+ b.getWidth()<= ship.getPosX()+ship.getWidth())||
					((b.getPosX() >= ship.getPosX() &&
					b.getPosX()<= ship.getPosX()+ship.getWidth())		
							))){
				
				bossToRemove.add(b);
				ship.sethP(ship.gethP() - 12);
				hit = true;
				if(ship.gethP() <= 0){
					ship.setAlive(false);
					
				}
			}
			
		}
		
			
		
		
		for(Enemy e : enemies){
										
					
					if(e.isEnemyAttacking() == false){
						if(e.getPosY()<71){
						e.setPosY(e.getPosY() + enemyVelY);
						}
						
						if(e.isEnemyFirstMove() == false){
							if(e.getPosY() >= 70.1f ){
								e.setPosY(73.0f);
								e.setEnemyFirstMove(true);
							}
						}else{
							
							e.setCountShoot(e.getCountShoot() + delta);
						
							
													
						if(e.isToRight() == true){
							
							e.setPosX(e.getPosX() + enemyVelX);
							
							if(e.isEnemyMoveOn() == true){
								
								e.setPosY(e.getPosY() + 0.5f);
								
									if(e.getPosY() > 100.0f){
										e.setEnemyMoveOn(false);
									}
								
							}
							
							
							
							if (e.isEnemyMoveOn() == false){ 
								
								e.setPosY(e.getPosY() - 0.5f);
								if(e.getPosY() < 73.0f){
									e.setEnemyMoveOn(true);
								}
								
															
							}
							
							
							
							
							if(e.getPosX() > 770){
								e.setToRight(false);
							}
							
											
						}else{
							
							e.setPosX(e.getPosX() - enemyVelX);
							
							if (e.isEnemyMoveOn() == false){ 
								e.setPosY(e.getPosY() - 0.5f);
								if(e.getPosY() < 73.0f){
									e.setEnemyMoveOn(true);
								}
							}
							
							
							
							if(e.isEnemyMoveOn() == true){
								e.setPosY(e.getPosY() + 0.5f);
								if(e.getPosY() > 100.0f){
									e.setEnemyMoveOn(false);
								}
							}
							
							
							
							
							if(e.getPosX() < 2 ){
								e.setToRight(true);
							}
						}
						
						if((e.getCountShoot() >= 2000)&&(e.getCountShoot()<=2008) || 
							(e.getCountShoot() >= 4000)&&(e.getCountShoot()<=4008) ||
							(e.getCountShoot() >= 6000)&&(e.getCountShoot()<=6008)){
						
																
						eBullets.add(new Bullet(e.getPosX()+10,e.getPosY()+e.getHeight(),5,5,-4));
						
					
						}
					
						
						if(e.getCountShoot() >= 8000){
							
							e.setEnemyAttacking(true);
													
							
						}
						
					}
					
								
					
			}else{
				
				if(e.isToRight() == true){
					
					e.setPosX(e.getPosX() + enemyVelX);
					e.setPosY(e.getPosY() + enemyVelY * 8);
					if(e.getPosX() >= 800 - e.getWidth()){
						e.setToRight(false);
					}
					
									
				}else{
					e.setPosX(e.getPosX() - enemyVelX);
					e.setPosY(e.getPosY() + enemyVelY * 8);
					if(e.getPosX() <= 2 ){
						e.setToRight(true);
					}
				}

			}
				
						
	}
		
		for(Enemy e : enemies){
		
			
			if(e.getPosY()>=530){
				enemieToRemove.add(e);
			}
			
			if(((e.getPosY() + e.getHeight() >= ship.getPosY())||(e.getPosY() >= ship.getPosY())) && 
					((e.getPosX()+e.getWidth() >= ship.getPosX() &&
					e.getPosX()+ e.getWidth()<= ship.getPosX()+ship.getWidth())||
					((e.getPosX() >= ship.getPosX() &&
					e.getPosX()<= ship.getPosX()+ship.getWidth())		
							))){
				
				enemieToRemove.add(e);
				ship.sethP(ship.gethP() - 4);
				hit = true;
				if(ship.gethP() <= 0){
					ship.setAlive(false);
					
				}
			}
			
		}
		
		
		
		
		for(Bullet j : eBullets){
			j.setPosY((j.getPosY() - j.getBulletVel()));
			if(j.getPosY() <= 0){
				toRemove.add(j);
			}
		}
		
		if(ship.getPosX()<=0){
			ship.setPosX(0);
		}
		if(ship.getPosX()+ship.getWidth()>=800){
			ship.setPosX(800 - ship.getWidth());
		}
		
		}
	
		
		
	@Override
	public int getID() {

		return 0;
	}

}
