package com.patrickpissurno.crackpots;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Pote implements IGameObject, ICollider{
    private static final int WINDOW_BASE_POSITION = 128;
    private static final int WINDOW_BASE_OFFSET = 64;

    private static final int DEFAULT_Y = 88;

    private JLabel label;
    private int x;
    private int y;
    private float vspeed;

    private final int width = 28;
    private final int height = 20;

    @Override
    public JLabel onCreate(Game game) {
        label = new JLabel(new ImageIcon("pote.png"));

        y = DEFAULT_Y;
        vspeed = 0;

        label.setBounds(x, y, width, height);
        label.setLocation(x, y);
        return label;
    }

    @Override
    public void onUpdate(Game game) {

        if(vspeed != 0){
            if(vspeed < 6)
                vspeed += 0.1;
            else
                vspeed = 6;
        }

        y += vspeed;

        if(y > 300) {
            y = DEFAULT_Y;
            vspeed = 0;
            SoundController.Play(SoundController.POT_CRACK);
        }

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
        if(e.getKeyCode() != KeyEvent.VK_SPACE)
            return;
        if(vspeed != 0 || y != DEFAULT_Y)
            return;

        final Rectangle p = game.getPlayerCollider().getBoundingBox();
        if(p.getX() + p.getWidth() > x && p.getX() < x + width)
            doThrow();
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    public void setTargetWindow(int id){
        x = WINDOW_BASE_POSITION + id * WINDOW_BASE_OFFSET;
    }

    private void doThrow(){
        vspeed = 2;
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
}
