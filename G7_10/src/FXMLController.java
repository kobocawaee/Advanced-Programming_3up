/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class FXMLController{
    //金額
    @FXML
    private TextField amountTextField;
    //小費百分比標籤
    @FXML
    private Label tipPercentageLabel;
    //小費百分比滑條
    @FXML
    private Slider tipPercentageSlider;
    //小費文字框
    @FXML
    private TextField tipTextField;
    //總價文字框
    @FXML
    private TextField totalTextField;
    //計算小費
    public double calculate_tip() {
        // 小費=金額*小費百分比
        try {
            double tip = Double.parseDouble(amountTextField.getText()) * tipPercentageSlider.getValue() / 100.0;
            return tip;
        }catch (NumberFormatException e) {
        //例外處理 回傳 0
            return 0;
        }
    }

    //忽略未使用變數(observable,oldValue)
    @SuppressWarnings("unused")
    public void initialize() { 
        //初始化
        amountTextField.setText("0");
        tipTextField.setText("0");
        totalTextField.setText(String.format("0"));
        // 監聽滑條滑動時更新小費$
        tipPercentageSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            try {
                //取得金額
                double amount = Double.parseDouble(amountTextField.getText());
                //計算小費
                double tip = calculate_tip();
                //計算總價
                double total = amount + tip;
                //顯示小費
                tipTextField.setText(String.format("%.0f", tip));
                //顯示總價
                totalTextField.setText(String.format("%.0f",total));
            } catch (NumberFormatException e) {
            //例外處理
                amountTextField.setText(String.format("0"));
                tipTextField.setText(String.format("0"));
                totalTextField.setText(String.format("0"));
            }
        });

        // 監聽金額輸入時計算總額$
        amountTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                //計算小費
                double tip = calculate_tip();
                //計算總價
                double total = Double.parseDouble(newValue) + tip;
                //顯示小費
                tipTextField.setText(String.format("%.0f", tip));
                //顯示總價
                totalTextField.setText(String.format("%.0f",total));
            } catch (NumberFormatException e) {
                amountTextField.setText(String.format("0"));
                tipTextField.setText(String.format("0"));
                totalTextField.setText(String.format("0"));
            }
        });
        // 綁定小費百分比與滑條數值
        tipPercentageLabel.textProperty().bind(tipPercentageSlider.valueProperty().asString("%.1f%%"));
    }
}

