package org.example.p051;

import java.util.ArrayList;
import java.util.List;

/**
 * Listのコレクション演習テストクラス
 * JUnitを使わずに手動でテストを実行
 */
public class ListExerciseTest {

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

    private static void assertFalse(boolean condition) {
        testCount++;
        if (!condition) {
            passCount++;
            System.out.println("✓ PASS: Condition is false");
        } else {
            failCount++;
            System.out.println("✗ FAIL: Expected false but got true");
        }
    }

    /**
     * Listの基本操作をテストするメソッド
     */
    public void listExerciseTest() {
        System.out.println("\n=== listExerciseTest ===");
        
        // List<String>を作成し、5つの要素を追加
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Grape");
        fruits.add("Pineapple");
        
        System.out.println("\n初期リスト:");
        System.out.println("  " + fruits);
        
        // 拡張for文を使って、リストの要素を1つずつ出力
        System.out.println("\n拡張for文でリストの要素を出力:");
        for (String fruit : fruits) {
            System.out.println("  - " + fruit);
        }
        
        // removeメソッドを使って、要素が"Banana"のものを削除
        System.out.println("\n\"Banana\"を削除します...");
        fruits.remove("Banana");
        System.out.println("削除後のリスト: " + fruits);
        
        // containsメソッドを使って、要素に"Banana"が含まれているかを検証
        System.out.println("\n\"Banana\"が含まれているか検証:");
        assertFalse(fruits.contains("Banana"));
        
        // sizeメソッドを使って、リストの要素数を検証(4であること)
        System.out.println("\nリストの要素数を検証:");
        assertEquals(4, fruits.size());
    }

    /**
     * テストを実行するmainメソッド
     */
    public static void main(String[] args) {
        System.out.println("=== ListExerciseTestを開始します ===");
        
        ListExerciseTest test = new ListExerciseTest();
        test.listExerciseTest();
        
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

