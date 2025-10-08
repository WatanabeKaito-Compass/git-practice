package org.example.p041;

import java.util.Random;

/**
 * 例外処理演習用のテストクラス
 */
public class ExceptionTest {

    public static void main(String[] args) {
        ExceptionTest test = new ExceptionTest();
        System.out.println("=== 例外処理演習 ===");
        test.testException();
    }

    /**
     * ArithmeticExceptionをテストするメソッド
     * Randomクラスを使って50%の確率でArithmeticExceptionを発生させる
     */
    public void testException() {
        Random random = new Random();
        
        try {
            // 50%の確率でArithmeticExceptionを発生させる
            if (random.nextBoolean()) {
                // 0で割り算してArithmeticExceptionを発生させる
                int result = 10 / 0;
                System.out.println("例外は発生しませんでした。結果: " + result);
            } else {
                // 正常な計算を実行
                int result = 10 / 2;
                System.out.println("正常な計算が実行されました。結果: " + result);
            }
        } catch (ArithmeticException e) {
            // ArithmeticExceptionをキャッチしてメッセージを出力
            System.out.println("ArithmeticExceptionが発生しました: " + e.getMessage());
            System.out.println("例外の詳細: " + e.toString());
        }
    }
}
