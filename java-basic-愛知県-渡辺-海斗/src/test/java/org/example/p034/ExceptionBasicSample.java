package org.example.p034;

/**
 * 例外処理の基本サンプルクラス
 */
public class ExceptionBasicSample {
    
    /**
     * 基本的なtry-catch文の例
     * 0で割り算を行い、ArithmeticExceptionをキャッチする
     */
    public void basicTryCatchExample() {
        try {
            // 例外が発生する可能性がある処理
            System.out.println("処理を開始します");
            int result = 10 / 0; // 例: 10を0で割るため、例外が発生
        } catch (ArithmeticException e) {
            // 例外が発生した場合の処理
            System.out.println("例外が発生しました: " + e.getMessage());
        } finally {
            // 必ず実行される処理
            System.out.println("処理を終了します");
        }
    }
    
    /**
     * 複数の例外をキャッチする例
     */
    public void multipleCatchExample() {
        try {
            String str = null;
            int length = str.length(); // NullPointerExceptionが発生
        } catch (NullPointerException e) {
            System.out.println("NullPointerExceptionが発生しました: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticExceptionが発生しました: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("その他の例外が発生しました: " + e.getMessage());
        }
    }
    
    /**
     * 配列の範囲外アクセスの例
     */
    public void arrayIndexOutOfBoundsExample() {
        try {
            int[] arr = new int[5];
            arr[10] = 100; // ArrayIndexOutOfBoundsExceptionが発生
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("配列の範囲外アクセスが発生しました: " + e.getMessage());
        }
    }
    
    /**
     * 例外が発生しない場合の例
     */
    public void noExceptionExample() {
        try {
            System.out.println("正常な処理を開始します");
            int result = 10 / 2; // 正常に実行される
            System.out.println("計算結果: " + result);
        } catch (ArithmeticException e) {
            System.out.println("例外が発生しました: " + e.getMessage());
        } finally {
            System.out.println("処理を終了します");
        }
    }
}
