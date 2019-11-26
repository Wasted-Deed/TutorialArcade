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

    public void Control(int delta)
{

switch (input)
{
    case R_UP:
        move(getmWalkSpeed() * delta / 10, 0);
        if (!isJump())
        {
            float j = -getmJumpSpeed();
            addSpeed(0, (float) j);
            setJump(true);
        }
        break;
    case L_UP:
    move(-getmWalkSpeed() * delta / 10, 0);
    case Up:
        if (!isJump())
        {
            float j = -getmJumpSpeed();
            addSpeed(0, (float) j);
            setJump(true);
        }
        break;
    case L:
        move(-getmWalkSpeed() * delta / 10, 0);
        break;
    case R:
        move(getmWalkSpeed() * delta / 10, 0);
        break;
}
}
    public void update(GameContainer gc,  int delta)
    {
        super.update(gc,delta);
        Control(delta);
        move();

    }
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException
    {


    }
}
