package com.game.engine.handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.game.engine.projectile.Projectile;
import com.game.entities.Player;
import com.game.weapons.projectiles.Bullet;

public class WorldContactListener implements ContactListener{

	@Override
	public void beginContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		//Foot sensor
		if(fa.getUserData() == "foot" || fb.getUserData() == "foot") {
			System.out.println("On ground");
			Player.isGrounded = true;
		}
		
		//Bullet collision
		if(fa.getUserData() instanceof Projectile || fb.getUserData() instanceof Projectile) {
			Fixture projectile = fa.getUserData() instanceof Projectile ? fa : fb;
			Fixture target = !(fa.getUserData() instanceof Projectile) ? fa : fb;
			if(!(fa.getUserData() instanceof Player) && !(fb.getUserData() instanceof Player)) {
				((Projectile)projectile.getUserData()).onCollide(target);
			}
		}
	}

	@Override
	public void endContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if(fa.getUserData() == "foot" || fb.getUserData() == "foot") {
			System.out.println("On ground");
			Player.isGrounded = false;
		}
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		
	}

}
