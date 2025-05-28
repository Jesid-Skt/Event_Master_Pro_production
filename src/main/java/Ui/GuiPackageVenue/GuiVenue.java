package Ui.GuiPackageVenue;

import Ui.GuiPackageEvent.GuiPackageFormulariosEvent.GuiFormularioCreateEvent;
import Ui.GuiPackageMainMenu.GuiMainMenu;
import Ui.GuiPackageVenue.GuiPackageFormularioVenue.GuiFormilarioVenue;
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

public class GuiVenue {
    private JPanel PanelPrincipalVenue;
    private JPanel JpanelTituloPrincipal;
    private JLabel eventMasterProMenuLabel;
    private JPanel JPanelMenuEvent;
    private JButton exitButton;
    private JButton modifyVenueButton;
    private JButton createVenueButton;
    private JButton backToMainMenuButton;
    private JButton ButtonShowVenue;
    private JButton ButtonShowVenueDetails;
    private JButton ButtonRemoveVenue;

    public GuiVenue() {


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelPrincipalVenue);
                currentFrame.dispose(); // Cierra la ventana actual

                JFrame frame = new JFrame("Exit");
                JOptionPane.showMessageDialog(frame, "Thank you for using Event Master Pro!", "Exit", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0); // Cierra la aplicación

            }
        });
        backToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(PanelPrincipalVenue);
                currentFrame.dispose(); // Cierra la ventana actual

                GuiMainMenu guiMainMenu = new GuiMainMenu();
                JFrame frame = new JFrame("Main Menu");
                frame.setContentPane(guiMainMenu.getPanelPrincipalMainMenu());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

            }
        });

        createVenueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiFormilarioVenue formulario = new GuiFormilarioVenue();

                JFrame frame = new JFrame("Create Event");
                frame.setContentPane(formulario.getPanelPrincipalFormularioVenue());
                formulario.getButtonDeleteVenue().setVisible(false);
                formulario.getButtonModifyVenue().setVisible(false);
                formulario.getTextFieldIDVenue().setEditable(false);
                frame.setSize(600, 500);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });
        modifyVenueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        ButtonRemoveVenue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Instancia del formulario de Venue
                GuiFormilarioVenue formularioVenue = new GuiFormilarioVenue();

                // Configura los campos y botones visibles para eliminar
                formularioVenue.getTextFieldIDVenue().setEditable(true);
                formularioVenue.getButtonDeleteVenue().setVisible(true);
                formularioVenue.getButtonCreateVenue().setVisible(false);
                formularioVenue.getButtonModifyVenue().setVisible(false);
                formularioVenue.getTextFieldAddresVenue().setVisible(false);

                // Ocultar los demás campos
                formularioVenue.getPanelNameVenue().setVisible(false);
                formularioVenue.getPanelComboboxLocation().setVisible(false);
                formularioVenue.getPanelCapavityVenue().setVisible(false);

                // Mostrar formulario
                JFrame frame = new JFrame("Delete Venue");
                frame.setContentPane(formularioVenue.getPanelPrincipalFormularioVenue());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        ButtonShowVenue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiFormilarioVenue formularioVenue = new GuiFormilarioVenue();

                // Configura los campos y botones visibles para eliminar
                formularioVenue.getTextFieldIDVenue().setEditable(true);
                formularioVenue.getButtonDeleteVenue().setVisible(true);
                formularioVenue.getButtonCreateVenue().setVisible(false);
                formularioVenue.getButtonModifyVenue().setVisible(false);
                formularioVenue.getTextFieldAddresVenue().setVisible(false);

                // Ocultar los demás campos
                formularioVenue.getPanelNameVenue().setVisible(false);
                formularioVenue.getPanelComboboxLocation().setVisible(false);
                formularioVenue.getPanelCapavityVenue().setVisible(false);

                // Mostrar formulario
                JFrame frame = new JFrame("Show Venue");
                frame.setContentPane(formularioVenue.getPanelPrincipalFormularioVenue());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        ButtonShowVenueDetails.addActionListener(new ActionListener() {
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
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelPrincipalVenue = new JPanel();
        PanelPrincipalVenue.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        PanelPrincipalVenue.setBackground(new Color(-15591660));
        panel1.add(PanelPrincipalVenue, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        JpanelTituloPrincipal = new JPanel();
        JpanelTituloPrincipal.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        JpanelTituloPrincipal.setBackground(new Color(-15591660));
        PanelPrincipalVenue.add(JpanelTituloPrincipal, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        eventMasterProMenuLabel = new JLabel();
        Font eventMasterProMenuLabelFont = this.$$$getFont$$$("Roboto Light", Font.BOLD, 28, eventMasterProMenuLabel.getFont());
        if (eventMasterProMenuLabelFont != null) eventMasterProMenuLabel.setFont(eventMasterProMenuLabelFont);
        eventMasterProMenuLabel.setForeground(new Color(-330753));
        eventMasterProMenuLabel.setText("Event Master Pro ");
        JpanelTituloPrincipal.add(eventMasterProMenuLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        JpanelTituloPrincipal.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        JPanelMenuEvent = new JPanel();
        JPanelMenuEvent.setLayout(new GridLayoutManager(10, 1, new Insets(0, 0, 0, 0), -1, -1));
        JPanelMenuEvent.setBackground(new Color(-15591660));
        PanelPrincipalVenue.add(JPanelMenuEvent, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(207, 298), null, 0, false));
        exitButton = new JButton();
        exitButton.setBackground(new Color(-14829228));
        exitButton.setEnabled(true);
        exitButton.setForeground(new Color(-330753));
        exitButton.setText("Exit");
        JPanelMenuEvent.add(exitButton, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        modifyVenueButton = new JButton();
        modifyVenueButton.setBackground(new Color(-14829228));
        modifyVenueButton.setEnabled(true);
        modifyVenueButton.setForeground(new Color(-330753));
        modifyVenueButton.setText("modify Venue ");
        JPanelMenuEvent.add(modifyVenueButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createVenueButton = new JButton();
        createVenueButton.setBackground(new Color(-14829228));
        createVenueButton.setEnabled(true);
        createVenueButton.setForeground(new Color(-330753));
        createVenueButton.setText("create Venue");
        JPanelMenuEvent.add(createVenueButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        JPanelMenuEvent.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        JPanelMenuEvent.add(spacer3, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        JPanelMenuEvent.add(spacer4, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        ButtonRemoveVenue = new JButton();
        ButtonRemoveVenue.setBackground(new Color(-14829228));
        ButtonRemoveVenue.setEnabled(true);
        ButtonRemoveVenue.setForeground(new Color(-330753));
        ButtonRemoveVenue.setText("remove Venue");
        JPanelMenuEvent.add(ButtonRemoveVenue, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ButtonShowVenue = new JButton();
        ButtonShowVenue.setBackground(new Color(-14829228));
        ButtonShowVenue.setEnabled(true);
        ButtonShowVenue.setForeground(new Color(-330753));
        ButtonShowVenue.setText("show Venues");
        JPanelMenuEvent.add(ButtonShowVenue, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ButtonShowVenueDetails = new JButton();
        ButtonShowVenueDetails.setBackground(new Color(-14829228));
        ButtonShowVenueDetails.setEnabled(true);
        ButtonShowVenueDetails.setForeground(new Color(-330753));
        ButtonShowVenueDetails.setText("show Venue Details");
        JPanelMenuEvent.add(ButtonShowVenueDetails, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        backToMainMenuButton = new JButton();
        backToMainMenuButton.setBackground(new Color(-14829228));
        backToMainMenuButton.setEnabled(true);
        backToMainMenuButton.setForeground(new Color(-330753));
        backToMainMenuButton.setText("Back to Main Menu");
        JPanelMenuEvent.add(backToMainMenuButton, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-15591660));
        PanelPrincipalVenue.add(panel2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setBackground(new Color(-15591660));
        label1.setEnabled(true);
        Font label1Font = this.$$$getFont$$$(null, -1, 72, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-330753));
        label1.setText("Welcome To Manage Venue");
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

    public JPanel getPanelPrincipalVenue() {
        return PanelPrincipalVenue;
    }
}
