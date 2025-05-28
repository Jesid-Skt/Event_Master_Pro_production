package Ui.GuiPackageEvent.GuiPackageFormulariosEvent;

import DTOS.EventDTO;
import DTOS.VenueDTO;
import Enums.EventType;
import Model.EventPackage.Venue;
import Repository.EventRepository;
import Repository.VenueRepository;
import Services.EventService;
import Services.VenueService;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JLabel LabelTituloPrincipal;
    private JButton buttonModifyEvent;
    private JButton buttonDeleteEvent;
    private JPanel PanelTituloPrincipal;
    private JPanel PanelStartDate;
    private JPanel PanelEndDate;
    private JPanel PanelComboboxEvenType;
    private JPanel PanelEventName;
    private JPanel PanelIDEvent;
    private JPanel PanelVenueId;
    private JButton searchEventButton;
    private EventService eventService;
    private VenueService venueService;
    private VenueRepository venueRepository; // Repositorio para manejar venues
    private EventRepository eventRepository = new EventRepository(); // Repositorio para manejar eventos
    private String generatedEventID; // ID único generado para el evento
    private JComboBox<EventType> eventTypeComboBox;

    // 🔽 AQUÍ debes agregarla
    private EventDTO currentEvent = null;

    public GuiFormularioCreateEvent() {
        comboBoxEventType.setModel(new DefaultComboBoxModel<>(EventType.values()));

        // Instanciar servicios
        venueRepository = new VenueRepository();
        venueService = new VenueService(venueRepository);
        eventRepository = new EventRepository();
        eventService = new EventService(venueService, eventRepository);


        venueRepository.loadFromFile();
        eventRepository.loadFromFile();// Asegúrate de que venueRepository esté inicializado
        // Generar ID único al abrir el formulario
        generatedEventID = eventService.generateUniqueEventID();
        getFieldEventID().setText(generatedEventID);

        createEventButton.addActionListener(e -> {
            // Cargar los datos de los recintos desde el archivo
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

            // Obtener el Venue (modelo)
            Venue venue = venueService.getVenueById(venueID);

            if (venue == null) {
                JOptionPane.showMessageDialog(null, "El ID del recinto no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Convertir Venue (modelo) a VenueDTO
            VenueDTO venueDTO = VenueDTO.fromVenue(venue);
            EventDTO dto = new EventDTO(generatedEventID, eventName, eventType.name(), startDateTimeStr, endDateTimeStr, venueDTO);

            try {
                eventRepository.addEvent(dto);

                JOptionPane.showMessageDialog(null, "Evento creado exitosamente con ID: " + dto.getEventId());

                // Limpiar campos y restablecer placeholders
                FIeldVenueID.setText("Ingrese el ID del venue");
                FIeldVenueID.setForeground(Color.white);

                FieldEventName.setText("Ingrese el nombre del evento");
                FieldEventName.setForeground(Color.white);

                formattedFieldStartDate.setText("Ejemplo: yyyy-MM-dd HH:mm");
                formattedFieldStartDate.setForeground(Color.white);

                formattedFieldEndDate.setText("Ejemplo: yyyy-MM-dd HH:mm");
                formattedFieldEndDate.setForeground(Color.white);

                comboBoxEventType.setSelectedIndex(0);

                // Generar nuevo ID para futuro evento
                generatedEventID = eventService.generateUniqueEventID();
                getFieldEventID().setText(generatedEventID);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al crear el evento: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        // Listeners para limpiar placeholder al hacer click
        setupPlaceholders();

        buttonDeleteEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = FieldEventID.getText().trim();
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar el ID del evento.", "Campo requerido", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                EventDTO dto = eventRepository.getEventById(id);
                if (dto == null) {
                    JOptionPane.showMessageDialog(null, "Evento no encontrado con ese ID.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(null,
                        "¿Estás seguro de que deseas eliminar el evento \"" + dto.getEventName() + "\"?",
                        "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    eventRepository.removeEventById(id);
                    JOptionPane.showMessageDialog(null, "✅ Evento eliminado correctamente.");
                    // Limpiar campos si deseas
                    clearFields();
                }
            }
        });
        buttonModifyEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentEvent == null) {
                    JOptionPane.showMessageDialog(null, "Primero debe buscar un evento válido");
                    return;
                }
                // Actualizar campos modificables en el objeto event
                currentEvent.setEventName(FieldEventName.getText().trim());
                currentEvent.setStartDate(formattedFieldStartDate.getText().trim());
                currentEvent.setEndDate(formattedFieldEndDate.getText().trim());

                // Actualizar evento en repositorio (guardará en archivo)
                eventRepository.updateEvent(currentEvent);

                JOptionPane.showMessageDialog(null, "Evento modificado y guardado correctamente");

                // Opcional: limpiar formulario o cerrar ventana
            }
        });
        searchEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventId = FieldEventID.getText().trim();
                if (eventId.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un ID de evento");
                    return;
                }
                currentEvent = eventRepository.getEventById(eventId);
                if (currentEvent == null) {
                    JOptionPane.showMessageDialog(null, "Evento no encontrado");
                    return;
                }
                // Cargar datos en campos
                FieldEventName.setText(currentEvent.getEventName());
                formattedFieldStartDate.setText(currentEvent.getStartDate());
                formattedFieldEndDate.setText(currentEvent.getEndDate());

                // Solo permitir modificar nombre y fechas
                FieldEventID.setEditable(false);
                FieldEventName.setEditable(true);
                formattedFieldStartDate.setEditable(true);
                formattedFieldEndDate.setEditable(true);

                // Habilitar botón modificar, si estaba deshabilitado
                buttonModifyEvent.setEnabled(true);
            }
        });
        buttonModifyEvent.setEnabled(false);
    }

    private void setupPlaceholders() {
        FieldEventID.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (FIeldVenueID.getText().equals("Ingrese el ID del evento")) {
                    FIeldVenueID.setText("");
                    FIeldVenueID.setForeground(Color.BLACK);
                }
            }
        });
        FieldEventID.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (FieldEventID.getText().equals("Ingrese el ID del evento")) {
                    FieldEventID.setText("");
                    FieldEventID.setForeground(Color.BLACK);
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
        getFieldEventID().setText(generatedEventID);
    }

    private void clearFields() {
        FieldEventID.setText("");
        FieldEventName.setText("");
        comboBoxEventType.setSelectedIndex(0);
        formattedFieldStartDate.setText("");
        formattedFieldEndDate.setText("");
        FIeldVenueID.setText("");
        // Y los demás campos si tienes
    }


    public JLabel getLabelEventID() {
        return LabelEventID;
    }

    public JTextField getFieldEventID() {
        return FieldEventID;
    }

    public JLabel getFormCreateEventLabel() {
        return LabelTituloPrincipal;
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
        PanelVenueId = new JPanel();
        PanelVenueId.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        PanelVenueId.setBackground(new Color(-15591660));
        PanelVenueId.setEnabled(false);
        Font PanelVenueIdFont = this.$$$getFont$$$(null, -1, 22, PanelVenueId.getFont());
        if (PanelVenueIdFont != null) PanelVenueId.setFont(PanelVenueIdFont);
        PanelVenueId.setForeground(new Color(-330753));
        PanelFolmularioCreateEvent.add(PanelVenueId, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PanelVenueId.setBorder(BorderFactory.createTitledBorder(null, "ID Venue", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        FIeldVenueID = new JTextField();
        FIeldVenueID.setBackground(new Color(-330753));
        FIeldVenueID.setForeground(new Color(-16777216));
        FIeldVenueID.setText("Ingrese el ID del venue");
        PanelVenueId.add(FIeldVenueID, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        PanelVenueId.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        PanelIDEvent = new JPanel();
        PanelIDEvent.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelIDEvent.setBackground(new Color(-15591660));
        PanelIDEvent.setEnabled(false);
        PanelIDEvent.setForeground(new Color(-330753));
        PanelFolmularioCreateEvent.add(PanelIDEvent, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PanelIDEvent.setBorder(BorderFactory.createTitledBorder(null, "ID Event", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        FieldEventID = new JTextField();
        FieldEventID.setEditable(false);
        FieldEventID.setEnabled(true);
        PanelIDEvent.add(FieldEventID, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        PanelEventName = new JPanel();
        PanelEventName.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelEventName.setBackground(new Color(-15591660));
        PanelEventName.setEnabled(false);
        PanelEventName.setForeground(new Color(-330753));
        PanelFolmularioCreateEvent.add(PanelEventName, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PanelEventName.setBorder(BorderFactory.createTitledBorder(null, "Event Name", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        FieldEventName = new JTextField();
        FieldEventName.setBackground(new Color(-330753));
        FieldEventName.setForeground(new Color(-330753));
        FieldEventName.setText("Ingrese el nombre del evento");
        PanelEventName.add(FieldEventName, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        PanelComboboxEvenType = new JPanel();
        PanelComboboxEvenType.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelComboboxEvenType.setBackground(new Color(-15591660));
        PanelComboboxEvenType.setEnabled(false);
        PanelComboboxEvenType.setForeground(new Color(-330753));
        PanelFolmularioCreateEvent.add(PanelComboboxEvenType, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PanelComboboxEvenType.setBorder(BorderFactory.createTitledBorder(null, "Event Type", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        comboBoxEventType = new JComboBox();
        comboBoxEventType.setBackground(new Color(-330753));
        comboBoxEventType.setEditable(true);
        comboBoxEventType.setForeground(new Color(-330753));
        PanelComboboxEvenType.add(comboBoxEventType, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PanelStartDate = new JPanel();
        PanelStartDate.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelStartDate.setBackground(new Color(-15591660));
        PanelStartDate.setEnabled(false);
        PanelStartDate.setForeground(new Color(-330753));
        PanelFolmularioCreateEvent.add(PanelStartDate, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PanelStartDate.setBorder(BorderFactory.createTitledBorder(null, "Start Date Time", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        formattedFieldStartDate = new JFormattedTextField();
        formattedFieldStartDate.setBackground(new Color(-330753));
        formattedFieldStartDate.setForeground(new Color(-16777216));
        formattedFieldStartDate.setText("Ejemplo: yyyy-MM-dd HH:mm");
        PanelStartDate.add(formattedFieldStartDate, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        PanelEndDate = new JPanel();
        PanelEndDate.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelEndDate.setBackground(new Color(-15591660));
        PanelEndDate.setEnabled(false);
        PanelFolmularioCreateEvent.add(PanelEndDate, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PanelEndDate.setBorder(BorderFactory.createTitledBorder(null, "End Date Time", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        formattedFieldEndDate = new JFormattedTextField();
        formattedFieldEndDate.setBackground(new Color(-330753));
        formattedFieldEndDate.setForeground(new Color(-16777216));
        formattedFieldEndDate.setText("Ejemplo: yyyy-MM-dd HH:mm");
        PanelEndDate.add(formattedFieldEndDate, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-15591660));
        PanelFolmularioCreateEvent.add(panel1, new GridConstraints(14, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        createEventButton = new JButton();
        createEventButton.setBackground(new Color(-14829228));
        createEventButton.setForeground(new Color(-330753));
        createEventButton.setText("Create event");
        panel1.add(createEventButton, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonModifyEvent = new JButton();
        buttonModifyEvent.setBackground(new Color(-14829228));
        buttonModifyEvent.setForeground(new Color(-16777216));
        buttonModifyEvent.setText("Modify Event");
        panel1.add(buttonModifyEvent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonDeleteEvent = new JButton();
        buttonDeleteEvent.setBackground(new Color(-14829228));
        buttonDeleteEvent.setForeground(new Color(-16777216));
        buttonDeleteEvent.setText("Delete Event");
        panel1.add(buttonDeleteEvent, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchEventButton = new JButton();
        searchEventButton.setBackground(new Color(-14829228));
        searchEventButton.setForeground(new Color(-16777216));
        searchEventButton.setText("Search event");
        panel1.add(searchEventButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        PanelTituloPrincipal = new JPanel();
        PanelTituloPrincipal.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelTituloPrincipal.setBackground(new Color(-15591660));
        PanelFolmularioCreateEvent.add(PanelTituloPrincipal, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        LabelTituloPrincipal = new JLabel();
        Font LabelTituloPrincipalFont = this.$$$getFont$$$(null, -1, 36, LabelTituloPrincipal.getFont());
        if (LabelTituloPrincipalFont != null) LabelTituloPrincipal.setFont(LabelTituloPrincipalFont);
        LabelTituloPrincipal.setForeground(new Color(-330753));
        LabelTituloPrincipal.setText("");
        PanelTituloPrincipal.add(LabelTituloPrincipal, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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

    public JTextField getFIeldVenueID() {
        return FIeldVenueID;
    }

    public JTextField getFieldEventName() {
        return FieldEventName;
    }

    public JComboBox getComboBoxEventType() {
        return comboBoxEventType;
    }

    public JFormattedTextField getFormattedFieldStartDate() {
        return formattedFieldStartDate;
    }

    public JLabel getLabelTituloPrincipal() {
        return LabelTituloPrincipal;
    }

    public JFormattedTextField getFormattedFieldEndDate() {
        return formattedFieldEndDate;
    }

    public JButton getButtonModifyEvent() {
        return buttonModifyEvent;
    }

    public JButton getButtonDeleteEvent() {
        return buttonDeleteEvent;
    }

    public JPanel getPanelTituloPrincipal() {
        return PanelTituloPrincipal;
    }

    public EventService getEventService() {
        return eventService;
    }

    public VenueService getVenueService() {
        return venueService;
    }

    public VenueRepository getVenueRepository() {
        return venueRepository;
    }

    public EventRepository getEventRepository() {
        return eventRepository;
    }

    public String getGeneratedEventID() {
        return generatedEventID;
    }

    public JComboBox<EventType> getEventTypeComboBox() {
        return eventTypeComboBox;
    }

    public JPanel getPanelStartDate() {
        return PanelStartDate;
    }

    public JPanel getPanelVenueId() {
        return PanelVenueId;
    }

    public JPanel getPanelIDEvent() {
        return PanelIDEvent;
    }

    public JPanel getPanelEventName() {
        return PanelEventName;
    }

    public JPanel getPanelComboboxEvenType() {
        return PanelComboboxEvenType;
    }

    public JPanel getPanelEndDate() {
        return PanelEndDate;
    }

    public JButton getSearchEventButton() {
        return searchEventButton;
    }
}
