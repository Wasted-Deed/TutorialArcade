package ocean;

public interface Behaviour {
	void move(float step); // перемещение карты
	void update(int screenWidth, int screenheight);
	//void dash();  // столкновение объекта с пулей или героем
}
