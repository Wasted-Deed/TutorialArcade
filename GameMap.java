package ocean;

import java.util.*;

import org.newdawn.slick.Graphics;

public class GameMap implements Drawable {
	
	ArrayList<Building> objects;
	
	public GameMap(ArrayList<Building> obj) {
		objects = obj;
	}
	
	
	@Override
	public void draw(Graphics g) {
		for (Building object : objects) {
			object.getImage().draw((float)object.getX(),(float)object.getY());
		}
	}



	
}
