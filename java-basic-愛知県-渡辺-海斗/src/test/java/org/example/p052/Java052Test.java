package org.example.p052;

/**
 * Java052Test - すべてのファイルAPI演習テストを実行するメインクラス
 */
public class Java052Test {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   Java052 - ファイルAPI演習テスト          ║");
        System.out.println("╚════════════════════════════════════════════╝");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("【1】FileReader/FileWriter テスト (文字ストリーム)");
        System.out.println("=".repeat(60));
        FileReaderWriterTest.main(args);
        
        System.out.println("\n\n" + "=".repeat(60));
        System.out.println("【2】FileInputStream/FileOutputStream テスト (バイトストリーム)");
        System.out.println("=".repeat(60));
        FileStreamTest.main(args);
        
        System.out.println("\n\n" + "=".repeat(60));
        System.out.println("【3】Files API テスト (Java NIO)");
        System.out.println("=".repeat(60));
        FilesAPITest.main(args);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("すべてのファイルAPI演習テストが完了しました！");
        System.out.println("=".repeat(60));
        
        System.out.println("\n【学習ポイント】");
        System.out.println("1. 文字ストリーム (Reader/Writer) はテキストファイルに適している");
        System.out.println("2. バイトストリーム (InputStream/OutputStream) はバイナリファイルに適している");
        System.out.println("3. Files API (Java NIO) は簡潔で現代的なファイル操作を提供");
        System.out.println("4. try-with-resources を使えば自動的にリソースをクローズできる");
        System.out.println("5. Path.of と File.separator でクロスプラットフォーム対応");
    }
}

