package domain;

public class Bruchs {

    int testGGT(int zahl1, int zahl2) {
        if (zahl1 < 0) {
            zahl1 = zahl1*(-1);
        }
        if (zahl2 < 0) {
            zahl2 = zahl2*(-1);
        }
        int ggt = 1337;
        if (zahl1 == zahl2) {
            ggt = zahl1;
        } else if (zahl1 < zahl2) {
            for (int i = zahl1; i > 0; i--) {
                if (zahl1 % i == 0 && zahl2 % i == 0 && ggt == 1337) {
                    ggt = i;
                }
            }
        } else if (zahl2 < zahl1) {
            for (int i = zahl2; i > 0; i--) {
                if (zahl1 % i == 0 && zahl2 % i == 0 && ggt == 1337) {
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