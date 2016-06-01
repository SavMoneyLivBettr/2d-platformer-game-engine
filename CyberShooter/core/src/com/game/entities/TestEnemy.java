package com.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.game.engine.entity.Entity;
import com.game.engine.handlers.Assets;
import com.game.engine.handlers.Vars;
import com.game.engine.screen.GameScreen;
import com.game.weapons.guns.TestGun;

public class TestEnemy extends Entity{
	
	
	public TestEnemy(World world, GameScreen screen) {
		super(world, screen);
		
		gun = new TestGun(screen, this, world);
	}
	@Override
	public void update(float dt) {
		super.update(dt);
		gun.shoot(screen.player.body.getPosition());
	}

	@Override
	protected void defineBody() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(500 / Vars.PPM, 64 / Vars.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(6/Vars.PPM, 12/Vars.PPM);
		
		fdef.shape = shape;
		fdef.filter.categoryBits = Vars.PLAYER_BIT + Vars.ENTITY_BIT;
		fdef.filter.maskBits = Vars.GROUND_BIT;
		body.createFixture(fdef).setUserData(this);	
	}

	@Override
	protected void defineSprite() {
		sprite = new Sprite(Assets.manager.get(Assets.enemy, Texture.class));
		sprite.setBounds(0, 0, 28/Vars.PPM, 24/Vars.PPM);
	}

	@Override
	protected void updateSprite(float dt) {
		sprite.setPosition(body.getPosition().x - sprite.getWidth()/2, body.getPosition().y - sprite.getHeight()/2);
		screen.batch.setProjectionMatrix(screen.camera.combined);
		screen.batch.begin();
		sprite.draw(screen.batch);
		screen.batch.end();		
	}
	
	

}
