module com.example.algo_pro3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.algo_pro3 to javafx.fxml;
    exports com.example.algo_pro3;
}