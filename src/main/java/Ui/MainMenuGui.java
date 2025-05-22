package Ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuGui {
    private JPanel PanelPrincipalMainMenu;
    private JLabel eventMasterProMenuLabel;
    private JButton exitButton;
    private JButton manageAccessControlButton;
    private JButton manageTicketButton;
    private JButton manageFinancialsButton;
    private JButton manageVenueButton;
    private JButton manageArtistButton;
    private JButton manageEventButton;
    private JPanel JpanelTituloPrincipal;
    private JPanel JPanelMenu;


    public MainMenuGui() {
        manageEventButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelPrincipalMainMenu);
                currentFrame.dispose(); // Cierra la ventana actual

                ManageEventGui manageEventGui = new ManageEventGui();
                JFrame frame = new JFrame("Manage Event");
                frame.setContentPane(manageEventGui.getPanelPrincipalEvent());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

            }
        });
        manageArtistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelPrincipalMainMenu);
                currentFrame.dispose(); // Cierra la ventana actual

                ManageArtistGui manageArtistGui = new ManageArtistGui();
                JFrame frame = new JFrame("Manage Artist");
                frame.setContentPane(manageArtistGui.getPanelPrincipalArtist());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);


            }
        });
        manageVenueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelPrincipalMainMenu);
                currentFrame.dispose(); // Cierra la ventana actual

                ManageVenueGui manageVenueGui = new ManageVenueGui();
                JFrame frame = new JFrame("Manage Venue");
                frame.setContentPane(manageVenueGui.getPanelPrincipalVenue());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

            }
        });
        manageFinancialsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelPrincipalMainMenu);
                currentFrame.dispose(); // Cierra la ventana actual

                ManageFinancialsGui manageFinancialsGui = new ManageFinancialsGui();
                JFrame frame = new JFrame("Manage Financials");
                frame.setContentPane(manageFinancialsGui.getPanelPrincipalFinancials());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

            }
        });
        manageTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelPrincipalMainMenu);
                currentFrame.dispose(); // Cierra la ventana actual

                ManageTicketGui manageTicketGui = new ManageTicketGui();
                JFrame frame = new JFrame("Manage Ticket");
                frame.setContentPane(manageTicketGui.getPanelPrincipalTicket());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

            }
        });
        manageAccessControlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelPrincipalMainMenu);
                currentFrame.dispose(); // Cierra la ventana actual

                ManageAccessControlGui manageAccessControlGui = new ManageAccessControlGui();
                JFrame frame = new JFrame("Manage Access Control");
                frame.setContentPane(manageAccessControlGui.getPanelPrincipalAccessControl());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelPrincipalMainMenu);
                currentFrame.dispose(); // Cierra la ventana actual

                JFrame frame = new JFrame("Exit");
                JOptionPane.showMessageDialog(frame, "Thank you for using Event Master Pro!", "Exit", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0); // Cierra la aplicación

            }
        });
    }

    public JPanel getPanelPrincipalMainMenu() {
        return PanelPrincipalMainMenu;
    }


}