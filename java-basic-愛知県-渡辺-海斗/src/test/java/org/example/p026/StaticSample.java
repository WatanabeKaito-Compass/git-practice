package org.example.p026;

/**
 * staticキーワードのサンプルクラス
 * staticはクラスに属するため、インスタンスを生成しなくてもアクセスできる
 */
public class StaticSample {
    // staticフィールド - クラス全体で共有される
    private static int staticField = 0;
    
    // static定数 - クラスに紐づく定数はstatic finalで定義する
    public static final int STATIC_CONSTANT = 100;
    
    // インスタンスフィールド - 各インスタンスごとに独立
    private int instanceField = 0;
    
    // staticイニシャライザ - クラスが最初にロードされた時に実行される
    static {
        System.out.println("static initializer - クラスがロードされました");
        staticField = 10; // staticフィールドの初期化
    }
    
    // staticメソッド - クラス名でアクセス可能
    public static void staticMethod() {
        staticField++;
        // instanceField++; // エラー: staticメソッド内ではインスタンスフィールドにアクセスできない
        System.out.println("static method called, staticField: " + staticField);
    }
    
    // staticフィールドの値を取得するstaticメソッド
    public static int getStaticField() {
        return staticField;
    }
    
    // staticフィールドの値を設定するstaticメソッド
    public static void setStaticField(int value) {
        staticField = value;
    }
    
    // インスタンスメソッド - newでインスタンス化が必要
    public void instanceMethod() {
        staticField++; // インスタンスメソッドからstaticフィールドにアクセス可能
        instanceField++;
        System.out.println("instance method called, staticField: " + staticField + ", instanceField: " + instanceField);
    }
    
    // インスタンスフィールドの値を取得するインスタンスメソッド
    public int getInstanceField() {
        return instanceField;
    }
    
    // インスタンスフィールドの値を設定するインスタンスメソッド
    public void setInstanceField(int value) {
        instanceField = value;
    }
    
    // static内部クラス - 外部クラスに依存しない場合
    // 外部クラスでのみ利用するネストクラスを定義する場合など
    public static class StaticInnerClass {
        private String message;
        
        public StaticInnerClass(String message) {
            this.message = message;
        }
        
        // static内部クラスのメソッド
        public void innerMethod() {
            System.out.println("static inner class method: " + message);
        }
        
        // static内部クラスから外部クラスのstaticフィールドにアクセス可能
        public void accessStaticField() {
            System.out.println("Accessing static field from static inner class: " + staticField);
        }
    }
    
    // 非static内部クラス - 外部クラスに依存する場合
    // 外部のクラスのフィールドを利用したい場合など
    public class InnerClass {
        private String message;
        
        public InnerClass(String message) {
            this.message = message;
        }
        
        // 非static内部クラスのメソッド
        public void innerMethod() {
            System.out.println("inner class method: " + message);
        }
        
        // 非static内部クラスから外部クラスのフィールドにアクセス可能
        public void accessFields() {
            System.out.println("Accessing static field: " + staticField);
            System.out.println("Accessing instance field: " + instanceField);
        }
    }
}
