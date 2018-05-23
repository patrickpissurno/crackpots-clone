package com.patrickpissurno.crackpots;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {
    public static void main(String args[]){
        javax.swing.SwingUtilities.invokeLater(() -> init());
    }

    private static void init(){
        JFrame frame = new JFrame("Crackpots");
        frame.setMinimumSize(new Dimension(640, 420));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLayeredPane panel = new JLayeredPane();
        frame.setContentPane(panel);
        panel.setLayout(null);

        //Display the window
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        final Game game = new Game(panel);

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                game.onKeyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                game.onKeyReleased(e);
            }
        });

        //Set game loop
        new Timer( 1000/60, a -> game.onUpdate()).start();
    }
}
