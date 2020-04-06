package Utils;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import java.awt.*;

public class CheckInput
{
    GameContainer gc;
    Input input ;
    public CheckInput(GameContainer gc)
    {
        this.gc=gc;
        input = this.gc.getInput();
    }
    public TypeInput CheckPlayer()
    {


        if (input.isKeyPressed( Input.KEY_UP))
        {

            if (input.isKeyDown( Input.KEY_RIGHT))
                return TypeInput.R_UP;
            else if (input.isKeyDown( Input.KEY_LEFT))
                return TypeInput.L_UP;
            else return TypeInput.Up;
        }
        else {
            if (input.isKeyDown( Input.KEY_RIGHT))
                return TypeInput.R;
            else if (input.isKeyDown( Input.KEY_LEFT))
                return TypeInput.L;
            else return TypeInput.None;
        }

    }
    public Point CheckClickMouse()
    {
        if (input.isMousePressed(input.MOUSE_LEFT_BUTTON))
        {
            return new Point(input.getMouseX(),input.getMouseY());
        }else return null;
    }
}