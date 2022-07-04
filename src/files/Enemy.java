package files;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Enemy extends Rectangle{
	
	public int spd = 6;
	public boolean right, up, down, left;
	public boolean shoot = false;
	
	public int curAnimation = 0;
	public int curFrames = 0, targetFrames = 15;
	
	public int dir = 1;
	
	public static List<Bullet> bullets = new ArrayList<Bullet>();
	
	public Enemy(int x, int y) {
		super(x, y, 32, 32);
	}
	
	public void tick() {
		boolean moved = false;
		if(right && World.IsFree(x + spd, y)) {
			x += spd;
			moved = true;
			dir = 1;
		}else if(left && World.IsFree(x - spd, y)) {
			x -= spd;
			moved = true;
			dir = -1;
		}
		
		if(up && World.IsFree(x, y - spd)) {
			y -= spd;
			moved = true;
		}else if(down && World.IsFree(x, y + spd)){
			y += spd;
			moved = true;
		}
		
		if(moved) {
			curFrames++;
			if(curFrames == targetFrames) {
				curFrames = 0;
				curAnimation++;
				if(curAnimation == Spritesheet.player_front.length && 
						curAnimation == Spritesheet.player_back.length &&
							curAnimation == Spritesheet.player_side_right.length &&
								curAnimation == Spritesheet.player_side_left.length) {
					curAnimation = 0;
				}
			}
		}
		
		if(shoot) {
			shoot = false;
			bullets.add(new Bullet(x, y, dir));
		}
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).tick();
		}
	}
		

	
	public void render(Graphics g) {
		//g.setColor(Color.blue);
		//g.fillRect(x, y, width, height);
		if(down) {
			g.drawImage(Spritesheet.player_front[curAnimation], x, y, 32, 32, null);
		}else if(up) {
			g.drawImage(Spritesheet.player_back[curAnimation], x, y, 32, 32, null);
		}else if(right){
			g.drawImage(Spritesheet.player_side_right[curAnimation], x, y, 32, 32, null);
		}else if (left) {
			g.drawImage(Spritesheet.player_side_left[curAnimation], x, y, 32, 32, null);
		}else {
			g.drawImage(Spritesheet.player_front[curAnimation], x, y, 32, 32, null);
		}
		
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
	}
}