package Unit;

import ocean.Building;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;
import java.util.LinkedList;


public class Ai extends Unit implements BehaveAi {

    public void attack()
    {
    }

    public void behave(ArrayList<? extends Unit> unit)
    {

        if (this.getLocation().getMinX() <= 0.0F) {
            this.getImageR().setRotation(180.0F);
            this.SetSpeed(-this.getSpeed().getX(), 0.0F);
        }

        if (this.getLocation().getMaxX() >= 700.0F) {
            this.getImageR().setRotation(0.0F);
            this.SetSpeed(-this.getSpeed().getX(), 0.0F);
        }

        this.move();
    }


    public Ai(int health, float mWalkSpeed, Rectangle location, int damage)
    {
        super(health, mWalkSpeed, location, damage);

        this.SetSpeed(-this.getmWalkSpeed() / 10.0F, 0.0F);
    }

    @Override
    public void behave()
    {

    }



}