package com.patrickpissurno.crackpots;

import javax.swing.*;

public class GreenRound extends Round{
    @Override
    protected ImageIcon getLifeIcon() {
        return new ImageIcon(Utils.getResource("life_green.png"));
    }

    @Override
    protected Round getNextRound() {
        return new BlackRound();
    }

    @Override
    protected Spider getNewSpider() {
        return new GreenSpider();
    }
}
