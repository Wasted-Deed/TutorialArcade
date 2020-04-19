package SystemDialogue;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import java.util.HashMap;

public class CustomFont implements Font
{
    SpriteSheet spriteSheet;
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
    int heigth, width;
    final int  CountWordsInLine=13;
    final int  CountWordsInHeight=8;


    public CustomFont(Image font,int Wsize,int Hsize) {
        heigth =Wsize;
        this.width =Wsize;
        this.heigth =Hsize;
        this.FONT_COLLECTION = new HashMap<>();
        spriteSheet=new SpriteSheet(font,font.getWidth()/CountWordsInLine,font.getHeight()/CountWordsInHeight);
    }
    public void loadBasicFont()
    {
        int count=0;
        //:NOTE: Не хватает пробелов + в Java скобки в основном ставят так: func() {
        //                                                                  }
        for (int k=0;k<spriteSheet.getVerticalCount();k++) {
            if (count>=ALPHABET_ARRAY.length)
            {
                break;
            }
            for (int i = 0; i < spriteSheet.getHorizontalCount(); i++)
            {
                FONT_COLLECTION.put(ALPHABET_ARRAY[count], spriteSheet.getSprite(i, k).getScaledCopy((int) width, (int) Math.ceil(heigth)));
                if (ALPHABET_ARRAY[count].equals("0")||ALPHABET_ARRAY[count].equals("Я")||ALPHABET_ARRAY[count].equals("я"))
                {
                    count++;
                    break;
                }
                count++;
            }
        }
    int k=0;
        k++;
    }

    @Override
    public int getWidth(String s)
    {
        return (int) Math.ceil(s.length()* width);
    }

    @Override
    public int getHeight(String s)
    {
        return (int) (heigth);
    }

    @Override
    public int getLineHeight()
    {
        return (int) (heigth);
    }

    @Override
    public void drawString(float v, float v1, String s)
    {
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
