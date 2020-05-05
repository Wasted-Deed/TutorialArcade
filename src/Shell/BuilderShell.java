package Shell;

import Utils.Sprites;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import java.util.HashMap;

public class BuilderShell
{
    Shell NewShell=new Shell();



    public BuilderShell setSize(final Point size)
    {

        NewShell.getLocation().setHeight( size.getY());
        NewShell.getLocation().setWidth( size.getX());

        return this;
    }

    public BuilderShell setDamage(final int damage)
    {
        NewShell.damage = damage;
        return this;
    }

    public BuilderShell setNumberCommand(final int numberCommand)
    {
        NewShell.NumberCommand = numberCommand;
        return this;
    }

    public BuilderShell setPosition(final Point location) {
        NewShell.getLocation().setLocation(location.getX(),location.getY());
        return this;
    }

    public BuilderShell setSpeed(final Point speed) {
        NewShell.speed = speed;
        return this;
    }

    public BuilderShell setCondition(final ConditionShell condition) {
        NewShell.condition = condition;
        return this;
    }

    public BuilderShell setIDimage(final HashMap<ConditionShell, Sprites> IDimage) {
        NewShell.IDimage = IDimage;
        return this;
    }

    public BuilderShell setType(final TypeShell type) {
        NewShell.type = type;
        return this;
    }

    public Shell build()
    {
        return  new Shell(NewShell);
    }

}
