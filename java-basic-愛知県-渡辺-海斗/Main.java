import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // プリミティブ型の比較
        int a = 100;
        int b = 100;
        System.out.println("a == b: " + (a == b)); // true

        // オブジェクト型の比較
        Integer x = 200;
        Integer y = 200;
        System.out.println("x == y: " + (x == y)); // false
        System.out.println("x.equals(y): " + x.equals(y)); // true

        // if文の例
        int num = 50;
        if (num > 40) {
            System.out.println("numは40より大きい");
        } else {
            System.out.println("numは40以下");
        }

        // for文とcontinue/breakの例
        for (int i = 0; i < 10; i++) {
            if (i == 5) continue;
            if (i == 8) break;
            System.out.println("i = " + i);
        }

        // sumメソッド呼び出し
        int result = sum(1);
        System.out.println("sum(1) = " + result);

        // 配列の比較
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};
        int[] arr3 = arr1;
        System.out.println("arr1 == arr2: " + (arr1 == arr2)); // false
        System.out.println("arr1 == arr3: " + (arr1 == arr3)); // true
        System.out.println("Arrays.equals(arr1, arr2): " + Arrays.equals(arr1, arr2)); // true

        // Integerキャッシュの例
        Integer c = 100;
        Integer d = 100;
        Integer e = 200;
        Integer f = 200;
        System.out.println("c == d: " + (c == d)); // true
        System.out.println("c.equals(d): " + c.equals(d)); // true
        System.out.println("e == f: " + (e == f)); // false
        System.out.println("e.equals(f): " + e.equals(f)); // true
    }

    public static int sum(int n){
        int sum = 0;
        for (int i = n; i <= 10; i++) {
            sum += i;
            if (sum > 20) {
                return sum; // 20を超えたらループを抜ける
            }
        }
        return sum;
    }
}
