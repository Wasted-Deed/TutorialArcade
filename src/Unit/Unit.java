package Unit;


import Shell.Shell;
import Utils.ImageLoader;
import Utils.Sprites;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import Shell.ConditionShell;
import java.util.HashMap;
import java.util.Map;
import  Shell.TypeShell;
public abstract class Unit implements Drawable, Movable, Behave ,Collision{
    ConditionUnit condition;
    HashMap<ConditionUnit, Sprites>  IDimage=new HashMap<>();
    private Vector2f Speed = new Vector2f();
    private float mJumpSpeed;
    private TypeShell TypeBullet;
    private int NumberCommand;
    boolean RightOrLeftLook=false;
    private int health;
    private boolean jump = false;
    private float mWalkSpeed;
    private Map<String, Vector2f> ListForce = new HashMap();
    private boolean OnEarth = false;
    private Rectangle Location;
    private int damage;
    private boolean StopX=false;

    public void setTypeBullet(final TypeShell typeBullet)
    {
        this.TypeBullet = typeBullet;
    }
    public boolean isRightOrLeftLook() {
        return this.RightOrLeftLook;
    }
    public int getNumberCommand() {
        return this.NumberCommand;
    }

    public void setNumberCommand(final int numberCommand) {
        this.NumberCommand = numberCommand;
    }

    public TypeShell getTypeBullet() {
        return this.TypeBullet;
    }
    public ConditionUnit getCondition() {
        return this.condition;
    }

    public void setCondition(final ConditionUnit condition) {
        this.condition = condition;
    }

    public HashMap<ConditionUnit, Sprites> getIDimage() {
        return this.IDimage;
    }

    public void setIDimage(final HashMap<ConditionUnit, Sprites> IDimage) {
        this.IDimage = IDimage;
    }
    public Unit()
    {
        IDimage.put(ConditionUnit.MOVE_LEFT,Sprites.PLAYER_L);
        IDimage.put(ConditionUnit.MOVE_RIGHT,Sprites.PLAYER_R);
    }


 public boolean checkCollision(Shape shape)
 {
     return Location.intersects(shape);
 }
    public boolean isStopX() {
        return StopX;
    }
    public void setStopX(boolean stopX) {
        StopX = stopX;
    }
    public void behave() {
    }

    public boolean isOnEarth() {
        return this.OnEarth;
    }
    public void setOnEarth(boolean onEarth) {
        this.OnEarth = onEarth;
    }
    public void move() {
        this.Location.setLocation(this.Location.getX() + this.Speed.getX(), this.Location.getY() + this.Speed.getY());
    }
    public void move(float x, float y) {
        this.Location.setLocation(this.Location.getX() + x, this.Location.getY() + y);
    }
    public Map<String, Vector2f> getListForce() {
        return this.ListForce;
    }
    public void setListForce(Map<String, Vector2f> listForce) {
        this.ListForce = listForce;
    }
    public Shell attack(Point StartSpeed)
    {

        Shell newshell = new Shell();
        newshell.setSpeed(new Point(StartSpeed.getX(),StartSpeed.getY()));
        if (RightOrLeftLook == true)
        {
            newshell.setLocation(new Rectangle(this.getLocation().getMaxX(), this.getLocation().getY() + getLocation().getHeight() / 2, 10, 10));
            newshell.setCondition(ConditionShell.MOVE_RIGHT);
        } else
        {
            newshell.setLocation(new Rectangle(this.getLocation().getX(), this.getLocation().getY() + getLocation().getHeight() / 2, 10, 10));
            newshell.setCondition(ConditionShell.MOVE_LEFT);
        }
        newshell.setDamage(1);
        newshell.setNumberCommand(getNumberCommand());
        return newshell;

    }

    public Unit(int health, float mWalkSpeed, Rectangle location, int damage)
    {
        this();
        this.health = health;
        this.mWalkSpeed = mWalkSpeed;
        this.Location = location;
        this.damage = damage;
    }
    public void setSpeed(Vector2f speed) {
        this.Speed = speed;
    }
    public void draw(ImageLoader loader)
    {

        loader.getImagesMap().get(IDimage.get(condition)).draw(this.getLocation().getX(), this.getLocation().getY(), this.getLocation().getWidth(), this.getLocation().getHeight());


    }
    public int getHealth() {
        return this.health;
    }
    public float getmJumpSpeed() {
        return this.mJumpSpeed;
    }
    public void setmJumpSpeed(float mJumpSpeed) {
        this.mJumpSpeed = mJumpSpeed ;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public float getmWalkSpeed() {
        return this.mWalkSpeed;
    }
    public void setmWalkSpeed(float mWalkSpeed) {
        this.mWalkSpeed = mWalkSpeed/10.0F;
    }
    public Rectangle getLocation() {
        return this.Location;
    }
    public Vector2f getSpeed() {
        return this.Speed;
    }
    public void SetSpeed(float x, float y) {
        this.Speed.x = x;
        this.Speed.y = y;
    }
    public void addSpeed(float x, float y)
    {
        Vector2f var10000 = this.Speed;
        var10000.x += x;
        var10000 = this.Speed;
        var10000.y += y;
    }
    public void setLocation(Rectangle location) {
        this.Location = location;
    }
    public boolean isJump() {
        return this.jump;
    }
    public void setJump(boolean jump) {
        this.jump = jump;
    }
    public int getDamage() {
        return this.damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
}
