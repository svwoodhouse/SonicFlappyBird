package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.SonicFlappyBird;
import com.mygdx.game.sprites.Tails;
import com.mygdx.game.sprites.Tube;

import java.util.Vector;

/**
 * Created by Sydnee on 5/20/2017.
 */

public class PlayState extends State
{
    private Tails tails;
    private Texture bg;
    private Vector2 bgPosition1, bgPosition2;

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        tails = new Tails(50,300);
        bg = new Texture("Wiki-background.jpg");
        bgPosition1 = new Vector2(cam.position.x-cam.viewportWidth / 2,0);
        bgPosition2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + bg.getWidth(),0);
        cam.setToOrtho(false, SonicFlappyBird.WIDTH / 2, SonicFlappyBird.HEIGHT / 2);

        tubes = new Array<Tube>();

        for(int i = 0; i < TUBE_COUNT; i++)
        {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override

    protected void handleInput() {
        if(Gdx.input.justTouched())
            tails.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        tails.update(dt);
        cam.position.x = tails.getPosition().x + 80;
        for(int i = 0; i < tubes.size; i++)
        {
            Tube tube = tubes.get(i);
            if(cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getRegionWidth())
            {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            if(tube.collide(tails.getBounds()))
                gsm.set(new PlayState(gsm));
        }
        if(tails.getPosition().y <= 0)
            gsm.set(new PlayState(gsm));
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg,bgPosition1.x,bgPosition1.y);
        sb.draw(bg,bgPosition2.x,bgPosition2.y);
        //sb.draw(bg,cam.position.x - (cam.viewportWidth / 2) , 0, SonicFlappyBird.WIDTH / 2 , SonicFlappyBird.HEIGHT / 2);
        sb.draw(tails.getTexture(),tails.getPosition().x,tails.getPosition().y);

        for(Tube tube : tubes)
        {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        tails.dispose();
        for(Tube tube : tubes)
            tube.dispose();
    }

    private void updateBG(){
        if(cam.position.x - (cam.viewportWidth / 2) > bgPosition1.x + bg.getWidth())
            bgPosition1.add(bg.getWidth() * 2, 0);

        if(cam.position.x - (cam.viewportWidth / 2) > bgPosition2.x + bg.getWidth())
            bgPosition2.add(bg.getWidth() * 2, 0);
    }
}
