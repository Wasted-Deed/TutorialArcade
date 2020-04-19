package SystemDialogue;


import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Button
{
    Rectangle Location;
    Font FontName;
    ButtonName Name;
    ConditionChoice NameAction;
     boolean isVisible=false;
    public Rectangle getLocation() {
        return this.Location;
    }

    public Button( final ButtonName name, final ConditionChoice nameAction)
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

    public ButtonName getName() {
        return this.Name;
    }

    public void setName(final ButtonName name)
    {
        this.Name = name;
    }
    public void draw(Graphics g)
    {

        g.drawRect(Location.getX(),Location.getY(),Location.getWidth(),Location.getHeight());
        g.setFont(FontName);
        g.drawString(String.valueOf(Name),Location.getX(),Location.getY());
    }
}
