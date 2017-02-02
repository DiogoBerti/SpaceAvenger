package Main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;



public class Main extends StateBasedGame {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int game = 0;
	public static final int over = 1;
	public static final int intro = 2;
	
	public Main(String name) {
		super(name);
		this.addState(new Game());
		this.addState(new GameOver());
		this.addState(new Intro());
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(intro).init(gc,this);
		this.enterState(intro);
		
	}
	
	public static void main(String[] args) {
			
		AppGameContainer appgc;
		
		try {
			
			appgc = new AppGameContainer(new Main("Avenger"));
			appgc.setDisplayMode(WIDTH, HEIGHT, false);
			appgc.setTargetFrameRate(60);
			appgc.start();
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
