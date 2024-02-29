package com.example.internetcafe;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SelectedDealerController {
    @FXML
    private Label dealerNameLabel; // New label to display the dealer's name
    @FXML
    private TableView<DealerItem> dealerItemsTableView;
    @FXML
    private TableColumn<DealerItem, String> itemNameColumn;
    @FXML
    private TableColumn<DealerItem, String> itemBrandColumn;
    @FXML
    private TableColumn<DealerItem, Integer> itemPriceColumn;
    @FXML
    private TableColumn<DealerItem, Integer> itemQuantityColumn;
    @FXML
    private Button homeButton;
    @FXML
    private Button backButton;

    public void initialize() {
        // Set cell value factories for the columns
        itemNameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        itemBrandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        itemPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        itemQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    public void setDealerName(String dealerName) {
        dealerNameLabel.setText(dealerName);
    }

    public void populateDealerItemsTable(List<DealerItem> dealerItemsList) {
        // Set the items of the TableView with the dealer's items
        dealerItemsTableView.setItems(FXCollections.observableArrayList(dealerItemsList));
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
    private void handleBackButtonClick() {
        try {
            FXMLLoader backloader = new FXMLLoader(getClass().getResource("SelectDealers.fxml"));
            Parent backRoot = backloader.load();
            Scene backScene = new Scene(backRoot);


            Stage backStage = (Stage) backButton.getScene().getWindow();
            backStage.setScene(backScene);
            backStage.setTitle("Select Dealer");
            backStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

