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

    private Bruch kuerzen() {
        final int gekürzterZaehler = (zaehler)/new Bruchs().testGGT(zaehler,nenner);
        final int gekürzterNenner = nenner/new Bruchs().testGGT(zaehler,nenner);
        return new Bruch(ganzzahl,gekürzterZaehler,gekürzterNenner);
    }

    private Bruch erweitern(final Bruch bruch2) {
        return new Bruch(this.getGanzzahl(),zaehler*bruch2.getNenner(),nenner*bruch2.getNenner());
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
        int zaehlerneu = 0, ganzzahlneu = 0, nenneralt = 0;
        if (this.getZaehler() < this.getNenner()) {
            ganzzahlneu = (ganzzahlneu + this.getGanzzahl());
            zaehlerneu = this.getZaehler();
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

}
