package com.game.engine.projectile;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.game.engine.entity.Entity;
import com.game.engine.handlers.Vars;
import com.game.engine.screen.GameScreen;

public abstract class Projectile{
	
	protected GameScreen screen;
	protected World world;
	protected Entity originEntity;
	protected Vector2 destination;

	protected Vector2 originToDest;
	protected float angle;
	
	protected float speed = 5;
	
	protected Body body;
	public boolean isActive = true;
	
	protected Sprite sprite;
	
	public Projectile(GameScreen screen, Vector2 destination, Entity originEntity, World world) {
		
		this.world = world;
		this.originEntity = originEntity;
		this.destination = destination;
		this.screen = screen;
		
		screen.bulletHandler.addProjectile(this);
		
		originToDest = new Vector2(destination.x - originEntity.getGun().getPosition().x, destination.y - originEntity.getGun().getPosition().y).nor();
		angle = (float) Math.toDegrees(Math.atan(originToDest.y / originToDest.x));

				
		defineProjectile();
	}

	public void update(float dt) {
		updateSprite();
	}
	
	
	protected abstract void defineSprite();
	private void updateSprite() {
		if(sprite != null) {
			sprite.setPosition(body.getPosition().x - sprite.getWidth()/2, body.getPosition().y - sprite.getHeight()/2);
			sprite.setRotation(angle);
			screen.batch.setProjectionMatrix(screen.camera.combined);
			screen.batch.begin();
			sprite.draw(screen.batch);
			screen.batch.end();
		}
	}
	private void defineProjectile() { 
		BodyDef bdef = new BodyDef();
		bdef.position.set(new Vector2(originEntity.getGun().getPosition().x, originEntity.getGun().getPosition().y - 4/Vars.PPM));
		bdef.type = BodyDef.BodyType.DynamicBody;
		body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		
		CircleShape cShape = new CircleShape();
		cShape.setRadius(3 / Vars.PPM);
		
		fdef.shape = cShape;
		
		fdef.filter.categoryBits = Vars.ENTITY_BIT + Vars.BULLET_BIT;
		fdef.filter.maskBits = Vars.GROUND_BIT + Vars.ENTITY_BIT;
		
		body.createFixture(fdef).setUserData(this);
	}

	public void onCollide(Fixture target) {
		isActive = false;
	}
	public Body getBody() {
		return body;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	


}
