package com.example.internetcafe;

import javafx.scene.image.Image;

import java.time.LocalDate;

public class Item {

    // Item code property.
    private String itemCode;

    // Item name property.
    private String itemName;

    // Item brand property.
    private String itemBrand;

    // Item price property.
    private double itemPrice;

    // Item quantity property.
    private int itemQuantity;

    // Item category property.
    private String itemCategory;

    // Date the item was purchased property.
    private LocalDate purchasedDate;

    // Image of the item property.
    private Image itemImage;

    // Constructor to initialize the Item object with provided data.
    public Item(String itemCode, String itemName, String itemBrand, double itemPrice, int itemQuantity,
                String itemCategory, LocalDate purchasedDate, Image itemImage) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemBrand = itemBrand;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.itemCategory = itemCategory;
        this.purchasedDate = purchasedDate;
        this.itemImage = itemImage;
    }

    // Getters for the properties of the Item class.

    public String getItemCode() {
        return itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemBrand() {
        return itemBrand;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public LocalDate getItemPurchasedDate() {
        return purchasedDate;
    }

    public Image getItemImage() {
        return itemImage;
    }

    // Setters for some of the properties of the Item class.

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemBrand(String itemBrand) {
        this.itemBrand = itemBrand;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public void setPurchasedDate(LocalDate purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public void setItemImage(Image itemImage) {
        this.itemImage = itemImage;
    }
}



