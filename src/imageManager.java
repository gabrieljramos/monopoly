import java.util.*;
import javafx.scene.image.*;

public class imageManager {
    private static final String iconsPath = "../assets/images/icons/";

    private static Map<String, Image> images = new HashMap<>(); // Hash map com todas imagens

    // Precisa ser chamado no comeco do jogo
    public static void loadEssentialImages() {
        try {
            images.put("board", new Image("file:../assets/images/board.png"));
            images.put("player1", new Image("file:../assets/images/player1.png"));
            images.put("player2", new Image("file:../assets/images/player2.png"));
            images.put("player3", new Image("file:../assets/images/player3.png"));
            images.put("player4", new Image("file:../assets/images/player4.png"));
            
        } catch (Exception e) {
            System.err.println("Failed to load board/player");
        }
    }

    //Lazy load as imagens
    public static Image getImage(String key)
    {
        if(!images.containsKey(key)){
            try {
                images.put(key, loadImage(key));
            }
            catch (Exception e){
                System.err.println("Failed to load image: " + key);
                return null;
            }
        }
        return images.get(key);
    }

    //Carrega icone pelo nome
    private static Image loadImage(String key)
    {
        try {
            return new Image("file:"+ iconsPath + key + ".png");
        } catch (Exception e) {
            return null;
        }
    }
}
