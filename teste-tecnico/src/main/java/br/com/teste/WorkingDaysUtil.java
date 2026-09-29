package br.com.teste;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;

public final class WorkingDaysUtil {

    private WorkingDaysUtil() {
    }

    public static long count(final LocalDate start, final LocalDate end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Start e End não podem ser nulas");
        }

        YearMonth inicioDoMes = YearMonth.from(start);
        YearMonth fimDoMes = YearMonth.from(end);

        if (fimDoMes.isBefore(inicioDoMes)) {
            throw new IllegalArgumentException(
                    "O mês final não pode ser anterior ao mês inicial"
            );
        }

        LocalDate primeiraSegunda = inicioDoMes
                .atDay(1)
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));

        LocalDate ultimaSexta = fimDoMes
                .atEndOfMonth()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY));

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
