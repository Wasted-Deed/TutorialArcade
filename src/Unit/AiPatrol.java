package Unit;

import ocean.Building;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class AiPatrol implements BehaveAi
{
    Bot bot;
    long TimeMovePatrol;
    int DelayChangeMove=3000;
    boolean DetectedPlayer;
    int RadiusVisibility;
    int RadiusAttack;
    int RadiusDistanceToEnemy;

    public Rectangle getTargetEnemy() {
        return this.TargetEnemy;
    }

    public void setTargetEnemy(final Rectangle targetEnemy) {
        this.TargetEnemy = targetEnemy;
    }

    long TimeShooting;
    int DelayShooting;
    Rectangle TargetEnemy;
    public Bot getBot() {
        return bot;
    }

    public void setBot(Bot bot) {
        this.bot = bot;
    }

    public void patrolAi(final LinkedList<Building> buildings) {
        if (!this.DetectedPlayer) {
            if ((this.bot.getSpeed().getY() == 0)) {
                final Iterator var1 = buildings.iterator();
                while (var1.hasNext())
                {
                    final Building CurrentBuilding = (Building) var1.next();
                    if ((Math.abs(CurrentBuilding.getPos().getY() - this.bot.getLocation().getMaxY()) > 10) && (Math.abs(CurrentBuilding.getPos().getY() - this.bot.getLocation().getMaxY()) < 20)) {
                        if ((Math.abs(CurrentBuilding.getPos().getX() - this.bot.getLocation().getMaxX()) < 20) && (this.bot.getSpeed().getX() > 0)) {
                            this.bot.SetSpeed(this.bot.getmWalkSpeed(), this.bot.getSpeed().getY());
                            bot.setRightOrLeftLook(true);
                            this.TimeMovePatrol = System.currentTimeMillis();

                        } else if ((Math.abs(CurrentBuilding.getPos().getMaxX() - this.bot.getLocation().getX()) < 20) && (this.bot.getSpeed().getX() < 0)) {
                            this.bot.SetSpeed(-this.bot.getmWalkSpeed(), this.bot.getSpeed().getY());

                            this.TimeMovePatrol = System.currentTimeMillis();
                        }
                    }
                }
                if ((System.currentTimeMillis() - this.TimeMovePatrol) > this.DelayChangeMove) {
                    this.bot.SetSpeed(-this.bot.getSpeed().getX(), this.bot.getSpeed().getY());
                    bot.setRightOrLeftLook(!bot.isRightOrLeftLook());
                    this.bot.move();
                    this.TimeMovePatrol = System.currentTimeMillis();
                }
            }
            this.bot.move();
        }

    }
    public void PursuerAi(final LinkedList<Building> buildings, final ArrayList<? extends Unit> unit, final Rectangle PositionEnemy)
    {
        if (PositionEnemy!=null) {
            if (this.DetectedPlayer) {
                if (!this.bot.isCollision())
                {
                    int distance= (int) Math.abs(PositionEnemy.getCenterX() - this.bot.getLocation().getCenterX());
                    //System.out.println(distance+" RadiusDistanceToEnemy="+RadiusDistanceToEnemy);
                    if (( distance>= (this.RadiusDistanceToEnemy + this.bot.getmWalkSpeed()))) {
                        if (this.bot.isRightOrLeftLook())
                            this.bot.SetSpeed(Math.abs(this.bot.getSpeed().getX()), this.bot.getSpeed().getY());
                        else this.bot.SetSpeed(-Math.abs(this.bot.getSpeed().getX()), this.bot.getSpeed().getY());
                        this.bot.move();
                    } else if (distance <= (this.RadiusDistanceToEnemy - this.bot.getmWalkSpeed())) {
                        if (this.bot.isRightOrLeftLook())
                            this.bot.SetSpeed(-Math.abs(this.bot.getSpeed().getX()), this.bot.getSpeed().getY());
                        else this.bot.SetSpeed(Math.abs(this.bot.getSpeed().getX()), this.bot.getSpeed().getY());
                        this.bot.move();
                    }
                }
            }

        }
        this.patrolAi(buildings);
    }
    public void attack(final Unit CurrentUnit )
    {
        this.DetectedPlayer =true;
        int distance= (int) Math.abs(CurrentUnit.getLocation().getCenterX()- this.bot.getLocation().getCenterX());
                if (((((System.currentTimeMillis()- this.TimeShooting)> this.DelayShooting)&&(CurrentUnit.getNumberCommand()!= this.bot.getNumberCommand())&& this.bot.isCanAttack()))&&
                    ((distance-CurrentUnit.getLocation().getWidth()/2-getBot().getLocation().getWidth()/2)<= this.RadiusAttack))
                {
                    this.bot.setAttacked(true);
                    this.TimeShooting =  System.currentTimeMillis();
                }else this.bot.setAttacked(false);
    }



    public long getTimeMovePatrol() {
        return this.TimeMovePatrol;
    }

    public void setTimeMovePatrol(final long timeMovePatrol) {
        this.TimeMovePatrol = timeMovePatrol;
    }
    public AiPatrol()
    {

    }
    public AiPatrol(final Bot bot, final int delayChangeMove, final int radiusVisibility, final int radiusAttack, final int delayShooting, int RadiusDistanceToEnemy) {
        this.bot = bot;
        this.DelayChangeMove = delayChangeMove;
        this.RadiusVisibility = radiusVisibility;
        this.RadiusAttack = radiusAttack;
        this.DelayShooting = delayShooting;
        this.RadiusDistanceToEnemy=RadiusDistanceToEnemy;
    }

    public int getDelayChangeMove() {
        return this.DelayChangeMove;
    }

    public void setDelayChangeMove(final int delayChangeMove) {
        this.DelayChangeMove = delayChangeMove;
    }

    public boolean isDetectedPlayer() {
        return this.DetectedPlayer;
    }

    public void setDetectedPlayer(final boolean detectedPlayer) {
        this.DetectedPlayer = detectedPlayer;
    }

    public int getRadiusVisibility() {
        return this.RadiusVisibility;
    }

    public void setRadiusVisibility(final int radiusVisibility) {
        this.RadiusVisibility = radiusVisibility;
    }

    public int getRadiusAttack() {
        return this.RadiusAttack;
    }

    public void setRadiusAttack(final int radiusAttack) {
        this.RadiusAttack = radiusAttack;
    }

    public long getTimeShooting() {
        return this.TimeShooting;
    }

    public void setTimeShooting(final long timeShooting) {
        this.TimeShooting = timeShooting;
    }

    public int getDelayShooting() {
        return this.DelayShooting;
    }

    public void setDelayShooting(final int delayShooting) {
        this.DelayShooting = delayShooting;
    }

    private Unit CheckEnemy(final ArrayList<? extends Unit> unit)
    {
        final Iterator var1 = unit.iterator();
        Unit      CurrentUnit=null;
        while (var1.hasNext())
        {
            CurrentUnit = (Unit) var1.next();
            if (CurrentUnit.equals(this.bot))
                continue;
            int distance= (int) Math.abs(CurrentUnit.getLocation().getCenterX() - this.bot.getLocation().getCenterX());
            //Проверка на попадание в зону видимости текущего юнита
            final boolean Left = ((getBot().getLocation().getCenterX()>CurrentUnit.getLocation().getCenterX())
                    && (Math.abs(CurrentUnit.getLocation().getCenterY() - this.bot.getLocation().getCenterY()) < this.bot.getLocation().getHeight())
                    && (!this.bot.isRightOrLeftLook()));
            final boolean Right =(getBot().getLocation().getCenterX()<CurrentUnit.getLocation().getCenterX())&&
                     (Math.abs(CurrentUnit.getLocation().getCenterY() - this.bot.getLocation().getCenterY()) < this.bot.getLocation().getHeight())
                    && (this.bot.isRightOrLeftLook());
            if ((distance<RadiusVisibility)&&(Left||Right)) return CurrentUnit;
        }
        return null;
    }
    @Override
    public void behave(final ArrayList<? extends Unit> unit, final LinkedList<Building> buildings,Unit player)
    {
       final Unit DetectedEnemy= this.CheckEnemy(unit);

        if ((DetectedEnemy == null))
        {
            this.DetectedPlayer = false;
        } else {
            TargetEnemy=DetectedEnemy.getLocation();

            this.attack(DetectedEnemy);
        }

        if (getBot().isOnEarth()) {
            this.PursuerAi(buildings, unit, TargetEnemy);
        }
    }

    @Override
    public void behave() {

    }
}
