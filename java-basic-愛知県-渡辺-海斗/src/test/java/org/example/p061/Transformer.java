package org.example.p061;

/**
 * ジェネリクスな変換を行う関数型インターフェイス
 * ラムダ演習2で使用
 * @param <T> 入力の型
 * @param <R> 出力の型
 */
@FunctionalInterface
public interface Transformer<T, R> {
    /**
     * 入力を変換するメソッド
     * @param input 入力値
     * @return 変換後の値
     */
    R transform(T input);
}

