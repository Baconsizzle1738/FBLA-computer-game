package me.Baconsizzle1738.main;

public abstract class Enemy extends GameObject{
	protected int damageCooldown;
	protected int damage;
	protected int cooldownTimer = 0;
	protected boolean canDoDamage = false;
	HUD hud;
	public Enemy(int x, int y, ID typeId, int level, int cool, int dmg) {
		super(x, y, typeId, level);
		
		damageCooldown = cool;
		damage = dmg;
	}
	public void setDamageAbility(boolean thing) {
		canDoDamage = thing;
	}
}
