/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Play2Controller{
    @FXML ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12, image13, image14, image15, image16;
    @FXML Button A, S, K, L, again;
    @FXML Label ready, score, time, combo;
    @FXML VBox ready_game, endgame;
    @FXML GridPane game;
    int point=0;
    int comboHit=0;
    Random ran = new Random();
    private Timeline timeline;
    private int timeLeft = 30;
    private Timeline countdownTimeline;
    private int countdownTime = 4;
    AudioClip hit_sound, countdown_sound, start_sound, gamestart_sound;


    @FXML
    public void initialize() {
        Image img = new Image(getClass().getResource("background2.jpg").toExternalForm()); //背景圖片
        BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        game.setBackground(bGround);
        
        hit_sound = new AudioClip(getClass().getResource("hit.wav").toExternalForm());
        countdown_sound = new AudioClip(getClass().getResource("countdown_music.wav").toExternalForm());
        start_sound = new AudioClip(getClass().getResource("start_music.wav").toExternalForm());
        gamestart_sound = new AudioClip(getClass().getResource("gamestart_music.mp3").toExternalForm());
        
        ImageView[] images = {image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12, image13, image14, image15, image16};
        for(int i=0; i<16; i++){
            Image image = new Image(getClass().getResource("image.png").toExternalForm());
            images[i].setImage(image);
        }
        
        setupCountdown();
        setupKeyboardControl();
        setupButtons();
    }
    
    private void setupCountdown() {
        countdownTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            
            if(countdownTime > 1){
                countdown_sound.play();
                ready.setText(String.valueOf(countdownTime-1));
            }
            else if (countdownTime == 1) {
                start_sound.play();
                ready.setText("開始");
                startGame();
                gamestart_sound.play();
            }
            else{
                ready_game.setOpacity(0);
                countdownTimeline.stop();
            }
            countdownTime--;
        }));

        countdownTimeline.setCycleCount(5); 
        countdownTimeline.play();
    }
    
    private void startGame() {
        ImageView[] images = {image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12, image13, image14, image15, image16};
        for(int i=0; i<16; i+=4){
            int r = ran.nextInt(4)+i;
            if(r == 0){
                images[r].setOpacity(0.35);
            }
            else if(r >= 1){
                images[r].setOpacity(1);
            }
        }
        setupTimer();
    }
    
    //鍵盤控制
    private void setupKeyboardControl() {
        A.sceneProperty().addListener((observable, oldScene, newScene) -> {
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
            case S:
                S.fire();
                break;
            case K:
                K.fire();
                break;
            case L:
                L.fire();
                break;
            case R:
                again.fire();
                break;
            default:
                break;
        }
    }
    
    //按鈕控制
    private void setupButtons() {
        A.setOnAction(this::handleButtonClick);
        S.setOnAction(this::handleButtonClick);
        K.setOnAction(this::handleButtonClick);
        L.setOnAction(this::handleButtonClick);
    }
    
    private void handleButtonClick(ActionEvent event) {
        Button clickedButton = (Button)event.getSource();
        int column;
        if (clickedButton == A) column = 0;
        else if (clickedButton == S) column = 1;
        else if (clickedButton == K) column = 2;
        else column = 3;
        Shot_Circle(column);
    }
    
    //射擊圓形設定
    private void Shot_Circle(int column) {
        ImageView[] images = {image13, image14, image15, image16};
        ImageView circle = images[column];
        if(circle.getOpacity() == 1.0){
            hit_sound.play();
            comboHit++;
            point+=10+(comboHit/5)*5;
            score.setText("Score : "+point);
            circle.setOpacity(0);
            placeRandomCircles();
        }
        else{
            comboHit=0;
        }
        combo.setText("Combo : "+comboHit);
    }
    
    private void placeRandomCircles() {
        ImageView[] images = {image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12, image13, image14, image15, image16};
        
        for (int i = 11; i >=0; i--) {
            if(images[i].getOpacity() == 1.0 || images[i].getOpacity() == 0.35){
                images[i+4].setOpacity(1.0);
                images[i].setOpacity(0);
            }
        }
        int num = ran.nextInt(4);
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
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event ->{
            timeLeft--;
            time.setText("Time :"+timeLeft);
            if(timeLeft <= 0){
                timeline.stop();
                try {
                    GameOver();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));
        timeline.setCycleCount(timeLeft);
        timeline.play();
    }
    
    @FXML
    public void play_again(){
        ImageView[] images = {image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12, image13, image14, image15, image16};
        for(int i=0; i<16; i++){
            images[i].setOpacity(0);
        }
        for(int i=0; i<16; i+=4){
            int r = ran.nextInt(4)+i;
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
        S.setDisable(false);
        K.setDisable(false);
        L.setDisable(false);
    }
    @FXML
    public void GameOver() throws IOException{
        if(timeLeft==0){
            gamestart_sound.stop();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameOver.fxml"));
            Parent root = fxmlLoader.load();
            GameOverController controller = fxmlLoader.getController();
            controller.initialize(point,comboHit,2);
            Stage stage = (Stage) game.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
