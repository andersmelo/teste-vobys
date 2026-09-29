package br.com.teste;

public final class FactorialUtil {

    private FactorialUtil() {
    }

    public static int zeros(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n não deve ser negativo");
        }
        int zeros = 0;

        while (n > 0) {
            n /= 5;
            zeros += n;
        }

        return zeros;
    }
}
