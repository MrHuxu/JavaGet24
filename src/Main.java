//by Huxu, 09/28/2013, 22:12

import java.util.Random;

public class Main {
    //print ((a @ a) @ a ) @ a
    public static void printResult(float[] num, char[] sym) {
        System.out.printf("(( %d %c %d ) %c %d ) %c %d\n", (int) num[0], sym[0], (int) num[1], sym[1], (int) num[2], sym[2], (int) num[3]);
    }
    //print (a @ a) @ (a @ a)
    public static void printResult(float[] num, char[] sym, char thi) {
        System.out.printf("( %d %c %d ) %c ( %d %c %d )\n", (int) num[0], sym[0], (int) num[1], thi, (int) num[2], sym[1], (int) num[3]);
    }
    //calculate ((a @ a) @ a) @ a
    public static void eval(float[] num, int index, float preRes, char[] sym) {
        if (index != 3) {
            sym[index] = '+';
            eval(num, index + 1, preRes + num[index + 1], sym);
            sym[index] = '-';
            eval(num, index + 1, preRes - num[index + 1], sym);
            sym[index] = '*';
            eval(num, index + 1, preRes * num[index + 1], sym);
            sym[index] = '/';
            eval(num, index + 1, preRes / num[index + 1], sym);
        } else if (preRes == 24) {
            printResult(num, sym);
        }
    }
    //calculate (a @ a) @ (a @ a)
    public static void eval(float[] num, int index, float[] preRes, char[] sym) {
        if (index != 2) {
            preRes[index] = num[index * 2] + num[index * 2 + 1];
            sym[index] = '+';
            eval(num, index + 1, preRes, sym);
            preRes[index] = num[index * 2] - num[index * 2 + 1];
            sym[index] = '-';
            eval(num, index + 1, preRes, sym);
            preRes[index] = num[index * 2] * num[index * 2 + 1];
            sym[index] = '*';
            eval(num, index + 1, preRes, sym);
            preRes[index] = num[index * 2] / num[index * 2 + 1];
            sym[index] = '/';
            eval(num, index + 1, preRes, sym);
        } else {
            if ((preRes[0] + preRes[1]) == 24)
                printResult(num, sym, '+');
            else if ((preRes[0] - preRes[1]) == 24)
                printResult(num, sym, '-');
            else if ((preRes[0] * preRes[1]) == 24)
                printResult(num, sym, '*');
            else if ((preRes[0] / preRes[1]) == 24)
                printResult(num, sym, '/');
        }
    }

    public static void main(String argv[]) {
        Random rd = new Random();
        float[] rdNum = new float[4];
        float[] form = new float[4];
        char[] sym = new char[3];
        float[] preRes = new float[2];
        for (int i = 0; i <= 3; i++) {
            rdNum[i] = rd.nextInt(9) + 1;
            System.out.printf("%d%s", (int) rdNum[i], "   ");
        }
        System.out.println("\n==============================");
        for (int i = 0; i <= 3; i++) {
            form[0] = rdNum[i];
            for (int j = 0; j <= 3; j++) {
                if (j != i) {
                    form[1] = rdNum[j];
                    for (int k = 0; k <= 3; k++) {
                        if (k != i && k != j) {
                            form[2] = rdNum[k];
                            for (int l = 0; l <= 3; l++) {
                                if (l != i && l != j && l != k) {
                                    form[3] = rdNum[l];
                                    eval(form, 0, form[0], sym);
                                    eval(form, 0, preRes, sym);
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}