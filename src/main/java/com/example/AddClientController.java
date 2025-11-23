package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AddClientController {
    @FXML
    private TextField nomField;
    
    @FXML
    private TextField prenomField;
    
    @FXML
    private TextField emailField;
    
    @FXML
    private TextField telephoneField;
    
    @FXML
    private TextField adresseField;
    
    @FXML
    private RadioButton femininRadio;
    
    @FXML
    private RadioButton masculinRadio;
    
    @FXML
    private ToggleGroup sexeGroup;
    
    @FXML
    private Button validerButton;
    
    @FXML
    private Button listeClientsButton;
    
    @FXML
    private Button annulerButton;
    
    private List<Client> clients;
    private Stage primaryStage;
    
    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
    
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    @FXML
    private void initialize() {
        // Initialize toggle group
        sexeGroup = new ToggleGroup();
        femininRadio.setToggleGroup(sexeGroup);
        masculinRadio.setToggleGroup(sexeGroup);
        femininRadio.setSelected(true);
    }
    
    @FXML
    private void handleValider() {
        String nom = nomField.getText().trim();
        String prenom = prenomField.getText().trim();
        String email = emailField.getText().trim();
        String telephone = telephoneField.getText().trim();
        String adresse = adresseField.getText().trim();
        String sexe = femininRadio.isSelected() ? "F" : "M";

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || telephone.isEmpty() || adresse.isEmpty()) {
            showAlert(AlertType.WARNING, "Attention", "Veuillez remplir tous les champs.");
            return;
        }

        Client client = new Client(nom, prenom, email, telephone, adresse, sexe);
        clients.add(client);

        // Show confirmation dialog
        showConfirmationDialog();

        // Clear form
        clearForm();
    }
    
    @FXML
    private void handleListeClients() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/client-list.fxml"));
            Parent root = loader.load();
            
            ClientListController controller = loader.getController();
            controller.setClients(clients);
            controller.setPrimaryStage(primaryStage);
            controller.initializeTable();
            
            primaryStage.setScene(new Scene(root, 900, 600));
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Erreur", "Impossible de charger la liste des clients.");
        }
    }
    
    @FXML
    private void handleAnnuler() {
        clearForm();
    }
    
    private void clearForm() {
        nomField.clear();
        prenomField.clear();
        emailField.clear();
        telephoneField.clear();
        adresseField.clear();
        femininRadio.setSelected(true);
    }
    
    private void showConfirmationDialog() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Enregistrement avec succes !");
        alert.getDialogPane().setStyle("-fx-background-color: white;");
        alert.showAndWait();
    }
    
    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

