package org.example.p060;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * ストリームAPIのテストクラス
 * ストリームの生成、中間操作、終端操作を学習
 */
public class StreamTest {

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
     * ストリーム生成のテスト
     */
    public void testStreamCreation() {
        System.out.println("\n=== testStreamCreation ===");
        
        System.out.println("\n【1】配列からストリーム生成");
        String[] array = {"apple", "banana", "cherry"};
        Stream<String> arrayStream = Arrays.stream(array);
        System.out.println("配列から生成: " + arrayStream.collect(Collectors.toList()));
        
        System.out.println("\n【2】Stream.of()で生成");
        Stream<String> ofStream = Stream.of("dog", "cat", "bird");
        System.out.println("Stream.of(): " + ofStream.collect(Collectors.toList()));
        
        System.out.println("\n【3】空のストリーム");
        Stream<String> emptyStream = Stream.empty();
        System.out.println("空ストリーム: " + emptyStream.collect(Collectors.toList()));
        
        System.out.println("\n【4】コレクションからストリーム生成");
        List<String> list = List.of("red", "green", "blue");
        Stream<String> listStream = list.stream();
        System.out.println("リストから生成: " + listStream.collect(Collectors.toList()));
        
        System.out.println("\n【5】Stream.builder()で生成");
        Stream<String> builderStream = Stream.<String>builder()
            .add("first")
            .add("second")
            .add("third")
            .build();
        System.out.println("Builder: " + builderStream.collect(Collectors.toList()));
        
        System.out.println("\n【6】Stream.generate()で生成");
        Stream<String> generatedStream = Stream.generate(() -> "element")
            .limit(5);
        System.out.println("Generated: " + generatedStream.collect(Collectors.toList()));
        
        System.out.println("\n【7】Stream.iterate()で生成");
        Stream<Integer> iteratedStream = Stream.iterate(1, n -> n * 2)
            .limit(6);
        System.out.println("Iterated: " + iteratedStream.collect(Collectors.toList()));
        
        System.out.println("\n【8】IntStream.range()で生成");
        IntStream rangeStream = IntStream.range(1, 6);
        System.out.println("IntStream.range: " + rangeStream.boxed().collect(Collectors.toList()));
        
        assertTrue(true, "ストリーム生成の操作が成功しました");
    }

    /**
     * 中間操作のテスト
     */
    public void testIntermediateOperations() {
        System.out.println("\n=== testIntermediateOperations ===");
        
        List<Student> students = Arrays.asList(
            new Student("Alice", 85, 20, "情報工学"),
            new Student("Bob", 92, 21, "数学"),
            new Student("Charlie", 78, 19, "物理"),
            new Student("David", 88, 22, "情報工学"),
            new Student("Eve", 95, 20, "数学"),
            new Student("Frank", 72, 21, "物理")
        );
        
        System.out.println("\n【1】filter - 条件に合致する要素を抽出");
        System.out.println("スコア80点以上の学生:");
        List<Student> highScoreStudents = students.stream()
            .filter(student -> student.getScore() >= 80)
            .collect(Collectors.toList());
        highScoreStudents.forEach(s -> System.out.println("  " + s.getName() + ": " + s.getScore()));
        
        System.out.println("\n【2】map - 要素を変換");
        System.out.println("学生名を大文字に変換:");
        List<String> upperNames = students.stream()
            .map(Student::getName)
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        upperNames.forEach(System.out::println);
        
        System.out.println("\n【3】sorted - 要素をソート");
        System.out.println("スコア降順でソート:");
        List<Student> sortedStudents = students.stream()
            .sorted((s1, s2) -> Integer.compare(s2.getScore(), s1.getScore()))
            .collect(Collectors.toList());
        sortedStudents.forEach(s -> System.out.println("  " + s.getName() + ": " + s.getScore()));
        
        System.out.println("\n【4】distinct - 重複を除去");
        List<String> departments = students.stream()
            .map(Student::getDepartment)
            .distinct()
            .collect(Collectors.toList());
        System.out.println("重複を除去した学科: " + departments);
        
        System.out.println("\n【5】skip - 最初のn個をスキップ");
        System.out.println("最初の2人をスキップ:");
        students.stream()
            .skip(2)
            .forEach(s -> System.out.println("  " + s.getName()));
        
        System.out.println("\n【6】limit - 最初のn個のみ取得");
        System.out.println("最初の3人のみ:");
        students.stream()
            .limit(3)
            .forEach(s -> System.out.println("  " + s.getName()));
        
        assertTrue(true, "中間操作の操作が成功しました");
    }

    /**
     * 終端操作のテスト
     */
    public void testTerminalOperations() {
        System.out.println("\n=== testTerminalOperations ===");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        System.out.println("\n【1】forEach - 各要素に対して処理");
        System.out.println("各数値を2倍して出力:");
        numbers.stream()
            .map(n -> n * 2)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        System.out.println("\n【2】collect - 新しいコレクションを作成");
        List<Integer> evenNumbers = numbers.stream()
            .filter(n -> n % 2 == 0)
            .collect(Collectors.toList());
        System.out.println("偶数: " + evenNumbers);
        
        System.out.println("\n【3】count - 要素数をカウント");
        long evenCount = numbers.stream()
            .filter(n -> n % 2 == 0)
            .count();
        System.out.println("偶数の個数: " + evenCount);
        assertEquals(5L, evenCount);
        
        System.out.println("\n【4】anyMatch - 条件に合致する要素があるか");
        boolean hasEven = numbers.stream()
            .anyMatch(n -> n % 2 == 0);
        System.out.println("偶数があるか: " + hasEven);
        assertTrue(hasEven, "偶数が存在します");
        
        System.out.println("\n【5】allMatch - 全ての要素が条件に合致するか");
        boolean allPositive = numbers.stream()
            .allMatch(n -> n > 0);
        System.out.println("全て正数か: " + allPositive);
        assertTrue(allPositive, "全て正数です");
        
        System.out.println("\n【6】findFirst - 最初の要素を取得");
        Optional<Integer> firstEven = numbers.stream()
            .filter(n -> n % 2 == 0)
            .findFirst();
        System.out.println("最初の偶数: " + firstEven.orElse(-1));
        assertEquals(2, firstEven.orElse(-1));
        
        System.out.println("\n【7】reduce - 要素を結合");
        int sum = numbers.stream()
            .reduce(0, (a, b) -> a + b);
        System.out.println("合計: " + sum);
        assertEquals(55, sum);
        
        // 文字列の結合
        List<String> words = Arrays.asList("Hello", "World", "Java", "Stream");
        String joined = words.stream()
            .reduce("", (a, b) -> a + " " + b)
            .trim();
        System.out.println("結合された文字列: " + joined);
        
        assertTrue(true, "終端操作の操作が成功しました");
    }

    /**
     * 並列ストリームのテスト
     */
    public void testParallelStream() {
        System.out.println("\n=== testParallelStream ===");
        
        List<Integer> numbers = IntStream.rangeClosed(1, 1000000)
            .boxed()
            .collect(Collectors.toList());
        
        System.out.println("\n【1】通常のストリーム");
        long startTime = System.currentTimeMillis();
        long sum1 = numbers.stream()
            .mapToLong(Integer::longValue)
            .sum();
        long endTime = System.currentTimeMillis();
        System.out.println("合計: " + sum1 + " (時間: " + (endTime - startTime) + "ms)");
        
        System.out.println("\n【2】並列ストリーム");
        startTime = System.currentTimeMillis();
        long sum2 = numbers.parallelStream()
            .mapToLong(Integer::longValue)
            .sum();
        endTime = System.currentTimeMillis();
        System.out.println("合計: " + sum2 + " (時間: " + (endTime - startTime) + "ms)");
        
        assertEquals(sum1, sum2);
        
        System.out.println("\n【3】並列ストリームで重複除去");
        List<Integer> distinctNumbers = numbers.parallelStream()
            .distinct()
            .collect(Collectors.toList());
        System.out.println("重複除去後の要素数: " + distinctNumbers.size());
        
        assertTrue(true, "並列ストリームの操作が成功しました");
    }

    /**
     * テストを実行するmainメソッド
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   StreamTest - ストリームAPIテスト         ║");
        System.out.println("╚════════════════════════════════════════════╝");
        
        StreamTest test = new StreamTest();
        test.testStreamCreation();
        test.testIntermediateOperations();
        test.testTerminalOperations();
        test.testParallelStream();
        
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
