package files;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Rectangle{
	
	public int spd = 3;
	public int right = 1, up = 0, down = 0, left = 0;
	public boolean shoot = false;
	
	public int curAnimation = 0;
	public int curFrames = 0, targetFrames = 15;
	
	public int dir = 1;
	
	public static List<Bullet> bullets = new ArrayList<Bullet>();
	
	public Enemy(int x, int y) {
		super(x, y, 32, 32);
	}
	
	public void tick() {
		boolean moved = true;
		
		chasePlayer();
		
		if(moved) {
			curFrames++;
			if(curFrames == targetFrames) {
				curFrames = 0;
				curAnimation++;
				if(curAnimation == Spritesheet.enemy_front.length /*&& 
						curAnimation == Spritesheet.player_back.length &&
							curAnimation == Spritesheet.player_side_right.length &&
								curAnimation == Spritesheet.player_side_left.length*/) {
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
		/*
		 * if(down) { g.drawImage(Spritesheet.player_front[curAnimation], x, y, 32, 32,
		 * null); }else if(up) { g.drawImage(Spritesheet.player_back[curAnimation], x,
		 * y, 32, 32, null); }else if(right){
		 * g.drawImage(Spritesheet.player_side_right[curAnimation], x, y, 32, 32, null);
		 * }else if (left) { g.drawImage(Spritesheet.player_side_left[curAnimation], x,
		 * y, 32, 32, null); }else { g.drawImage(Spritesheet.player_front[curAnimation],
		 * x, y, 32, 32, null); }
		 */
		g.drawImage(Spritesheet.enemy_front[curAnimation], x, y, 32, 32, null);
		
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
	}
	
	public void chasePlayer() {
		Player p = Game.player;
		
		if(x < p.x && World.IsFree(x + spd, y)) {
			if(new Random().nextInt(100) < 50) {
				x += spd;
			}
		}else if(x > p.x && World.IsFree(x - spd, y)) {
			if(new Random().nextInt(100) < 50) {
				x -= spd;
			}
		}
		
		if(y < p.y && World.IsFree(x, y + spd)) {
			if(new Random().nextInt(100) < 50) {
				y += spd;
			}
		}else if(y > p.y && World.IsFree(x, y - spd)) {
			if(new Random().nextInt(100) < 50) {
				y -= spd;
			}
		}
	}
}