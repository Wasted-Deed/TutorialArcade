package Main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {

    private final static int STATE_MENU = 0, STATE_PlAY = 1;
    private final static int SCREEN_WIDTH = 776, SCREEN_HEIGHT = 436;

    public Game(String name) {
        super(name);
        addState(new MenuWindow(STATE_MENU));
        addState(new PlayWindow(STATE_PlAY));
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        getState(STATE_MENU).init(gameContainer, this);
      //  getState(STATE_PlAY).init(gameContainer, this);
        enterState(STATE_MENU);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer game = new AppGameContainer(new Game("game"));
        game.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        game.setAlwaysRender(true);
        game.setTargetFrameRate(60);
        game.setMinimumLogicUpdateInterval(17);  // 60 ?????? ? ???????
        game.start();
    }
}
