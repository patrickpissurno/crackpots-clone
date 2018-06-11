package com.patrickpissurno.crackpots;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class Spider implements IGameObject, ICollider{
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
    protected boolean destroyed;

    private final int width = 32;
    private final int height = 32;

    private static AlphaIcon[] dyingWallSprites;
    private static AlphaIcon[] dyingGroundSprites;

    private Icon[] wallSprites;
    private Icon[] groundSprites;
    private Icon[] sprites;
    private int spriteType;

    private int animationClock;
    private int currentFrame;

    public final int getX(){
        return this.x;
    }

    public final int getY(){
        return this.y;
    }

    private Runnable attackedListener;
    protected boolean attacked;
    private boolean fadeInProgress;
    private float fadeAlpha;

    @Override
    public JLabel onCreate(Game game) {
        label = new JLabel();

        fadeInProgress = false;

        wallSprites = new ImageIcon[]{
                new ImageIcon(getSpritePrefix() + "_wall_1.png"),
                new ImageIcon(getSpritePrefix() + "_wall_2.png"),
        };
        groundSprites = new ImageIcon[]{
                new ImageIcon(getSpritePrefix() + "_ground_1.png"),
                new ImageIcon(getSpritePrefix() + "_ground_2.png"),
        };

        if(dyingWallSprites == null){
            dyingWallSprites = new AlphaIcon[]{
                    new AlphaIcon(new ImageIcon("spider_dying_wall_1.png"), 1f),
                    new AlphaIcon(new ImageIcon("spider_dying_wall_2.png"), 1f),
            };
            dyingGroundSprites = new AlphaIcon[]{
                    new AlphaIcon(new ImageIcon("spider_dying_ground_1.png"), 1f),
                    new AlphaIcon(new ImageIcon("spider_dying_ground_2.png"), 1f),
            };
        }

        setSprites(SPRITES_WALL);

        x = 128 + 32;
        y = 420 - 96;
        hspeed = 0;
        vspeed = 0;

        animationClock = 5;
        currentFrame = 0;

        destroyed = false;
        attacked = false;

        label.setBounds(x, y, width, height);
        label.setLocation(x, y);
        return label;
    }

    protected abstract String getSpritePrefix();

    @Override
    public void onUpdate(Game game) {

        if(y < 130 && !destroyed) {
            attacked = true;
            game.destroy(this);
            if(attackedListener != null)
                attackedListener.run();
            return;
        }

        animationClock -= 1;
        if(animationClock <= 0){
            animationClock = 5;
            currentFrame = currentFrame + 2 > sprites.length ? 0 : currentFrame + 1;
            label.setIcon(sprites[currentFrame]);
        }

        if(destroyed && fadeInProgress && sprites[currentFrame] instanceof AlphaIcon){
            fadeAlpha -= 0.05f;
            if(fadeAlpha <= 0) {
                fadeAlpha = 0;
                fadeInProgress = false;
                game.destroy(this);
            }

            final AlphaIcon currentIcon = (AlphaIcon)sprites[currentFrame];
            currentIcon.setAlpha(fadeAlpha);
        }

        x += hspeed;
        y += vspeed;
        label.setLocation(x, y);
    }

    public void destroyWithAnimation(Game game){
        destroyed = true;
        fadeInProgress = true;
        fadeAlpha = 1f;
        setSprites(spriteType);
    }

    @Override
    public void onDestroy(Game game) {
        destroyed = true;
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
        spriteType = type;

        if(type == SPRITES_WALL)
            sprites = fadeInProgress ? dyingWallSprites : wallSprites;
        else if(type == SPRITES_GROUND)
            sprites = fadeInProgress ? dyingGroundSprites : groundSprites;
    }

    @Override
    public boolean isColliding(Rectangle rectangle) {
        return x < rectangle.x + rectangle.width &&
                x + width > rectangle.x &&
                y < rectangle.y + rectangle.height &&
                height + y > rectangle.y;
    }

    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    public boolean isFadeInProgress(){
        return fadeInProgress;
    }

    public void setAttackedListener(Runnable listener){
        attackedListener = listener;
    }
}
