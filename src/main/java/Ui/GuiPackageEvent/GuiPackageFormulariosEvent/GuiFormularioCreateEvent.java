package Ui.GuiPackageEvent.GuiPackageFormulariosEvent;

import DTOS.EventDTO;
import Enums.EventType;
import Services.EventService;
import Services.VenueService;
import Model.EventPackage.Event;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;

public class GuiFormularioCreateEvent {
    private JPanel PanelFolmularioCreateEvent;
    private JTextField FIeldVenueID;
    private JTextField FieldEventName;
    private JComboBox comboBoxEventType;
    private JFormattedTextField formattedFieldStartDate;
    private JFormattedTextField formattedFieldEndDate;
    private JButton createEventButton;
    private JLabel LabelEventID;
    private JTextField FieldEventID;
    private JLabel formCreateEventLabel;
    private EventService eventService;
    private VenueService venueService;
    private String generatedEventID; // ID único generado para el evento
    private JComboBox<EventType> eventTypeComboBox;

    public GuiFormularioCreateEvent() {
        comboBoxEventType.setModel(new DefaultComboBoxModel<>(EventType.values()));

        // Instanciar servicios
        venueService = new VenueService();
        eventService = new EventService(new VenueService(), new Repository.EventRepository());

        // Generar ID único al abrir el formulario
        generatedEventID = eventService.generateUniqueEventID();
        LabelEventID.setText("ID: " + generatedEventID);

        createEventButton.addActionListener(e -> {
            String venueID = FIeldVenueID.getText().trim();
            String eventName = FieldEventName.getText().trim();
            EventType eventType = (EventType) comboBoxEventType.getSelectedItem();
            String startDateTimeStr = formattedFieldStartDate.getText().trim();
            String endDateTimeStr = formattedFieldEndDate.getText().trim();

            if (venueID.isEmpty() || eventName.isEmpty() || eventType == null
                    || startDateTimeStr.isEmpty() || endDateTimeStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!venueService.venueExists(venueID)) {
                JOptionPane.showMessageDialog(null, "El ID del recinto no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            EventDTO dto = new EventDTO(generatedEventID, eventName, eventType.name(), startDateTimeStr, endDateTimeStr, venueID);

            try {
                Event createdEvent = eventService.createEvent(dto);

                JOptionPane.showMessageDialog(null, "Evento creado exitosamente con ID: " + createdEvent.getId());

                // Limpiar campos
                FIeldVenueID.setText("Ingrese el ID del venue");
                FIeldVenueID.setForeground(Color.white);

                FieldEventName.setText("Ingrese el nombre del evento");
                FieldEventName.setForeground(Color.white);

                formattedFieldStartDate.setText("Ejemplo: yyyy-MM-dd HH:mm");
                formattedFieldStartDate.setForeground(Color.white);

                formattedFieldEndDate.setText("Ejemplo: yyyy-MM-dd HH:mm");
                formattedFieldEndDate.setForeground(Color.white);

                comboBoxEventType.setSelectedIndex(0);

                // Generar nuevo ID para un posible nuevo evento
                generatedEventID = eventService.generateUniqueEventID();
                LabelEventID.setText("ID: " + generatedEventID);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al crear el evento: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Listeners para limpiar placeholder al hacer click
        setupPlaceholders();
    }

    private void setupPlaceholders() {
        FIeldVenueID.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (FIeldVenueID.getText().equals("Ingrese el ID del venue")) {
                    FIeldVenueID.setText("");
                    FIeldVenueID.setForeground(Color.BLACK);
                }
            }
        });

        FieldEventName.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (FieldEventName.getText().equals("Ingrese el nombre del evento")) {
                    FieldEventName.setText("");
                    FieldEventName.setForeground(Color.BLACK);
                }
            }
        });

        formattedFieldStartDate.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (formattedFieldStartDate.getText().equals("Ejemplo: yyyy-MM-dd HH:mm")) {
                    formattedFieldStartDate.setText("");
                    formattedFieldStartDate.setForeground(Color.BLACK);
                }
            }
        });

        formattedFieldEndDate.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (formattedFieldEndDate.getText().equals("Ejemplo: yyyy-MM-dd HH:mm")) {
                    formattedFieldEndDate.setText("");
                    formattedFieldEndDate.setForeground(Color.BLACK);
                }
            }
        });
    }

    private void generateEventID() {
        // Aquí generas un ID único. Ejemplo simple usando timestamp:
        generatedEventID = "EVT-" + System.currentTimeMillis();
        LabelEventID.setText("ID: " + generatedEventID);
    }

    public JLabel getLabelEventID() {
        return LabelEventID;
    }

    public JTextField getFieldEventID() {
        return FieldEventID;
    }

    public JLabel getFormCreateEventLabel() {
        return formCreateEventLabel;
    }

    public JButton getCreateEventButton() {
        return createEventButton;
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
        PanelFolmularioCreateEvent = new JPanel();
        PanelFolmularioCreateEvent.setLayout(new GridLayoutManager(15, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelFolmularioCreateEvent.setBackground(new Color(-15591660));
        PanelFolmularioCreateEvent.setForeground(new Color(-330753));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-15591660));
        PanelFolmularioCreateEvent.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-15591660));
        panel1.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        formCreateEventLabel = new JLabel();
        Font formCreateEventLabelFont = this.$$$getFont$$$(null, -1, 36, formCreateEventLabel.getFont());
        if (formCreateEventLabelFont != null) formCreateEventLabel.setFont(formCreateEventLabelFont);
        formCreateEventLabel.setForeground(new Color(-330753));
        formCreateEventLabel.setText("Form Create Event");
        panel2.add(formCreateEventLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setBackground(new Color(-15591660));
        panel3.setEnabled(false);
        Font panel3Font = this.$$$getFont$$$(null, -1, 22, panel3.getFont());
        if (panel3Font != null) panel3.setFont(panel3Font);
        panel3.setForeground(new Color(-330753));
        PanelFolmularioCreateEvent.add(panel3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel3.setBorder(BorderFactory.createTitledBorder(null, "ID Venue", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        FIeldVenueID = new JTextField();
        FIeldVenueID.setBackground(new Color(-330753));
        FIeldVenueID.setForeground(new Color(-16777216));
        FIeldVenueID.setText("Ingrese el ID del venue");
        panel3.add(FIeldVenueID, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.setBackground(new Color(-15591660));
        panel4.setEnabled(false);
        panel4.setForeground(new Color(-330753));
        PanelFolmularioCreateEvent.add(panel4, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel4.setBorder(BorderFactory.createTitledBorder(null, "ID Event", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        LabelEventID = new JLabel();
        LabelEventID.setBackground(new Color(-330753));
        LabelEventID.setEnabled(false);
        LabelEventID.setForeground(new Color(-16777216));
        LabelEventID.setText("");
        panel4.add(LabelEventID, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        FieldEventID = new JTextField();
        panel4.add(FieldEventID, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel5.setBackground(new Color(-15591660));
        panel5.setEnabled(false);
        panel5.setForeground(new Color(-330753));
        PanelFolmularioCreateEvent.add(panel5, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel5.setBorder(BorderFactory.createTitledBorder(null, "Event Name", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        FieldEventName = new JTextField();
        FieldEventName.setBackground(new Color(-330753));
        FieldEventName.setForeground(new Color(-330753));
        FieldEventName.setText("Ingrese el nombre del evento");
        panel5.add(FieldEventName, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel6.setBackground(new Color(-15591660));
        panel6.setEnabled(false);
        panel6.setForeground(new Color(-330753));
        PanelFolmularioCreateEvent.add(panel6, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel6.setBorder(BorderFactory.createTitledBorder(null, "Event Type", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        comboBoxEventType = new JComboBox();
        comboBoxEventType.setBackground(new Color(-330753));
        comboBoxEventType.setForeground(new Color(-16777216));
        panel6.add(comboBoxEventType, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel7.setBackground(new Color(-15591660));
        panel7.setEnabled(false);
        panel7.setForeground(new Color(-330753));
        PanelFolmularioCreateEvent.add(panel7, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel7.setBorder(BorderFactory.createTitledBorder(null, "Start Date Time", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        formattedFieldStartDate = new JFormattedTextField();
        formattedFieldStartDate.setBackground(new Color(-330753));
        formattedFieldStartDate.setForeground(new Color(-16777216));
        formattedFieldStartDate.setText("Ejemplo: yyyy-MM-dd HH:mm");
        panel7.add(formattedFieldStartDate, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel8.setBackground(new Color(-15591660));
        panel8.setEnabled(false);
        PanelFolmularioCreateEvent.add(panel8, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel8.setBorder(BorderFactory.createTitledBorder(null, "End Date Time", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        formattedFieldEndDate = new JFormattedTextField();
        formattedFieldEndDate.setBackground(new Color(-330753));
        formattedFieldEndDate.setForeground(new Color(-16777216));
        formattedFieldEndDate.setText("Ejemplo: yyyy-MM-dd HH:mm");
        panel8.add(formattedFieldEndDate, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel9.setBackground(new Color(-15591660));
        PanelFolmularioCreateEvent.add(panel9, new GridConstraints(14, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        createEventButton = new JButton();
        createEventButton.setBackground(new Color(-14829228));
        createEventButton.setForeground(new Color(-330753));
        createEventButton.setText("Create event");
        panel9.add(createEventButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        PanelFolmularioCreateEvent.add(spacer2, new GridConstraints(13, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        PanelFolmularioCreateEvent.add(spacer3, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        PanelFolmularioCreateEvent.add(spacer4, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        PanelFolmularioCreateEvent.add(spacer5, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        PanelFolmularioCreateEvent.add(spacer6, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        PanelFolmularioCreateEvent.add(spacer7, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        PanelFolmularioCreateEvent.add(spacer8, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
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
        return PanelFolmularioCreateEvent;
    }

    /**
     * @noinspection ALL
     */
    public JPanel getPanelFolmularioCreateEvent() {
        return PanelFolmularioCreateEvent;
    }
}
