package files;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Spritesheet {
	
	public static BufferedImage spritesheet;
	public static BufferedImage[] player_front;
	public static BufferedImage[] player_side_right;
	public static BufferedImage[] player_side_left;
	public static BufferedImage[] player_back;
	public static BufferedImage tile_wall;
	
	public Spritesheet() {
		try {
			spritesheet = ImageIO.read(getClass().getResource("/spritesheet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		player_front = new BufferedImage[2];
		player_side_right = new BufferedImage[2];
		player_side_left = new BufferedImage[2];
		player_back = new BufferedImage[2];
		
		player_front[0] = Spritesheet.getSprite(0, 11, 16, 16);
		player_front[1] = Spritesheet.getSprite(16, 11, 16, 16);
		
		player_side_right[0] = Spritesheet.getSprite(34, 11, 16, 16);
		player_side_right[1] = Spritesheet.getSprite(51, 11, 16, 16);
		
		player_side_left[0] = Spritesheet.getSprite(165, 11, 16, 16);
		player_side_left[1] = Spritesheet.getSprite(185, 11, 16, 16);
		
		player_back[0] = Spritesheet.getSprite(69, 11, 16, 16);
		player_back[1] = Spritesheet.getSprite(86, 11, 16, 16);
		
		tile_wall = Spritesheet.getSprite(285, 237, 16, 16);
	}
	
	public static BufferedImage getSprite(int x, int y, int width, int height) {
		return spritesheet.getSubimage(x, y, width, height);
	}
}