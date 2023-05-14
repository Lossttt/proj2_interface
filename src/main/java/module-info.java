module com.example.hhs_proj2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hhs_proj2 to javafx.fxml;
    exports com.example.hhs_proj2;
}