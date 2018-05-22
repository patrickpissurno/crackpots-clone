package com.patrickpissurno.crackpots;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Player implements IGameObject{
    private JLabel label;
    private int x;
    private int y;
    private int hspeed;

    @Override
    public JLabel onCreate(Game game) {
        label = new JLabel(new ImageIcon("player.png"));
        x = 320 - 14;
        y = 44;
        hspeed = 0;

        label.setBounds(x, y, 28, 40);
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
}
