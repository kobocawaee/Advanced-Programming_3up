/*
形狀名稱:圓形, 面積: 12.57
形狀名稱:正方形, 面積: 9.00
形狀名稱:球體, 表面積: 201.06, 體積:268.08
形狀名稱:立方體, 表面積: 150.00, 體積:125.00
*/
package g5_10;
import java.util.*;
public class G5_10 {
    // 定義一個形狀接口 (Shape)，所有形狀都必須實作這個接口，並提供形狀名稱的輸出方法 print()
    public interface Shape{
        String print();  // 用來輸出形狀名稱
    }

    // 定義二維形狀的接口 (TwoDimensionalShape)，繼承自 Shape，並額外加入計算面積的方法 area()
    public interface TwoDimensionalShape extends Shape{
        double area();  // 計算二維形狀的面積
    }

    // 定義三維形狀的接口 (ThreeDimensionalShape)，繼承自 Shape，並額外加入計算面積和體積的方法
    public interface ThreeDimensionalShape extends Shape{
        double area();   // 計算三維形狀的表面積
        double volume(); // 計算三維形狀的體積
    }

    // 定義圓形類別，實作 TwoDimensionalShape 接口
    static class Circle implements TwoDimensionalShape{
        public double radius; // 半徑
        public Circle(double radius){
            this.radius = radius;
        }
        public String print(){
            return "圓形";  // 回傳圓形名稱
        }
        public double area(){
            return Math.pow(radius, 2) * Math.PI; // 計算圓形面積，公式為 πr²
        }
    }

    // 定義正方形類別，實作 TwoDimensionalShape 接口
    static class Square implements TwoDimensionalShape {
        public double side; // 邊長
        public Square(double side){
            this.side = side;
        }
        public String print(){
            return "正方形"; // 回傳正方形名稱
        }
        public double area(){
            return Math.pow(side, 2); // 計算正方形面積，公式為 邊長²
        }
    }

    // 定義球體類別，實作 ThreeDimensionalShape 接口
    static class Sphere implements ThreeDimensionalShape{
        public double radius; // 半徑
        public Sphere(double radius){
            this.radius = radius;
        }
        public String print(){
            return "球體"; // 回傳球體名稱
        }
        public double area(){
            return Math.pow(radius, 2) * Math.PI * 4; // 計算球體表面積，公式為 4πr²
        }
        public double volume(){
            return Math.PI * Math.pow(radius, 3) * 4 / 3; // 計算球體體積，公式為 (4/3)πr³
        }
    }

    // 定義立方體類別，實作 ThreeDimensionalShape 接口
    static class Cube implements ThreeDimensionalShape{
        public double side; // 邊長
        public Cube(double side){
            this.side = side;
        }
        public String print(){
            return "立方體"; // 回傳立方體名稱
        }
        public double area(){
            return Math.pow(side, 2)*6; // 計算立方體表面積，公式為 6 * 邊長²
        }
        public double volume(){
            return Math.pow(side, 3); // 計算立方體體積，公式為 邊長³
        }
    }

    public static void main(String[] args){
        // 創建一個包含不同形狀的陣列
        Shape[] shapes = {new Circle(2), new Square(3), new Sphere(4), new Cube(5)};
        
        // 遍歷所有形狀，根據類型分別輸出面積和體積
        for (Shape shape : shapes){
            if(shape instanceof TwoDimensionalShape){ // 檢查形狀是否為二維形狀
                TwoDimensionalShape a = (TwoDimensionalShape) shape;
                // 印出二維形狀的名稱和面積
                System.out.printf("形狀名稱:%s, 面積: %.2f\n", a.print(), a.area());
            }
            else{ // 如果是三維形狀
                ThreeDimensionalShape b = (ThreeDimensionalShape) shape;
                // 印出三維形狀的名稱、表面積和體積
                System.out.printf("形狀名稱:%s, 表面積: %.2f, 體積:%.2f\n", b.print(), b.area(), b.volume());
            }
        }
    }
}

