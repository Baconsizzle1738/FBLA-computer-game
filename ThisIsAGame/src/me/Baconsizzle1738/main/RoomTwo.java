package me.Baconsizzle1738.main;

import java.awt.Color;
import java.awt.Graphics;

public class RoomTwo extends Room{
	
	Player p;
	private int clickCool = 0;
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
		if (clickCool<20) {
			clickCool++;
		}
		
		if (p.using && clickCool >=20) {
			DinnerboneButton temp;
			for (int i = 0; i<handler.objects.size(); i++) {
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
			//System.out.println(resetCool);
			if (resetCool>50) {
				canReset = true;
				resetCool = 0;
			}
		}
	}
	
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
		handler.addObject(new Dinnerbone(Game.WIDTH/2-25, Game.HEIGHT/2-25, ID.Door, lvl));
		handler.addObject(new DinnerboneButton(100,100, ID.Button1, lvl, p, "flip"));
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
			}
			handler.addObject(new FollowEnemy(500, 300, ID.FollowEnemy, lvl, 100, 25, 0.7f, handler));
			SetPlayerSpawn();
			canReset = false; 
		}
		
	}


}
