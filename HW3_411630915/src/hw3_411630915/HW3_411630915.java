/*
週薪 => Employee: Ben, earning: 1000.00
時薪 => Employee: Camey, earning: 712.50
抽佣 => Employee: Kimberly, earning: 500.00
底薪加抽佣 => Employee: Ryu, earning: 1500000.10

-- 修改計薪模型 --
週薪 => Employee: Kimberly, earning: 1200.00
抽佣 => Employee: Ryu, earning: 900.00
*/
package hw3_411630915;
import java.util.*;
public class HW3_411630915 {
    // 定義補償模型的介面，包含一個計算收入的抽象方法
    interface CompensationModel {
        double earnings(); // 計算收入的方法
    }

    // 薪資制的補償模型實作
    static class SalariedCompensationModel implements CompensationModel {
        private double weeklySalary; // 每週薪水
        public SalariedCompensationModel(double weeklySalary){
            this.weeklySalary = weeklySalary;
        }
        public void setWeeklySalary(double weeklySalary){
            this.weeklySalary = weeklySalary; // 設定每週薪水
        }
        public double earnings() {
            return weeklySalary; // 回傳每週薪水
        }
    }

    // 計時制的補償模型實作
    static class HourlyCompensationModel implements CompensationModel {
        private double wage; // 時薪
        private double hours; // 工作時數
        public HourlyCompensationModel(double wage, double hours){
            this.wage = wage;
            this.hours = hours;
        }

        public void setWage(double wage){
            this.wage = wage; // 設定時薪
        }

        public void setHours(double hours){
            this.hours = hours; // 設定工作時數
        }

        public double earnings() {
            // 如果工作時數小於或等於40小時，按時薪計算
            // 否則，超過40小時的部分按1.5倍時薪計算
            if(hours <= 40){
                return wage * hours;
            }
            else{
                return 40 * wage + (hours - 40) * wage * 1.5;
            }
        }
    }

    // 抽佣制的補償模型實作
    static class CommissionCompensationModel implements CompensationModel {
        private double grossSales; // 銷售總額
        private double commissionRate; // 抽佣比例
        public CommissionCompensationModel(double grossSales, double commissionRate){
            this.grossSales = grossSales;
            this.commissionRate = commissionRate;
        }

        public void setGrossSales(double grossSales){
            this.grossSales = grossSales; // 設定銷售總額
        }

        public void setCommissionRate(double commissionRate){
            this.commissionRate = commissionRate; // 設定抽佣比例
        }

        public double earnings() {
            return grossSales * commissionRate; // 根據銷售總額和抽佣比例計算收入
        }
    }

    // 底薪加抽佣的補償模型實作
    static class BasePlusCommissionCompensationModel implements CompensationModel {
        private double grossSales; // 銷售總額
        private double commissionRate; // 抽佣比例
        private double baseSalary; // 底薪
        public BasePlusCommissionCompensationModel(double grossSales, double commissionRate, double baseSalary){
            this.grossSales = grossSales;
            this.commissionRate = commissionRate;
            this.baseSalary = baseSalary;
        }

        public void setGrossSales(double grossSales){
            this.grossSales = grossSales; // 設定銷售總額
        }

        public void setCommissionRate(double commissionRate){
            this.commissionRate = commissionRate; // 設定抽佣比例
        }

        public void setBaseSalary(double baseSalary){
            this.baseSalary = baseSalary; // 設定底薪
        }

        public double earnings() {
            // 底薪加上銷售收入的抽佣
            return baseSalary + grossSales * commissionRate;
        }
    }

    // 員工類別，使用補償模型來計算收入
    static class Employee {

        private String name; // 員工姓名
        private CompensationModel compensationModel; // 補償模型

        public Employee(String name, CompensationModel compensationModel) {
            this.name = name;
            this.compensationModel = compensationModel;
        }

        // 計算員工的收入
        public double earnings() {
            return compensationModel.earnings();
        }

        // 設定新的補償模型
        public void setCompensationModel(CompensationModel compensationModel) {
            this.compensationModel = compensationModel;
        }

        // 產生員工資訊的字串
        public String toString() {
            return String.format("Employee: %s, earning: %.2f", name, earnings());
        }
    }

    // 主程式入口
    public static void main(String[] args) {
         // 建立員工並使用不同的補償模型
         Employee a = new Employee("Ben", new SalariedCompensationModel(1000.0));
         System.out.println("週薪 => "+a);

         Employee b = new Employee("Camey", new HourlyCompensationModel(15.0, 45));
         System.out.println("時薪 => "+b);

         Employee c = new Employee("Kimberly", new CommissionCompensationModel(5000.0, 0.1));
         System.out.println("抽佣 => "+c);

         Employee d = new Employee("Ryu", new BasePlusCommissionCompensationModel(300.0, 5000.0, 0.1));
         System.out.println("底薪加抽佣 => "+d);

         System.out.println("\n-- 修改計薪模型 --");

         // 修改員工的補償模型
         c.setCompensationModel(new SalariedCompensationModel(1200.0));
         System.out.println("週薪 => "+c);

         d.setCompensationModel(new CommissionCompensationModel(6000.0, 0.15));
         System.out.println("抽佣 => "+d);
    }
}

