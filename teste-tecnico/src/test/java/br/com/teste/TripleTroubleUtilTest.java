package br.com.teste;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TripleTroubleUtil")
class TripleTroubleUtilTest {

    @Nested
    @DisplayName("Quando existe triplo em num1 e dobro correspondente em num2")
    class TriploEDobroEncontrado {

        @ParameterizedTest(name = "num1={0}, num2={1} deve retornar 1")
        @CsvSource({
                "451999277, 41177722899",
                "1222345,    1223345",
                "666789,     12366",
                "1000,       100",
                "777,        77",
                "1111,       11",
                "9999,       999"
        })
        @DisplayName("Deve retornar 1")
        void deveRetornarUmQuandoExistirTriplaEDupla(
                long num1,
                long num2
        ) {
            int result = TripleTroubleUtil.tripleDouble(num1, num2);

            assertEquals(1, result);
        }
    }

    @Nested
    @DisplayName("Quando não existe combinação válida")
    class TriploEDobroNaoEncontrado {

        @ParameterizedTest(name = "num1={0}, num2={1} deve retornar 0")
        @CsvSource({
                "451999277, 411777289",
                "12345,     12345",
                "123456,    112233",
                "111,       22",
                "11,        11",
                "1,         1",
                "0,         1"
        })
        @DisplayName("Deve retornar 0")
        void deveRetornarZeroQuandoTriplaEDuplaNaoExistirem(
                long num1,
                long num2
        ) {
            int result = TripleTroubleUtil.tripleDouble(num1, num2);

            assertEquals(0, result);
        }
    }

    @Nested
    @DisplayName("Outros cenários possíveis")
    class EdgeCases {

        @Test
        @DisplayName("Deve identificar sequência de zeros")
        void deveIdentificarSequenciaDeZeros() {
            long num1 = 100023;
            long num2 = 10045;

            int result = TripleTroubleUtil.tripleDouble(num1, num2);

            assertEquals(1, result);
        }

        @Test
        @DisplayName("Deve identificar triplo dentro de sequência maior")
        void deveIdentificarTriplaDentroDeSequenciaMaior() {
            long num1 = 11111;
            long num2 = 11;

            int result = TripleTroubleUtil.tripleDouble(num1, num2);

            assertEquals(1, result);
        }

        @Test
        @DisplayName("Não deve considerar dígitos diferentes")
        void naoDeveConsiderarDigitosDiferentes() {
            long num1 = 111;
            long num2 = 22;

            int result = TripleTroubleUtil.tripleDouble(num1, num2);

            assertEquals(0, result);
        }

        @Test
        @DisplayName("Deve funcionar com zero em ambos os argumentos")
        void deveTratarValoresZero() {
            int result = TripleTroubleUtil.tripleDouble(0, 0);

            assertEquals(0, result);
        }

        @Test
        @DisplayName("Deve funcionar com números negativos")
        void deveTratarNumerosNegativos() {
            long num1 = -111234;
            long num2 = -11234;

            int result = TripleTroubleUtil.tripleDouble(num1, num2);

            assertEquals(1, result);
        }

        @Test
        @DisplayName("Deve funcionar quando apenas o primeiro número é negativo")
        void deveTratarPrimeiroNumeroNegativo() {
            int result = TripleTroubleUtil.tripleDouble(-555123, 55123);

            assertEquals(1, result);
        }

        @Test
        @DisplayName("Deve funcionar quando apenas o segundo número é negativo")
        void deveTratarSegundoNumeroNegativo() {
            int result = TripleTroubleUtil.tripleDouble(777123, -77123);

            assertEquals(1, result);
        }
    }
}