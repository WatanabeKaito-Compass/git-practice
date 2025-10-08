package org.example.p061;

/**
 * 文字列処理を行うクラス
 * ラムダ演習1で使用
 */
public class Processor {
    
    /**
     * StringProcessorを使って文字列を処理する
     * @param input 入力文字列
     * @param processor StringProcessorインターフェイスの実装
     * @return 処理後の文字列
     */
    public static String processString(String input, StringProcessor processor) {
        return processor.process(input);
    }
}

