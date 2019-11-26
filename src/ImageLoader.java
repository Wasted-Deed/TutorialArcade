import java.util.HashMap;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageLoader
{
    private HashMap<Sprites,Image> ImagesMap;

    public  ImageLoader()
    {
        ImagesMap=new HashMap<>();
    }
    public  void LoadImage(Sprites sprite,String PathFile)
    {
        Image newImage= null;
        try {
            newImage = new Image(PathFile);
        } catch (SlickException e)
        {
            System.out.println("Не удалось загрузить изображение");
        }

        ImagesMap.put(sprite,newImage);
}
    public HashMap<Sprites, Image> getImagesMap()
    {
        return this.ImagesMap;
    }

    public void setImagesMap(final HashMap<Sprites, Image> imagesMap) {
        this.ImagesMap = imagesMap;
    }
}
