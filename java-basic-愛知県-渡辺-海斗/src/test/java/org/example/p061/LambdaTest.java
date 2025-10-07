package org.example.p061;

/**
 * ラムダ式の演習テストクラス
 * JUnitを使わずに手動でテストを実行
 */
public class LambdaTest {

    // テスト結果をカウントするstaticフィールド
    private static int testCount = 0;
    private static int passCount = 0;
    private static int failCount = 0;

    // テスト用のアサーションメソッド
    private static void assertEquals(Object expected, Object actual) {
        testCount++;
        if (expected.equals(actual)) {
            passCount++;
            System.out.println("✓ PASS: Expected \"" + expected + "\", got \"" + actual + "\"");
        } else {
            failCount++;
            System.out.println("✗ FAIL: Expected \"" + expected + "\", but got \"" + actual + "\"");
        }
    }

    private static void assertTrue(boolean condition, String message) {
        testCount++;
        if (condition) {
            passCount++;
            System.out.println("✓ PASS: " + message);
        } else {
            failCount++;
            System.out.println("✗ FAIL: " + message);
        }
    }

    /**
     * ラムダ演習1：StringProcessorを使った文字列処理
     * ラムダ式を使って文字列を大文字に変換する
     */
    public void testLambda1() {
        System.out.println("\n=== testLambda1 ===");
        System.out.println("ラムダ式を使って文字列を大文字に変換");
        
        String input = "hello world";
        System.out.println("入力文字列: " + input);
        
        // ラムダ式を使ってStringProcessorを実装
        String result = Processor.processString(input, s -> s.toUpperCase());
        
        System.out.println("変換結果: " + result);
        assertEquals("HELLO WORLD", result);
        
        // 追加のテストケース
        System.out.println("\n追加テスト:");
        
        // 文字列の逆順
        String reversed = Processor.processString("Java Lambda", s -> new StringBuilder(s).reverse().toString());
        System.out.println("逆順: " + reversed);
        assertEquals("adbmaL avaJ", reversed);
        
        // 文字列の長さを返す
        String lengthStr = Processor.processString("Stream API", s -> String.valueOf(s.length()));
        System.out.println("文字列長: " + lengthStr);
        assertEquals("10", lengthStr);
    }

    /**
     * ラムダ演習2：Transformer<T,R>を使ったジェネリクス変換
     * ラムダ式を使って整数を文字列に変換する
     */
    public void testLambda2() {
        System.out.println("\n=== testLambda2 ===");
        System.out.println("ラムダ式を使って整数を文字列に変換");
        
        Integer input = 12345;
        System.out.println("入力整数: " + input);
        
        // ラムダ式を使ってTransformerを実装（整数→文字列）
        String result = GenericProcessor.process(input, n -> "Number: " + n);
        
        System.out.println("変換結果: " + result);
        assertEquals("Number: 12345", result);
        
        // 追加のテストケース
        System.out.println("\n追加テスト:");
        
        // 整数→2倍の整数
        Integer doubled = GenericProcessor.process(10, n -> n * 2);
        System.out.println("2倍: " + doubled);
        assertEquals(20, doubled);
        
        // 文字列→文字数
        Integer length = GenericProcessor.process("Hello", s -> s.length());
        System.out.println("文字数: " + length);
        assertEquals(5, length);
        
        // Double→Boolean（10.0より大きいか）
        Boolean isGreaterThan10 = GenericProcessor.process(15.5, d -> d > 10.0);
        System.out.println("10.0より大きいか: " + isGreaterThan10);
        assertTrue(isGreaterThan10, "15.5は10.0より大きい");
    }

    /**
     * テストを実行するmainメソッド
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   LambdaTest - ラムダ式演習テスト          ║");
        System.out.println("╚════════════════════════════════════════════╝");
        
        LambdaTest test = new LambdaTest();
        test.testLambda1();
        test.testLambda2();
        
        System.out.println("\n=== テスト結果 ===");
        System.out.println("総テスト数: " + testCount);
        System.out.println("成功: " + passCount);
        System.out.println("失敗: " + failCount);
        
        if (failCount == 0) {
            System.out.println("\n✓ すべてのテストに成功しました！");
        } else {
            System.out.println("\n✗ " + failCount + "個のテストが失敗しました");
        }
    }
}

