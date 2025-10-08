package org.example.p034;

/**
 * アプリケーション独自の例外クラス（検査例外）
 * Exceptionを継承しているため、コンパイル時にチェックされる
 */
public class ApplicationException extends Exception {
    
    /**
     * デフォルトコンストラクタ
     */
    public ApplicationException() {
        super();
    }
    
    /**
     * メッセージ付きコンストラクタ
     * @param message 例外メッセージ
     */
    public ApplicationException(String message) {
        super(message);
    }
    
    /**
     * メッセージと原因付きコンストラクタ
     * @param message 例外メッセージ
     * @param cause 原因となった例外
     */
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * 原因付きコンストラクタ
     * @param cause 原因となった例外
     */
    public ApplicationException(Throwable cause) {
        super(cause);
    }
}
