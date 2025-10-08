package org.example.p033;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 日付をフォーマットするためのクラス
 */
public class DateFormatter {
    
    /**
     * 日付フォーマット用のstatic定数フィールド
     * DateTimeFormatterはスレッドセーフなため、staticフィールドとして使用可能
     */
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    
    /**
     * 指定されたLocalDateをフォーマットして返すstaticメソッド
     * @param date フォーマットする日付
     * @return フォーマットされた日付文字列
     */
    public static String formatDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }
}
