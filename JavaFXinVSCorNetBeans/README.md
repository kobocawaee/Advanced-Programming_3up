# HowToSteupJavaFXinVSC
Fast guided to run JavaFX in vsc.
# Step 1 Install vsc
Install vsc.
[https://code.visualstudio.com/](https://code.visualstudio.com/)
# Step 2 Extension Pack for Java
Install extensions "Extension Pack for Java" (Ctrl+Shift+X).
![image](https://github.com/user-attachments/assets/d9180fa5-80d8-4d4a-8065-1a7afc227941)
# Step 3 Install JDK
Install JDK
![image](https://github.com/user-attachments/assets/69984e1f-cb8e-45c4-83b3-169f1b3ea07f)
![image](https://github.com/user-attachments/assets/051a64e4-02d0-4c21-a3ea-ae4f68a425f6)
You'll download jdk on your browser.
After installing the jdk, you need to restart your vsc.
![image](https://github.com/user-attachments/assets/34dac7b6-da9f-4ef1-9b2b-115e3bef0c9c)
# Step 4 Install JavaFX
Install JavaFX.
[https://gluonhq.com/products/javafx/](https://gluonhq.com/products/javafx/)
If you're using windows os, download the SDK type.
Then unzip it, you'll get a folder like this "javafx-sdk-23.0.1".
# Step 5 Create java project
1. Open new window in vsc (Ctrl+Shift+N)
2. Open explorer in you left bar (Ctrl+Shift+E)
3. Press the button "Create Java Project"
4. "No build tools"
5. Choose path to save your java project.
6. Name your project (JavaFX_1 or something else)
7. Open App.java (.src/App.java) then open Add configurations
![image](https://github.com/user-attachments/assets/2a0cf178-6d2f-4d03-bac6-b8fc3f1461df)
![image](https://github.com/user-attachments/assets/a8b6efee-ea23-4134-8041-b6830c80b0ec)
8. Add "vmArgs": --module-path "\path\to\javafx-sdk-23.0.1\lib" --add-modules javafx.controls,javafx.fxml after projectName like this:
```
{
    // Use IntelliSense to learn about possible attributes.
    // Hover to view descriptions of existing attributes.
    // For more information, visit: https://go.microsoft.com/fwlink/?linkid=830387
    "version": "0.2.0",
    "configurations": [

        {
            "type": "java",
            "name": "Current File",
            "request": "launch",
            "mainClass": "${file}"
        },
        {
            "type": "java",
            "name": "App",
            "request": "launch",
            "mainClass": "App",
            "projectName": "JavaFX_1_d53e76a6",
            "vmArgs": "--module-path \"C:/Users/Administrator/Desktop/javafx-sdk-23.0.1/lib\" --add-modules javafx.controls,javafx.fxml"
        }
    ]
}
```
Where \"C:/Users/Administrator/Desktop/javafx-sdk-23.0.1/lib\" is your javafx/lib install path
![image](https://github.com/user-attachments/assets/52e11a13-aea5-4dc0-9ea2-03defd3adc0b)
9. Add all the file in javafx/lib to Referenced Libraries
![image](https://github.com/user-attachments/assets/95a61c6a-1063-4093-a1a4-8bfe32b30445)
# Step 6 Test
Open App.java then replce it:
```Java
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
  
  /*
  Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
  Scene scene = new Scene(root);
  */
  
  Scene scene = new Scene(root, 300, 250);
  
  primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
 public static void main(String[] args) {
        launch(args);
    }
} 
```
Save it then run:
![image](https://github.com/user-attachments/assets/4c9d4cbf-c9b5-40f0-ba21-e44dad3df1f4)
If you can see this, CONGRATULATION!
# Option (Scene Builder)
1. Install Scene Builder (Windows Installer for windows operation system)
[https://gluonhq.com/products/scene-builder/](https://gluonhq.com/products/scene-builder/)
2. Create "MainSceneController.java" or other name in ./src
![image](https://github.com/user-attachments/assets/1102a9a1-1454-41e9-bb97-f22ec4996f93)
3. Open Scene Builder and create a file such like empty:
![image](https://github.com/user-attachments/assets/405cd937-2b51-45be-8f51-e79d4588dc52)
4. Drag AnchorPane then drag Label on it and type anything.
![image](https://github.com/user-attachments/assets/71c1bde7-7628-479c-a412-ef9d38132fd9)
5. Save it under src.
![image](https://github.com/user-attachments/assets/2557cad6-46e6-485c-b44c-77e2119ab9d7)
6. Open view->Show Sample Controller Skeleton then copy it to MainSceneController.java
![image](https://github.com/user-attachments/assets/0e8bbca5-46ad-4bfe-a2dc-fb50c47f1e0f)
![image](https://github.com/user-attachments/assets/bf769ab2-c026-4f1b-8dc6-c1e237d11a33)


