/*
d:2004/1/27, cTime:2004/1/27  15:40:24, iTime:2004/1/27  15:40:24
*/
package hw2_411630915;
import java.util.*;
public class HW2_411630915 {

    // 定義 Date 類別，表示日期 (年、月、日)
    static class Date {
        private int year;
        private int month;
        private int day;
        
        // Date 類別的建構子，初始化 year, month, day
        public Date(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }
        
        // 取得年份
        public int getYear() {
            return year;
        }

        // 取得月份
        public int getMonth() {
            return month;
        }

        // 取得日期
        public int getDay() {
            return day;
        }
        
        // 設定年份
        public void setYear(int year) {
            this.year = year;
        }

        // 設定月份
        public void setMonth(int month) {
            this.month = month;
        }

        // 設定日期
        public void setDay(int day) {
            this.day = day;
        }
        
        // 將日期轉為字串格式 (year/month/day)
        public String toString() {
            return year + "/" + month + "/" + day;
        }
    }

    // 定義 ComposeTime 類別，表示組合時間 (日期 + 時、分、秒)
    static class ComposeTime {
        private Date date;  // 組合日期 (組合模式)
        private int hour;
        private int minute;
        private int second;
        
        // ComposeTime 類別的建構子，初始化 date, hour, minute, second
        public ComposeTime(Date date, int hour, int minute, int second) {
            this.date = date;
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }

        // 取得日期
        public Date getDate() {
            return date;
        }

        // 取得小時
        public int getHour() {
            return hour;
        }

        // 取得分鐘
        public int getMinute() {
            return minute;
        }

        // 取得秒數
        public int getSecond() {
            return second;
        }

        // 設定日期
        public void setDate(Date date) {
            this.date = date;
        }

        // 設定小時
        public void setHour(int hour) {
            this.hour = hour;
        }

        // 設定分鐘
        public void setMinute(int minute) {
            this.minute = minute;
        }

        // 設定秒數
        public void setSecond(int second) {
            this.second = second;
        }

        // 將組合的時間 (日期 + 時間) 轉為字串格式
        public String toString() {
            return date.toString() + "  " + hour + ":" + minute + ":" + second;
        }
    }

    // 定義 InheritTime 類別，繼承自 Date，額外加入時、分、秒 (繼承模式)
    static class InheritTime extends Date {
        private int hour;
        private int minute;
        private int second;

        // InheritTime 類別的建構子，繼承日期並初始化時間
        public InheritTime(Date date, int hour, int minute, int second) {
            super(date.getYear(), date.getMonth(), date.getDay());  // 呼叫父類別 Date 的建構子
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }

        // 取得日期，返回新的 Date 物件
        public Date getDate() {
            return new Date(getYear(), getMonth(), getDay());
        }

        // 取得小時
        public int getHour() {
            return hour;
        }

        // 取得分鐘
        public int getMinute() {
            return minute;
        }

        // 取得秒數
        public int getSecond() {
            return second;
        }

        // 設定日期，使用父類別的 setter 方法
        public void setDate(Date date) {
            setYear(date.getYear());
            setMonth(date.getMonth());
            setDay(date.getDay());
        }

        // 設定小時
        public void setHour(int hour) {
            this.hour = hour;
        }

        // 設定分鐘
        public void setMinute(int minute) {
            this.minute = minute;
        }

        // 設定秒數
        public void setSecond(int second) {
            this.second = second;
        }

        // 將繼承的日期和時間轉為字串格式
        public String toString() {
            return super.toString() + "  " + hour + ":" + minute + ":" + second;
        }
    }

    // 主程式，測試類別和物件方法
    public static void main(String[] args) {
        // 建立 Date 物件
        Date d = new Date(2004, 1, 27);

        // 建立 ComposeTime 物件 (組合模式)
        ComposeTime cTime = new ComposeTime(d, 15, 40, 24);

        // 建立 InheritTime 物件 (繼承模式)
        InheritTime iTime = new InheritTime(d, 15, 40, 24);

        // 輸出 Date, ComposeTime, InheritTime 的內容
        System.out.printf("d:%s, cTime:%s, iTime:%s%n", d, cTime, iTime);
    }
}

