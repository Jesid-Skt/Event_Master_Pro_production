package Ui.GuiPackageVenue.GuiPackageFormularioVenue;

import Enums.City;
import Enums.Country;
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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiFormilarioVenue {
    private JPanel PanelPrincipalFormularioVenue;
    private JPanel PanelPrincipalTituloVenue;
    private JLabel LabelTituloPrincipal;
    private JTextField textFieldIDVenue;
    private JPanel PanelIDVenue;
    private JTextField textFieldNameVenue;
    private JPanel PanelNameVenue;
    private JPanel PanelAddresVenue;
    private JTextField textFieldAddresVenue;
    private JPanel PanelComboboxLocation;
    private JComboBox<Country> comboBoxCountry;
    private JComboBox<City> comboBoxCity;
    private JPanel PanelCapavityVenue;
    private JTextField textFieldCapacity;
    private JPanel PanelBotonCreateVenue;
    private JButton buttonCreateVenue;
    private JButton ButtonModifyVenue;
    private JButton ButtonDeleteVenue;
    private EventService eventService;
    private VenueService venueService = new VenueService();
    private VenueRepository repository = new VenueRepository();
    private String generatedVenueID;

    public GuiFormilarioVenue() {
        eventService = new EventService(new VenueService(), new EventRepository());
        // Genera el ID solo una vez al abrir el formulario
        generatedVenueID = venueService.generateUniqueVenueID();
        getTextFieldIDVenue().setText(generatedVenueID);

        JComboBox<City> cityComboBox = new JComboBox<>(City.values());
        JComboBox<Country> countryComboBox = new JComboBox<>(Country.values());
        comboBoxCity.setModel(cityComboBox.getModel());
        comboBoxCountry.setModel(countryComboBox.getModel());

       buttonCreateVenue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameVenue = textFieldNameVenue.getText();
                String address = textFieldAddresVenue.getText();
                Country selectedCountry = (Country) comboBoxCountry.getSelectedItem();
                City selectedCity = (City) comboBoxCity.getSelectedItem();
                String capacityText = textFieldCapacity.getText();

                int capacity = 0;
                try {
                    capacity = Integer.parseInt(capacityText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PanelPrincipalFormularioVenue, "La capacidad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    // Crea el objeto Venue (ajusta el constructor según tu implementación)
                   // Ajusta el constructor de Venue para que acepte solo los argumentos necesarios
                // Usa el constructor correcto según la definición de Venue
                Venue venue = new Venue(generatedVenueID, nameVenue, selectedCountry, selectedCity, capacity);

                // Crea el VenueDTO usando los parámetros necesarios (ajusta según el constructor real de VenueDTO)
                repository.addVenue(new DTOS.VenueDTO(generatedVenueID, nameVenue, selectedCountry + ", " + selectedCity, capacity));

                    JOptionPane.showMessageDialog(PanelPrincipalFormularioVenue,
                            "Venue creado exitosamente:\nID: " + generatedVenueID +
                                    "\nNombre: " + nameVenue +
                                    "\nDirección: " + address +
                                    "\nPaís: " + selectedCountry +
                                    "\nCiudad: " + selectedCity +
                                    "\nCapacidad: " + capacity,
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);

                    // Limpia los campos si lo deseas
                    textFieldNameVenue.setText("");
                    textFieldAddresVenue.setText("");
                    textFieldCapacity.setText("");
                    comboBoxCountry.setSelectedIndex(0);
                    comboBoxCity.setSelectedIndex(0);

                    // Genera un nuevo ID para el siguiente registro
                    generatedVenueID = venueService.generateUniqueVenueID();
                    getTextFieldIDVenue().setText(generatedVenueID);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(PanelPrincipalFormularioVenue, "Error al crear el venue: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        ButtonModifyVenue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        ButtonDeleteVenue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        comboBoxCountry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Country selectedCountry = (Country) comboBoxCountry.getSelectedItem();
                DefaultComboBoxModel<City> cityModel = new DefaultComboBoxModel<>();
                for (City city : City.values()) {
                    if (city.getCountry() == selectedCountry) { // Suponiendo que City tiene getCountry()
                        cityModel.addElement(city);
                    }
                }
                comboBoxCity.setModel(cityModel);
            }
        });
    }

    private void generateEventID() {
        // Aquí generas un ID único. Ejemplo simple usando timestamp:
        generatedVenueID = "EVT-" + System.currentTimeMillis();
        getTextFieldIDVenue().setText("ID: " + generatedVenueID);
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
        PanelPrincipalFormularioVenue = new JPanel();
        PanelPrincipalFormularioVenue.setLayout(new GridLayoutManager(7, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelPrincipalFormularioVenue.setBackground(new Color(-15591660));
        PanelPrincipalFormularioVenue.setForeground(new Color(-330753));
        PanelPrincipalTituloVenue = new JPanel();
        PanelPrincipalTituloVenue.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelPrincipalTituloVenue.setBackground(new Color(-15591660));
        PanelPrincipalFormularioVenue.add(PanelPrincipalTituloVenue, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        LabelTituloPrincipal = new JLabel();
        LabelTituloPrincipal.setBackground(new Color(-15591660));
        LabelTituloPrincipal.setEnabled(false);
        LabelTituloPrincipal.setForeground(new Color(-330753));
        LabelTituloPrincipal.setText("");
        PanelPrincipalTituloVenue.add(LabelTituloPrincipal, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PanelIDVenue = new JPanel();
        PanelIDVenue.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelIDVenue.setBackground(new Color(-15591660));
        PanelIDVenue.setEnabled(false);
        PanelPrincipalFormularioVenue.add(PanelIDVenue, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PanelIDVenue.setBorder(BorderFactory.createTitledBorder(null, "ID Venue", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        textFieldIDVenue = new JTextField();
        textFieldIDVenue.setForeground(new Color(-15591660));
        PanelIDVenue.add(textFieldIDVenue, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        PanelNameVenue = new JPanel();
        PanelNameVenue.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelNameVenue.setBackground(new Color(-15591660));
        PanelNameVenue.setEnabled(false);
        PanelPrincipalFormularioVenue.add(PanelNameVenue, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PanelNameVenue.setBorder(BorderFactory.createTitledBorder(null, "Name Venue", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        textFieldNameVenue = new JTextField();
        textFieldNameVenue.setBackground(new Color(-330753));
        textFieldNameVenue.setForeground(new Color(-16777216));
        PanelNameVenue.add(textFieldNameVenue, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        PanelAddresVenue = new JPanel();
        PanelAddresVenue.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelAddresVenue.setBackground(new Color(-15591660));
        PanelAddresVenue.setEnabled(false);
        PanelPrincipalFormularioVenue.add(PanelAddresVenue, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PanelAddresVenue.setBorder(BorderFactory.createTitledBorder(null, "Addres", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        textFieldAddresVenue = new JTextField();
        textFieldAddresVenue.setBackground(new Color(-330753));
        textFieldAddresVenue.setForeground(new Color(-16777216));
        PanelAddresVenue.add(textFieldAddresVenue, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        PanelComboboxLocation = new JPanel();
        PanelComboboxLocation.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelComboboxLocation.setBackground(new Color(-15591660));
        PanelComboboxLocation.setEnabled(false);
        PanelPrincipalFormularioVenue.add(PanelComboboxLocation, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PanelComboboxLocation.setBorder(BorderFactory.createTitledBorder(null, "Seleccione the country and city", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        comboBoxCountry = new JComboBox();
        comboBoxCountry.setBackground(new Color(-330753));
        comboBoxCountry.setForeground(new Color(-16777216));
        PanelComboboxLocation.add(comboBoxCountry, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        PanelComboboxLocation.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        comboBoxCity = new JComboBox();
        comboBoxCity.setBackground(new Color(-330753));
        comboBoxCity.setForeground(new Color(-16777216));
        PanelComboboxLocation.add(comboBoxCity, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PanelCapavityVenue = new JPanel();
        PanelCapavityVenue.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelCapavityVenue.setBackground(new Color(-15591660));
        PanelCapavityVenue.setEnabled(false);
        PanelCapavityVenue.setForeground(new Color(-15591660));
        PanelPrincipalFormularioVenue.add(PanelCapavityVenue, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PanelCapavityVenue.setBorder(BorderFactory.createTitledBorder(null, "Capacity", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        textFieldCapacity = new JTextField();
        textFieldCapacity.setBackground(new Color(-330753));
        textFieldCapacity.setForeground(new Color(-16777216));
        PanelCapavityVenue.add(textFieldCapacity, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        PanelBotonCreateVenue = new JPanel();
        PanelBotonCreateVenue.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        PanelBotonCreateVenue.setBackground(new Color(-15591660));
        PanelBotonCreateVenue.setForeground(new Color(-330753));
        PanelPrincipalFormularioVenue.add(PanelBotonCreateVenue, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonCreateVenue = new JButton();
        buttonCreateVenue.setBackground(new Color(-14829228));
        buttonCreateVenue.setForeground(new Color(-16777216));
        buttonCreateVenue.setText("Create Venue");
        PanelBotonCreateVenue.add(buttonCreateVenue, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ButtonDeleteVenue = new JButton();
        ButtonDeleteVenue.setBackground(new Color(-14829228));
        ButtonDeleteVenue.setForeground(new Color(-16777216));
        ButtonDeleteVenue.setText("Delete Venue");
        PanelBotonCreateVenue.add(ButtonDeleteVenue, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ButtonModifyVenue = new JButton();
        ButtonModifyVenue.setBackground(new Color(-14829228));
        ButtonModifyVenue.setForeground(new Color(-16777216));
        ButtonModifyVenue.setText("Modify Venue");
        PanelBotonCreateVenue.add(ButtonModifyVenue, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return PanelPrincipalFormularioVenue;
    }

    /**
     * @noinspection ALL
     */
    public JPanel getPanelPrincipalFormularioVenue() {
        return PanelPrincipalFormularioVenue;
    }

    public JPanel getPanelPrincipalTituloVenue() {
        return PanelPrincipalTituloVenue;
    }

    public JLabel getLabelTituloPrincipal() {
        return LabelTituloPrincipal;
    }

    public JTextField getTextFieldIDVenue() {
        return textFieldIDVenue;
    }

    public JPanel getPanelIDVenue() {
        return PanelIDVenue;
    }

    public JTextField getTextFieldNameVenue() {
        return textFieldNameVenue;
    }

    public JPanel getPanelNameVenue() {
        return PanelNameVenue;
    }

    public JPanel getPanelAddresVenue() {
        return PanelAddresVenue;
    }

    public JTextField getTextFieldAddresVenue() {
        return textFieldAddresVenue;
    }

    public JPanel getPanelComboboxLocation() {
        return PanelComboboxLocation;
    }

    public JComboBox getComboBoxCountry() {
        return comboBoxCountry;
    }

    public JComboBox getComboBoxCity() {
        return comboBoxCity;
    }

    public JPanel getPanelCapavityVenue() {
        return PanelCapavityVenue;
    }

    public JTextField getTextFieldCapacity() {
        return textFieldCapacity;
    }

    public JPanel getPanelBotonCreateVenue() {
        return PanelBotonCreateVenue;
    }

    public JButton getButtonCreateVenue() {
        return buttonCreateVenue;
    }

    public JButton getButtonModifyVenue() {
        return ButtonModifyVenue;
    }

    public JButton getButtonDeleteVenue() {
        return ButtonDeleteVenue;
    }


}
