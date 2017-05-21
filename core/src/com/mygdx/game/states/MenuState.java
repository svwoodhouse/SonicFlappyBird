package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SonicFlappyBird;

/**
 * Created by Sydnee on 5/20/2017.
 */

public class MenuState extends State{
    private Texture background;
    private Texture playButton;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("Wiki-background.jpg");
        playButton = new Texture("start_game.png");
    }

    public void handleInput() {
        if(Gdx.input.justTouched())
        {
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0, SonicFlappyBird.WIDTH, SonicFlappyBird.HEIGHT);
        sb.draw(playButton,(SonicFlappyBird.WIDTH / 2)- (playButton.getWidth() / 2),SonicFlappyBird.HEIGHT /2);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }
}
