package Utils;

import java.util.HashMap;

import Unit.Sprites;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageLoader {
    private HashMap<Sprites, Image> ImagesMap = new HashMap();

    public ImageLoader() {
    }

    public void LoadImage(Sprites sprite, String PathFile) {
        Image newImage = null;

        try {
            newImage = new Image(PathFile);
        } catch (SlickException var5) {
            System.out.println("Не удалось загрузить изображение");
        }

        this.ImagesMap.put(sprite, newImage);
    }

    public HashMap<Sprites, Image> getImagesMap() {
        return this.ImagesMap;
    }

    public void setImagesMap(HashMap<Sprites, Image> imagesMap) {
        this.ImagesMap = imagesMap;
    }
}