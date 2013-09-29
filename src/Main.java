/*
by Huxu, 09/28/2013, 22:12
 */
import java.util.Random;

public class Main {

    public static void printResult(float[] num, char[] sym) {
        System.out.printf("(( %d %c %d ) %c %d ) %c %d\n", (int) num[0], sym[0], (int) num[1], sym[1], (int) num[2], sym[2], (int) num[3]);
    }

    public static void printResult(float[] num, char[] sym, char thi) {
        System.out.printf("( %d %c %d ) %c ( %d %c %d )\n", (int) num[0], sym[0], (int) num[1], thi, (int) num[2], sym[1], (int) num[3]);
    }

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
            if ((preRes[0] + preRes[1]) == 24 )
                printResult(num, sym, '+');
            else if ((preRes[0] - preRes[1]) == 24)
                printResult(num, sym, '-');
            else if ((preRes[0] * preRes[1]) == 24)
                printResult(num, sym, '*');
            else if ((preRes[0] / preRes[1]) == 24)
                printResult(num, sym, '/');
        }
    }

    public static void calc(int[] sort, float[] num) {
        float[] form = new float[4];
        for (int i = 0; i < 4; i++) {
            form[i] = num[sort[i]];
        }
        char[] sym = new char[3];
        float[] preRes = new float[2];
        eval(form, 0, form[0], sym);
        eval(form, 0, preRes, sym);
    }

    public static void main(String argv[]) {
        Random rd = new Random();
        float[] rdNum = new float[4];
        for (int i = 0; i <= 3; i++) {
            rdNum[i] = rd.nextInt(9) + 1;
            System.out.printf("%d%s", (int) rdNum[i], "   ");
        }
        System.out.println("\n==============================");
        int[][] sort = new int[24][4];
        int count = 0;
        for (int i = 0; i <= 3; i++) {
            for (int j = 0; j <= 3; j++) {
                if (j != i) {
                    for (int k = 0; k <= 3; k++) {
                        if (k != i && k != j) {
                            for (int l = 0; l <= 3; l++) {
                                if (l != i && l != j && l != k) {
                                    sort[count][0] = i;
                                    sort[count][1] = j;
                                    sort[count][2] = k;
                                    sort[count++][3] = l;
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 24; i++) {
            calc(sort[i], rdNum);
        }

    }
}