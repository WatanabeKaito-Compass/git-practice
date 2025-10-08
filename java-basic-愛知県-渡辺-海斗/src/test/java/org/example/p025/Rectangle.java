package test.java.org.example.p025;

/**
 * 矩形クラス - 抽象クラスShapeの実装
 */
public class Rectangle extends Shape {
    // フィールド
    private final double width;
    private final double height;

    // コンストラクタ
    public Rectangle(String name, int x, int y, double width, double height) {
        super(name, x, y); // スーパークラスのコンストラクタを呼び出す
        this.width = width;
        this.height = height;
    }

    // ゲッター
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    // 抽象メソッドの実装
    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    // メソッドのオーバーライド
    @Override
    public void draw() {
        super.draw(); // スーパークラスのメソッドを呼び出す
        System.out.println("幅: " + width + ", 高さ: " + height + "の矩形を描画");
    }

    // 独自メソッド
    public void drawRectangle() {
        System.out.println("□");
    }
}
