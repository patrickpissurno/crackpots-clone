package com.patrickpissurno.crackpots;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String args[]){
        System.out.println("Works");
        javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Crackpots");
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("//TODO: everything");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(label);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
