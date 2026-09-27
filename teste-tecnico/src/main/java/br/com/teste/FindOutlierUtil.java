package br.com.teste;

public class FindOutlierUtil {

    public static int find(int[] integers) {
        int contador = 0;

        for (int i = 0; i < 3; i++) {
            if (ehPar(integers[i])) {
                contador++;
            }
        }

        boolean buscandoPar = contador < 2;

        for (int numero : integers) {
            if (ehPar(numero) == buscandoPar) {
                return numero;
            }
        }
        throw new IllegalArgumentException("Valor atípico não encontrado");
    }

    public static boolean ehPar(int numero) {
        return numero % 2 == 0;
    }
}
