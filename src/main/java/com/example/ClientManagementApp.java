package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientManagementApp extends Application {
    private static List<Client> clients = new ArrayList<>();
    private Stage primaryStage;

    public static List<Client> getClients() {
        return clients;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Clients management");
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/add-client.fxml"));
            Parent root = loader.load();
            
            AddClientController controller = loader.getController();
            controller.setClients(clients);
            controller.setPrimaryStage(primaryStage);
            
            primaryStage.setScene(new Scene(root, 900, 600));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading FXML file: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

