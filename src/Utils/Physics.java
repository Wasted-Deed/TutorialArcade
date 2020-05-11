package Utils;

import Shell.Shell;
import Unit.Unit;
import Unit.Player;
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
    public static void collision(Unit unit,Rectangle pos)
    {
        //Проверка на столкновение с верхом
        if( (unit.checkCollision(new Rectangle(pos.getX()+2,pos.getY(),pos.getWidth()-4,2))))
        {
            unit.setOnEarth(true);
            unit.SetSpeed(unit.getSpeed().x, 0.0F);
           //   if ((unit.getSpeed().getY()>0))
                unit.setLocation(new Rectangle(unit.getLocation().getX(),pos.getY()-unit.getLocation().getHeight(),unit.getLocation().getWidth(),unit.getLocation().getHeight()));

        }else
            //проверка на столкновение с низом
            if ((unit.checkCollision(new Rectangle(pos.getX()+2,pos.getMaxY(),pos.getWidth()-4,2)))&&(unit.getSpeed().getY()<0)) {
                unit.SetSpeed(unit.getSpeed().x, 0.0F);
                unit.setLocation(new Rectangle(unit.getLocation().getX(),pos.getMaxY(),unit.getLocation().getWidth(),unit.getLocation().getHeight()));
            }
        //Столкновение слева
        if (unit.checkCollision(new Rectangle(pos.getX()+1,pos.getY()+2,2,pos.getHeight()-4))) {
            System.out.println("Столкновение!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            unit.setCollision(true);
            unit.setLocation(new Rectangle(pos.getX()-unit.getLocation().getWidth(),unit.getLocation().getY(),unit.getLocation().getWidth(),unit.getLocation().getHeight()));
        }else//Столкновение справа
            if (unit.checkCollision(new Rectangle(pos.getMaxX()-1,pos.getY()+2,2,pos.getHeight()-4))) { System.out.println("X="+unit.getLocation().getX());
                unit.setLocation(new Rectangle(pos.getMaxX(),unit.getLocation().getY(),unit.getLocation().getWidth(),unit.getLocation().getHeight()));
                unit.setCollision(true);
            }


    }
    public static void  CollisionWithBlocks(Unit unit, LinkedList<Building> buildings) {
        //unit.setOnEarth(false);
        unit.setCollision(false);
        for (Building building: buildings) {
            collision(unit, building.getPos());
        }
    }

    public static void CollissionUnits(Unit unit, ArrayList<Unit> allUnit)
    {
        //unit.setOnEarth(false);
        Iterator var1 = allUnit.iterator();
        while(var1.hasNext())
        {
            Unit CurrentUnit = (Unit) var1.next();
            if (!CurrentUnit.equals(unit))
            {
                collision(unit, CurrentUnit.getLocation());
            }
        }
    }
}