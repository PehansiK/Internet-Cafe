package com.example.internetcafe;

public class DealerItem {

    // Item name property.
    private String itemName;

    // Brand property.
    private String brand;

    // Price property.
    private int price;

    // Quantity property.
    private int quantity;

    // Dealer's name property.
    private String dealerName;

    // Constructor to initialize the DealerItem object.
    public DealerItem(String itemName, String brand, int price, int quantity, String dealerName) {
        this.itemName = itemName;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.dealerName = dealerName;
    }

    // Getter for the item's name.
    public String getItemName() {
        return itemName;
    }

    // Setter for the item's name.
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    // Getter for the item's brand.
    public String getBrand() {
        return brand;
    }

    // Setter for the item's brand.
    public void setBrand(String brand) {
        this.brand = brand;
    }

    // Getter for the item's price.
    public int getPrice() {
        return price;
    }

    // Setter for the item's price.
    public void setPrice(int price) {
        this.price = price;
    }

    // Getter for the item's quantity.
    public int getQuantity() {
        return quantity;
    }

    // Setter for the item's quantity.
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getter for the name of the dealer who supplies the item.
    public String getDealerName() {
        return dealerName;
    }

    // Setter for the name of the dealer who supplies the item.
    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }
}
