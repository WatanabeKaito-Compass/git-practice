package org.example.p061;

/**
 * ジェネリクスを使った処理を行うクラス
 * ラムダ演習2で使用
 */
public class GenericProcessor {
    
    /**
     * Transformerを使って値を変換する
     * @param <T> 入力の型
     * @param <R> 出力の型
     * @param input 入力値
     * @param transformer Transformerインターフェイスの実装
     * @return 変換後の値
     */
    public static <T, R> R process(T input, Transformer<T, R> transformer) {
        return transformer.transform(input);
    }
}

