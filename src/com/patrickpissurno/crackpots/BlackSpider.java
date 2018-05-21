package com.patrickpissurno.crackpots;

import javax.swing.*;

public class BlackSpider extends Spider {
    @Override
    protected String getSpritePath() {
        return "sprite.png";
    }

    @Override
    public JLabel onCreate(Game game){
        final JLabel label = super.onCreate(game);
        vspeed = -4;
        return label;
    }
}
