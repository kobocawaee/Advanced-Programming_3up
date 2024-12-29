/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Hw4_411630915 extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        // 載入 FXML 檔案
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML.fxml"));
        AnchorPane root = loader.load();

        // 設定場景並顯示
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("加法出題");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);  // 啟動應用程式
    }
}
