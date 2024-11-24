package app;
import javax.swing.*; // Ensure this import is present for Swing components
import java.awt.*; // Ensure this import is present for layout managers
import java.awt.event.*; // Ensure this import is present for ActionListener
import java.sql.*; // Ensure this import is present for SQL connection

public class AutoSalonApp extends JFrame implements ActionListener {

    // GUI components
    private JMenuBar menuBar;
    private JMenu fileMenu, helpMenu, documentsMenu, tovarMenu;
    private JMenuItem exitMenuItem, priceListItem, applicationItem, invoiceItem, incomingItem, realizationItem, postavshic, rekvisit, sklad, manager, client;
    private JPanel mainPanel;
    private JLabel photoLabel;

    // Database connection details
    private String dbURL = "jdbc:mysql://localhost:3306/autosalon";
    private String dbUser    = "root";
    private String dbPassword = "Ruy20hpmup2)";

    // Database connection
    private Connection connection;

    // Customizable sizes
    private int buttonWidth;
    private int buttonHeight;
    private int photoWidth;
    private int photoHeight;

    public AutoSalonApp() {
        // Set up JFrame
        setTitle("Автосалон");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true); // Убираем рамку окна для полноэкранного режима

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Устанавливаем полноэкранный режим
        setVisible(true); // Делаем окно видимым

        // Initialize menu bar
        menuBar = new JMenuBar();
        menuBar.setFont(new Font("Arial", Font.PLAIN, 24));
        menuBar.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        menuBar.setBackground(new Color(153, 153, 255));
        fileMenu = new JMenu("Файл");
        fileMenu.setFont(new Font("Arial", Font.PLAIN, 20));
        helpMenu = new JMenu("Справка");
        helpMenu.setFont(new Font("Arial", Font.PLAIN, 20));
        documentsMenu = new JMenu("Документы");
        documentsMenu.setFont(new Font("Arial", Font.PLAIN, 20));
        tovarMenu = new JMenu("Товар");
        tovarMenu.setFont(new Font("Arial", Font.PLAIN, 20));
        exitMenuItem = new JMenuItem("Выход");
        exitMenuItem.setFont(new Font("Arial", Font.PLAIN, 20));
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);

        // Adding document items to the documents menu
        priceListItem = new JMenuItem("Прайс-лист");
        priceListItem.setFont(new Font("Arial", Font.PLAIN, 20));
        applicationItem = new JMenuItem("Заявка");
        applicationItem.setFont(new Font("Arial", Font.PLAIN, 20));
        invoiceItem = new JMenuItem("Счет");
        invoiceItem.setFont(new Font("Arial", Font.PLAIN, 20));
        incomingItem = new JMenuItem("Приход товара");
        incomingItem.setFont(new Font("Arial", Font.PLAIN, 20));
        realizationItem = new JMenuItem("Реализация товара");
        realizationItem.setFont(new Font("Arial", Font.PLAIN, 20));

        // Adding tovar items to the tovar menu
        postavshic = new JMenuItem("Поставщики");
        postavshic.setFont(new Font("Arial", Font.PLAIN, 20));
        sklad = new JMenuItem("Склад");
        sklad.setFont(new Font("Arial", Font.PLAIN, 20));

        // Adding help items to the help menu
        rekvisit = new JMenuItem("Реквизиты организации");
        rekvisit.setFont(new Font("Arial", Font.PLAIN, 20));
        manager = new JMenuItem("Менеджеры организации");
        manager.setFont(new Font("Arial", Font.PLAIN, 20));
        client = new JMenuItem("Клиенты организации");
        client.setFont(new Font("Arial", Font.PLAIN, 20));

        documentsMenu.add(priceListItem);
        documentsMenu.add(applicationItem);
        documentsMenu.add(invoiceItem);
        documentsMenu.add(incomingItem);
        documentsMenu.add(realizationItem);
        tovarMenu.add(postavshic);
        tovarMenu.add(sklad);
        tovarMenu.add(priceListItem);
        helpMenu.add(rekvisit);
        helpMenu.add(manager);
        helpMenu.add(client);

        menuBar.add(fileMenu);
        menuBar.add(documentsMenu);
        menuBar.add(helpMenu);
        menuBar.add(tovarMenu);
        setJMenuBar(menuBar);

        // Initialize main panel
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(220, 220, 220));
        add(mainPanel);

        // Create a panel for the photo and buttons
        JPanel photoAndButtonsPanel = new JPanel(new GridBagLayout());
        mainPanel.add(photoAndButtonsPanel, BorderLayout.CENTER);

        // Add photo to the left
        JPanel leftPanel = new JPanel(new GridBagLayout());
        photoAndButtonsPanel.add(leftPanel, new GridBagConstraints(0, 0, 1, 1, 0.1, 1.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        photoLabel = new JLabel();
        leftPanel.add(photoLabel, new GridBagConstraints());

        // Create a panel for buttons
        JPanel buttonsPanel = new JPanel(new GridLayout(5, 1, 10, 10)); // Установка пробелов между кнопками
        photoAndButtonsPanel.add(buttonsPanel, new GridBagConstraints(1, 0, 1, 1, 0.9, 1.0, GridBagConstraints.EAST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        // Create buttons
        JButton buttonWarehouse = new JButton("Склад");
        JButton buttonApplication = new JButton("Заявка");
        JButton buttonInvoice = new JButton("Счет");
        JButton buttonIncoming = new JButton("Приход товара");
        JButton buttonRealization = new JButton("Реализация товара");

        // Set button sizes based on current resolution
        setSizes(screenSize.width, screenSize.height);

        // Set button sizes
        buttonWarehouse.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        buttonApplication.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        buttonInvoice.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        buttonIncoming.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        buttonRealization.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        // Set button fonts based on their sizes
        setButtonFont(buttonWarehouse);
        setButtonFont(buttonApplication);
        setButtonFont(buttonInvoice);
        setButtonFont(buttonIncoming);
        setButtonFont(buttonRealization);

        // Add buttons to the panel
        buttonsPanel.add(buttonWarehouse);
        buttonsPanel.add(buttonApplication);
        buttonsPanel.add(buttonInvoice);
        buttonsPanel.add(buttonIncoming);
        buttonsPanel.add(buttonRealization);

        // Add action listeners for buttons
        buttonWarehouse.addActionListener(e -> showWarehouseMenu());
        buttonApplication.addActionListener(e -> showApplicationMenu());
        buttonInvoice.addActionListener(e -> showInvoiceMenu());
        buttonIncoming.addActionListener(e -> showIncomingMenu());
        buttonRealization.addActionListener(e -> showRealizationMenu());
        postavshic.addActionListener(e -> showPostavshicMenu());
        rekvisit.addActionListener(e -> showRekvisit());
        sklad.addActionListener(e -> showWarehouseMenu());
        priceListItem.addActionListener(e -> showPriceList());
        manager.addActionListener(e -> showManagerMenu());
        client.addActionListener(e -> showClientMenu());

        // Load and scale image
        ImageIcon originalIcon = new ImageIcon("D:/Downloads/background.jpg");
        setImageWithMargins(originalIcon, photoWidth, photoHeight, 10, 10); // Установка размеров и отступов

        // Establish database connection
        connectToDatabase();
    }

    private void setSizes(int width, int height) {
        if (width == 800 && height == 600) {
            buttonWidth = 100;
            buttonHeight = 0;
            photoWidth = 500;
            photoHeight = 400;
        } else if (width == 1920 && height == 1080) {
            buttonWidth = 150;
            buttonHeight = 40;
            photoWidth = 900;
            photoHeight = 700;
        } else if (width == 2560 && height == 1440) {
            buttonWidth = 500;
            buttonHeight = 70;
            photoWidth = 1200;
            photoHeight = 1000;
        } else {
            // Default sizes for other resolutions
            buttonWidth = width / 10; // 10% of screen width
            buttonHeight = height / 20; // 5% of screen height
            photoWidth = width; // Full width
            photoHeight = height / 2; // Half height
        }
    }

    private void setButtonFont(JButton button) {
        // Получаем размеры кнопки
        Dimension size = button.getPreferredSize();
    
        if (size.height > 0) {
            // Определяем желаемый размер шрифта в зависимости от высоты кнопки
            int fontSize = (int) (size.height * 1.2); 
    
            button.setFont(new Font("Arial", Font.PLAIN, fontSize));
        } else {
            // Устанавливаем фиксированный размер шрифта, если высота кнопки равна нулю
            button.setFont(new Font("Arial", Font.PLAIN, 24)); // Фиксированный размер
        }
    }

    private void setImageWithMargins(ImageIcon icon, int width, int height, int horizontalMargin, int verticalMargin) {
        Image scaledImage = icon.getImage().getScaledInstance(width - 2 * horizontalMargin, height - 2 * verticalMargin, Image.SCALE_SMOOTH);
        photoLabel.setIcon(new ImageIcon(scaledImage));
        photoLabel.setBorder(BorderFactory.createEmptyBorder(verticalMargin, horizontalMargin, verticalMargin, horizontalMargin)); // Установка отступов
    }

    private void connectToDatabase() {
        try {
            connection = DriverManager.getConnection(dbURL, dbUser , dbPassword);
            System.out.println("Подключение к базе данных успешно !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showWarehouseMenu() {
        // Create and show the InventoryWindow
        InventoryWindow inventoryWindow = new InventoryWindow();
        inventoryWindow.setVisible(true);
    }

    private void showApplicationMenu() {
        // Logic to show application menu
    }

    private void showInvoiceMenu() {
        // Logic to show invoice menu
    }

    private void showIncomingMenu() {
        // Logic to show incoming menu
        IncomingWindow incomingWindow = new IncomingWindow();
        incomingWindow.setVisible(true);
    }

    private void showRealizationMenu() {
        // Logic to show realization menu
        OutcomingWindow outcomingWindow = new OutcomingWindow();
        outcomingWindow.setVisible(true);
    }

    private void showPostavshicMenu() {
        // Create and show the Postavshic Window
        PostavshicWindow postavshicWindow = new PostavshicWindow();
        postavshicWindow.setVisible(true);
    }

    private void showRekvisit() {
        // Create and show the RekvisitWindow
        RekvisitWindow.showRekvisitWindow();
    }

    private void showPriceList() {
        // Create and show the PriceListWindow
    }

    private void showManagerMenu() {
        // Create and show the ManagerWindow
    }

    private void showClientMenu() {
        ClientWindow clientWindow = new ClientWindow();
        clientWindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitMenuItem) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AutoSalonApp app = new AutoSalonApp();
            app.setVisible(true);
        });
    }
}