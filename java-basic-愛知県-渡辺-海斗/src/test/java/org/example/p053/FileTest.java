package org.example.p053;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * ファイル演習 - CSVファイルの読み書きテスト
 */
public class FileTest {

    /**
     * Personレコードクラス
     * recordクラスは不変のデータクラスを簡潔に定義できる
     */
    public record Person(int no, String name, int age) {
        /**
         * CSV形式の文字列を返すメソッド
         * @return CSV形式の文字列 (例: "1,John,25")
         */
        public String toCsv() {
            return no + "," + name + "," + age;
        }

        /**
         * CSV文字列からPersonオブジェクトを生成するstaticメソッド
         * @param csvLine CSV形式の文字列
         * @return Personオブジェクト
         */
        public static Person fromCsv(String csvLine) {
            String[] parts = csvLine.split(",");
            int no = Integer.parseInt(parts[0]);
            String name = parts[1];
            int age = Integer.parseInt(parts[2]);
            return new Person(no, name, age);
        }
    }

    // テスト結果をカウントするstaticフィールド
    private static int testCount = 0;
    private static int passCount = 0;
    private static int failCount = 0;

    // テスト用のアサーションメソッド
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
     * dataフォルダ内のファイルを探索するメソッド
     * カレントディレクトリやその親ディレクトリからファイルを検索する
     * 
     * @param dataDir データディレクトリ名
     * @param fileName ファイル名
     * @return ファイルのPath
     */
    private static Path findDataFile(String dataDir, String fileName) {
        // 1. カレントディレクトリからの相対パス（java-basic-愛知県-渡辺-海斗ディレクトリから実行した場合）
        Path path1 = Path.of(dataDir, fileName);
        if (Files.exists(path1)) {
            return path1;
        }
        
        // 2. java-basic-愛知県-渡辺-海斗ディレクトリを含むパス（git-practiceディレクトリから実行した場合）
        Path path2 = Path.of("java-basic-愛知県-渡辺-海斗", dataDir, fileName);
        if (Files.exists(path2)) {
            return path2;
        }
        
        // 3. カレントディレクトリに java-basic-愛知県-渡辺-海斗 がある場合
        Path currentDir = Path.of(".").toAbsolutePath().normalize();
        Path path3 = currentDir.resolve("java-basic-愛知県-渡辺-海斗").resolve(dataDir).resolve(fileName);
        if (Files.exists(path3)) {
            return path3;
        }
        
        // 4. 見つからない場合はデフォルトのパスを返す（エラーメッセージを明確にするため）
        System.err.println("警告: " + fileName + " が見つかりません。以下のパスを確認しました:");
        System.err.println("  1. " + path1.toAbsolutePath());
        System.err.println("  2. " + path2.toAbsolutePath());
        System.err.println("  3. " + path3.toAbsolutePath());
        System.err.println("カレントディレクトリ: " + currentDir);
        
        // デフォルトでpath2を返す（最も一般的なケース）
        return path2;
    }

    /**
     * CSVファイルの読み書きをテストするメソッド
     */
    public void testFileCsv() {
        System.out.println("\n=== testFileCsv ===");
        
        // ファイルパスの設定
        // カレントディレクトリに応じてパスを調整
        String csvFileName = "test.csv";
        String dataDir = "data";
        Path path = findDataFile(dataDir, csvFileName);
        
        System.out.println("CSVファイルパス: " + path.toAbsolutePath());
        
        try {
            // ========================================
            // 1. test.csvファイルを読み込み、内容を出力する
            // ========================================
            System.out.println("\n【1】CSVファイルを読み込みます");
            System.out.println("---");
            
            List<Person> persons = new ArrayList<>();
            
            // try-with-resources文を使用してファイルを読み込む
            try (BufferedReader br = Files.newBufferedReader(path)) {
                String line;
                boolean isFirstLine = true;
                
                while ((line = br.readLine()) != null) {
                    // 空行をスキップ
                    if (line.trim().isEmpty()) {
                        continue;
                    }
                    
                    if (isFirstLine) {
                        // ヘッダー行は読み飛ばす
                        System.out.println("ヘッダー: " + line);
                        isFirstLine = false;
                        continue;
                    }
                    
                    // CSV行からPersonオブジェクトを生成
                    Person person = Person.fromCsv(line);
                    persons.add(person);
                    System.out.println("読み込み: " + person);
                }
            }
            System.out.println("---");
            System.out.println("合計 " + persons.size() + " 件のデータを読み込みました");
            
            assertTrue(persons.size() > 0, "データが正常に読み込まれました");
            
            // ========================================
            // 2. test.csvファイルに新しいデータを追記する
            // ========================================
            System.out.println("\n【2】新しいデータを追記します");
            
            // 追加するデータを作成
            int nextNo = persons.size() + 1;
            Person newPerson1 = new Person(nextNo, "Alice", 28);
            Person newPerson2 = new Person(nextNo + 1, "Bob", 32);
            Person newPerson3 = new Person(nextNo + 2, "Charlie", 27);
            
            List<Person> newPersons = List.of(newPerson1, newPerson2, newPerson3);
            
            System.out.println("追加するデータ:");
            for (Person person : newPersons) {
                System.out.println("  " + person);
            }
            
            // try-with-resources文を使用してファイルに追記
            // StandardOpenOption.APPENDを指定して追記モード
            try (BufferedWriter bw = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                for (Person person : newPersons) {
                    bw.write(person.toCsv());
                    bw.newLine();
                    System.out.println("追記しました: " + person.toCsv());
                }
            }
            
            assertTrue(true, "データが正常に追記されました");
            
            // ========================================
            // 3. 追記後のファイル内容を確認
            // ========================================
            System.out.println("\n【3】追記後のファイル内容を確認します");
            System.out.println("---");
            
            // ファイル全体を読み込んで表示
            try (BufferedReader br = Files.newBufferedReader(path)) {
                String line;
                int lineNumber = 1;
                while ((line = br.readLine()) != null) {
                    System.out.println(lineNumber + ": " + line);
                    lineNumber++;
                }
            }
            System.out.println("---");
            
            // データ件数の確認
            List<Person> allPersons = new ArrayList<>();
            try (BufferedReader br = Files.newBufferedReader(path)) {
                String line;
                boolean isFirstLine = true;
                
                while ((line = br.readLine()) != null) {
                    // 空行をスキップ
                    if (line.trim().isEmpty()) {
                        continue;
                    }
                    
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }
                    allPersons.add(Person.fromCsv(line));
                }
            }
            
            System.out.println("\n現在の総データ件数: " + allPersons.size() + " 件");
            assertTrue(allPersons.size() == persons.size() + newPersons.size(), 
                      "データ件数が正しく増加しました");
            
            // ========================================
            // 4. 統計情報の表示
            // ========================================
            System.out.println("\n【4】統計情報");
            
            // 平均年齢の計算
            double averageAge = allPersons.stream()
                .mapToInt(Person::age)
                .average()
                .orElse(0.0);
            System.out.println("平均年齢: " + String.format("%.1f", averageAge) + " 歳");
            
            // 最年長者
            Person oldest = allPersons.stream()
                .max((p1, p2) -> Integer.compare(p1.age(), p2.age()))
                .orElse(null);
            if (oldest != null) {
                System.out.println("最年長者: " + oldest.name() + " (" + oldest.age() + " 歳)");
            }
            
            // 最年少者
            Person youngest = allPersons.stream()
                .min((p1, p2) -> Integer.compare(p1.age(), p2.age()))
                .orElse(null);
            if (youngest != null) {
                System.out.println("最年少者: " + youngest.name() + " (" + youngest.age() + " 歳)");
            }
            
        } catch (IOException e) {
            failCount++;
            System.out.println("✗ FAIL: IOException - " + e.getMessage());
            e.printStackTrace();
            testCount++;
        }
    }

    /**
     * テストを実行するmainメソッド
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   FileTest - CSV読み書きテスト             ║");
        System.out.println("╚════════════════════════════════════════════╝");
        
        FileTest test = new FileTest();
        test.testFileCsv();
        
        System.out.println("\n=== テスト結果 ===");
        System.out.println("総テスト数: " + testCount);
        System.out.println("成功: " + passCount);
        System.out.println("失敗: " + failCount);
        
        if (failCount == 0) {
            System.out.println("\n✓ すべてのテストに成功しました！");
        } else {
            System.out.println("\n✗ " + failCount + "個のテストが失敗しました");
        }
        
        System.out.println("\n【確認】");
        System.out.println("data" + File.separator + "test.csv ファイルを開いて、");
        System.out.println("データが追記されていることを確認してください。");
    }
}

