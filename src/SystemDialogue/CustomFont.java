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
    private final String[] ALPHABET_ARRAY =
            {" ","1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
            ".",",", "!", "?", "(", ")", "'", " \" ", "/", "|", "\\", ":",";",
            "А", "Б", "В", "Г", "Д", "Е", "Ё", "Ж", "З", "И", "Й", "К", "Л",
            "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш",
            "Щ", "Ъ", "Ы", "Ь", "Э", "Ю", "Я",
            "а", "б", "в", "г", "д", "е", "ё", "ж", "з", "и", "й", "к", "л",
            "м", "н", "о", "п", "р", "с", "т", "у", "ф", "х", "ц", "ч", "ш",
            "щ", "ъ", "ы", "ь", "э", "ю", "я"};
    private final HashMap<String, Integer> ALPHABET;
    SpriteSheet spriteSheet;
    int Heigth,Width;

    public CustomFont(Image font,int Wsize,int Hsize)
    {
        Heigth=Wsize;
        this.Width=Wsize;
        this.Heigth=Hsize;
        this.FONT_COLLECTION = new HashMap<>();
        ALPHABET = new HashMap<>();
        spriteSheet=new SpriteSheet(font,font.getWidth()/13,font.getHeight()/8);
    }
    public void loadBasicFont()
    {
        int count=0;

        System.out.println("горизонтал="+spriteSheet.getVerticalCount()+" верт="+spriteSheet.getHorizontalCount());
        for (int k=0;k<spriteSheet.getVerticalCount();k++)
        {
            if (count>=ALPHABET_ARRAY.length)
            {
                break;
            }
            for (int i = 0; i < spriteSheet.getHorizontalCount(); i++)
            {


                FONT_COLLECTION.put(ALPHABET_ARRAY[count], spriteSheet.getSprite(i, k).getScaledCopy((int)Width, (int) Math.ceil(Heigth)));
                if (ALPHABET_ARRAY[count].equals("Я")||ALPHABET_ARRAY[count].equals("я")||ALPHABET_ARRAY[count].equals("0"))
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
