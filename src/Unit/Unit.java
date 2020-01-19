package Unit;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import java.util.HashMap;
import java.util.Map;

public abstract class Unit implements Drawable, Movable, Behave ,Collision{
    private Image ImageR;
    private Image ImageL;
    private Vector2f Speed = new Vector2f();
    private float mJumpSpeed;
    private int health;
    private boolean jump = false;
    private float mWalkSpeed;
    private Map<String, Vector2f> ListForce = new HashMap();
    private boolean OnEarth = false;
    private Rectangle Location;
    private int damage;
    private boolean StopX=false;
    public Image getImageL() {
        return this.ImageL;

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

    public void setImageL(Image imageL) {
        this.ImageL = imageL;
    }

    public boolean isOnEarth() {
        return this.OnEarth;
    }

    public void setOnEarth(boolean onEarth) {
        this.OnEarth = onEarth;
    }

    public Image getImageR() {
        return this.ImageR;
    }

    public void setImageR(Image imageR) {
        this.ImageR = imageR;
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

    public abstract void attack();

    public Unit(int health, float mWalkSpeed, Rectangle location, int damage) {
        this.health = health;
        this.mWalkSpeed = mWalkSpeed;
        this.Location = location;
        this.damage = damage;
    }

    public void setSpeed(Vector2f speed) {
        this.Speed = speed;
    }

    public void draw(Graphics g) {
        if (this.getImageR() == null) {
            g.drawRect(this.getLocation().getX(), this.getLocation().getY(), this.getLocation().getWidth(), this.getLocation().getHeight());
        } else {
            this.getImageR().drawCentered(this.getLocation().getCenterX(), this.getLocation().getCenterY());
        }

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
