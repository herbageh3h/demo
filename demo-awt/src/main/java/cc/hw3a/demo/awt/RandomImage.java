package cc.hw3a.demo.awt;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class RandomImage {
	public static void main(String ... args) throws Exception {
		BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();

		for (int x = 0; x < 400; x++) {
			for (int y = 0; y < 400; y++) {
				int alpha = (int)(Math.random() * 256);
				int red = (int)(Math.random() * 256);
				int green = (int)(Math.random() * 256);
				int blue = (int)(Math.random() * 256);

                int pixel = (alpha<<24) | (red<<16) | (green<<8) | blue;
                image.setRGB(x, y, pixel);
			}
		}

		ImageIO.write(image, "png", new File("/Users/huanghao/tmp/aa.png"));

	}
}
