package org.example.p043;

/**
 * EnumExceptionのテストクラス
 * JUnitを使わずに手動でテストを実行
 */
public class EnumExceptionTest {

    // テスト結果をカウントするstaticフィールド
    private static int testCount = 0;
    private static int passCount = 0;
    private static int failCount = 0;

    // テスト用のアサーションメソッド
    private static void assertEquals(Object expected, Object actual) {
        testCount++;
        if (expected.equals(actual)) {
            passCount++;
            System.out.println("✓ PASS: Expected " + expected + ", got " + actual);
        } else {
            failCount++;
            System.out.println("✗ FAIL: Expected " + expected + ", but got " + actual);
        }
    }

    /**
     * EnumExceptionのテスト
     * 各エラーコードに対してEnumExceptionをthrowし、catchブロックでcodeとmessageを出力する
     */
    public void testEnumException() {
        System.out.println("\n=== testEnumException ===");
        
        // INVALID_REQUESTのテスト
        System.out.println("\n[Test 1] INVALID_REQUEST");
        try {
            throw new EnumException(ErrorCode.INVALID_REQUEST);
        } catch (EnumException e) {
            System.out.println("例外をキャッチしました:");
            System.out.println("  Code: " + e.getCode());
            System.out.println("  Message: " + e.getMessage());
            assertEquals("E001", e.getCode());
            assertEquals("不正なリクエストです", e.getMessage());
        }

        // AUTHENTICATION_FAILのテスト
        System.out.println("\n[Test 2] AUTHENTICATION_FAIL");
        try {
            throw new EnumException(ErrorCode.AUTHENTICATION_FAIL);
        } catch (EnumException e) {
            System.out.println("例外をキャッチしました:");
            System.out.println("  Code: " + e.getCode());
            System.out.println("  Message: " + e.getMessage());
            assertEquals("E002", e.getCode());
            assertEquals("認証に失敗しました", e.getMessage());
        }

        // NOT_PRIVILEGEのテスト
        System.out.println("\n[Test 3] NOT_PRIVILEGE");
        try {
            throw new EnumException(ErrorCode.NOT_PRIVILEGE);
        } catch (EnumException e) {
            System.out.println("例外をキャッチしました:");
            System.out.println("  Code: " + e.getCode());
            System.out.println("  Message: " + e.getMessage());
            assertEquals("E003", e.getCode());
            assertEquals("権限がありません", e.getMessage());
        }

        // SYSTEM_ERRORのテスト
        System.out.println("\n[Test 4] SYSTEM_ERROR");
        try {
            throw new EnumException(ErrorCode.SYSTEM_ERROR);
        } catch (EnumException e) {
            System.out.println("例外をキャッチしました:");
            System.out.println("  Code: " + e.getCode());
            System.out.println("  Message: " + e.getMessage());
            assertEquals("E004", e.getCode());
            assertEquals("システムエラーが発生しました", e.getMessage());
        }
    }

    /**
     * ErrorCodeの列挙型のテスト
     */
    public void testErrorCode() {
        System.out.println("\n=== testErrorCode ===");
        
        // 各エラーコードの値を確認
        System.out.println("\nErrorCode一覧:");
        for (ErrorCode errorCode : ErrorCode.values()) {
            System.out.println("  " + errorCode + ": [" + errorCode.getCode() + "] " + errorCode.getMessage());
        }
        
        // 個別のテスト
        assertEquals("E001", ErrorCode.INVALID_REQUEST.getCode());
        assertEquals("不正なリクエストです", ErrorCode.INVALID_REQUEST.getMessage());
        
        assertEquals("E002", ErrorCode.AUTHENTICATION_FAIL.getCode());
        assertEquals("認証に失敗しました", ErrorCode.AUTHENTICATION_FAIL.getMessage());
        
        assertEquals("E003", ErrorCode.NOT_PRIVILEGE.getCode());
        assertEquals("権限がありません", ErrorCode.NOT_PRIVILEGE.getMessage());
        
        assertEquals("E004", ErrorCode.SYSTEM_ERROR.getCode());
        assertEquals("システムエラーが発生しました", ErrorCode.SYSTEM_ERROR.getMessage());
    }

    /**
     * 実用例：業務処理シミュレーション
     */
    public void testBusinessLogic() {
        System.out.println("\n=== testBusinessLogic (業務処理シミュレーション) ===");
        
        // ケース1: 認証処理
        System.out.println("\n[ケース1] ユーザー認証処理");
        try {
            authenticateUser("invalid_user", "wrong_password");
        } catch (EnumException e) {
            System.out.println("エラー発生: [" + e.getCode() + "] " + e.getMessage());
            assertEquals(ErrorCode.AUTHENTICATION_FAIL, e.getErrorCode());
        }
        
        // ケース2: 権限チェック
        System.out.println("\n[ケース2] 管理者権限チェック");
        try {
            checkAdminPrivilege("normal_user");
        } catch (EnumException e) {
            System.out.println("エラー発生: [" + e.getCode() + "] " + e.getMessage());
            assertEquals(ErrorCode.NOT_PRIVILEGE, e.getErrorCode());
        }
        
        // ケース3: リクエスト検証
        System.out.println("\n[ケース3] リクエストパラメータ検証");
        try {
            validateRequest(null);
        } catch (EnumException e) {
            System.out.println("エラー発生: [" + e.getCode() + "] " + e.getMessage());
            assertEquals(ErrorCode.INVALID_REQUEST, e.getErrorCode());
        }
    }

    // 模擬業務メソッド
    private void authenticateUser(String username, String password) {
        // 認証失敗を模擬
        if (!"admin".equals(username) || !"password123".equals(password)) {
            throw new EnumException(ErrorCode.AUTHENTICATION_FAIL);
        }
    }

    private void checkAdminPrivilege(String userRole) {
        // 権限不足を模擬
        if (!"admin".equals(userRole)) {
            throw new EnumException(ErrorCode.NOT_PRIVILEGE);
        }
    }

    private void validateRequest(String parameter) {
        // 不正なリクエストを模擬
        if (parameter == null || parameter.isEmpty()) {
            throw new EnumException(ErrorCode.INVALID_REQUEST);
        }
    }

    /**
     * テストを実行するmainメソッド
     */
    public static void main(String[] args) {
        System.out.println("=== EnumExceptionのテストを開始します ===");
        
        EnumExceptionTest test = new EnumExceptionTest();
        test.testEnumException();
        test.testErrorCode();
        test.testBusinessLogic();
        
        System.out.println("\n=== テスト結果 ===");
        System.out.println("総テスト数: " + testCount);
        System.out.println("成功: " + passCount);
        System.out.println("失敗: " + failCount);
        
        if (failCount == 0) {
            System.out.println("\n✓ すべてのテストに成功しました！");
        } else {
            System.out.println("\n✗ " + failCount + "個のテストが失敗しました");
        }
    }
}

