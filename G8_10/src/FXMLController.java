/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;

public class FXMLController{
    public class MyRectangle{
        public double x1;
        public double x2;
        public double y1;
        public double y2;
        public MyRectangle(double x1, double y1, double x2, double y2){
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
        public void draw(GraphicsContext gc){
            gc.setStroke(Color.BLACK);
            double wigth = Math.abs(x1-x2);
            double heigth = Math.abs(y1-y2);
            double startX = Math.min(x1, x2);
            double startY = Math.min(y1, y2);
            gc.strokeRect( startX, startY, wigth, heigth);
        }
    }
    
    private MyRectangle Rect = null;
    @FXML private Canvas canvas;
    @FXML private Label area;
    
    public void initialize() {
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, this::handleMousePressed);
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::handleMouseDragged);
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, this::handleMouseReleased);
    }    
    // 畫布滑鼠壓下事件處理器
    private void handleMousePressed(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        Rect = new MyRectangle(x, y, x, y);
        updateAreaLabel();
    }

    // 畫布滑鼠拖曳事件處理器
    private void handleMouseDragged(MouseEvent e) {
        if(Rect!=null){
            Rect.x2 = e.getX();
            Rect.y2 = e.getY();
            redraw(canvas.getGraphicsContext2D());
            updateAreaLabel();
        }
    }

    // 畫布滑鼠放開事件處理器
    private void handleMouseReleased(MouseEvent e) {
        if (Rect != null) {
            Rect.x2 = e.getX();
            Rect.y2 = e.getY();
            redraw(canvas.getGraphicsContext2D());
            updateAreaLabel();
        }
    }

    // 畫布滑鼠移動事件處理器
    private void updateAreaLabel(){
        if (Rect != null) {
            double x1 = Rect.x1;
            double y1 = Rect.y1;
            double x2 = Rect.x2;
            double y2 = Rect.y2;
            double w = Math.abs(x1-x2);
            double h = Math.abs(y1-y2);
            double ans = w*h;
            area.setText(String.format("Wigth & Heigth: (%.1f, %.1f)，Rectangle area: %.1f", w, h, ans)); 
        }
    }

    // 畫布重繪方法
    private void redraw(GraphicsContext gc) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Rect.draw(gc);
    }
}
