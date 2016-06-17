package domain;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BruchTest {

    @Test
    public void shouldGetGanzzahlPostiv() throws Exception {
        //given
        Bruch bruch = new Bruch(712,1,2);
        //when
        //then
        assertThat (bruch.getGanzzahl(), is (712));
    }

    @Test
    public void shouldGetGanzzahlNegtiv() throws Exception {
        //given
        Bruch bruch = new Bruch(-8745,1,2);
        //when
        //then
        assertThat (bruch.getGanzzahl(), is (-8745));
    }

    @Test
    public void shouldGetGanzzahlMultiply() throws Exception {
        //given
        Bruch bruch = new Bruch(3*7,1,2);
        //when
        //then
        assertThat (bruch.getGanzzahl(), is (21));
    }

    @Test
    public void shouldGetGanzzahlSub() throws Exception {
        //given
        Bruch bruch = new Bruch(7-3,1,2);
        //when
        //then
        assertThat (bruch.getGanzzahl(), is (4));
    }

    @Test
    public void shouldMultiplyPositiv() throws Exception {
        //given
        Bruch bruch1 = new Bruch(1,15,3);
        Bruch bruch2 = new Bruch(0,6,5);
        //when
        Bruch produkt = bruch1.mul(bruch2);
        //then
        assertThat (produkt.getGanzzahl(), is(0));
        assertThat (produkt.getZaehler(), is(36));
        assertThat (produkt.getNenner(), is(5));

    }

    @Test
    public void shouldMultiplyNegativ() throws Exception {
        //given
        Bruch bruch1 = new Bruch(2,-33,5);
        Bruch bruch2 = new Bruch(0,4,7);
        //when
        Bruch produkt = bruch1.mul(bruch2);
        //then
        assertThat (produkt.getGanzzahl(), is(0));
        assertThat (produkt.getZaehler(), is(-92));
        assertThat (produkt.getNenner(), is(35));
    }

    @Test
    public void shouldDivide() throws Exception {
        //given
        Bruch bruch1 = new Bruch(0,1,4);
        Bruch bruch2 = new Bruch(0,1,2);
        //when
        Bruch produkt = bruch1.div(bruch2);
        //then
        assertThat (produkt.getGanzzahl(), is (0));
        assertThat (produkt.getZaehler(), is (1));
        assertThat (produkt.getNenner(), is (2));
    }

    @Test
    public void shouldErweitern() throws Exception {
        //given
        Bruch bruch1 = new Bruch(5,7,6);
        Bruch bruch2 = new Bruch(3,1,55);
        //when
        Bruch b1ErweitertUmB2 = bruch1.erweitern(bruch2);
        Bruch b2ErweitertUmB1 = bruch2.erweitern(bruch1);
        //then
        assertThat(b1ErweitertUmB2.getGanzzahl(), is(5));
        assertThat(b1ErweitertUmB2.getZaehler(), is(385));
        assertThat(b1ErweitertUmB2.getNenner(), is(330));
        assertThat(b2ErweitertUmB1.getGanzzahl(), is(3));
        assertThat(b2ErweitertUmB1.getZaehler(), is(6));
        assertThat(b2ErweitertUmB1.getNenner(), is(330));
    }
}
