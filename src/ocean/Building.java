package ocean;

import Unit.Behave;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class Building
{
	private Rectangle Pos=new Rectangle(0,0,0,0);
	private Image image;
	private int health;
	int TimeEndMove,DelayChangeDirection;

	public void setImage(final Image image) {
		this.image = image;
	}

	public int getHealth() {
		return this.health;
	}

	public void setHealth(final int health) {
		this.health = health;
	}

	public int getDelayChangeDirection() {
		return this.DelayChangeDirection;
	}

	public void setDelayChangeDirection(final int delayChangeDirection) {
		this.DelayChangeDirection = delayChangeDirection;
	}

	public void setTypeBuilding(final TypeBuilding typeBuilding) {
		this.typeBuilding = typeBuilding;
	}

	private TypeBuilding typeBuilding;  // тип сооружения


	public Rectangle getPos() {
		return Pos;
	}

	public void setPos(Rectangle pos) {
		Pos = pos;
	}

	public Building(Image img, TypeBuilding type) {
		image = img;
		typeBuilding = type;

		Pos.setX(-1);
		Pos.setY(-1);
	}

	public Building(Building another) {
		this.Pos=another.getPos();
		this.image = another.image;
		this.typeBuilding = another.typeBuilding;
	}

	public Image getImage() {
		return image;
	}
	public TypeBuilding getTypeBuilding() {
		return typeBuilding;
	}


}
