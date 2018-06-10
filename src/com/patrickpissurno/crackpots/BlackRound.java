package com.patrickpissurno.crackpots;

import javax.swing.*;

public class BlackRound extends Round {

    @Override
    protected ImageIcon getLifeIcon() {
        return new ImageIcon("life_black.png");
    }

    @Override
    protected Round getNextRound() {
        return new BlackRound();
    }

    @Override
    protected Spider getNewSpider() {
        return new BlackSpider();
    }
}
