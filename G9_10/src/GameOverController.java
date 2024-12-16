import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GameOverController {
    @FXML
    private Button button_play_again,button_title;
    @FXML
    private Label label_final_score,label_combo;
    @FXML
    public void Click_title(ActionEvent event) throws IOException {
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayController.fxml"));
        Parent root = loader.load();
        
        // 取得目前舞台
        Stage stage = (Stage) button_play_again.getScene().getWindow();
        
        // 建立一個新的場景，並將其設定為舞台的場景
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void initialize(int score, int combo){
        label_final_score.setText(String.valueOf(score));
        label_combo.setText(String.valueOf(combo));
    }
}
