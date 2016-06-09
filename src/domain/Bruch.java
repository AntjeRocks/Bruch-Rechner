package domain;

public class Bruch {

    private int ganzzahl;
    private int zaehler;
    private int nenner;

    public Bruch() {
        zaehler = 0;
        nenner = 1;
        ganzzahl = 0;
    }

    public Bruch(final int ganzzahl, final int zaehler, final int nenner) {
        this.nenner = nenner;
        this.zaehler = zaehler;
        this.ganzzahl = ganzzahl;
    }

    int getGanzzahl() {
        return ganzzahl;
    }

    int getNenner() {
        return nenner;
    }

    int getZaehler() {
        return zaehler;
    }

    public String anzeigen() {
        if (this.zaehler > 0) {
            return ganzzahl+" "+zaehler+"/"+nenner;
        } else if (this.zaehler <= 0) {
            return ganzzahl+ " 0/0";
        } else {
            return "Fehler";
        }

    }

    public String toString(){
        return "Ganzzahl: " + ganzzahl + "Zähler: " + zaehler + ", Nenner: " + nenner;
    }

    public Bruch mul(final Bruch faktor2) {
        final Bruch produkt = new Bruch(0, (((this.ganzzahl*this.nenner)+this.zaehler)*((faktor2.ganzzahl*faktor2.nenner)+faktor2.zaehler)),(this.nenner*faktor2.nenner));
        return new Bruch(0, (produkt.getZaehler()/new Bruchs().testGGT(produkt)),(produkt.getNenner()/new Bruchs().testGGT(produkt)));
    }

    public Bruch div (final Bruch divisor) {
        final Bruch kehrwehrt = new Bruch(divisor.getGanzzahl(), divisor.getNenner(),divisor.getZaehler());
        return this.mul(kehrwehrt);
    }

    public Bruch add (final Bruch summand) {
        final Bruch summe = new Bruch();
        final int kgv = new Bruchs().testKGV(this, summand);
        int multiBruch1 = 0;
        int multiBruch2 = 0;
        if (this.nenner != kgv && this.nenner < kgv) {
            multiBruch1 = kgv/this.nenner;
        } else if (this.nenner != kgv && this.nenner > kgv) {
            multiBruch1 = this.nenner/kgv;
        } else if (this.nenner == kgv) {
            multiBruch1 = 1;
        }
        if (summand.nenner != kgv && summand.nenner < kgv) {
            multiBruch2 = kgv/summand.nenner;
        } else if (summand.nenner != kgv && summand.nenner > kgv) {
            multiBruch2 = summand.nenner/kgv;
        } else if (summand.nenner == kgv) {
            multiBruch2 = 1;
        }
        summe.nenner = this.nenner*multiBruch1;
        summe.zaehler = (this.zaehler*multiBruch1) + (summand.zaehler*multiBruch2);
        final int normal = new Bruchs().testGGT(summe);
        summe.zaehler = summe.zaehler/normal;
        summe.nenner = summe.nenner/normal;
        return summe;
    }

    public Bruch sub (final Bruch subtrahend) {
        final Bruch differenz = new Bruch();
        final int kgv = new Bruchs().testKGV(this, subtrahend);
        int multiBruch1 = 0;
        int multiBruch2 = 0;
        if (this.nenner != kgv && this.nenner < kgv) {
            multiBruch1 = kgv/this.nenner;
        } else if (this.nenner != kgv && this.nenner > kgv) {
            multiBruch1 = this.nenner/kgv;
        } else if (this.nenner == kgv) {
            multiBruch1 = 1;
        }
        differenz.nenner = this.nenner*multiBruch1;
        if (subtrahend.nenner != kgv && subtrahend.nenner < kgv) {
            multiBruch2 = kgv/subtrahend.nenner;
        } else if (subtrahend.nenner != kgv && subtrahend.nenner > kgv) {
            multiBruch2 = this.nenner/kgv;
        } else if (subtrahend.nenner == kgv) {
            multiBruch2 = 1;
        }
        differenz.nenner = this.nenner*multiBruch1;
        differenz.zaehler = (this.zaehler*multiBruch1) - (subtrahend.zaehler*multiBruch2);

        final int normal = new Bruchs().testGGT(differenz);
        differenz.zaehler = differenz.zaehler/normal;
        differenz.nenner = differenz.nenner/normal;
        return differenz;
    }
}
