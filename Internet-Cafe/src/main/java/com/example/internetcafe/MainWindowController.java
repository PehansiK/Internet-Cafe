package com.example.internetcafe;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;


public class MainWindowController {

    @FXML
    private Button addItemButton;

    @FXML
    private Button homeButton;
    @FXML
    private Button clearButton;

    @FXML
    private TextField itemCodeField;

    @FXML
    private TextField itemNameField;

    @FXML
    private TextField itemBrandField;

    @FXML
    private TextField itemPriceField;

    @FXML
    private TextField itemQuantityField;

    @FXML
    private TextField itemCategoryField;

    @FXML
    private DatePicker purchasedDateField;

    @FXML
    private Button DeleteItemButton;

    @FXML
    private TextField deleteItemField;

    @FXML
    private Button updateItemButton;

    @FXML
    private Button ViewItemButton;
    @FXML
    private ImageView imageView;

    @FXML
    private Button uploadButton;
    @FXML
    private Button dealerButton;
    @FXML
    private Button newuploadButton;

    @FXML
    private TextField updateItemField;
    @FXML
    private TextField newItemNameField;
    @FXML
    private TextField newItemBrandField;
    @FXML
    private TextField newItemPriceField;
    @FXML
    private TextField newItemQuantityField;
    @FXML
    private TextField newItemCategoryField;
    @FXML
    private DatePicker newPurchasedDateField;
    @FXML
    private ImageView newImageView;
    private static final ObservableList<Item> itemList = FXCollections.observableArrayList();

    public static ObservableList<Item> getItemList() {
        return itemList;
    }


    @FXML
    private void handleHomeButtonClick() {
        try {
            FXMLLoader homeloader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            Parent homeRoot = homeloader.load();
            Scene homeScene = new Scene(homeRoot);


            Stage homeStage = (Stage) homeButton.getScene().getWindow();
            homeStage.setScene(homeScene);
            homeStage.setTitle("Jhon’s Internet Café");
            homeStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddItemButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddItem.fxml"));
            Parent addItemRoot = loader.load();
            Scene addItemScene = new Scene(addItemRoot);


            Stage mainStage = (Stage) addItemButton.getScene().getWindow();
            mainStage.setScene(addItemScene);
            mainStage.setTitle("Add Item");
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleUpdateButtonClick() {
        try {
            FXMLLoader updateloader = new FXMLLoader(getClass().getResource("Update.fxml"));
            Parent updateItemRoot = updateloader.load();
            Scene updateItemScene = new Scene(updateItemRoot);

            // Get the main window's stage
            Stage updateStage = (Stage) updateItemButton.getScene().getWindow();
            updateStage.setScene(updateItemScene);
            updateStage.setTitle("Update Item");
            updateStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteItemButtonClick() {
        try {
            FXMLLoader delloader = new FXMLLoader(getClass().getResource("DeleteItem.fxml"));
            Parent delItemRoot = delloader.load();
            Scene delItemScene = new Scene(delItemRoot);


            Stage delStage = (Stage) DeleteItemButton.getScene().getWindow();
            delStage.setScene(delItemScene);
            delStage.setTitle("Delete Item");
            delStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleViewItemButtonClick() {
        try {
            FXMLLoader viewloader = new FXMLLoader(getClass().getResource("Table.fxml"));
            Parent viewItemRoot = viewloader.load();
            Scene viewItemScene = new Scene(viewItemRoot);

            // Get the main window's stage
            Stage viewStage = (Stage) ViewItemButton.getScene().getWindow();
            viewStage.setScene(viewItemScene);
            viewStage.setTitle("View Item Details");
            viewStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleSaveButtonClick() {
        try {
            if (!itemList.isEmpty()) {
                File file = new File("item.txt");
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
            // Show an error message using an alert if an exception occurs during the save operation
            showInfoDialog("Error saving data to item.txt");
        }
    }
    @FXML
    private void dealerButtonClick() {
        try {
            FXMLLoader dealerloader = new FXMLLoader(getClass().getResource("SelectDealers.fxml"));
            Parent dealerItemRoot = dealerloader.load();
            Scene dealerItemScene = new Scene(dealerItemRoot);

            // Get the main window's stage
            Stage dealerStage = (Stage) dealerButton.getScene().getWindow();
            dealerStage.setScene(dealerItemScene);
            dealerStage.setTitle("Select Dealer");
            dealerStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleTerminateButtonClick() {
        System.exit(0);
    }




//Add Item Details (AID)
    @FXML
    private void handleAddTableItemButtonClick() {
        // Validate the input fields before proceeding
        if (validateFields()) {
            // Create a new Item object with the entered details
            String itemCode = itemCodeField.getText().trim();
            String itemName = itemNameField.getText().trim();
            String itemBrand = itemBrandField.getText().trim();
            double itemPrice = Double.parseDouble(itemPriceField.getText().trim());
            int itemQuantity = Integer.parseInt(itemQuantityField.getText().trim());
            String itemCategory = itemCategoryField.getText().trim();
            LocalDate purchasedDate = purchasedDateField.getValue();
            Image itemImage = imageView.getImage();

            Item newItem = new Item(itemCode, itemName, itemBrand, itemPrice, itemQuantity, itemCategory, purchasedDate, itemImage);

            // Add the new Item to the ObservableList representing the table data
            itemList.add(newItem);
            System.out.println("Item Added Successfully.");

            // Show success message
            showInfoDialog();

            // Clear the input fields for the next item
            clearInputFields();


        }
    }
    // Adding Images
    @FXML
    private void handleUploadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.jpg", "*.jpeg", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(uploadButton.getScene().getWindow());

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);
        }
    }

    private void clearInputFields() {
        itemCodeField.clear();
        itemNameField.clear();
        itemBrandField.clear();
        itemPriceField.clear();
        itemQuantityField.clear();
        itemCategoryField.clear();
        purchasedDateField.setValue(null);
        imageView.setImage(null);
    }

    private void showInfoDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Item added successfully!");
        alert.showAndWait();
    }
// Validation for AID
    private boolean validateFields() {
        String itemCode = itemCodeField.getText().trim();
        String itemName = itemNameField.getText().trim();
        String itemBrand = itemBrandField.getText().trim();
        String itemPriceText = itemPriceField.getText().trim();
        String itemQuantityText = itemQuantityField.getText().trim();
        String itemCategory = itemCategoryField.getText().trim();
        LocalDate purchasedDate = purchasedDateField.getValue();

        if (itemCode == null) {
            showAlertDialog("Item Code cannot be empty.");
            return false;
        }
        boolean itemCodeExists = false;
        for (Item item : itemList) {
            if (item.getItemCode().equals(itemCode)) {
                itemCodeExists = true;
                break;
            }
        }

        if (itemCodeExists) {
            showAlertDialog("Item code already exists.");
            return false;
        }


        if (emptyFields(itemCode,itemName,itemBrand,itemPriceText,itemQuantityText,itemCategory,purchasedDate)){
            showAlertDialog("Please fill in all the required fields.");
            return false;
        }

        try {
            double itemPrice = Double.parseDouble(itemPriceText);
            if (itemPrice <= 0) {
                showAlertDialog("Please enter a valid positive price.");
                return false;
            }
        } catch (NumberFormatException e) {
            showAlertDialog("Invalid input. Please enter a valid price.");
            return false;
        }

        try {
            int itemQuantity = Integer.parseInt(itemQuantityText);
            if (itemQuantity <= 0) {
                showAlertDialog("Please enter a valid positive quantity.");
                return false;
            }
            // Validate purchased date
            if (purchasedDate.getYear() > 2023 || purchasedDate.getMonthValue() > 12 || purchasedDate.getDayOfMonth() > purchasedDate.lengthOfMonth()) {
                showAlertDialog("Invalid purchased date. Please enter a valid date.");
                return false;
            }
        } catch (NumberFormatException e) {
            showAlertDialog("Invalid input. Please enter a valid quantity.");
            return false;
        }

        // All validations passed
        return true;
    }
    public static boolean emptyFields(String itemCode,String itemName,String itemBrand,String  itemPriceText, String itemQuantityText, String itemCategory,LocalDate purchasedDate){
        return (itemCode.isEmpty() ||
                itemName.isEmpty() ||
                itemBrand.isEmpty() ||
                itemPriceText.isEmpty() ||
                itemQuantityText.isEmpty() ||
                itemCategory.isEmpty() ||
                purchasedDate==null);
    }
    private void showAlertDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
//Clear Button to Delete any field
    @FXML
    private void clearButtonHandle() {
        try {
            FXMLLoader clearloader = new FXMLLoader(getClass().getResource("AddItem.fxml"));
            Parent clearItemRoot = clearloader.load();
            Scene clearItemScene = new Scene(clearItemRoot);


            Stage clearStage = (Stage) clearButton.getScene().getWindow();
            clearStage.setScene(clearItemScene);
            clearStage.setTitle("Add Item");
            clearStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clearInputFields();
    }

    // Deleting Item Details From the List(DID)
    @FXML
    private void handleDeleteButtonClick() {
        String deleteCode = deleteItemField.getText().trim();
        //Check for the presence of entered itemcode in the list
        boolean itemFound = false;
        Iterator<Item> iterator = itemList.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            //Deleting Item
            if (item.getItemCode().equals(deleteCode)) {
                iterator.remove();
                itemFound = true;
                System.out.println("Item deleted successfully.\n");
                break;
            }
        }

        if (!itemFound) {
            System.out.println("Item code not found.\n");
        }


        showDeleteConfirmation(itemFound);


        // Clear the input field for the next deletion.
        deleteItemField.clear();
    }

    private void showDeleteConfirmation(boolean itemFound) {
        String message = itemFound ? "Item deleted successfully!" : "Item code not found.";
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Delete Item");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Update Item Detail (UID)
    @FXML
    private void handleUpdateItemDetailButtonClick() {
        if(updatevalidation()) {
            String updateCode = updateItemField.getText().trim();
            String newitemName = newItemNameField.getText().trim();
            String newitemBrand = newItemBrandField.getText().trim();
            String newitemPriceText = newItemPriceField.getText().trim();
            String newitemQuantityText = newItemQuantityField.getText().trim();
            String newitemCategory = newItemCategoryField.getText().trim();
            LocalDate newpurchasedDate = newPurchasedDateField.getValue();
            Image newitemImage = newImageView.getImage();

            // Find the item in the list based on the updateCode
            for (Item item : itemList) {
                if (item.getItemCode().equals(updateCode)) {
                    // Update the fields only if they are not empty
                    if (!newitemName.isEmpty()) {
                        item.setItemName(newitemName);
                    }
                    if (!newitemBrand.isEmpty()) {
                        item.setItemBrand(newitemBrand);
                    }
                    if (!newitemPriceText.isEmpty()) {
                        item.setItemPrice(Double.parseDouble(newitemPriceText));
                    }
                    if (!newitemQuantityText.isEmpty()) {
                        item.setItemQuantity(Integer.parseInt(newitemQuantityText));
                    }
                    if (!newitemCategory.isEmpty()) {
                        item.setItemCategory(newitemCategory);
                    }
                    if (newpurchasedDate != null) {
                        item.setPurchasedDate(newpurchasedDate);
                    }
                    if (newitemImage != null) {
                        item.setItemImage(newitemImage);
                    }

                    System.out.println("Item updated successfully.\n");

                    showUpdateConfirmation(true);

                    break;
                }else {
                    showUpdateConfirmation(false);
                    System.out.println("Item code not found.\n");
                }
            }

        }
        updateClearField();
    }

    // Updating Image Field
    @FXML
    private void handlenewUploadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.jpg", "*.jpeg", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(newuploadButton.getScene().getWindow());

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            newImageView.setImage(image);
        }
    }
    //Validation for UID
    private boolean updatevalidation(){
        String updateitemCode = updateItemField.getText().trim();
        String newitemName = newItemNameField.getText().trim();
        String newitemBrand = newItemBrandField.getText().trim();
        String newitemPriceText = newItemPriceField.getText().trim();
        String newitemQuantityText = newItemQuantityField.getText().trim();
        String newitemCategory = newItemCategoryField.getText().trim();
        LocalDate newpurchasedDate = newPurchasedDateField.getValue();
        Image newitemImage = newImageView.getImage();

        if (updateitemCode == null) {
            showAlertDialog("Item Code cannot be empty.");
            return false;
        }
        if (updateEmptyFields(newitemName,newitemBrand,newitemPriceText,newitemQuantityText,newitemCategory,newpurchasedDate,newitemImage)) {
            showAlertDialog("All the fields are empty.");
            return false;
        }
        if(!newitemPriceText.isEmpty()) {
            try {
                double newitemPrice = Double.parseDouble(newitemPriceText);
                if (newitemPrice <= 0) {
                    showAlertDialog("Please enter a valid positive price.");
                    return false;
                }
            } catch (NumberFormatException e) {
                showAlertDialog("Invalid input. Please enter a valid price.");
                return false;
            }
        }
        if(!newitemQuantityText.isEmpty()) {
            try {
                int newitemQuantity = Integer.parseInt(newitemQuantityText);
                if (newitemQuantity <= 0) {
                    showAlertDialog("Please enter a valid positive quantity.");
                    return false;
                }
            } catch (NumberFormatException e) {
                showAlertDialog("Invalid input. Please enter a valid quantity.");
                return false;
            }
            if (newpurchasedDate.getYear() > 2023 || newpurchasedDate.getMonthValue() > 12 || newpurchasedDate.getDayOfMonth() > newpurchasedDate.lengthOfMonth()) {
                showAlertDialog("Invalid purchased date. Please enter a valid date.");
                return false;
            }

        }

        // All validations passed
        return true;
    }
    public static boolean updateEmptyFields(String newitemName, String newitemBrand, String newitemPriceText, String newitemQuantityText, String newitemCategory, LocalDate newpurchasedDate, Image newitemImage){
        return (newitemName.isEmpty() && newitemBrand.isEmpty() && newitemPriceText.isEmpty() && newitemQuantityText.isEmpty() && newitemCategory.isEmpty() && newpurchasedDate == null && newitemImage == null);
    }
    private void showUpdateConfirmation(boolean itemFound) {
        String message = itemFound ? "Item updated successfully!" : "Item code not found.";
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update Item");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void updateClearField() {
        updateItemField.clear();
        newItemNameField.clear();
        newItemBrandField.clear();
        newItemPriceField.clear();
        newItemQuantityField.clear();
        newItemCategoryField.clear();
        newPurchasedDateField.setValue(null);
        newImageView.setImage(null);
    }

    private void showInfoDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}


