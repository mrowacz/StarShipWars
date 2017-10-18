package com.mrowacz.game.entities;

import com.badlogic.gdx.physics.box2d.*;

import static com.mrowacz.game.GameSettings.*;

public class Boundary {
    private Body body;
    private BodyDef bdef;
    private FixtureDef fdef;
    private PolygonShape shape;

    public Boundary (World world)
    {
        shape = new PolygonShape();
        fdef = new FixtureDef();
        bdef = new BodyDef();

        // right
        bdef.position.set(600 / PPM, 0 / PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        shape.setAsBox(10 / PPM, 600 / PPM);
        fdef.shape = shape;
        fdef.filter.categoryBits = BIT_BOUNDS;
        fdef.filter.maskBits = BIT_ROCK | BIT_PLAYER;
        fdef.restitution = 0f;
        body.createFixture(fdef).setUserData("bound");
    }
}
