package Ui;

import Services.AccessControlService;

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
    private AccessControlService accessControlService;




    public ManageAccessControlGui() {
        accessControlService = new AccessControlService();
        registerNewAttendeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventId = JOptionPane.showInputDialog("Enter Event ID:");
                String attendeeId = JOptionPane.showInputDialog("Enter Attendee ID:");
                String name = JOptionPane.showInputDialog("Enter Name:");
                String email = JOptionPane.showInputDialog("Enter Email:");

                accessControlService.registerNewAttendee(eventId, attendeeId, name, email); // 👈 se usa aquí
                JOptionPane.showMessageDialog(null, "Attendee registered successfully!");
            }
        });



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
        validateAttendeeEntryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventId = JOptionPane.showInputDialog("Enter Event ID:");
                String attendeeId = JOptionPane.showInputDialog("Enter Attendee ID:");

                boolean valid = accessControlService.validateAttendeeEntry(eventId, attendeeId);

                if (valid) {
                    JOptionPane.showMessageDialog(null, "✅ Entry validated. Welcome!");
                } else {
                    JOptionPane.showMessageDialog(null, "❌ Invalid entry or attendee already checked in.");
                }
            }
        });
        showAttendanceStatisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventId = JOptionPane.showInputDialog("Ingrese el ID del evento:");
                String attendeesList = accessControlService.getAllAttendeesAsString(eventId);
                JOptionPane.showMessageDialog(null, attendeesList, "Lista de Asistentes", JOptionPane.INFORMATION_MESSAGE);
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
