package com.example.webview;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private WebView webView;
    @FXML
    private TextField textField;
    private WebEngine engine;

    private String homePage;

    private double webZoom;

    private WebHistory history;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        engine = webView.getEngine();
        homePage = "www.google.com";
        textField.setText(homePage); // sets homepage
        webZoom = 1; // initial page size (100%)
        loadPage();
    }
    public void loadPage() { // will allow us to load page
        // engine.load("https://github.com/Avila-Angel"); // loads my GitHub page
        engine.load("https://" + textField.getText()); // allows us to type a URL
    }
    public void refreshPage() { // will allow us to refresh page
        engine.reload();
    }
    public void zoomIn() { // allows us to zoom in
        webZoom += 0.25; // allows us to keep zooming out
        webView.setZoom(webZoom);
    }
    public void zoomOut() { // allows us to zoom out
        webZoom -= 0.25; // allows us to keep zooming out
        webView.setZoom(webZoom); // set a number below 1
    }
    public void displayHistory() {

        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();

        // iterate over observable list
        for(WebHistory.Entry entry : entries) {
            // System.out.println(entry); // prints entries
            System.out.println(entry.getUrl() + " " + entry.getLastVisitedDate());
        }
    }
    public void back() {
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(-1); // allows us to move back a page
        textField.setText(entries.get(history.getCurrentIndex()).getUrl()); // updates URL when moving back
    }
    public void forward() {
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(1); // allows us to move back up a page
        textField.setText(entries.get(history.getCurrentIndex()).getUrl()); // updates URL when moving forward
    }
    public void executeJS() {
        // line below allows us to execute JS code, which will allows us to redirect to YouTube when pressing executeJS button
        engine.executeScript("window.location = \"https://www.youtube.com\";");
    }
}