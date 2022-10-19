module com.example.webview {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens com.example.webview to javafx.fxml;
    exports com.example.webview;
}