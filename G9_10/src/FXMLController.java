/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

 import javafx.scene.control.*;
 import java.util.Random;
 import javafx.fxml.FXML;
 import javafx.scene.image.Image;
 import javafx.scene.image.ImageView;
 import javafx.animation.Timeline;
 import javafx.animation.KeyFrame;
 import javafx.util.Duration;
 import javafx.event.ActionEvent;
 import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
 import javafx.scene.layout.VBox;
 
 public class FXMLController{
     @FXML ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12;
     @FXML Button A, W, D, again;
     @FXML Label score, time, combo, endscore;
     @FXML VBox endgame;
     @FXML GridPane game;
     int point=0;
     int comboHit=0;
     Random ran = new Random();
     private Timeline timeline;
     private int timeLeft = 30;
     
     public void initialize() {
            Image img =  new Image("background.jpg"); //背景圖片
            BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            Background bGround = new Background(bImg);
            game.setBackground(bGround);
            
         ImageView[] images = {image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12};
         for(int i=0; i<12; i++){
             Image image = new Image(getClass().getResource("/image2.png").toExternalForm());
             images[i].setImage(image);
         }
         
         for(int i=0; i<12; i+=3){
             int r = ran.nextInt(3)+i;
             if(r == 0 && r < images.length){
                 images[r].setOpacity(0.35);
             }
             else if(r >= 1 && r < images.length){
                 images[r].setOpacity(1);
             }
         }
         setupKeyboardControl();
         setupButtons();
         setupTimer();
     }
     
     //鍵盤控制
     private void setupKeyboardControl() {
         A.sceneProperty().addListener((_, _, newScene) -> {
             if (newScene != null) {
                 newScene.setOnKeyPressed(this::handleKeyPress);
             }
         });
     }
 
     private void handleKeyPress(KeyEvent e) {
         switch (e.getCode()) {
             case A:
                 A.fire();
                 break;
             case W:
                 W.fire();
                 break;
             case D:
                 D.fire();
                 break;
             case R:
                 again.fire();
             default:
                 break;
         }
     }
     
     //按鈕控制
     private void setupButtons() {
         A.setOnAction(this::handleButtonClick);
         W.setOnAction(this::handleButtonClick);
         D.setOnAction(this::handleButtonClick);
     }
 
     private void handleButtonClick(ActionEvent event) {
         Button clickedButton = (Button)event.getSource();
         int column;
         if (clickedButton == A) column = 0;
         else if (clickedButton == W) column = 1;
         else column = 2;
         HitThatBitch(column);
     }
     
     //打殭屍設定
     private void HitThatBitch(int column) {
         ImageView[] images = {image10, image11, image12};
         ImageView zombie = images[column];
         if(zombie.getOpacity() == 1.0){
             comboHit++;
             point+=10+(comboHit/5)*5;
             score.setText("Score : "+point);
             zombie.setOpacity(0);
             placeRandomZombies();
         }
         else{
             comboHit=0;
         }
         combo.setText("Combo : "+comboHit);
     }
 
     private void placeRandomZombies() {
         ImageView[] images = {image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12};
         
         for (int i = 8; i >=0; i--) {
             if(images[i].getOpacity() == 1.0 || images[i].getOpacity() == 0.35){
                 images[i+3].setOpacity(1.0);
                 images[i].setOpacity(0);
             }
         }
         int num = ran.nextInt(3);
         if(num==0){
             images[num].setOpacity(0.35);
         }else{
             images[num].setOpacity(1.0);
         }
     }
     
     //
     private void setupTimer(){
         if(timeline != null){
             timeline.stop();
         }
         time.setText("Time :"+timeLeft);
         timeline = new Timeline(new KeyFrame(Duration.seconds(1), _ ->{
             timeLeft--;
             time.setText("Time :"+timeLeft);
             if(timeLeft <= 0){
                 timeline.stop();
                 endGame();
             }
         }));
         timeline.setCycleCount(timeLeft);
         timeline.play();
     }
     
     private void endGame(){
         A.setDisable(true);
         W.setDisable(true);
         D.setDisable(true);
         image8.setOpacity(0);
         endgame.setOpacity(1.0);
         endscore.setText("   final score : " + point);
     }
     
     @FXML
     public void play_again(){
         ImageView[] images = {image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12};
         for(int i=0; i<12; i++){
             images[i].setOpacity(0);
         }
         for(int i=0; i<12; i+=3){
             int r = ran.nextInt(3)+i;
             if(r == 0 && r < images.length){
                 images[r].setOpacity(0.35);
             }
             else if(r >= 1 && r < images.length){
                 images[r].setOpacity(1);
             }
         }
         endgame.setOpacity(0);
         point=0;
         comboHit=0;
         score.setText("score : "+point);
         timeLeft=30;
         setupTimer();
         A.setDisable(false);
         W.setDisable(false);
         D.setDisable(false);
     }
 }