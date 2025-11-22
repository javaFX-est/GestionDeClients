package com.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ClientManagementApp extends Application {
    private List<Client> clients = new ArrayList<>();
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Clients management");
        primaryStage.setScene(createAddClientScene());
        primaryStage.setWidth(900);
        primaryStage.setHeight(600);
        primaryStage.show();
    }

    private Scene createAddClientScene() {
        // Main container
        HBox mainContainer = new HBox();
        mainContainer.setPrefSize(900, 600);

        // Left Panel (Light Blue)
        VBox leftPanel = new VBox(20);
        leftPanel.setPrefWidth(300);
        leftPanel.setAlignment(Pos.CENTER);
        leftPanel.setStyle("-fx-background-color: #E6F3FF;");
        leftPanel.setPadding(new Insets(40));

        // Title "Gestion de clients"
        Label titleLabel = new Label("Gestion de clients");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, FontPosture.ITALIC, 28));
        titleLabel.setTextFill(Color.rgb(64, 64, 64));
        titleLabel.setWrapText(true);
        titleLabel.setAlignment(Pos.CENTER);

        // User icon placeholder (circular white frame)
        StackPane iconContainer = new StackPane();
        iconContainer.setPrefSize(120, 120);
        iconContainer.setStyle("-fx-background-color: white; -fx-background-radius: 60;");
        
        // Simple user icon representation
        Label userIcon = new Label("👤");
        userIcon.setFont(Font.font(60));
        iconContainer.getChildren().add(userIcon);

        leftPanel.getChildren().addAll(titleLabel, iconContainer);

        // Right Panel (White)
        VBox rightPanel = new VBox(20);
        rightPanel.setPrefWidth(600);
        rightPanel.setPadding(new Insets(40));
        rightPanel.setStyle("-fx-background-color: white;");

        // Title "Ajouter un client"
        Label formTitle = new Label("Ajouter un client");
        formTitle.setFont(Font.font("System", FontWeight.BOLD, 24));
        formTitle.setTextFill(Color.rgb(64, 64, 64));
        formTitle.setUnderline(true);

        // Form fields
        VBox formFields = new VBox(15);
        
        // Nom
        Label nomLabel = new Label("Nom");
        TextField nomField = new TextField();
        nomField.setStyle("-fx-border-color: #B3D9FF; -fx-border-width: 1;");
        nomField.setPrefHeight(30);

        // Prenom
        Label prenomLabel = new Label("Prenom");
        TextField prenomField = new TextField();
        prenomField.setPromptText("entrer_le_Prenom");
        prenomField.setStyle("-fx-border-color: #B3D9FF; -fx-border-width: 1;");
        prenomField.setPrefHeight(30);

        // Email
        Label emailLabel = new Label("Email");
        TextField emailField = new TextField();
        emailField.setPromptText("entrer_un_email_valid");
        emailField.setStyle("-fx-border-color: #B3D9FF; -fx-border-width: 1;");
        emailField.setPrefHeight(30);

        // Telephone
        Label telephoneLabel = new Label("Telephone");
        TextField telephoneField = new TextField();
        telephoneField.setPromptText("entrer_mu,ero_telephone");
        telephoneField.setStyle("-fx-border-color: #B3D9FF; -fx-border-width: 1;");
        telephoneField.setPrefHeight(30);

        // Adresse
        Label adresseLabel = new Label("Adresse");
        TextField adresseField = new TextField();
        adresseField.setPromptText("entrer_une_adresse");
        adresseField.setStyle("-fx-border-color: #B3D9FF; -fx-border-width: 1;");
        adresseField.setPrefHeight(30);

        // Sexe (Gender) - Radio buttons
        Label sexeLabel = new Label("Sexe");
        HBox sexeContainer = new HBox(20);
        ToggleGroup sexeGroup = new ToggleGroup();
        RadioButton femininRadio = new RadioButton("Feminin");
        femininRadio.setToggleGroup(sexeGroup);
        femininRadio.setSelected(true);
        RadioButton masculinRadio = new RadioButton("Masculin");
        masculinRadio.setToggleGroup(sexeGroup);
        sexeContainer.getChildren().addAll(femininRadio, masculinRadio);

        formFields.getChildren().addAll(
            nomLabel, nomField,
            prenomLabel, prenomField,
            emailLabel, emailField,
            telephoneLabel, telephoneField,
            adresseLabel, adresseField,
            sexeLabel, sexeContainer
        );

        // Buttons
        VBox buttonsContainer = new VBox(10);
        buttonsContainer.setAlignment(Pos.CENTER);
        
        Button validerButton = new Button("Valider");
        validerButton.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 14;");
        validerButton.setPrefWidth(200);
        validerButton.setPrefHeight(35);
        
        Button listeClientsButton = new Button("Liste clients");
        listeClientsButton.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 14;");
        listeClientsButton.setPrefWidth(200);
        listeClientsButton.setPrefHeight(35);
        
        Button annulerButton = new Button("Annuler");
        annulerButton.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 14;");
        annulerButton.setPrefWidth(200);
        annulerButton.setPrefHeight(35);

        buttonsContainer.getChildren().addAll(validerButton, listeClientsButton, annulerButton);

        // Valider button action
        validerButton.setOnAction(e -> {
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
            nomField.clear();
            prenomField.clear();
            emailField.clear();
            telephoneField.clear();
            adresseField.clear();
            femininRadio.setSelected(true);
        });

        // Liste clients button action
        listeClientsButton.setOnAction(e -> {
            primaryStage.setScene(createClientListScene());
        });

        // Annuler button action
        annulerButton.setOnAction(e -> {
            nomField.clear();
            prenomField.clear();
            emailField.clear();
            telephoneField.clear();
            adresseField.clear();
            femininRadio.setSelected(true);
        });

        rightPanel.getChildren().addAll(formTitle, formFields, buttonsContainer);

        mainContainer.getChildren().addAll(leftPanel, rightPanel);

        return new Scene(mainContainer);
    }

    private Scene createClientListScene() {
        VBox mainContainer = new VBox(20);
        mainContainer.setPadding(new Insets(30));
        mainContainer.setStyle("-fx-background-color: #E6F3FF;");

        // Title and Refresh button
        HBox titleContainer = new HBox(20);
        titleContainer.setAlignment(Pos.CENTER_LEFT);
        
        Label titleLabel = new Label("Liste de Clients");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, FontPosture.ITALIC, 28));
        titleLabel.setTextFill(Color.rgb(64, 64, 64));
        titleLabel.setUnderline(true);

        Button actualiserButton = new Button("Actualiser");
        actualiserButton.setStyle("-fx-background-color: #4A90E2; -fx-text-fill: white; -fx-font-size: 14;");
        actualiserButton.setPrefHeight(30);

        titleContainer.getChildren().addAll(actualiserButton, titleLabel);

        // Table
        TableView<Client> table = new TableView<>();
        table.setPrefHeight(400);

        TableColumn<Client, String> nomCol = new TableColumn<>("Nom");
        nomCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNom()));
        nomCol.setPrefWidth(150);

        TableColumn<Client, String> prenomCol = new TableColumn<>("Prenom");
        prenomCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPrenom()));
        prenomCol.setPrefWidth(150);

        TableColumn<Client, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
        emailCol.setPrefWidth(200);

        TableColumn<Client, String> telephoneCol = new TableColumn<>("Telephone");
        telephoneCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTelephone()));
        telephoneCol.setPrefWidth(150);

        TableColumn<Client, String> sexeCol = new TableColumn<>("Sexe");
        sexeCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getSexe()));
        sexeCol.setPrefWidth(100);

        table.getColumns().addAll(nomCol, prenomCol, emailCol, telephoneCol, sexeCol);

        ObservableList<Client> clientList = FXCollections.observableArrayList(clients);
        table.setItems(clientList);

        // Back button
        Button backButton = new Button("Retour");
        backButton.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 14;");
        backButton.setPrefWidth(150);
        backButton.setPrefHeight(35);
        backButton.setOnAction(e -> {
            primaryStage.setScene(createAddClientScene());
        });

        actualiserButton.setOnAction(e -> {
            clientList.clear();
            clientList.addAll(clients);
        });

        mainContainer.getChildren().addAll(titleContainer, table, backButton);

        return new Scene(mainContainer, 900, 600);
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

    public static void main(String[] args) {
        launch(args);
    }
}

