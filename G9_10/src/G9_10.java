/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
 
 public class G9_10 extends Application {
     @Override
     public void start(Stage stage) throws Exception {
         Parent root = FXMLLoader.load(getClass().getResource("MenuController.fxml"));
         Scene scene = new Scene(root);
         Image img = new Image(getClass().getResource("icon.jpg").toExternalForm());//縮圖及背景圖片
         stage.getIcons().add(img);
         stage.setTitle("圓射!啟動!!");
         stage.setScene(scene);
         stage.show();
    }
 
     public static void main(String[] args) {
         launch(args);
     }
 } 
