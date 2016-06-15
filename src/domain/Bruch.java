package domain;

public class Bruch {

    private int ganzzahl;
    private int zaehler;
    private int nenner;

    public Bruch() {
        ganzzahl = 0;
        zaehler = 0;
        nenner = 1;
    }

    public Bruch (final int ganzzahl, final int zaehler, final int nenner) {
        this.ganzzahl = ganzzahl;
        this.zaehler = zaehler;
        this.nenner = nenner;
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
        if (ganzzahlNichtNull() && zaehlerNichtNull()) {
            return ganzzahl + " " + zaehler + "/" + nenner;
        } else if (ganzzahlIstNull() && zaehlerNichtNull()) {
            return zaehler + "/" + nenner;
        } else if ((ganzzahlNichtNull()) || ganzzahlIstNull()) {
            return String.valueOf(ganzzahl);
        } else {
            return "Fehler";
        }
    }

    private boolean ganzzahlIstNull() {
        return ganzzahl == 0;
    }

    private boolean zaehlerNichtNull() {
        return zaehler != 0;
    }

    private boolean ganzzahlNichtNull() {
        return ganzzahl != 0;
    }

    public String toString(){
        return "Ganzzahl: " + ganzzahl + ", Zähler: " + zaehler + ", Nenner: " + nenner;
    }

    public Bruch mul(final Bruch faktor2) {
        final Bruch faktor1 = this.zuBruchUmwandeln();
        faktor2.zuBruchUmwandeln();
        final Bruch produkt = new Bruch(0,(faktor1.zaehler*faktor2.zaehler),(faktor1.nenner*faktor2.nenner));
        return produkt.kuerzen();
    }

    public Bruch div (final Bruch divisor) {
        final Bruch kehrwehrt = new Bruch(divisor.getGanzzahl(), divisor.getNenner(),divisor.getZaehler());
        return mul(kehrwehrt.kuerzen());
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

    public Bruch zuGanzzahlUmwandeln() {
        int zaehlerneu = 0, ganzzahlneu = 0, nenneralt = 0;
        if (this.getZaehler() < this.getNenner()) {
            ganzzahlneu = (ganzzahlneu + this.getGanzzahl());
            zaehlerneu = this.getZaehler();
            System.out.println(zaehlerneu);
            nenneralt = this.getNenner();
        } else if (this.getZaehler() >= this.getNenner()) {
            ganzzahlneu = this.getZaehler()/this.getNenner();
            ganzzahlneu = (ganzzahlneu + this.getGanzzahl());
            zaehlerneu = (this.getZaehler() - (ganzzahlneu*this.getNenner()));
            nenneralt = this.getNenner();
        }
        return new Bruch(ganzzahlneu, zaehlerneu, nenneralt);
    }

    public Bruch zuBruchUmwandeln() {
        int zaehlerneu = 0, ganzzahlneu = 0, nenneralt = 0;
        if (this.getGanzzahl() != 0) {
            final int multiplikator = this.getNenner();
            ganzzahlneu = 0;
            zaehlerneu = ((multiplikator*this.getGanzzahl())+this.getZaehler());
            nenneralt = this.getNenner();
        } else {
            ganzzahlneu = this.getGanzzahl();
            zaehlerneu = this.getZaehler();
            nenneralt = this.getNenner();
        }
        return new Bruch(ganzzahlneu, zaehlerneu,nenneralt);
    }

    public Bruch kuerzen() {
        final int gekürzterZaehler = zaehler/new Bruchs().testGGT(this);
        final int gekürzterNenner = nenner/new Bruchs().testGGT(this);
        return new Bruch(ganzzahl,gekürzterZaehler,gekürzterNenner);
    }
}
