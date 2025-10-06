package org.example.p026;

/**
 * staticキーワードのテストクラス
 * JUnitを使わずに手動でテストを実行
 */
public class Java026Test {
    
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
    
    private static void assertNotNull(Object object) {
        testCount++;
        if (object != null) {
            passCount++;
            System.out.println("✓ PASS: Object is not null");
        } else {
            failCount++;
            System.out.println("✗ FAIL: Object is null");
        }
    }
    
    // テスト結果を表示するメソッド
    private static void printTestResults() {
        System.out.println("\n=== テスト結果 ===");
        System.out.println("総テスト数: " + testCount);
        System.out.println("成功: " + passCount);
        System.out.println("失敗: " + failCount);
        System.out.println("成功率: " + (testCount > 0 ? (passCount * 100 / testCount) + "%" : "0%"));
    }
    
    // mainメソッドでテストを実行
    public static void main(String[] args) {
        System.out.println("=== Java026Test 実行開始 ===\n");
        
        // テストカウンターをリセット
        testCount = 0;
        passCount = 0;
        failCount = 0;
    
        // 各テストメソッドを実行
        testStaticFieldAccess();
        testStaticConstant();
        testStaticMethod();
        testInstanceFields();
        testStaticInnerClass();
        testNonStaticInnerClass();
        testStaticFieldSharing();
        testStaticInitializer();
        
        // テスト結果を表示
        printTestResults();
    }
    
    // staticフィールドアクセスのテスト
    private static void testStaticFieldAccess() {
        System.out.println("--- testStaticFieldAccess ---");
        // staticフィールドの初期値をテスト
        assertEquals(10, StaticSample.getStaticField()); // staticイニシャライザで10に設定される
        
        // staticフィールドの値を変更
        StaticSample.setStaticField(20);
        assertEquals(20, StaticSample.getStaticField());
        
        // 別のインスタンスからも同じstaticフィールドにアクセスできることを確認
        StaticSample sample1 = new StaticSample();
        StaticSample sample2 = new StaticSample();
        
        sample1.instanceMethod(); // staticFieldが21になる
        assertEquals(21, StaticSample.getStaticField());
        
        sample2.instanceMethod(); // staticFieldが22になる
        assertEquals(22, StaticSample.getStaticField());
    }
    
    // static定数のテスト
    private static void testStaticConstant() {
        System.out.println("\n--- testStaticConstant ---");
        // static定数にアクセス
        assertEquals(100, StaticSample.STATIC_CONSTANT);
        
        // static定数は変更できないことを確認（コンパイルエラーになるため、コメントアウト）
        // StaticSample.STATIC_CONSTANT = 200; // コンパイルエラー
    }
    
    // staticメソッドのテスト
    private static void testStaticMethod() {
        System.out.println("\n--- testStaticMethod ---");
        // staticメソッドを呼び出し
        StaticSample.staticMethod();
        
        // staticメソッドがstaticフィールドを変更することを確認
        int beforeValue = StaticSample.getStaticField();
        StaticSample.staticMethod();
        int afterValue = StaticSample.getStaticField();
        
        assertEquals(beforeValue + 1, afterValue);
    }
    
    // インスタンスフィールドのテスト
    private static void testInstanceFields() {
        System.out.println("\n--- testInstanceFields ---");
        // インスタンスフィールドは各インスタンスで独立していることを確認
        StaticSample sample1 = new StaticSample();
        StaticSample sample2 = new StaticSample();
        
        sample1.setInstanceField(10);
        sample2.setInstanceField(20);
        
        assertEquals(10, sample1.getInstanceField());
        assertEquals(20, sample2.getInstanceField());
        
        // staticフィールドは共有されていることを確認
        int staticValue = StaticSample.getStaticField();
        sample1.instanceMethod();
        sample2.instanceMethod();
        
        assertEquals(staticValue + 2, StaticSample.getStaticField());
    }
    
    // static内部クラスのテスト
    private static void testStaticInnerClass() {
        System.out.println("\n--- testStaticInnerClass ---");
        // static内部クラスのインスタンス化（外部クラスのインスタンス不要）
        StaticSample.StaticInnerClass staticInner = new StaticSample.StaticInnerClass("Test message");
        
        // static内部クラスのメソッドをテスト
        assertNotNull(staticInner);
        
        // static内部クラスから外部クラスのstaticフィールドにアクセス可能
        staticInner.accessStaticField();
    }
    
    // 非static内部クラスのテスト
    private static void testNonStaticInnerClass() {
        System.out.println("\n--- testNonStaticInnerClass ---");
        // 非static内部クラスは外部クラスのインスタンスが必要
        StaticSample outer = new StaticSample();
        StaticSample.InnerClass inner = outer.new InnerClass("Test message");
        
        assertNotNull(inner);
        
        // 非static内部クラスから外部クラスのフィールドにアクセス可能
        inner.accessFields();
    }
    
    // staticフィールド共有のテスト
    private static void testStaticFieldSharing() {
        System.out.println("\n--- testStaticFieldSharing ---");
        // 複数のインスタンス間でstaticフィールドが共有されることを確認
        StaticSample sample1 = new StaticSample();
        StaticSample sample2 = new StaticSample();
        
        int initialStaticValue = StaticSample.getStaticField();
        
        // 両方のインスタンスでstaticフィールドを変更
        sample1.instanceMethod();
        sample2.instanceMethod();
        
        // staticフィールドが2回増加していることを確認
        assertEquals(initialStaticValue + 2, StaticSample.getStaticField());
        
        // 各インスタンスのinstanceFieldは独立していることを確認
        assertEquals(1, sample1.getInstanceField());
        assertEquals(1, sample2.getInstanceField());
    }
    
    // staticイニシャライザのテスト
    private static void testStaticInitializer() {
        System.out.println("\n--- testStaticInitializer ---");
        // staticイニシャライザが実行されていることを確認
        // staticイニシャライザでstaticFieldが10に設定される
        assertEquals(10, StaticSample.getStaticField());
    }
}
