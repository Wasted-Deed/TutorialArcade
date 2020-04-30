package Main;

import Shell.Shell;
import SystemDialogue.*;
import Unit.*;
import Utils.*;
import ocean.GameMap;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import  Shell.TypeShell;
import java.util.ArrayList;
import java.util.Iterator;

public class PlayWindow extends BasicGameState {

    private final int SPEED_BLOCKS = 5;
    private Image background;
    private int STATE;
    private final int PLACE_BLOCKS_Y = 200;
    private GameMap map;
    private CheckInput Input;

    private ArrayList<Ai> enemy = new ArrayList();
    private ArrayList<Shell> shells=new ArrayList<>();
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
        Input= new CheckInput(gameContainer);
        TrueTypeFont FontText=new TrueTypeFont(new java.awt.Font("Text", java.awt.Font.LAYOUT_LEFT_TO_RIGHT,10),false);
        this.loader = new ImageLoader();
        this.loader.LoadImage(Sprites.FONT_0, "resources/images/font_2.png");
        this.loader.LoadImage(Sprites.ShellR, "resources/images/bulletR.png");
        this.loader.LoadImage(Sprites.ShellL, "resources/images/bulletL.png");
        this.loader.LoadImage(Sprites.BACKGROUND_SPACE, "resources/images/Kosmos2.png");
        this.loader.LoadImage(Sprites.PLAYER_L, "resources/images/playerL.png");
        this.loader.LoadImage(Sprites.PLAYER_R, "resources/images/playerR.png");
        this.loader.LoadImage(Sprites.FISH_L, "resources/images/fishL.png");
        this.loader.LoadImage(Sprites.FISH_R, "resources/images/fishR.png");
        this.loader.LoadImage(Sprites.ENEMY_0L, "resources/images/enemy_0L.png");
        this.loader.LoadImage(Sprites.ENEMY_0R, "resources/images/enemy_0R.png");
        this.loader.LoadImage(Sprites.HEART, "resources/images/heart.png");
        background=loader.getImagesMap().get(Sprites.BACKGROUND_SPACE);
        CustomFont font=new CustomFont(loader.getImagesMap().get(Sprites.FONT_0),10,10) ;
        font.loadBasicFont();
        Page page1=new Page();
        //page1.addLine("1");
        page1.addLine("1234567890");
        page1.addLine(".,!?()'\"/|\\:;");
        page1.addLine("�����Ũ������");
        page1.addLine("������������");
        page1.addLine("�����");
        Page page3=new Page();
        page3.addLine("1�����");
        page3.addLine("1 �����");
        page3.addLine("3 �����");
        page3.addLine("4 �����");
        page3.addLine("5 �����");
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


        Ai NewEnemy = new Ai(1, 0, new Rectangle(300, PLACE_BLOCKS_Y,
                (float)((Image)this.loader.getImagesMap().get(Sprites.FISH_L)).getWidth(),
                (float)((Image)this.loader.getImagesMap().get(Sprites.FISH_L)).getHeight()), 2);
        NewEnemy.setNumberCommand(1);
        NewEnemy.getIDimage().put(ConditionUnit.MOVE_LEFT, Sprites.FISH_L);
        NewEnemy.getIDimage().put(ConditionUnit.MOVE_RIGHT,Sprites.FISH_R);
        NewEnemy.setDelayShooting(100000);
        NewEnemy.setRadiusVisibility(-5);
        this.enemy.add(NewEnemy);
        Ai NewEnemy2 = new Ai(1, 0, new Rectangle(290, PLACE_BLOCKS_Y-180,
                (float)((Image)this.loader.getImagesMap().get(Sprites.PLAYER_L)).getWidth(),
                (float)((Image)this.loader.getImagesMap().get(Sprites.PLAYER_L)).getHeight()), 2);
        NewEnemy2.getListForce().put("Gravity", new Vector2f(0.0F, 10.0F));
        NewEnemy2.setNumberCommand(1);
        NewEnemy2.setRadiusVisibility(200);
        NewEnemy2.setDelayShooting(2000);
        NewEnemy2.getIDimage().put(ConditionUnit.MOVE_LEFT, Sprites.ENEMY_0L);
        NewEnemy2.getIDimage().put(ConditionUnit.MOVE_RIGHT,Sprites.ENEMY_0R);
        this.enemy.add(NewEnemy2);
        this.player = new Player(3, 5.0F, new Rectangle(170.0F, PLACE_BLOCKS_Y-180,
                (float)((Image)this.loader.getImagesMap().get(Sprites.PLAYER_L)).getWidth(),
                (float)((Image)this.loader.getImagesMap().get(Sprites.PLAYER_L)).getHeight()), 2);
        this.player.setmJumpSpeed(3.0F);
        this.player.setJump(false);
        this.player.getListForce().put("Gravity", new Vector2f(0.0F, 10.0F));
        this.player.setTypeBullet(TypeShell.NORMAL);

        player.setNumberCommand(0);
        map.addBuilding(80, (float) 125.6);
        map.addBuilding(80, (float)PLACE_BLOCKS_Y+16);
        map.addBuilding(200, (float)PLACE_BLOCKS_Y-50);
        map.addBuilding(233, (float)PLACE_BLOCKS_Y-50);
        map.addBuilding(265, (float)PLACE_BLOCKS_Y-50);
        map.addBuilding(297, (float)PLACE_BLOCKS_Y-50);
        map.addBuilding(329, (float)PLACE_BLOCKS_Y-50);
        map.addBuilding(170, (float)PLACE_BLOCKS_Y-50);
        map.addBuilding(-20, (float)PLACE_BLOCKS_Y+17);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        /*Input input = gameContainer.getInput();
        if (input.isKeyDown(Input.KEY_LEFT))
            map.move(player.getmWalkSpeed());
        else if (input.isKeyDown(Input.KEY_RIGHT))
            map.move(-player.getmWalkSpeed());*/

//����������� ���� ������� � ��������� ������������
        Iterator var2 = this.shells.iterator();
        while(var2.hasNext())
        {
            Shell CurrentShells = (Shell)var2.next();
            CurrentShells.move();
        }
        ArrayList<Unit> AllUnit = new ArrayList();
        AllUnit.addAll(this.enemy);
        AllUnit.add(this.player);
        Iterator var1 = AllUnit.iterator();
        while(var1.hasNext())
        {
            Unit CurrentUnit = (Unit)var1.next();
            Physics.ForceGravityOnAllUnits(CurrentUnit);
            Physics.CollisionWithBlocks(CurrentUnit,  map.getListBuildings());
            Physics.CollisionBulletWithUnit(CurrentUnit,shells);
            if (CurrentUnit.getHealth()<=0)
            {
                if (enemy.contains(CurrentUnit)) enemy.remove(CurrentUnit);
                   else if (CurrentUnit.equals(player)) System.exit(0);
            }
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
            CurrentEnemy.behave(AllUnit);
            if (CurrentEnemy.isAttack())
                shells.add(CurrentEnemy.attack(new Point(1,0)));

        }
        if (CollisionWithAnyEnemy)
          dialogue.setVisible(true);
        else dialogue.setVisible(false);
        dialogue.checkClickOnButton((new CheckInput(gameContainer)).CheckClickMouse());
        dialogue.udpate();
        this.player.setInput(Input.CheckButtonMove());

        if (Input.CheckClickButtons()== TypeInput.Control)
            shells.add(player.attack(new Point(5,0)));


        this.player.behave();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        background.draw(0,0,gameContainer.getWidth(),gameContainer.getHeight());
        map.draw(g);
        this.player.draw(loader);

        Iterator var3 = this.enemy.iterator();
        while(var3.hasNext())
        {
            Ai CurrentEnemy = (Ai)var3.next();
            CurrentEnemy.draw(loader);
        }
        Iterator var4 = this.shells.iterator();
        while(var4.hasNext())
        {
            Shell CurrentShells = (Shell)var4.next();
            CurrentShells.draw(loader);
        }
        dialogue.draw(g);
        for (int i=0;i<player.getHealth();i++)
        {
            loader.getImagesMap().get(Sprites.HEART).draw(25*i,0,25,25);
        }
    }
}
