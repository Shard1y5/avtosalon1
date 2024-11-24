package app;
import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Connection.JDBC;

public class RekvisitWindow {

    public static void showRekvisitWindow() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Информация о компании");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Закрывать только это окно
            frame.setSize(400, 300);
            frame.setLayout(new GridLayout(8, 2));

            String[] labels = {
                "Наименование:",
                "Адрес:",
                "Телефоны:",
                "Реквизиты:",
                "ИНН:",
                "КПП:",
                "Ген. директор:",
                "Глав. бухгалтер:"
            };

            JTextField[] textFields = new JTextField[labels.length];

            // Получаем данные из базы данных
            String[] data = getDataFromDatabase();

            for (int i = 0; i < labels.length; i++) {
                JLabel label = new JLabel(labels[i]);
                textFields[i] = new JTextField(data[i] != null ? data[i] : "Нет данных");
                textFields[i].setEditable(false); // Делаем текстовые поля неизменяемыми
                frame.add(label);
                frame.add(textFields[i]);
            }

            frame.setVisible(true);
        });
    }

    private static String[] getDataFromDatabase() {
    String[] data = new String[8]; // Массив для данных из БД

    try {
        JDBC.connect();
        String sql = "SELECT name, adress, phone, rekvisit, INN, KPP, GenDir, GenBuh FROM organisationrekv"; //SQL-запрос
        PreparedStatement stmt = JDBC.connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            data[0] = rs.getString("name");
            data[1] = rs.getString("adress");
            data[2] = rs.getString("phone");
            data[3] = rs.getString("rekvisit");
            data[4] = rs.getString("INN");
            data[5] = rs.getString("KPP");
            data[6] = rs.getString("GenDir");
            data[7] = rs.getString("GenBuh");
        } else {
            for (int i = 0; i < data.length; i++) {
                data[i] = "Нет данных"; // Указываем "Нет данных" по умолчанию
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        JDBC.close(); // Закрываем соединение в блоке finally
    }

    return data;
}
}