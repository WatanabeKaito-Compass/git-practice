package org.example.p052;

import java.io.*;

/**
 * FileInputStreamとFileOutputStreamを使ったファイル操作のテスト
 * バイトストリームを使用してファイルの読み書きを行う
 */
public class FileStreamTest {

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

    private static void assertTrue(boolean condition, String message) {
        testCount++;
        if (condition) {
            passCount++;
            System.out.println("✓ PASS: " + message);
        } else {
            failCount++;
            System.out.println("✗ FAIL: " + message);
        }
    }

    /**
     * BufferedOutputStreamを使用してファイルに書き込むテスト
     */
    public void testBufferedOutputStream() {
        System.out.println("\n=== testBufferedOutputStream ===");
        
        String fileName = "test_bytes.bin";
        byte[] data = {65, 66, 67, 68, 69}; // A, B, C, D, E
        
        try {
            System.out.println("バイトデータをファイルに書き込みます: " + fileName);
            System.out.print("書き込むデータ: ");
            for (byte b : data) {
                System.out.print((char) b + " ");
            }
            System.out.println();
            
            // BufferedOutputStreamを使用してファイルに書き込む
            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName))) {
                bos.write(data);
            }
            
            File file = new File(fileName);
            assertTrue(file.exists(), "ファイルが作成されました");
            
        } catch (IOException e) {
            failCount++;
            System.out.println("✗ FAIL: IOException - " + e.getMessage());
            testCount++;
        }
    }

    /**
     * BufferedInputStreamを使用してファイルを読み込むテスト
     */
    public void testBufferedInputStream() {
        System.out.println("\n=== testBufferedInputStream ===");
        
        String fileName = "test_bytes.bin";
        
        try {
            System.out.println("バイトデータをファイルから読み込みます: " + fileName);
            
            // BufferedInputStreamを使用してファイルを読み込む
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName))) {
                int content;
                System.out.print("読み込んだデータ: ");
                while ((content = bis.read()) != -1) {
                    System.out.print((char) content + " ");
                }
                System.out.println();
            }
            
            assertTrue(true, "ファイルの読み込みに成功しました");
            
        } catch (IOException e) {
            failCount++;
            System.out.println("✗ FAIL: IOException - " + e.getMessage());
            testCount++;
        }
    }

    /**
     * バイト配列を一度に読み書きするテスト
     */
    public void testByteArray() {
        System.out.println("\n=== testByteArray ===");
        
        String fileName = "test_array.bin";
        String text = "Hello, Byte Stream!";
        byte[] writeData = text.getBytes();
        
        try {
            // バイト配列を書き込む
            System.out.println("文字列をバイト配列として書き込みます: " + fileName);
            System.out.println("書き込む文字列: " + text);
            
            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName))) {
                bos.write(writeData);
            }
            
            // バイト配列を読み込む
            System.out.println("\nバイト配列として読み込みます:");
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName))) {
                byte[] readData = bis.readAllBytes();
                String readText = new String(readData);
                System.out.println("読み込んだ文字列: " + readText);
                
                assertEquals(text, readText);
            }
            
        } catch (IOException e) {
            failCount++;
            System.out.println("✗ FAIL: IOException - " + e.getMessage());
            testCount++;
        }
    }

    /**
     * ファイルのコピーを行うテスト
     */
    public void testFileCopy() {
        System.out.println("\n=== testFileCopy ===");
        
        String sourceFile = "test_source.txt";
        String destFile = "test_destination.txt";
        String content = "This is test content for file copy.";
        
        try {
            // ソースファイルを作成
            System.out.println("ソースファイルを作成します: " + sourceFile);
            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(sourceFile))) {
                bos.write(content.getBytes());
            }
            
            // ファイルをコピー
            System.out.println("ファイルをコピーします: " + sourceFile + " -> " + destFile);
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));
                 BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile))) {
                
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = bis.read(buffer)) != -1) {
                    bos.write(buffer, 0, bytesRead);
                }
            }
            
            // コピー先のファイル内容を確認
            System.out.println("\nコピー先のファイル内容を確認:");
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(destFile))) {
                byte[] readData = bis.readAllBytes();
                String readContent = new String(readData);
                System.out.println("読み込んだ内容: " + readContent);
                
                assertEquals(content, readContent);
            }
            
        } catch (IOException e) {
            failCount++;
            System.out.println("✗ FAIL: IOException - " + e.getMessage());
            testCount++;
        }
    }

    /**
     * クリーンアップ：テストファイルを削除
     */
    public void cleanup() {
        System.out.println("\n=== cleanup ===");
        String[] files = {"test_bytes.bin", "test_array.bin", "test_source.txt", "test_destination.txt"};
        
        for (String fileName : files) {
            File file = new File(fileName);
            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("削除しました: " + fileName);
                }
            }
        }
    }

    /**
     * テストを実行するmainメソッド
     */
    public static void main(String[] args) {
        System.out.println("=== FileStreamTestを開始します ===");
        
        FileStreamTest test = new FileStreamTest();
        test.testBufferedOutputStream();
        test.testBufferedInputStream();
        test.testByteArray();
        test.testFileCopy();
        
        System.out.println("\n=== テスト結果 ===");
        System.out.println("総テスト数: " + testCount);
        System.out.println("成功: " + passCount);
        System.out.println("失敗: " + failCount);
        
        if (failCount == 0) {
            System.out.println("\n✓ すべてのテストに成功しました！");
        } else {
            System.out.println("\n✗ " + failCount + "個のテストが失敗しました");
        }
        
        // テストファイルをクリーンアップ
        test.cleanup();
    }
}

