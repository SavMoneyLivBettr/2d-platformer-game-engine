package com.game.engine.weapon;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.game.engine.entity.Entity;
import com.game.engine.handlers.Vars;
import com.game.engine.projectile.Projectile;
import com.game.engine.screen.GameScreen;

public abstract class Gun {
	
	protected World world;
	protected Entity holder;
	protected Vector2 position;
	
	protected GameScreen screen;
	
	protected Projectile projectile;
	
	protected Body body;
	protected Sprite sprite;
	
	protected float playerOffset = 16;
	
	protected float angle;
	protected int frequencyCounter = 0;
	protected int frequency = 10;
	
	public Gun(GameScreen gameScreen, Entity holder, World world) {
		this.screen = gameScreen;
		this.world = world;
		this.holder = holder;
	}

	

	public void update(float dt) {
		position = new Vector2(holder.body.getPosition().x - sprite.getWidth()/2, holder.body.getPosition().y - sprite.getHeight()/2 - 2/Vars.PPM);
		
		updateSprite();
		
		angle = 0;
		frequencyCounter++;
	}
	
	protected abstract void defineSprite();

	private void updateSprite() {
		if(sprite != null) {
			sprite.setPosition(position.x, position.y);
			sprite.setRotation(angle);
			if(angle > 90) sprite.setFlip(false, true);
			else sprite.setFlip(false, false);
			screen.batch.begin();
			sprite.draw(screen.batch);
			screen.batch.end();
		}
	}
	public void shoot(Vector2 target) {
		position = new Vector2(holder.body.getPosition().x - sprite.getWidth()/2 + 12/Vars.PPM, holder.body.getPosition().y - sprite.getHeight()/2 + 8/Vars.PPM);

		Vector2 originToDest = new Vector2(target.x - holder.body.getPosition().x, target.y - holder.body.getPosition().y).nor();
		angle = (float) Math.toDegrees(Math.atan(originToDest.y / originToDest.x));
		
		if(originToDest.x < 0) angle += 180;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}


}
