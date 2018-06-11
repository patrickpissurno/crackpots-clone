package com.patrickpissurno.crackpots;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class Round {
    public static final int ROUND_TYPE_COUNT = 4;
    private boolean created = false;

    private int remainingEnemies;
    private int spawnTimer;
    private int animateRoundEndTimer;

    private List<Pote> potes;
    private List<Spider> spiders;

    private Stack<JLabel> lives;

    public void onCreate(Game game){
        remainingEnemies = 12;
        spawnTimer = 60;
        animateRoundEndTimer = 60;

        lives = new Stack<>();
        final ImageIcon lifeIcon = getLifeIcon();
        for(int i = 0; i<6; i++){
            final JLabel life = new JLabel(lifeIcon);
            life.setBounds(0, 0, 24, 14);
            life.setLocation(220 + i * 32, 352);
            game.addUI(life);
            lives.push(life);
        }

        potes = new ArrayList<>();
        spiders = new ArrayList<>();

        for(int i = 0; i<6; i++){
            final Pote pote = new Pote();
            pote.setTargetWindow(i);
            game.instantiate(pote);
            potes.add(pote);
        }

        created = true;
    }

    public void onUpdate(Game game){
        if(!created)
            return;

        checkCollisions(game);

        spawnTimer -= 1;
        if(spawnTimer <= 0 && remainingEnemies > 0)
        {
            final Spider spider = getNewSpider();
            spider.setTargetWindow(Utils.randomRange(0, 5));
            spider.setAttackedListener(() -> removeLife(game));

            game.instantiate(spider);
            spiders.add(spider);

            spawnTimer = 120;
            remainingEnemies -= 1;
        }

        if(remainingEnemies == 0 && spiders.isEmpty())
            animateRoundEnd(game);
    }

    private void animateRoundEnd(Game game){
        animateRoundEndTimer -= 1;
        if(animateRoundEndTimer <= 0 && !lives.empty())
        {
            removeLife(game);
            animateRoundEndTimer = 30;
            game.addScore(200);
        }
        if(lives.empty())
            game.nextRound(getNextRound());
    }

    public void onDestroy(Game game){
        for(Pote p : potes)
            game.destroy(p);
        for(Spider s : spiders)
            game.destroy(s);
    }

    private void checkCollisions(Game game){
        boolean removed = false;

        for(Pote p : potes){
            for(Spider s : spiders)
            {
                if(!s.isDestroyed() && p.isColliding(s.getBoundingBox()))
                    game.destroy(s);

                if(s.isDestroyed())
                    removed = true;
            }
        }

        if(removed){
            final List<Spider> arr = new ArrayList<>();
            for(Spider s : spiders)
                if(!s.isDestroyed())
                    arr.add(s);
            spiders = arr;
        }
    }

    private void removeLife(Game game){
        if(!lives.empty()) {
            final JLabel life = lives.pop();
            life.setVisible(false);
            game.removeUI(life);
        }
    }

    public boolean isCreated(){
        return created;
    }

    protected abstract ImageIcon getLifeIcon();
    protected abstract Round getNextRound();
    protected abstract Spider getNewSpider();
}
