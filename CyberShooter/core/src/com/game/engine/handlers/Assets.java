package com.game.engine.handlers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
	
	public static final AssetManager manager = new AssetManager();
	
	public static final String player = "sprites/megaman.png";
	public static final String gun = "sprites/megashark.png";
	public static final String laser = "sprites/laser.png";
	public static final String greenLaser = "sprites/greenlaser.png";
	public static final String enemy = "sprites/sonic.gif";
	public static final String laserRifle = "sprites/laserrifle.png";
	
	public static void load() {
		manager.load(player, Texture.class);
		manager.load(gun, Texture.class);
		manager.load(laser, Texture.class);
		manager.load(enemy, Texture.class);
		manager.load(laserRifle, Texture.class);
		manager.load(greenLaser, Texture.class);
	}
	public static void dispose() {
		manager.dispose();
	}

}
