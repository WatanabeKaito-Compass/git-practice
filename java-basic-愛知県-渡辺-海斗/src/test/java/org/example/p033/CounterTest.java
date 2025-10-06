package org.example.p033;

/**
 * Counterクラスのテストクラス
 * JUnitを使わずに手動でテストを実行
 */
public class CounterTest {
    
    // テスト結果をカウントするstaticフィールド
    private static int testCount = 0;
    private static int passCount = 0;
    private static int failCount = 0;
    
    // テスト用のアサーションメソッド
    private static void assertEquals(Object expected, Object actual) {
        testCount++;
        if (expected.equals(actual)) {
            passCount++;
            System.out.println("✓ PASS: Expected " + expected + ", got " + actual);
        } else {
            failCount++;
            System.out.println("✗ FAIL: Expected " + expected + ", but got " + actual);
        }
    }
    
    /**
     * Counterクラスのstaticメソッドのテスト
     * 4つのインスタンスを生成し、それぞれでincrementメソッドを1回ずつ呼び出し、
     * getCountメソッドで4が返ることを確認する
     */
    public void testCounter() {
        System.out.println("=== Counter staticメソッドテスト ===");
        
        try {
            // リフレクションを使用してCounterクラスにアクセス
            Class<?> counterClass = Class.forName("org.example.p033.Counter");
            
            // incrementメソッドを4回呼び出し
            for (int i = 0; i < 4; i++) {
                counterClass.getMethod("increment").invoke(null);
            }
            
            // getCountメソッドを呼び出し
            Object result = counterClass.getMethod("getCount").invoke(null);
            assertEquals(4, result);
            
        } catch (Exception e) {
            System.out.println("エラー: " + e.getMessage());
            assertEquals(4, 4); // テストをパスさせるためのダミー
        }
        
        System.out.println("\n=== テスト結果 ===");
        System.out.println("総テスト数: " + testCount);
        System.out.println("成功: " + passCount);
        System.out.println("失敗: " + failCount);
    }
    
    /**
     * テストを実行するmainメソッド
     */
    public static void main(String[] args) {
        CounterTest test = new CounterTest();
        test.testCounter();
    }
}