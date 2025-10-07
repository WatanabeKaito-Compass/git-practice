package org.example.p043;

/**
 * EnumExceptionの実用例サンプル
 * 実際の業務でどのように使用するかを示す
 */
public class EnumExceptionSample {

    /**
     * ユーザー認証を行うメソッド
     * @param username ユーザー名
     * @param password パスワード
     * @throws EnumException 認証失敗時
     */
    public static void login(String username, String password) {
        System.out.println("\n--- ログイン処理 ---");
        System.out.println("ユーザー名: " + username);
        
        // パラメータの検証
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new EnumException(ErrorCode.INVALID_REQUEST);
        }
        
        // 認証処理（ここでは簡易的にハードコード）
        if (!username.equals("admin") || !password.equals("password123")) {
            throw new EnumException(ErrorCode.AUTHENTICATION_FAIL);
        }
        
        System.out.println("✓ ログイン成功！");
    }

    /**
     * 管理者専用機能を実行するメソッド
     * @param userRole ユーザーの役割
     * @throws EnumException 権限不足時
     */
    public static void executeAdminFunction(String userRole) {
        System.out.println("\n--- 管理者機能の実行 ---");
        System.out.println("ユーザー役割: " + userRole);
        
        if (!"admin".equals(userRole)) {
            throw new EnumException(ErrorCode.NOT_PRIVILEGE);
        }
        
        System.out.println("✓ 管理者機能を実行しました");
    }

    /**
     * データベース操作を行うメソッド
     * @param operation 操作内容
     * @throws EnumException システムエラー時
     */
    public static void performDatabaseOperation(String operation) {
        System.out.println("\n--- データベース操作 ---");
        System.out.println("操作: " + operation);
        
        // システムエラーを模擬（例：データベース接続失敗）
        if ("fail".equals(operation)) {
            throw new EnumException(ErrorCode.SYSTEM_ERROR);
        }
        
        System.out.println("✓ データベース操作成功");
    }

    /**
     * リクエストパラメータの検証メソッド
     * @param email メールアドレス
     * @param age 年齢
     * @throws EnumException パラメータが不正な場合
     */
    public static void validateUserInput(String email, Integer age) {
        System.out.println("\n--- 入力検証 ---");
        
        if (email == null || !email.contains("@")) {
            throw new EnumException(ErrorCode.INVALID_REQUEST);
        }
        
        if (age == null || age < 0 || age > 150) {
            throw new EnumException(ErrorCode.INVALID_REQUEST);
        }
        
        System.out.println("✓ 入力検証成功");
    }

    /**
     * エラーハンドリングの実例
     */
    public static void handleError(ErrorCode errorCode) {
        System.out.println("\n[エラーハンドリング]");
        System.out.println("エラーコード: " + errorCode.getCode());
        System.out.println("エラーメッセージ: " + errorCode.getMessage());
        
        // エラーコードに応じた処理
        switch (errorCode) {
            case INVALID_REQUEST -> {
                System.out.println("→ 対応: リクエストパラメータを確認してください");
            }
            case AUTHENTICATION_FAIL -> {
                System.out.println("→ 対応: ユーザー名とパスワードを確認してください");
            }
            case NOT_PRIVILEGE -> {
                System.out.println("→ 対応: 管理者に権限の付与を依頼してください");
            }
            case SYSTEM_ERROR -> {
                System.out.println("→ 対応: システム管理者に連絡してください");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== EnumExceptionの実用例デモ ===");

        // ケース1: 正常なログイン
        System.out.println("\n【ケース1】正常なログイン");
        try {
            login("admin", "password123");
        } catch (EnumException e) {
            handleError(e.getErrorCode());
        }

        // ケース2: 認証失敗
        System.out.println("\n【ケース2】認証失敗");
        try {
            login("user", "wrongpassword");
        } catch (EnumException e) {
            handleError(e.getErrorCode());
        }

        // ケース3: 不正なリクエスト（空のパラメータ）
        System.out.println("\n【ケース3】不正なリクエスト");
        try {
            login("", "");
        } catch (EnumException e) {
            handleError(e.getErrorCode());
        }

        // ケース4: 権限不足
        System.out.println("\n【ケース4】権限不足");
        try {
            executeAdminFunction("user");
        } catch (EnumException e) {
            handleError(e.getErrorCode());
        }

        // ケース5: 正常な管理者機能実行
        System.out.println("\n【ケース5】正常な管理者機能実行");
        try {
            executeAdminFunction("admin");
        } catch (EnumException e) {
            handleError(e.getErrorCode());
        }

        // ケース6: システムエラー
        System.out.println("\n【ケース6】システムエラー");
        try {
            performDatabaseOperation("fail");
        } catch (EnumException e) {
            handleError(e.getErrorCode());
        }

        // ケース7: 入力検証エラー
        System.out.println("\n【ケース7】入力検証エラー");
        try {
            validateUserInput("invalid-email", 200);
        } catch (EnumException e) {
            handleError(e.getErrorCode());
        }

        // ケース8: 正常な入力検証
        System.out.println("\n【ケース8】正常な入力検証");
        try {
            validateUserInput("user@example.com", 25);
        } catch (EnumException e) {
            handleError(e.getErrorCode());
        }

        System.out.println("\n=== デモ終了 ===");
    }
}

