package utils;

import play.Environment;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtils {
	private Environment environment;

	public ImageUtils(Environment environment) {
		this.environment = environment;
	}

	public InputStream formatMailImage(String msg) throws IOException {
		final BufferedImage image = ImageIO.read(environment.getFile("/public/images/mail.jpg"));
		Graphics g = image.getGraphics();
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		int y = 60;
		int z = 1;
		for (String part : msg.split("\n")) {
			if (z == 3)
				g2.setFont(new Font("Courier New", Font.BOLD, 14));
			else
				g2.setFont(new Font("Courier New", Font.PLAIN, 14));
			g2.drawString(part.trim(), 160, y);
			y += 20;
			z++;
		}
		g.dispose();
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		ImageIO.write(image, "png", buffer);
		InputStream is = new ByteArrayInputStream(buffer.toByteArray());
		return is;
	}
}
