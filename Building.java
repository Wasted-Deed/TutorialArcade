package ocean;

import org.newdawn.slick.Image;

public class Building implements Behaviour {
	private double posX, posY;
	private Image image;
	private TypeBuilding typeBuilding;  // тип сооружения
	
	public Building(double x, double y, Image img, TypeBuilding type) {
		posX = x;
		posY = y;
		image = img;
		typeBuilding = type;
	}

	public Image getImage() {
		return image;
	}
	
	public double getX() {
		return posX;
	}
	
	public double getY() {
		return posY;
	}
	
	public TypeBuilding getTypeBuilding() {
		return typeBuilding;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public void setX(double x) {
		this.posX = x;
	}
	
	public void setY(double y) {
		this.posY = y;
	}
	
	public void setTypeBuilding(TypeBuilding typeBuilding) {
		this.typeBuilding = typeBuilding;
	}
	
	// обработка столкновений
	@Override
	public void dash() {
		
	}

	
}
