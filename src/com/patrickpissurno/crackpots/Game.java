package com.patrickpissurno.crackpots;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private JPanel panel;
    private List<IGameObject> gameObjects;

    public Game(JPanel panel){
        this.panel = panel;
        gameObjects = new ArrayList<>();

        instantiate(new Player());
    }

    public void onUpdate(){
        for(IGameObject obj : gameObjects)
            obj.onUpdate(this);
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
