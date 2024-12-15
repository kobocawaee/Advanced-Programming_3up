/*
als.get(0).getName(): 點

als.get(1).getName(): 圓

als.get(2).getName(): 圓柱

als.get(1).area(): 78.539816

als.get(1).volume(): 0.000000

als.get(2).area(): 188.495559

als.get(2).volume(): 197.920337
*/
package g4_10;
import java.util.*;

public class G4_10 {
    // 抽象類別 Shape，定義共同方法 area、volume 以及抽象方法 getName
    abstract static class Shape {
        // 預設的面積方法，返回 0
        public double area() {
            return 0;
        }

        // 預設的體積方法，返回 0
        public double volume() {
            return 0;
        }

        // 抽象方法 getName，由具體的子類別實現
        public abstract String getName();
    }

    // 點類別，繼承自 Shape，具有 x 和 y 座標
    static class Point extends Shape {
        protected int x, y;  // x 和 y 座標
        
        // 建構子，初始化點的座標
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        // 設定點的座標
        public void setPoint(int x, int y){
            this.x = x;
            this.y = y;
        }

        // 取得 x 座標
        public int getX(){
            return x;
        }

        // 取得 y 座標
        public int getY(){
            return y;
        }

        // 回傳點的座標資訊
        public String toString(){
            return String.format("座標值=[%d,%d]", x, y);
        }

        // 回傳形狀名稱「點」
        public String getName() {
            return "點";
        }
    }

    // 圓類別，繼承自 Point，額外增加 radius 半徑屬性
    static class Circle extends Point {
        protected int radius;  // 半徑

        // 建構子，初始化圓心座標和半徑
        public Circle(int x, int y, int radius){
            super(x, y);  // 呼叫父類別 Point 的建構子來設定 x 和 y
            if(radius <= 0){
                System.out.println("半徑必須大於0");
            }
            else{
                this.radius = radius;
            }
        }

        // 設定圓的半徑
        public void setRadius(int radius){
            this.radius = radius;
        }

        // 取得圓的半徑
        public int getRadius(){
            return radius;
        }

        // 計算並回傳圓的面積，公式為 π*r^2
        public double area(){
            return radius * radius * Math.PI;
        }

        // 回傳圓的資訊，包括圓心座標和半徑
        public String toString(){
            return String.format("圓心=[%d,%d],半徑=%d", super.getX(), super.getY(), radius);
        }

        // 回傳形狀名稱「圓」
        public String getName() {
            return "圓";
        }
    }

    // 圓柱類別，繼承自 Circle，額外增加 height 柱高屬性
    static class Cylinder extends Circle {
        protected int height;  // 柱高

        // 建構子，初始化圓柱的圓心、半徑和柱高
        public Cylinder(int x, int y, int radius, int height){
            super(x, y, radius);  // 呼叫父類別 Circle 的建構子來設定 x、y 和 radius
            if(height <= 0){
                System.out.println("柱高必須大於0");
            }
            else{
                this.height = height;
            }
        }

        // 設定圓柱的柱高
        public void setHeight(int height){
            this.height = height;
        }

        // 取得圓柱的柱高
        public int getHeight(){
            return height;
        }

        // 計算並回傳圓柱的表面積，公式為 2πr(r + h)
        public double area(){
            return  2 * super.area() + (2 * super.getRadius() * height * Math.PI);
        }

        // 計算並回傳圓柱的體積，公式為 πr^2h
        public double volume(){
            return super.area() * height;
        }

        // 回傳圓柱的資訊，包括圓心座標、半徑和柱高
        public String toString(){
            return String.format("圓心=[%d,%d],半徑=%d,柱高=%d", super.getX(), super.getY(), super.getRadius(), height);
        }

        // 回傳形狀名稱「圓柱」
        public String getName() {
            return "圓柱";
        }
    }

    // 主方法
    public static void main(String[] args) {
        // 建立一個形狀列表來儲存不同的形狀物件
        ArrayList<Shape> als = new ArrayList<Shape>();

        // 建立一個點對象並添加到列表中
        Point p = new Point(0, 0);
        als.add(p);

        // 建立一個圓對象並添加到列表中
        Circle c = new Circle(1, 1, 5);
        als.add(c);

        // 建立一個圓柱對象並添加到列表中
        Cylinder y = new Cylinder(2, 2, 3, 7);
        als.add(y);

        // 列印出各形狀的名稱、面積、體積
        System.out.printf("als.get(0).getName(): %s\n\n" +
                          "als.get(1).getName(): %s\n\n" +
                          "als.get(2).getName(): %s\n\n" +
                          "als.get(1).area(): %f\n\n" +
                          "als.get(1).volume(): %f\n\n"+
                          "als.get(2).area(): %f\n\n" +
                          "als.get(2).volume(): %f\n",
                          als.get(0).getName(),
                          als.get(1).getName(),
                          als.get(2).getName(),
                          als.get(1).area(),
                          als.get(1).volume(),
                          als.get(2).area(),
                          als.get(2).volume());
    }
}

