package Shell;

import Utils.ImageLoader;
import Utils.Sprites;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import java.util.HashMap;

public class Shell implements Cloneable
{

    Rectangle Location=new Rectangle(0,0,0,0);

    Point speed;
    ConditionShell condition;
    HashMap<ConditionShell, Sprites>  IDimage=new HashMap<>();
    TypeShell type;
    int damage;




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
        this.damage = newShell.getDamage() ;
        this.NumberCommand = newShell.getNumberCommand();
    }

    public int getNumberCommand() {
        return this.NumberCommand;
    }

    public void setNumberCommand(final int numberCommand) {
        this.NumberCommand = numberCommand;
    }

    public Shell(final Rectangle location, final Point speed, final ConditionShell condition, final HashMap<ConditionShell, Sprites> IDimage, final TypeShell type, final int damage, final int numberCommand) {
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
        IDimage.put(ConditionShell.MOVE_LEFT,Sprites.ShellL);
        IDimage.put(ConditionShell.MOVE_RIGHT,Sprites.ShellR);
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
        switch (condition)
        {
            case MOVE_RIGHT:
                speed.setX(Math.abs(speed.getX()));
                break;
            case MOVE_LEFT:
                speed.setX(-Math.abs(speed.getX()));
                break;
        }
        this.condition = condition;
    }
    public void draw(ImageLoader loader)
    {
        loader.getImagesMap().get(IDimage.get(condition)).draw(Location.getX(),Location.getY(),Location.getWidth(),Location.getHeight());
    }
    public void move()
    {

        if (condition==ConditionShell.MOVE_LEFT)Location.setLocation(getLocation().getX()-speed.getX(),getLocation().getY()+speed.getY());
   else if (condition==ConditionShell.MOVE_RIGHT) Location.setLocation(getLocation().getX()+speed.getX(),getLocation().getY()+speed.getY());
    }
    public Rectangle getLocation() {
        return this.Location;
    }



    public void setLocation(final Rectangle location) {
        this.Location = location;
    }

    public TypeShell getType() {
        return this.type;
    }


    public Point getSpeed() {
        return this.speed;
    }

    public void setSpeed(final Point speed) {
        this.speed = speed;
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(final int damage) {
        this.damage = damage;
    }

    public void update()
    {

    }
}

