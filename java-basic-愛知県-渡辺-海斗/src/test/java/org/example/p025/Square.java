package test.java.org.example.p025;

/**
 * 正方形クラス - Rectangleクラスの継承
 * 具象クラスの継承の例
 */
public class Square extends Rectangle {
    // フィールド
    private final double side;

    // コンストラクタ
    public Square(String name, int x, int y, double side) {
        super(name, x, y, side, side); // スーパークラスのコンストラクタを呼び出す
        this.side = side;
    }

    // ゲッター
    public double getSide() {
        return side;
    }

    // メソッドのオーバーライド
    @Override
    public void draw() {
        super.draw(); // スーパークラスのメソッドを呼び出す
        System.out.println("一辺: " + side + "の正方形を描画");
    }

    // 独自メソッド
    public void drawSquare() {
        System.out.println("■");
    }

    // 正方形特有のメソッド
    public boolean isPerfectSquare() {
        return getWidth() == getHeight() && getWidth() == side;
    }
}
