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
    ArrayList<Page> text;//Коллекция страниц
    Rectangle PlaceOutputText;//Позиция вывода текста
    Rectangle  Border;//Позиция рамки
    Integer MaxY;//Ограничение.Значнеие.эниже которого диалог не должен находится
    int MaxCountPage,NumberCurrentPage;
    boolean isVisible=false;
    ConditionChoice condition;
    HashMap<String,Button> Buttons;//Коллекция кнопок
    int DistancesFromTextToBorder=0;//Расстояние между рамкой и текстом

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
//Задать единый шрифт всем страницам
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
        //Если конечная страница - установить другие кнопки
        if ((NumberCurrentPage)>=(text.size()-1))
        {
            Buttons.get("NO").setVisible(true);
            Buttons.get("YES").setVisible(true);
            Buttons.get("NEXT").setVisible(false);
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
//Изменение положения по Y в зависимости от данных( количесство строк)
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
//Изменение положения по Y в зависимости от данных( длина строк)
public void updateWidth()
{
    Page CurrentPage=text.get(NumberCurrentPage);
    int Maxlength=0;
    for (int i=0;i<CurrentPage.getText().size();i++)
    {
       if (CurrentPage.GetWidthline(i)>Maxlength)
           Maxlength=CurrentPage.GetWidthline(i);
    }
    //Адаптация  ширины диалога
    System.out.println("Ширина строки"+Maxlength+"Ширина вывода");
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
    System.out.println("Разица между бордюром и выводом:"+(Border.getMaxX()-PlaceOutputText.getMaxX()));
    System.out.println("Бордюр:"+Border.getMaxX());
}
//Функция,которая обновляет положение всех элементов диалога ,используя updateHeight() и updateWidth()
    public void UpdatePosition()
    {
        updateHeight();
        updateWidth();
        //Обновление положения кнопок
        Iterator<Button> CurrentButton=Buttons.values().iterator();
            while (CurrentButton.hasNext())
            {
                Button button=CurrentButton.next();
                if(button.getName().equals("NEXT"))
                {
                    button.getLocation().setLocation(Border.getX(),PlaceOutputText.getMaxY());
                    button.getLocation().setWidth(Border.getWidth());
                }
                if(button.getName().equals("YES"))
                {
                    button.getLocation().setLocation(Border.getX(),PlaceOutputText.getMaxY());
                    button.getLocation().setWidth(Border.getWidth()/2);
                }
                if(button.getName().equals("NO"))
                {
                    button.getLocation().setLocation(Border.getX()+Border.getWidth()/2,PlaceOutputText.getMaxY());
                    button.getLocation().setWidth(Border.getWidth()/2);
                }


                if (button.getLocation().getHeight()==0)
                {
                    button.getLocation().setHeight(DistancesFromTextToBorder);

                }else
                    {
                        //Проверка на то,что кнопка лежит внутри рамки
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
        //Установка положения рамки
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

    public HashMap<String, Button> getButtons() {
        return this.Buttons;
    }

    public void setButtons(final HashMap<String, Button> buttons) {
        this.Buttons = buttons;
    }

    public void draw(Graphics g)
    {
      //  Page CurrentPage = text.get(NumberCurrentPage);
       // CurrentPage.getFontText().drawString(100, 100,"2");
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
