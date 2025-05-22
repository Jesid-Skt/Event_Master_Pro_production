package Ui;

import javax.swing.*;

public class pruebasGUIS {
    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("Main Menu");
        MainMenuGui mainMenuGui = new MainMenuGui();
        frame.setContentPane(mainMenuGui.getPanelPrincipalMainMenu());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
