package org.example.p042;

/**
 * 定数に情報や振る舞いを持たせる列挙型
 */
public enum Level {
    LOW(1),    // 各列挙子の後ろに()でパラメータを渡す
    MEDIUM(2),
    HIGH(3);

    private final int levelCode;      // フィールドの定義

    Level(int levelCode) { // コンストラクタの定義 自動でprivateになる
        this.levelCode = levelCode;
    }

    public int getLevelCode() {      // メソッドの定義（ゲッター）
        return this.levelCode;
    }

    /**
     * 文字列からLevelに変換する
     * @param code レベルコード（文字列）
     * @return 対応するLevel
     */
    public static Level of(String code) {
        int levelCode = Integer.parseInt(code);
        for (Level level : Level.values()) {
            if (level.getLevelCode() == levelCode) {
                return level;
            }
        }
        throw new IllegalArgumentException("Invalid level code: " + code);
    }
}

