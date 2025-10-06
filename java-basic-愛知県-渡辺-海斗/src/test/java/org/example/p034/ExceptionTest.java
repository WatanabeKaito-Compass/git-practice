package org.example.p034;

import java.io.IOException;

/**
 * 例外処理のテストクラス
 * JUnitを使わずに手動でテストを実行
 */
public class ExceptionTest {
    
    // テスト結果をカウントするstaticフィールド
    private static int testCount = 0;
    private static int passCount = 0;
    private static int failCount = 0;
    
    private ExceptionBasicSample basicSample = new ExceptionBasicSample();
    private CheckedUncheckedExceptionSample checkedUncheckedSample = new CheckedUncheckedExceptionSample();
    private CustomExceptionSample customSample = new CustomExceptionSample();
    
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
    
    private static void assertTrue(boolean condition) {
        testCount++;
        if (condition) {
            passCount++;
            System.out.println("✓ PASS: Condition is true");
        } else {
            failCount++;
            System.out.println("✗ FAIL: Condition is false");
        }
    }
    
    private static void assertFalse(boolean condition) {
        testCount++;
        if (!condition) {
            passCount++;
            System.out.println("✓ PASS: Condition is false");
        } else {
            failCount++;
            System.out.println("✗ FAIL: Condition is true");
        }
    }
    
    private static void assertDoesNotThrow(Runnable runnable) {
        testCount++;
        try {
            runnable.run();
            passCount++;
            System.out.println("✓ PASS: No exception was thrown");
        } catch (Exception e) {
            failCount++;
            System.out.println("✗ FAIL: Exception was thrown: " + e.getMessage());
        }
    }
    
    private static void assertThrows(Class<? extends Throwable> expectedException, Runnable runnable) {
        testCount++;
        try {
            runnable.run();
            failCount++;
            System.out.println("✗ FAIL: Expected " + expectedException.getSimpleName() + " but no exception was thrown");
        } catch (Throwable e) {
            if (expectedException.isInstance(e)) {
                passCount++;
                System.out.println("✓ PASS: Expected " + expectedException.getSimpleName() + " was thrown");
            } else {
                failCount++;
                System.out.println("✗ FAIL: Expected " + expectedException.getSimpleName() + " but got " + e.getClass().getSimpleName());
            }
        }
    }
    
    // チェック例外を処理するための専用メソッド
    private static void assertThrowsChecked(Class<? extends Throwable> expectedException, CheckedRunnable runnable) {
        testCount++;
        try {
            runnable.run();
            failCount++;
            System.out.println("✗ FAIL: Expected " + expectedException.getSimpleName() + " but no exception was thrown");
        } catch (Throwable e) {
            if (expectedException.isInstance(e)) {
                passCount++;
                System.out.println("✓ PASS: Expected " + expectedException.getSimpleName() + " was thrown");
            } else {
                failCount++;
                System.out.println("✗ FAIL: Expected " + expectedException.getSimpleName() + " but got " + e.getClass().getSimpleName());
            }
        }
    }
    
    // チェック例外をスローできる関数型インターフェース
    @FunctionalInterface
    private interface CheckedRunnable {
        void run() throws Exception;
    }
    
    private static void fail(String message) {
        testCount++;
        failCount++;
        System.out.println("✗ FAIL: " + message);
    }
    
    private static void assertNull(Object object) {
        testCount++;
        if (object == null) {
            passCount++;
            System.out.println("✓ PASS: Object is null");
        } else {
            failCount++;
            System.out.println("✗ FAIL: Object is not null");
        }
    }
    
    /**
     * 基本的なtry-catch文のテスト
     */
    public void testBasicTryCatch() {
        // 例外が発生してもプログラムが終了しないことを確認
        assertDoesNotThrow(() -> {
            basicSample.basicTryCatchExample();
        });
    }
    
    /**
     * 複数の例外キャッチのテスト
     */
    public void testMultipleCatch() {
        assertDoesNotThrow(() -> {
            basicSample.multipleCatchExample();
        });
    }
    
    /**
     * 配列範囲外例外のテスト
     */
    public void testArrayIndexOutOfBounds() {
        assertDoesNotThrow(() -> {
            basicSample.arrayIndexOutOfBoundsExample();
        });
    }
    
    /**
     * 例外が発生しない場合のテスト
     */
    public void testNoException() {
        assertDoesNotThrow(() -> {
            basicSample.noExceptionExample();
        });
    }
    
    /**
     * 検査例外のテスト（throws句）
     */
    public void testCheckedExceptionWithThrows() {
        // IOExceptionがスローされることを確認
        assertThrowsChecked(IOException.class, () -> {
            checkedUncheckedSample.checkedExceptionExample();
        });
    }
    
    /**
     * 検査例外のテスト（try-catch）
     */
    public void testCheckedExceptionWithTryCatch() {
        assertDoesNotThrow(() -> {
            checkedUncheckedSample.checkedExceptionWithTryCatch();
        });
    }
    
    /**
     * 非検査例外のテスト
     */
    public void testUncheckedException() {
        // ArithmeticExceptionがスローされることを確認
        assertThrows(ArithmeticException.class, () -> {
            checkedUncheckedSample.uncheckedExceptionExample();
        });
    }
    
    /**
     * 非検査例外のテスト（try-catch）
     */
    public void testUncheckedExceptionWithTryCatch() {
        assertDoesNotThrow(() -> {
            checkedUncheckedSample.uncheckedExceptionWithTryCatch();
        });
    }
    
    /**
     * NumberFormatExceptionのテスト
     */
    public void testNumberFormatException() {
        assertDoesNotThrow(() -> {
            checkedUncheckedSample.parseIntExceptionExample();
        });
    }
    
    /**
     * ファイル作成例外のテスト
     */
    public void testCreateFileException() {
        assertDoesNotThrow(() -> {
            checkedUncheckedSample.createFileExceptionExample();
        });
    }
    
    /**
     * 独自検査例外のテスト
     */
    public void testCustomCheckedException() {
        // ApplicationExceptionがスローされることを確認
        assertThrowsChecked(ApplicationException.class, () -> {
            customSample.checkValueWithCheckedException(-5);
        });
        
        // 正常な値の場合は例外がスローされないことを確認
        assertDoesNotThrow(() -> {
            try {
                customSample.checkValueWithCheckedException(5);
            } catch (ApplicationException e) {
                throw new RuntimeException(e);
            }
        });
    }
    
    /**
     * 独自非検査例外のテスト
     */
    public void testCustomUncheckedException() {
        // ApplicationRuntimeExceptionがスローされることを確認
        assertThrows(ApplicationRuntimeException.class, () -> {
            customSample.checkValueWithUncheckedException(-5);
        });
        
        // 正常な値の場合は例外がスローされないことを確認
        assertDoesNotThrow(() -> {
            customSample.checkValueWithUncheckedException(5);
        });
    }
    
    /**
     * 検査例外のハンドリングテスト
     */
    public void testHandleCheckedException() {
        assertDoesNotThrow(() -> {
            customSample.handleCheckedException();
        });
    }
    
    /**
     * 非検査例外のハンドリングテスト
     */
    public void testHandleUncheckedException() {
        assertDoesNotThrow(() -> {
            customSample.handleUncheckedException();
        });
    }
    
    /**
     * 複数例外のハンドリングテスト
     */
    public void testHandleMultipleExceptions() {
        assertDoesNotThrow(() -> {
            customSample.handleMultipleExceptions();
        });
    }
    
    /**
     * 例外メッセージのテスト
     */
    public void testExceptionMessage() {
        try {
            customSample.checkValueWithCheckedException(-10);
            fail("例外がスローされるべきでした");
        } catch (ApplicationException e) {
            assertEquals("値が負です: -10", e.getMessage());
        }
    }
    
    /**
     * 例外の原因のテスト
     */
    public void testExceptionCause() {
        try {
            customSample.checkValueWithUncheckedException(-15);
            fail("例外がスローされるべきでした");
        } catch (ApplicationRuntimeException e) {
            assertEquals("値が負です: -15", e.getMessage());
            assertNull(e.getCause()); // 原因となる例外は設定されていない
        }
    }
    
    /**
     * テストを実行するmainメソッド
     */
    public static void main(String[] args) {
        System.out.println("=== 例外処理テスト開始 ===");
        
        ExceptionTest test = new ExceptionTest();
        
        // 基本例外処理のテスト
        System.out.println("\n--- 基本例外処理テスト ---");
        test.testBasicTryCatch();
        test.testMultipleCatch();
        test.testArrayIndexOutOfBounds();
        test.testNoException();
        
        // 検査例外・非検査例外のテスト
        System.out.println("\n--- 検査例外・非検査例外テスト ---");
        test.testCheckedExceptionWithThrows();
        test.testCheckedExceptionWithTryCatch();
        test.testUncheckedException();
        test.testUncheckedExceptionWithTryCatch();
        test.testNumberFormatException();
        test.testCreateFileException();
        
        // 独自例外のテスト
        System.out.println("\n--- 独自例外テスト ---");
        test.testCustomCheckedException();
        test.testCustomUncheckedException();
        test.testHandleCheckedException();
        test.testHandleUncheckedException();
        test.testHandleMultipleExceptions();
        test.testExceptionMessage();
        test.testExceptionCause();
        
        System.out.println("\n=== テスト結果 ===");
        System.out.println("総テスト数: " + testCount);
        System.out.println("成功: " + passCount);
        System.out.println("失敗: " + failCount);
        
        if (failCount == 0) {
            System.out.println("🎉 すべてのテストが成功しました！");
        } else {
            System.out.println("❌ " + failCount + "個のテストが失敗しました。");
        }
    }
}
