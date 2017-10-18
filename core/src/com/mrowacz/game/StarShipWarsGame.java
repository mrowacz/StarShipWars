package com.mrowacz.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mrowacz.game.entities.Boundary;
import com.mrowacz.game.entities.Player;

import java.util.ArrayList;

import static com.mrowacz.game.GameSettings.*;

public class StarShipWarsGame extends ApplicationAdapter implements InputProcessor {

	private World world;
	private Player player;
	private Boundary boundary;
	private Box2DDebugRenderer b2dr;

	private OrthographicCamera camera;
	private OrthographicCamera b2dCam;

	@Override
	public void create () {

		// create box2d world
		world = new World(new Vector2(0, 0), false);

		// set up b2renderer
		b2dr = new Box2DDebugRenderer();

		// set player
		player = new Player(world);

		// set up main camera
		float cameraWidth = 2 * GameSettings.V_WIDTH / PPM;
		float cameraHeight = 2 * GameSettings.V_HEIGHT / PPM;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, cameraWidth, cameraHeight);

		// set up box2d cam;
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, cameraWidth, cameraHeight);

		// set key listener
		Gdx.input.setInputProcessor(this);

		// boundaries
		boundary = new Boundary(world);
	}

	@Override
	public void render () {
		Gdx.graphics.setTitle("GAME fps: " + Gdx.graphics.getFramesPerSecond());

		// make step in box2d world
		world.step(Gdx.graphics.getDeltaTime(), 6, 12);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// move camera
		Vector2 vec = new Vector2(
				player.getBody().getPosition().x,
				player.getBody().getPosition().y
		);
		camera.position.x = vec.x; camera.position.y = vec.y;
		b2dCam.position.x = vec.x; b2dCam.position.y = vec.y;

		camera.update();
		b2dCam.update();

		// render box2d debug objects
		b2dr.render(world, b2dCam.combined);
	}
	
	@Override
	public void dispose () {
		world.dispose();
		b2dr.dispose();
	}

	// InputProcessor
	@Override
	public boolean keyDown(int keycode)
	{
		System.out.println("Keycode " + keycode);
		if (keycode == Input.Keys.UP)
			player.applyForce(new Vector2(MOVE_FORCE, 0));
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
