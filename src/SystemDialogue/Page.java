package SystemDialogue;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.newdawn.slick.Font;

public class Page
{
    ArrayList<String> text = new ArrayList<>();
    boolean   isSaveFormatting=true;
    Font FontText;
    public void addLine(String newText)
    {
        /*ArrayList<String> Line=new ArrayList<>();;
        newText.replace("   "," ").replace("  "," ");
        Collections.addAll(Line,newText.split(" "));*/
        text.add(newText);
    }
   public  int GetWidthline(int NumberLine)
   {

      return FontText.getWidth(text.get(NumberLine));
   }
   public  int GetHeightPage()
   {

         return  FontText.getLineHeight()*text.size();


   }
    public Font getFontText() {
        return this.FontText;
    }

    public void setFontText(final Font fontText) {
        this.FontText = fontText;
    }

    public ArrayList<String> getText() {
        return this.text;
    }

    public void setText(final ArrayList<String> text) {
        this.text = text;
    }

    public boolean isSaveFormatting() {
        return this.isSaveFormatting;
    }

    public void setSaveFormatting(final boolean saveFormatting) {
        this.isSaveFormatting = saveFormatting;
    }

}
