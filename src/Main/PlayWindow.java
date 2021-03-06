package Main;

import Shell.Shell;
import Shell.*;
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

    private ArrayList<Bot> enemy = new ArrayList();
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
        this.loader.LoadImage(Sprites.FONT_0, "resources/images/font_2.png",0,0);
        this.loader.LoadImage(Sprites.ShellR, "resources/images/bulletR.png",0,0);
        this.loader.LoadImage(Sprites.ShellL, "resources/images/bulletL.png",0,0);
        this.loader.LoadImage(Sprites.BACKGROUND_SPACE, "resources/images/Kosmos2.png",0,0);
        this.loader.LoadImage(Sprites.PLAYER_L, "resources/images/playerL.png",41,60);
        this.loader.LoadImage(Sprites.PLAYER_R, "resources/images/playerR.png",0,0);
        this.loader.LoadImage(Sprites.FISH_L, "resources/images/fishL.png",0,0);
        this.loader.LoadImage(Sprites.FISH_R, "resources/images/fishR.png",0,0);
        this.loader.LoadImage(Sprites.ENEMY_0L, "resources/images/enemy_0L.png",65,47);
        this.loader.LoadImage(Sprites.ENEMY_0R, "resources/images/enemy_0R.png",65,47);
        this.loader.LoadImage(Sprites.ENEMY_1, "resources/images/Enemy_1.png",31,64);

        this.loader.LoadImage(Sprites.HEART, "resources/images/heart.png",0,0);
        BuilderShell BuilderBulletInvisible=new BuilderShell().setDamage(1).setType(TypeShell.ROCKET).setSpeed(1).setSize(new Point(20,10)).addIDimage(ConditionShell.MOVE,null);
        BuilderShell BuilderBulletNormal=new BuilderShell().setDamage(1).setType(TypeShell.ROCKET).setSpeed(2).setSize(new Point(20,10)).addIDimage(ConditionShell.MOVE,Sprites.ShellR);
        BuilderShell BuilderBulletFast=new BuilderShell().setDamage(1).setType(TypeShell.NORMAL).setSpeed(6).setSize(new Point(10,5)).addIDimage(ConditionShell.MOVE,Sprites.ShellR);
        background= (Image) loader.getImagesMap().get(Sprites.BACKGROUND_SPACE).getImage(0);
        CustomFont font=new CustomFont((Image) loader.getImagesMap().get(Sprites.FONT_0).getImage(0),10,10) ;
        font.loadBasicFont();
        Page page1=new Page();
        page1.addLine("1");
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


        Bot NewEnemy = new Bot(1, 1, new Rectangle(480, 0, 58.0F, 41.0F),2);
        NewEnemy.setmJumpSpeed(1.0F);
        NewEnemy.setNumberCommand(1);
        NewEnemy.getIDimage().put(ConditionUnit.MOVE_LEFT, Sprites.ENEMY_0L);
        NewEnemy.getIDimage().put(ConditionUnit.MOVE_RIGHT,Sprites.ENEMY_0R);
        NewEnemy.setCanAttack(true);
        NewEnemy.getListForce().put("Gravity", new Vector2f(0.0F, 5.0F));
        NewEnemy.setAi(new AiPatrol(NewEnemy,3000,500,300,6000,20));
        NewEnemy.setBuilder(BuilderBulletNormal);
        NewEnemy.setRightOrLeftLook(false);
      this.enemy.add(NewEnemy);
        Bot NewEnemy2 = new Bot(1, 0.3F, new Rectangle(300, 0,
                31, 64),2);
        NewEnemy2.getListForce().put("Gravity", new Vector2f(0.0F, 10.0F));
        NewEnemy2.setNumberCommand(1);
        NewEnemy2.getIDimage().put(ConditionUnit.MOVE_LEFT, Sprites.ENEMY_1);
        NewEnemy2.getIDimage().put(ConditionUnit.MOVE_RIGHT,Sprites.ENEMY_1);
        NewEnemy2.setCanAttack(true);
        NewEnemy2.setRightOrLeftLook(false);

        NewEnemy2.setAi(new AiPatrol(NewEnemy2,3000,500,0,6000,0));
         NewEnemy2.setBuilder(BuilderBulletInvisible);
        // this.enemy.add(NewEnemy2);
        this.player = new Player(10, 10.0F, new Rectangle(170.0F, 0, 58.0f, 41.0f),2);
        this.player.setmJumpSpeed(5.0F);
        this.player.setJump(false);
        player.setRightOrLeftLook(true);
        this.player.getListForce().put("Gravity", new Vector2f(0.0F, 10.0F));
        this.player.setBuilder(BuilderBulletFast);

        player.setNumberCommand(0);
       map.addBuilding(100, (float) 125.6);
        map.addBuilding(200, (float)232);
        map.addBuilding(220, (float)PLACE_BLOCKS_Y-70);
        map.addBuilding(253, (float)PLACE_BLOCKS_Y-70);
        map.addBuilding(285, (float)PLACE_BLOCKS_Y-70);
        map.addBuilding(317, (float)PLACE_BLOCKS_Y-70);
        map.addBuilding(349, (float)PLACE_BLOCKS_Y-70);
        map.addBuilding(190, (float)PLACE_BLOCKS_Y-70);
       map.addBuilding(100, (float)232);
        map.addBuilding(300, (float)232);

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


            CurrentShells.update(player);

            CurrentShells.move(player);

        }
        System.out.println(player.getLocation());
       // System.out.println("X=" + player.getLocation().getCenterX() + " Y=" + player.getLocation().getCenterY());
        ArrayList<Unit> AllUnit = new ArrayList();
        AllUnit.addAll(this.enemy);
        AllUnit.add(this.player);
        Iterator var1 = AllUnit.iterator();
        while(var1.hasNext())
        {
            Unit CurrentUnit = (Unit)var1.next();
            Physics.ForceGravityOnAllUnits(CurrentUnit);
            Physics.CollisionBulletWithUnit(CurrentUnit,shells);
            CurrentUnit.setOnEarth(false);
            Physics.CollisionWithBlocks(CurrentUnit,  map.getListBuildings());
            Physics.CollissionUnits(CurrentUnit,AllUnit);
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
            Bot CurrentEnemy = (Bot)var4.next();
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
            CurrentEnemy.behave(AllUnit,map.getListBuildings(),player);
            if (CurrentEnemy.isAttacked()) {
                shells.add(CurrentEnemy.attack(CurrentEnemy.getAi().getTargetEnemy(),player));
            }

        }
        if (CollisionWithAnyEnemy)
          dialogue.setVisible(true);
        else dialogue.setVisible(false);
        dialogue.checkClickOnButton((new CheckInput(gameContainer)).CheckClickMouse());
        dialogue.udpate();
        this.player.setInput(Input.CheckButtonMove());

        if (Input.CheckClickButtons()== TypeInput.Control)
        {
            shells.add(player.attack(null,player));
           //map.addBuilding(player.getLocation().getX()+50, player.getLocation().getY()+50);
        }

        this.player.behave();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
        background.draw(0,0,gameContainer.getWidth(),gameContainer.getHeight());
        map.draw(g);
        if (player.getInput()==TypeInput.L)
        {
           int fff=0;
           fff++;
        }
        this.player.draw(loader);

        Iterator var3 = this.enemy.iterator();
        while(var3.hasNext())
        {
            Bot CurrentEnemy = (Bot)var3.next();
            CurrentEnemy.draw(loader);
        }
        Iterator var4 = this.shells.iterator();
        while(var4.hasNext())
        {
            Shell CurrentShells = (Shell)var4.next();
            CurrentShells.draw(loader,g);
          //  g.drawRect(CurrentShells.getLocation().getX(),CurrentShells.getLocation().getY(),CurrentShells.getLocation().getWidth(),CurrentShells.getLocation().getHeight());
          //  g.drawString("X="+String.valueOf(CurrentShells.getLocation().getX()),CurrentShells.getLocation().getX(),CurrentShells.getLocation().getY());
        }
        dialogue.draw(g);
        for (int i=0;i<player.getHealth();i++)
        {
            Image CurrentHealth=(Image)loader.getImagesMap().get(Sprites.HEART).getImage(0);
            CurrentHealth.draw(25*i,0,25,25);
        }
    }
}
