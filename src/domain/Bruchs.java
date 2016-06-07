package domain;

public class Bruchs {

    int testGGT(final Bruch bruch1) {
        int ggt = 1;
        if (bruch1.nenner == bruch1.zaehler) {
            ggt = bruch1.nenner;
        } else if (bruch1.nenner > bruch1.zaehler) {
            for (int i = bruch1.zaehler; i > 0; i--) {
                if (bruch1.nenner % i == 0 && bruch1.zaehler % i == 0 && ggt == 1) {
                    ggt = i;
                }
            }
        } else if (bruch1.zaehler > bruch1.nenner) {
            for (int i = bruch1.nenner; i > 0; i--) {
                if (bruch1.zaehler % i == 0 && bruch1.nenner % i == 0 && ggt == 1) {
                    ggt = i;
                }
            }
        }
        return ggt;
    }

    int testKGV(final Bruch bruch1, final Bruch bruch2) {
        int kgv = 1;
        if (bruch1.nenner == bruch2.nenner) {
            kgv = bruch1.nenner;
        } else if (bruch1.nenner < bruch2.nenner) {
            for (int i = bruch1.nenner; i < bruch2.nenner*bruch1.nenner; i++) {
                if (i % bruch1.nenner == 0 && i % bruch2.nenner == 0 && kgv == 1) {
                    kgv = i;
                }
            }
        } else if (bruch2.nenner < bruch1.nenner) {
            for (int i = bruch2.nenner; i < bruch1.nenner*bruch2.nenner; i++) {
                if (i % bruch2.nenner == 0 && i % bruch1.nenner == 0 && kgv == 1) {
                    kgv = i;
                }
            }
        }
        return kgv;
    }
    public Bruch zuGanzzahl (final Bruch bruch1) {
        int ganzzahl = 0;
        if (bruch1.zaehler < bruch1.nenner) {
            bruch1.ganzzahl = (ganzzahl + bruch1.ganzzahl);
        } else if (bruch1.zaehler >= bruch1.nenner) {
            ganzzahl = bruch1.zaehler/bruch1.nenner;
            bruch1.zaehler = (bruch1.zaehler - (ganzzahl*bruch1.nenner));
            bruch1.ganzzahl = (ganzzahl + bruch1.ganzzahl);
        }
        return bruch1;
    }
    public Bruch vonGanzzahl (final Bruch bruch1) {
        if (bruch1.ganzzahl >= 1) {
            final int multiplikator = bruch1.nenner;
            bruch1.zaehler = ((multiplikator*bruch1.ganzzahl)+bruch1.zaehler);
            bruch1.ganzzahl = 0;
        }
        return bruch1;
    }
}
