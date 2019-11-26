import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;

public class Ai extends Unit
{

    @Override
    public void attack()
    {

    }
    public void update(GameContainer gc, int delta)
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
