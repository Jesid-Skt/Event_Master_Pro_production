package Ui.GuiPackageFinancilas.GuiPackageFormilarioFinancial;

import DTOS.EventDTO;
import DTOS.FinancialsDTO;
import Repository.EventRepository;
import Repository.VenueRepository;
import Services.EventService;
import Services.FinanceService;
import Services.VenueService;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;

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
    private JButton ViewFinancialsSumary;
    private JLabel LabelViewFinancilaSummry;
    private JPanel PanelViewFinancials;
    private JLabel LabelViewFinancials;
    private FinanceService financeService;
    private EventRepository eventRepository;

    public GuiFormularioFinancials() {
        // Cargar datos al iniciar
        eventRepository = new EventRepository();
        cargarEventosEnComboBox();

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
                        JOptionPane.showMessageDialog(null, "Debe ingresar al menos un valor (budget, income o expense).", "Error", JOptionPane.ERROR_MESSAGE);
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
        ViewFinancialsSumary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Asegúrate de que tu FinanceService esté correctamente inicializado
                    FinanceService financeService = new FinanceService();

                    // Obtener todos los registros financieros
                    List<FinancialsDTO> allFinancials = financeService.getAllFinancials();

                    // Validar si hay registros
                    if (allFinancials == null || allFinancials.isEmpty()) {
                        LabelViewFinancials.setText("❌ No hay registros financieros.");
                        return;
                    }

                    // Construir el resumen en HTML
                    StringBuilder sb = new StringBuilder("<html>");
                    for (FinancialsDTO dto : allFinancials) {
                        sb.append("<b>🧾 ID:</b> ").append(dto.getFinancialId())
                                .append("<br><b>📌 Evento:</b> ").append(dto.getEventId())
                                .append("<br><b>💰 Presupuesto:</b> $").append(dto.getBudget() != null ? dto.getBudget() : "N/A")
                                .append("<br><b>📥 Ingreso:</b> $").append(dto.getIncome() != null ? dto.getIncome() : "N/A")
                                .append("<br><b>📤 Gasto:</b> $").append(dto.getExpense() != null ? dto.getExpense() : "N/A")
                                .append("<br>——————————————<br>");
                    }
                    sb.append("</html>");

                    // Mostrar en el label
                    LabelViewFinancials.setText(sb.toString());

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al visualizar finanzas: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        ButtonViewFinancials.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FinanceService financeService = new FinanceService();
                    String resumen = financeService.getFinancialSummary();

                    // Convertimos los saltos de línea a <br> para HTML
                    resumen = resumen.replaceAll("\n", "<br>");

                    getLabelViewFinancials().setText("<html>" + resumen + "</html>");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al obtener el resumen: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
        ButtonViewFinancials.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        ViewFinancialsSumary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void cargarEventosEnComboBox() {
        try {
            VenueRepository venueRepository = new VenueRepository();
            VenueService venueService = new VenueService(venueRepository);

            EventRepository repository = new EventRepository();
            EventService eventService = new EventService(venueService, repository);

            List<EventDTO> eventos = eventService.getAllEvents();

            comboBoxListEVent.removeAllItems();
            for (EventDTO evento : eventos) {
                comboBoxListEVent.addItem(evento);
            }

            // Mostrar solo el nombre del evento en el combo
            comboBoxListEVent.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof EventDTO) {
                        setText(((EventDTO) value).getEventName());
                    }
                    return c;
                }
            });

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar eventos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }


    public JPanel getPanelPrincipalFormularioFinancials() {
        return PanelPrincipalFormularioFinancials;
    }

    // Métodos $$$setupUI$$$ y $$$getFont$$$ generados por IntelliJ permanecen sin modificar

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
        panel1.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
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
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-15591660));
        panel2.setEnabled(false);
        panel1.add(panel2, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel2.setBorder(BorderFactory.createTitledBorder(null, "Valor  income", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        textFieldIncome = new JTextField();
        panel2.add(textFieldIncome, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        PanelbuttonFinancials = new JPanel();
        PanelbuttonFinancials.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        PanelbuttonFinancials.setBackground(new Color(-15591660));
        panel1.add(PanelbuttonFinancials, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        registreBudgetButton = new JButton();
        registreBudgetButton.setBackground(new Color(-14829228));
        registreBudgetButton.setText("Registre Budget");
        PanelbuttonFinancials.add(registreBudgetButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ButtonViewFinancials = new JButton();
        ButtonViewFinancials.setBackground(new Color(-14829228));
        ButtonViewFinancials.setText("View Financials");
        PanelbuttonFinancials.add(ButtonViewFinancials, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ViewFinancialsSumary = new JButton();
        ViewFinancialsSumary.setBackground(new Color(-14829228));
        ViewFinancialsSumary.setText("View Financials Summary");
        PanelbuttonFinancials.add(ViewFinancialsSumary, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PanelcomboboxListEvent = new JPanel();
        PanelcomboboxListEvent.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelcomboboxListEvent.setBackground(new Color(-15591660));
        PanelcomboboxListEvent.setEnabled(false);
        panel1.add(PanelcomboboxListEvent, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        PanelcomboboxListEvent.setBorder(BorderFactory.createTitledBorder(null, "Seleccione una opcion", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        comboBoxListEVent = new JComboBox();
        PanelcomboboxListEvent.add(comboBoxListEVent, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setBackground(new Color(-15591660));
        panel1.add(panel3, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        LabelViewFinancilaSummry = new JLabel();
        LabelViewFinancilaSummry.setText("");
        panel3.add(LabelViewFinancilaSummry, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PanelViewFinancials = new JPanel();
        PanelViewFinancials.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        PanelViewFinancials.setBackground(new Color(-15591660));
        panel1.add(PanelViewFinancials, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        LabelViewFinancials = new JLabel();
        LabelViewFinancials.setText("");
        PanelViewFinancials.add(LabelViewFinancials, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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

    public JPanel getPanelPrincipalTituloFinancials() {
        return PanelPrincipalTituloFinancials;
    }

    public JTextField getTextFieldBudget() {
        return textFieldBudget;
    }

    public JPanel getPanelbudget() {
        return Panelbudget;
    }

    public JPanel getPanelExpense() {
        return PanelExpense;
    }

    public JTextField getTextFieldExpense() {
        return textFieldExpense;
    }

    public JTextField getTextFieldIncome() {
        return textFieldIncome;
    }

    public JButton getRegistreBudgetButton() {
        return registreBudgetButton;
    }

    public JPanel getPanelbuttonFinancials() {
        return PanelbuttonFinancials;
    }

    public JPanel getPanelcomboboxListEvent() {
        return PanelcomboboxListEvent;
    }

    public JComboBox<EventDTO> getComboBoxListEVent() {
        return comboBoxListEVent;
    }

    public JButton getButtonViewFinancials() {
        return ButtonViewFinancials;
    }

    public JButton getViewFinancialsSumary() {
        return ViewFinancialsSumary;
    }

    public JLabel getLabelViewFinancilaSummry() {
        return LabelViewFinancilaSummry;
    }

    public JPanel getPanelViewFinancials() {
        return PanelViewFinancials;
    }

    public JLabel getLabelViewFinancials() {
        return LabelViewFinancials;
    }
}
