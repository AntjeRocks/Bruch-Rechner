package domain;

import org.junit.Test;

public class BruchTest {

    @Test
    public void shouldGetGanzzahl() throws Exception {
        //given
        Bruch bruch = new Bruch(1,2,-8745);
        //when
        //then
        assert bruch.getGanzzahl()== -8745;
    }

    @Test
    public void shouldMultiply() throws Exception {
        //given
        Bruch bruch1 = new Bruch(15,1,2);
        Bruch bruch2 = new Bruch(1,6,1);
        //when
        Bruch produkt = bruch1.mul(bruch2);
        //then
        assert produkt.getZaehler() == 119;
        assert produkt.getNenner() == 6;
        assert produkt.getGanzzahl() == 0;
    }

    @Test
    public void shouldDivide() throws Exception {
        //given
        Bruch bruch1 = new Bruch(1,4,0);
        Bruch bruch2 = new Bruch(1,2,0);
        //when
        Bruch produkt = bruch1.div(bruch2);
        //then
        assert produkt.getZaehler() == 1;
        assert produkt.getNenner() == 2;
        assert produkt.getGanzzahl() == 0;
    }
}
