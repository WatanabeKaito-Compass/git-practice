package org.example.p060;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * ラムダ式のテストクラス
 * ラムダ式の基本文法とメソッド参照を学習
 */
public class LambdaTest {

    // テスト結果をカウントするstaticフィールド
    private static int testCount = 0;
    private static int passCount = 0;
    private static int failCount = 0;

    // テスト用のアサーションメソッド
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

    /**
     * ラムダ式の基本文法テスト
     */
    public void testBasicLambda() {
        System.out.println("\n=== testBasicLambda ===");
        
        // テスト用の学生リストを作成
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 85, 20, "情報工学"));
        students.add(new Student("Bob", 92, 21, "数学"));
        students.add(new Student("Charlie", 78, 19, "物理"));
        students.add(new Student("David", 88, 22, "情報工学"));
        
        System.out.println("\n【1】ラムダ式でforEachを使用");
        System.out.println("学生名を出力:");
        students.forEach(student -> System.out.println("  - " + student.getName()));
        
        System.out.println("\n【2】ラムダ式でsortを使用");
        System.out.println("名前でソート前:");
        students.forEach(s -> System.out.println("  " + s.getName()));
        
        // 名前でソート（ラムダ式）
        students.sort((s1, s2) -> s1.getName().compareTo(s2.getName()));
        
        System.out.println("名前でソート後:");
        students.forEach(s -> System.out.println("  " + s.getName()));
        
        // スコアでソート（降順）
        students.sort((s1, s2) -> Integer.compare(s2.getScore(), s1.getScore()));
        
        System.out.println("\nスコアでソート後（降順）:");
        students.forEach(s -> System.out.println("  " + s.getName() + ": " + s.getScore()));
        
        assertTrue(true, "ラムダ式の基本操作が成功しました");
    }

    /**
     * メソッド参照のテスト
     */
    public void testMethodReference() {
        System.out.println("\n=== testMethodReference ===");
        
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        
        System.out.println("\n【1】メソッド参照でforEachを使用");
        System.out.println("従来のラムダ式:");
        names.forEach(name -> System.out.println(name));
        
        System.out.println("\nメソッド参照:");
        names.forEach(System.out::println);
        
        System.out.println("\n【2】Stringのメソッド参照");
        List<String> upperNames = new ArrayList<>();
        names.forEach(name -> upperNames.add(name.toUpperCase()));
        
        System.out.println("大文字変換（ラムダ式）:");
        upperNames.forEach(System.out::println);
        
        // メソッド参照を使った変換
        List<String> upperNames2 = new ArrayList<>();
        names.forEach(upperNames2::add);
        upperNames2.replaceAll(String::toUpperCase);
        
        System.out.println("\n大文字変換（メソッド参照）:");
        upperNames2.forEach(System.out::println);
        
        assertTrue(true, "メソッド参照の操作が成功しました");
    }

    /**
     * 関数型インターフェイスのテスト
     */
    public void testFunctionalInterfaces() {
        System.out.println("\n=== testFunctionalInterfaces ===");
        
        List<Student> students = Arrays.asList(
            new Student("Alice", 85, 20, "情報工学"),
            new Student("Bob", 92, 21, "数学"),
            new Student("Charlie", 78, 19, "物理"),
            new Student("David", 88, 22, "情報工学")
        );
        
        System.out.println("\n【1】Consumerインターフェイス（引数1つ、戻り値なし）");
        Consumer<Student> printStudent = student -> 
            System.out.println("  " + student.getName() + " (スコア: " + student.getScore() + ")");
        
        System.out.println("全学生の情報:");
        students.forEach(printStudent);
        
        System.out.println("\n【2】Predicateインターフェイス（引数1つ、boolean戻り値）");
        Predicate<Student> highScoreFilter = student -> student.getScore() >= 85;
        
        System.out.println("高スコア学生（85点以上）:");
        students.stream()
            .filter(highScoreFilter)
            .forEach(printStudent);
        
        System.out.println("\n【3】Comparatorインターフェイス（引数2つ、int戻り値）");
        students.sort((s1, s2) -> {
            // まずスコアで比較、同じなら名前で比較
            int scoreCompare = Integer.compare(s2.getScore(), s1.getScore());
            return scoreCompare != 0 ? scoreCompare : s1.getName().compareTo(s2.getName());
        });
        
        System.out.println("スコア降順、同点なら名前昇順でソート:");
        students.forEach(printStudent);
        
        assertTrue(true, "関数型インターフェイスの操作が成功しました");
    }

    /**
     * 従来の匿名クラスとの比較テスト
     */
    public void testAnonymousClassComparison() {
        System.out.println("\n=== testAnonymousClassComparison ===");
        
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        
        System.out.println("\n【1】従来の匿名クラス");
        names.forEach(new Consumer<String>() {
            @Override
            public void accept(String name) {
                System.out.println("  Anonymous: " + name);
            }
        });
        
        System.out.println("\n【2】ラムダ式");
        names.forEach(name -> System.out.println("  Lambda: " + name));
        
        System.out.println("\n【3】メソッド参照");
        names.forEach(System.out::println);
        
        // ソートの比較
        List<String> namesCopy1 = new ArrayList<>(names);
        List<String> namesCopy2 = new ArrayList<>(names);
        
        // 従来の匿名クラス
        namesCopy1.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        
        // ラムダ式
        namesCopy2.sort((s1, s2) -> s1.compareTo(s2));
        
        System.out.println("\nソート結果（匿名クラス）: " + namesCopy1);
        System.out.println("ソート結果（ラムダ式）: " + namesCopy2);
        
        assertEquals(namesCopy1, namesCopy2);
        
        assertTrue(true, "匿名クラスとラムダ式の比較が成功しました");
    }

    /**
     * テストを実行するmainメソッド
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   LambdaTest - ラムダ式テスト              ║");
        System.out.println("╚════════════════════════════════════════════╝");
        
        LambdaTest test = new LambdaTest();
        test.testBasicLambda();
        test.testMethodReference();
        test.testFunctionalInterfaces();
        test.testAnonymousClassComparison();
        
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
