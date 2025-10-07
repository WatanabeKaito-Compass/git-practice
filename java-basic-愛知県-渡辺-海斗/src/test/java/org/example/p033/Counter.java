package org.example.p033;

/**
 * カウンター機能を提供するクラス
 * staticフィールドを使用してカウント数を管理する
 */
public class Counter {
    
    /**
     * カウント数を保持するstaticフィールド
     */
    private static int count = 0;
    
    /**
     * カウント数を1増加させるstaticメソッド
     */
    public static void increment() {
        count++;
        System.out.println("Current count: " + count);
    }
    
    /**
     * 現在のカウント数を取得するstaticメソッド
     * @return 現在のカウント数
     */
    public static int getCount() {
        return count;
    }
}
