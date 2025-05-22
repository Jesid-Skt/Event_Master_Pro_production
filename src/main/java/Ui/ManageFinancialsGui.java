package Ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageFinancialsGui {
    private JPanel JpanelTituloPrincipal;
    private JLabel eventMasterProMenuLabel;
    private JPanel JPanelMenuFinancials;
    private JButton exitButton;
    private JButton backToMainMenuButton;
    private JButton viewFinancialSummaryButton;
    private JButton viewExpensesButton;
    private JButton viewIncomeButton;
    private JPanel PanelPrincipalFinancials;

    public ManageFinancialsGui() {
        backToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelPrincipalFinancials);
                currentFrame.dispose(); // Cierra la ventana actual

                MainMenuGui mainMenuGui = new MainMenuGui();
                JFrame frame = new JFrame("Main Menu");
                frame.setContentPane(mainMenuGui.getPanelPrincipalMainMenu());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelPrincipalFinancials);
                currentFrame.dispose(); // Cierra la ventana actual

                JFrame frame = new JFrame("Exit");
                JOptionPane.showMessageDialog(frame, "Thank you for using Event Master Pro!", "Exit", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0); // Cierra la aplicación

            }
        });
    }

    public JPanel getPanelPrincipalFinancials() {
        return PanelPrincipalFinancials;
    }
}
