package br.com.teste;

public class FactorialUtil {

    public static int zeros(int n) {
        int zeros = 0;

        while (n > 0) {
            n /= 5;
            zeros += n;
        }

        return zeros;
    }
}
