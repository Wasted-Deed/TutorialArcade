package Unit;

import Utils.TypeInput;
import org.newdawn.slick.geom.Rectangle;

public class Player extends Unit
{
    TypeInput input;


    public void attack() {
    }

    public Player(int health, float mWalkSpeed, Rectangle location, int damage)
    {
        super(health, mWalkSpeed/10.0F, location, damage);
        this.input = TypeInput.None;
    }

    public TypeInput getInput() {
        return this.input;
    }

    public void setInput(TypeInput input) {
        this.input = input;
    }

    public void Control()
    {
        float j;
        switch(this.input)
        {

            case R_UP:
                if (!isJump())
                {
                    if (!isStopX()) this.move(this.getmWalkSpeed() , 0.0F);
                    if (!this.isJump()) {
                        j = -this.getmJumpSpeed();
                        this.addSpeed(0.0F, j);
                        this.setJump(true);
                    }
                }
                break;
            case L_UP:
                if (!isJump()) {
                    if (!isStopX()) this.move(-this.getmWalkSpeed() , 0.0F);
                }
            case Up:

                    if (!this.isJump())
                    {
                        j = -this.getmJumpSpeed();
                        this.addSpeed(0.0F, j);
                        this.setJump(true);
                        System.out.println("Õ¿∆¿“»≈!!!!!!!!!!!");
                    }
                break;
            case L:

               if (!isStopX())this.move(-this.getmWalkSpeed() , 0.0F);
                break;
            case R:
                if (!isStopX())  this.move(this.getmWalkSpeed() , 0.0F);
        }

    }

    public void behave() {
        this.Control();
        this.move();
    }
}
