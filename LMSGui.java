// Zohaib Ahmadzai
// CEN-3024C
// 11/12/2023
package com.example.lmsgui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.Optional;


public class LMSGui extends Application {

    private Library library;
    private TextArea textArea;
    private TableView<Library.Book> tableView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        library = new Library();
        library.loadBooksFromDatabase();

        primaryStage.setTitle("Library Management System");

        textArea = new TextArea();
        textArea.setEditable(false);

        tableView = new TableView<>();
        TableColumn<Library.Book, String> barcodeColumn;
        barcodeColumn = new TableColumn<>("Barcode");
        barcodeColumn.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        TableColumn<Library.Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<Library.Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        TableColumn<Library.Book, Boolean> availableColumn = new TableColumn<>("Available");
        availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));

        tableView.getColumns().addAll(barcodeColumn, titleColumn, authorColumn, availableColumn);

        Button addButton = new Button("Add Book");
        Button removeButton = new Button("Remove Book");
        Button checkOutButton = new Button("Check Out");
        Button checkInButton = new Button("Check In");
        Button exitButton = new Button("Exit");

        addButton.setOnAction(e -> addBook());
        removeButton.setOnAction(e -> removeBook());
        checkOutButton.setOnAction(e -> checkOutBook());
        checkInButton.setOnAction(e -> checkInBook());
        exitButton.setOnAction(e -> exitApplication(primaryStage));

        VBox buttonBox = new VBox(10);
        buttonBox.getChildren().addAll(addButton, removeButton, checkOutButton, checkInButton, exitButton);

        HBox root = new HBox(10);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.getChildren().addAll(tableView, buttonBox);

        Scene scene = new Scene(root, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void exitApplication(Stage primaryStage) {
        primaryStage.close();
    }

    private void addBook() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Book");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter the book title:");
        Optional<String> titleResult = dialog.showAndWait();

        titleResult.ifPresent(title -> {
            TextInputDialog barcodeDialog = new TextInputDialog();
            barcodeDialog.setTitle("Add Book");
            barcodeDialog.setHeaderText(null);
            barcodeDialog.setContentText("Enter the barcode:");
            Optional<String> barcodeResult = barcodeDialog.showAndWait();

            barcodeResult.ifPresent(barcode -> {
                TextInputDialog authorDialog = new TextInputDialog();
                authorDialog.setTitle("Add Book");
                authorDialog.setHeaderText(null);
                authorDialog.setContentText("Enter the author:");
                Optional<String> authorResult = authorDialog.showAndWait();

                authorResult.ifPresent(author -> {
                    Library.Book book = new Library.Book(barcode, title, author);
                    library.addBook(book);
                    textArea.appendText("Book added: " + book.getTitle() + "\n");
                    refreshTableData();
                });
            });
        });
    }

    private void removeBook() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Remove Book");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter the title of the book to remove:");
        Optional<String> titleResult = dialog.showAndWait();

        titleResult.ifPresent(title -> {
            if (library.removeBookByTitle(title)) {
                textArea.appendText("Book removed: " + title + "\n");
                refreshTableData();
            } else {
                textArea.appendText("Book not found: " + title + "\n");
            }
        });
    }

    private void checkOutBook() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Check Out Book");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter the title of the book to check out:");
        Optional<String> titleResult = dialog.showAndWait();

        titleResult.ifPresent(title -> {
            if (library.checkOutBookByTitle(title)) {
                textArea.appendText("Book checked out: " + title + "\n");
                refreshTableData();
            } else {
                textArea.appendText("Book not found or already checked out: " + title + "\n");
            }
        });
    }

    private void checkInBook() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Check In Book");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter the title of the book to check in:");
        Optional<String> titleResult = dialog.showAndWait();

        titleResult.ifPresent(title -> {
            if (library.checkInBook(title)) {
                textArea.appendText("Book checked in: " + title + "\n");
                refreshTableData();
            } else {
                textArea.appendText("Book not found or already checked in: " + title + "\n");
            }
        });
    }

    private void refreshTableData() {
        tableView.getItems().clear();
        tableView.getItems().addAll(library.getBooks());
    }
}
