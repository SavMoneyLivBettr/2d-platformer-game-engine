package com.game.engine.handlers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.game.engine.main.Main;
import com.game.engine.projectile.Projectile;
import com.game.engine.screen.GameScreen;

public class BulletHandler {
	
	public GameScreen screen;
	
	private Array<Projectile> projectiles;
	
	private World world;
	
	public BulletHandler(World world, GameScreen screen) {
		this.screen = screen;
		this.world = world;
		projectiles = new Array<Projectile>();
	}
	
	public void update(float dt) {
		if(projectiles != null)
		for(int projectile = 0; projectile < projectiles.size; projectile++) {
			Projectile currentProjectile = projectiles.get(projectile);
			currentProjectile.update(dt);
			if(currentProjectile.isActive == false) {
				world.destroyBody(currentProjectile.getBody());
				projectiles.removeIndex(projectile);
			}
		}
	}
	
	public void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}

}
