package org.example.p034;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 検査例外と非検査例外のサンプルクラス
 */
public class CheckedUncheckedExceptionSample {
    
    /**
     * 検査例外の例
     * IOExceptionをthrows句で宣言する必要がある
     */
    public void checkedExceptionExample() throws IOException {
        // ファイルの読み込み処理（検査例外）
        FileInputStream fis = new FileInputStream("nonexistent.txt");
        fis.close();
    }
    
    /**
     * 検査例外をtry-catchで処理する例
     */
    public void checkedExceptionWithTryCatch() {
        try {
            // ファイルの読み込み処理
            FileInputStream fis = new FileInputStream("nonexistent.txt");
            fis.close();
        } catch (IOException e) {
            System.out.println("ファイル読み込みエラー: " + e.getMessage());
        }
    }
    
    /**
     * 非検査例外の例
     * RuntimeExceptionを継承した例外はコンパイル時にチェックされない
     */
    public void uncheckedExceptionExample() {
        // ArithmeticException（非検査例外）
        int result = 10 / 0;
        
        // NullPointerException（非検査例外）
        String str = null;
        str.length();
        
        // ArrayIndexOutOfBoundsException（非検査例外）
        int[] arr = new int[5];
        arr[10] = 100;
    }
    
    /**
     * 非検査例外をtry-catchで処理する例
     */
    public void uncheckedExceptionWithTryCatch() {
        try {
            // ArithmeticException
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("算術例外: " + e.getMessage());
        }
        
        try {
            // NullPointerException
            String str = null;
            str.length();
        } catch (NullPointerException e) {
            System.out.println("NullPointer例外: " + e.getMessage());
        }
        
        try {
            // ArrayIndexOutOfBoundsException
            int[] arr = new int[5];
            arr[10] = 100;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("配列範囲外例外: " + e.getMessage());
        }
    }
    
    /**
     * Integer.parseInt()の例外処理例
     */
    public void parseIntExceptionExample() {
        try {
            // 数値に変換できない文字列を渡すとNumberFormatExceptionが発生
            int number = Integer.parseInt("abc");
            System.out.println("変換結果: " + number);
        } catch (NumberFormatException e) {
            System.out.println("数値変換エラー: " + e.getMessage());
        }
    }
    
    /**
     * Files.createFile()の例外処理例
     */
    public void createFileExceptionExample() {
        try {
            // ファイル作成処理（検査例外）
            Path path = Paths.get("test.txt");
            Files.createFile(path);
            System.out.println("ファイルが作成されました");
        } catch (IOException e) {
            System.out.println("ファイル作成エラー: " + e.getMessage());
        }
    }
}
