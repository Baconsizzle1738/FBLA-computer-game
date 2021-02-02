package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import me.Baconsizzle1738.Objects.Dinnerbone;
import me.Baconsizzle1738.Objects.DinnerboneButton;
import me.Baconsizzle1738.Objects.FollowEnemy;
import me.Baconsizzle1738.Objects.GameHandler;
import me.Baconsizzle1738.Objects.Obstacle;
import me.Baconsizzle1738.Objects.Player;
import me.Baconsizzle1738.Objects.StaticEnemy;

/**
 * This room is based off Minecraft Dinnerbone.
 * The player has to press buttons to rotate
 * the box 180 degrees and then go into the box to complete the room.
 * @author zheng
 *
 */
public class RoomTwo extends Room{
	private boolean isFlip = false, onButton = false;
	Player p;
	private int clickCool = 0;
	/**
	 * The second room, contains a box and four buttons that rotate the box certain ways, the Player must flip it upside down.
	 * 
	 * @param spawnX	x location of player spawn
	 * @param spawnY	y location of player spawn
	 * @param h			The GameHandler for the GameObjects in the room
	 * @param lvl		The level the room is.
	 */
	public RoomTwo(int spawnX, int spawnY, GameHandler h, int lvl) {
		super(spawnX, spawnY, h, lvl);
		p = new Player(spawnX, spawnY, ID.Player, this.lvl, this.handler);
		
	}

	@Override
	public boolean isComplete() {
		Dinnerbone temp;
		//chack if box is flipped
		if (isFlip) {
			//get the box object
			for (int i = 0; i<handler.objects.size(); i++) {
				if (handler.objects.get(i).gettypeID() == ID.Door) {
					temp = (Dinnerbone) handler.objects.get(i);
					//check if player is in box
					if (temp.getBounds().intersects(p.getBounds())) {
						//reset flip for when the player plays again
						isFlip = false;
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public void tick() {
		//button clicking cooldown so no spamming
		if (clickCool<20) {
			clickCool++;
		}
		
		for (int i = 0; i<handler.objects.size(); i++) {
			if (handler.objects.get(i).gettypeID() == ID.Button1 && handler.objects.get(i).getBounds().intersects(p.getBounds())) {
				onButton = true;
				break;
			}
			else {
				onButton = false;
			}
		}
		
		if (clickCool >=20) {
			DinnerboneButton temp;
			for (int i = 0; i<handler.objects.size(); i++) {
				//check if player is pressing E and also on a button
				if (handler.objects.get(i).gettypeID() == ID.Button1 && p.getBounds().intersects(handler.objects.get(i).getBounds())) {
					temp = (DinnerboneButton) handler.objects.get(i);
					if (p.using) {
						doAction(temp.getAction());
						clickCool = 0;
						break;
					}
					
				}
			}
		}
		//level reset cooldown timer
		if (!canReset) {
			resetCool++;
			if (resetCool>50) {
				canReset = true;
				resetCool = 0;
			}
		}
	}
	
	/**
	 * Makes the Dinnerbone box perform a desired action.
	 * @param a	Action for Dinnerbone box to take
	 */
	private void doAction(String a) {
		for (int i = 0; i<handler.objects.size(); i++) {
			if (handler.objects.get(i).gettypeID() == ID.Door) {
				((Dinnerbone) handler.objects.get(i)).doAction(a);
				if (((Dinnerbone) handler.objects.get(i)).getOrientation() == 180.0) {
					isFlip = true;
				}
				else {
					isFlip = false;
					break;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		//title
		g.setColor(new Color(205, 102, 51));
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
		g.drawString("Bones for Dinner", 500, 40);

		g.setFont(new Font(Font.MONOSPACED, 0, 12));
		if (!isFlip) {
			//Enemy lines
			g.setColor(new Color(230, 30, 30));
			g.drawString("HOW DID THE PLAYER ESCAPE THE", 10, 240);
			g.drawString("MAZE!?", 10, 255);
			g.drawString("GET THE PLAYER YOU FOOLS", 10, 285);
			
			//Player "thoughts"
			g.setColor(new Color(200,200,0));
			
			Dinnerbone temp = null;
			for (int i = 0; i<handler.objects.size(); i++) {
				if (handler.objects.get(i).gettypeID() == ID.Door) {
					temp = (Dinnerbone) handler.objects.get(i);
					break;
				}
			}
			if (temp.getBounds().intersects(p.getBounds())) {
				g.drawString("I can't go through here", Game.WIDTH/2+255, 240);
			}
			else {
				g.drawString("I wonder what these buttons do", Game.WIDTH/2+255, 240);
			}
			
		}
		else if (isFlip) {
			//enemy lines
			g.setColor(new Color(230, 30, 30));
			g.drawString("WAIT NO DON'T LET THE PLAYER IN", 10, 240);
			g.drawString("THE BOX", 10, 255);
			
			//player lines
			g.setColor(new Color(200,200,0));
			g.drawString("Maybe I can go into the box now", Game.WIDTH/2+255, 240);
			
		}
		
		if (onButton) {
			g.setColor(Color.white);
			g.drawString("E - INTERACT", 10, 530);
		}
		
	}

	@Override
	public void startLevel() {
		
		Color wall = new Color(73, 116, 122);
		handler.addObject(p);
		
		//the box
		handler.addObject(new Dinnerbone(Game.WIDTH/2, 200, ID.Door, lvl));
		//the buttons
		handler.addObject(new DinnerboneButton(Game.WIDTH/2-82, 400, ID.Button1, lvl, "turnR"));
		handler.addObject(new DinnerboneButton(Game.WIDTH/2-32, 400, ID.Button1, lvl, "turn"));
		handler.addObject(new DinnerboneButton(Game.WIDTH/2+18, 400, ID.Button1, lvl, "reflect"));
		handler.addObject(new DinnerboneButton(Game.WIDTH/2+68, 400, ID.Button1, lvl, "turn2"));
		//the following guard
		handler.addObject(new FollowEnemy(Game.WIDTH/2, 200, ID.FollowEnemy, lvl, 100, 15, 0.55f, handler));
		//border/walls
		handler.addObject(new Obstacle(Game.WIDTH/2-250, 50, ID.Obstacle, lvl, 5, 800, wall));
		handler.addObject(new Obstacle(Game.WIDTH/2+245, 50, ID.Obstacle, lvl, 5, 800, wall));
		handler.addObject(new Obstacle(Game.WIDTH/2-250, 50, ID.Obstacle, lvl, 500, 5, wall)); 
		//the door from the maze
		handler.addObject(new Obstacle(Game.WIDTH/2-250, 495, ID.Obstacle, lvl, 5, 50, new Color(0, 200, 200)));
		//static enemies/guards
		handler.addObject(new StaticEnemy(Game.WIDTH/2-230, 360, ID.Enemy, lvl, Game.WIDTH/2+200, 8, 7, 40, "x"));
		handler.addObject(new StaticEnemy(Game.WIDTH/2+200, 230, ID.Enemy, lvl, Game.WIDTH/2-230, 8, 7, 40, "x"));
		this.SetPlayerSpawn();
		
	}
	
	
	
	@Override
	public void reset() {
		
		if (canReset) {
			for (int i = 0; i<handler.objects.size(); i++) {
				if (handler.objects.get(i).gettypeID() == ID.FollowEnemy) {
					handler.removeObject(handler.objects.get(i));
				}
				//resets data and orientation for the box
				else if (handler.objects.get(i).gettypeID() == ID.Door) {
					((Dinnerbone) handler.objects.get(i)).reset();
				}
			}
			//put enemy back into position
			handler.addObject(new FollowEnemy(Game.WIDTH/2, 200, ID.FollowEnemy, lvl, 100, 25, 0.55f, handler));
			SetPlayerSpawn();
			canReset = false;
			isFlip = false;
		}
		
	}


}
