package org.example.p031;

/**
 * 固定電話の具象クラス
 */
public class FixedPhone extends Phone {
    
    /**
     * コンストラクタ
     * @param rawNumber 電話番号
     */
    public FixedPhone(String rawNumber) {
        super(rawNumber);
    }
    
    /**
     * 電話をかけるメソッド（オーバーライド）
     * @param number 発信先の電話番号
     */
    @Override
    public void call(String number) {
        System.out.println("FixedPhone: " + getRawNumber() + "から" + number);
    }
}
