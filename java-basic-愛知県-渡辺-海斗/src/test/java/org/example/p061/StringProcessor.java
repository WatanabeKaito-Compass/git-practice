package org.example.p061;

/**
 * 文字列を処理する関数型インターフェイス
 * ラムダ演習1で使用
 */
@FunctionalInterface
public interface StringProcessor {
    /**
     * 文字列を処理するメソッド
     * @param input 入力文字列
     * @return 処理後の文字列
     */
    String process(String input);
}

