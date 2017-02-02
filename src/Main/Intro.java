package Main;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Intro extends BasicGameState{

	public static final int ID = 2;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setBackground(Color.black);
		
		g.setColor(Color.white);
		g.drawString("SPACE AVENGER!!", 300, 200);
		
		g.drawString("Press 1 to Play!", 300, 300);
		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
Input input = gc.getInput();
		
		if(input.isKeyPressed(input.KEY_1)){
			gc.reinit();
			sbg.enterState(Game.ID, new FadeOutTransition(Color.red), new FadeInTransition(Color.black));
			
			
		}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}
	

}
