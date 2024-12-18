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
import javafx.stage.Stage;

public class HelpController {
    @FXML private Button button_Title;

    @FXML
    public void Click_Title(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuController.fxml"));
        Parent root = loader.load();
        
        // 取得目前舞台
        Stage stage = (Stage) button_Title.getScene().getWindow();
        
        // 建立一個新的場景，並將其設定為舞台的場景
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
