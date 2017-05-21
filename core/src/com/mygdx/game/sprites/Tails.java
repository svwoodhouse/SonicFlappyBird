package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Vector;

/**
 * Created by Sydnee on 5/20/2017.
 */

public class Tails
{
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation tailsAnimation;
    private Texture texture;

    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;

    public Tails(int x, int y){
        position = new Vector3(x, y,0);
        velocity = new Vector3(0,0,0);
        texture = new Texture("Tails-SonicAdvance1.gif");
        tailsAnimation = new Animation(new TextureRegion(texture,200,170,310,40),8,0.5f);

        bounds = new Rectangle(x,y,tailsAnimation.frameWidth / 8,tailsAnimation.frameHeight);
    }

    public void update(float dt)
    {
        tailsAnimation.update(dt);
        if(position.y > 0)
            velocity.add(0, GRAVITY, 0);

        velocity.scl(dt);
        position.add(0,velocity.y,0);
        position.add(MOVEMENT * dt, velocity.y, 0);
        if(position.y < 0)
            position.y = 0;

        velocity.scl(1/dt);
        bounds.setPosition(position.x,position.y);
    }

    public TextureRegion getTexture() {
        return tailsAnimation.getFrame();
    }

    public Vector3 getPosition() {
        return position;
    }

    public void jump(){
        velocity.y = 250;
    }

    public Rectangle getBounds()
    {
        return bounds;
    }

    public void dispose(){texture.dispose();}
}
