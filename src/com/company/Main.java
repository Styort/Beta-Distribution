package com.company;

import org.knowm.xchart.*;

public class Main {
    static double a1, b, s1, s2;
    static double[] arrBetta = new double[100];

    public static void main(String[] args) {
        getPeriod();
        Calculate();
        DrawGraph();
    }
    public static long count = 0;

    public static void getPeriod(){
        long firstRand = seed;
        do {
            count++;
            getRand();
        }
        while (seed != firstRand);

        System.out.println("Интервал между повторениями = " + count);
    }

    public static void Calculate() { //Бета-распределение
        a1 = 2.5;
        b = 2;
        long i = 0;
        double h = (double) 1 / arrBetta.length;
        while (i < count) {
            s1 = Math.exp(Math.log(getRand()) / a1);
            s2 = Math.exp(Math.log(getRand()) / b);
            if (s1 + s2 <= 1) {
                int arrIt = (int) ((s1 / (s2 + s1) / h));
                arrBetta[arrIt]++;
                i++;
            }
        }
    }

    public static long a = 1664525;
    public static long c = 	1013904223;
    public static long m = 4294967296l;
    public static long seed = 131231;

    public static double getRand() { //получаем рандомные значения
        seed = (a * seed + c) % m;
        return (double)seed / m;

    }

    public static void DrawGraph() { //рисуем гистограмму

        double arrY[] = new double[100];
        for (int i = 0; i < arrY.length; i++) {
            arrY[i] = i;
        }
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Бета-Распределение").xAxisTitle("Интервал").yAxisTitle("Полученные значения").build();
        chart.addSeries("Бета-Распределение", arrY, arrBetta);
        new SwingWrapper<>(chart).displayChart();
    }
}
