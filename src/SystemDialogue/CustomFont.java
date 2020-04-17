package SystemDialogue;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.opengl.ImageData;

import java.awt.*;
import java.util.HashMap;

public class CustomFont implements Font
{

    private final  HashMap<String, Image> FONT_COLLECTION;
    //:NOTE: UTF-8
    private final String[] ALPHABET_ARRAY =
            {" ","1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
            ".",",", "!", "?", "(", ")", "'", " \" ", "/", "|", "\\", ":",";",
            "À", "Á", "Â", "Ã", "Ä", "Å", "¨", "Æ", "Ç", "È", "É", "Ê", "Ë",
            "Ì", "Í", "Î", "Ï", "Ð", "Ñ", "Ò", "Ó", "Ô", "Õ", "Ö", "×", "Ø",
            "Ù", "Ú", "Û", "Ü", "Ý", "Þ", "ß",
            "à", "á", "â", "ã", "ä", "å", "¸", "æ", "ç", "è", "é", "ê", "ë",
            "ì", "í", "î", "ï", "ð", "ñ", "ò", "ó", "ô", "õ", "ö", "÷", "ø",
            "ù", "ú", "û", "ü", "ý", "þ", "ÿ"};
    private final HashMap<String, Integer> ALPHABET;
    SpriteSheet spriteSheet;
    //:NOTE: Переменные должны быть на разных строках (codestyle) + переменные в Java - с маленькой буквы (camelCase)
    int Heigth,Width;

    public CustomFont(Image font,int Wsize,int Hsize)
    {
        Heigth=Wsize;
        this.Width=Wsize;
        this.Heigth=Hsize;
        this.FONT_COLLECTION = new HashMap<>();
        ALPHABET = new HashMap<>();
        //:NOTE: Лучше сделать константы для всех подгонов
        spriteSheet=new SpriteSheet(font,font.getWidth()/13,font.getHeight()/8);
    }
    public void loadBasicFont()
    {
        int count=0;

        System.out.println("ãîðèçîíòàë="+spriteSheet.getVerticalCount()+" âåðò="+spriteSheet.getHorizontalCount());
        //:NOTE: Не хватает пробелов + в Java скобки в основном ставят так: func() {
        //                                                                  }
        for (int k=0;k<spriteSheet.getVerticalCount();k++)
        {
            if (count>=ALPHABET_ARRAY.length)
            {
                break;
            }
            for (int i = 0; i < spriteSheet.getHorizontalCount(); i++)
            {


                FONT_COLLECTION.put(ALPHABET_ARRAY[count], spriteSheet.getSprite(i, k).getScaledCopy((int)Width, (int) Math.ceil(Heigth)));
                if (ALPHABET_ARRAY[count].equals("ß")||ALPHABET_ARRAY[count].equals("ÿ")||ALPHABET_ARRAY[count].equals("0"))
                {
                    count++;
                    break;
                }
                count++;
            }
        }

    }

    @Override
    public int getWidth(String s)
    {
        return (int) Math.ceil(s.length()*Width);
    }

    @Override
    public int getHeight(String s)
    {
        return (int) (Heigth);
    }

    @Override
    public int getLineHeight()
    {
        return (int) (Heigth);
    }

    @Override
    public void drawString(float v, float v1, String s)
    {
        float starts=v;
        for (int i = 0; i < s.length(); i++)
        {
        String unit = String.valueOf(s.charAt(i));
        Image toDraw = FONT_COLLECTION.get(unit);
        if (toDraw == null) {
            continue;
        }
         toDraw.draw(v,v1);
            v+=toDraw.getWidth();
        }
    }

    @Override
    public void drawString(float v, float v1, String s, Color color)
    {
        drawString( v,  v1,  s);
    }

    @Override
    public void drawString(float v, float v1, String s, Color color, int i, int i1) {
        drawString( v,  v1,  s);
    }
}
