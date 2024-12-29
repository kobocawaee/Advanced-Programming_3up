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

    // 定義 FXML 對應的 TextField 和 Label，分別用於輸入和顯示結果
    @FXML TextField num1; // 顯示第一個隨機數
    @FXML TextField num2; // 顯示第二個隨機數
    @FXML TextField total; // 用戶輸入兩數和的欄位
    @FXML Label result; // 顯示判斷結果的 Label

    Random random = new Random(); // 用於生成隨機數的工具
    int ans; // 儲存正確答案

    /**
     * 生成新題目的方法
     * 產生兩個介於 10 到 99 的隨機整數，並計算其總和。
     * 將生成的隨機數顯示在 num1 和 num2 中，清空 total 和 result。
     */
    @FXML
    private void quesion() {
        int a = random.nextInt(90) + 10; // 生成第一個隨機數（10-99）
        int b = random.nextInt(90) + 10; // 生成第二個隨機數（10-99）
        ans = a + b; // 計算正確答案

        num1.setText(String.valueOf(a)); // 顯示第一個隨機數
        num2.setText(String.valueOf(b)); // 顯示第二個隨機數
        total.clear(); // 清空用戶輸入欄位
        result.setText(""); // 清空結果顯示
    }

    /**
     * 檢查用戶答案的方法
     * 從 total TextField 中讀取用戶輸入，並與正確答案比較。
     * 顯示結果 "對" 或 "錯"，若輸入無效則提示用戶。
     */
    @FXML
    private void checkans() {
        try {
            int userans = Integer.parseInt(total.getText()); // 讀取用戶輸入的數字
            if (userans == ans) {
                result.setText("對"); // 答案正確
            } else {
                result.setText("錯"); // 答案錯誤
            }
        } catch (Exception e) {
            result.setText("請輸入有效的數字"); // 捕捉例外，提示用戶輸入有效數字
        }
    }

    /**
     * 初始化方法，當 FXML 加載完成後會自動調用。
     * 用於在程式啟動時清空 num1 和 num2 的內容。
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        num1.clear(); // 清空 num1
        num2.clear(); // 清空 num2
    }
}

