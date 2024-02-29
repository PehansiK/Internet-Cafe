module com.example.internetcafe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.fontawesome5;
    requires org.kordamp.ikonli.javafx;



    opens com.example.internetcafe to javafx.fxml;
    exports com.example.internetcafe;

}