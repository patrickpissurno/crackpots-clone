package com.patrickpissurno.crackpots;

import javax.swing.*;
import java.awt.event.KeyEvent;

public abstract class Spider implements IGameObject{
    private JLabel label;
    private int x;
    private int y;
    protected int hspeed;
    protected int vspeed;

    @Override
    public JLabel onCreate(Game game) {
        label = new JLabel(new ImageIcon(getSpritePath()));
        x = 0;
        y = 420 - 32;
        hspeed = 0;
        vspeed = 0;

        label.setBounds(x, y, 32, 32);
        label.setLocation(x, y);
        return label;
    }

    protected abstract String getSpritePath();

    @Override
    public void onUpdate(Game game) {
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
}
