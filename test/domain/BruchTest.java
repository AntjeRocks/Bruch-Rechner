package domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class BruchTest {

    @Test
    public void shouldGetGanzzahlPostiv() throws Exception {
        // given
        final Bruch bruch = new Bruch(712, 1, 2);

        // when

        // then
        assertThat(bruch.getGanzzahl(), is(712));
    }

    @Test
    public void shouldGetGanzzahlNegtiv() throws Exception {
        // given
        final Bruch bruch = new Bruch(-8745, 1, 2);

        // when

        // then
        assertThat(bruch.getGanzzahl(), is(-8745));
    }

    @Test
    public void shouldGetGanzzahlMultiply() throws Exception {
        // given
        final Bruch bruch = new Bruch(3 * 7, 1, 2);

        // when

        // then
        assertThat(bruch.getGanzzahl(), is(21));
    }

    @Test
    public void shouldGetGanzzahlSub() throws Exception {
        // given
        final Bruch bruch = new Bruch(7 - 3, 1, 2);

        // when

        // then
        assertThat(bruch.getGanzzahl(), is(4));
    }

    @Test
    public void shouldMultiplyPositiv() throws Exception {
        // given
        final Bruch bruch1 = new Bruch(1, 15, 3);
        final Bruch bruch2 = new Bruch(0, 6, 5);

        // when
        final Bruch produkt = bruch1.mul(bruch2);

        // then
        assertThat(produkt.getGanzzahl(), is(0));
        assertThat(produkt.getZaehler(), is(36));
        assertThat(produkt.getNenner(), is(5));
    }

    @Test
    public void shouldMultiplyNegativ() throws Exception {
        // given
        final Bruch bruch1 = new Bruch(2, -33, 5);
        final Bruch bruch2 = new Bruch(0, 4, 7);

        // when
        final Bruch produkt = bruch1.mul(bruch2);

        // then
        assertThat(produkt.getGanzzahl(), is(0));
        assertThat(produkt.getZaehler(), is(-92));
        assertThat(produkt.getNenner(), is(35));
    }

    @Test
    public void shouldDivide() throws Exception {
        // given
        final Bruch bruch1 = new Bruch(0, 1, 4);
        final Bruch bruch2 = new Bruch(0, 1, 2);

        // when
        final Bruch produkt = bruch1.div(bruch2);

        // then
        assertThat(produkt.getGanzzahl(), is(0));
        assertThat(produkt.getZaehler(), is(1));
        assertThat(produkt.getNenner(), is(2));
    }

    @Test
    public void shouldAdd() throws Exception {
        // given
        Bruch bruch1 = new Bruch(-6, 1, 4);
        Bruch bruch2 = new Bruch(0, 1, 2);

        // when
        final Bruch ergebnis = bruch1.add(bruch2);

        // then
        assertThat(ergebnis.getGanzzahl(), is(0));
        assertThat(ergebnis.getZaehler(), is(-21));
        assertThat(ergebnis.getNenner(), is(4));
    }

    @Test
    public void shouldSubtract() throws Exception {
        // given
        Bruch bruch1 = new Bruch(-6, 1, 4);
        Bruch bruch2 = new Bruch(0, 1, 2);

        // when
        final Bruch ergebnis = bruch1.sub(bruch2);

        // then
        assertThat(ergebnis.getGanzzahl(), is(0));
        assertThat(ergebnis.getZaehler(), is(-25));
        assertThat(ergebnis.getNenner(), is(4));
    }

    @Test
    public void shouldErweitern() throws Exception {
        // given
        final Bruch bruch1 = new Bruch(5, 7, 6);
        final Bruch bruch2 = new Bruch(3, 1, 55);

        // when
        final Bruch b1ErweitertUmB2 = bruch1.erweitern(bruch2);
        final Bruch b2ErweitertUmB1 = bruch2.erweitern(bruch1);

        // then
        assertThat(b1ErweitertUmB2.getGanzzahl(), is(5));
        assertThat(b1ErweitertUmB2.getZaehler(), is(385));
        assertThat(b1ErweitertUmB2.getNenner(), is(330));
        assertThat(b2ErweitertUmB1.getGanzzahl(), is(3));
        assertThat(b2ErweitertUmB1.getZaehler(), is(6));
        assertThat(b2ErweitertUmB1.getNenner(), is(330));
    }

    @Test
    public void shouldUmwandelnZuGanzzahl() throws Exception {
        // given
        Bruch bruch1 = new Bruch(0,-25,4);
        Bruch bruch2 = new Bruch(0,-23,4);
        Bruch bruch3 = new Bruch(0,-21,4);

        // when
        Bruch testi1 = bruch1.zuGanzzahlUmwandeln();
        Bruch testi2 = bruch2.zuGanzzahlUmwandeln();
        Bruch testi3 = bruch3.zuGanzzahlUmwandeln();

        // then
        assertThat(testi1.getGanzzahl(), is(-6));
        assertThat(testi1.getZaehler(), is(1));
        assertThat(testi1.getNenner(), is(4));
        assertThat(testi2.getGanzzahl(), is(-5));
        assertThat(testi2.getZaehler(), is(3));
        assertThat(testi2.getNenner(), is(4));
        assertThat(testi3.getGanzzahl(), is(-5));
        assertThat(testi3.getZaehler(), is(1));
        assertThat(testi3.getNenner(), is(4));
    }

    @Test
    public void shouldUmwandelnZuBruch() throws Exception {
        // given
        Bruch bruch1 = new Bruch(2,1,2);
        Bruch bruch2 = new Bruch(-2,-1,2);

        // when
        Bruch testi1 = bruch1.zuBruchUmwandeln();
        Bruch testi2 = bruch2.zuBruchUmwandeln();

        // then
        assertThat(testi1.getGanzzahl(), is(0));
        assertThat(testi1.getZaehler(), is(5));
        assertThat(testi1.getNenner(), is(2));
        assertThat(testi2.getGanzzahl(), is(0));
        assertThat(testi2.getZaehler(), is(-5));
        assertThat(testi2.getNenner(), is(2));
    }
}
