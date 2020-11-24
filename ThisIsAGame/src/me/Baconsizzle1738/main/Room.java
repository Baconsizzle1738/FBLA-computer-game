package me.Baconsizzle1738.main;

public abstract class Room {
	private int initialX, initialY;
	GameHandler handler;
	public boolean complete = false;
	
	public Room(int spawnX, int spawnY, GameHandler h) {
		initialX = spawnX;
		initialY = spawnY;
		handler = h;
	}
	
	public void SetPlayerSpawn() {
		for (int i = 0; i<handler.objects.size(); i++) {
			if (handler.objects.get(i).gettypeID() == ID.Player) {
				GameObject temp = handler.objects.get(i);
				temp.setX(initialX);
				temp.setY(initialY);
			}
		}
	}
	
	public abstract void tick();
	public abstract void render();
}