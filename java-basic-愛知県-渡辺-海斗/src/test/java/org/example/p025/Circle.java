package test.java.org.example.p025;

/**
 * 円クラス - 抽象クラスShapeの実装
 */
public class Circle extends Shape {
    // フィールド
    private final double radius;

    // コンストラクタ
    public Circle(String name, int x, int y, double radius) {
        super(name, x, y); // スーパークラスのコンストラクタを呼び出す
        this.radius = radius;
    }

    // ゲッター
    public double getRadius() {
        return radius;
    }

    // 抽象メソッドの実装
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    // メソッドのオーバーライド
    @Override
    public void draw() {
        super.draw(); // スーパークラスのメソッドを呼び出す
        System.out.println("半径: " + radius + "の円を描画");
    }

    // 独自メソッド
    public void drawCircle() {
        System.out.println("○");
    }
}
