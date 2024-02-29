module com.example.internetcafe {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.internetcafe to javafx.fxml;
    exports com.example.internetcafe;
}