package com.game.engine.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.engine.entity.Entity;
import com.game.engine.handlers.B2DWorldCreator;
import com.game.engine.handlers.BulletHandler;
import com.game.engine.handlers.Vars;
import com.game.engine.handlers.WorldContactListener;
import com.game.engine.main.Main;
import com.game.engine.projectile.Projectile;
import com.game.entities.Player;
import com.game.entities.TestEnemy;

public class GameScreen implements Screen{
			
		protected Main game;
		public OrthographicCamera camera;
		protected Viewport viewPort;
		
		public SpriteBatch batch;
		
		protected B2DWorldCreator worldCreator;
		
		public Player player;
		public TestEnemy enemy;
		
		protected TmxMapLoader mapLoader;
		protected TiledMap map;
		protected OrthogonalTiledMapRenderer renderer;
		
		//Box2D variables
		protected World world;
		protected Box2DDebugRenderer b2dr;
				
		public Array<Projectile> projectiles;
		
		public BulletHandler bulletHandler;
		
		public GameScreen(Main game, String mapPath) {
			this.game = game;
			//Set up camera
			camera = new OrthographicCamera();
			viewPort = new FitViewport(Main.V_WIDTH / Vars.PPM, Main.V_HEIGHT / Vars.PPM, camera);
			
			camera.position.set(viewPort.getWorldWidth() / 2, viewPort.getWorldHeight() / 2, 0);
			//Set up world
			world = new World(new Vector2(0, Vars.GRAVITY), false);
			world.setContactListener(new WorldContactListener());
			
			b2dr = new Box2DDebugRenderer();

			batch = new SpriteBatch();
			
			worldCreator = new B2DWorldCreator(world, mapPath);
			
			bulletHandler = new BulletHandler(world, this);
			
			createTiledMapRenderer(mapPath);
			
			player = new Player(world, this);
			enemy = new TestEnemy(world, this);

		}

		@Override
		public void render(float delta) {
			world.step(1 / 60f, 6, 2);
			
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			handleInput(delta);
			bulletHandler.update(delta);
			renderer.setView(camera);
			
			player.update(delta);
			enemy.update(delta);
			
			renderer.render();
			//b2dr.render(world, camera.combined);

			if(player.body.getPosition().x > Main.V_WIDTH / Vars.PPM / 3){
				camera.position.x += player.body.getLinearVelocity().x / Vars.PPM;
				camera.update();
			}
			

		}
		public void handleInput(float dt) {
			if(Gdx.input.isKeyJustPressed(Input.Keys.W)) {
				player.jump();
			}
			if(Gdx.input.isKeyPressed(Input.Keys.D) && player.body.getLinearVelocity().x <= 2) {
				player.moveRight();
			}
			if(Gdx.input.isKeyPressed(Input.Keys.A) && player.body.getLinearVelocity().x >= -2) {
				player.moveLeft();
			}
			if(Gdx.input.isTouched()) {
				player.onClick(getMousePosition());
			}
		}
		
		public Vector2 getMousePosition() {
			Vector3 mousePositionV3 = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
			return new Vector2(mousePositionV3.x, mousePositionV3.y);
		}
		
		protected void createTiledMapRenderer(String mapPath) {
			TmxMapLoader mapLoader = new TmxMapLoader();
			TiledMap map = mapLoader.load(mapPath);
			
			renderer = new OrthogonalTiledMapRenderer(map, 1 / Vars.PPM);
		}
		
		
		
		
		@Override
		public void resize(int width, int height) {
			viewPort.update(width, height);
		}

		@Override
		public void pause() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void resume() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void hide() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void dispose() {
			map.dispose();
			renderer.dispose();
			world.dispose();
			b2dr.dispose();
			game.dispose();
			batch.dispose();
		}
		
		@Override
		public void show() {
			// TODO Auto-generated method stub
		}

}
