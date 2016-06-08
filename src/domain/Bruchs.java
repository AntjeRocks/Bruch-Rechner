package domain;

public class Bruchs {

    int testGGT(final Bruch bruch1) {
        int ggt = 1;
        if (bruch1.getNenner() == bruch1.getZaehler()) {
            ggt = bruch1.getNenner();
        } else if (bruch1.getNenner() > bruch1.getZaehler()) {
            for (int i = bruch1.getZaehler(); i > 0; i--) {
                if (bruch1.getNenner() % i == 0 && bruch1.getZaehler()% i == 0 && ggt == 1) {
                    ggt = i;
                }
            }
        } else if (bruch1.getZaehler() > bruch1.getNenner()) {
            for (int i = bruch1.getNenner(); i > 0; i--) {
                if (bruch1.getZaehler() % i == 0 && bruch1.getNenner()% i == 0 && ggt == 1) {
                    ggt = i;
                }
            }
        }
        return ggt;
    }

    int testKGV(final Bruch bruch1, final Bruch bruch2) {
        int kgv = 1;
        if (bruch1.getNenner() == bruch2.getNenner()) {
            kgv = bruch1.getNenner();
        } else if (bruch1.getNenner() < bruch2.getNenner()) {
            for (int i = bruch1.getNenner(); i < bruch2.getNenner()*bruch1.getNenner(); i++) {
                if (i % bruch1.getNenner() == 0 && i % bruch2.getNenner() == 0 && kgv == 1) {
                    kgv = i;
                }
            }
        } else if (bruch2.getNenner() < bruch1.getNenner()) {
            for (int i = bruch2.getNenner(); i < bruch1.getNenner()*bruch2.getNenner(); i++) {
                if (i % bruch2.getNenner() == 0 && i % bruch1.getNenner() == 0 && kgv == 1) {
                    kgv = i;
                }
            }
        }
        return kgv;
    }

    public Bruch zuGanzzahl (final Bruch bruch1) {
        int zaehlerneu = 0, ganzzahlneu = 0, nenneralt = 0;
        if (bruch1.getZaehler() < bruch1.getNenner()) {
            ganzzahlneu = (ganzzahlneu + bruch1.getGanzzahl());
            zaehlerneu = bruch1.getZaehler();
            nenneralt = bruch1.getNenner();
        } else if (bruch1.getZaehler() >= bruch1.getNenner()) {
            ganzzahlneu = bruch1.getZaehler()/bruch1.getNenner();
            ganzzahlneu = (ganzzahlneu + bruch1.getGanzzahl());
            zaehlerneu = (bruch1.getZaehler() - (ganzzahlneu*bruch1.getNenner()));
            nenneralt = bruch1.getNenner();
        }
        return new Bruch(zaehlerneu, nenneralt, ganzzahlneu);
    }

    public Bruch vonGanzzahl (final Bruch bruch1) {
        int zaehlerneu = 0, ganzzahlneu = 0, nenneralt = 0;
        if (bruch1.getGanzzahl() >= 1) {
            final int multiplikator = bruch1.getNenner();
            ganzzahlneu = 0;
            zaehlerneu = ((multiplikator*bruch1.getGanzzahl())+bruch1.getZaehler());
            nenneralt = bruch1.getNenner();
        }
        return new Bruch(zaehlerneu,nenneralt,ganzzahlneu);
    }
}
