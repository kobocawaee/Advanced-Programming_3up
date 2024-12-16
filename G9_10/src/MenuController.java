import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {
    @FXML
    private Button button_play;

    @FXML
    public void Click_Play(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayController.fxml"));
        Parent root = loader.load();
        
        // 取得目前舞台
        Stage stage = (Stage) button_play.getScene().getWindow();
        
        // 建立一個新的場景，並將其設定為舞台的場景
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
}

