package org.example.p061;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ストリームAPIの演習テストクラス
 * JUnitを使わずに手動でテストを実行
 */
public class StreamTest {

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
     * testStream1：3の倍数かつ10以上の要素を抽出
     */
    public void testStream1() {
        System.out.println("\n=== testStream1 ===");
        System.out.println("3の倍数かつ10以上の要素を抽出");
        
        // 1から20までの整数を格納
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            numbers.add(i);
        }
        
        System.out.println("元のリスト: " + numbers);
        
        // 3の倍数かつ10以上の要素を抽出
        List<Integer> filtered = numbers.stream()
            .filter(n -> n % 3 == 0)  // 3の倍数
            .filter(n -> n >= 10)     // 10以上
            .collect(Collectors.toList());
        
        System.out.println("抽出結果: " + filtered);
        assertEquals(Arrays.asList(12, 15, 18), filtered);
    }

    /**
     * testStream2：各文字列の先頭文字を抽出
     */
    public void testStream2() {
        System.out.println("\n=== testStream2 ===");
        System.out.println("各文字列の先頭文字を抽出");
        
        // 任意の文字列を5つ格納
        List<String> strings = Arrays.asList("Apple", "Banana", "Cherry", "Date", "Elderberry");
        
        System.out.println("元のリスト: " + strings);
        
        // 各文字列の先頭文字を抽出
        List<Character> firstChars = strings.stream()
            .map(s -> s.charAt(0))
            .collect(Collectors.toList());
        
        System.out.println("先頭文字リスト: " + firstChars);
        assertEquals(Arrays.asList('A', 'B', 'C', 'D', 'E'), firstChars);
    }

    /**
     * testStream3：数値の合計を計算
     */
    public void testStream3() {
        System.out.println("\n=== testStream3 ===");
        System.out.println("数値の合計を計算");
        
        // 任意の数値を5つ格納
        List<Double> numbers = Arrays.asList(10.5, 20.3, 15.7, 8.2, 12.8);
        
        System.out.println("元のリスト: " + numbers);
        
        // 合計を計算
        double sum = numbers.stream()
            .mapToDouble(Double::doubleValue)
            .sum();
        
        System.out.println("合計: " + sum);
        assertEquals(67.5, sum);
    }

    /**
     * testStream4：重複を排除してソート
     */
    public void testStream4() {
        System.out.println("\n=== testStream4 ===");
        System.out.println("重複を排除してソート");
        
        // 重複を含む文字列を5つ格納
        List<String> strings = Arrays.asList("Cherry", "Apple", "Banana", "Apple", "Date");
        
        System.out.println("元のリスト（重複あり）: " + strings);
        
        // 重複を排除してソート
        List<String> result = strings.stream()
            .distinct()           // 重複を排除
            .sorted()             // ソート
            .collect(Collectors.toList());
        
        System.out.println("重複排除・ソート後: " + result);
        assertEquals(Arrays.asList("Apple", "Banana", "Cherry", "Date"), result);
    }

    /**
     * testStream5：flatMapでネストしたリストを平坦化
     */
    public void testStream5() {
        System.out.println("\n=== testStream5 ===");
        System.out.println("flatMapでネストしたリストを平坦化");
        
        // ネストしたリスト構造を格納
        List<List<Integer>> nestedList = Arrays.asList(
            Arrays.asList(1, 2, 3, 4, 5),
            Arrays.asList(6, 7, 8, 9, 10),
            Arrays.asList(11, 12, 13, 14, 15)
        );
        
        System.out.println("ネストしたリスト:");
        nestedList.forEach(list -> System.out.println("  " + list));
        
        // flatMapで平坦化
        List<Integer> flattened = nestedList.stream()
            .flatMap(List::stream)
            .collect(Collectors.toList());
        
        System.out.println("平坦化後: " + flattened);
        assertEquals(15, flattened.size());
        assertTrue(flattened.get(0) == 1, "最初の要素は1");
        assertTrue(flattened.get(14) == 15, "最後の要素は15");
    }

    /**
     * testStream6：allMatchとanyMatchを使った判定
     */
    public void testStream6() {
        System.out.println("\n=== testStream6 ===");
        System.out.println("allMatchとanyMatchを使った判定");
        
        // 任意の文字列を5つ格納
        List<String> strings = Arrays.asList("Apple", "Banana", "Cherry", "Date", "Fig123");
        
        System.out.println("元のリスト: " + strings);
        
        // 全ての要素が大文字で始まるか
        boolean allStartWithUpperCase = strings.stream()
            .allMatch(s -> Character.isUpperCase(s.charAt(0)));
        
        System.out.println("\n全ての要素が大文字で始まるか: " + allStartWithUpperCase);
        assertTrue(allStartWithUpperCase, "全て大文字で始まる");
        
        // いずれかの要素に数字を含むか
        boolean anyContainsDigit = strings.stream()
            .anyMatch(s -> s.chars().anyMatch(Character::isDigit));
        
        System.out.println("いずれかの要素に数字を含むか: " + anyContainsDigit);
        assertTrue(anyContainsDigit, "数字を含む要素がある");
    }

    /**
     * testStream7：skipとlimitを使った範囲指定
     */
    public void testStream7() {
        System.out.println("\n=== testStream7 ===");
        System.out.println("skipとlimitを使った範囲指定");
        
        // 任意の文字列を5つ格納
        List<String> strings = Arrays.asList("First", "Second", "Third", "Fourth", "Fifth");
        
        System.out.println("元のリスト: " + strings);
        
        // 先頭2件をスキップし、次の3件を取得
        List<String> result = strings.stream()
            .skip(2)    // 先頭2件をスキップ
            .limit(3)   // 次の3件を取得
            .collect(Collectors.toList());
        
        System.out.println("先頭2件スキップ、次の3件: " + result);
        assertEquals(Arrays.asList("Third", "Fourth", "Fifth"), result);
    }

    /**
     * testStream8：製品の価格フィルタとカテゴリ別売上集計
     */
    public void testStream8() {
        System.out.println("\n=== testStream8 ===");
        System.out.println("製品の価格フィルタとカテゴリ別売上集計");
        
        // 製品情報を5つ格納
        List<Product> products = Arrays.asList(
            new Product("Laptop", "electronics", 1200.0, 5),
            new Product("Smartphone", "electronics", 800.0, 10),
            new Product("T-Shirt", "clothing", 25.0, 50),
            new Product("Jeans", "clothing", 60.0, 30),
            new Product("Apple", "food", 2.0, 100)
        );
        
        System.out.println("全製品:");
        products.forEach(System.out::println);
        
        // 【1】価格が1000円以上の製品を抽出
        System.out.println("\n【1】価格が1000円以上の製品:");
        List<Product> expensiveProducts = products.stream()
            .filter(p -> p.getPrice() >= 1000.0)
            .collect(Collectors.toList());
        
        expensiveProducts.forEach(System.out::println);
        assertEquals(1, expensiveProducts.size());
        
        // 【2】カテゴリごとの売上金額の合計
        System.out.println("\n【2】カテゴリごとの売上金額の合計:");
        Map<String, Double> salesByCategory = products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.summingDouble(p -> p.getPrice() * p.getQuantity())
            ));
        
        salesByCategory.forEach((category, totalSales) -> 
            System.out.println("  " + category + ": $" + String.format("%.2f", totalSales)));
        
        // 検証
        assertEquals(3, salesByCategory.size());
        assertEquals(14000.0, salesByCategory.get("electronics"));  // 1200*5 + 800*10
        assertEquals(3050.0, salesByCategory.get("clothing"));      // 25*50 + 60*30
        assertEquals(200.0, salesByCategory.get("food"));           // 2*100
    }

    /**
     * テストを実行するmainメソッド
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   StreamTest - ストリームAPI演習テスト     ║");
        System.out.println("╚════════════════════════════════════════════╝");
        
        StreamTest test = new StreamTest();
        test.testStream1();
        test.testStream2();
        test.testStream3();
        test.testStream4();
        test.testStream5();
        test.testStream6();
        test.testStream7();
        test.testStream8();
        
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

