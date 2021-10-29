package ui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class App {
    public static BufferedImage getImg(String path) throws IOException {
        try {
            BufferedImage img = ImageIO.read(App.class.getResource(path));
            return img ;
        } catch (IOException e) {
            e.printStackTrace();
            return null ;
        }

    }
}