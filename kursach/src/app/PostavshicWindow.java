package app;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PostavshicWindow extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public PostavshicWindow() {
        setTitle("Поставщики");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create table model with header
        String[] columnNames = {"Шифр", "Наименование", "Адрес", "Телефоны", "Реквизиты", "ИНН", "КПП"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);

        // Add data to the table
        addDataToTable();

        // Create scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(table);

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Add listeners to buttons


        // Create main panel and add components
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to the frame
        add(mainPanel);
    }

    private void addDataToTable() {
        // Example data for the table
        Object[][] data = {
            {"006", "ООО", "ак", 182775, 243700, 7, 1705900},
            // ... другие записи
        };

        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InventoryWindow window = new InventoryWindow();
            window.setVisible(true);
        });
    }
}