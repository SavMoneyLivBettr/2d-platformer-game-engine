package com.game.engine.handlers;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class B2DWorldCreator {
	
	private World world;
	
	public B2DWorldCreator(World world, String mapPath) {
		
		this.world = world;
		
		TmxMapLoader mapLoader = new TmxMapLoader();
		TiledMap map = mapLoader.load(mapPath);
		
		//Create Box2D bodies and fixtures
				BodyDef bdef = new BodyDef();
				PolygonShape shape = new PolygonShape();
				FixtureDef fdef = new FixtureDef();
				Body body;
				

				for(MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
					Rectangle rect = ((RectangleMapObject) object).getRectangle();
					
					bdef.type = BodyDef.BodyType.StaticBody;
					bdef.position.set((rect.getX() + rect.getWidth() / 2) / Vars.PPM, (rect.getY() + rect.getHeight() / 2) / Vars.PPM);
					
					body = world.createBody(bdef);
					
					shape.setAsBox(rect.getWidth() / 2 / Vars.PPM, rect.getHeight() / 2 / Vars.PPM);
					fdef.shape = shape;
					fdef.filter.categoryBits = Vars.GROUND_BIT;
					fdef.filter.maskBits = Vars.ENTITY_BIT;
					fdef.friction = .5f;
					body.createFixture(fdef);
				}
	}
}
