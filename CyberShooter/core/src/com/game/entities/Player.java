package com.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.game.engine.entity.Entity;
import com.game.engine.handlers.Assets;
import com.game.engine.handlers.Vars;
import com.game.engine.screen.GameScreen;
import com.game.engine.weapon.Gun;
import com.game.weapons.guns.TestGun2;

public class Player extends Entity{
	
	private float accel = .1f;
	private float maxSpeed = 2f;
	
	private Gun gun;
	
	public static boolean isGrounded = false;
	
	public Player(World world, GameScreen screen) {
		super(world, screen);
		gun = new TestGun2(screen, this, world);
		
	}
	public void update(float dt) {
		updateSprite(dt);
		if(gun != null) gun.update(dt);
	}
	
	protected void updateSprite(float dt) {
		sprite.setPosition(body.getPosition().x - sprite.getWidth()/2, body.getPosition().y - sprite.getHeight()/2);
		screen.batch.setProjectionMatrix(screen.camera.combined);
		screen.batch.begin();
		sprite.draw(screen.batch);
		screen.batch.end();
	}
	
	protected void defineSprite() {
		sprite = new Sprite(Assets.manager.get(Assets.player, Texture.class));
		sprite.setBounds(0, 0, 28/Vars.PPM, 24/Vars.PPM);
	}
	
	protected void defineBody() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(32 / Vars.PPM, 32 / Vars.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(6/Vars.PPM, 12/Vars.PPM);
		
		fdef.shape = shape;
		fdef.filter.categoryBits = Vars.PLAYER_BIT + Vars.ENTITY_BIT;
		fdef.filter.maskBits = Vars.GROUND_BIT;
		body.createFixture(fdef).setUserData(this);
		
		shape.setAsBox(5f/Vars.PPM, 3/Vars.PPM, new Vector2(0, -14/Vars.PPM), 0);
		fdef.shape = shape;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("foot");
	}
	
	
	public void setMaxSpeed(float speed) {
		this.maxSpeed = speed;
	}
	public void setAccel(float accel) {
		this.accel = accel;
	}
	public float getMaxSpeed() {
		return maxSpeed;
	}
	public float getAccel() {
		return accel;
	}
	
	public Gun getGun() {
		return gun;
	}
	public void setGun(Gun gun) {
		this.gun = gun;
	}
	
	
	
	//Movement handlers
	public void moveRight() {
		if(body.getLinearVelocity().x < maxSpeed)
		body.applyLinearImpulse(new Vector2(accel, 0), body.getWorldCenter(), true);
		//player.setFlip(true, false);
		sprite.setFlip(true, false);
	}
	public void moveLeft() {
		if(body.getLinearVelocity().x < maxSpeed)
		body.applyLinearImpulse(new Vector2(-accel, 0), body.getWorldCenter(), true);
		//player.setFlip(false, false);
		sprite.setFlip(false, false);
	}
	public void jump() {
		if(isGrounded)
		body.applyForceToCenter(new Vector2(0, 200), true);
	}
	public void onClick(Vector2 mousePosition) {
		if(gun != null)gun.shoot(mousePosition);
	}

}
