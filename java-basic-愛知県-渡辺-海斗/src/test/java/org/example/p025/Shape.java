package test.java.org.example.p025;

/**
 * 図形の抽象クラス
 * インスタンス化できないクラス
 * 抽象メソッドを持つことができる
 */
public abstract class Shape implements Drawable {
    // フィールド
    private final String name;
    private final int x;
    private final int y;

    // 抽象クラスのコンストラクタはインスタンス化の際に呼び出すことはできないため
    // サブクラスでコンストラクタを定義して、superで呼び出す必要がある
    public Shape(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    // 具象メソッド
    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(int newX, int newY) {
        System.out.println(name + "を(" + newX + ", " + newY + ")に移動しました");
    }

    // 抽象メソッド - サブクラスでの実装を強制する
    public abstract double getArea();
    
    public abstract double getPerimeter();

    // インターフェイスのメソッド実装
    @Override
    public void draw() {
        System.out.println(name + "を描画します");
    }
}
