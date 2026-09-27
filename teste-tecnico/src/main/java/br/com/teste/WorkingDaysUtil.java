package br.com.teste;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class WorkingDaysUtil {

    public static long count(final LocalDate start, final LocalDate end) {

        LocalDate primeiraSegunda = start.withDayOfMonth(1).with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        LocalDate ultimaSexta = end.with(TemporalAdjusters.lastDayOfMonth()).with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY));

        long diasUteis = 0;

        LocalDate dataAtual = primeiraSegunda;

        while (!dataAtual.isAfter(ultimaSexta)) {
            if (ehDiaUtil(dataAtual)) {
                diasUteis++;
            }
            dataAtual = dataAtual.plusDays(1);
        }
        return diasUteis;
    }

    private static boolean ehDiaUtil(LocalDate date) {
        DayOfWeek dia = date.getDayOfWeek();

        return dia != DayOfWeek.SATURDAY && dia != DayOfWeek.SUNDAY;
    }
}
