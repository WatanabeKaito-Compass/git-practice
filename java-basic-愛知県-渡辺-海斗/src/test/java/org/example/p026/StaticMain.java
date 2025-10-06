package org.example.p026;

/**
 * staticキーワードのデモンストレーション用Mainクラス
 */
public class StaticMain {
    public static void main(String[] args) {
        System.out.println("=== Static Sample Demo ===");
        
        // 1. staticメソッドの呼び出し - クラス名でアクセス可能（インスタンス化不要）
        System.out.println("\n1. staticメソッドの呼び出し:");
        StaticSample.staticMethod();
        StaticSample.staticMethod(); // staticフィールドが共有されることを確認
        
        // 2. staticフィールドのアクセス
        System.out.println("\n2. staticフィールドのアクセス:");
        System.out.println("Static field (getter経由): " + StaticSample.getStaticField());
        StaticSample.setStaticField(50);
        System.out.println("Static field after set: " + StaticSample.getStaticField());
        
        // 3. static定数のアクセス
        System.out.println("\n3. static定数のアクセス:");
        System.out.println("Static constant: " + StaticSample.STATIC_CONSTANT);
        
        // 4. インスタンスメソッドの呼び出し - newでインスタンス化が必要
        System.out.println("\n4. インスタンスメソッドの呼び出し:");
        StaticSample sample1 = new StaticSample();
        StaticSample sample2 = new StaticSample();
        
        sample1.instanceMethod();
        sample2.instanceMethod();
        
        System.out.println("Sample1 instance field: " + sample1.getInstanceField());
        System.out.println("Sample2 instance field: " + sample2.getInstanceField());
        System.out.println("Static field (shared): " + StaticSample.getStaticField());
        
        // 5. 非static内部クラスの使用
        System.out.println("\n5. 非static内部クラスの使用:");
        StaticSample.InnerClass inner1 = sample1.new InnerClass("Hello from sample1");
        StaticSample.InnerClass inner2 = sample2.new InnerClass("Hello from sample2");
        
        inner1.innerMethod();
        inner1.accessFields();
        
        inner2.innerMethod();
        inner2.accessFields();
        
        // 6. static内部クラスの使用
        System.out.println("\n6. static内部クラスの使用:");
        StaticSample.StaticInnerClass staticInner1 = new StaticSample.StaticInnerClass("Static inner 1");
        StaticSample.StaticInnerClass staticInner2 = new StaticSample.StaticInnerClass("Static inner 2");
        
        staticInner1.innerMethod();
        staticInner1.accessStaticField();
        
        staticInner2.innerMethod();
        staticInner2.accessStaticField();
        
        // 7. 複数のインスタンスでのstaticフィールドの共有確認
        System.out.println("\n7. staticフィールドの共有確認:");
        StaticSample sample3 = new StaticSample();
        StaticSample sample4 = new StaticSample();
        
        System.out.println("Before calling methods:");
        System.out.println("Static field: " + StaticSample.getStaticField());
        System.out.println("Sample3 instance field: " + sample3.getInstanceField());
        System.out.println("Sample4 instance field: " + sample4.getInstanceField());
        
        sample3.instanceMethod();
        sample4.instanceMethod();
        
        System.out.println("After calling methods:");
        System.out.println("Static field (shared by all instances): " + StaticSample.getStaticField());
        System.out.println("Sample3 instance field: " + sample3.getInstanceField());
        System.out.println("Sample4 instance field: " + sample4.getInstanceField());
        
        System.out.println("\n=== Demo Complete ===");
    }
}
