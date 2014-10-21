package com.rosenberg.plattform.window;

import javax.swing.*;
import java.awt.*;


public class Window {

    //Fields


    //Constructor
    public Window(int w, int h, String title, Game game){
        game.setPreferredSize(new Dimension(w,h));
        game.setMaximumSize(new Dimension(w, h));
        game.setMinimumSize(new Dimension(w, h));

        JFrame frame = new JFrame(title);

        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }

}
