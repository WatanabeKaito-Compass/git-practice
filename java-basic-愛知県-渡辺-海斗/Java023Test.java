//package org.example.p023;

//import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Java023Test {

    //@Test
    void ensyu1() {
        int rows = 5, cols = 5;
        int num = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(num++ + " ");
            }
            System.out.println();
        }
    }

    //@Test
    void ensyu2() {
        int rows = 4, cols = 4;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                System.out.print(i * j + " ");
            }
            System.out.println();
        }
    }

    //@Test
    void ensyu3() {
        int rows = 4, cols = 4;
        int num = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(num % 10 + " ");
                num++;
            }
            System.out.println();
        }
    }
}
