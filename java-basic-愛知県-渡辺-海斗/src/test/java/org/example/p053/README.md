# p053 - ファイル演習

## 概要
CSVファイルの読み書きを学ぶ演習です。

## ファイル構成

```
java-basic-愛知県-渡辺-海斗/
├── .gitignore              # data/フォルダをGit管理から除外
├── data/
│   └── test.csv            # テスト用CSVファイル
└── src/test/java/org/example/p053/
    └── FileTest.java       # メインのテストクラス
```

## 実行方法

### 1. 初期状態の確認
`data/test.csv` の内容:
```csv
No,Name,Age
1,John,25
2,Jane,30
3,Tom,35
```

### 2. テストの実行
```bash
cd java-basic-愛知県-渡辺-海斗
java -cp src/test/java org.example.p053.FileTest
```

### 3. 実行後の確認
`data/test.csv` にデータが追記されていることを確認してください。

実行するたびに3件ずつデータが追加されます:
- Alice, 28歳
- Bob, 32歳
- Charlie, 27歳

## 主な学習ポイント

### 1. recordクラス（Java 14以降）
```java
public record Person(int no, String name, int age) {
    public String toCsv() {
        return no + "," + name + "," + age;
    }
}
```

**recordクラスの特徴:**
- 不変（immutable）なデータクラスを簡潔に定義
- 自動的に以下が生成される:
  - コンストラクタ
  - getter メソッド（フィールド名と同じ名前）
  - `equals()`, `hashCode()`, `toString()` メソッド
- フィールドは全て `private final`

### 2. try-with-resources
```java
try (BufferedReader br = Files.newBufferedReader(path)) {
    // ファイル操作
} // 自動的にクローズされる
```

**利点:**
- リソース（ファイル）が自動的にクローズされる
- 例外が発生してもクローズ処理が実行される
- コードがシンプルで読みやすい

### 3. ファイルへの追記
```java
Files.newBufferedWriter(path, StandardOpenOption.APPEND)
```

`StandardOpenOption.APPEND` を指定することで、既存のファイル内容を保持したまま末尾に追記できます。

### 4. File.separator
```java
String path = "data" + File.separator + "test.csv";
```

OS依存のパス区切り文字（Windows: `\`, Unix/Mac: `/`）を自動的に使用します。

## データの流れ

1. **読み込み** → CSV行をパース → Personオブジェクトに変換
2. **追記** → 新しいPersonオブジェクト作成 → CSV形式に変換 → ファイルに書き込み
3. **確認** → ファイル全体を再読み込み → 統計情報を計算

## 注意事項

- `data/` フォルダは `.gitignore` で除外されているため、Gitにコミットされません
- テストを実行するたびにデータが追加されるため、初期状態に戻したい場合は `data/test.csv` を手動で編集してください

