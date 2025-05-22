package Ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageAccessControlGui {
    private JPanel JpanelTituloPrincipal;
    private JLabel eventMasterProMenuLabel;
    private JPanel JPanelMenuEvent;
    private JButton exitButton;
    private JButton backToMainMenuButton;
    private JButton showAttendanceStatisticsButton;
    private JButton validateAttendeeEntryButton;
    private JButton registerNewAttendeeButton;
    private JPanel PnaelPrincipalAccessControl;


    public ManageAccessControlGui() {
        backToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(PnaelPrincipalAccessControl);
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
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(PnaelPrincipalAccessControl);
                currentFrame.dispose(); // Cierra la ventana actual

                JFrame frame = new JFrame("Exit");
                JOptionPane.showMessageDialog(frame, "Thank you for using Event Master Pro!", "Exit", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0); // Cierra la aplicación

            }
        });
    }

    public JPanel getPanelPrincipalAccessControl() {
        return PnaelPrincipalAccessControl;
    }
}
