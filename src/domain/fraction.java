package domain;

public final class Fraction {

  private final int fractionalInteger;
  private final int numerator;
  private final int denominator;

  public Fraction() {
    this(0,0,1);
  }

  public Fraction(final int fractionalInteger, final int numerator, final int denominator) {
    if (denominator == 0 || denominator < 0) {
      throw new IllegalArgumentException("Der Nenner ist Null oder kleiner als Null");
    }
    this.fractionalInteger = fractionalInteger;
    this.numerator = numerator;
    this.denominator = denominator;
  }

  public String toStringForLabel() {
    if (!fractionalIntegerIsNull() && !numeratorIsNull()) {
      return fractionalInteger + " " + numerator + "/" + denominator;
    } else if (fractionalIntegerIsNull() && !numeratorIsNull()) {
      return numerator + "/" + denominator;
    } else {
      return String.valueOf(fractionalInteger);
    }
  }

  public Fraction multiplication(Fraction fraction2) {
    final Fraction factor1 = transformToFractionWithoutFractionalInteger();
    final Fraction factor2 = fraction2.transformToFractionWithoutFractionalInteger();
    final Fraction product = new Fraction(0, factor1.numerator * factor2.numerator, factor1.denominator * factor2.denominator);
    return product.reduce();
  }

  public Fraction division(final Fraction divisor) {
    return multiplication(divisor.reciprocal());
  }

  public Fraction addition(final Fraction fraction2) {
    final Fraction addend1 = transformToFractionWithoutFractionalInteger().expand(fraction2);
    final Fraction addend2 = fraction2.transformToFractionWithoutFractionalInteger().expand(this);
    final Fraction sum = new Fraction(0, addend1.numerator + addend2.numerator, addend1.denominator);
    return sum.reduce();
  }

  public Fraction subtraction(final Fraction subtrahend) {
    final Fraction difference = addition(subtrahend.negateNumerator());
    return difference.reduce();
  }

  private Fraction reciprocal() {
    return new Fraction(fractionalInteger, denominator, numerator);
  }

  private Fraction negateNumerator() {
    return new Fraction(fractionalInteger, numerator * -1, denominator);
  }

  private Fraction reduce() {
      int expandedNumerator = (fractionalInteger * denominator) + (numerator);
      int reducedNumerator = expandedNumerator / Fractions.greatestCommonDivisor(expandedNumerator, denominator);
      int reducedDenominator = denominator / Fractions.greatestCommonDivisor(expandedNumerator, denominator);
      return new Fraction(0, reducedNumerator, reducedDenominator);
  }

  private Fraction expand(final Fraction fraction2) {
    final int leastCommonMultiple = Fractions.leastCommonMultiple(denominator, fraction2.denominator);
    return new Fraction(fractionalInteger, (leastCommonMultiple / denominator) * numerator, leastCommonMultiple);
  }

  public Fraction transformToFractionWithoutFractionalInteger() {
      return new Fraction(0, fractionalInteger * denominator + numerator, denominator);
  }

  public Fraction transformToFractionWithFractionalInteger() {
    final Fraction fraction = transformToFractionWithoutFractionalInteger();

    if (Fractions.amountOf(fraction.numerator) < Fractions.amountOf(fraction.denominator)) {
      return this;
    } else {
      int reducedShare = calculateReducedShare(Fractions.amountOf(fraction.numerator));
      if (fraction.numerator >= 0) {
        return new Fraction(reducedShare / fraction.denominator, fraction.numerator - reducedShare, fraction.denominator);
      } else {
        return new Fraction(reducedShare / fraction.denominator * -1, fraction.numerator + reducedShare, fraction.denominator);
      }
    }
  }

  private int calculateReducedShare(int betragVonZaehler) {
    int reducedShare = 0;
    for (int i = Fractions.amountOf(betragVonZaehler); i > 0; i--) {
      if (i % denominator == 0 && reducedShare == 0) {
        reducedShare = i;
      }
    }
    return reducedShare;
  }

  private boolean fractionalIntegerIsNull() {
      return fractionalInteger == 0;
  }

  private boolean numeratorIsNull() {
      return numerator == 0;
  }
}
