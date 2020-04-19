package Main;

import SystemDialogue.*;
import Unit.*;
import Utils.CheckInput;
import Utils.ImageLoader;
import Utils.Physics;
import Utils.Sprites;
import javafx.scene.layout.Background;
import ocean.GameMap;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayWindow extends BasicGameState {

    private final int SPEED_BLOCKS = 5;
    private Image background;
    private int STATE;
    private final int PLACE_BLOCKS_Y = 200;
    private int screenHeight, screenWidth;
    private GameMap map;


    private ArrayList<Ai> enemy = new ArrayList();
    private Dialogue dialogue=new Dialogue();
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
        TrueTypeFont FontText=new TrueTypeFont(new java.awt.Font("Text", java.awt.Font.LAYOUT_LEFT_TO_RIGHT,10),false);
        this.loader = new ImageLoader();
        this.loader.LoadImage(Sprites.FONT_0, "resources/images/font_2.jpg");
        this.loader.LoadImage(Sprites.BACKGROUND_SPACE, "resources/images/Kosmos2.png");
        background=loader.getImagesMap().get(Sprites.BACKGROUND_SPACE);
        CustomFont font=new CustomFont(loader.getImagesMap().get(Sprites.FONT_0),10,10) ;
        font.loadBasicFont();
        Page page1=new Page();
        //page1.addLine("1");
        page1.addLine("1234567890");
        page1.addLine(".,!?()'\"/|\\:;");
        page1.addLine("АБВГДЕЁЖЗИЙКЛ");
        page1.addLine("МНОПРСТУФХЦШ");
        page1.addLine("ЩЪЫЮЯ");
        Page page3=new Page();
        page3.addLine("1линия");
        page3.addLine("1 линия");
        page3.addLine("3 линия");
        page3.addLine("4 линия");
        page3.addLine("5 линия");
        dialogue.addPage(page1);
        dialogue.addPage(page3);
        dialogue.SetFontAllPage(font) ;
        dialogue.setDistancesFromTextToBorder(3);
        dialogue.addButton(new Button(ButtonName.NEXT,ConditionChoice.NEXT));
        dialogue.getButtons().get(ButtonName.NEXT).setFontName(FontText);
        dialogue.getButtons().get(ButtonName.NEXT).setVisible(true);
        dialogue.getButtons().get(ButtonName.NEXT).getLocation().setHeight(FontText.getLineHeight());
        dialogue.addButton(new Button(ButtonName.YES,ConditionChoice.YES));
        dialogue.getButtons().get(ButtonName.YES).setFontName(FontText);
        dialogue.getButtons().get(ButtonName.YES).getLocation().setHeight(FontText.getLineHeight());
        dialogue.addButton(new Button(ButtonName.NO,ConditionChoice.YES));
         dialogue.getButtons().get(ButtonName.NO).setFontName(FontText);
         dialogue.getButtons().get(ButtonName.NO).getLocation().setHeight(FontText.getLineHeight());

        this.loader = new ImageLoader();
        this.loader.LoadImage(Sprites.PLAYER_L, "resources/images/player.jpg");
        this.loader.LoadImage(Sprites.FISH_L, "resources/images/fish.jpg");
        Ai NewEnemy = new Ai(1, 0, new Rectangle(300, PLACE_BLOCKS_Y,
                (float)((Image)this.loader.getImagesMap().get(Sprites.FISH_L)).getWidth(),
                (float)((Image)this.loader.getImagesMap().get(Sprites.FISH_L)).getHeight()), 2);
        NewEnemy.setImageR((Image)this.loader.getImagesMap().get(Sprites.FISH_L));
        NewEnemy.getImageR().setRotation(180.0F);
        this.enemy.add(NewEnemy);
        this.player = new Player(3, 5.0F, new Rectangle(250.0F, PLACE_BLOCKS_Y-180,
                (float)((Image)this.loader.getImagesMap().get(Sprites.PLAYER_L)).getWidth(),
                (float)((Image)this.loader.getImagesMap().get(Sprites.PLAYER_L)).getHeight()), 2);
        this.player.setmJumpSpeed(3.0F);
        this.player.setJump(false);
        this.player.getListForce().put("Gravity", new Vector2f(0.0F, 10.0F));
        this.player.setImageR((Image)this.loader.getImagesMap().get(Sprites.PLAYER_L));
        map.addBuilding(80, (float) 125.6);
        map.addBuilding(80, (float)PLACE_BLOCKS_Y+16);
        map.addBuilding(180, (float)PLACE_BLOCKS_Y-50);
        map.addBuilding(-20, (float)PLACE_BLOCKS_Y+17);
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
        Boolean CollisionWithAnyEnemy=false;

        while(var4.hasNext())
        {
            Ai CurrentEnemy = (Ai)var4.next();
            if (player.checkCollision(CurrentEnemy.getLocation()))
            {
                CollisionWithAnyEnemy=true;
                dialogue.setMaxY((int) CurrentEnemy.getLocation().getY());
                if (dialogue.isVisible()==false)
                {
                    dialogue.setPlaceOutputText(new Rectangle(CurrentEnemy.getLocation().getX(), CurrentEnemy.getLocation().getY() - 100,
                            CurrentEnemy.getLocation().getWidth(), 100));

                }
                  if (dialogue.getCondition()==ConditionChoice.YES) CurrentEnemy.SetSpeed(5/ 10.0F, 0.0F);
            }
            CurrentEnemy.behave(this.enemy);
        }
        if (CollisionWithAnyEnemy)
          dialogue.setVisible(true);
        else dialogue.setVisible(false);
        dialogue.checkClickOnButton((new CheckInput(gameContainer)).CheckClickMouse());
        dialogue.udpate();
        this.player.setInput((new CheckInput(gameContainer)).CheckPlayer());
        this.player.behave();

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        background.draw(0,0,gameContainer.getWidth(),gameContainer.getHeight());
        map.draw(g);
        this.player.draw(g);
       //g.setBackground(Color.red);

        Iterator var3 = this.enemy.iterator();
        while(var3.hasNext())
        {
            Ai CurrentEnemy = (Ai)var3.next();

            CurrentEnemy.draw(g);
        }

        dialogue.draw(g);
    }
}
