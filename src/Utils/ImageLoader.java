package Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.*;

public class ImageLoader {
    private HashMap<Sprites, Animation > ImagesMap = new HashMap();

    public ImageLoader() {
    }

    public void LoadImage(Sprites sprite, String PathFile,int tw,int th) {
        Image newImage = null;

        try {
            newImage = new Image(PathFile);
        } catch (SlickException var5) {
            System.out.println("Не удалось загрузить изображение");
        }
        Animation anim=new Animation();

        this.ImagesMap.put(sprite, new Animation());
       if (tw==0) tw=newImage.getWidth();
       if (th==0) th=newImage.getHeight();
        SpriteSheet spriteSheet=new SpriteSheet(newImage,tw,th);

        System.out.println(spriteSheet.getVerticalCount()+"Горизонт="+spriteSheet.getHorizontalCount());
        for (int i=0;i<spriteSheet.getHorizontalCount();i++)
        {
            for (int k=0;k<spriteSheet.getVerticalCount();k++)
            {
                System.out.println("i="+i+"k="+k);
                ImagesMap.get(sprite).addFrame(spriteSheet.getSprite(i,k),100);
            }
        }

    }

    public HashMap<Sprites,Animation> getImagesMap() {
        return this.ImagesMap;
    }

    public void setImagesMap(HashMap<Sprites, Animation> imagesMap) {
        this.ImagesMap = imagesMap;
    }
}