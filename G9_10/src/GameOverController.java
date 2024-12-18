/*
* Author:https://github.com/kobocawaee
* Author:https://github.com/JeffreyLee143
*/

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class GameOverController {
    @FXML private Button button_play_again,button_title;
    @FXML private Label label_final_score,label_combo;
    @FXML AnchorPane gameover;
    
    int play;
    Parent root;
    private MediaPlayer backgroundMusicPlayer;
    
    @FXML
    public void Click_title(ActionEvent event) throws IOException {
        backgroundMusicPlayer.stop();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuController.fxml"));
        Parent root = loader.load();
        
        // 取得目前舞台
        Stage stage = (Stage) button_title.getScene().getWindow();
        
        // 建立一個新的場景，並將其設定為舞台的場景
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void Click_Play_Again(ActionEvent event) throws IOException {
        backgroundMusicPlayer.stop();
        if(play==1){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Play1Controller.fxml"));
            root = loader.load();
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Play2Controller.fxml"));
            root = loader.load();
        }
        
        // 取得目前舞台
        Stage stage = (Stage) button_play_again.getScene().getWindow();
        
        // 建立一個新的場景，並將其設定為舞台的場景
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void initialize(int score, int combo, int num){
        Image img = new Image(getClass().getResource("/res/gameover_image.png").toExternalForm()); //背景圖片
        BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        gameover.setBackground(bGround);
        
        label_final_score.setText(String.valueOf(score));
        label_combo.setText(String.valueOf(combo));
        
        play = num;
        Media backgroundMusic = new Media(getClass().getResource("/res/gameover_music.wav").toExternalForm());
        backgroundMusicPlayer = new MediaPlayer(backgroundMusic);
        backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusicPlayer.play();
    }
}
