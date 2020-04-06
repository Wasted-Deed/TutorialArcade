package SystemDialogue;

import org.newdawn.slick.*;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import java.awt.*;

public class Dialogue
{
    private Rectangle LocationText=new Rectangle(0,0,0,0);
    private Rectangle Border=new Rectangle(0,0,0,0);


    private String Text;
    private int CountLineInPage =0;
    private int CountCharInLine=0;
    private Rectangle ButtonYes=new Rectangle(0,0,0,0);
    private Rectangle ButtonNo=new Rectangle(0,0,0,0);
    private  Rectangle ButtonNextPage=new Rectangle(0,0,0,0);
    private int MaxCountLineInPage;
    private int CountPage;
    private int  CurrentPage=0;
    private Font   FontText;
    private ConditionChoice condition=ConditionChoice.NO_CHOICE;
    private boolean visible=false;
    public Dialogue(Rectangle location, String text, Font fontText, ConditionChoice condition) {
        this.LocationText = location;
        this.Text = text;
        this.FontText = fontText;
        this.condition = condition;
    }
    public int getMaxCountLineInPage() {
        return this.MaxCountLineInPage;
    }

    public void setMaxCountLineInPage(final int maxCountLineInPage) {
        this.MaxCountLineInPage = maxCountLineInPage;
    }
    public int getCountLineInPage() {
        return this.CountLineInPage;
    }

    public void setCountLineInPage(int countLineInPage) {
        this.CountLineInPage = countLineInPage;
    }

    public int getCountCharInLine() {
        return this.CountCharInLine;
    }

    public void setCountCharInLine(int countCharInLine) {
        this.CountCharInLine = countCharInLine;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public Dialogue()
    {
    }

    public Font getFontText() {
        return this.FontText;
    }

    public void setFontText(Font fontText) {
        this.FontText = fontText;
    }

    public Rectangle getLocationText() {
        return this.LocationText;
    }

    public void setLocation(float x1,float y1, float width)
    {
        LocationText.setX(x1);
        LocationText.setWidth(width);
        OutputText();
        LocationText.setY(y1-50);
        Border=new Rectangle(LocationText.getX()-10,LocationText.getY(),LocationText.getWidth()+10,LocationText.getHeight()+30);
    }
    public String getText() {
        return this.Text;
    }

    public void OutputText()
    {
        int Width=FontText.getWidth(Text.substring(0,1));
        if (CountCharInLine==0)
            CountCharInLine=(int)Math.ceil(LocationText.getWidth()/Width);

    CountLineInPage = (int) Math.ceil((double)Text.length()/(CountCharInLine));
    if (CountLineInPage >MaxCountLineInPage)
        CountLineInPage =MaxCountLineInPage;
        CountPage= Text.length() /(CountLineInPage*CountCharInLine);
        getLocationText().setHeight(FontText.getLineHeight()* CountLineInPage);

    }
    public void setText(String text)
    {
        this.Text = text;

    }
    public void update()
    {
        if (CurrentPage<(CountPage))
        {
            ButtonNextPage=new Rectangle(Border.getX(), LocationText.getMaxY(), Border.getWidth(), Border.getHeight()-LocationText.getHeight());
        }else
        {
            ButtonNextPage=new Rectangle(0, 0, 0, 0);
            ButtonYes=new Rectangle(Border.getX()+2, LocationText.getMaxY(),Border.getWidth()/2-2,Border.getHeight()-LocationText.getHeight());
            ButtonNo=new Rectangle(Border.getX()+2+Border.getWidth()/2+2, LocationText.getMaxY(),Border.getWidth()/2-2,Border.getHeight()-LocationText.getHeight());

        }
    }
    public ConditionChoice getCondition() {
        return this.condition;
    }

    public void setCondition(ConditionChoice condition) {
        this.condition = condition;
    }
    public  void  checkClickOnButton(Point PosMouse)
    {
        if (PosMouse!=null) {
            if (ButtonNextPage.contains((float) PosMouse.getX(), (float) PosMouse.getY())) {
                condition = ConditionChoice.NEXT;
                CurrentPage += 1;
            } else if (ButtonNo.contains((float) PosMouse.getX(), (float) PosMouse.getY()))
                condition = ConditionChoice.NO;
            else if (ButtonYes.contains((float) PosMouse.getX(), (float) PosMouse.getY()))
                condition = ConditionChoice.YES;
        }
        System.out.println(condition.toString());

    }
    public void draw(Graphics g)
    {
        if (visible)
        {
            g.setFont(FontText);
            g.drawRect(Border.getX(), Border.getY(), Border.getWidth(), Border.getHeight());
            String texts=Text;
                for (int i = 0; i < CountLineInPage; i++)
                {


                    int p1 = i * CountCharInLine;
                    int p2 = i * CountCharInLine + CountCharInLine;

                    int PageFirst=CurrentPage*(CountLineInPage)*CountCharInLine;
                    int PageEnd=(CurrentPage+1)*(CountLineInPage)*CountCharInLine;
                    int length=Text.length();
                    if (length<(PageFirst))
                        break;
                    if (PageEnd>texts.length())
                        PageEnd=texts.length()-1;
                    if (p2 > PageEnd-PageFirst) {
                        p2 = PageEnd-PageFirst;
                    }
                    System.out.println("Индекс:" + i + " Число символов в строке:" + CountCharInLine);
                    System.out.println("p1:" + p1 + "  p2:" + p2 + "Последний индекс строки:" + (texts.length()));
                    System.out.println("PageFirst:"+PageFirst+" PageEnd:"+PageEnd );
                    String CurrentText = texts.substring(PageFirst,PageEnd)
                             .substring(p1, p2);

                    g.drawString(CurrentText.trim(), LocationText.getX(), LocationText.getY() + i * FontText.getLineHeight());

                }

                if (CurrentPage< (CountPage))
                g.drawString("Next", ButtonNextPage.getX(),
                        ButtonNextPage.getY());
                else
                {
                    g.drawString("YES", ButtonYes.getX(),
                            ButtonYes.getY());
                    g.drawString("NO", ButtonNo.getX(),
                            ButtonNo.getY());
                }


        }
    }
}
