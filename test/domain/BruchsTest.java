package domain;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BruchsTest {

    @Test
    public void shouldGetGGTOfOne() {
        //given
        Bruch bruch1 = new Bruch(1,1,11);
        Bruch bruch2 = new Bruch(1,1,23);
        //when
        int ggt = new Bruchs().testGGT(bruch1.getNenner(),bruch2.getNenner());
        //then
        assertThat(ggt, is(1));
    }

    @Test
    public void shouldGetGGT() {
        //given
        Bruch bruch1 = new Bruch(1,5,144);
        Bruch bruch2 = new Bruch(1,77,16);
        //when
        int ggt = new Bruchs().testGGT(bruch1.getNenner(),bruch2.getNenner());
        //then
        assertThat(ggt, is(16));
    }

}