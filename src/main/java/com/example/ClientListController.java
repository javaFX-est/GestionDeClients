package com.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ClientListController {
    @FXML
    private TableView<Client> clientTable;
    
    @FXML
    private TableColumn<Client, String> nomCol;
    
    @FXML
    private TableColumn<Client, String> prenomCol;
    
    @FXML
    private TableColumn<Client, String> emailCol;
    
    @FXML
    private TableColumn<Client, String> telephoneCol;
    
    @FXML
    private TableColumn<Client, String> sexeCol;
    
    @FXML
    private Button actualiserButton;
    
    @FXML
    private Button backButton;
    
    private List<Client> clients;
    private Stage primaryStage;
    private ObservableList<Client> clientList;
    
    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
    
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    @FXML
    private void initialize() {
        // This will be called after FXML loading
    }
    
    public void initializeTable() {
        // Set up table columns
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        telephoneCol.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        sexeCol.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        
        // Populate table
        clientList = FXCollections.observableArrayList(clients);
        clientTable.setItems(clientList);
    }
    
    @FXML
    private void handleActualiser() {
        if (clientList != null) {
            clientList.clear();
            clientList.addAll(clients);
        }
    }
    
    @FXML
    private void handleRetour() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/add-client.fxml"));
            Parent root = loader.load();
            
            AddClientController controller = loader.getController();
            controller.setClients(clients);
            controller.setPrimaryStage(primaryStage);
            
            primaryStage.setScene(new Scene(root, 900, 600));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

