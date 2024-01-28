package com.quirotech.quirotech.Utils;

public class CPFvalidator {
    public static boolean validateCPF(String cpf) {

        int[] cpfDigits = new int[11];
        for (int i = 0; i < 11; i++) {
            cpfDigits[i] = Character.getNumericValue(cpf.charAt(i));
        }

        return isValidCPF(cpfDigits);
    }

    private static boolean isValidCPF(int[] cpfDigits) {
        int firstDV = calculateDigit(cpfDigits, 10);

        int secondDV = calculateDigit(cpfDigits, 11);


        return cpfDigits[8] == firstDV && cpfDigits[9] == secondDV;
    }

    private static int calculateDigit(int[] cpfDigits, int weightStart) {
        int sum = 0;
        for (int i = 0; i < weightStart; i++) {
            sum += cpfDigits[i] * (weightStart - i);
        }

        int remainder = sum % 11;
        int digit = 11 - remainder;
        return (remainder < 2) ? 0 : digit;
    }
}