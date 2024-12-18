import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class MenuController {
    @FXML
    private Button button_play1, button_play2, help;
    @FXML AnchorPane game;
    private MediaPlayer backgroundMusicPlayer;
    
    @FXML
    public void initialize() {
        Image img = new Image(getClass().getResource("game_background.png").toExternalForm()); //背景圖片
        BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        game.setBackground(bGround);
        
        
        Media backgroundMusic = new Media(getClass().getResource("game_background_music.mp3").toExternalForm());
        backgroundMusicPlayer = new MediaPlayer(backgroundMusic);
        backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusicPlayer.play();
    }
    @FXML
    public void Click_Play1(ActionEvent event) throws IOException {
        backgroundMusicPlayer.stop();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Play1.fxml"));
        Parent root = loader.load();
        
        // 取得目前舞台
        Stage stage = (Stage) button_play1.getScene().getWindow();
        
        // 建立一個新的場景，並將其設定為舞台的場景
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void Click_Play2(ActionEvent event) throws IOException {
        backgroundMusicPlayer.stop();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Play2.fxml"));
        Parent root = loader.load();
        
        // 取得目前舞台
        Stage stage = (Stage) button_play2.getScene().getWindow();
        
        // 建立一個新的場景，並將其設定為舞台的場景
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

