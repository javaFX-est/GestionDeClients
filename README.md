# Gestion des Clients - JavaFX Application (FXML)

A JavaFX desktop application for managing clients, built with FXML files for Scene Builder compatibility.

## Features

- **Add Client Form**: Add new clients with fields for name, first name, email, phone, address, and gender
- **Client List**: View all registered clients in a table format
- **Form Validation**: Ensures all required fields are filled before submission
- **Confirmation Dialog**: Shows success message after adding a client
- **FXML-based UI**: All UI layouts are in FXML files, editable with Scene Builder

## Project Structure

```
java/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           ├── ClientManagementApp.java  (Main entry point)
│       │           ├── Client.java               (Model class)
│       │           ├── AddClientController.java  (Controller for add form)
│       │           └── ClientListController.java  (Controller for list view)
│       └── resources/
│           └── com/
│               └── example/
│                   ├── add-client.fxml          (Add client form UI)
│                   └── client-list.fxml         (Client list view UI)
├── pom.xml
└── README.md
```

## Using Scene Builder

1. **Install Scene Builder** (see installation guide below)
2. **Open FXML files** in Scene Builder:
   - Right-click `add-client.fxml` → "Open with Scene Builder"
   - Or open Scene Builder and use File → Open
3. **Edit the UI** visually by dragging and dropping components
4. **Save** - changes are automatically reflected in your application

## Building and Running

### Using Maven:

```bash
mvn clean compile
mvn javafx:run
```

### Without Maven:

1. Download JavaFX SDK 21 from https://openjfx.io/
2. Extract to a location like `C:\javafx-sdk-21`
3. Run:
   ```powershell
   $env:JAVAFX_HOME = "C:\javafx-sdk-21"
   .\run.ps1
   ```

## FXML Files

- **add-client.fxml**: Main form for adding clients
- **client-list.fxml**: Table view for displaying all clients

Both files can be edited with Scene Builder for visual UI design.

## Dependencies

- JavaFX Controls 21
- JavaFX FXML 21

