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



public class GameOver extends BasicGameState{
	
	public static final int ID = 1;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setBackground(Color.blue);
		g.setColor(Color.white);
		g.drawString("GAME OVER!", 300, 280);
		
		g.drawString("Press 1 to restart", 300, 320);
		g.drawString("Press 2 to Surrender", 300, 350);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if(input.isKeyPressed(input.KEY_1)){
			gc.reinit();
			sbg.enterState(Game.ID, new FadeOutTransition(Color.blue), new FadeInTransition(Color.white));
			
		}
		
		if(input.isKeyPressed(input.KEY_2)){
			gc.reinit();
			sbg.enterState(Intro.ID, new FadeOutTransition(Color.blue), new FadeInTransition(Color.white));
			
		}
		
		
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

}
