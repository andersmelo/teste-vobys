package br.com.teste;

public final class TripleTroubleUtil {

    private TripleTroubleUtil() {
    }

    public static int tripleDouble(long num1, long num2) {
        String numero1 = String.valueOf(num1);
        String numero2 = String.valueOf(num2);

        for (char digito = '0'; digito <= '9'; digito++) {
            String triplo = String.valueOf(digito).repeat(3);
            String dobro = String.valueOf(digito).repeat(2);

            if (numero1.contains(triplo) && numero2.contains(dobro)) {
                return 1;
            }
        }
        return 0;
    }
}
