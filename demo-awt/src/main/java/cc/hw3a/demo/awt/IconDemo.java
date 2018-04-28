package cc.hw3a.demo.awt;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class IconDemo extends JPanel {
    @Override
	public void paintComponent(Graphics g) {
        System.out.println("start painting");

        super.paintComponent(g);
		try {
			BufferedImage image = ImageIO.read(IconDemo.class.getResourceAsStream("aa.png"));
			g.drawImage(image, 0, 0, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

    public static void main(String... args) {
        JFrame f = new JFrame("Canvas Demo");
        f.setContentPane(new IconDemo());
        f.getContentPane().setLayout(null);
        f.setSize(400, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setVisible(true);
    }
}
