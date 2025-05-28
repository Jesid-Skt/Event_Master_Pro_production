package Ui.GuiPackageEvent;

import DTOS.EventDTO;
import Repository.EventRepository;
import Repository.VenueRepository;
import Ui.GuiPackageEvent.GuiPackageFormulariosEvent.CalendarEvents;
import Ui.GuiPackageEvent.GuiPackageFormulariosEvent.GuiFormularioCreateEvent;
import Services.EventService;
import Services.VenueService;
import Ui.GuiPackageMainMenu.GuiMainMenu;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class GuiEvent {
    private JPanel PanelPrincipalEvent;
    private JPanel JpanelTituloPrincipal;
    private JLabel eventMasterProMenuLabel;
    private JPanel JPanelMenuEvent;
    private JButton exitButton;
    private JButton backToMainMenuButton;
    private JButton viewCalendarButton;
    private JButton deleteEventButton;
    private JButton modifyEventButton;
    private JButton createEventButton;
    private JPanel PanelDinamicoEvent;
    private EventService eventService;


    public GuiEvent() {
        VenueRepository venueRepository = new VenueRepository();
        VenueService venueService = new VenueService(venueRepository);
        EventRepository eventRepository = new EventRepository();
        this.eventService = new EventService(venueService, eventRepository);

// Verificación de carga del archivo events.json
        List<EventDTO> eventos = eventRepository.getAllEventsAsList();
        if (eventos.isEmpty()) {
            System.out.println("No se cargaron eventos. Verifica el archivo events.json.");
        } else {
            System.out.println("Eventos cargados:");
            for (EventDTO e : eventos) {
                System.out.println("ID: " + e.getEventId() + " - Nombre: " + e.getEventName());
            }
        }


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelPrincipalEvent);
                currentFrame.dispose(); // Cierra la ventana actual

                JFrame frame = new JFrame("Exit");
                JOptionPane.showMessageDialog(frame, "Thank you for using Event Master Pro!", "Exit", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0); // Cierra la aplicación

            }
        });
        backToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelPrincipalEvent);
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
        createEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crea una nueva instancia de la ventana principal
                GuiFormularioCreateEvent formularioEvent = new GuiFormularioCreateEvent();
                formularioEvent.getFieldEventID().setEditable(false);
                formularioEvent.getButtonDeleteEvent().setVisible(false);
                formularioEvent.getButtonModifyEvent().setVisible(false);


                JFrame frame = new JFrame("Create Event");
                frame.setContentPane(formularioEvent.getPanelFolmularioCreateEvent());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setSize(500, 500);
                frame.setVisible(true);
            }
        });
        modifyEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambia la visibilidad de los componentes
                GuiFormularioCreateEvent formularioEvent = new GuiFormularioCreateEvent();
                formularioEvent.getFieldEventID().setVisible(true);

                JFrame frame = new JFrame("Modify Event");
                frame.setContentPane(formularioEvent.getPanelFolmularioCreateEvent());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setSize(500, 500);
                formularioEvent.getFIeldVenueID().setEditable(false);
                formularioEvent.getFieldEventID().setEditable(true);
                frame.setVisible(true);
            }
        });

        deleteEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiFormularioCreateEvent formularioEvent = new GuiFormularioCreateEvent();
                formularioEvent.getFieldEventID().setEditable(true);
                formularioEvent.getButtonDeleteEvent().setVisible(true);
                formularioEvent.getButtonModifyEvent().setVisible(false);

                JFrame frame = new JFrame("Delete Event");
                frame.setContentPane(formularioEvent.getPanelFolmularioCreateEvent());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setSize(500, 500);
                frame.setVisible(true);
            }
        });
        viewCalendarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventRepository repository = new EventRepository();
                CalendarEvents calendarEvents = new CalendarEvents();
                JFrame frame = new JFrame("Calendar");
                frame.setContentPane(calendarEvents.getPanelPrincipalClanderEvents());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setVisible(true);
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
        PanelPrincipalEvent = new JPanel();
        PanelPrincipalEvent.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        PanelPrincipalEvent.setBackground(new Color(-15591660));
        panel1.add(PanelPrincipalEvent, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        JpanelTituloPrincipal = new JPanel();
        JpanelTituloPrincipal.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        JpanelTituloPrincipal.setBackground(new Color(-15591660));
        PanelPrincipalEvent.add(JpanelTituloPrincipal, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        eventMasterProMenuLabel = new JLabel();
        Font eventMasterProMenuLabelFont = this.$$$getFont$$$("Roboto Light", Font.BOLD, 28, eventMasterProMenuLabel.getFont());
        if (eventMasterProMenuLabelFont != null) eventMasterProMenuLabel.setFont(eventMasterProMenuLabelFont);
        eventMasterProMenuLabel.setForeground(new Color(-330753));
        eventMasterProMenuLabel.setText("Event Master Pro ");
        JpanelTituloPrincipal.add(eventMasterProMenuLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        JpanelTituloPrincipal.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        JPanelMenuEvent = new JPanel();
        JPanelMenuEvent.setLayout(new GridLayoutManager(9, 1, new Insets(0, 0, 0, 0), -1, -1));
        JPanelMenuEvent.setBackground(new Color(-15591660));
        PanelPrincipalEvent.add(JPanelMenuEvent, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(207, 298), null, 0, false));
        exitButton = new JButton();
        exitButton.setBackground(new Color(-14829228));
        exitButton.setEnabled(true);
        exitButton.setForeground(new Color(-330753));
        exitButton.setText("Exit");
        JPanelMenuEvent.add(exitButton, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        backToMainMenuButton = new JButton();
        backToMainMenuButton.setBackground(new Color(-14829228));
        backToMainMenuButton.setEnabled(true);
        backToMainMenuButton.setForeground(new Color(-330753));
        backToMainMenuButton.setText(" Back to Main Menu");
        JPanelMenuEvent.add(backToMainMenuButton, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        viewCalendarButton = new JButton();
        viewCalendarButton.setBackground(new Color(-14829228));
        viewCalendarButton.setEnabled(true);
        viewCalendarButton.setForeground(new Color(-330753));
        viewCalendarButton.setText("View Calendar");
        JPanelMenuEvent.add(viewCalendarButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteEventButton = new JButton();
        deleteEventButton.setBackground(new Color(-14829228));
        deleteEventButton.setEnabled(true);
        deleteEventButton.setForeground(new Color(-330753));
        deleteEventButton.setText(" Delete Event");
        JPanelMenuEvent.add(deleteEventButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        modifyEventButton = new JButton();
        modifyEventButton.setBackground(new Color(-14829228));
        modifyEventButton.setEnabled(true);
        modifyEventButton.setForeground(new Color(-330753));
        modifyEventButton.setText("Modify Event");
        JPanelMenuEvent.add(modifyEventButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createEventButton = new JButton();
        createEventButton.setBackground(new Color(-14829228));
        createEventButton.setEnabled(true);
        createEventButton.setForeground(new Color(-330753));
        createEventButton.setText("Create Event");
        JPanelMenuEvent.add(createEventButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        JPanelMenuEvent.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        JPanelMenuEvent.add(spacer3, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        JPanelMenuEvent.add(spacer4, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        PanelDinamicoEvent = new JPanel();
        PanelDinamicoEvent.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        PanelDinamicoEvent.setBackground(new Color(-15591660));
        PanelPrincipalEvent.add(PanelDinamicoEvent, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setBackground(new Color(-15591660));
        label1.setEnabled(true);
        Font label1Font = this.$$$getFont$$$(null, -1, 72, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-330753));
        label1.setText("Welcome To Manage Event ");
        PanelDinamicoEvent.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        PanelDinamicoEvent.add(spacer5, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
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


    public JPanel getPanelPrincipalEvent() {
        return PanelPrincipalEvent;
    }
}
