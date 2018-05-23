package com.patrickpissurno.crackpots;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private int lives;
    private int remainingEnemies;
    private int spawnTimer;

    private List<Pote> potes;
    private List<Spider> spiders;

    public void onCreate(Game game){
        lives = 6;
        remainingEnemies = 12;
        spawnTimer = 60;

        potes = new ArrayList<>();
        spiders = new ArrayList<>();

        for(int i = 0; i<6; i++){
            final Pote pote = new Pote();
            pote.setTargetWindow(i);
            game.instantiate(pote);
            potes.add(pote);
        }
    }

    public void onUpdate(Game game){
        checkCollisions(game);

        spawnTimer -= 1;
        if(spawnTimer <= 0 && remainingEnemies > 0)
        {
            final BlackSpider spider = new BlackSpider();
            spider.setTargetWindow(Utils.randomRange(0, 5));

            game.instantiate(spider);
            spiders.add(spider);

            spawnTimer = 120;
            remainingEnemies -= 1;
        }
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
}
