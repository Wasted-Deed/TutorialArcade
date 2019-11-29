package Main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

	public static void main(String[] args) throws SlickException {
		
		AppGameContainer game = new AppGameContainer(new App("game"));
		game.setDisplayMode(800, 600, false);
		game.setAlwaysRender(true);
		game.start();
	}
	
}
