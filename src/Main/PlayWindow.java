package Main;

import Unit.*;
import ocean.GameMap;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayWindow extends BasicGameState {

    private final int SPEED_BLOCKS = 5;

    private int STATE;
    private final int PLACE_BLOCKS_Y = 200;
    private int screenHeight, screenWidth;
    private GameMap map;


    private ArrayList<Ai> enemy = new ArrayList();
    private Player player;
    private ImageLoader loader;



    public PlayWindow(int state) {
        STATE = state;
    }

    @Override
    public int getID() {
        return STATE;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        map = new GameMap(gameContainer.getWidth());
        screenWidth = gameContainer.getWidth();
        screenHeight = gameContainer.getHeight();


        this.loader = new ImageLoader();
        this.loader.LoadImage(Sprites.PLAYER_L, "resources/images/player.jpg");
        this.loader.LoadImage(Sprites.FISH_L, "resources/images/fish.jpg");
        Ai NewEnemy = new Ai(1, 2.0F, new Rectangle(screenWidth - 200, PLACE_BLOCKS_Y,
                (float)((Image)this.loader.getImagesMap().get(Sprites.FISH_L)).getWidth(),
                (float)((Image)this.loader.getImagesMap().get(Sprites.FISH_L)).getHeight()), 2);
        NewEnemy.setImageR((Image)this.loader.getImagesMap().get(Sprites.FISH_L));
       // this.enemy.add(NewEnemy);
        this.player = new Player(3, 5.0F, new Rectangle(30.0F, PLACE_BLOCKS_Y-180,
                (float)((Image)this.loader.getImagesMap().get(Sprites.PLAYER_L)).getWidth(),
                (float)((Image)this.loader.getImagesMap().get(Sprites.PLAYER_L)).getHeight()), 2);
        this.player.setmJumpSpeed(3.0F);
        this.player.setJump(false);
        this.player.getListForce().put("Gravity", new Vector2f(0.0F, 10.0F));
        this.player.setImageR((Image)this.loader.getImagesMap().get(Sprites.PLAYER_L));
        map.addBuilding(player.getLocation().getX()+50, (float) 125.6);
        map.addBuilding(player.getLocation().getX()+50, (float)PLACE_BLOCKS_Y+16);
        map.addBuilding(player.getLocation().getX()+150, (float)PLACE_BLOCKS_Y-50);
        map.addBuilding(player.getLocation().getX()-50, (float)PLACE_BLOCKS_Y+17);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        /*Input input = gameContainer.getInput();
        if (input.isKeyDown(Input.KEY_LEFT))
            map.move(player.getmWalkSpeed());
        else if (input.isKeyDown(Input.KEY_RIGHT))
            map.move(-player.getmWalkSpeed());*/



        ArrayList<Unit> AllUnit = new ArrayList();
        AllUnit.addAll(this.enemy);
        AllUnit.add(this.player);
//Воздействие силы тяжести и обработка столкновений
        Iterator var1 = AllUnit.iterator();
        while(var1.hasNext())
        {
            Unit CurrentUnit = (Unit)var1.next();
            Physics.ForceGravityOnAllUnits(CurrentUnit);
            Physics.CollisionWithBlocks(CurrentUnit,  map.getListBuildings());
        }
        Iterator var4 = this.enemy.iterator();

        while(var4.hasNext())
        {
            Ai CurrentEnemy = (Ai)var4.next();
            CurrentEnemy.behave(this.enemy);
        }
        this.player.setInput((new ChechInput(gameContainer)).CheckPlayer());
        this.player.behave();

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        map.draw(g);
        this.player.draw(g);
    //   g.setBackground(Color.red);
        Iterator var3 = this.enemy.iterator();
        while(var3.hasNext()) {
            Ai CurrentEnemy = (Ai)var3.next();
            CurrentEnemy.draw(g);
        }
    }
}
