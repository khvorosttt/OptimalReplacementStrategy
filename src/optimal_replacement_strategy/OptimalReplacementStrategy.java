/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimal_replacement_strategy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class OptimalReplacementStrategy {

    /**
     * @param args the command line arguments
     */
    static int N;//плановый период
    static int s;//остаточная стоимость
    static int p;//цена единицы нового оборудования
    static int[] r;//стоимость произведённой продукции, с использованием данного оборудования, для каждого года
    static int[] u;//затраты, связанные с эксплуатацией оборудования для каждого года
    static int t;//возраст оборудования
    public static final String ANSI_RESET = "\u001B[0m";//сброс цвета
    public static final String ANSI_GREEN = "\u001B[32m";//вывод зелёного цвета
    public static final String ANSI_RED = "\u001B[31m";//вывод красного цвета

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        IN();
        Profit[][] profit = new Profit[N][N + 1];
        F_1(profit);
        for (int i = 1; i < profit.length; i++) {
            F_k(i, profit);
        }
        showMatrixProfit(profit);
        System.out.print("Введите возраст оборудования t = ");
        t = in.nextInt();
        ProfitPolitica[] politica = new ProfitPolitica[N];
        for (int i = N - 1; i >= 0; i--) {
            politica[N - 1 - i] = new ProfitPolitica(profit[i][t], t);
            if (profit[i][t].Save()) {
                t++;
            } else {
                t = 1;
            }
        }
        showPolitica(politica);
    }

    public static void IN() throws FileNotFoundException, IOException {
        BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(System.getProperty("user.dir") + "\\data.txt")));
        //BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(System.getProperty("user.dir") + "\\data_10_13_b.txt")));
        //BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(System.getProperty("user.dir") + "\\data_10_14_b.txt")));
        String[] data_info = fin.readLine().split(" ");//плановый период, остаточная стоимость оборудования, цена единицы нового оборудования
        N = Integer.parseInt(data_info[0]);
        s = Integer.parseInt(data_info[1]);
        p = Integer.parseInt(data_info[2]);
        r = new int[N + 1];
        u = new int[N + 1];
        String[] data = fin.readLine().split(" ");
        for (int j = 0; j < r.length; j++) {
            r[j] = Integer.parseInt(data[j]);
        }
        data = fin.readLine().split(" ");
        for (int j = 0; j < u.length; j++) {
            u[j] = Integer.parseInt(data[j]);
        }
    }

    public static void showVector(int[] vector) {
        for (int j = 0; j < vector.length; j++) {
            System.out.print(vector[j] + " ");
        }
        System.out.println();
    }

    //показ матрицы
    public static void showMatrixProfit(Profit[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j].Save()) {
                    System.out.print(ANSI_GREEN + matrix[i][j].Pr() + " " + ANSI_RESET);
                } else {
                    System.out.print(ANSI_RED + matrix[i][j].Pr() + " " + ANSI_RESET);
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public static void F_1(Profit[][] profit) {
        for (int j = 0; j < profit[0].length; j++) {
            if ((r[j] - u[j]) >= (s - p + r[0] - u[0])) {
                profit[0][j] = new Profit(r[j] - u[j], true);
            } else {
                profit[0][j] = new Profit(s - p + r[0] - u[0], false);
                for (int k = j + 1; k < profit[0].length; k++) {
                    profit[0][k] = new Profit(s - p + r[0] - u[0], false);
                }
                break;
            }
        }
    }

    public static void F_k(int i, Profit[][] profit) {
        for (int j = 0; j < profit[i].length; j++) {
            if ((r[j] - u[j] + profit[i - 1][j + 1].Pr()) >= (s - p + r[0] - u[0] + profit[i - 1][1].Pr())) {
                profit[i][j] = new Profit(r[j] - u[j] + profit[i - 1][j + 1].Pr(), true);
            } else {
                profit[i][j] = new Profit(s - p + r[0] - u[0] + profit[i - 1][1].Pr(), false);
                for (int k = j + 1; k < profit[0].length; k++) {
                    profit[i][k] = new Profit(s - p + r[0] - u[0] + profit[i - 1][1].Pr(), false);
                }
                break;
            }
        }
    }

    public static void showPolitica(ProfitPolitica[] politica) {
        for (int i = 0; i < politica.length; i++) {
            if (politica[i]._profit().Save()) {
                System.out.println((i + 1) + "-ый год -"+ANSI_GREEN+" сохранение"+ANSI_RESET+ " (возраст оборудования t = " + politica[i].Age() + ", прибыль " + politica[i]._profit().Pr() + ")");
            } else {
                System.out.println((i + 1) + "-ый год -"+ANSI_RED+" замена"+ANSI_RESET+" (возраст оборудования t = " + politica[i].Age() + ", прибыль " + politica[i]._profit().Pr() + ")");
            }
        }
    }

}
