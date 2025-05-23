package Ui;

import Ui.GuiPackageMainMenu.GuiMainMenu;

import javax.swing.*;

public class PruebasGUIS {
    public static void main(String[] args) {
        try {
            // Create a JFrame
            JFrame frame = new JFrame("Main Menu");
            // Create an instance of MainMenuGui
            GuiMainMenu guiMainMenu = new GuiMainMenu();
            frame.setContentPane(guiMainMenu.getPanelPrincipalMainMenu());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al crear la ventana");
        }
    }
}
