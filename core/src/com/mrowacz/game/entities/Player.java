package com.mrowacz.game.entities;

import com.badlogic.gdx.physics.box2d.Body;

public class Player {
    private Body body;
    public Body getBody() {
        return body;
    }
    public void setBody(Body body) {
        this.body = body;
    }
}
