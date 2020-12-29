package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Graphics;

public class RoomTwo extends Room{
	
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
		p = new Player(500, 500, ID.Player, this.lvl, this.handler);
		handler.addObject(p);
	}

	@Override
	public boolean isComplete() {
		return false;
	}

	@Override
	public void tick() {
		//button clicking cooldown so no spamming
		if (clickCool<20) {
			clickCool++;
		}
		
		if (p.using && clickCool >=20) {
			DinnerboneButton temp;
			for (int i = 0; i<handler.objects.size(); i++) {
				//check if player is pressing E and also on a button
				if (handler.objects.get(i).gettypeID() == ID.Button1 && p.getBounds().intersects(handler.objects.get(i).getBounds())) {
					temp = (DinnerboneButton) handler.objects.get(i);
					doAction(temp.getAction());
					clickCool = 0;
					break;
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
			}
		}
	}

	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void startLevel() {
		//test
		
//		handler.addObject(new StaticEnemy(210, 400, ID.StaticEnemy, lvl, 500, 3, 1, 40, -1));
//		handler.addObject(new Obstacle(234, 483, ID.Obstacle, lvl, 625, 18, new Color(50, 200, 0)));
		handler.addObject(new Dinnerbone(Game.WIDTH/2, 200, ID.Door, lvl));
		handler.addObject(new DinnerboneButton(Game.WIDTH/2-82, 400, ID.Button1, lvl, "turnR"));
		handler.addObject(new DinnerboneButton(Game.WIDTH/2-32, 400, ID.Button1, lvl, "turn"));
		handler.addObject(new DinnerboneButton(Game.WIDTH/2+18, 400, ID.Button1, lvl, "reflect"));
		handler.addObject(new DinnerboneButton(Game.WIDTH/2+68, 400, ID.Button1, lvl, "turn2"));
		handler.addObject(new FollowEnemy(500, 300, ID.FollowEnemy, lvl, 100, 15, 0.63f, handler));
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
			handler.addObject(new FollowEnemy(500, 300, ID.FollowEnemy, lvl, 100, 25, 0.63f, handler));
			SetPlayerSpawn();
			canReset = false;
		}
		
	}


}
