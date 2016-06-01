package com.game.engine.main;

import com.badlogic.gdx.Game;
import com.game.engine.handlers.Assets;
import com.game.screens.Level1Screen;

public class Main extends Game {
	
	public static final String TITLE = "Cyber Shooter Version 0";
	public static final int V_WIDTH = 640;
	public static final int V_HEIGHT = 360;
	public static final int SCALE = 2;

	
	@Override
	public void create () {
		Assets.load();
		while(!Assets.manager.update())
			System.out.println(Assets.manager.getProgress());
		setScreen(new Level1Screen(this, "maps/test2.tmx"));
	}
	
	public void changeScreen(int level) {
		if(level == 1) {
			setScreen(new Level1Screen(this, "maps/test2.tmx"));
		}
	}
	

	@Override
	public void render () {
		super.render();
		
		
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}
}
