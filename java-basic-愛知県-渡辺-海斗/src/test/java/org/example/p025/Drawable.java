package test.java.org.example.p025;

/**
 * 描画可能なオブジェクトのインターフェイス
 * 振る舞いを定義するためのもの
 */
public interface Drawable {
    // 定数
    int DEFAULT_SIZE = 10;

    // メソッドの定義
    void draw();
    
    // デフォルトメソッド（Java 8以降）
    default void drawBorder() {
        System.out.println("境界線を描画します");
    }
}
