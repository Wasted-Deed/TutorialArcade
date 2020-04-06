package Utils;

import Unit.Unit;
import ocean.Building;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import java.util.LinkedList;

public class Physics {
    public Physics() {
    }

    public static void ForceGravityOnAllUnits(Unit unit)
    {
            if (!unit.isOnEarth()) {
                if (unit.getListForce().containsKey("Gravity")) {
                    unit.addSpeed(((Vector2f)unit.getListForce().get("Gravity")).getX() / 100.0F, ((Vector2f)unit.getListForce().get("Gravity")).getY() / 100.0F);
                }
            } else {
                unit.setJump(false);
            }
    }
    public static void  CollisionWithBlocks(Unit unit, LinkedList<Building> buildings)
    {
        boolean flag1=false;
        for (Building building: buildings)
        {
            //ѕроверка на столкновение с верхом
            if( (unit.checkCollision(new Rectangle(building.getPos().getX()+1,building.getPos().getY(),building.getPos().getWidth()-10,1))))
            {
                flag1 = true;
                if ((unit.getSpeed().getY()>0))
                unit.setLocation(new Rectangle(unit.getLocation().getX(),building.getPos().getY()-unit.getLocation().getHeight(),unit.getLocation().getWidth(),unit.getLocation().getHeight()));

            }else
                //проверка на столкновение с низом
                if ((unit.checkCollision(new Rectangle(building.getPos().getX()+1,building.getPos().getMaxY(),building.getPos().getWidth()-10,1)))&&(unit.getSpeed().getY()<0))
                {
                    unit.SetSpeed(unit.getSpeed().x, 0.0F);
                    unit.setLocation(new Rectangle(unit.getLocation().getX(),building.getPos().getMaxY(),unit.getLocation().getWidth(),unit.getLocation().getHeight()));

                }
            //—толкновение слева
            if (unit.checkCollision(new Rectangle(building.getPos().getX(),building.getPos().getY()+2,1,building.getPos().getHeight()-10)))
            {
                unit.setLocation(new Rectangle(building.getPos().getX()-unit.getLocation().getWidth(),unit.getLocation().getY(),unit.getLocation().getWidth(),unit.getLocation().getHeight()));
            }else
                //—толкновение справа
                if (unit.checkCollision(new Rectangle(building.getPos().getMaxX()-1,building.getPos().getY()+10,1,building.getPos().getHeight()-15)))
                {
                    unit.checkCollision(new Rectangle(building.getPos().getMaxX(),building.getPos().getY()+2,1,building.getPos().getHeight()-15));
                    unit.setLocation(new Rectangle(building.getPos().getMaxX(),unit.getLocation().getY(),unit.getLocation().getWidth(),unit.getLocation().getHeight()));
                }

        }
        if (flag1)
        {

            unit.setOnEarth(true);
            unit.SetSpeed(unit.getSpeed().x, 0.0F);
        } else {
            unit.setOnEarth(false);
        }

    }
}