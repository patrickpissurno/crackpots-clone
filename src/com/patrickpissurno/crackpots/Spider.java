package com.patrickpissurno.crackpots;

import javax.swing.*;
import java.awt.event.KeyEvent;

public abstract class Spider implements IGameObject{
    protected static final int WINDOW_BASE_POSITION = 128;
    protected static final int WINDOW_BASE_OFFSET = 64;

    protected static final int SPRITES_WALL = 0;
    protected static final int SPRITES_GROUND = 1;

    private JLabel label;
    protected int x;
    protected int y;
    protected int hspeed;
    protected int vspeed;
    protected int targetWindow;

    private ImageIcon[] wallSprites;
    private ImageIcon[] groundSprites;
    private ImageIcon[] sprites;

    private int animationClock;
    private int currentFrame;

    public final int getX(){
        return this.x;
    }

    public final int getY(){
        return this.y;
    }

    @Override
    public JLabel onCreate(Game game) {
        label = new JLabel();

        wallSprites = new ImageIcon[]{
                new ImageIcon(getSpritePrefix() + "_wall_1.png"),
                new ImageIcon(getSpritePrefix() + "_wall_2.png"),
        };
        groundSprites = new ImageIcon[]{
                new ImageIcon(getSpritePrefix() + "_ground_1.png"),
                new ImageIcon(getSpritePrefix() + "_ground_2.png"),
        };

        setSprites(SPRITES_WALL);

        x = 128 + 32;
        y = 420 - 96;
        hspeed = 0;
        vspeed = 0;

        animationClock = 5;
        currentFrame = 0;

        label.setBounds(x, y, 32, 32);
        label.setLocation(x, y);
        return label;
    }

    protected abstract String getSpritePrefix();

    @Override
    public void onUpdate(Game game) {

        if(y < 130) {
            game.destroy(this);
            return;
        }

        animationClock -= 1;
        if(animationClock <= 0){
            animationClock = 5;
            currentFrame = currentFrame + 2 > sprites.length ? 0 : currentFrame + 1;
            label.setIcon(sprites[currentFrame]);
        }

        x += hspeed;
        y += vspeed;
        label.setLocation(x, y);
    }

    @Override
    public void onDestroy(Game game) {

    }

    @Override
    public void onKeyPressed(Game game, KeyEvent e) {

    }

    @Override
    public void onKeyReleased(Game game, KeyEvent e) {

    }

    public void setTargetWindow(int id){
        targetWindow = id;
    }

    protected final void setSprites(int type){
        if(type == SPRITES_WALL)
            sprites = wallSprites;
        else if(type == SPRITES_GROUND)
            sprites = groundSprites;
    }
}
