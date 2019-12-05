import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

public class Ai extends Unit implements BehaveAi
{


    @Override
    public void attack()
    {

    }
    @Override
    public void behave(ArrayList<? extends Unit> unit)
    {
        System.out.println(getLocation().getMinX());
        if (getLocation().getMinX()<=0 )
        {
            getImageR().setRotation(180);
            SetSpeed(-getSpeed().getX()  , 0);

        }
        if((getLocation().getMaxX()>=700))
        {
            getImageR().setRotation(0);
            SetSpeed(-getSpeed().getX() , 0);

        }
        move();
    }

    public Ai(int health, float mWalkSpeed, Rectangle location, int damage)
    {

        super(health, mWalkSpeed, location, damage);
        SetSpeed(-getmWalkSpeed()/10 , 0);
    }


}
