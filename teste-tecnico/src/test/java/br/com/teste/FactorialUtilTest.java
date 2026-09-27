package br.com.teste;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("FactorialUtil")
class FactorialUtilTest {

    @ParameterizedTest(name = "[{index}] zeros({0}) deve retornar {1}")
    @CsvSource({
            "0,    0",
            "1,    0",
            "4,    0",
            "5,    1",
            "6,    1",
            "9,    1",
            "10,   2",
            "20,   4",
            "24,   4",
            "25,   6",
            "26,   6",
            "30,   7",
            "50,   12",
            "100,  24",
            "125,  31",
            "200,  49",
            "625,  156",
            "1000, 249"
    })
    @DisplayName("Deve retornar a quantidade de zeros à direita do fatorial")
    void deveRetornarZerosFinais(int n, int expected) {
        int result = FactorialUtil.zeros(n);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Deve retornar zero para número negativo")
    void deveRetornarZeroParaNumeroNegativo() {
        int result = FactorialUtil.zeros(-10);

        assertEquals(0, result);
    }
}