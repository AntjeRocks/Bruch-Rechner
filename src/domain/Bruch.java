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

    private int betragVon(int zahl) {
        if (zahl < 0) {
            zahl*=(-1);
        }
        return zahl;
    }

    public String toString(){
        return "Ganzzahl: " + ganzzahl + ", Zähler: " + zaehler + ", Nenner: " + nenner;
    }

    public Bruch mul(final Bruch faktor2) {
        final Bruch faktor1 = this.zuBruchUmwandeln();
        faktor2.zuBruchUmwandeln();
        final int zaehlerMultipliziert = faktor1.zaehler*faktor2.zaehler;
        final int nennerMultipliziert = faktor1.nenner*faktor2.nenner;
        final Bruch produkt = new Bruch(0, zaehlerMultipliziert, nennerMultipliziert);
        return produkt.kuerzen();
    }

    public Bruch div (final Bruch divisor) {
        final Bruch kehrwehrt = new Bruch(divisor.getGanzzahl(), divisor.getNenner(),divisor.getZaehler());
        return mul(kehrwehrt.kuerzen());
    }

    public Bruch add (final Bruch bruch2) {
        final Bruch summand1 = this.erweitern(bruch2);
        final Bruch summand2 = bruch2.erweitern(this);
        final int ganzzahlAddiert = summand1.getGanzzahl()+summand2.getGanzzahl();
        final int zaehlerAddiert = summand1.getZaehler()+summand2.getZaehler();
        final Bruch summe = new Bruch(ganzzahlAddiert, zaehlerAddiert, summand1.getNenner());
        return summe.kuerzen();
    }

    public Bruch sub (final Bruch bruch2) {
        final Bruch minuend = this.erweitern(bruch2);
        final Bruch subtrahend = bruch2.erweitern(this);
        final int ganzzahlSubtrahiert = minuend.getGanzzahl()-subtrahend.getGanzzahl();
        final int zaehlerSubtrahiert = minuend.getZaehler()-subtrahend.getZaehler();
        final Bruch differenz = new Bruch(ganzzahlSubtrahiert, zaehlerSubtrahiert, minuend.getNenner());
        return differenz.kuerzen();
    }

    public Bruch kuerzen() {
        final int gekürzterZaehler = (zaehler)/new Bruchs().testGGT(zaehler,nenner);
        final int gekürzterNenner = nenner/new Bruchs().testGGT(zaehler,nenner);
        return new Bruch(ganzzahl,gekürzterZaehler,gekürzterNenner);
    }

    public Bruch erweitern(final Bruch bruch2) {
        final int kgv = new Bruchs().testKGV(nenner, bruch2.getNenner());
        return new Bruch(this.getGanzzahl(),(kgv/nenner)*zaehler,kgv);
    }

    public Bruch zuGanzzahlUmwandeln() {
        int ueberhang = 0;
        if (betragVon(zaehler) < nenner) {
            return this;
        } else if (betragVon(zaehler) == nenner) {
            return new Bruch(ganzzahl+1,0,nenner);
        } else for (int i = betragVon(zaehler); i > 0; i--) {
            if (i % nenner == 0 && ueberhang == 0) {
                ueberhang = i;
            }
        }
        return new Bruch(ganzzahl+(ueberhang/nenner), zaehler-ueberhang, nenner);
    }

    public Bruch zuBruchUmwandeln() {
        if (ganzzahlNichtNull()) {
            return new Bruch(0,(ganzzahl*nenner)+zaehler,nenner);
        } else {
            return this;
        }
    }

}
