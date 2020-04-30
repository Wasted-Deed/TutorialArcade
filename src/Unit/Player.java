package Unit;

import Shell.Shell;
import Utils.CheckInput;
import Utils.Sprites;
import Utils.TypeInput;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

public class Player extends Unit
{
    TypeInput input;



    public Player(int health, float mWalkSpeed, Rectangle location, int damage)
    {
        super(health, mWalkSpeed/10.0F, location, damage);
        IDimage.put(ConditionUnit.MOVE_LEFT, Sprites.PLAYER_L);
        IDimage.put(ConditionUnit.MOVE_RIGHT,Sprites.PLAYER_R);
        setCondition(ConditionUnit.MOVE_RIGHT);
    }

    public TypeInput getInput() {
        return this.input;
    }



    public void setInput(final TypeInput input) {
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
                setCondition(ConditionUnit.MOVE_RIGHT);
                break;
            case L_UP:
                if (!isJump()) {
                    if (!isStopX()) this.move(-this.getmWalkSpeed() , 0.0F);
                }
                setCondition(ConditionUnit.MOVE_LEFT);
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
                RightOrLeftLook=false;
                setCondition(ConditionUnit.MOVE_LEFT);
                break;
            case R:
                if (!isStopX())  this.move(this.getmWalkSpeed() , 0.0F);
                RightOrLeftLook=true;
                setCondition(ConditionUnit.MOVE_RIGHT);
                break;
        }

    }

    public void behave()
    {
        this.Control();
        this.move();
    }


}
