package br.com.teste;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("WorkingDaysUtil")
class WorkingDaysUtilTest {

    @Nested
    @DisplayName("Contagem no mesmo mês")
    class MesmoMes {

        @ParameterizedTest(
                name = "[{index}] {0} até {1} deve retornar {2} dias úteis"
        )
        @CsvSource({
                "2026-09-01, 2026-09-30, 15",
                "2026-02-01, 2026-02-28, 20",
                "2026-03-01, 2026-03-31, 20",
                "2026-06-01, 2026-06-30, 20"
        })
        @DisplayName("Deve contar da primeira segunda até a última sexta")
        void deveContarDiasUteisNoMesmoMes(
                LocalDate start,
                LocalDate end,
                long expected
        ) {
            long result = WorkingDaysUtil.count(start, end);

            assertEquals(expected, result);
        }

        @Test
        @DisplayName("Deve ignorar os dias informados nas datas")
        void deveIgnorarDiasInformados() {
            LocalDate start = LocalDate.of(2026, 9, 25);
            LocalDate end = LocalDate.of(2026, 9, 26);

            long result = WorkingDaysUtil.count(start, end);

            assertEquals(15, result);
        }
    }

    @Nested
    @DisplayName("Contagem entre meses diferentes")
    class MesDiferente {

        @ParameterizedTest(
                name = "[{index}] {0} até {1} deve retornar {2} dias úteis"
        )
        @CsvSource({
                "2026-09-15, 2026-10-10, 40",
                "2026-01-20, 2026-02-05, 40",
                "2026-11-10, 2026-12-20, 40"
        })
        @DisplayName("Deve contar dias úteis entre meses diferentes")
        void deveContarDiasUteisEntreMesesDiferentes(
                LocalDate start,
                LocalDate end,
                long expected
        ) {
            long result = WorkingDaysUtil.count(start, end);

            assertEquals(expected, result);
        }
    }

    @Nested
    @DisplayName("Primeira segunda-feira")
    class PrimeiraSegundaFeira {

        @Test
        @DisplayName("Deve considerar dia 1 quando ele for segunda-feira")
        void deveIniciarNoPrimeiroDiaQuandoForSegundaFeira() {
            LocalDate start = LocalDate.of(2026, 6, 20);
            LocalDate end = LocalDate.of(2026, 6, 25);

            long result = WorkingDaysUtil.count(start, end);

            assertEquals(20, result);
        }

        @Test
        @DisplayName("Deve avançar até a primeira segunda-feira")
        void deveAvancarParaPrimeiraSegundaFeira() {
            LocalDate start = LocalDate.of(2026, 9, 1);
            LocalDate end = LocalDate.of(2026, 9, 30);

            long result = WorkingDaysUtil.count(start, end);

            assertEquals(15, result);
        }
    }

    @Nested
    @DisplayName("Última sexta-feira")
    class UltimaSextaFeira {

        @Test
        @DisplayName("Deve considerar o último dia quando ele for sexta-feira")
        void deveUsarUltimoDiaQuandoForSextaFeira() {
            LocalDate start = LocalDate.of(2026, 7, 10);
            LocalDate end = LocalDate.of(2026, 7, 15);

            long result = WorkingDaysUtil.count(start, end);

            assertEquals(20, result);
        }

        @Test
        @DisplayName("Deve retroceder até a última sexta-feira")
        void deveRetrocederParaUltimaSextaFeira() {
            LocalDate start = LocalDate.of(2026, 9, 10);
            LocalDate end = LocalDate.of(2026, 9, 15);

            long result = WorkingDaysUtil.count(start, end);

            assertEquals(15, result);
        }
    }
}