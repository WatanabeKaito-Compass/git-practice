package test.java.org.example.p025;

/**
 * Javaオブジェクト指向プログラミングのテストクラス
 * クラス、インターフェイス、抽象クラス、継承、インスタンス化の実装例
 */
public class Java025Test {

    public static void main(String[] args) {
        Java025Test test = new Java025Test();
        
        System.out.println("=== Javaオブジェクト指向プログラミング演習 ===");
        
        System.out.println("\n=== 演習1: インターフェイスの実装 ===");
        test.ensyu1();
        
        System.out.println("\n=== 演習2: 抽象クラスの継承 ===");
        test.ensyu2();
        
        System.out.println("\n=== 演習3: 具象クラスの継承 ===");
        test.ensyu3();
        
        System.out.println("\n=== 演習4: ポリモーフィズム ===");
        test.ensyu4();
        
        System.out.println("\n=== 演習5: インターフェイスの活用 ===");
        test.ensyu5();
    }

    void ensyu1() {
        // インターフェイスの実装
        Circle circle = new Circle("円1", 10, 20, 5.0);
        
        // メソッドの呼び出し
        circle.draw();
        circle.drawBorder(); // デフォルトメソッドの呼び出し
        circle.drawCircle();
        
        System.out.println("面積: " + circle.getArea());
        System.out.println("周囲長: " + circle.getPerimeter());
    }

    void ensyu2() {
        // 抽象クラスの継承
        Rectangle rectangle = new Rectangle("矩形1", 30, 40, 10.0, 8.0);
        
        rectangle.draw();
        rectangle.drawRectangle();
        rectangle.move(50, 60);
        
        System.out.println("面積: " + rectangle.getArea());
        System.out.println("周囲長: " + rectangle.getPerimeter());
    }

    void ensyu3() {
        // 具象クラスの継承
        Square square = new Square("正方形1", 70, 80, 6.0);
        
        square.draw();
        square.drawSquare();
        square.move(90, 100);
        
        System.out.println("面積: " + square.getArea());
        System.out.println("周囲長: " + square.getPerimeter());
        System.out.println("完全な正方形か: " + square.isPerfectSquare());
    }

    void ensyu4() {
        // ポリモーフィズム - 同じ型で異なるクラスを扱う
        Shape[] shapes = {
            new Circle("円2", 0, 0, 3.0),
            new Rectangle("矩形2", 10, 10, 4.0, 6.0),
            new Square("正方形2", 20, 20, 5.0)
        };
        
        System.out.println("--- ポリモーフィズムの例 ---");
        for (Shape shape : shapes) {
            System.out.println("図形名: " + shape.getName());
            shape.draw(); // 各クラスの実装されたdrawメソッドが呼ばれる
            System.out.println("面積: " + shape.getArea());
            System.out.println("---");
        }
    }

    void ensyu5() {
        // インターフェイスの活用
        Drawable[] drawables = {
            new Circle("円3", 0, 0, 2.0),
            new Rectangle("矩形3", 5, 5, 3.0, 4.0),
            new Square("正方形3", 10, 10, 3.0)
        };
        
        System.out.println("--- インターフェイスの活用例 ---");
        for (Drawable drawable : drawables) {
            drawable.draw(); // インターフェイスのメソッドを呼び出し
            drawable.drawBorder(); // デフォルトメソッドの呼び出し
            System.out.println("デフォルトサイズ: " + Drawable.DEFAULT_SIZE);
            System.out.println("---");
        }
    }
}
