package org.example.p031;

/**
 * 電話の抽象クラス
 */
public abstract class Phone {
    private final String rawNumber;
    
    /**
     * コンストラクタ
     * @param rawNumber 電話番号
     */
    protected Phone(String rawNumber) {
        this.rawNumber = rawNumber;
    }
    
    /**
     * 電話をかける抽象メソッド
     * @param number 発信先の電話番号
     */
    public abstract void call(String number);
    
    /**
     * rawNumberの値を返す
     * @return rawNumber
     */
    public String getRawNumber() {
        return rawNumber;
    }
}
