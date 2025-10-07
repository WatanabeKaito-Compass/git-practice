package org.example.p043;

/**
 * 列挙型のエラーコードを持つ例外クラス
 */
public class EnumException extends RuntimeException {
    
    private final ErrorCode errorCode;

    public EnumException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return errorCode.getMessage();
    }

    public String getCode() {
        return errorCode.getCode();
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}

