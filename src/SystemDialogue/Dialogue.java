package SystemDialogue;

import org.lwjgl.Sys;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Dialogue
{
    //:NOTE: UTF-8; Переменные на разных строках; По убыванию размеров классов-> затем по убыванию размеров типов.
    ArrayList<Page> text;
    Rectangle PlaceOutputText;
    Rectangle  Border;
    ConditionChoice condition;
    HashMap<ButtonName,Button> Buttons;
    Integer MaxY;
    int MaxCountPage;
    int NumberCurrentPage;
    boolean isVisible;
    int DistancesFromTextToBorder;

    public int getDistancesFromTextToBorder() {
        return this.DistancesFromTextToBorder;
    }


    public void setDistancesFromTextToBorder(final int distancesFromTextToBorder) {
        this.DistancesFromTextToBorder = distancesFromTextToBorder;
    }

    public ConditionChoice getCondition() {
        return this.condition;
    }

    public void setCondition(final ConditionChoice condition) {
        this.condition = condition;
    }

    public Dialogue()
    {
        text=new ArrayList<>();
        PlaceOutputText=new Rectangle(0,0,0,0);
        Border=new Rectangle(0,0,0,0);
        condition=ConditionChoice.NO_CHOICE;
        Buttons=new HashMap<>();
    }
    public boolean isVisible() {
        return this.isVisible;
    }

    public void setVisible(final boolean visible)
    {

        this.isVisible = visible;
    }

    public void SetFontAllPage(Font FontPages)
    {
        Iterator<Page> Pages=text.iterator();
        while (Pages.hasNext())
        {
            Pages.next().setFontText(FontPages);

        }
    }
    public  void addPage(Page newPage)
    {
        text.add(newPage);

    }
    public void addButton(Button newButton)
    {
        Buttons.put(newButton.getName(),newButton);

    }
    public int getNumberCurrentPage() {
        return this.NumberCurrentPage;
    }
    public  void  checkClickOnButton(Point PosMouse)
    {
        if (PosMouse!=null)
        {
            Iterator<Button> CurrentButton=Buttons.values().iterator();
            while (CurrentButton.hasNext())
            {
                Button button=CurrentButton.next();
                if ((button.getLocation().contains((float) PosMouse.getX(), (float) PosMouse.getY())&&
                        (button.isVisible())))
                {
                    condition = button.getNameAction();
                }
            }
        }


    }
    public  void udpate()
    {

        switch (condition)
        {
            case NEXT:
                setNumberCurrentpage(NumberCurrentPage+1);
                condition=ConditionChoice.NO_CHOICE;

                break;
        }
        //Åñëè êîíå÷íàÿ ñòðàíèöà - óñòàíîâèòü äðóãèå êíîïêè
        if ((NumberCurrentPage)>=(text.size()-1))
        {
            Buttons.get(ButtonName.NO).setVisible(true);
            Buttons.get(ButtonName.YES).setVisible(true);
            Buttons.get(ButtonName.NEXT).setVisible(false);
        }
    }
    public int getMaxY() {
        return this.MaxY;
    }
    public void setMaxY(final Integer maxY) {
        this.MaxY = maxY;
    }
public  void CheckBellowMaxY()
{
    if((MaxY!=null))
    {
        if ((Border.getMaxY()>MaxY)) {
            int difference = (int) (Border.getMaxY() - MaxY);
            PlaceOutputText.setY((PlaceOutputText.getY() - difference));
            Iterator<Button> CurrentButton=Buttons.values().iterator();
            while (CurrentButton.hasNext())
            {
                Button button=CurrentButton.next();
                button.getLocation().setY(button.getLocation().getY()-difference);
            }
            Border.setY(Border.getY() - difference);
            difference = (int) (Border.getMaxY() - MaxY);
            System.out.println(difference);
        }
    }
}

public void updateHeight()
{
    Page CurrentPage=text.get(NumberCurrentPage);
    int difference= (int) (CurrentPage.GetHeightPage()-PlaceOutputText.getHeight());
    if(difference>0)
    {
        PlaceOutputText.setHeight(PlaceOutputText.getHeight()+difference);
        PlaceOutputText.setY(PlaceOutputText.getY()-difference);
        Border.setY(Border.getY() - difference);
        Border.setHeight(Border.getHeight()+difference);
    }else
    {
        PlaceOutputText.setHeight(PlaceOutputText.getHeight()-Math.abs(difference));
        PlaceOutputText.setY(PlaceOutputText.getY()+Math.abs(difference));
        Border.setHeight(Border.getHeight()-Math.abs(difference));
        Border.setY(Border.getY()+Math.abs(difference));
    }
}

public void updateWidth()
{
    Page CurrentPage=text.get(NumberCurrentPage);
    int Maxlength=0;
    for (int i=0;i<CurrentPage.getText().size();i++)
    {
       if (CurrentPage.GetWidthline(i)>Maxlength)
           Maxlength=CurrentPage.GetWidthline(i);
    }

    int difference= (int) ((Maxlength-PlaceOutputText.getWidth())/2);
    if (difference>=0)
    {
        PlaceOutputText.setWidth(2*difference+PlaceOutputText.getWidth());
        PlaceOutputText.setX(PlaceOutputText.getX()-difference);
        Border.setWidth(2*difference+Border.getWidth());
        Border.setX(Border.getX()-difference);
    }else
    {
        difference=Math.abs(difference);
        PlaceOutputText.setWidth(PlaceOutputText.getWidth()-2*difference);
        PlaceOutputText.setX(PlaceOutputText.getX()+difference);
        Border.setWidth(Border.getWidth()-2*difference);
        Border.setX(Border.getX()+difference);
    }

}
    public void UpdatePosition()
    {
        updateHeight();
        updateWidth();
             Iterator<Button> CurrentButton=Buttons.values().iterator();
            while (CurrentButton.hasNext())
            {
                Button button=CurrentButton.next();
                switch (button.getName())
                {
                    case NEXT:
                        button.getLocation().setLocation(Border.getX(),PlaceOutputText.getMaxY());
                        button.getLocation().setWidth(Border.getWidth());
                        break;
                    case YES:
                        button.getLocation().setLocation(Border.getX(),PlaceOutputText.getMaxY());
                        button.getLocation().setWidth(Border.getWidth()/2);
                        break;
                    case NO:
                        button.getLocation().setLocation(Border.getX()+Border.getWidth()/2,PlaceOutputText.getMaxY());
                        button.getLocation().setWidth(Border.getWidth()/2);
                        break;
                }
                if (button.getLocation().getHeight()==0)
                {
                    button.getLocation().setHeight(DistancesFromTextToBorder);

                }else
                    {
                     int difference = (int) (button.getLocation().getMaxY()-Border.getMaxY());
                    if (difference>=0)
                    {
                        PlaceOutputText.setY((PlaceOutputText.getY() -  difference));
                        button.getLocation().setY(button.getLocation().getY()-difference);
                        Border.setY(PlaceOutputText.getY()-DistancesFromTextToBorder);
                        Border.setHeight(  button.getLocation().getMaxY()-Border.getY());
                    }
                }
            }
        CheckBellowMaxY();
    }
    public void setNumberCurrentpage(final int numberCurrentpage)
    {
        this.NumberCurrentPage = numberCurrentpage;
        UpdatePosition();
    }

    public Rectangle getPlaceOutputText() {
        return this.PlaceOutputText;
    }

    public void setPlaceOutputText(final Rectangle placeOutputText)
    {
        this.PlaceOutputText = placeOutputText;
        //Óñòàíîâêà ïîëîæåíèÿ ðàìêè
        if (DistancesFromTextToBorder!=0)
        {
            Border.setX(PlaceOutputText.getX()-DistancesFromTextToBorder);
            Border.setY(PlaceOutputText.getY()-DistancesFromTextToBorder);
            Border.setHeight(PlaceOutputText.getHeight()+2*DistancesFromTextToBorder);
            Border.setWidth(PlaceOutputText.getWidth()+2*DistancesFromTextToBorder);
            System.out.println("00-MaxYBorder="+Border.getMaxY()+"MaxYText="+PlaceOutputText.getMaxY()+" MinYBorder="+Border.getY()+" MinYPlace="+PlaceOutputText.getY());
        }
        UpdatePosition();
    }

    public Rectangle getBorder() {
        return this.Border;
    }

    public void setBorder(final Rectangle border) {
        this.Border = border;
    }

    public int getMaxCountPage() {
        return this.MaxCountPage;
    }

    public void setMaxCountPage(final int maxCountPage) {
        this.MaxCountPage = maxCountPage;
    }

    public ArrayList<Page> getText() {
        return this.text;
    }

    public HashMap<ButtonName, Button> getButtons() {
        return this.Buttons;
    }

    public void setButtons(final HashMap<ButtonName, Button> buttons) {
        this.Buttons = buttons;
    }

    public void draw(Graphics g)
    {
        if (isVisible)
        {
            Page CurrentPage = text.get(NumberCurrentPage);
            int XPos = (int) PlaceOutputText.getX(), YPos = (int) PlaceOutputText.getY();
            g.setFont(CurrentPage.getFontText());
            Iterator<String> Line = CurrentPage.getText().iterator();
            while (Line.hasNext())
            {
                String text=Line.next();
                CurrentPage.getFontText().drawString(XPos, YPos,text);
                YPos += CurrentPage.getFontText().getLineHeight();
            }
            g.drawRect(Border.getX(),Border.getY(),Border.getWidth(),Border.getHeight());
            Iterator<Button> CurrentButton=Buttons.values().iterator();
            while (CurrentButton.hasNext())
            {
                Button button=CurrentButton.next();
                if (button.isVisible())
                {
                    button.draw(g);
                }
            }

        }
    }

}
