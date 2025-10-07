package org.example.p052;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * FileReaderとFileWriterを使ったファイル操作のテスト
 * 文字ストリームを使用してファイルの読み書きを行う
 */
public class FileReaderWriterTest {

    // テスト結果をカウントするstaticフィールド
    private static int testCount = 0;
    private static int passCount = 0;
    private static int failCount = 0;

    // テスト用のアサーションメソッド
    private static void assertEquals(Object expected, Object actual) {
        testCount++;
        if (expected.equals(actual)) {
            passCount++;
            System.out.println("✓ PASS: Expected \"" + expected + "\", got \"" + actual + "\"");
        } else {
            failCount++;
            System.out.println("✗ FAIL: Expected \"" + expected + "\", but got \"" + actual + "\"");
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
     * BufferedWriterを使用してファイルに書き込むテスト
     */
    public void testBufferedWriter() {
        System.out.println("\n=== testBufferedWriter ===");
        
        String fileName = "test_output.txt";
        String content = "Hello, World!\nこんにちは、世界！\nJava File API Test";
        
        try {
            System.out.println("ファイルに書き込みます: " + fileName);
            
            // BufferedWriterを使用してファイルに書き込む
            // try-with-resources文を使用して、リソースを自動的に閉じる
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
                bw.write(content);
                System.out.println("書き込み内容:");
                System.out.println(content);
            }
            
            // ファイルが作成されたか確認
            File file = new File(fileName);
            assertTrue(file.exists(), "ファイルが作成されました");
            
        } catch (IOException e) {
            failCount++;
            System.out.println("✗ FAIL: IOException - " + e.getMessage());
            testCount++;
        }
    }

    /**
     * BufferedReaderを使用してファイルを読み込むテスト
     */
    public void testBufferedReader() {
        System.out.println("\n=== testBufferedReader ===");
        
        String fileName = "test_output.txt";
        
        try {
            System.out.println("ファイルを読み込みます: " + fileName);
            
            // BufferedReaderを使用してファイルを読み込む
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                System.out.println("\n読み込み結果:");
                System.out.println("---");
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
                System.out.println("---");
            }
            
            assertTrue(true, "ファイルの読み込みに成功しました");
            
        } catch (IOException e) {
            failCount++;
            System.out.println("✗ FAIL: IOException - " + e.getMessage());
            testCount++;
        }
    }

    /**
     * 1行ずつ読み書きするテスト
     */
    public void testLineByLine() {
        System.out.println("\n=== testLineByLine ===");
        
        String fileName = "test_lines.txt";
        
        try {
            // 複数行のデータを書き込む
            System.out.println("複数行をファイルに書き込みます: " + fileName);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
                bw.write("Line 1: First line");
                bw.newLine(); // 改行
                bw.write("Line 2: Second line");
                bw.newLine();
                bw.write("Line 3: Third line");
                bw.newLine();
            }
            
            // 行数をカウントしながら読み込む
            System.out.println("\n1行ずつ読み込みます:");
            int lineCount = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    lineCount++;
                    System.out.println("  " + lineCount + ": " + line);
                }
            }
            
            assertEquals(3, lineCount);
            
        } catch (IOException e) {
            failCount++;
            System.out.println("✗ FAIL: IOException - " + e.getMessage());
            testCount++;
        }
    }

    /**
     * ファイルに追記するテスト
     */
    public void testAppendToFile() {
        System.out.println("\n=== testAppendToFile ===");
        
        String fileName = "test_append.txt";
        
        try {
            // 最初の書き込み
            System.out.println("最初の内容を書き込みます: " + fileName);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
                bw.write("First content");
            }
            
            // 追記（第2引数にtrueを指定）
            System.out.println("内容を追記します");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
                bw.newLine();
                bw.write("Appended content");
            }
            
            // 内容を確認
            System.out.println("\nファイルの内容:");
            System.out.println("---");
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
            System.out.println("---");
            
            assertTrue(true, "追記に成功しました");
            
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
        String[] files = {"test_output.txt", "test_lines.txt", "test_append.txt"};
        
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
        System.out.println("=== FileReaderWriterTestを開始します ===");
        
        FileReaderWriterTest test = new FileReaderWriterTest();
        test.testBufferedWriter();
        test.testBufferedReader();
        test.testLineByLine();
        test.testAppendToFile();
        
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

