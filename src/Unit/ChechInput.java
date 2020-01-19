package Unit;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class ChechInput
{
    GameContainer gc;
    public  ChechInput(GameContainer gc)
    {
        this.gc=gc;
    }
    public  TypeInput CheckPlayer()
    {
        Input input = this.gc.getInput();

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
}