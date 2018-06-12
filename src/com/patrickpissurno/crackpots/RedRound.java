package com.patrickpissurno.crackpots;

import javax.swing.*;

public class RedRound extends Round{
    @Override
    protected ImageIcon getLifeIcon() {
        return new ImageIcon(Utils.getResource("life_red.png"));
    }

    @Override
    protected Round getNextRound() {
        return new GreenRound();
    }

    @Override
    protected Spider getNewSpider() {
        return new RedSpider();
    }
}
