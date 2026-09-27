package br.com.teste;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello, World!");
//        System.out.println(
//                TripleTroubleUtil.tripleDouble(1116666789, 12345667)
//        );

//        System.out.println(
//                FindOutlierUtil.find(new int[]{163, 3, 1719, 19, 11, 13, -21})
//        );

//        System.out.println(
//                FactorialUtil.zeros(30)
//        );

        LocalDate start = LocalDate.of(2026, 9, 15);
        LocalDate end = LocalDate.of(2026, 10, 20);

        System.out.println(
                WorkingDaysUtil.count(start, end)
        );

    }
}