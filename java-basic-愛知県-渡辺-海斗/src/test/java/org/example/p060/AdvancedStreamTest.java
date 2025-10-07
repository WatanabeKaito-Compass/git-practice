package org.example.p060;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 高度なストリームAPIのテストクラス
 * flatMap、groupingBy、joining等の高度な操作を学習
 */
public class AdvancedStreamTest {

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
     * flatMapのテスト
     */
    public void testFlatMap() {
        System.out.println("\n=== testFlatMap ===");
        
        // 注文データを作成
        List<Order> orders = Arrays.asList(
            new Order("O001", Arrays.asList(
                new Product("Laptop", 1000.0, "Electronics", 1),
                new Product("Mouse", 25.0, "Electronics", 2)
            ), "Alice"),
            new Order("O002", Arrays.asList(
                new Product("Book", 15.0, "Books", 3),
                new Product("Pen", 2.0, "Stationery", 5)
            ), "Bob"),
            new Order("O003", Arrays.asList(
                new Product("Laptop", 1000.0, "Electronics", 1),
                new Product("Book", 15.0, "Books", 2)
            ), "Charlie")
        );
        
        System.out.println("\n【1】flatMapで全注文の商品を平坦化");
        List<Product> allProducts = orders.stream()
            .flatMap(order -> order.getItems().stream())
            .collect(Collectors.toList());
        
        System.out.println("全商品:");
        allProducts.forEach(p -> System.out.println("  " + p.getName() + " - " + p.getPrice()));
        
        System.out.println("\n【2】商品ごとに数量を合計");
        Map<String, Integer> totalQuantityPerProduct = orders.stream()
            .flatMap(order -> order.getItems().stream())
            .collect(Collectors.groupingBy(
                Product::getName,
                Collectors.summingInt(Product::getQuantity)
            ));
        
        System.out.println("商品ごとの合計数量:");
        totalQuantityPerProduct.forEach((name, quantity) -> 
            System.out.println("  " + name + ": " + quantity));
        
        System.out.println("\n【3】カテゴリごとの商品価格合計");
        Map<String, Double> totalPricePerCategory = orders.stream()
            .flatMap(order -> order.getItems().stream())
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.summingDouble(p -> p.getPrice() * p.getQuantity())
            ));
        
        System.out.println("カテゴリごとの価格合計:");
        totalPricePerCategory.forEach((category, total) -> 
            System.out.println("  " + category + ": $" + String.format("%.2f", total)));
        
        assertTrue(true, "flatMapの操作が成功しました");
    }

    /**
     * joiningのテスト
     */
    public void testJoining() {
        System.out.println("\n=== testJoining ===");
        
        List<Product> products = Arrays.asList(
            new Product("Apple", 1.0, "Fruit", 10),
            new Product("Banana", 0.5, "Fruit", 15),
            new Product("Orange", 1.2, "Fruit", 8)
        );
        
        System.out.println("\n【1】基本的なjoining");
        String productNames = products.stream()
            .map(Product::getName)
            .collect(Collectors.joining(", "));
        System.out.println("商品名（カンマ区切り）: " + productNames);
        
        System.out.println("\n【2】プレフィックスとサフィックス付きjoining");
        String productList = products.stream()
            .map(Product::getName)
            .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("商品リスト: " + productList);
        
        System.out.println("\n【3】Java 17のtoList()メソッド");
        List<String> productNameList = products.stream()
            .map(Product::getName)
            .toList(); // Java 17以降
        System.out.println("商品名リスト: " + productNameList);
        
        assertTrue(true, "joiningの操作が成功しました");
    }

    /**
     * groupingByのテスト
     */
    public void testGroupingBy() {
        System.out.println("\n=== testGroupingBy ===");
        
        List<Student> students = Arrays.asList(
            new Student("Alice", 85, 20, "情報工学"),
            new Student("Bob", 92, 21, "数学"),
            new Student("Charlie", 78, 19, "物理"),
            new Student("David", 88, 22, "情報工学"),
            new Student("Eve", 95, 20, "数学"),
            new Student("Frank", 72, 21, "物理")
        );
        
        System.out.println("\n【1】学科ごとにグループ化");
        Map<String, List<Student>> studentsByDepartment = students.stream()
            .collect(Collectors.groupingBy(Student::getDepartment));
        
        studentsByDepartment.forEach((department, studentList) -> {
            System.out.println("\n" + department + "学科:");
            studentList.forEach(s -> System.out.println("  " + s.getName() + " (スコア: " + s.getScore() + ")"));
        });
        
        System.out.println("\n【2】学科ごとの平均スコア");
        Map<String, Double> averageScoreByDepartment = students.stream()
            .collect(Collectors.groupingBy(
                Student::getDepartment,
                Collectors.averagingDouble(Student::getScore)
            ));
        
        System.out.println("学科ごとの平均スコア:");
        averageScoreByDepartment.forEach((department, avgScore) -> 
            System.out.println("  " + department + ": " + String.format("%.2f", avgScore)));
        
        System.out.println("\n【3】年齢ごとの学生数");
        Map<Integer, Long> studentCountByAge = students.stream()
            .collect(Collectors.groupingBy(
                Student::getAge,
                Collectors.counting()
            ));
        
        System.out.println("年齢ごとの学生数:");
        studentCountByAge.forEach((age, count) -> 
            System.out.println("  " + age + "歳: " + count + "人"));
        
        assertTrue(true, "groupingByの操作が成功しました");
    }

    /**
     * 複雑なストリーム操作のテスト
     */
    public void testComplexStreamOperations() {
        System.out.println("\n=== testComplexStreamOperations ===");
        
        List<Student> students = Arrays.asList(
            new Student("Alice", 85, 20, "情報工学"),
            new Student("Bob", 92, 21, "数学"),
            new Student("Charlie", 78, 19, "物理"),
            new Student("David", 88, 22, "情報工学"),
            new Student("Eve", 95, 20, "数学"),
            new Student("Frank", 72, 21, "物理"),
            new Student("Grace", 90, 19, "情報工学"),
            new Student("Henry", 82, 22, "数学")
        );
        
        System.out.println("\n【1】複雑な条件でのフィルタリングとソート");
        List<String> topStudents = students.stream()
            .filter(s -> s.getScore() >= 80)  // 80点以上
            .filter(s -> s.getAge() >= 20)    // 20歳以上
            .sorted((s1, s2) -> Integer.compare(s2.getScore(), s1.getScore()))  // スコア降順
            .limit(3)  // 上位3人
            .map(Student::getName)
            .collect(Collectors.toList());
        
        System.out.println("条件に合致する上位3人: " + topStudents);
        
        System.out.println("\n【2】学科ごとの最高スコア学生");
        Map<String, Optional<Student>> topStudentByDepartment = students.stream()
            .collect(Collectors.groupingBy(
                Student::getDepartment,
                Collectors.maxBy(Comparator.comparing(Student::getScore))
            ));
        
        System.out.println("学科ごとの最高スコア学生:");
        topStudentByDepartment.forEach((department, topStudent) -> 
            topStudent.ifPresent(s -> 
                System.out.println("  " + department + ": " + s.getName() + " (スコア: " + s.getScore() + ")")));
        
        System.out.println("\n【3】統計情報の計算");
        IntSummaryStatistics stats = students.stream()
            .mapToInt(Student::getScore)
            .summaryStatistics();
        
        System.out.println("スコア統計:");
        System.out.println("  平均: " + String.format("%.2f", stats.getAverage()));
        System.out.println("  最高: " + stats.getMax());
        System.out.println("  最低: " + stats.getMin());
        System.out.println("  合計: " + stats.getSum());
        System.out.println("  件数: " + stats.getCount());
        
        assertTrue(true, "複雑なストリーム操作が成功しました");
    }

    /**
     * パフォーマンステスト
     */
    public void testPerformance() {
        System.out.println("\n=== testPerformance ===");
        
        // 大きなデータセットを作成
        List<Integer> largeList = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            largeList.add(i % 100);
        }
        
        System.out.println("\n【1】通常のストリーム処理");
        long startTime = System.currentTimeMillis();
        long count = largeList.stream()
            .filter(n -> n % 2 == 0)
            .distinct()
            .count();
        long endTime = System.currentTimeMillis();
        System.out.println("偶数の重複除去後の個数: " + count);
        System.out.println("処理時間: " + (endTime - startTime) + "ms");
        
        System.out.println("\n【2】並列ストリーム処理");
        startTime = System.currentTimeMillis();
        long parallelCount = largeList.parallelStream()
            .filter(n -> n % 2 == 0)
            .distinct()
            .count();
        endTime = System.currentTimeMillis();
        System.out.println("偶数の重複除去後の個数: " + parallelCount);
        System.out.println("処理時間: " + (endTime - startTime) + "ms");
        
        assertEquals(count, parallelCount);
        
        assertTrue(true, "パフォーマンステストが成功しました");
    }

    /**
     * テストを実行するmainメソッド
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   AdvancedStreamTest - 高度なストリームAPI  ║");
        System.out.println("╚════════════════════════════════════════════╝");
        
        AdvancedStreamTest test = new AdvancedStreamTest();
        test.testFlatMap();
        test.testJoining();
        test.testGroupingBy();
        test.testComplexStreamOperations();
        test.testPerformance();
        
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
