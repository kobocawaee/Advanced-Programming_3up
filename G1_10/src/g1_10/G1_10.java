/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package g1_10;
import java.util.*;
public class G1_10 {
    public static class Complex{
        double realPart;
        double imaginaryPart;
        public Complex(){
            this(0,0);
        }
        public Complex(double real, double imaginary){
            this.realPart=real;
            this.imaginaryPart=imaginary;
        }
        public static void classAdd(Complex a,Complex b){
            a.realPart+=b.realPart;
            a.imaginaryPart+=b.imaginaryPart;
        }

        public void add(Complex b){
            this.realPart+=b.realPart;
            this.imaginaryPart+=b.imaginaryPart;
        }
        public static void classSubtract(Complex a,Complex b){
            a.realPart-=b.realPart;
            a.imaginaryPart-=b.imaginaryPart;
        }
        public void subtract(Complex b){
            this.realPart-=b.realPart;
            this.imaginaryPart-=b.imaginaryPart;
        }
        public String toString(){
            String s = "";
            s+=Double.toString(this.realPart)+"+("+Double.toString(this.imaginaryPart)+")i";
            return s;
        }
    }
    public static void main(String[] args) {
        Complex a = new Complex();
        Complex b = new Complex(1,2);
        System.out.println("Original data:");
        System.out.printf("a = %s%n",a.toString()); //利用toString顯示a狀態
        System.out.printf("b = %s%n",b.toString()); //利用toString顯示b狀態
        Complex.classAdd(a, b);//利用public static void classAdd(Complex a,Complex b)運算a+b
        System.out.printf("a + b using Complex.classAdd(Complex a,Complex b):%na = %s%nb = %s%n",a.toString(),b.toString());//利用toString顯示a狀態
        a.add(b);//利用public void add(Complex b)運算a+b
        System.out.printf("a + b using a.add(b):%na = %s%nb = %s%n",a.toString(),b.toString());//利用toString顯示a狀態
        Complex.classSubtract(a, b);//利用public static void classAdd(Complex a,Complex b)運算a-b
        System.out.printf("a - b using Complex.classSubtract(Complex a,Complex b):%na = %s%nb = %s%n",a.toString(),b.toString());//利用toString顯示a狀態
        a.subtract(b);//利用public void add(Complex b)運算a-b
        System.out.printf("a - b using a.subtract(b):%na = %s%nb = %s%n",a.toString(),b.toString());//利用toString顯示a狀態
    }
}
