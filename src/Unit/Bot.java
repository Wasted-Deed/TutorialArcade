package Unit;

import ocean.Building;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class Bot extends Unit implements BehaveAi
{

    AiPatrol Ai;
    boolean Attacked =false;
    boolean CanAttack;
    public boolean isCanAttack() {
        return this.CanAttack;
    }

    public void setCanAttack(final boolean canAttack) {
        this.CanAttack = canAttack;
    }


    public boolean isAttacked() {
        return this.Attacked;
    }

    public void setAttacked(final boolean attacked) {
        this.Attacked = attacked;
    }


    public   LinkedList<Building> FindCloseBuildings(int distanceX,int distanceY,LinkedList<Building> buildings)
    {
            LinkedList<Building> Result=new LinkedList<>();
        Iterator var1 = buildings.iterator();
        while (var1.hasNext())
        {
            Building CurrentBuilding = (Building) var1.next();
            if((Math.abs(CurrentBuilding.getPos().getCenterX()-getLocation().getCenterX())<distanceX)&&
            (Math.abs(CurrentBuilding.getPos().getCenterX()-getLocation().getCenterX())<distanceY))
            {
                Result.add(CurrentBuilding);
            }


        }
        return Result;
    }

    public AiPatrol getAi() {
        return this.Ai;
    }

    public void setAi(final AiPatrol ai) {
        this.Ai = ai;
    }

    public void behave(ArrayList<? extends Unit> unit, LinkedList<Building> buildings)
    {


        Ai.behave(unit,buildings);
        if(this.getSpeed().getY()!=0) move();
        setCondition(this.isRightOrLeftLook() ? ConditionUnit.MOVE_RIGHT : ConditionUnit.MOVE_LEFT);
    }


    public Bot(int health, float mWalkSpeed, Rectangle location, int damage)
    {
        super(health, mWalkSpeed, location, damage);
        RightOrLeftLook=false;
        setCondition(ConditionUnit.MOVE_LEFT);
        this.SetSpeed(-this.getmWalkSpeed(), 0.0f);
    }

    @Override
    public void behave()
    {

    }



}