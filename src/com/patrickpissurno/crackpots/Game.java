package com.patrickpissurno.crackpots;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Game {
    private JLayeredPane panel;
    private ArrayList<Pair<IGameObject, JLabel>> gameObjects;
    private Round round;
    private Player player;

    public Game(JLayeredPane panel){
        this.panel = panel;
        gameObjects = new ArrayList<>();
        round = new Round();

        final JLabel bg = new JLabel(new ImageIcon("bg.png"));
        bg.setBounds(0, 0, 640, 420);
        panel.add(bg, new Integer(1));

        player = new Player();
        instantiate(player);
        round.onCreate(this);
    }

    public void onUpdate(){
        for(Pair<IGameObject, JLabel> obj : gameObjects)
            obj.getKey().onUpdate(this);
        round.onUpdate(this);
    }

    public void onKeyPressed(KeyEvent e){
        for(Pair<IGameObject, JLabel> obj : gameObjects)
            obj.getKey().onKeyPressed(this, e);
    }

    public void onKeyReleased(KeyEvent e){
        for(Pair<IGameObject, JLabel> obj : gameObjects)
            obj.getKey().onKeyReleased(this, e);
    }

    public ICollider getPlayerCollider(){
        return player;
    }

    public void instantiate(IGameObject gameObject){
        final JLabel sprite = gameObject.onCreate(this);

        gameObjects.add(new Pair<>(gameObject, sprite));
        panel.add(sprite, new Integer(panel.getComponentCount() + 1));
    }

    public void destroy(IGameObject gameObject){
        final ArrayList<Pair<IGameObject, JLabel>> arr = new ArrayList<>(gameObjects);
        for(Pair<IGameObject, JLabel> obj : gameObjects)
        {
            if(obj.getKey().equals(gameObject)){
                obj.getKey().onDestroy(this);
                arr.remove(obj);
                obj.getValue().setVisible(false);
                panel.remove(panel.getIndexOf(obj.getValue()));
                break;
            }
        }
        gameObjects = arr;
    }
}
