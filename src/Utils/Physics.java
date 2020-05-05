package Utils;

import Shell.Shell;
import Unit.Unit;
import ocean.Building;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;
import java.util.Iterator;
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
    public static void   CollisionBulletWithUnit(Unit unit, ArrayList<Shell> ListShels)
    {
        Iterator var2 = ListShels.iterator();
        while (var2.hasNext())
        {
            Shell currentShell=(Shell)var2.next();
            if((currentShell.getLocation().intersects(unit.getLocation()))&&(currentShell.getNumberCommand()!=unit.getNumberCommand()))
            {
                ListShels.remove(currentShell);
                System.out.println("Пуля уничтожена");
                unit.setHealth(unit.getHealth()-currentShell.getDamage());
                break;
            }
        }

    }
    public static void  CollisionWithBlocks(Unit unit, LinkedList<Building> buildings)
    {
        boolean flag1=false;
        unit.setCollision(false);
        for (Building building: buildings)
        {
            //Проверка на столкновение с верхом
            if( (unit.checkCollision(new Rectangle(building.getPos().getX()+2,building.getPos().getY(),building.getPos().getWidth()-4,1))))
            {
                flag1 = true;
                if ((unit.getSpeed().getY()>0))
                unit.setLocation(new Rectangle(unit.getLocation().getX(),building.getPos().getY()-unit.getLocation().getHeight(),unit.getLocation().getWidth(),unit.getLocation().getHeight()));

            }else
                //проверка на столкновение с низом
                if ((unit.checkCollision(new Rectangle(building.getPos().getX()+2,building.getPos().getMaxY(),building.getPos().getWidth()-4,1)))&&(unit.getSpeed().getY()<0))
                {
                    unit.SetSpeed(unit.getSpeed().x, 0.0F);
                    unit.setLocation(new Rectangle(unit.getLocation().getX(),building.getPos().getMaxY(),unit.getLocation().getWidth(),unit.getLocation().getHeight()));

                }
            //Столкновение слева
            if (unit.checkCollision(new Rectangle(building.getPos().getX()+1,building.getPos().getY()+2,1,building.getPos().getHeight()-4)))
            {
                System.out.println("Столкновение!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                unit.setCollision(true);
                unit.setLocation(new Rectangle(building.getPos().getX()-unit.getLocation().getWidth(),unit.getLocation().getY(),unit.getLocation().getWidth(),unit.getLocation().getHeight()));
            }else
                //Столкновение справа
                if (unit.checkCollision(new Rectangle(building.getPos().getMaxX()-1,building.getPos().getY()+2,1,building.getPos().getHeight()-4)))
                {
                    //System.out.println("Столкновение--------------------------------------------------!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    System.out.println("X="+unit.getLocation().getX());
                    unit.setLocation(new Rectangle(building.getPos().getMaxX(),unit.getLocation().getY(),unit.getLocation().getWidth(),unit.getLocation().getHeight()));
                    unit.setCollision(true);

                }
        }
        if (flag1)
        {

            unit.setOnEarth(true);

            unit.SetSpeed(unit.getSpeed().x, 0.0F);
        } else
            {
            unit.setOnEarth(false);
        }

    }
}