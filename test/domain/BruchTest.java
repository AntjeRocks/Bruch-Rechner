package domain;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BruchTest {

    @Test
    public void shouldGetGanzzahl() throws Exception {
        //given
        Bruch bruch = new Bruch(-8745, 1, 2);

        //when

        //then
        assertThat(bruch.getGanzzahl(), is(-8745));
    }

    @Test
    public void shouldMultiply() throws Exception {
        //given
        Bruch bruch1 = new Bruch(2, 15, 1);
        Bruch bruch2 = new Bruch(1, 1, 6);

        //when
        Bruch produkt = bruch1.mul(bruch2);

        //then
        assertThat(produkt.getZaehler(), is(119));
        assertThat(produkt.getNenner(), is(6));
        assertThat(produkt.getGanzzahl(), is(0));
    }

    @Test
    public void shouldDivide() throws Exception {
        //given
        Bruch bruch1 = new Bruch(0, 1, 4);
        Bruch bruch2 = new Bruch(0, 1, 2);

        //when
        Bruch produkt = bruch1.div(bruch2);

        //then
        assertThat(produkt.getZaehler(), is(1));
        assertThat(produkt.getNenner(), is(2));
        assertThat(produkt.getGanzzahl(), is(0));
    }
}
