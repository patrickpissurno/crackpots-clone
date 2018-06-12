package com.patrickpissurno.crackpots;

import javax.swing.*;

public class BlueRound extends Round{
    @Override
    protected ImageIcon getLifeIcon() {
        return new ImageIcon(Utils.getResource("life_blue.png"));
    }

    @Override
    protected Round getNextRound() {
        return new RedRound();
    }

    @Override
    protected Spider getNewSpider() {
        return new BlueSpider();
    }
}
