package Unit;

import Shell.Shell;
import ocean.Building;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class Ai extends Unit implements BehaveAi {
    public int getDelayShooting() {
        return this.DelayShooting;
    }

    public void setDelayShooting(final int delayShooting) {
        this.DelayShooting = delayShooting;
    }

    int RadiusVisibility=0;
    long TimeShooting=0;
    int DelayShooting=0;
    boolean Attack=false;

    public int getRadiusVisibility() {
        return this.RadiusVisibility;
    }

    public void setRadiusVisibility(final int radiusVisibility) {
        this.RadiusVisibility = radiusVisibility;
    }

    public boolean isAttack() {
        return this.Attack;
    }

    public void setAttack(final boolean attack) {
        this.Attack = attack;
    }

    public void behave(ArrayList<? extends Unit> unit)
    {

        Iterator var1 = unit.iterator();
        while (var1.hasNext())
        {

            Unit CurrentUnit=(Unit)var1.next();
            if (CurrentUnit.equals(this))
                continue;
            Rectangle LeftR=new Rectangle(getLocation().getX()-RadiusVisibility,getLocation().getY(),
                    getLocation().getWidth()/2+RadiusVisibility,getLocation().getHeight());
            Rectangle RightR =new Rectangle(getLocation().getMaxX(),getLocation().getY(),
                    getLocation().getWidth()+RadiusVisibility,getLocation().getHeight());
            boolean Left=(CurrentUnit.getLocation().intersects(LeftR)&&(!isRightOrLeftLook()));
            boolean Right=(((CurrentUnit.getLocation().intersects(RightR)&&(isRightOrLeftLook()))));
             if (( Right||Left) &&(CurrentUnit.getNumberCommand()!=getNumberCommand()))
            {
                 if ((System.currentTimeMillis()-TimeShooting)>DelayShooting)
                 {
                     Attack = true;
                     TimeShooting=  System.currentTimeMillis();
                 }else  Attack = false;
                 break;
            }
        }
        if (this.getLocation().getMinX() <= 0.0F)
        {

            this.SetSpeed(-this.getSpeed().getX(), 0.0F);
        }

        if (this.getLocation().getMaxX() >= 700.0F)
        {
            this.SetSpeed(-this.getSpeed().getX(), 0.0F);
        }
        if (getSpeed().getX()>0) setCondition(ConditionUnit.MOVE_RIGHT);
        else setCondition(ConditionUnit.MOVE_LEFT);
        this.move();
    }


    public Ai(int health, float mWalkSpeed, Rectangle location, int damage)
    {
        super(health, mWalkSpeed, location, damage);
        RightOrLeftLook=false;
        setCondition(ConditionUnit.MOVE_LEFT);
        this.SetSpeed(-this.getmWalkSpeed() / 10.0F, 0.0F);
    }

    @Override
    public void behave()
    {

    }



}