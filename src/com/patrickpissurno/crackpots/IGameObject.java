package com.patrickpissurno.crackpots;

import javax.swing.*;
import java.awt.event.KeyEvent;

public interface IGameObject {
    JLabel onCreate(Game game);
    void onUpdate(Game game);
    void onDestroy(Game game);
    void onKeyPressed(Game game, KeyEvent e);
    void onKeyReleased(Game game, KeyEvent e);
}
