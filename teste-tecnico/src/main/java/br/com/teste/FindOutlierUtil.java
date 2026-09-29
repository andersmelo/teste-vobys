package br.com.teste;

public final class FindOutlierUtil {

    private static final int TAMANHO_MINIMO = 3;

    private FindOutlierUtil() {
    }

    public static int find(int[] integers) {
        if (integers == null || integers.length < TAMANHO_MINIMO) {
            throw new IllegalArgumentException("Array deveria conter no mínimo 3 inteiros");
        }
        int quantidadePares = 0;

        for (int i = 0; i < TAMANHO_MINIMO; i++) {
            if (ehPar(integers[i])) {
                quantidadePares++;
            }
        }

        boolean valorAtipicoEhPar = quantidadePares < 2;

        for (int numero : integers) {
            if (ehPar(numero) == valorAtipicoEhPar) {
                return numero;
            }
        }
        throw new IllegalArgumentException("Valor atípico não encontrado");
    }

    private static boolean ehPar(int numero) {
        return numero % 2 == 0;
    }
}
