package com.patrickpissurno.crackpots;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private JPanel panel;
    private List<IGameObject> gameObjects;
    private Round round;

    public Game(JPanel panel){
        this.panel = panel;
        gameObjects = new ArrayList<>();
        round = new Round();

        instantiate(new Player());
        round.onCreate(this);

        final JLabel bg = new JLabel(new ImageIcon("bg.png"));
        bg.setBounds(0, 0, 640, 420);
        panel.add(bg);
    }

    public void onUpdate(){
        for(IGameObject obj : gameObjects)
            obj.onUpdate(this);
        round.onUpdate(this);
    }

    public void onKeyPressed(KeyEvent e){
        for(IGameObject obj : gameObjects)
            obj.onKeyPressed(this, e);
    }

    public void onKeyReleased(KeyEvent e){
        for(IGameObject obj : gameObjects)
            obj.onKeyReleased(this, e);
    }

    public void instantiate(IGameObject gameObject){
        gameObjects.add(gameObject);
        panel.add(gameObject.onCreate(this));
    }
}
