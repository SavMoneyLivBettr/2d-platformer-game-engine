package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.game.engine.handlers.Vars;
import com.game.engine.main.Main;
import com.game.engine.screen.GameScreen;

public class Level1Screen extends GameScreen{
		
	private ParticleEffect particleEffect;

	public Level1Screen(Main game, String mapPath) {
		super(game, mapPath);
		
		particleEffect = new ParticleEffect();
		particleEffect.load(Gdx.files.internal("particles/blueflame"), Gdx.files.internal(""));
		particleEffect.getEmitters().first().setPosition(32 / Vars.PPM, 32 / Vars.PPM);
		particleEffect.start();
		
	}
	
	@Override
	public void render(float dt) {
		super.render(dt);
	}
}
