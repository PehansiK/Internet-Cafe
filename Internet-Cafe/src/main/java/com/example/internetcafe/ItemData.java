package com.example.internetcafe;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemData {

    private static final List<DealerItem> allItems = new ArrayList<>();


    static {
        loadDataFromFile();
    }

    // Load data from the file "dealeritem.txt".
    private static void loadDataFromFile() {
        String fileName = "src/main/resources/dealerItem.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into an array using commas as separators.
                String[] data = line.split(",");

                // Ensure that the line contains all necessary data.
                if (data.length == 5) {
                    String itemName = data[0].trim();
                    String itemBrand = data[1].trim();
                    int itemPrice = Integer.parseInt(data[2].trim());
                    int itemQuantity = Integer.parseInt(data[3].trim());
                    String dealerName = data[4].trim();

                    // Create a new DealerItem object and add it to the list.
                    DealerItem dealerItem = new DealerItem(itemName, itemBrand, itemPrice, itemQuantity, dealerName);
                    allItems.add(dealerItem);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // A public method to get all DealerItem objects belongs to a specific dealer.
    public static List<DealerItem> getItemsByDealer(Dealer dealer) {
        List<DealerItem> itemsByDealer = new ArrayList<>();
        for (DealerItem dealerItem : allItems) {
            if (dealerItem.getDealerName().equals(dealer.getDealerName())) {
                itemsByDealer.add(dealerItem);
            }
        }
        return itemsByDealer;
    }
}


