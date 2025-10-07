package org.example.p042;

/**
 * 列挙型のテストクラス
 * JUnitを使わずに手動でテストを実行
 */
public class EnumTest {

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

    private static void assertTrue(boolean condition) {
        testCount++;
        if (condition) {
            passCount++;
            System.out.println("✓ PASS: Condition is true");
        } else {
            failCount++;
            System.out.println("✗ FAIL: Expected true but got false");
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

    public void testDay() {
        System.out.println("\n=== testDay ===");
        // 曜日によって処理を変える
        Day today = Day.MONDAY;
        String result = switch (today) {
            case MONDAY -> "It's Monday!";
            case TUESDAY -> "It's Tuesday!";
            case WEDNESDAY -> "It's Wednesday!";
            default -> "It's another day!";
        };
        
        assertEquals("It's Monday!", result);
        
        // 別の曜日もテスト
        Day sunday = Day.SUNDAY;
        String sundayResult = switch (sunday) {
            case MONDAY -> "It's Monday!";
            case TUESDAY -> "It's Tuesday!";
            case WEDNESDAY -> "It's Wednesday!";
            default -> "It's another day!";
        };
        
        assertEquals("It's another day!", sundayResult);
    }

    public void testLevel() {
        System.out.println("\n=== testLevel ===");
        // レベルコードの取得
        assertEquals(1, Level.LOW.getLevelCode());
        assertEquals(2, Level.MEDIUM.getLevelCode());
        assertEquals(3, Level.HIGH.getLevelCode());
        
        // 文字列からLevelに変換
        Level level = Level.of("2");
        assertEquals(Level.MEDIUM, level);
        assertEquals(2, level.getLevelCode());
    }

    public void testLevelInvalidCode() {
        System.out.println("\n=== testLevelInvalidCode ===");
        // 無効なレベルコードの場合、例外が発生する
        try {
            Level.of("99");
            failCount++;
            System.out.println("✗ FAIL: Expected IllegalArgumentException but no exception was thrown");
        } catch (IllegalArgumentException e) {
            passCount++;
            System.out.println("✓ PASS: IllegalArgumentException was thrown as expected");
        }
        testCount++;
    }

    public void testOperation() {
        System.out.println("\n=== testOperation ===");
        // 加算
        double addResult = Operation.ADD.apply(10, 5);
        assertEquals(15.0, addResult);
        
        // 減算
        double subtractResult = Operation.SUBTRACT.apply(10, 5);
        assertEquals(5.0, subtractResult);
        
        // 乗算
        double multiplyResult = Operation.MULTIPLY.apply(10, 5);
        assertEquals(50.0, multiplyResult);
        
        // 除算
        double divideResult = Operation.DIVIDE.apply(10, 5);
        assertEquals(2.0, divideResult);
    }

    public void testTrafficLight() {
        System.out.println("\n=== testTrafficLight ===");
        // 信号の遷移
        TrafficLight red = TrafficLight.RED;
        assertEquals(TrafficLight.GREEN, red.next());
        
        TrafficLight green = TrafficLight.GREEN;
        assertEquals(TrafficLight.YELLOW, green.next());
        
        TrafficLight yellow = TrafficLight.YELLOW;
        assertEquals(TrafficLight.RED, yellow.next());
        
        // 連続した遷移のテスト
        TrafficLight current = TrafficLight.RED;
        current = current.next(); // GREEN
        current = current.next(); // YELLOW
        current = current.next(); // RED
        assertEquals(TrafficLight.RED, current);
    }

    public void testEnumValues() {
        System.out.println("\n=== testEnumValues ===");
        // values()メソッドで全ての列挙子を取得できる
        Day[] days = Day.values();
        assertEquals(7, days.length);
        assertEquals(Day.MONDAY, days[0]);
        assertEquals(Day.SUNDAY, days[6]);
    }

    public void testEnumValueOf() {
        System.out.println("\n=== testEnumValueOf ===");
        // valueOf()メソッドで文字列から列挙子を取得できる
        Day monday = Day.valueOf("MONDAY");
        assertEquals(Day.MONDAY, monday);
        
        Level high = Level.valueOf("HIGH");
        assertEquals(Level.HIGH, high);
    }

    public void testEnumComparison() {
        System.out.println("\n=== testEnumComparison ===");
        // 列挙型は==で比較できる
        Day day1 = Day.MONDAY;
        Day day2 = Day.MONDAY;
        Day day3 = Day.TUESDAY;
        
        assertTrue(day1 == day2);
        assertFalse(day1 == day3);
        
        // equalsでも比較できる
        assertTrue(day1.equals(day2));
        assertFalse(day1.equals(day3));
    }

    /**
     * メソッドの引数に列挙型を使う例（型安全性が向上）
     */
    private String printLevel(Level level) {
        return "Level code: " + level.getLevelCode();
    }

    public void testPrintLevel() {
        System.out.println("\n=== testPrintLevel ===");
        String result = printLevel(Level.HIGH);
        assertEquals("Level code: 3", result);
        
        // コンパイル時に型チェックされるため、誤った値を渡すことができない
        // printLevel("HIGH"); // コンパイルエラー
        // printLevel(3);      // コンパイルエラー
    }

    /**
     * テストを実行するmainメソッド
     */
    public static void main(String[] args) {
        System.out.println("=== 列挙型のテストを開始します ===");
        
        EnumTest test = new EnumTest();
        test.testDay();
        test.testLevel();
        test.testLevelInvalidCode();
        test.testOperation();
        test.testTrafficLight();
        test.testEnumValues();
        test.testEnumValueOf();
        test.testEnumComparison();
        test.testPrintLevel();
        
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

