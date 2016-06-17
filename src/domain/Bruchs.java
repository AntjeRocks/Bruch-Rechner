package domain;

public class Bruchs {

    private int wertVon (int zahl) {
        if (zahl < 0) {
            zahl*=(-1);
        }
        return zahl;
    }

    int testGGT(int zahl1, int zahl2) {
        int ggt = 0;
        if (this.wertVon(zahl1) == this.wertVon(zahl2)) {
            ggt = zahl1;
        } else if (this.wertVon(zahl1) < this.wertVon(zahl2)) {
            for (int i = this.wertVon(zahl1); i > 0; i--) {
                if (zahl1 % i == 0 && zahl2 % i == 0 && ggt == 0) {
                    ggt = i;
                }
            }
        } else if (this.wertVon(zahl2) < this.wertVon(zahl1)) {
            for (int i = this.wertVon(zahl2); i > 0; i--) {
                if (zahl1 % i == 0 && zahl2 % i == 0 && ggt == 0) {
                    ggt = i;
                }
            }
        } return ggt;
    }

    int testKGV(final int zahl1, final int zahl2) {
        int kgv = (zahl1*zahl2)/testGGT(zahl1,zahl2);
        if (kgv > 0) {
            return kgv;
        } else return kgv*(-1);
    }
}