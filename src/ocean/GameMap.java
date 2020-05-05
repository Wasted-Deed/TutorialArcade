package ocean;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class GameMap implements Drawable, Behaviour {

	private final int PLACE_BLOCKS_Y = 200;
	private ArrayList<Building> allTypes;
	private LinkedList<Building> listBuildings;
	private SpriteSheet spriteSheet;
	private int gameWidth;// screenHeight, maxHorizontalAmount, maxVerticalAmount;
	private Random random;

	
	public GameMap(int gameWidth) throws SlickException {
		listBuildings = new LinkedList<>();
		allTypes = new ArrayList<>();
		spriteSheet = new SpriteSheet("resources/images/1.jpg", 16, 16);
		this.gameWidth = gameWidth;
		random = new Random();
		addAlltypes();
		for (int x = 0; x <= gameWidth; x += 32)
			addBuilding(x, 1);
	}

	public void addAlltypes() {
		allTypes.add(new Building(spriteSheet.getSprite(0,0), TypeBuilding.WALL));
		allTypes.add(new Building(spriteSheet.getSprite(3,1),  TypeBuilding.WALL));
		for (int i = 0; i < 4; ++i)
			allTypes.add(new Building(spriteSheet.getSprite(i,2),  TypeBuilding.WALL));
		allTypes.add(new Building(spriteSheet.getSprite(0,3),  TypeBuilding.WALL));
		allTypes.add(new Building(spriteSheet.getSprite(1,3),  TypeBuilding.WALL));
		allTypes.add(new Building(spriteSheet.getSprite(7,3),  TypeBuilding.WALL));
		// добавлены не все!
	}

	public LinkedList<Building> getListBuildings() {
		return listBuildings;
	}


	public void addBuilding(float x, float y)
	{
		Building building = new Building(allTypes.get(random.nextInt(allTypes.size())));
		building.setPos(new Rectangle(x,y,32,32));
		listBuildings.addFirst(building);

	}
	//side = -1 - добавление слева, 1 - справа
	public void addBuilding(float x, int side) {
		Building building = new Building(allTypes.get(random.nextInt(allTypes.size())));
		building.setPos(new Rectangle(x,PLACE_BLOCKS_Y + 64,32,32));
		if (side == -1)
			listBuildings.addFirst(building);
		else if (side == 1)
			listBuildings.add(building);
	}


	@Override
	public void update(int screenWidth, int screenHeight) {
		//this.screenWidth = screenWidth;
		//this.screenHeight = screenHeight;
	}

	//-1 - влево, 1 - вправо
	@Override
	public void move(float step) {
		// добавление первого или последнего элемента, если за ним никого нет
		if (listBuildings.getFirst().getPos().getX() + step > 0)
			addBuilding(listBuildings.getFirst().getPos().getX() - 32, -1);
		else if (listBuildings.getLast().getPos().getX() + 32 + step < 800)
			addBuilding(listBuildings.getLast().getPos().getX() + 32, 1);
		for (Building building: listBuildings) {
			building.getPos().setX(building.getPos().getX() + step);
		}
	}

	@Override
	public void draw(Graphics g) {
		spriteSheet.startUse();
		//g.drawString("hello", 200, 200);
		for (Building building: listBuildings)
			building.getImage().drawEmbedded(building.getPos().getX(), building.getPos().getY(), building.getPos().getWidth(),  building.getPos().getHeight());

		spriteSheet.endUse();
	}
}
