package org.example.p051;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapのコレクション演習テストクラス
 * JUnitを使わずに手動でテストを実行
 */
public class MapExerciseTest {

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
     * Mapの基本操作をテストするメソッド
     */
    public void mapExerciseTest() {
        System.out.println("\n=== mapExerciseTest ===");
        
        // Map<String, Integer>を作成し、キーと値のペアを追加
        Map<String, Integer> people = new HashMap<>();
        people.put("Alice", 30);
        people.put("Bob", 25);
        people.put("Charlie", 35);
        
        System.out.println("\n初期マップ:");
        for (Map.Entry<String, Integer> entry : people.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
        
        // putメソッドを使って、キー"David"と値40のペアを追加
        System.out.println("\nキー\"David\"と値40のペアを追加します...");
        people.put("David", 40);
        System.out.println("追加後のマップ:");
        for (Map.Entry<String, Integer> entry : people.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
        
        // getメソッドを使って、キー"Bob"の値を取得し、検証(25であること)
        System.out.println("\nキー\"Bob\"の値を取得して検証:");
        Integer bobAge = people.get("Bob");
        System.out.println("Bobの年齢: " + bobAge);
        assertEquals(25, bobAge);
        
        // removeメソッドを使って、キー"Alice"のペアを削除
        System.out.println("\nキー\"Alice\"のペアを削除します...");
        people.remove("Alice");
        System.out.println("削除後のマップ:");
        for (Map.Entry<String, Integer> entry : people.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
        
        // containsKeyメソッドを使って、キー"Alice"が含まれているかを検証
        System.out.println("\nキー\"Alice\"が含まれているか検証:");
        assertFalse(people.containsKey("Alice"));
        
        // sizeメソッドを使って、マップの要素数を検証(3であること)
        System.out.println("\nマップの要素数を検証:");
        assertEquals(3, people.size());
    }

    /**
     * テストを実行するmainメソッド
     */
    public static void main(String[] args) {
        System.out.println("=== MapExerciseTestを開始します ===");
        
        MapExerciseTest test = new MapExerciseTest();
        test.mapExerciseTest();
        
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

