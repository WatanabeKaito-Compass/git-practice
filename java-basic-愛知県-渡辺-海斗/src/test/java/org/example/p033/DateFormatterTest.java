package org.example.p033;

import java.time.LocalDate;

/**
 * DateFormatterクラスのテストクラス
 * JUnitを使わずに手動でテストを実行
 */
public class DateFormatterTest {
    
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
     * formatDateメソッドのテスト
     * LocalDate.of(2023, 10, 1)を引数にformatDateメソッドを呼び出し、
     * "2023/10/01"が返ることを確認する
     */
    public void testFormatDate() {
        System.out.println("=== DateFormatter staticメソッドテスト ===");
        
        // テスト用の日付を作成
        LocalDate testDate = LocalDate.of(2023, 10, 1);
        
        // formatDateメソッドを呼び出し
        String result = DateFormatter.formatDate(testDate);
        
        // 期待値と実際の結果を比較
        assertEquals("2023/10/01", result);
        
        System.out.println("\n=== テスト結果 ===");
        System.out.println("総テスト数: " + testCount);
        System.out.println("成功: " + passCount);
        System.out.println("失敗: " + failCount);
    }
    
    /**
     * テストを実行するmainメソッド
     */
    public static void main(String[] args) {
        DateFormatterTest test = new DateFormatterTest();
        test.testFormatDate();
    }
}
