/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.net.URL;
import java.util.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class FXMLController implements Initializable {
    // FXMLController 類別實作 Initializable 介面，讓它能在 FXML 加載後初始化
    
    @FXML private TextField a;   // FXML 中定義的 TextField，用於輸入華氏溫度值
    @FXML private Button change; // FXML 中定義的 Button，用於觸發溫度轉換的事件
    @FXML private Label b;       // FXML 中定義的 Label，用於顯示攝氏溫度轉換結果

    // 定義 changenum() 方法，當使用者點擊 'change' 按鈕時執行
    @FXML
    private void changenum() {
        // 取得 TextField 'a' 的文字內容並轉換為 double 型別
        double num1 = Double.parseDouble(a.getText());
        
        // 計算攝氏溫度：使用公式 (華氏 - 32) * 5/9
        double num2 = 5.0 / 9.0 * (num1 - 32);
        
        // 將結果格式化為一位小數，然後設置為 Label 'b' 的文字內容
        b.setText(String.format("%.1f", num2));
    }
    
    // initialize 方法，當 FXML 文件加載完成時自動調用
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 可以在這裡進行任何初始化設置
    }
}

