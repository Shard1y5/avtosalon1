package app;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryWindow extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public InventoryWindow() {
        setTitle("Материальные ценности на складе");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create table model with header
        String[] columnNames = {"Шифр", "Наименование", "Ед. изм.", "Цена закуп., руб", "Цена реализ., руб", "Кол-во", "Сумма, руб"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);

        // Add data to the table
        addDataToTable();

        // Create scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(table);

        // Create group box for "Группа товаров"
        JPanel groupPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel groupLabel = new JLabel("Группа товаров:");
        //JMenu groupMenu = new JMenu("Марки автомобилей");
        // Создаем массив с марками автомобилей
        String[] carBrands = {"ВАЗ", "Honda", "Ford", "BMW", "Mercedes"};
        
        // Создаем JComboBox с марками автомобилей
        JComboBox<String> carBrandComboBox = new JComboBox<>(carBrands);

        groupPanel.add(groupLabel);
        groupPanel.add(carBrandComboBox);

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton printButton = new JButton("Печать");
        JButton groupButton = new JButton("Группы товаров");
        buttonPanel.add(printButton);
        buttonPanel.add(groupButton);

        // Add listeners to buttons
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement print functionality
            }
        });

        groupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement group functionality
            }
        }); 

        // Create main panel and add components
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(groupPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to the frame
        add(mainPanel);
    }

    private void addDataToTable() {
        // Example data for the table
        Object[][] data = {
            {"0138", "BA3-11183 \"Калина\"", "шт", 182775, 243700, 7, 1705900},
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