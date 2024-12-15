/*
b:<書名: java, 出版年份: 1982, 作者: ben frank>
ab:<書名: java, 出版年份: 1982, 作者: ben frank, 大小(MB):20000MB, 播放長度(分鐘): 850, 表演者: 星街彗星>
pb:<書名: java, 出版年份: 1982, 作者: ben frank, 出版商: hololive, ISBN: 0-306-40615-2>
*/
package g3_10;
import java.util.*;

public class G3_10 {
    // 定義書類別 Book
    public static class Book {
        // 私密屬性: 書名、出版年份、作者
        private String title;
        private String yearOfPublication;
        private String author;

        // 建構子: 初始化書名、出版年份、作者
        public Book(String title, String yearOfPublication, String author) {
            this.title = title;
            this.yearOfPublication = yearOfPublication;
            this.author = author;
        }

        // 存取方法: 取得書名
        public String getTitle() {
            return title;
        }

        // 存取方法: 取得出版年份
        public String getYearOfPublication() {
            return yearOfPublication;
        }

        // 存取方法: 取得作者
        public String getAuthor() {
            return author;
        }

        // 存取方法: 設定書名
        public void setTitle(String title) {
            this.title = title;
        }

        // 存取方法: 設定出版年份
        public void setYearOfPublication(String yearOfPublication) {
            this.yearOfPublication = yearOfPublication;
        }

        // 存取方法: 設定作者
        public void setAuthor(String author) {
            this.author = author;
        }

        // toString 方法: 返回書的屬性資訊
        public String toString() {
            return "<書名: " + title + ", 出版年份: " + yearOfPublication + ", 作者: " + author + ">";
        }
    }

    // 定義有聲書類別 AudioBook，繼承自 Book
    static class AudioBook extends Book {
        // 私密屬性: 檔案大小、播放長度、表演者姓名
        private String sizeInMB;
        private String playLength;
        private String playbackArtistName;

        // 建構子: 初始化有聲書屬性
        public AudioBook(Book b, String sizeInMB, String playLength, String playbackArtistName) {
            super(b.getTitle(), b.getYearOfPublication(), b.getAuthor());
            this.sizeInMB = sizeInMB;
            this.playLength = playLength;
            this.playbackArtistName = playbackArtistName;
        }

        // 存取方法: 取得書的屬性
        public Book getBook() {
            return new Book(getTitle(), getYearOfPublication(), getAuthor());
        }

        // 存取方法: 取得檔案大小
        public String getSizeInMB() {
            return sizeInMB;
        }

        // 存取方法: 取得播放長度
        public String getPlayLength() {
            return playLength;
        }

        // 存取方法: 取得表演者姓名
        public String getPlaybackArtistName() {
            return playbackArtistName;
        }

        // 存取方法: 設定書的屬性
        public void setBook(Book b) {
            setTitle(b.getTitle());
            setYearOfPublication(b.getYearOfPublication());
            setAuthor(b.getAuthor());
        }

        // 存取方法: 設定檔案大小
        public void setSizeInMB(String sizeInMB) {
            this.sizeInMB = sizeInMB;
        }

        // 存取方法: 設定播放長度
        public void setPlayLength(String playLength) {
            this.playLength = playLength;
        }

        // 存取方法: 設定表演者姓名
        public void setPlaybackArtistName(String playbackArtistName) {
            this.playbackArtistName = playbackArtistName;
        }

        // toString 方法: 返回有聲書的屬性資訊
        public String toString() {
            return "<書名: " + super.getTitle() + ", 出版年份: " + super.getYearOfPublication() + ", 作者: " + super.getAuthor() +
                    ", 大小(MB): " + sizeInMB + ", 播放長度(分鐘): " + playLength + ", 表演者: " + playbackArtistName + ">";
        }
    }

    // 定義印刷書類別 PrintBook，繼承自 Book
    static class PrintBook extends Book {
        // 私密屬性: 出版商、ISBN
        private String publisher;
        private String isbn;

        // 建構子: 初始化印刷書屬性
        public PrintBook(Book b, String publisher, String isbn) {
            super(b.getTitle(), b.getYearOfPublication(), b.getAuthor());
            this.publisher = publisher;
            this.isbn = isbn;
        }

        // 存取方法: 取得書的屬性
        public Book getBook() {
            return new Book(getTitle(), getYearOfPublication(), getAuthor());
        }

        // 存取方法: 取得出版商
        public String getPublisher() {
            return publisher;
        }

        // 存取方法: 取得 ISBN
        public String getIsbn() {
            return isbn;
        }

        // 存取方法: 設定書的屬性
        public void setBook(Book b) {
            setTitle(b.getTitle());
            setYearOfPublication(b.getYearOfPublication());
            setAuthor(b.getAuthor());
        }

        // 存取方法: 設定出版商
        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        // 存取方法: 設定 ISBN
        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        // toString 方法: 返回印刷書的屬性資訊
        public String toString() {
            return "<書名: " + super.getTitle() + ", 出版年份: " + super.getYearOfPublication() + ", 作者: " + super.getAuthor() +
                    ", 出版商: " + publisher + ", ISBN: " + isbn + ">";
        }
    }

    // 主程式: 建立書、有聲書和印刷書物件，並輸出屬性
    public static void main(String[] args) {
        Book b = new Book("java", "1982", "ben frank");  // 建立書物件
        AudioBook ab = new AudioBook(b, "20000MB", "850", "星街彗星");  // 建立有聲書物件
        PrintBook pb = new PrintBook(b, "hololive", "0-306-40615-2");  // 建立印刷書物件
        System.out.printf("b:%s\nab:%s\npb:%s\n", b, ab, pb);  // 列印物件內容
    }
}

