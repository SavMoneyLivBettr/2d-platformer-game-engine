package com.game.engine.handlers;

public class Vars {
	
	public static final float GRAVITY = -9.8f;
	
	public static final float PPM = 100;
	
	//Collision variables
	public static final short PLAYER_BIT = 0x0001;
	public static final short GROUND_BIT = 0x0010;
	public static final short ENTITY_BIT = 0x0100;
	public static final short BULLET_BIT = 0x1000;

}
