package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.Random;
import java.util.Vector;

/**
 * Created by Sydnee on 5/20/2017.
 */

public class Tube {
    private TextureRegion topTube;
    private TextureRegion bottomTube;
    private Vector2 posTopTube, posBottomTube;
    private Random rand;
    private Rectangle boundsTop, boundsBottom;

    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 150;
    private static final int LOWEST_OPENING = 120;
    public static final int TUBE_WIDTH = 54;

    public Tube(float x){
        Texture topTexture = new Texture("flappyBird.png");
        topTube = new TextureRegion(topTexture,110,650,54,319);
        Texture bottomTexture = new Texture("flappyBird.png");
        bottomTube = new TextureRegion(bottomTexture,165,646,58,325);
        rand = new Random();

        posTopTube = new Vector2(x,rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottomTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getRegionHeight());

        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getRegionWidth(), topTube.getRegionHeight());
        boundsBottom = new Rectangle(posBottomTube.x, posBottomTube.y, bottomTube.getRegionWidth(), topTube.getRegionHeight());
    }
    public TextureRegion getTopTube() {
        return topTube;
    }

    public TextureRegion getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }

    public void reposition(float x){
        posTopTube.set(x,rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottomTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getRegionHeight());

        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBottom.setPosition(posBottomTube.x,posBottomTube.y);
    }

    public boolean collide(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBottom);
    }

    public void dispose()
    {
        bottomTube.getTexture().dispose();
        topTube.getTexture().dispose();
    }

}
