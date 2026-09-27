package br.com.teste;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("FindOutlierUtil")
class FindOutlierUtilTest {

    @Nested
    @DisplayName("find")
    class Find {

        @ParameterizedTest(name = "[{index}] deve encontrar {1}")
        @CsvSource({
                "'2,4,6,8,11,10', 11",
                "'1,3,5,7,10,9', 10",
                "'2,6,8,-10,3', 3",
                "'1,3,-5,8,7', 8",
                "'0,2,4,6,7', 7",
                "'1,3,5,0,7', 0"
        })
        @DisplayName("Deve encontrar o número com paridade diferente")
        void deveEncontrarValorDivergente(String input, int expected) {
            int[] integers = parseArray(input);

            int result = FindOutlierUtil.find(integers);

            assertEquals(expected, result);
        }

        @Test
        @DisplayName("Deve encontrar ímpar quando estiver na primeira posição")
        void deveEncontrarImparDivergenteNaPrimeiraPosicao() {
            int[] integers = {7, 2, 4, 6, 8};

            int result = FindOutlierUtil.find(integers);

            assertEquals(7, result);
        }

        @Test
        @DisplayName("Deve encontrar par quando estiver na última posição")
        void deveEncontrarParDivergenteNaUltimaPosicao() {
            int[] integers = {1, 3, 5, 7, 10};

            int result = FindOutlierUtil.find(integers);

            assertEquals(10, result);
        }

        @Test
        @DisplayName("Deve funcionar com valores extremos de int")
        void deveTratarLimitesDeInteger() {
            int[] integers = {
                    1,
                    3,
                    5,
                    Integer.MIN_VALUE,
                    7
            };

            int result = FindOutlierUtil.find(integers);

            assertEquals(Integer.MIN_VALUE, result);
        }

        @Test
        @DisplayName("Deve lançar exceção quando todos forem pares")
        void deveLancarExcecaoQuandoTodosNumerosForemPares() {
            int[] integers = {2, 4, 6, 8, 10};

            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> FindOutlierUtil.find(integers)
            );

            assertEquals(
                    "Valor atípico não encontrado",
                    exception.getMessage()
            );
        }

        @Test
        @DisplayName("Deve lançar exceção quando todos forem ímpares")
        void deveLancarExcecaoQuandoTodosNumerosForemImpares() {
            int[] integers = {1, 3, 5, 7, 9};

            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> FindOutlierUtil.find(integers)
            );

            assertEquals(
                    "Valor atípico não encontrado",
                    exception.getMessage()
            );
        }

        private int[] parseArray(String input) {
            return java.util.Arrays.stream(input.split(","))
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
    }

    @Nested
    @DisplayName("ehPar")
    class EhPar {

        @ParameterizedTest(name = "{0} deve ser considerado par")
        @ValueSource(ints = {
                0,
                2,
                10,
                -2,
                -100,
                Integer.MAX_VALUE - 1,
                Integer.MIN_VALUE
        })
        void deveRetornarVerdadeiroParaNumerosPares(int numero) {
            assertTrue(FindOutlierUtil.ehPar(numero));
        }

        @ParameterizedTest(name = "{0} deve ser considerado ímpar")
        @ValueSource(ints = {
                1,
                3,
                11,
                -1,
                -99,
                Integer.MAX_VALUE,
                Integer.MIN_VALUE + 1
        })
        void deveRetornarFalsoParaNumerosImpares(int numero) {
            assertFalse(FindOutlierUtil.ehPar(numero));
        }
    }
}