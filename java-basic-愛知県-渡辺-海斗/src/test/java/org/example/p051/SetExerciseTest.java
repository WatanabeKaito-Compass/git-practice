package org.example.p051;

import java.util.HashSet;
import java.util.Set;

/**
 * Setのコレクション演習テストクラス
 * JUnitを使わずに手動でテストを実行
 */
public class SetExerciseTest {

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
     * Setの基本操作をテストするメソッド
     */
    public void setExerciseTest() {
        System.out.println("\n=== setExerciseTest ===");
        
        // Set<Integer>を作成し、5つの要素を追加
        Set<Integer> numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        
        System.out.println("\n初期セット:");
        System.out.println("  " + numbers);
        
        // addメソッドを使って、要素6を追加
        System.out.println("\n要素6を追加します...");
        numbers.add(6);
        System.out.println("追加後のセット: " + numbers);
        
        // addメソッドを使って、要素3を追加(重複するため追加されない)
        System.out.println("\n要素3を追加します（重複のため追加されない）...");
        boolean added = numbers.add(3);
        System.out.println("追加結果: " + (added ? "追加された" : "追加されなかった（重複）"));
        System.out.println("セット: " + numbers);
        
        // sizeメソッドを使って、セットの要素数を検証(6であること)
        System.out.println("\nセットの要素数を検証:");
        assertEquals(6, numbers.size());
        
        // removeメソッドを使って、要素4を削除
        System.out.println("\n要素4を削除します...");
        numbers.remove(4);
        System.out.println("削除後のセット: " + numbers);
        
        // containsメソッドを使って、要素に4が含まれているかを検証
        System.out.println("\n要素4が含まれているか検証:");
        assertFalse(numbers.contains(4));
    }

    /**
     * テストを実行するmainメソッド
     */
    public static void main(String[] args) {
        System.out.println("=== SetExerciseTestを開始します ===");
        
        SetExerciseTest test = new SetExerciseTest();
        test.setExerciseTest();
        
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

