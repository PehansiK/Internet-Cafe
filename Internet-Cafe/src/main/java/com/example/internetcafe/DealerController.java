package com.example.internetcafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;


public class DealerController implements Initializable {

    @FXML
    public TableView<Dealer> dealerTableView;
    @FXML
    public TableColumn<Dealer, String> dealerName;
    @FXML
    public TableColumn<Dealer, String> dealerLocation;
    @FXML
    public TableColumn<Dealer, String> dealerContact;
    @FXML
    public Button selectButton;
    @FXML
    public Button HomeButton;


    ObservableList<Dealer> observableList = FXCollections.observableArrayList();

    @FXML
    private void handleHomeButtonClick() {
        try {
            FXMLLoader homeloader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            Parent homeRoot = homeloader.load();
            Scene homeScene = new Scene(homeRoot);

            // Get the main window's stage
            Stage homeStage = (Stage) HomeButton.getScene().getWindow();
            homeStage.setScene(homeScene);
            homeStage.setTitle("Jhon’s Internet Café");
            homeStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dealerName.setCellValueFactory(new PropertyValueFactory<>("dealerName"));
        dealerLocation.setCellValueFactory(new PropertyValueFactory<>("dealerLocation"));
        dealerContact.setCellValueFactory(new PropertyValueFactory<>("dealerContact"));

        // Load data from the file and populate the table
        loadDataFromFile();
        dealerTableView.setItems(observableList);


    }



    // Get Dealer details from text file
    private void loadDataFromFile() {
        String fileName = "src/main/resources/dealers.txt";
        List<Dealer> allDealers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String dealerName = data[0].trim();
                    String dealerLocation = data[1].trim();
                    String dealerContact = data[2].trim();
                    allDealers.add(new Dealer(dealerName, dealerLocation, dealerContact));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Get the total number of dealers to show (maximum 4)
        int numDealersToShow = Math.min(4, allDealers.size());

        // Randomly select the dealers
        Collections.shuffle(allDealers);

        // Select the first 4 dealers from the shuffled list
        List<Dealer> selectedDealers = allDealers.subList(0, numDealersToShow);

        // Bubble Sort to sort the selected dealers based on their location
        for (int i = 0; i < numDealersToShow - 1; i++) {
            for (int j = 0; j < numDealersToShow - i - 1; j++) {
                Dealer dealer1 = selectedDealers.get(j);
                Dealer dealer2 = selectedDealers.get(j + 1);

                // Compare the locations and swap if necessary
                if (dealer1.getDealerLocation().compareTo(dealer2.getDealerLocation()) > 0) {
                    selectedDealers.set(j, dealer2);
                    selectedDealers.set(j + 1, dealer1);
                }
            }
        }

        // Clear the observable list to remove any previous entries
        observableList.clear();

        // Add the sorted dealers to the observable list
        observableList.addAll(selectedDealers);
    }
    @FXML
    public void handleSelectButtonAction(ActionEvent event) {
        // Get the selected dealer from the table
        Dealer selectedDealer = dealerTableView.getSelectionModel().getSelectedItem();
        if (selectedDealer != null) {
            try {
                FXMLLoader itemLoader = new FXMLLoader(getClass().getResource("SelectedDealer.fxml"));
                Parent itemRoot = itemLoader.load();
                Scene itemScene = new Scene(itemRoot);

                // Get the controller of the new scene
                SelectedDealerController selectedDealerController = itemLoader.getController();

                // Get the items associated with the selected dealer
                List<DealerItem> dealerItemsList = ItemData.getItemsByDealer(selectedDealer);

                // Populate the TableView in the new scene with the dealer items
                selectedDealerController.populateDealerItemsTable(dealerItemsList);
                selectedDealerController.setDealerName(selectedDealer.getDealerName());


                Stage currentStage = (Stage) selectButton.getScene().getWindow();
                currentStage.setScene(itemScene);
                currentStage.setTitle("Selected Dealer Item Details");
                currentStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}

