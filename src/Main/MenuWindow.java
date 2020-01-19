package Main;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuWindow extends BasicGameState {

    private int STATE;
    private Image backgroundImage;
    private int screenWidth, screenHeight;

    public MenuWindow(int state) {
        STATE = state;
    }

    @Override
    public int getID() {
        return STATE;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        backgroundImage = new Image("menu.jpg");
        screenWidth = gameContainer.getWidth();
        screenHeight = gameContainer.getHeight();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int time) {
        Input input = gameContainer.getInput();
        if (input.isMouseButtonDown(0)) {
            int inputX = Mouse.getX();
            int inputY = Mouse.getY();
            if (inputX > 240 && inputX < 530 && inputY > 310 && inputY < 370)
                stateBasedGame.enterState(1);
        }
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g)  {
        g.drawImage(backgroundImage, 0, 0);
    }
}
