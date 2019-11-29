package Main;

import java.util.ArrayList;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import ocean.*;

public class App extends BasicGame {

	private GameMap map;
	
	public App(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		ArrayList<Building> listBuildings = new ArrayList<>();
		listBuildings.add(new Building(200, 200, new Image("1.jpg"), TypeBuilding.BLOCK));
		listBuildings.add(new Building(400, 200, new Image("2.jpg"), TypeBuilding.GROUND));
		map = new GameMap(listBuildings);
	}
	
	@Override
	public void update(GameContainer container, int time) throws SlickException {
		
	}
	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		map.draw(g);
	}
}