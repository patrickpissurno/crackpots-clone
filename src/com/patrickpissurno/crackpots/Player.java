package com.patrickpissurno.crackpots;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player implements IGameObject, ICollider{
    private JLabel label;
    private int x;
    private int y;
    private int hspeed;

    private final int width = 28;
    private final int height = 48;

    @Override
    public JLabel onCreate(Game game) {
        label = new JLabel(new ImageIcon(Utils.getResource("player.png")));
        x = 320 - width/2;
        y = 40;
        hspeed = 0;

        label.setBounds(x, y, width, height);
        label.setLocation(x, y);
        return label;
    }

    @Override
    public void onUpdate(Game game) {
        x += hspeed;
        label.setLocation(x, y);
    }

    @Override
    public void onDestroy(Game game) {

    }

    @Override
    public void onKeyPressed(Game game, KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
            hspeed = -4;
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            hspeed = 4;
    }

    @Override
    public void onKeyReleased(Game game, KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)
            hspeed = 0;
    }

    @Override
    public boolean isDestroyed() {
        return false;
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
