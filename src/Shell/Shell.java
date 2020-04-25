package Shell;

import Utils.ImageLoader;
import Utils.Sprites;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import java.util.HashMap;

public class Shell
{
    Rectangle Location;
    TypeShell type;
    Point speed;

    public int getNumberCommand() {
        return this.NumberCommand;
    }

    public void setNumberCommand(final int numberCommand) {
        this.NumberCommand = numberCommand;
    }

    int damage;
    int NumberCommand;
    ConditionShell condition;
    HashMap<ConditionShell, Sprites>  IDimage=new HashMap<>();
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
        Location.setLocation(getLocation().getX()+speed.getX(),getLocation().getY()+speed.getY());
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

