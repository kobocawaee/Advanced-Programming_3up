/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
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

public class PlayController{
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

    @FXML
    public void initialize() {
        Image img = new Image(getClass().getResource("background.jpg").toExternalForm()); //背景圖片
        BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        game.setBackground(bGround);
        
        ImageView[] images = {image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12};
        for(int i=0; i<12; i++){
            Image image = new Image(getClass().getResource("image.png").toExternalForm());
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
                break;
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
        Shot_Circle(column);
    }
    
    //射擊圓形設定
    private void Shot_Circle(int column) {
        AudioClip pop_sound = new AudioClip(getClass().getResource("pop.wav").toExternalForm());
        ImageView[] images = {image10, image11, image12};
        ImageView circle = images[column];
        if(circle.getOpacity() == 1.0){
            pop_sound.play();
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
    @FXML
    public void GameOver() throws IOException{
        if(timeLeft==0){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameOverController.fxml"));
            Parent root = fxmlLoader.load();
            GameOverController controller = fxmlLoader.getController();
            controller.initialize(point,comboHit);
            Stage stage = (Stage) game.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
