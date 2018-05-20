package com.patrickpissurno.crackpots;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String args[]){
        System.out.println("Works");
        javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static int posX = 0;
    private static int posY = 0;

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Crackpots");
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = (JPanel) frame.getContentPane();
        panel.setLayout(null);

        JLabel label = new JLabel(new ImageIcon("sprite.png"));

        label.setLocation(posX, posY);

        Timer timer = new Timer( 100, a -> {
            posX += 2;
            posY += 2;
            label.setLocation(posX, posY);
        });
        timer.start();

        label.setBounds(100, 100, 32, 32);

        panel.add(label);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
