package SystemDialogue;


import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Button
{
    Rectangle Location;
    Font FontName;
    String Name;
    ConditionChoice NameAction;
     boolean isVisible=false;
    public Rectangle getLocation() {
        return this.Location;
    }

    public Button( final String name, final ConditionChoice nameAction)
    {
        Location=new Rectangle(0,0,0,0);
        this.Name = name;
        this.NameAction = nameAction;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public void setVisible(final boolean visible) {
        this.isVisible = visible;
    }

    public void setLocation(final Rectangle location) {
        this.Location = location;
    }

    public ConditionChoice getNameAction() {
        return this.NameAction;
    }

    public Font getFontName() {
        return this.FontName;
    }

    public void setFontName(final Font fontName) {
        this.FontName = fontName;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(final String name)
    {
        this.Name = name;
    }
    public void draw(Graphics g)
    {

        g.drawRect(Location.getX(),Location.getY(),Location.getWidth(),Location.getHeight());
        g.setFont(FontName);
        g.drawString(Name,Location.getX(),Location.getY());
    }
}
