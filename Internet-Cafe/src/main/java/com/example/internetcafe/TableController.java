package com.example.internetcafe;


import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class TableController {

    @FXML
    private TableView<Item> tableView;

    @FXML
    private TableColumn<Item, String> itemCodeColumn;

    @FXML
    private TableColumn<Item, String> itemNameColumn;

    @FXML
    private TableColumn<Item, String> itemBrandColumn;

    @FXML
    private TableColumn<Item, Double> itemPriceColumn;

    @FXML
    private TableColumn<Item, Integer> quantityColumn;

    @FXML
    private TableColumn<Item, String> itemCategoryColumn;

    @FXML
    private TableColumn<Item, String> purchasedDateColumn;

    @FXML
    private TableColumn<Item, Image> itemImageColumn;
    @FXML
    private Label totalLabel;

    @FXML
    private Button homeButton;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;



    @FXML
    private void handleHomeButtonClick() {
        try {
            FXMLLoader homeloader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            Parent homeRoot = homeloader.load();
            Scene homeScene = new Scene(homeRoot);

            // Get the main window's stage
            Stage homeStage = (Stage) homeButton.getScene().getWindow();
            homeStage.setScene(homeScene);
            homeStage.setTitle("Jhon’s Internet Café"); // Set a title for the new scene
            homeStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddButtonClick() {
        try {
            FXMLLoader addloader = new FXMLLoader(getClass().getResource("AddItem.fxml"));
            Parent addRoot = addloader.load();
            Scene addScene = new Scene(addRoot);

            // Get the add stage
            Stage addStage = (Stage) addButton.getScene().getWindow();
            addStage.setScene(addScene);
            addStage.setTitle("Add Item"); // Set a title for the new scene
            addStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteButtonClick() {
        try {
            FXMLLoader deleteloader = new FXMLLoader(getClass().getResource("DeleteItem.fxml"));
            Parent deleteRoot = deleteloader.load();
            Scene deleteScene = new Scene(deleteRoot);

            // Get the delete stage
            Stage deleteStage = (Stage) deleteButton.getScene().getWindow();
            deleteStage.setScene(deleteScene);
            deleteStage.setTitle("Delete Item"); // Set a title for the new scene
            deleteStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUpdateButtonClick() {
        try {
            FXMLLoader updateloader = new FXMLLoader(getClass().getResource("Update.fxml"));
            Parent updateRoot = updateloader.load();
            Scene updateScene = new Scene(updateRoot);

            // Get the update stage
            Stage updateStage = (Stage) updateButton.getScene().getWindow();
            updateStage.setScene(updateScene);
            updateStage.setTitle("Update Item"); // Set a title for the new scene
            updateStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleSaveButtonClick() {
        ObservableList<Item> itemList = MainWindowController.getItemList();
        try {
            if (!itemList.isEmpty()) {
                File file = new File("src/main/resources/item.txt");
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                // Write the itemList to the file in a table format
                bufferedWriter.write("Item Code\tItem Name\tItem Brand\tItem Price\tItem Quantity\tItem Category\tPurchased Date");
                bufferedWriter.newLine();
                for (Item item : itemList) {
                    bufferedWriter.write(item.getItemCode() + "\t" +
                            item.getItemName() + "\t" +
                            item.getItemBrand() + "\t" +
                            item.getItemPrice() + "\t" +
                            item.getItemQuantity() + "\t" +
                            item.getItemCategory() + "\t" +
                            item.getItemPurchasedDate());
                    bufferedWriter.newLine();
                }

                bufferedWriter.close();

                // Alert after the loop has finished
                showInfoDialog("Data saved to item.txt successfully!");
            } else {
                showAlertDialog("Item List is empty");

            }
        } catch (IOException e) {
            e.printStackTrace();

            showInfoDialog("Error saving data to item.txt");
        }
    }

    private void showInfoDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void showAlertDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    private void initialize() {
        itemCodeColumn.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        itemBrandColumn.setCellValueFactory(new PropertyValueFactory<>("itemBrand"));
        itemPriceColumn.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("itemQuantity"));
        itemCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));
        purchasedDateColumn.setCellValueFactory(cellData -> {
            Item item = cellData.getValue();
            return new SimpleStringProperty(item.getItemPurchasedDate().toString());
        });
        itemImageColumn.setCellValueFactory(cellData -> {
            Item item = cellData.getValue();
            Image itemImage = item.getItemImage();
            return new SimpleObjectProperty<>(itemImage);
        });
        itemImageColumn.setCellFactory(param -> new ImageTableCell<>());

        // Get the unsorted item list
        ObservableList<Item> itemList = MainWindowController.getItemList();

        // Sort the items based on their item-id using Bubble Sort
        int n = itemList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Item item1 = itemList.get(j);
                Item item2 = itemList.get(j + 1);

                // Compare the item ids using compareTo and swap if necessary
                if (item1.getItemCode().compareTo(item2.getItemCode()) > 0) {
                    itemList.set(j, item2);
                    itemList.set(j + 1, item1);
                }
            }
        }

        // Set the sorted items to the tableView
        tableView.setItems(itemList);
        double total = itemList.stream()
                .mapToDouble(item -> item.getItemPrice() * item.getItemQuantity())
                .sum();

        // Update the totalLabel with the calculated value
        totalLabel.setText("Total: $" + String.format("%.2f", total));
    }




    public class ImageTableCell<S, T> extends TableCell<S, T> {

        private final ImageView imageView = new ImageView();

        public ImageTableCell() {
            // Required configurations for the ImageView
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            setGraphic(imageView);
        }

        @Override
        protected void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
            } else {
                imageView.setImage((Image) item);
                setGraphic(imageView);
            }
        }


    }


}