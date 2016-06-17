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

    private int wertVon (int zahl) {
        if (zahl < 0) {
            zahl*=(-1);
        }
        return zahl;
    }

    private Bruch kuerzen() {
        final int gekürzterZaehler = (zaehler)/new Bruchs().testGGT(zaehler,nenner);
        final int gekürzterNenner = nenner/new Bruchs().testGGT(zaehler,nenner);
        return new Bruch(ganzzahl,gekürzterZaehler,gekürzterNenner);
    }

    private Bruch erweitern(final Bruch bruch2) {
        final int kgv = new Bruchs().testKGV(nenner, bruch2.getNenner());
        return new Bruch(this.getGanzzahl(),(kgv/nenner)*zaehler,kgv);
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

    public Bruch add (final Bruch bruch2) {
        final Bruch summand1 = this.erweitern(bruch2);
        final Bruch summand2 = bruch2.erweitern(this);
        final Bruch summe = new Bruch(summand1.getGanzzahl()+summand2.getGanzzahl(),summand1.getZaehler()+summand2.getZaehler(),summand1.getNenner());
        return summe.kuerzen();
    }

    public Bruch sub (final Bruch bruch2) {
        final Bruch minuend = this.erweitern(bruch2);
        final Bruch subtrahend = bruch2.erweitern(this);
        final Bruch differenz = new Bruch(minuend.getGanzzahl()-subtrahend.getGanzzahl(),minuend.getZaehler()-subtrahend.getZaehler(),minuend.getNenner());
        return differenz.kuerzen();
    }

    public Bruch zuGanzzahlUmwandeln() {
        int ueberhang = 0;
        if (this.wertVon(zaehler) < nenner) {
            return this;
        } else if (this.wertVon(zaehler) == nenner) {
            return new Bruch(ganzzahl+1,0,nenner);
        } else for (int i = this.wertVon(zaehler); i > 0; i--) {
            if (i % nenner == 0 && ueberhang == 0) {
                ueberhang = i;
            }
        }
        return new Bruch(ganzzahl+(ueberhang/nenner), zaehler-ueberhang, nenner);
    }

    public Bruch zuBruchUmwandeln() {
        if (this.getGanzzahl() != 0) {
            return new Bruch(0,(ganzzahl*nenner)+zaehler,nenner);
        } else {
            return this;
        }
    }

}
