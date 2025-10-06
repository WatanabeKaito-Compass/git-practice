package org.example.p031;

/**
 * Java031テストクラス
 */
public class Java031Test {
    
    public static void main(String[] args) {
        Java031Test test = new Java031Test();
        test.testPhone();
    }
    
    /**
     * 電話クラスのテストメソッド
     */
    public void testPhone() {
        System.out.println("=== 電話クラステスト ===");
        
        // MobilePhoneクラスのインスタンスを生成（型はPhone）
        Phone mobilePhone = new MobilePhone("090-1234-5678");
        mobilePhone.call("03-1234-5678");
        
        // FixedPhoneクラスのインスタンスを生成（型はPhone）
        Phone fixedPhone = new FixedPhone("052-123-4567");
        fixedPhone.call("06-1234-5678");
    }
}
