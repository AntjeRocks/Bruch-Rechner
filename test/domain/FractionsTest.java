package domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class FractionsTest {

  @Test
  public void shouldGetGreatestCommonDivisorOfOne() {
    //given
    final Fraction fraction1 = new Fraction(1, 1, 11);
    final Fraction fraction2 = new Fraction(1, 1, 23);

    //when
    final int greatestCommonDivisor =
        Fractions.greatestCommonDivisor(fraction1.getDenominator(), fraction2.getDenominator());

    //then
    assertThat(greatestCommonDivisor, is(1));
  }

  @Test
  public void shouldGetGreatestCommonDivisor() {
    //given
    final Fraction fraction1 = new Fraction(1, 5, 144);
    final Fraction fraction2 = new Fraction(1, 77, 16);

    //when
    final int greatestCommonDivisor =
        Fractions.greatestCommonDivisor(fraction1.getDenominator(), fraction2.getDenominator());

    //then
    assertThat(greatestCommonDivisor, is(16));
  }
}
