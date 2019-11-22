import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import static org.newdawn.slick.Graphics.MODE_ALPHA_MAP;

public class Player extends Unit
{
    @Override
    public void attack() {

    }

    public Player(int health, float mWalkSpeed, Rectangle location, int damage) {
        super(health, mWalkSpeed, location, damage);
    }
public void Control(GameContainer gc,  int delta)
{
    Input input = gc.getInput();

    if (input.isKeyPressed( Input.KEY_UP))
    {

        if (!isJump())
        {
            float j=-getmJumpSpeed();
            setSpeed(0, (float) j);
            setJump(true);
        }
    }
    if (input.isKeyDown(Input.KEY_LEFT))
    {
        move(-getmWalkSpeed()*delta/10,0);
    }else
    if (input.isKeyDown(Input.KEY_RIGHT))
    {
        move(getmWalkSpeed()*delta/10,0);
    }

}
    public void update(GameContainer gc,  int delta)
    {
        super.update(gc,delta);
        Control( gc, delta);
        move();

    }
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException
    {
        graphics.drawRect(getLocation().getX(),getLocation().getY(),getLocation().getWidth(),getLocation().getHeight());
        graphics.drawLine(-1000,400,1000,400);
    }
}
