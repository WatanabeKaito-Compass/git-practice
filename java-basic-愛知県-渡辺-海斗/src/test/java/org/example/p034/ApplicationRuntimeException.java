package org.example.p034;

/**
 * アプリケーション独自の例外クラス（非検査例外）
 * RuntimeExceptionを継承しているため、コンパイル時にチェックされない
 */
public class ApplicationRuntimeException extends RuntimeException {
    
    /**
     * デフォルトコンストラクタ
     */
    public ApplicationRuntimeException() {
        super();
    }
    
    /**
     * メッセージ付きコンストラクタ
     * @param message 例外メッセージ
     */
    public ApplicationRuntimeException(String message) {
        super(message);
    }
    
    /**
     * メッセージと原因付きコンストラクタ
     * @param message 例外メッセージ
     * @param cause 原因となった例外
     */
    public ApplicationRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * 原因付きコンストラクタ
     * @param cause 原因となった例外
     */
    public ApplicationRuntimeException(Throwable cause) {
        super(cause);
    }
}
