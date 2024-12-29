import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class FXMLController {

    // 抽象形狀類別
    abstract static class MyShape {

        protected double x1, y1, x2, y2;  // 形狀的一角及另一角(x,y)座標
        protected Color color; // 形狀顏色
        protected boolean filled; // 形狀是否填滿

        // 建構子
        public MyShape(double x1, double y1, double x2, double y2, Color color, boolean filled) {
            this.x1=x1;
            this.x2=x2;
            this.y1=y1;
            this.y2=y2;
            this.color=color;
            this.filled=filled;
          // 填寫形狀屬性初始值
        }
        
        public double get_Width(){
            return Math.abs(x1-x2);
        }

        public double get_High(){
            return Math.abs(y1-y2);
        }
        // 形狀繪圖方法，待後代形狀補齊此家規
        public abstract void draw(GraphicsContext gc);
    }

    // 矩形類別
    static class MyRectangle extends MyShape {
        // 建構子
        public MyRectangle(double x1, double y1, double x2, double y2, 
                           Color color, boolean filled) {
            super(x1, y1, x2, y2, color, filled);
        }

        @Override // 矩形繪圖方法
        public void draw(GraphicsContext gc) {
            if(this.filled){
                gc.setFill(this.color);

                gc.fillRect(Math.min(this.x1,this.x2), Math.min(this.y1,this.y2), this.get_Width(), this.get_High());
            }else{
                gc.setStroke(this.color); 
                gc.setLineWidth(2); 
                gc.strokeRect(Math.min(this.x1,this.x2), Math.min(this.y1,this.y2), this.get_Width(), this.get_High()); 
            } 
            // 依據形狀的 color, filled, x1, y1, x2, y2 屬性在畫布繪製矩形
        }
    }

    // 圓形類別
    static class MyCircle extends MyShape {

        public MyCircle(double x1, double y1, double x2, double y2, 
                        Color color, boolean filled) {
            super(x1, y1, x2, y2, color, filled);
        }

        @Override
        public void draw(GraphicsContext gc) {
            double centerX = (this.x1 + this.x2) / 2; 
            double centerY = (this.y1 + this.y2) / 2; 
            double radius = Math.abs(this.x2 - this.x1) / 2; 

            if (this.filled) {
                
                gc.setFill(this.color); 
                gc.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
            } else {
                gc.setStroke(this.color); 
                gc.setLineWidth(2); 
                gc.strokeOval(centerX - radius, centerY - radius, radius * 2, radius * 2); 
            }
            // 依據形狀的 color, filled, x1, y1, x2, y2 屬性在畫布繪製圓形
        }
    }

    // 直線類別
    static class MyLine extends MyShape {

        public MyLine(double x1, double y1, double x2, double y2, 
                      Color color, boolean filled) {
            super(x1, y1, x2, y2, color, filled);
            
        }

        @Override
        public void draw(GraphicsContext gc) {
            gc.setStroke(this.color);
            gc.setLineWidth(2);
            gc.strokeLine(this.x1, this.y1, this.x2, this.y2);
            // 依據形狀的 color, x1, y1, x2, y2 屬性在畫布繪製直線
        }
    }
    static class MyEllipse extends MyShape {
        public MyEllipse(double x1, double y1, double x2, double y2, Color color, boolean filled) {
            super(x1, y1, x2, y2, color, filled);
        }
        @Override
        public void draw(GraphicsContext gc) {
            double centerX = (this.x1 + this.x2) / 2; 
            double centerY = (this.y1 + this.y2) / 2; 
            double width = Math.abs(this.x2 - this.x1); 
            double height = Math.abs(this.y2 - this.y1); 

            if (this.filled) {
                gc.setFill(this.color); 
                gc.fillOval(centerX - width / 2, centerY - height / 2, width, height); 
            } else {
                gc.setStroke(this.color); 
                gc.setLineWidth(2); 
                gc.strokeOval(centerX - width / 2, centerY - height / 2, width, height); 
            }
        }
    }

    // 繪板元件等屬性
    @FXML
    private Canvas canvas;  
    @FXML
    private final ArrayList<MyShape> shapes = new ArrayList<>(); 
    private MyShape currentShape = null; 
    private Color currentColor = Color.BLACK;  
    private boolean filledShape = false; 
    private int shapeType = 0; 
    @FXML
    private Label statusLabel; 
    @FXML
    private ComboBox<String> colorBox; 
    @FXML
    private ComboBox<String> shapeBox; 
    @FXML
    private CheckBox filledCheckBox; 
    @FXML
    private Button undoButton;  
    @FXML
    private Button clearButton;  


    // 建立各元件的動作事件處理器
    public void initialize() 
    {
        canvas.setOnMousePressed(this::handleMousePressed);
        canvas.setOnMouseDragged(this::handleMouseDragged);
        canvas.setOnMouseReleased(this::handleMouseReleased);
        canvas.setOnMouseMoved(this::handleMouseMoved);
        shapeType=0;
        colorBox.getItems().addAll(
            "Red",   
            "Orange",   
            "Yellow",  
            "Green",   
            "Blue",   
            "Purple",   
            "Black",   
            "White"    
        );
        shapeBox.getItems().addAll(
            "Rectangle",
            "Circle",    
            "Line" ,
            "Ellipse"
        );
        colorBox.setValue("Black"); 

        shapeBox.setValue("Rectangle"); 
        // 還原按鈕事件處理器
        undoButton.setOnAction(e -> {
            if(!shapes.isEmpty()){
                shapes.remove(shapes.size()-1); // 蛻ｪ髯､譛�蠕御ｸ�蛟句ｽ｢迢�
                redraw(canvas.getGraphicsContext2D());
            }
            // 若形狀集合不為空，則移除最後一個形狀，並重繪畫布
        });

        // 清除按鈕事件處理器
        clearButton.setOnAction(e -> {
            shapes.removeAll(shapes); 
            redraw(canvas.getGraphicsContext2D());
            // 清除形狀集合，並重繪畫布 
        });

        // 顏色下拉盒事件處理器
        colorBox.setOnAction(e -> {
            String selectedColor = colorBox.getValue();
            switch (selectedColor) {
                case "Red":
                    currentColor = Color.RED; 
                    break;
                case "Orange":
                    currentColor = Color.ORANGE; 
                    break;
                case "Yellow":
                    currentColor = Color.YELLOW; 
                    break;
                case "Green":
                    currentColor = Color.GREEN; 
                    break;
                case "Blue":
                    currentColor = Color.BLUE; 
                    break;
                case "Purple":
                    currentColor = Color.PURPLE;
                    break;
                case "Black":
                    currentColor = Color.BLACK; 
                    break;
                case "White":
                    currentColor = Color.WHITE; 
                    break;
                default:
                    currentColor = Color.BLACK; 
                    break;
            }
            // 依據顏色下拉盒選擇的選項字串，設定目前顏色 currentColor
        });

        // 形狀下拉盒事件處理器
        shapeBox.setOnAction(e -> {
            String selectShapeType = shapeBox.getValue();
            switch (selectShapeType) {
                case "Rectangle":
                    shapeType=0; 
                    break;
                case "Circle":
                    shapeType=1; 
                    break;
                case "Line":
                    shapeType=2;  
                    break;
                case "Ellipse":
                    shapeType=3; 
                    break;
                default:
                    shapeType=0; 
                    break;
            }
            // 依據形狀下拉盒選擇的選項下標0,1,2，設定目前形狀類型 shapeType
        });

        // 填滿勾選盒事件處理器
        filledCheckBox.setOnAction(e -> {
            if(filledCheckBox.isSelected()){
                filledShape=true; 
            }else{
                filledShape=false; 
            }
            // 設定是否填滿形狀 fillShape
        });
    }

    // 畫布滑鼠壓下事件處理器
    private void handleMousePressed(MouseEvent e) {
        // 依據目前形狀類型 shapeType，建立對應形狀物件 currentShape
        double x1 = e.getX();
        double y1 = e.getY();
        switch (shapeType) {
            case 0: 
                currentShape = new MyRectangle(x1, y1, x1, y1, currentColor, filledShape);
                break;
            case 1: 
                currentShape = new MyCircle(x1, y1, x1, y1, currentColor, filledShape);
                break;
            case 2: 
                currentShape = new MyLine(x1, y1, x1, y1, currentColor, filledShape);
                break;
            case 3: 
                currentShape = new MyEllipse(x1, y1, x1, y1, currentColor, filledShape);
                break;
            default:
                currentShape = new MyRectangle(x1, y1, x1, y1, currentColor, filledShape);
                break;
        }
    }

    // 畫布滑鼠拖曳事件處理器
    private void handleMouseDragged(MouseEvent e) {
        // 若目前形狀物件不為空，則設定其第二角(x,y)座標，並重繪畫布
        if (currentShape != null) {
            double x2 = e.getX();
            double y2 = e.getY();
            statusLabel.setText(String.format("X: %.2f ,Y: %.2f",x2,y2)); 
            currentShape.x2 = x2; 
            currentShape.y2 = y2; 
            shapes.add(currentShape); 
            redraw(canvas.getGraphicsContext2D()); 
            shapes.remove(shapes.size()-1); 
        }
    }

    // 畫布滑鼠放開事件處理器
    private void handleMouseReleased(MouseEvent e) {
        if (currentShape != null) {
            double x2 = e.getX();
            double y2 = e.getY();
            currentShape.x2 = x2;
            currentShape.y2 = y2; 
            shapes.add(currentShape); 
            currentShape = null; 
            redraw(canvas.getGraphicsContext2D()); 
        }
        // 若目前形狀物件不為空，
        // 則設定其第二角(x,y)座標，加入形狀集合，
        //   並重繪畫布，將目前形狀物件設為空
    }

    // 畫布滑鼠移動事件處理器
    private void handleMouseMoved(MouseEvent e) {
        // 顯示滑鼠座標於狀態列 statusLabel
        double x = e.getX();
        double y = e.getY();
        statusLabel.setText(String.format("X: %.2f ,Y: %.2f",x,y));
    }

    // 畫布重繪方法
    private void redraw(GraphicsContext gc) {
        gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); 
        for (MyShape shape : shapes) {
            if (shape instanceof MyRectangle) {
                MyRectangle rect = (MyRectangle) shape;
                rect.draw(gc);
            } else if (shape instanceof MyCircle) {
                MyCircle circle = (MyCircle) shape;
                circle.draw(gc);
            }else if(shape instanceof MyLine){
                MyLine line = (MyLine) shape;
                line.draw(gc);
            }else if(shape instanceof MyEllipse){
                MyEllipse ellipse = (MyEllipse) shape;
                ellipse.draw(gc);
            }
        // 清除畫布, 重繪形狀集合中的所有形狀。若目前形狀物件不為空，也重繪之
    }
}
}