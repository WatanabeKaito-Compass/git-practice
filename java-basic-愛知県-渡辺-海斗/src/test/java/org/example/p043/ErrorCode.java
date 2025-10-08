package org.example.p043;

/**
 * エラーコードを表す列挙型
 */
public enum ErrorCode {
    INVALID_REQUEST("E001", "不正なリクエストです"),
    AUTHENTICATION_FAIL("E002", "認証に失敗しました"),
    NOT_PRIVILEGE("E003", "権限がありません"),
    SYSTEM_ERROR("E004", "システムエラーが発生しました");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

