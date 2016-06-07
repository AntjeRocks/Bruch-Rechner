package domain;

public class Bruch {
    int zaehler;
    int nenner;
    int ganzzahl;

    public Bruch() {
        zaehler = 0;
        nenner = 1;
        ganzzahl = 0;
    }

    public Bruch(final int zaehler,final int nenner, final int ganzzahl) {
        this.nenner = nenner;
        this.zaehler = zaehler;
        this.ganzzahl = ganzzahl;
    }

    public int getGanzzahl() {
        return ganzzahl;
    }
    public int getNenner() {
        return nenner;
    }
    public int getZaehler() {
        return zaehler;
    }

    /*public void setGanzzahl(final int ganzzahl) {
        this.ganzzahl = ganzzahl;
    }
    public void setNenner(final int nenner) {
        this.nenner = nenner;
    }
    public void setZaehler(final int zaehler) {
        this.zaehler = zaehler;
    }*/

    public String anzeigen() {
        return ganzzahl+" "+zaehler+"/"+nenner;
    }
    public String toString(){
        return "Ganzzahl: " + ganzzahl + "Zähler: " + zaehler + ", Nenner: " + nenner;
    }

    public Bruch mul(final Bruch faktor2) {
        final Bruch produkt = new Bruch();
        produkt.zaehler = this.zaehler*faktor2.zaehler;
        produkt.nenner = this.nenner*faktor2.nenner;
        final int normal = new Bruchs().testGGT(produkt);
        produkt.zaehler = produkt.zaehler/normal;
        produkt.nenner = produkt.nenner/normal;
        return produkt;
    }

    public Bruch div (final Bruch divisor) {
        final Bruch quotient = new Bruch();
        quotient.zaehler = this.zaehler*divisor.nenner;
        quotient.nenner = this.nenner*divisor.zaehler;
        final int normal = new Bruchs().testGGT(quotient);
        quotient.zaehler = quotient.zaehler/normal;
        quotient.nenner = quotient.nenner/normal;
        return quotient;
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
