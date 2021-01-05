package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

/**
 * This is the third room.
 * The player must find the correct number and collect it in order
 * to advance to the next level. In this case, the number is 3 as
 * hinted by the fact that the Level indicator in the HUD displays
 * MISSINGNO instead of 3.
 * 
 * @author zheng
 *
 */
public class RoomThree extends Room{
	
	private Player p;
	
	private boolean onBlock = false, complete = false;
	
	private Point[][] grass;

	/**
	 * Room takes only the parameters for <code>Room</code>.
	 * 
	 * @param spawnX	x position of player spawn
	 * @param spawnY	y position of player spawn
	 * @param h			<code>GameHandler</code> for any objects on level
	 * @param lvl		level of <code>Room</code>
	 */
	public RoomThree(int spawnX, int spawnY, GameHandler h, int lvl) {
		super(spawnX, spawnY, h, lvl);
		
		p = new Player(spawnX, spawnY, ID.Player, lvl, handler);
		grass = new Point[25][30];
		//put points in for the grass generation
		for (int i = 0; i<grass.length; i++) {
			for (int j = 0; j<grass[i].length; j++) {
				grass[i][j] = new Point(grassX(i), grassY(j));
			}
		}
	}
	
	/**
	 * x increments for the grass so they are evenly spaced.
	 * @param n	x increment for grass
	 * @return	x coordinate corresponding to the increment
	 */
	private int grassX(int n) {
		return 250+20*n;
	}
	
	/**
	 * y increments for the grass so they are evenly spaced.
	 * @param n	y increment for grass
	 * @return	y coordinate corresponding to the increment
	 */
	private int grassY(int n) {
		return 100+20*n;
	}
	
	@Override
	public void startLevel() {
		
		handler.addObject(p);
		p.using = false;
		//following enemies
		handler.addObject(new FollowEnemy(280, 150, ID.FollowEnemy, lvl, 85, 10, 0.62f, handler));
		handler.addObject(new FollowEnemy(550, 410, ID.FollowEnemy, lvl, 109, 20, 1.2f, handler));
		//static enemies
		handler.addObject(new StaticEnemy(grassX(12), grassY(0), ID.Enemy, lvl, Game.HEIGHT-50, 3, 8, 25, "y"));
		handler.addObject(new StaticEnemy(grassX(0), grassY(10), ID.Enemy, lvl, grassX(23), 3, 8, 25, "x"));
		
		//add the numbers for player to find
		handler.addObject(new NumberBlock(grassX(10), grassY(3), ID.Interactable, lvl, 1));
		handler.addObject(new NumberBlock(grassX(9), grassY(7), ID.Interactable, lvl, 2));
		handler.addObject(new NumberBlock(grassX(23), grassY(5), ID.Interactable, lvl, 3));
		handler.addObject(new NumberBlock(grassX(18), grassX(2), ID.Interactable, lvl, 4));
		handler.addObject(new NumberBlock(grassX(21), grassY(20), ID.Interactable, lvl, 5));
		handler.addObject(new NumberBlock(grassX(15), grassY(19), ID.Interactable, lvl, 6));
		handler.addObject(new NumberBlock(grassX(3), grassY(17), ID.Interactable, lvl, 7));
		
		//the walls
		handler.addObject(new Obstacle(grassX(0)-5, grassY(0)-5, ID.Obstacle, lvl, 5, 600, new Color(200, 200, 200)));
		handler.addObject(new Obstacle(grassX(0)-5, grassY(0)-5, ID.Obstacle, lvl, 510, 5, new Color(200, 200, 200)));
		handler.addObject(new Obstacle(grassX(25), grassY(0)-5, ID.Obstacle, lvl, 5, 600, new Color(200, 200, 200)));
		
		//adds grass last to hide everything
		for (int i = 0; i<grass.length; i++) {
			for (int j = 0; j<grass[i].length; j++) {
				handler.addObject(new GrassBlock(grass[i][j].x, grass[i][j].y, ID.Grass, lvl, p));
			}
		}
		SetPlayerSpawn();
	}
	
	@Override
	public void reset() {
		if (canReset) {
			for (int i = 0; i<handler.objects.size(); i++) {
				if (handler.objects.get(i).gettypeID() == ID.FollowEnemy || handler.objects.get(i).gettypeID() == ID.Grass) {
					handler.removeObject(handler.objects.get(i));
					i--;
				}
			}
			handler.addObject(new FollowEnemy(280, 150, ID.FollowEnemy, lvl, 85, 10, 0.62f, handler));
			handler.addObject(new FollowEnemy(550, 410, ID.FollowEnemy, lvl, 109, 20, 0.69f, handler));
			for (int i = 0; i<grass.length; i++) {
				for (int j = 0; j<grass[i].length; j++) {
					handler.addObject(new GrassBlock(grass[i][j].x, grass[i][j].y, ID.Grass, lvl, p));
				}
			}
			SetPlayerSpawn();
			canReset = false;
		}
		
		
	}

	@Override
	public boolean isComplete() {
		
		if (complete) {
			complete = false;
			return true;
		}
		return false;
		
	}

	@Override
	public void tick() {
		onBlock = false;
		//level reset cooldown timer
		if (!canReset) {
			resetCool++;
			//System.out.println(resetCool);
			if (resetCool>50) {
				canReset = true;
				resetCool = 0;
			}
		}
		
		
		for (int i = 0; i<handler.objects.size(); i++) {
			//checks through all blocks
			if (handler.objects.get(i).gettypeID() == ID.Interactable && handler.objects.get(i).getBounds().intersects(p.getBounds())) {
				NumberBlock temp = (NumberBlock) handler.objects.get(i);
				//if player is on the block
				onBlock = true;
				//if player interacts with the correct block the room is complete
				if (p.using && temp.getNum() == 3) {
					complete = true;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		//title
		g.setColor(new Color(205, 102, 51));
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
		g.drawString("MISSINGNO", 500, 40);

		g.setFont(new Font(Font.MONOSPACED, 0, 12));
		g.setColor(new Color(230, 30, 30));
		g.drawString("You think you're so smart?", 10, 240);
		g.drawString("How 'bout I hide everything.", 10, 255);
		g.drawString("Good luck! hehehe", 10, 285);
				
		//Player "thoughts"
		g.setColor(new Color(200,200,0));
		g.drawString("Which room is this?", Game.WIDTH/2+255, 240);
		
		if (onBlock) {
			g.setColor(Color.white);
			g.drawString("E - INTERACT", 10, 520);
		}
	
	}

}
