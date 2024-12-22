/*
* Author:https://github.com/kobocawaee
* Author:https://github.com/JeffreyLee143
*/

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.media.AudioClip;
import java.io.IOException;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.stage.Stage;

public class Play1Controller{
    @FXML ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12;
    @FXML Button A, W, D;
    @FXML Label ready, score, time, combo;
    @FXML VBox ready_game;
    @FXML GridPane game;
    int point=0;
    int comboHit=0;
    int maxcombo=0;
    Random ran = new Random();
    private Timeline timeline;
    private int timeLeft = 30;
    private Timeline countdownTimeline;
    private int countdownTime = 4;
    AudioClip hit_sound, countdown_sound, start_sound, gamestart_sound;

    @FXML
    public void initialize() {
        Image img = new Image(getClass().getResource("/res/background.jpg").toExternalForm()); //背景圖片
        BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        game.setBackground(bGround);
        
        hit_sound = new AudioClip(getClass().getResource("/res/hit.wav").toExternalForm());
        countdown_sound = new AudioClip(getClass().getResource("/res/countdown_music.wav").toExternalForm());
        start_sound = new AudioClip(getClass().getResource("/res/start_music.wav").toExternalForm());
        gamestart_sound = new AudioClip(getClass().getResource("/res/gamestart_music.mp3").toExternalForm());
        
        ImageView[] images = {image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12};
        for(int i=0; i<12; i++){
            Image image = new Image(getClass().getResource("/res/image.png").toExternalForm());
            images[i].setImage(image);
        }
        
        setupCountdown();
        setupKeyboardControl();
        
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
        ImageView[] images = {image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12};
        for(int i=0; i<12; i+=3){
            int r = ran.nextInt(3)+i;
            if(r == 0 && r < images.length){
                images[r].setOpacity(0.35);
            }
            else if(r >= 1 && r < images.length){
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
            case A->A.fire();
            case W->W.fire();
            case D->D.fire();
        }
    }
    
    @FXML
    public void handleButtonClick(ActionEvent event) {
        Button clickedButton = (Button)event.getSource();
        int column;
        if (clickedButton == A) column = 0;
        else if (clickedButton == W) column = 1;
        else column = 2;
        Shot_Circle(column);
    }
    
    //射擊圓形設定
    private void Shot_Circle(int column) {
        ImageView[] images = {image10, image11, image12};
        ImageView circle = images[column];
        if(circle.getOpacity() == 1.0){
            hit_sound.play();
            comboHit++;
            point+=10+(comboHit/5)*5; // e.g. 18combohit, point=10+(18/5)*5=10+3*5=25
            score.setText("Score : "+point);
            circle.setOpacity(0);
            placeRandomCircles();
        }
        else{
            if(comboHit > maxcombo){
                maxcombo = comboHit;
            }
            comboHit=0;
        }
        combo.setText("Combo : "+comboHit);
    }
    
    private void placeRandomCircles() {
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
    public void GameOver() throws IOException{
        if(timeLeft==0){
            gamestart_sound.stop();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameOverController.fxml"));
            Parent root = fxmlLoader.load();
            GameOverController controller = fxmlLoader.getController();
            controller.initialize(point,maxcombo,1);
            Stage stage = (Stage) game.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}

