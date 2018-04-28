package cc.hw3a.demo.awt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageDemo {
    public static void main(String... args) throws Exception {
        BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        g.setColor(Color.RED);
        g.fillRect(100, 100, 50, 50);

        ImageIO.write(image, "png", new File("/Users/huanghao/tmp/aa.png"));
    }
}
