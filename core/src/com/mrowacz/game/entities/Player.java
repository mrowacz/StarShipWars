package com.mrowacz.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.mrowacz.game.GameSettings.*;

public class Player {

    private static final String TAG = "PLAYER";
    private Body body;
    private BodyDef bdef;
    private FixtureDef fdef;
    private PolygonShape shape;

    public  Player(World world)
    {
        shape = new PolygonShape();
        fdef = new FixtureDef();
        bdef = new BodyDef();

        bdef.position.set(0 / PPM, 0 / PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        shape.setAsBox(10 / PPM, 10 / PPM);
        fdef.shape = shape;
        fdef.filter.categoryBits = BIT_PLAYER;
        fdef.filter.maskBits = BIT_ROCK | BIT_BOUNDS;
        fdef.restitution = 0f;
        body.createFixture(fdef).setUserData("player");
    }

    public void applyForce(Vector2 force)
    {
        Gdx.app.log(TAG, "Applying force " + force.toString());
        body.applyForceToCenter(force, true);
    }

    public Body getBody()
    {
        return body;
    }
}
