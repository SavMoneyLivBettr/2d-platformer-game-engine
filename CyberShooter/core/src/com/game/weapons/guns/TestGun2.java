package com.game.weapons.guns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.game.engine.entity.Entity;
import com.game.engine.handlers.Assets;
import com.game.engine.handlers.Vars;
import com.game.engine.screen.GameScreen;
import com.game.engine.weapon.Gun;
import com.game.weapons.projectiles.GreenLaser;

public class TestGun2 extends Gun{
	

	public TestGun2(GameScreen gameScreen, Entity holder, World world) {
		super(gameScreen, holder, world);
		
		defineSprite();

		frequency = 5;
	}

	@Override
	protected void defineSprite() {
		sprite = new Sprite(Assets.manager.get(Assets.laserRifle, Texture.class));
		sprite.setBounds(0, 0, 20/Vars.PPM, 10/Vars.PPM);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		
	}
	@Override
	public void shoot(Vector2 target) {
		super.shoot(target);
		position = new Vector2(holder.body.getPosition().x - sprite.getWidth()/2 + 12/Vars.PPM, holder.body.getPosition().y - sprite.getHeight()/2 + 8/Vars.PPM);

		
		if(frequencyCounter >= frequency) {
			projectile = new GreenLaser(screen, target, holder, super.world);
			frequencyCounter = 0;
		}
	}
	

	
	

}
