import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//Сделал этот класс для тестирования
public class Game extends BasicGame
{
     private ArrayList<Ai> enemy=new ArrayList<>();
     private Player player;
     private  ImageLoader loader;
    public Game(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException
    {

        loader=new ImageLoader();
        loader.LoadImage(Sprites.PLAYER_L, "resources/player.jpg");
        loader.LoadImage(Sprites.FISH_L,"resources/fish.jpg");

        Ai NewEnemy=new Ai(1,2,new Rectangle(100,50,loader.getImagesMap().get(Sprites.FISH_L).getWidth(),
                loader.getImagesMap().get(Sprites.FISH_L).getHeight()),2);
        NewEnemy.setImageR(loader.getImagesMap().get(Sprites.FISH_L));
        enemy.add(NewEnemy);


        player=new Player(3,5,new Rectangle(30,50,loader.getImagesMap().get(Sprites.PLAYER_L).getWidth(),
                                loader.getImagesMap().get(Sprites.PLAYER_L).getHeight()),2);

        player.setmJumpSpeed((float) 1.5);
        player.setJump(false);
        player.getListForce().put("Gravity",new Vector2f(0, (float) 10));
        player.setImageR(loader.getImagesMap().get(Sprites.PLAYER_L) );
    }


    @Override
    public void update(GameContainer gc, int i) throws SlickException
    {
        ArrayList<Unit> AllUnit=new ArrayList();
        AllUnit.addAll(enemy);
        AllUnit.add(player);
        Physics.ForceGravityOnAllUnits(AllUnit);
        for ( Ai CurrentEnemy : enemy)
        {
            CurrentEnemy.behave(enemy);
        }
        if ((player.getLocation().getMaxY()>=400))//Условная проверка на достижение юнитов(игроком) некоторой поверхности
        {
            //Если игрок на   поверхности,то установка флага  нахождения на земле и зануление скорости по y.
            player.setOnEarth(true);
            player.SetSpeed(player.getSpeed().x, 0);
        }
        else player.setOnEarth(false);
        player.setInput(new ChechInput(gc).CheckPlayer());
        player.behave();


    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException
    {
        player.draw(graphics);

        for ( Ai CurrentEnemy : enemy)
        {
            CurrentEnemy.draw(graphics);
        }
        graphics.drawLine(-1000,400,1000,400);
    }




    public static void main(String[] args)
    {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Game("Simple Slick Game"));
            appgc.setDisplayMode(640, 480, false);
            appgc.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}