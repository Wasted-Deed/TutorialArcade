package ocean;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class Building {
//	private float posX, posY,width,height;
	private Rectangle Pos=new Rectangle(0,0,0,0);
	private Image image;
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
