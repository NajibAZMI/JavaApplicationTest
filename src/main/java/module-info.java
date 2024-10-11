module com.example.applicationtuto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.applicationtuto to javafx.fxml;
    exports com.example.applicationtuto;
}