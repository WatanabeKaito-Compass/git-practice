package test.java.org.example.p024;

//import org.junit.jupiter.api.Test;
import java.util.Random;

public class Java024Test {

    public static void main(String[] args) {
        Java024Test test = new Java024Test();
        
        System.out.println("=== 演習1 ===");
        test.ensyu1();
        
        System.out.println("\n=== 演習2 ===");
        test.ensyu2();
        
        System.out.println("\n=== 演習3 ===");
        test.ensyu3();
        
        System.out.println("\n=== 演習4 ===");
        test.ensyu4();
        
        System.out.println("\n=== 演習5 ===");
        test.ensyu5();
        
        System.out.println("\n=== 演習6 ===");
        test.ensyu6();
        
        System.out.println("\n=== 演習7 ===");
        test.ensyu7();
        
        System.out.println("\n=== 演習8 ===");
        test.ensyu8();
    }

    //@Test
    void ensyu1() {
        // 条件分岐１
        int hp = new Random().nextInt(100) + 1; // 1~100のランダムな値

        // TODO: hpが30以下なら"危険"、31~70なら"注意"、71以上なら"安全"とコンソールに出力する
        if (hp <= 30) {
            System.out.println("危険");
        } else if (hp <= 70) {
            System.out.println("注意");
        } else {
            System.out.println("安全");
        }
    }

    //@Test
    void ensyu2() {
        // 条件分岐２
        int hp = new Random().nextInt(100) + 1; // 1~100のランダムな値
        boolean hasShield = new Random().nextBoolean(); // true or falseのランダムな値

        // TODO: 
        // hpが50以下かつhasShieldがfalseなら"大ダメージ"
        // hpが50以下かつhasShieldがtrueなら"小ダメージ"
        // hpが51以上なら"無傷"
        // とコンソールに出力する
        if (hp <= 50 && !hasShield) {
            System.out.println("大ダメージ");
        } else if (hp <= 50 && hasShield) {
            System.out.println("小ダメージ");
        } else {
            System.out.println("無傷");
        }
    }

    //@Test
    void ensyu3() {
        // 条件分岐３
        char[] keys = {'w','a','s','d'};
        char key = keys[new Random().nextInt(keys.length)]; // 'w','a','s','d'のいずれかをランダムに選択

        // TODO:
        // keyが'w'なら"前進"
        // keyが'a'なら"左移動"
        // keyが's'なら"後退"
        // keyが'd'なら"右移動"
        // とコンソールに出力する
        switch (key) {
            case 'w':
                System.out.println("前進");
                break;
            case 'a':
                System.out.println("左移動");
                break;
            case 's':
                System.out.println("後退");
                break;
            case 'd':
                System.out.println("右移動");
                break;
        }
    }

    //@Test
    void ensyu4() {
        // ループ１
        int lives = new Random().nextInt(5) + 1; // 1~5のランダムな値

        // TODO: livesの数を１つずつ減らしながら、"残り{lives}回"とコンソールに出力する
        // ただし、livesが0になったら"ゲームオーバー"と出力してループを終了する
        while (lives > 0) {
            System.out.println("残り" + lives + "回");
            lives--;
        }
        System.out.println("ゲームオーバー");
    }

    //@Test
    void ensyu5() {
        // ループ２
        // TODO: 毎回のループで"y"または"n"をランダムに選択し、"n"が選択されるまでループを続ける。
        // ループごと選択された値を実行回数とともに"{回数}回目: {選択された値}"とコンソールに出力する
        Random random = new Random();
        int count = 0;
        String choice;
        
        do {
            count++;
            choice = random.nextBoolean() ? "y" : "n";
            System.out.println(count + "回目: " + choice);
        } while (!choice.equals("n"));
    }

    //@Test
    void ensyu6() {
        // ループ３
        int h = new Random().nextInt(10) + 1; // 1~10のランダムな値
        int w = new Random().nextInt(10) + 1; // 1~10のランダムな値

        // TODO: h行w列の各マスを"*"で表示する
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    //@Test
    void ensyu7() {
        // 総合1
        int[] enemyHp = new int[4];
        for (int i = 0; i < enemyHp.length; i++) {
            enemyHp[i] = new Random().nextInt(100) + 1; // 1~100のランダムな値
        }

        // TODO: 毎ターン、ランダムで1体の敵を攻撃し、20~50のランダムなダメージを与える。
        // ターンの開始時には各敵のHPを表示し、敵のHPが0以下になったら"敵{敵番号}を倒した！"と表示する。
        // すべての敵を倒したら"全ての敵を倒した！"と表示して終了する
        // ヒント： new Random().nextInt(31) + 20; // 20~50のランダムな値
        Random random = new Random();
        int turn = 1;
        
        while (true) {
            // ターン開始時のHP表示
            System.out.println("ターン" + turn + ":");
            for (int i = 0; i < enemyHp.length; i++) {
                if (enemyHp[i] > 0) {
                    System.out.println("敵" + (i + 1) + ": HP" + enemyHp[i]);
                }
            }
            
            // 生きている敵をカウント
            int aliveCount = 0;
            for (int hp : enemyHp) {
                if (hp > 0) aliveCount++;
            }
            
            if (aliveCount == 0) {
                System.out.println("全ての敵を倒した！");
                break;
            }
            
            // ランダムで敵を選択（生きている敵のみ）
            int target;
            do {
                target = random.nextInt(enemyHp.length);
            } while (enemyHp[target] <= 0);
            
            // ダメージを与える
            int damage = random.nextInt(31) + 20; // 20~50のランダムな値
            enemyHp[target] -= damage;
            
            System.out.println("敵" + (target + 1) + "に" + damage + "のダメージ！");
            
            if (enemyHp[target] <= 0) {
                System.out.println("敵" + (target + 1) + "を倒した！");
            }
            
            turn++;
        }
    }

    //@Test
    void ensyu8() {
        // 総合2
        int size = 7;
        int px = 0, py = 0;         // プレイヤー初期位置
        int gx = size - 1, gy = size - 1; // ゴール位置 (6,6)
        int maxSteps = 1000;      // 安全ガード

        // TODO: size*sizeのマス
        // ランダムで毎ターンプレイヤーを上下左右に1マス移動させる
        // プレイヤーがゴールに到達したら"ゴール！ ターン数: {ターン数}"と表示して終了する
        // プレイヤーがmaxStepsに到達したら"安全ガード発動！"と表示して終了する
        // 毎ターン、現在のマスをコンソールに表示する プレイヤー："P" ゴール："G" それ以外："*"
        // マスの外に出てしまう場合は移動しない
        // ヒント： Math.max(0. py-1) // 上に移動したときのy座標
        // Math.min(size-1, px+1) // 右に移動したときのx座標
        Random random = new Random();
        int turn = 0;
        char[] directions = {'u', 'd', 'l', 'r'}; // up, down, left, right
        
        while (turn < maxSteps) {
            // 現在のマスを表示
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    if (x == px && y == py) {
                        System.out.print("P");
                    } else if (x == gx && y == gy) {
                        System.out.print("G");
                    } else {
                        System.out.print("*");
                    }
                }
                System.out.println();
            }
            System.out.println();
            
            // ゴールチェック
            if (px == gx && py == gy) {
                System.out.println("ゴール！ ターン数: " + turn);
                break;
            }
            
            // ランダムに方向を選択
            char direction = directions[random.nextInt(4)];
            int newPx = px, newPy = py;
            
            switch (direction) {
                case 'u': // 上
                    newPy = Math.max(0, py - 1);
                    break;
                case 'd': // 下
                    newPy = Math.min(size - 1, py + 1);
                    break;
                case 'l': // 左
                    newPx = Math.max(0, px - 1);
                    break;
                case 'r': // 右
                    newPx = Math.min(size - 1, px + 1);
                    break;
            }
            
            px = newPx;
            py = newPy;
            turn++;
        }
        
        if (turn >= maxSteps) {
            System.out.println("安全ガード発動！");
        }
    }
}
