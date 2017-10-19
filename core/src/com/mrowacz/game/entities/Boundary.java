package com.mrowacz.game.entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.physics.box2d.*;
import com.mrowacz.game.GameSettings;

import static com.mrowacz.game.GameSettings.*;

public class Boundary {
    private Body body;
    private BodyDef bdef;
    private FixtureDef fdef;
    private PolygonShape shape;

    public Boundary (World world)
    {
        float wp = GameSettings.BOUNDARY_SIZE / PPM;
        float hp = GameSettings.BOUNDARY_WIDTH / PPM;

        float[][] v = new float[][] {
            {wp, 0}, {-wp, 0}, {0, wp}, {0, -wp}
        };

        float[][] orientation = new float[][] {
                {hp, wp}, {hp, wp}, {wp, hp}, {wp, hp}
        };

        for (int i = 0; i < 4; i++) {
            shape = new PolygonShape();
            fdef = new FixtureDef();
            bdef = new BodyDef();

            // right
            bdef.position.set(v[i][0], v[i][1]);
            bdef.type = BodyDef.BodyType.DynamicBody;
            body = world.createBody(bdef);

            shape.setAsBox(orientation[i][0], orientation[i][1]);
            fdef.shape = shape;
            fdef.filter.categoryBits = BIT_BOUNDS;
            fdef.filter.maskBits = BIT_ROCK | BIT_PLAYER;
            fdef.restitution = 0f;
            body.createFixture(fdef).setUserData("bound");
        }
    }
}
