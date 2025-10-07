package org.example.p051;

/**
 * Java051Test - すべてのコレクション演習テストを実行するメインクラス
 */
public class Java051Test {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   Java051 - コレクション演習テスト         ║");
        System.out.println("╚════════════════════════════════════════════╝");
        
        // 各テストクラスのインスタンスを作成
        ListExerciseTest listTest = new ListExerciseTest();
        SetExerciseTest setTest = new SetExerciseTest();
        MapExerciseTest mapTest = new MapExerciseTest();
        
        // すべてのテストを実行
        System.out.println("\n" + "=".repeat(50));
        System.out.println("【1】List演習テスト");
        System.out.println("=".repeat(50));
        listTest.listExerciseTest();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("【2】Set演習テスト");
        System.out.println("=".repeat(50));
        setTest.setExerciseTest();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("【3】Map演習テスト");
        System.out.println("=".repeat(50));
        mapTest.mapExerciseTest();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("すべてのコレクション演習テストが完了しました！");
        System.out.println("=".repeat(50));
    }
}

