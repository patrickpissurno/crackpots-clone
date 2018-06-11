package com.patrickpissurno.crackpots;

import javax.swing.*;

public class GreenSpider extends Spider {
    private static final int STATE_FIRST = 0;
    private static final int STATE_SECOND = 1;
    private static final int STATE_THIRD = 2;

    private int AIState;
    private int AITimer;

    @Override
    protected String getSpritePrefix() {
        return "spider_green";
    }

    @Override
    public JLabel onCreate(Game game){
        final JLabel label = super.onCreate(game);

        AIState = STATE_FIRST;

        return label;
    }

    @Override
    public void onDestroy(Game game){
        super.onDestroy(game);
        if(!attacked)
            game.addSpiderScore(40);
    }

    @Override
    public void onUpdate(Game game){
        int targetX = WINDOW_BASE_POSITION + targetWindow * WINDOW_BASE_OFFSET;

        switch (AIState){
            case STATE_FIRST:
                if(y > 303)
                    vspeed = -1;
                else{
                    vspeed = 0;
                    AIState = STATE_SECOND;
                    setSprites(SPRITES_GROUND);
                }
                break;
            case STATE_SECOND:
                if(Math.abs(targetX - x) > 3)
                    hspeed = 3 * (int)Math.signum(targetX - x);
                else{
                    x = targetX;
                    setSprites(SPRITES_WALL);

                    AIState = STATE_THIRD;
                    hspeed = 1;
                    AITimer = 30;
                }
                break;
            case STATE_THIRD:
                vspeed = -1;

                AITimer -= 1;
                if(AITimer <= 0){
                    hspeed *= -1;
                    AITimer = 60;
                }
                break;
        }

        super.onUpdate(game);
    }
}
