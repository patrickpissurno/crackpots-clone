package com.patrickpissurno.crackpots;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.util.ArrayList;

public class Game {
    public static final int LAYER_UI = 10000;

    private JLayeredPane panel;
    private ArrayList<Pair<IGameObject, JLabel>> gameObjects;
    private Round round;
    private Player player;

    private int score;
    private int roundCount;
    private JLabel scoreText;
    private boolean gameOver;
    private int gameOverTimer;

    public Game(JLayeredPane panel){
        init(panel);
    }

    private void init(JLayeredPane panel){
        score = 0;
        roundCount = 0;
        gameOver = false;
        gameOverTimer = 360;

        this.panel = panel;
        gameObjects = new ArrayList<>();
        nextRound(new BlackRound());

        final JLabel bg = new JLabel(new ImageIcon("bg.png"));
        bg.setBounds(0, 0, 640, 420);
        panel.add(bg, new Integer(1));

        scoreText = new JLabel("0");
        scoreText.setForeground(Color.WHITE);
        scoreText.setBounds(0,0, 400, 40);
        scoreText.setLocation(5, 11);


        try {
            final Font font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("PressStart2P.ttf"));
            scoreText.setFont(font.deriveFont(Font.PLAIN, 20));
        }
        catch(Exception ex) {
            scoreText.setFont(new Font(scoreText.getFont().getFontName(), Font.PLAIN, 24));
        }

        scoreText.setHorizontalAlignment(SwingUtilities.RIGHT);
        panel.add(scoreText, new Integer(LAYER_UI));

        player = new Player();
        instantiate(player);
        round.onCreate(this);
    }

    public void onUpdate(){
        if(round != null && !round.isCreated())
            round.onCreate(this);

        for(Pair<IGameObject, JLabel> obj : gameObjects) {
            if(gameOver && obj.getKey() instanceof Player)
                continue;

            obj.getKey().onUpdate(this);
        }

        if(round != null)
            round.onUpdate(this);

        if(gameOver) {
            if (gameOverTimer % 180 == 0)
                scoreText.setText("Game Over");
            else if (gameOverTimer % 90 == 0)
                scoreText.setText(score + "");

            gameOverTimer -= 1;
        }

        if(gameOverTimer <= 0)
            restart();
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

    public void addScore(int score){
        this.score += score;
        scoreText.setText(this.score + "");
    }

    public void addSpiderScore(int score){
        addScore(score * (1 + (int)Math.floor((roundCount + 0.001) / Round.ROUND_TYPE_COUNT)));
    }

    public void addUI(JLabel elem){
        panel.add(elem, new Integer(LAYER_UI));
    }

    public void removeUI(JLabel elem){
        panel.remove(panel.getIndexOf(elem));
    }

    public void nextRound(Round round){
        if(this.round != null) {
            this.round.onDestroy(this);
            roundCount += 1;
        }
        this.round = round;
    }

    public void onGameOver(){
        gameOver = true;
    }

    public boolean isGameOver(){
        return gameOver;
    }

    private void restart(){
        panel.removeAll();
        init(panel);
    }
}
