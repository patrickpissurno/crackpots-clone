package com.patrickpissurno.crackpots;

import javax.swing.*;

public class RedRound extends Round{
    @Override
    protected ImageIcon getLifeIcon() {
        return new ImageIcon("life_red.png");
    }

    @Override
    protected Round getNextRound() {
        return new BlackRound();
    }

    @Override
    protected Spider getNewSpider() {
        return new RedSpider();
    }
}
