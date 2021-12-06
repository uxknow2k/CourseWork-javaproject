module com.company.cursach {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.company.cursach to javafx.fxml;
    exports com.company.cursach;
}