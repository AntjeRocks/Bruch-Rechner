package domain;

final class Fractions {

    private Fractions() {}

    static int amountOf(int numeral) {
        if (numeral < 0) {
            numeral *= (-1);
        }
        return numeral;
    }

    static int greatestCommonDivisor(final int numeral1, final int numeral2) {
        int greatestCommonDivisor = 0;
        if (amountOf(numeral1) == amountOf(numeral2)) {
            greatestCommonDivisor = numeral1;
        } else if (amountOf(numeral1) < amountOf(numeral2)) {
            greatestCommonDivisor = calculateGreatestCommonDivisor(numeral1, numeral2, greatestCommonDivisor);
        } else if (amountOf(numeral2) < amountOf(numeral1)) {
            greatestCommonDivisor = calculateGreatestCommonDivisor(numeral2, numeral1, greatestCommonDivisor);
        }
        return greatestCommonDivisor;
    }

    private static int calculateGreatestCommonDivisor(int numeral1, int numeral2, int greatestCommonDivisor) {
        for (int i = amountOf(numeral1); i > 0; i--) {
            if (numeral1 % i == 0 && numeral2 % i == 0 && greatestCommonDivisor == 0) {
                greatestCommonDivisor = i;
                break;
            }
        }
        return greatestCommonDivisor;
    }

    static int leastCommonMultiple(final int numeral1, final int numeral2) {
        return (amountOf(numeral1) * amountOf(numeral2)) / greatestCommonDivisor(numeral1, numeral2);
    }
}
