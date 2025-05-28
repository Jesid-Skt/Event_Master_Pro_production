package Ui.GuiPackageFinancilas.GuiPackageFormilarioFinancial;

import DTOS.EventDTO;
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
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.math.BigDecimal;
import Services.FinanceService;


public class GuiFormularioFinancials {

    private JPanel PanelPrincipalFormularioFinancials;
    private JPanel PanelPrincipalTituloFinancials;
    private JTextField textFieldBudget;
    private JPanel Panelbudget;
    private JPanel PanelExpense;
    private JTextField textFieldExpense;
    private JTextField textFieldIncome;
    private JButton registreBudgetButton;
    private JPanel PanelbuttonFinancials;
    private JPanel PanelcomboboxListEvent;
    private JComboBox<EventDTO> comboBoxListEVent;
    private JButton ButtonViewFinancials;
    private JButton ButtonSummaryFinancials;
    private JPanel PanelItextFieldIncome;
    private JPanel PanelLabelViewFinancials;
    private JLabel LabelViewFinancials;

    private void cargarEventosEnComboBox() {
        try {
            VenueRepository venueRepository = new VenueRepository();
            VenueService venueService = new VenueService(venueRepository);

            EventRepository eventRepository = new EventRepository();
            EventService eventService = new EventService(venueService, eventRepository);

            List<EventDTO> eventos = eventService.getAllEvents();
            comboBoxListEVent.removeAllItems();

            for (EventDTO evento : eventos) {
                comboBoxListEVent.addItem(evento);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar eventos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public GuiFormularioFinancials() {
        cargarEventosEnComboBox(); // 👈 Llenar combo al iniciar

        registreBudgetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FinanceService financeService = new FinanceService();

                try {
                    String budgetText = textFieldBudget.getText().trim();
                    String expenseText = textFieldExpense.getText().trim();
                    String incomeText = textFieldIncome.getText().trim();
                    EventDTO selectedEvent = (EventDTO) comboBoxListEVent.getSelectedItem();

                    if (selectedEvent == null) {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar un evento.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (budgetText.isEmpty() && incomeText.isEmpty() && expenseText.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Debe ingresar al menos un valor numérico (budget, income o expense).", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    BigDecimal budget = budgetText.isEmpty() ? null : new BigDecimal(budgetText);
                    BigDecimal income = incomeText.isEmpty() ? null : new BigDecimal(incomeText);
                    BigDecimal expense = expenseText.isEmpty() ? null : new BigDecimal(expenseText);

                    boolean success = financeService.registerFinancials(selectedEvent.getEventId(), budget, income, expense);

                    if (success) {
                        JOptionPane.showMessageDialog(null, "✅ Registro exitoso.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        textFieldBudget.setText("");
                        textFieldIncome.setText("");
                        textFieldExpense.setText("");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Los campos deben contener valores numéricos válidos.", "Error de formato", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "❌ Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
        ButtonViewFinancials.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        ButtonSummaryFinancials.addActionListener(new ActionListener() {
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
        PanelPrincipalFormularioFinancials = new JPanel();
        PanelPrincipalFormularioFinancials.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelPrincipalFormularioFinancials.setBackground(new Color(-15591660));
        PanelPrincipalTituloFinancials = new JPanel();
        PanelPrincipalTituloFinancials.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelPrincipalTituloFinancials.setBackground(new Color(-15591660));
        PanelPrincipalFormularioFinancials.add(PanelPrincipalTituloFinancials, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$(null, -1, 24, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("Form Financials");
        PanelPrincipalTituloFinancials.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-15591660));
        PanelPrincipalFormularioFinancials.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Panelbudget = new JPanel();
        Panelbudget.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        Panelbudget.setBackground(new Color(-15591660));
        Panelbudget.setEnabled(false);
        panel1.add(Panelbudget, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Panelbudget.setBorder(BorderFactory.createTitledBorder(null, "Valor budget", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        textFieldBudget = new JTextField();
        Panelbudget.add(textFieldBudget, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        PanelExpense = new JPanel();
        PanelExpense.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelExpense.setBackground(new Color(-15591660));
        PanelExpense.setEnabled(false);
        panel1.add(PanelExpense, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PanelExpense.setBorder(BorderFactory.createTitledBorder(null, "Valor expense", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        textFieldExpense = new JTextField();
        PanelExpense.add(textFieldExpense, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        PanelItextFieldIncome = new JPanel();
        PanelItextFieldIncome.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelItextFieldIncome.setBackground(new Color(-15591660));
        PanelItextFieldIncome.setEnabled(false);
        panel1.add(PanelItextFieldIncome, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PanelItextFieldIncome.setBorder(BorderFactory.createTitledBorder(null, "Valor  income", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        textFieldIncome = new JTextField();
        PanelItextFieldIncome.add(textFieldIncome, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        PanelbuttonFinancials = new JPanel();
        PanelbuttonFinancials.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelbuttonFinancials.setBackground(new Color(-15591660));
        panel1.add(PanelbuttonFinancials, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        registreBudgetButton = new JButton();
        registreBudgetButton.setBackground(new Color(-14829228));
        registreBudgetButton.setText("Registre Budget");
        PanelbuttonFinancials.add(registreBudgetButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PanelcomboboxListEvent = new JPanel();
        PanelcomboboxListEvent.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelcomboboxListEvent.setBackground(new Color(-15591660));
        PanelcomboboxListEvent.setEnabled(false);
        panel1.add(PanelcomboboxListEvent, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PanelcomboboxListEvent.setBorder(BorderFactory.createTitledBorder(null, "Seleccione una opcion", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        comboBoxListEVent = new JComboBox();
        PanelcomboboxListEvent.add(comboBoxListEVent, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ButtonViewFinancials = new JButton();
        ButtonViewFinancials.setBackground(new Color(-14829228));
        ButtonViewFinancials.setText("view Financials");
        panel1.add(ButtonViewFinancials, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ButtonSummaryFinancials = new JButton();
        ButtonSummaryFinancials.setBackground(new Color(-14829228));
        ButtonSummaryFinancials.setText("View Summary Financials");
        panel1.add(ButtonSummaryFinancials, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PanelLabelViewFinancials = new JPanel();
        PanelLabelViewFinancials.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelLabelViewFinancials.setBackground(new Color(-15591660));
        panel1.add(PanelLabelViewFinancials, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        LabelViewFinancials = new JLabel();
        LabelViewFinancials.setText("");
        PanelLabelViewFinancials.add(LabelViewFinancials, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        return PanelPrincipalFormularioFinancials;
    }

    public JPanel getPanelPrincipalFormularioFinancials() {
        return PanelPrincipalFormularioFinancials;
    }

    public JTextField getTextFieldBudget() {
        return textFieldBudget;
    }

    public JPanel getPanelPrincipalTituloFinancials() {
        return PanelPrincipalTituloFinancials;
    }

    public JTextField getTextFieldExpense() {
        return textFieldExpense;
    }

    public JTextField getTextFieldIncome() {
        return textFieldIncome;
    }

    public JComboBox<EventDTO> getComboBoxListEVent() {
        return comboBoxListEVent;
    }

    public JButton getRegistreBudgetButton() {
        return registreBudgetButton;
    }
}
