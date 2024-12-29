/*
先輸入分子再輸入分母
請輸入第一個數字的分子及分母:1 2
請輸入第二個數字的分子及分母:1 3

相加測試-------------------------------------
5/6
0.83
請輸入想要印到小數點右n位:4
0.8333

相減測試-------------------------------------
1/6
0.17
請輸入想要印到小數點右n位:7
0.1666667
 */
package rational_number_operations;
import java.util.*;

class Rational {
    public int numerator; // 分子
    public int denominator; // 分母
    
    // 預設建構子，將分子設為 0，分母設為 1
    public Rational(){
        this.numerator = 0;
        this.denominator = 1;
    }
    
    // 帶參數的建構子，初始化分子和分母
    public Rational(int numerator, int denominator){
        this.numerator = numerator;
        this.denominator = denominator;
    }
    
    // 加法靜態方法，傳入兩個 Rational 對象，返回相加的結果
    public static Rational add(Rational a, Rational b){
        Rational c  = new Rational();
        // 分子：a 分子的比例調整 + b 分子的比例調整
        c.numerator = a.numerator * b.denominator + b.numerator * a.denominator;
        // 分母：兩個分母相乘
        c.denominator = a.denominator * b.denominator;
        return c; // 返回相加結果
    }
    
    // 減法靜態方法，傳入兩個 Rational 對象，返回相減的結果
    public static Rational subtract(Rational a, Rational b){
        Rational c  = new Rational();
        // 分子：a 分子的比例調整 - b 分子的比例調整
        c.numerator = a.numerator * b.denominator - b.numerator * a.denominator;
        // 分母：兩個分母相乘
        c.denominator = a.denominator * b.denominator;
        return c; // 返回相減結果
    }
    
    // 將 Rational 轉換為分數形式的字串 (例如：3/4)
    public String toString(){
        return this.numerator + "/" + this.denominator;
    }
    
    // 將 Rational 轉換為小數形式的字串，保留兩位小數 (例如：0.75)
    public String toString2(){
        double a = (double)this.numerator / this.denominator; // 進行浮點數除法
        return String.format("%.2f", a); // 格式化輸出，保留兩位小數
    }
    
    // 將 Rational 轉換為小數形式，並允許用戶指定保留的小數位數
    public String toString2(int n){
        double a = (double)this.numerator / this.denominator; // 進行浮點數除法
        String decimal = "%." + n + "f"; // 根據輸入動態生成格式
        return String.format(decimal, a); // 格式化輸出，保留 n 位小數
    }
}

public class Rational_number_operations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // 建立 Scanner 對象讀取用戶輸入
        
        // 提示用戶輸入第一個有理數的分子和分母
        System.out.println("先輸入分子再輸入分母");
        System.out.print("請輸入第一個數字的分子及分母:");
        Rational rational1 = new Rational(sc.nextInt(), sc.nextInt()); // 創建第一個 Rational 對象
        
        // 提示用戶輸入第二個有理數的分子和分母
        System.out.print("請輸入第二個數字的分子及分母:");
        Rational rational2 = new Rational(sc.nextInt(), sc.nextInt()); // 創建第二個 Rational 對象
        
        // 相加測試
        System.out.println("\n相加測試-------------------------------------");
        Rational rational3 = Rational.add(rational1, rational2); // 將兩個有理數相加
        System.out.println(rational3.toString()); // 印出加法結果的分數形式
        System.out.println(rational3.toString2()); // 印出加法結果的小數形式，保留兩位小數
        
        // 提示用戶輸入想要保留的小數位數
        System.out.print("請輸入想要印到小數點右n位:");
        int n = sc.nextInt();
        System.out.println(rational3.toString2(n)); // 根據用戶輸入的小數位數印出結果
        
        // 相減測試
        System.out.println("\n相減測試-------------------------------------");
        Rational rational4 = Rational.subtract(rational1, rational2); // 將兩個有理數相減
        System.out.println(rational4.toString()); // 印出減法結果的分數形式
        System.out.println(rational4.toString2()); // 印出減法結果的小數形式，保留兩位小數
        
        // 提示用戶輸入想要保留的小數位數
        System.out.print("請輸入想要印到小數點右n位:");
        n = sc.nextInt();
        System.out.println(rational4.toString2(n)); // 根據用戶輸入的小數位數印出結果
    }
}

