package com.game.engine.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.game.engine.screen.GameScreen;
import com.game.engine.weapon.Gun;

public abstract class Entity {
	
	protected GameScreen screen;
	protected World world;
	public Body body;
	
	protected float accel;
	protected float maxSpeed;
	
	protected Sprite sprite;
	
	protected Gun gun;
	
	public Entity(World world, GameScreen screen) {
		this.world = world;
		this.screen = screen;

		//Define Box2D for player
		defineBody();
		//Define Sprite
		defineSprite();
	}
	public void update(float dt) {
		updateSprite(dt);
		gun.update(dt);
	}
	
	protected abstract void defineBody();
	protected abstract void defineSprite();
	protected abstract void updateSprite(float dt);
	
	public Gun getGun() {
		return gun;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public float getMaxSpeed() {
		return maxSpeed;
	}

}
