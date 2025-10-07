package org.example.p042;

/**
 * 列挙型の使用例
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Day（曜日）の例 ===");
        // 曜日によって処理を変える
        Day today = Day.MONDAY;
        switch (today) {
            case MONDAY -> System.out.println("It's Monday!");
            case TUESDAY -> System.out.println("It's Tuesday!");
            case WEDNESDAY -> System.out.println("It's Wednesday!");
            default -> System.out.println("It's another day!");
        }

        System.out.println("\n=== Level（レベル）の例 ===");
        // レベルコードの表示
        printLevel(Level.LOW);
        printLevel(Level.MEDIUM);
        printLevel(Level.HIGH);

        // 文字列から列挙型に変換
        System.out.println("\n文字列\"2\"からLevelに変換:");
        Level level = toLevel("2");
        System.out.println("Level: " + level + ", Code: " + level.getLevelCode());

        System.out.println("\n=== Operation（演算）の例 ===");
        // 演算の実行
        double x = 10.0;
        double y = 5.0;
        System.out.println("x = " + x + ", y = " + y);
        System.out.println("ADD: " + Operation.ADD.apply(x, y));
        System.out.println("SUBTRACT: " + Operation.SUBTRACT.apply(x, y));
        System.out.println("MULTIPLY: " + Operation.MULTIPLY.apply(x, y));
        System.out.println("DIVIDE: " + Operation.DIVIDE.apply(x, y));

        System.out.println("\n=== TrafficLight（信号）の例 ===");
        // 信号の遷移
        TrafficLight light = TrafficLight.RED;
        System.out.println("Current: " + light);
        light = light.next();
        System.out.println("Next: " + light);
        light = light.next();
        System.out.println("Next: " + light);
        light = light.next();
        System.out.println("Next: " + light);

        System.out.println("\n=== 列挙型の便利なメソッド ===");
        // values()で全ての値を取得
        System.out.println("全ての曜日:");
        for (Day day : Day.values()) {
            System.out.println("  " + day);
        }

        // valueOf()で文字列から変換
        System.out.println("\nvalueOf(\"FRIDAY\"): " + Day.valueOf("FRIDAY"));

        // name()で名前を取得
        System.out.println("Level.HIGH.name(): " + Level.HIGH.name());

        // ordinal()で序数を取得（定義順の番号、0から始まる）
        System.out.println("Day.MONDAY.ordinal(): " + Day.MONDAY.ordinal());
        System.out.println("Day.SUNDAY.ordinal(): " + Day.SUNDAY.ordinal());
    }

    // メソッドの引数や戻り値に列挙型を使う (型安全性が向上)
    public static void printLevel(Level level) {
        System.out.println("Level: " + level + ", Code: " + level.getLevelCode());
    }

    // 文字列から列挙型に変換する
    public static Level toLevel(String code) {
        return Level.of(code);
    }
}

