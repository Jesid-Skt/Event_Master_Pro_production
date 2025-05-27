package Ui.GuiPackageAccessControl;

import DTOS.AttendeeDTO;
import DTOS.EventDTO;
import Services.AccessControlService;
import Services.AttendeeService;
import Services.TicketService;
import DTOS.TicketDTO;
import Ui.GuiPackageAccessControl.GuiPackageFormularioAccessControl.GuiFormularioAccessControl;
import Ui.GuiPackageMainMenu.GuiMainMenu;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.List;

import static java.awt.Color.black;
import static java.awt.Color.white;

public class GuiAccessControl {
    private JPanel PanelPrincipalAccessControl;
    private JPanel JpanelTituloPrincipal;
    private JLabel eventMasterProMenuLabel;
    private JPanel JPanelMenuEvent;
    private JButton exitButton;
    private JButton backToMainMenuButton;
    private JButton showAttendanceStatisticsButton;
    private JButton validateAttendeeEntryButton;
    private JButton registerNewAttendeeButton;
    private AccessControlService accessControlService;

    public GuiAccessControl() {
        accessControlService = new AccessControlService();
        TicketService ticketService = new TicketService();

        registerNewAttendeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                GuiFormularioAccessControl formulario = new GuiFormularioAccessControl();
                JFrame frame = new JFrame("Formulario Control de Acceso");
                frame.setContentPane(formulario.$$$getRootComponent$$$());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(600, 600); // ancho: 600, alto: 400
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setVisible(true);
                // Dentro del ActionListener del botón de validar ticket
                String codigoTicket = TicketService.generateTicketCode();
                String idAttendee = AttendeeService.generateAttendeeID();
                TicketDTO ticket = ticketService.getTicketByCode(codigoTicket);
                if (ticket == null) {
                    formulario.getLabelEstadoVenta().setText("En proceso");
                    formulario.getLabelEstadoUso().setText("No usado");
                } else {
                    formulario.getLabelEstadoVenta().setText(ticket.isSold() ? "Comprado" : "En proceso");
                    formulario.getLabelEstadoUso().setText(ticket.isUsed() ? "Usado" : "No usado");

                }
                formulario.getLabelTicketCode().setText(codigoTicket);
                formulario.getTextFieldIDattendee().setText(idAttendee);
                formulario.getPanelShowAttendee().setVisible(false);
                formulario.getLabelIDAttendee().setVisible(false);
                formulario.getShowAttendanceStatisticsButton().setVisible(false);
                formulario. getValidateTicketButton().setVisible(false);
                formulario.getLabelTituloPrincipal().setText("Registre new attendee Form");

            }
        });
        validateAttendeeEntryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiFormularioAccessControl formulario = new GuiFormularioAccessControl();
                JFrame frame = new JFrame("Formulario Control de Acceso");
                frame.setContentPane(formulario.getPanelPrincipalFormularioAccessControl());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setVisible(true);
                formulario.getPanelestadodeuso().setVisible(false);
                formulario.getPanelestodeventa().setVisible(false);
                formulario.getPanelcomboboxTicket().setVisible(false);
                formulario.getPanelComboboxevento().setVisible(false);
                formulario.getRegistreNewAttendeeButton().setVisible(false);
                formulario.getShowAttendanceStatisticsButton().setVisible(false);
                formulario.getPanelShowAttendee().setVisible(false);
                formulario.getPanelEmail().setVisible(false);
                formulario.getPanelNombreAttendee().setVisible(false);
                formulario.getPanelcodigoTicket().setVisible(false);
                formulario.getTextFieldIDattendee().setBackground(Color.white);
                formulario.getValidateTicketButton().setForeground(Color.white);
                formulario.getLabelTituloPrincipal().setText("Validate Attendee Entry");
            }
                });
        showAttendanceStatisticsButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GuiFormularioAccessControl formulario = new GuiFormularioAccessControl();
                        JFrame frame = new JFrame("Formulario Control de Acceso");
                        frame.setContentPane(formulario.getPanelPrincipalFormularioAccessControl());
                        formulario.getPanelPrincipalTituloFormulario().setVisible(true);
                        formulario.getLabelTituloPrincipal().setText("Show Attendance Statistics");
                        // Oculta los paneles innecesarios
                        formulario.getPanelcodigoTicket().setVisible(false);
                        formulario.getPanelComboboxevento().setVisible(false);
                        formulario.getPanelcomboboxTicket().setVisible(false);
                        formulario.getShowAttendanceStatisticsButton().setVisible(false);
                        formulario.getRegistreNewAttendeeButton().setVisible(false);
                        formulario.getPanelestodeventa().setVisible(false);
                        formulario.getPanelestadodeuso().setVisible(false);
                        formulario.getPanelEmail().setVisible(false);
                        formulario.getPanelNombreAttendee().setVisible(false);
                        formulario.getPanelIDattendee().setVisible(false);
                        formulario.getValidateTicketButton().setVisible(false);
                        formulario.getLabelShowAllAttendee().setForeground(Color.white);


                        // Obtener y mostrar los attendees
                        AttendeeService attendeeService = new AttendeeService();


                      // Supón que tienes un JComboBox llamado comboBoxEventos en tu formulario
                        EventDTO eventoSeleccionado = (EventDTO) formulario.getComboBoxListaEvento().getSelectedItem();
                        String eventId = (eventoSeleccionado != null) ? eventoSeleccionado.getEventId() : null;
                       List<AttendeeDTO> attendees = attendeeService.getAllAttendees(null)
                            .stream()
                            .map(att -> new AttendeeDTO(att)) // Ajusta el constructor si es necesario
                            .collect(java.util.stream.Collectors.toList());
                        StringBuilder sb = new StringBuilder();
                        for (AttendeeDTO attendee : attendees) {
                            sb.append(attendee.toString()).append("\n");
                        }
                        formulario.getLabelShowAllAttendee().setText("<html>" + sb.toString().replaceAll("\n", "<br>") + "</html>");

                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setResizable(false);
                        frame.setVisible(true);
                    }
                });
        backToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelPrincipalAccessControl);
                currentFrame.dispose(); // Cierra la ventana actual

                // Crea una nueva instancia de la ventana principal
                GuiMainMenu guiMainMenu = new GuiMainMenu();
                JFrame frame = new JFrame("Main Menu");
                frame.setContentPane(guiMainMenu.getPanelPrincipalMainMenu());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setVisible(true);

            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelPrincipalAccessControl);
                currentFrame.dispose(); // Cierra la ventana actual

                JFrame frame = new JFrame("Exit");
                JOptionPane.showMessageDialog(frame, "Thank you for using Event Master Pro!", "Exit", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0); // Cierra la aplicación

            }
        });
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelPrincipalAccessControl = new JPanel();
        PanelPrincipalAccessControl.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        PanelPrincipalAccessControl.setBackground(new Color(-15591660));
        panel1.add(PanelPrincipalAccessControl, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        JpanelTituloPrincipal = new JPanel();
        JpanelTituloPrincipal.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        JpanelTituloPrincipal.setBackground(new Color(-15591660));
        PanelPrincipalAccessControl.add(JpanelTituloPrincipal, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        eventMasterProMenuLabel = new JLabel();
        Font eventMasterProMenuLabelFont = this.$$$getFont$$$("Roboto Light", Font.BOLD, 28, eventMasterProMenuLabel.getFont());
        if (eventMasterProMenuLabelFont != null) eventMasterProMenuLabel.setFont(eventMasterProMenuLabelFont);
        eventMasterProMenuLabel.setForeground(new Color(-330753));
        eventMasterProMenuLabel.setText("Event Master Pro ");
        JpanelTituloPrincipal.add(eventMasterProMenuLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        JpanelTituloPrincipal.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        JPanelMenuEvent = new JPanel();
        JPanelMenuEvent.setLayout(new GridLayoutManager(8, 1, new Insets(0, 0, 0, 0), -1, -1));
        JPanelMenuEvent.setBackground(new Color(-15591660));
        PanelPrincipalAccessControl.add(JPanelMenuEvent, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(207, 298), null, 0, false));
        exitButton = new JButton();
        exitButton.setBackground(new Color(-14829228));
        exitButton.setEnabled(true);
        exitButton.setForeground(new Color(-330753));
        exitButton.setText("Exit");
        JPanelMenuEvent.add(exitButton, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        backToMainMenuButton = new JButton();
        backToMainMenuButton.setBackground(new Color(-14829228));
        backToMainMenuButton.setEnabled(true);
        backToMainMenuButton.setForeground(new Color(-330753));
        backToMainMenuButton.setText(" Back to Main Menu");
        JPanelMenuEvent.add(backToMainMenuButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        showAttendanceStatisticsButton = new JButton();
        showAttendanceStatisticsButton.setBackground(new Color(-14829228));
        showAttendanceStatisticsButton.setEnabled(true);
        showAttendanceStatisticsButton.setForeground(new Color(-330753));
        showAttendanceStatisticsButton.setText("show Attendance Statistics");
        JPanelMenuEvent.add(showAttendanceStatisticsButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        validateAttendeeEntryButton = new JButton();
        validateAttendeeEntryButton.setBackground(new Color(-14829228));
        validateAttendeeEntryButton.setEnabled(true);
        validateAttendeeEntryButton.setForeground(new Color(-330753));
        validateAttendeeEntryButton.setText("validate Attendee Entry");
        JPanelMenuEvent.add(validateAttendeeEntryButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        registerNewAttendeeButton = new JButton();
        registerNewAttendeeButton.setBackground(new Color(-14829228));
        registerNewAttendeeButton.setEnabled(true);
        registerNewAttendeeButton.setForeground(new Color(-330753));
        registerNewAttendeeButton.setText("register New Attendee");
        JPanelMenuEvent.add(registerNewAttendeeButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        JPanelMenuEvent.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        JPanelMenuEvent.add(spacer3, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        JPanelMenuEvent.add(spacer4, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-15591660));
        PanelPrincipalAccessControl.add(panel2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setBackground(new Color(-15591660));
        label1.setEnabled(true);
        Font label1Font = this.$$$getFont$$$(null, -1, 72, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-330753));
        label1.setText("Welcome To Manage Access Control");
        panel2.add(label1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel2.add(spacer5, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        panel2.add(spacer6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }


    public JPanel getPanelPrincipalAccessControl() {
        return PanelPrincipalAccessControl;
    }
}
