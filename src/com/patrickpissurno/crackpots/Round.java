package com.patrickpissurno.crackpots;

public class Round {
    private int lives;
    private int remainingEnemies;
    private int spawnTimer;

    public void onCreate(Game game){
        lives = 6;
        remainingEnemies = 6;
        spawnTimer = 60;
    }

    public void onUpdate(Game game){
        spawnTimer -= 1;
        if(spawnTimer <= 0 && remainingEnemies > 0)
        {
            final BlackSpider spider = new BlackSpider();
            spider.setTargetWindow(Utils.randomRange(0, 5));

            game.instantiate(spider);
            spawnTimer = 120;
            remainingEnemies -= 1;
        }
    }
}
