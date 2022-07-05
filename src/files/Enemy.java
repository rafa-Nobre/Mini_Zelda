package files;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Rectangle{
	
	public int spd = 3;
	public boolean rightDir, leftDir, upDir, downDir;
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
				if(curAnimation == Spritesheet.enemy_front.length && 
						curAnimation == Spritesheet.enemy_back.length &&
							curAnimation == Spritesheet.enemy_side_right.length &&
								curAnimation == Spritesheet.enemy_side_left.length) {
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
		if(downDir) {
			g.drawImage(Spritesheet.enemy_front[curAnimation], x, y, 32, 32, null);
		}else if(upDir) {
			g.drawImage(Spritesheet.enemy_back[curAnimation], x, y, 32, 32, null);
		}else if(rightDir){
			g.drawImage(Spritesheet.enemy_side_right[curAnimation], x, y, 32, 32, null);
		}else if (leftDir) {
			g.drawImage(Spritesheet.enemy_side_left[curAnimation], x, y, 32, 32, null);
		}else {
			g.drawImage(Spritesheet.enemy_front[curAnimation], x, y, 32, 32, null);
		}
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
	}
	
	public void chasePlayer() {
		Player p = Game.player;
		
		if(x < p.x && World.IsFree(x + spd, y)) {
			if(new Random().nextInt(100) < 60) {
				x += spd;
				rightDir = true;
			}
		}else if(x > p.x && World.IsFree(x - spd, y)) {
			if(new Random().nextInt(100) < 60) {
				x -= spd;
				leftDir = true;
			}
		}
		
		if(y < p.y && World.IsFree(x, y + spd)) {
			if(new Random().nextInt(100) < 60) {
				y += spd;
				upDir = true;
			}
		}else if(y > p.y && World.IsFree(x, y - spd)) {
			if(new Random().nextInt(100) < 60) {
				y -= spd;
				downDir = true;
			}
		}
	}
}