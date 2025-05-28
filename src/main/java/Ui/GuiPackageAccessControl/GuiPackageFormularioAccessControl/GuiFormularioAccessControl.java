package Ui.GuiPackageAccessControl.GuiPackageFormularioAccessControl;

import DTOS.EventDTO;
import Enums.TicketType;
import Repository.EventRepository;
import Repository.VenueRepository;
import Services.AccessControlService;
import Services.EventService;
import Services.VenueService;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;

public class GuiFormularioAccessControl {
    private JPanel PanelPrincipalFormularioAccessControl;
    private JPanel PanelPrincipalTituloFormulario;
    private JLabel LabelTicketCode;
    private JTextField textFieldNombreAttendee;
    private JPanel PanelcodigoTicket;
    private JPanel PanelNombreAttendee;
    private JComboBox<TicketType> comboBoxTipoTicket;
    private JComboBox<EventDTO> comboBoxListaEvento;
    private JLabel LabelIDAttendee;
    private JTextField textFieldEmailAttendee;
    private JLabel LabelEstadoVenta;
    private JLabel LabelEstadoUso;
    private JButton registreNewAttendeeButton;
    private JLabel LabelTituloPrincipal;
    private JButton validateTicketButton;
    private JButton showAttendanceStatisticsButton;
    private JPanel PanelEmail;
    private JPanel PanelcomboboxTicket;
    private JPanel PanelComboboxevento;
    private JPanel Panelestodeventa;
    private JPanel Panelestadodeuso;
    private JTextField textFieldIDattendee;
    private JPanel PanelIDattendee;
    private JPanel PanelShowAttendee;
    private JLabel LabelShowAllAttendee;
    private AccessControlService accessControlService = new AccessControlService();
    private VenueService venueService;
    private  EventService eventService;// crea el servicio
    // crea el repositorio


    public GuiFormularioAccessControl() {

        VenueRepository venueRepository = new VenueRepository();
        this.venueService = new VenueService(venueRepository);

        EventRepository eventRepository = new EventRepository();
        this.eventService = new EventService(this.venueService, eventRepository);

            comboBoxTipoTicket.setModel(new DefaultComboBoxModel<>(TicketType.values()));

            List<EventDTO> eventos = eventService.getAllEvents();
            comboBoxListaEvento.setModel(new DefaultComboBoxModel<>(eventos.toArray(new EventDTO[0])));
            registreNewAttendeeButton.setEnabled(!eventos.isEmpty());

            registreNewAttendeeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = textFieldNombreAttendee.getText();
                    String email = textFieldEmailAttendee.getText();
                    TicketType ticketType = (TicketType) comboBoxTipoTicket.getSelectedItem();
                    EventDTO event = (EventDTO) comboBoxListaEvento.getSelectedItem();

                    // Validation
                    if (name == null || name.trim().isEmpty() ||
                        email == null || email.trim().isEmpty() ||
                        event == null || ticketType == null) {
                        JOptionPane.showMessageDialog(null, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    System.out.println("Trying to register: " + name + " (" + email + ") for event " + event.getEventName());

                    boolean registered = accessControlService.registerNewAttendee(
                        event.getEventId(),
                        email,
                        name,
                        ticketType.toString()
                    );

                    if (registered) {
                        JOptionPane.showMessageDialog(null, "Registration saved successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "The attendee is already registered", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

        validateTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String attendeeId = getTextFieldIDattendee().getText();
                boolean valid = accessControlService.validateAttendeeEntryByTicketCode(attendeeId);

                if (valid) {
                    JOptionPane.showMessageDialog(null, "✅ Entrada validada. ¡Bienvenido!");
                } else {
                    JOptionPane.showMessageDialog(null, "❌ Entrada inválida o el asistente ya hizo check-in.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        showAttendanceStatisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
        PanelPrincipalFormularioAccessControl = new JPanel();
        PanelPrincipalFormularioAccessControl.setLayout(new GridLayoutManager(11, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelPrincipalFormularioAccessControl.setBackground(new Color(-15591660));
        PanelPrincipalTituloFormulario = new JPanel();
        PanelPrincipalTituloFormulario.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelPrincipalTituloFormulario.setBackground(new Color(-15591660));
        PanelPrincipalTituloFormulario.setForeground(new Color(-330753));
        PanelPrincipalFormularioAccessControl.add(PanelPrincipalTituloFormulario, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        LabelTituloPrincipal = new JLabel();
        LabelTituloPrincipal.setEnabled(false);
        Font LabelTituloPrincipalFont = this.$$$getFont$$$(null, -1, 24, LabelTituloPrincipal.getFont());
        if (LabelTituloPrincipalFont != null) LabelTituloPrincipal.setFont(LabelTituloPrincipalFont);
        LabelTituloPrincipal.setForeground(new Color(-330753));
        LabelTituloPrincipal.setText("");
        PanelPrincipalTituloFormulario.add(LabelTituloPrincipal, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PanelcodigoTicket = new JPanel();
        PanelcodigoTicket.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelcodigoTicket.setBackground(new Color(-15591660));
        PanelcodigoTicket.setEnabled(false);
        PanelPrincipalFormularioAccessControl.add(PanelcodigoTicket, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PanelcodigoTicket.setBorder(BorderFactory.createTitledBorder(null, "Codigo del ticket", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        LabelTicketCode = new JLabel();
        LabelTicketCode.setEnabled(false);
        LabelTicketCode.setText("");
        PanelcodigoTicket.add(LabelTicketCode, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PanelNombreAttendee = new JPanel();
        PanelNombreAttendee.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelNombreAttendee.setBackground(new Color(-15591660));
        PanelNombreAttendee.setEnabled(false);
        PanelPrincipalFormularioAccessControl.add(PanelNombreAttendee, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PanelNombreAttendee.setBorder(BorderFactory.createTitledBorder(null, "Nombre Attende", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        textFieldNombreAttendee = new JTextField();
        textFieldNombreAttendee.setBackground(new Color(-330753));
        PanelNombreAttendee.add(textFieldNombreAttendee, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        PanelcomboboxTicket = new JPanel();
        PanelcomboboxTicket.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelcomboboxTicket.setBackground(new Color(-15591660));
        PanelcomboboxTicket.setEnabled(false);
        PanelPrincipalFormularioAccessControl.add(PanelcomboboxTicket, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PanelcomboboxTicket.setBorder(BorderFactory.createTitledBorder(null, "Seleccione el Tipo de Ticket", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        comboBoxTipoTicket = new JComboBox();
        PanelcomboboxTicket.add(comboBoxTipoTicket, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PanelComboboxevento = new JPanel();
        PanelComboboxevento.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelComboboxevento.setBackground(new Color(-15591660));
        PanelComboboxevento.setEnabled(false);
        PanelPrincipalFormularioAccessControl.add(PanelComboboxevento, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PanelComboboxevento.setBorder(BorderFactory.createTitledBorder(null, "Seleccione el Evento ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        comboBoxListaEvento = new JComboBox();
        PanelComboboxevento.add(comboBoxListaEvento, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Panelestodeventa = new JPanel();
        Panelestodeventa.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        Panelestodeventa.setBackground(new Color(-15591660));
        Panelestodeventa.setEnabled(false);
        PanelPrincipalFormularioAccessControl.add(Panelestodeventa, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Panelestodeventa.setBorder(BorderFactory.createTitledBorder(null, "Estado de la venta", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        LabelEstadoVenta = new JLabel();
        LabelEstadoVenta.setEnabled(false);
        LabelEstadoVenta.setText("");
        Panelestodeventa.add(LabelEstadoVenta, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PanelIDattendee = new JPanel();
        PanelIDattendee.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        PanelIDattendee.setBackground(new Color(-15591660));
        PanelIDattendee.setEnabled(false);
        PanelIDattendee.setForeground(new Color(-16777216));
        PanelPrincipalFormularioAccessControl.add(PanelIDattendee, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PanelIDattendee.setBorder(BorderFactory.createTitledBorder(null, "ID Attende", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        textFieldIDattendee = new JTextField();
        textFieldIDattendee.setBackground(new Color(-15591660));
        textFieldIDattendee.setForeground(new Color(-330753));
        PanelIDattendee.add(textFieldIDattendee, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        LabelIDAttendee = new JLabel();
        LabelIDAttendee.setEnabled(false);
        LabelIDAttendee.setText("");
        PanelIDattendee.add(LabelIDAttendee, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PanelEmail = new JPanel();
        PanelEmail.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelEmail.setBackground(new Color(-15591660));
        PanelEmail.setEnabled(false);
        PanelPrincipalFormularioAccessControl.add(PanelEmail, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PanelEmail.setBorder(BorderFactory.createTitledBorder(null, "Email", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        textFieldEmailAttendee = new JTextField();
        textFieldEmailAttendee.setBackground(new Color(-330753));
        PanelEmail.add(textFieldEmailAttendee, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        Panelestadodeuso = new JPanel();
        Panelestadodeuso.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        Panelestadodeuso.setBackground(new Color(-15591660));
        Panelestadodeuso.setEnabled(false);
        PanelPrincipalFormularioAccessControl.add(Panelestadodeuso, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Panelestadodeuso.setBorder(BorderFactory.createTitledBorder(null, "Estado de uso", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        LabelEstadoUso = new JLabel();
        LabelEstadoUso.setEnabled(false);
        LabelEstadoUso.setText("");
        Panelestadodeuso.add(LabelEstadoUso, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-15591660));
        PanelPrincipalFormularioAccessControl.add(panel1, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        registreNewAttendeeButton = new JButton();
        registreNewAttendeeButton.setBackground(new Color(-14829228));
        registreNewAttendeeButton.setForeground(new Color(-330753));
        registreNewAttendeeButton.setText("Registre new Attendee");
        panel1.add(registreNewAttendeeButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        validateTicketButton = new JButton();
        validateTicketButton.setBackground(new Color(-14829228));
        validateTicketButton.setText("Validate ticket");
        panel1.add(validateTicketButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        showAttendanceStatisticsButton = new JButton();
        showAttendanceStatisticsButton.setBackground(new Color(-14829228));
        showAttendanceStatisticsButton.setText("show Attendance Statistics");
        panel1.add(showAttendanceStatisticsButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PanelShowAttendee = new JPanel();
        PanelShowAttendee.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelShowAttendee.setBackground(new Color(-15591660));
        PanelShowAttendee.setEnabled(false);
        PanelPrincipalFormularioAccessControl.add(PanelShowAttendee, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PanelShowAttendee.setBorder(BorderFactory.createTitledBorder(null, "Show attendee", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        LabelShowAllAttendee = new JLabel();
        LabelShowAllAttendee.setBackground(new Color(-330753));
        LabelShowAllAttendee.setForeground(new Color(-16777216));
        LabelShowAllAttendee.setText("");
        PanelShowAttendee.add(LabelShowAllAttendee, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return PanelPrincipalFormularioAccessControl;
    }

    /**
     * @noinspection ALL
     */
    public JPanel getPanelPrincipalFormularioAccessControl() {
        return PanelPrincipalFormularioAccessControl;
    }

    public JPanel getPanelPrincipalTituloFormulario() {
        return PanelPrincipalTituloFormulario;
    }

    public JLabel getLabelTicketCode() {
        return LabelTicketCode;
    }

    public JTextField getTextFieldNombreAttendee() {
        return textFieldNombreAttendee;
    }

    public JPanel getPanelcodigoTicket() {
        return PanelcodigoTicket;
    }

    public JPanel getPanelNombreAttendee() {
        return PanelNombreAttendee;
    }

    public JComboBox<TicketType> getComboBoxTipoTicket() {
        return comboBoxTipoTicket;
    }

    public JComboBox<EventDTO> getComboBoxListaEvento() {
        return comboBoxListaEvento;
    }

    public JLabel getLabelIDAttendee() {
        return LabelIDAttendee;
    }

    public JTextField getTextFieldEmailAttendee() {
        return textFieldEmailAttendee;
    }

    public JLabel getLabelEstadoVenta() {
        return LabelEstadoVenta;
    }

    public JLabel getLabelEstadoUso() {
        return LabelEstadoUso;
    }

    public JButton getRegistreNewAttendeeButton() {
        return registreNewAttendeeButton;
    }

    public JLabel getLabelTituloPrincipal() {
        return LabelTituloPrincipal;
    }

    public JButton getValidateTicketButton() {
        return validateTicketButton;
    }

    public JButton getShowAttendanceStatisticsButton() {
        return showAttendanceStatisticsButton;
    }

    public JPanel getPanelEmail() {
        return PanelEmail;
    }

    public JPanel getPanelcomboboxTicket() {
        return PanelcomboboxTicket;
    }

    public JPanel getPanelComboboxevento() {
        return PanelComboboxevento;
    }

    public JPanel getPanelestodeventa() {
        return Panelestodeventa;
    }

    public JPanel getPanelestadodeuso() {
        return Panelestadodeuso;
    }

    public AccessControlService getAccessControlService() {
        return accessControlService;
    }

    public JTextField getTextFieldIDattendee() {
        return textFieldIDattendee;
    }

    public JLabel getLabelShowAllAttendee() {
        return LabelShowAllAttendee;
    }

    public JPanel getPanelShowAttendee() {
        return PanelShowAttendee;
    }

    public JPanel getPanelIDattendee() {
        return PanelIDattendee;
    }

}
