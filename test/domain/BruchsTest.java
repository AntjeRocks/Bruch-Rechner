package domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class BruchsTest {

  @Test
  public void shouldGetGGTOfOne() {
    //given
    final Bruch bruch1 = new Bruch(1, 1, 11);
    final Bruch bruch2 = new Bruch(1, 1, 23);

    //when
    final int ggt = Bruchs.ggtBerechnen(bruch1.getNenner(), bruch2.getNenner());

    //then
    assertThat(ggt, is(1));
  }

  @Test
  public void shouldGetGGT() {
    //given
    final Bruch bruch1 = new Bruch(1, 5, 144);
    final Bruch bruch2 = new Bruch(1, 77, 16);

    //when
    final int ggt = Bruchs.ggtBerechnen(bruch1.getNenner(), bruch2.getNenner());

    //then
    assertThat(ggt, is(16));
  }
}
