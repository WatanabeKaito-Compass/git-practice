package org.example.p031;

/**
 * 携帯電話の具象クラス
 */
public class MobilePhone extends Phone {
    
    /**
     * コンストラクタ
     * @param rawNumber 電話番号
     */
    public MobilePhone(String rawNumber) {
        super(rawNumber);
    }
    
    /**
     * 電話をかけるメソッド（オーバーライド）
     * @param number 発信先の電話番号
     */
    @Override
    public void call(String number) {
        System.out.println("MobilePhone: " + getRawNumber() + "から" + number);
    }
}
