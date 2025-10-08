package org.example.p052;

import java.io.*;
import java.nio.file.*;

/**
 * FilesクラスとPathクラスを使ったファイル操作のテスト
 * Java NIOを使用した簡潔なファイル操作
 */
public class FilesAPITest {

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
     * Files.writeStringとFiles.readStringを使ったテスト
     */
    public void testFilesReadWriteString() {
        System.out.println("\n=== testFilesReadWriteString ===");
        
        String fileName = "test_files_string.txt";
        String content = "Hello, Files API!\nこれはJava NIOのテストです。\nシンプルで便利！";
        
        try {
            Path path = Path.of(fileName);
            
            // ファイルに書き込む
            System.out.println("ファイルに書き込みます: " + fileName);
            Files.writeString(path, content);
            System.out.println("書き込み内容:");
            System.out.println("---");
            System.out.println(content);
            System.out.println("---");
            
            // ファイルを読み込む
            System.out.println("\nファイルを読み込みます:");
            String readContent = Files.readString(path);
            System.out.println("読み込み内容:");
            System.out.println("---");
            System.out.println(readContent);
            System.out.println("---");
            
            assertEquals(content, readContent);
            
        } catch (IOException e) {
            failCount++;
            System.out.println("✗ FAIL: IOException - " + e.getMessage());
            testCount++;
        }
    }

    /**
     * Files.writeStringで追記するテスト
     */
    public void testFilesAppend() {
        System.out.println("\n=== testFilesAppend ===");
        
        String fileName = "test_files_append.txt";
        String firstContent = "First line";
        String appendContent = "\nSecond line";
        
        try {
            Path path = Path.of(fileName);
            
            // 最初の書き込み
            System.out.println("最初の内容を書き込みます: " + fileName);
            Files.writeString(path, firstContent);
            
            // 追記（StandardOpenOption.APPENDを指定）
            System.out.println("内容を追記します");
            Files.writeString(path, appendContent, StandardOpenOption.APPEND);
            
            // 内容を確認
            String readContent = Files.readString(path);
            System.out.println("\nファイルの内容:");
            System.out.println("---");
            System.out.println(readContent);
            System.out.println("---");
            
            assertEquals(firstContent + appendContent, readContent);
            
        } catch (IOException e) {
            failCount++;
            System.out.println("✗ FAIL: IOException - " + e.getMessage());
            testCount++;
        }
    }

    /**
     * Files.copyを使ったファイルコピーのテスト
     */
    public void testFilesCopy() {
        System.out.println("\n=== testFilesCopy ===");
        
        String sourceFile = "test_files_source.txt";
        String destFile = "test_files_destination.txt";
        String content = "This is content for copy test.";
        
        try {
            Path sourcePath = Path.of(sourceFile);
            Path destPath = Path.of(destFile);
            
            // ソースファイルを作成
            System.out.println("ソースファイルを作成します: " + sourceFile);
            Files.writeString(sourcePath, content);
            
            // ファイルをコピー
            System.out.println("ファイルをコピーします: " + sourceFile + " -> " + destFile);
            Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
            
            // コピー先のファイル内容を確認
            String destContent = Files.readString(destPath);
            System.out.println("\nコピー先のファイル内容:");
            System.out.println(destContent);
            
            assertEquals(content, destContent);
            
        } catch (IOException e) {
            failCount++;
            System.out.println("✗ FAIL: IOException - " + e.getMessage());
            testCount++;
        }
    }

    /**
     * Files.newBufferedReaderとFiles.newBufferedWriterを使ったテスト
     */
    public void testFilesBufferedReaderWriter() {
        System.out.println("\n=== testFilesBufferedReaderWriter ===");
        
        String fileName = "test_files_buffered.txt";
        
        try {
            Path path = Path.of(fileName);
            
            // BufferedWriterを使用して書き込む
            System.out.println("BufferedWriterで書き込みます: " + fileName);
            try (BufferedWriter bw = Files.newBufferedWriter(path)) {
                bw.write("Line 1: Files.newBufferedWriter");
                bw.newLine();
                bw.write("Line 2: とても便利");
                bw.newLine();
                bw.write("Line 3: try-with-resourcesで自動クローズ");
            }
            
            // BufferedReaderを使用して読み込む
            System.out.println("\nBufferedReaderで読み込みます:");
            System.out.println("---");
            try (BufferedReader br = Files.newBufferedReader(path)) {
                String line;
                int lineNumber = 1;
                while ((line = br.readLine()) != null) {
                    System.out.println(lineNumber + ": " + line);
                    lineNumber++;
                }
            }
            System.out.println("---");
            
            assertTrue(true, "BufferedReaderとBufferedWriterの使用に成功しました");
            
        } catch (IOException e) {
            failCount++;
            System.out.println("✗ FAIL: IOException - " + e.getMessage());
            testCount++;
        }
    }

    /**
     * PathとFile.separatorを使ったパス操作のテスト
     */
    public void testPathOperations() {
        System.out.println("\n=== testPathOperations ===");
        
        try {
            // File.separatorを使ったパスの構築
            String dir = "testdir";
            String fileName = "testfile.txt";
            String pathString = "." + File.separator + dir + File.separator + fileName;
            
            System.out.println("パス区切り文字: " + File.separator);
            System.out.println("構築したパス: " + pathString);
            
            // Path.ofを使ったパスの構築（推奨）
            Path path = Path.of(".", dir, fileName);
            System.out.println("Path.ofで構築: " + path);
            
            // PathをFileに変換
            File file = path.toFile();
            System.out.println("Fileに変換: " + file);
            
            // ファイル名や親ディレクトリの取得
            System.out.println("\nパス情報:");
            System.out.println("  ファイル名: " + path.getFileName());
            System.out.println("  親ディレクトリ: " + path.getParent());
            System.out.println("  絶対パス: " + path.toAbsolutePath());
            
            assertTrue(true, "パス操作に成功しました");
            
        } catch (Exception e) {
            failCount++;
            System.out.println("✗ FAIL: Exception - " + e.getMessage());
            testCount++;
        }
    }

    /**
     * ディレクトリ作成とファイル存在確認のテスト
     */
    public void testDirectoryOperations() {
        System.out.println("\n=== testDirectoryOperations ===");
        
        String dirName = "test_directory";
        String fileName = "file_in_directory.txt";
        
        try {
            Path dirPath = Path.of(dirName);
            
            // ディレクトリが存在しない場合は作成
            if (!Files.exists(dirPath)) {
                System.out.println("ディレクトリを作成します: " + dirName);
                Files.createDirectory(dirPath);
            }
            
            assertTrue(Files.exists(dirPath), "ディレクトリが存在します");
            assertTrue(Files.isDirectory(dirPath), "パスはディレクトリです");
            
            // ディレクトリ内にファイルを作成
            Path filePath = Path.of(dirName, fileName);
            System.out.println("\nディレクトリ内にファイルを作成します: " + filePath);
            Files.writeString(filePath, "File in directory test");
            
            assertTrue(Files.exists(filePath), "ファイルが存在します");
            assertTrue(Files.isRegularFile(filePath), "パスは通常ファイルです");
            
            // クリーンアップ
            System.out.println("\n作成したファイルとディレクトリを削除します");
            Files.deleteIfExists(filePath);
            Files.deleteIfExists(dirPath);
            
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
        String[] files = {
            "test_files_string.txt",
            "test_files_append.txt",
            "test_files_source.txt",
            "test_files_destination.txt",
            "test_files_buffered.txt"
        };
        
        for (String fileName : files) {
            try {
                Path path = Path.of(fileName);
                if (Files.exists(path)) {
                    Files.delete(path);
                    System.out.println("削除しました: " + fileName);
                }
            } catch (IOException e) {
                System.out.println("削除に失敗しました: " + fileName);
            }
        }
    }

    /**
     * テストを実行するmainメソッド
     */
    public static void main(String[] args) {
        System.out.println("=== FilesAPITestを開始します ===");
        
        FilesAPITest test = new FilesAPITest();
        test.testFilesReadWriteString();
        test.testFilesAppend();
        test.testFilesCopy();
        test.testFilesBufferedReaderWriter();
        test.testPathOperations();
        test.testDirectoryOperations();
        
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

