import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class Player extends Unit
{
    TypeInput input=TypeInput.None;
    @Override
    public void attack() {

    }

    public Player(int health, float mWalkSpeed, Rectangle location, int damage) {
        super(health, mWalkSpeed, location, damage);
    }

    public TypeInput getInput() {
        return input;
    }

    public void setInput(TypeInput input) {
        this.input = input;
    }

    public void Control()
{

switch (input)
{
    case R_UP:
        move(getmWalkSpeed()  / 4, 0);
        if (!isJump())
        {
            float j = -getmJumpSpeed();
            addSpeed(0, (float) j);
            setJump(true);
        }
        break;
    case L_UP:
    move(-getmWalkSpeed()  / 4, 0);
    case Up:
        if (!isJump())
        {
            float j = -getmJumpSpeed();
            addSpeed(0, (float) j);
            setJump(true);
        }
        break;
    case L:
        move(-getmWalkSpeed()  / 10, 0);
        break;
    case R:
        move(getmWalkSpeed() / 10, 0);
        break;
}
}

    @Override
    public void behave()
    {
        Control();
        move();

    }


}
