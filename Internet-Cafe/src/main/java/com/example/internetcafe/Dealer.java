package com.example.internetcafe;

import javafx.beans.property.SimpleStringProperty;

public class Dealer {
    // Dealer's name property.
    private SimpleStringProperty dealerName;

    // Dealer's location property.
    private SimpleStringProperty dealerLocation;

    // Dealer's contact property.
    private SimpleStringProperty dealerContact;

    // Constructor to initialize the Dealer object with provided data.
    public Dealer(String dealerName, String dealerLocation, String dealerContact) {
        // Wrap the provided strings with SimpleStringProperty.
        this.dealerName = new SimpleStringProperty(dealerName);
        this.dealerLocation = new SimpleStringProperty(dealerLocation);
        this.dealerContact = new SimpleStringProperty(dealerContact);
    }

    // Getter for the dealer's name.
    public String getDealerName() {
        return dealerName.get();
    }

    // Setter for the dealer's name.
    public void setDealerName(String dealerName) {
        this.dealerName = new SimpleStringProperty(dealerName);
    }

    // Getter for the dealer's location.
    public String getDealerLocation() {
        return dealerLocation.get();
    }

    // Setter for the dealer's location.
    public void setDealerLocation(String dealerLocation) {
        this.dealerLocation = new SimpleStringProperty(dealerLocation);
    }

    // Getter for the dealer's contact information.
    public String getDealerContact() {
        return dealerContact.get();
    }

    // Setter for the dealer's contact information.
    public void setDealerContact(String dealerContact) {
        this.dealerContact = new SimpleStringProperty(dealerContact);
    }


}

