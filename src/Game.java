import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import java.util.logging.Level;
import java.util.logging.Logger;
//Сделал этот класс для тестирования
public class Game extends BasicGame
{
     private Player player;
    public Game(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException
    {
        player=new Player(3,5,new Rectangle(30,50,20,20),2);
        player.setmJumpSpeed(2);
        player.setJump(false);
        player.getListForce().put("Gravity",new Vector2f(0, (float) 10));
    }


    @Override
    public void update(GameContainer gc, int i) throws SlickException
    {

        if ((player.getLocation().getMaxY()>=400))//Условная проверка на достижение юнитов(игроком) некоторой поверхности
        {
            //Если игрок на   поверхности,то установка флага  нахождения на земле и зануление скорости по y.
                    player.setOnEarth(true);
                    player.setSpeed(new Vector2f(player.getSpeed().x, 0));
        }
        else player.setOnEarth(false);
        player.update( gc,i);

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException
    {
        player.render(gameContainer,graphics);
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