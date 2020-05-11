package Shell;

import Unit.Unit;
import Utils.ImageLoader;
import Utils.Sprites;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import java.util.HashMap;

public class Shell implements Cloneable
{

    Rectangle Location=new Rectangle(0,0,0,0);

    double speed;
    ConditionShell condition;
    HashMap<ConditionShell, Sprites>  IDimage=new HashMap<>();
    TypeShell type;
    int damage;
    Rectangle Target=new Rectangle(0,0,0,0);
    float angle;



    public HashMap<ConditionShell, Sprites> getIDimage() {
        return this.IDimage;
    }

    public void setIDimage(final HashMap<ConditionShell, Sprites> IDimage) {
        this.IDimage = IDimage;
    }


    int NumberCommand;

    public Shell(Shell newShell)
    {
        this.Location.setLocation(newShell.getLocation().getX(),newShell.getLocation().getY());
        Location.setSize(newShell.getLocation().getWidth(),newShell.getLocation().getHeight());
        this.speed = newShell.getSpeed();
        this.condition = newShell.getCondition();
        this.IDimage = newShell.getIDimage();
        this.type = newShell.getType();
        this.angle=newShell.getAngle();
        this.damage = newShell.getDamage() ;
        this.Target=newShell.getTarget();
        this.NumberCommand = newShell.getNumberCommand();

    }

    public int getNumberCommand() {
        return this.NumberCommand;
    }

    public void setNumberCommand(final int numberCommand) {
        this.NumberCommand = numberCommand;
    }

    public Shell(final Rectangle location, final int speed, final ConditionShell condition, final HashMap<ConditionShell, Sprites> IDimage, final TypeShell type, final int damage, final int numberCommand) {
        this.Location = location;
        this.speed = speed;
        this.condition = condition;
        this.IDimage = IDimage;
        this.type = type;
        this.damage = damage;
        this.NumberCommand = numberCommand;
    }
    public Shell()
    {
    }

    public void setType(final TypeShell type)
    {
        this.type = type;
    }

    public ConditionShell getCondition() {
        return this.condition;
    }

    public void setCondition(final ConditionShell condition)
    {
        this.condition = condition;
    }

    public Float getAngle() {
        return this.angle;
    }

    public void setAngle(final Float angle) {
        this.angle = angle;
    }

    public void draw(ImageLoader loader, Graphics g)
    {
       if (IDimage.get(condition)!=null)
        {
            Image img= ((Image)loader.getImagesMap().get(IDimage.get(condition)).getImage(0));
            img.setCenterOfRotation(0, 0);
            img.setRotation(angle);
            img.draw(Location.getX(), Location.getY(), Location.getWidth(), Location.getHeight());
            loader.getImagesMap().get(IDimage.get(condition)).getCurrentFrame().setRotation((float) 0);
        }
    }

    public void move(Unit player)
    {

        /*if (condition==ConditionShell.MOVE_LEFT)Location.setLocation(getLocation().getX()-speed,getLocation().getY()+speed);
   else if (condition==ConditionShell.MOVE_RIGHT) Location.setLocation(getLocation().getX()+speed,getLocation().getY()+speed);*/
      //  System.out.println((speed*speed/(Math.pow(Math.tan(angle*Math.PI/180),2)+1)));
        double SpeedX=  Math.sqrt(speed*speed/(Math.pow(Math.tan(angle*Math.PI/180),2)+1));
        double SpeedPow=speed*speed;
        double tg=Math.tan(angle*Math.PI/180);
        double TgPow=Math.pow(tg,2);
        double znam=TgPow+1;
        double mn=Math.sqrt(TgPow/(znam));
        double SpeedY=SpeedPow*mn  ;
        Location.setLocation((float) (getLocation().getX()+(((angle>90&& this.angle <270)) ? (-SpeedX): SpeedX)) ,
                (float) (getLocation().getY()+((((angle> 180)) ? (-SpeedY): (SpeedY)))));

      //  System.out.println("X="+getLocation().getX()+"MaxX="+getLocation().getMaxX()+"Y="+getLocation().getY()+"MaxY="+getLocation().getMaxY());

    //    System.out.println("Move="+(player.getLocation()==getTarget()));

    }
    public Rectangle getLocation() {
        return this.Location;
    }



    public void setLocation(final Rectangle location) {
        this.Location = location;
    }

    public TypeShell getType (){
        return this.type;
    }


    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(final int speed) {
        this.speed = speed;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(final int damage) {
        this.damage = damage;
    }

    public Rectangle getTarget() {
        return this.Target;
    }

    public void setTarget(final Rectangle target)
    {
        this.Target = target;
        updateSpeed();
    }

    public void updateSpeed()
    {

            double x = getTarget().getCenterX() - getLocation().getCenterX();
            double y = getTarget().getCenterY() - getLocation().getCenterY();

            if ((x < 20)) {
                int ere = 8;
                ere++;
            }
            float angle = (float) (Math.atan(Math.abs(y / x)) * (180 / Math.PI));
        //    System.out.println("X_0=" + x + " Y_0=" + y + " Угол=" + angle);
          //  System.out.println("X=" + getLocation().getCenterX() + " Y=" + getLocation().getCenterY());
        ///    System.out.println("targetX" + getTarget().getCenterX()+""+"targetY" + getTarget().getCenterY());
            if (x > 0) {
                if (y > 0) setAngle(angle);
                else setAngle(270 + angle);
            } else if (x < 0) {
                if (y > 0) setAngle(180 - angle);
                else setAngle(180 + angle);
            }

    }

    public void update(Unit player)
    {
     //   System.out.println("X="+getLocation().getX()+"MaxX="+getLocation().getMaxX()+"Y="+getLocation().getY()+"MaxY="+getLocation().getMaxY());
     //   System.out.println("Обновление="+(player.getLocation()==getTarget()));
        if (type==TypeShell.ROCKET)
        {
            double distance= Math.sqrt(Math.pow(getTarget().getCenterX()-getLocation().getCenterX(),2)+Math.pow(getTarget().getCenterY()-getLocation().getCenterY(),2));
            if (distance<100) type=TypeShell.NORMAL;
           else updateSpeed();



        }

    }
}

