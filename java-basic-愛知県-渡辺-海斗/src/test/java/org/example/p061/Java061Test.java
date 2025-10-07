package org.example.p061;

/**
 * Java061Test - すべてのラムダ・ストリームAPI演習テストを実行するメインクラス
 */
public class Java061Test {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║   Java061 - ラムダ・ストリームAPI演習      ║");
        System.out.println("╚════════════════════════════════════════════╝");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("【1】ラムダ式演習");
        System.out.println("=".repeat(60));
        LambdaTest.main(args);
        
        System.out.println("\n\n" + "=".repeat(60));
        System.out.println("【2】ストリームAPI演習");
        System.out.println("=".repeat(60));
        StreamTest.main(args);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("すべてのラムダ・ストリームAPI演習テストが完了しました！");
        System.out.println("=".repeat(60));
        
        System.out.println("\n【演習のまとめ】");
        System.out.println("【ラムダ演習1】StringProcessor - 関数型インターフェイスの基本");
        System.out.println("【ラムダ演習2】Transformer<T,R> - ジェネリクス関数型インターフェイス");
        System.out.println("【Stream演習1】filter - 3の倍数かつ10以上を抽出");
        System.out.println("【Stream演習2】map - 先頭文字の抽出");
        System.out.println("【Stream演習3】sum - 合計計算");
        System.out.println("【Stream演習4】distinct + sorted - 重複排除とソート");
        System.out.println("【Stream演習5】flatMap - ネストしたリストの平坦化");
        System.out.println("【Stream演習6】allMatch + anyMatch - 条件判定");
        System.out.println("【Stream演習7】skip + limit - 範囲指定");
        System.out.println("【Stream演習8】groupingBy + summingDouble - カテゴリ別集計");
    }
}

