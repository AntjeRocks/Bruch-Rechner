package domain;

public final class Rename1 {

  private final int fractionalInteger;
  private final int numerator;
  private final int denominator;

  public Rename1() {
    this(0,0,1);
  }

  public Rename1(final int fractionalInteger, final int numerator, final int denominator) {
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

  public Rename1 multiplication(Rename1 fraction2) {
    final Rename1 factor1 = transformToFractionWithoutFractionalInteger();
    final Rename1 factor2 = fraction2.transformToFractionWithoutFractionalInteger();
    final Rename1 product = new Rename1(0, factor1.numerator * factor2.numerator, factor1.denominator * factor2.denominator);
    return product.reduce();
  }

  public Rename1 division(final Rename1 divisor) {
    return multiplication(divisor.reciprocal());
  }

  public Rename1 addition(final Rename1 fraction2) {
    final Rename1 addend1 = transformToFractionWithoutFractionalInteger().expand(fraction2);
    final Rename1 addend2 = fraction2.transformToFractionWithoutFractionalInteger().expand(this);
    final Rename1 sum = new Rename1(0, addend1.numerator + addend2.numerator, addend1.denominator);
    return sum.reduce();
  }

  public Rename1 subtraction(final Rename1 subtrahend) {
    final Rename1 difference = addition(subtrahend.negateNumerator());
    return difference.reduce();
  }

  private Rename1 reciprocal() {
    return new Rename1(fractionalInteger, denominator, numerator);
  }

  private Rename1 negateNumerator() {
    return new Rename1(fractionalInteger, numerator * -1, denominator);
  }

  private Rename1 reduce() {
      int expandedNumerator = (fractionalInteger * denominator) + (numerator);
      int reducedNumerator = expandedNumerator / Fractions.greatestCommonDivisor(expandedNumerator, denominator);
      int reducedDenominator = denominator / Fractions.greatestCommonDivisor(expandedNumerator, denominator);
      return new Rename1(0, reducedNumerator, reducedDenominator);
  }

  private Rename1 expand(final Rename1 fraction2) {
    final int leastCommonMultiple = Fractions.leastCommonMultiple(denominator, fraction2.denominator);
    return new Rename1(fractionalInteger, (leastCommonMultiple / denominator) * numerator, leastCommonMultiple);
  }

  public Rename1 transformToFractionWithoutFractionalInteger() {
      return new Rename1(0, fractionalInteger * denominator + numerator, denominator);
  }

  public Rename1 transformToFractionWithFractionalInteger() {
    final Rename1 fraction = transformToFractionWithoutFractionalInteger();

    if (Fractions.amountOf(fraction.numerator) < Fractions.amountOf(fraction.denominator)) {
      return this;
    } else {
      int reducedShare = calculateReducedShare(Fractions.amountOf(fraction.numerator));
      if (fraction.numerator >= 0) {
        return new Rename1(reducedShare / fraction.denominator, fraction.numerator - reducedShare, fraction.denominator);
      } else {
        return new Rename1(reducedShare / fraction.denominator * -1, fraction.numerator + reducedShare, fraction.denominator);
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
