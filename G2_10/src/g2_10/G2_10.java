/*
p:[1.0,1.0], ci:[1.0,1.0], 半徑=2.0, cy:[1.0,1.0], 半徑=2.0, 柱高=3.0
圓柱面積=62.83, 圓柱體積=37.70, 圓面積=12.57
 */
package g2_10;
import java.util.*;
public class G2_10 {
    // 定義一個靜態類別 Point (點)
    static class Point {
        protected double x, y; // 定義點的x與y座標

        // 建構子，初始化x與y
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        // 設定點的座標
        public void setPoint(double x, double y) {
            this.x = x;
            this.y = y;
        }

        // 取得x座標
        public double getX() {
            return x;
        }

        // 取得y座標
        public double getY() {
            return y;
        }

        // 回傳點的座標格式為 [x, y]
        public String toString() {
            return "["+ x + "," + y +"]";
        }
    }

    // 定義一個靜態類別 Circle (圓)，繼承 Point
    static class Circle extends Point {
        protected double radius; // 定義圓的半徑

        // 建構子，初始化圓心座標與半徑
        public Circle(double x, double y, double radius) {
            super(x, y);
            this.radius = radius;
        }

        // 設定半徑
        public void setRadius(double radius) {
            this.radius = radius;
        }

        // 取得半徑
        public double getRadius() {
            return this.radius;
        }

        // 計算圓的面積
        public double get_circle_area() {
            return radius * radius * Math.PI;
        }

        // 回傳圓的資訊，包含圓心座標與半徑
        public String toString() {
            return super.toString() + ", 半徑=" + radius;
        }
    }

    // 定義一個靜態類別 Cylinder (圓柱)，繼承 Circle
    static class Cylinder extends Circle {
        protected double height; // 定義圓柱的高度

        // 建構子，初始化圓心座標、半徑與圓柱高度
        public Cylinder(double x, double y, double radius, double height) {
            super(x, y, radius);
            this.height = height;
        }

        // 設定圓柱高度
        public void setHeight(double height) {
            this.height = height;
        }

        // 取得圓柱高度
        public double getHeight() {
            return this.height;
        }

        // 計算圓柱的體積
        public double getvolume() {
            return super.get_circle_area() * height;
        }

        // 計算圓柱的表面積
        public double get_Cylinder_area() {
            return 2 * super.get_circle_area() + (2 * super.getRadius() * height * Math.PI);
        }

        // 回傳圓柱的資訊，包含圓心座標、半徑與高度
        public String toString() {
            return super.toString() + ", 柱高=" + height;
        }
    }

    // 主程式
    public static void main(String[] args) {
        Point p = new Point(1,1); // 創建一個點物件
        Circle ci = new Circle(1,1,2); // 創建一個圓物件
        Cylinder cy = new Cylinder(1,1,2,3); // 創建一個圓柱物件

        // 顯示點、圓和圓柱的資訊
        System.out.printf("p:%s, ci:%s, cy:%s\n", p, ci, cy);

        // 顯示圓柱表面積、體積及圓的面積
        System.out.printf("圓柱面積=%.2f, 圓柱體積=%.2f, 圓面積=%.2f\n", cy.get_Cylinder_area(), cy.getvolume(), ci.get_circle_area());
    }
}
