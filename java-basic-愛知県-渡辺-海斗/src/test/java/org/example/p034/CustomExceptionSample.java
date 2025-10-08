package org.example.p034;

/**
 * 独自例外を使用するサンプルクラス
 */
public class CustomExceptionSample {
    
    /**
     * 検査例外を使用するメソッド
     * @param value チェックする値
     * @throws ApplicationException 値が負の場合にスローされる
     */
    public void checkValueWithCheckedException(int value) throws ApplicationException {
        if (value < 0) {
            throw new ApplicationException("値が負です: " + value);
        }
        System.out.println("値は正常です: " + value);
    }
    
    /**
     * 非検査例外を使用するメソッド
     * @param value チェックする値
     */
    public void checkValueWithUncheckedException(int value) {
        if (value < 0) {
            throw new ApplicationRuntimeException("値が負です: " + value);
        }
        System.out.println("値は正常です: " + value);
    }
    
    /**
     * 検査例外をtry-catchで処理する例
     */
    public void handleCheckedException() {
        try {
            checkValueWithCheckedException(-5);
        } catch (ApplicationException e) {
            System.out.println("検査例外が発生しました: " + e.getMessage());
        }
    }
    
    /**
     * 非検査例外をtry-catchで処理する例
     */
    public void handleUncheckedException() {
        try {
            checkValueWithUncheckedException(-5);
        } catch (ApplicationRuntimeException e) {
            System.out.println("非検査例外が発生しました: " + e.getMessage());
        }
    }
    
    /**
     * 複数の例外を処理する例
     */
    public void handleMultipleExceptions() {
        try {
            checkValueWithCheckedException(-5);
        } catch (ApplicationException e) {
            System.out.println("アプリケーション例外: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("その他の例外: " + e.getMessage());
        }
    }
}
