package app;

import java.io.*;
import java.net.*;
import java.sql.*;
import Connection.JDBC;

public class AutoSalonServer {
    public static void main(String[] args) {
        try {
            // Start the server
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                
                // Handle the client in a new thread
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Handle client requests
            String request;
            while ((request = in.readLine()) != null) {
                System.out.println("Received request: " + request);
                String response = handleRequest(request);
                out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String handleRequest(String request) {
        // Here you can handle different requests from the client
        // For example, you can query the database based on the request
        if (request.equals("GET_CLIENTS")) {
            return getClients();
        }
        return "Unknown request";
    }

    private String getClients() {
        StringBuilder clientsData = new StringBuilder();
        try {
            JDBC.connect();
            String sql = "SELECT * FROM clients"; // Adjust the query as needed
            PreparedStatement stmt = JDBC.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                clientsData.append(rs.getString("name")).append(", "); // Adjust according to your table structure
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBC.close();
        }
        return clientsData.toString();
    }
}