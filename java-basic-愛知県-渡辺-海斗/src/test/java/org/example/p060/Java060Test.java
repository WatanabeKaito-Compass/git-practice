package org.example.p060;

/**
 * Java060Test - すべてのラムダ・ストリームAPI演習テストを実行するメインクラス
 */
public class Java060Test {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   Java060 - ラムダ・ストリームAPI演習      ║");
        System.out.println("╚════════════════════════════════════════════╝");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("【1】ラムダ式テスト");
        System.out.println("=".repeat(60));
        LambdaTest.main(args);
        
        System.out.println("\n\n" + "=".repeat(60));
        System.out.println("【2】ストリームAPI基本テスト");
        System.out.println("=".repeat(60));
        StreamTest.main(args);
        
        System.out.println("\n\n" + "=".repeat(60));
        System.out.println("【3】高度なストリームAPIテスト");
        System.out.println("=".repeat(60));
        AdvancedStreamTest.main(args);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("すべてのラムダ・ストリームAPI演習テストが完了しました！");
        System.out.println("=".repeat(60));
        
        System.out.println("\n【学習ポイント】");
        System.out.println("1. ラムダ式: (引数) -> {処理} の形式で関数型インターフェイスを簡潔に実装");
        System.out.println("2. メソッド参照: クラス::メソッド でラムダ式をさらに簡潔に");
        System.out.println("3. ストリーム生成: コレクション.stream()、Stream.of()、Arrays.stream()等");
        System.out.println("4. 中間操作: filter、map、sorted、distinct、skip、limit等");
        System.out.println("5. 終端操作: forEach、collect、count、anyMatch、findFirst、reduce等");
        System.out.println("6. 高度な操作: flatMap、groupingBy、joining、並列ストリーム等");
        System.out.println("7. パフォーマンス: 並列ストリームで大容量データを効率的に処理");
    }
}
