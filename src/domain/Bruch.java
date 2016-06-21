package domain;

public class Bruch {

  private final int ganzzahl;
  private final int zaehler;
  private final int nenner;

  public Bruch() {
    ganzzahl = 0;
    zaehler = 0;
    nenner = 1;
  }

  public Bruch(final int ganzzahl, final int zaehler, final int nenner) {
    this.ganzzahl = ganzzahl;
    this.zaehler = zaehler;
    this.nenner = nenner;
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

  public String anzeigen() {
    if (!ganzzahlIstNull() && !zaehlerIstNull()) {
      return ganzzahl + " " + zaehler + "/" + nenner;
    } else if (ganzzahlIstNull() && !zaehlerIstNull()) {
      return zaehler + "/" + nenner;
    } else if ((!ganzzahlIstNull()) || ganzzahlIstNull()) {
      return String.valueOf(ganzzahl);
    } else {
      return "Fehler";
    }
  }

  public String toString() {
        return "Ganzzahl: " + ganzzahl + ", Zähler: " + zaehler + ", Nenner: " + nenner;
    }

  private boolean ganzzahlIstNull() {
    return ganzzahl == 0;
  }

  private boolean zaehlerIstNull() { return zaehler == 0; }

  public Bruch mul(final Bruch faktor2) {
    final Bruch faktor1 = this.zuBruchUmwandeln();
    faktor2.zuBruchUmwandeln();
    final int zaehlerMultipliziert = faktor1.zaehler * faktor2.zaehler;
    final int nennerMultipliziert = faktor1.nenner * faktor2.nenner;
    final Bruch produkt = new Bruch(0, zaehlerMultipliziert, nennerMultipliziert);
    return produkt.kuerzen();
  }

  public Bruch div(final Bruch divisor) {
    final Bruch kehrwehrt = new Bruch(divisor.getGanzzahl(), divisor.getNenner(), divisor.getZaehler());
    return mul(kehrwehrt.kuerzen());
  }

  public Bruch add(final Bruch bruch2) {
    final Bruch summand1 = zuBruchUmwandeln().erweitern(bruch2);
      System.out.println("erweitert summand 1: "+summand1);
    final Bruch summand2 = bruch2.zuBruchUmwandeln().erweitern(this);
      System.out.println("erweitert summand 2: "+summand2);
    final int zaehlerAddiert = summand1.getZaehler() + summand2.getZaehler();
    final Bruch summe = new Bruch(0, zaehlerAddiert, summand1.getNenner());
    return summe.kuerzen();
  }

  public Bruch sub(final Bruch bruch2) {
    final Bruch minuend = this.erweitern(bruch2);
    final Bruch subtrahend = bruch2.erweitern(this);
    final int ganzzahlSubtrahiert = minuend.getGanzzahl() - subtrahend.getGanzzahl();
    final int zaehlerSubtrahiert = minuend.getZaehler() - subtrahend.getZaehler();
    final Bruch differenz = new Bruch(ganzzahlSubtrahiert, zaehlerSubtrahiert, minuend.getNenner());
    return differenz.kuerzen();
  }

  public Bruch kuerzen() {
      int gekürzterZaehler = (zaehler) / Bruchs.ggtBerechnen(zaehler, nenner);
      final int gekürzterNenner = nenner / Bruchs.ggtBerechnen(zaehler, nenner);
      if (ganzzahl < 0 && zaehler < 0) {
          System.out.println("ganzzahl und zaehler negativ");
      }
      return new Bruch(ganzzahl, gekürzterZaehler, gekürzterNenner);
  }

  public Bruch erweitern(final Bruch bruch2) {
    final int kgv = Bruchs.kgvBerechnen(nenner, bruch2.getNenner());
    return new Bruch(this.getGanzzahl(), (kgv / nenner) * zaehler, kgv);
  }

    public Bruch zuGanzzahlUmwandeln() {
        int ueberhang = 0;
        if (Bruchs.betragVon(zaehler) < Bruchs.betragVon(nenner)) {
            return this;
        } else if (Bruchs.betragVon(zaehler) == Bruchs.betragVon(nenner) && ganzzahl > 0) {
            return new Bruch(ganzzahl + 1, 0, nenner);
        } else if (Bruchs.betragVon(zaehler) == Bruchs.betragVon(nenner) && ganzzahl < 0) {
            return new Bruch(ganzzahl - 1, 0, nenner);
        } else {
            for (int i = Bruchs.betragVon(zaehler); i > 0; i--) {
                if (i % nenner == 0 && ueberhang == 0) {
                    ueberhang = i;
                }
            }
        }
        if (ganzzahl > 0 && zaehler > 0) {
            return new Bruch(ganzzahl + (ueberhang / nenner), zaehler - ueberhang, nenner);
        } else if (ganzzahl > 0 && zaehler < 0) {
            return new Bruch(ganzzahl + (ueberhang / nenner), zaehler + ueberhang, nenner);
        } else if (ganzzahl < 0 && zaehler > 0) {
            return new Bruch(ganzzahl - (ueberhang / nenner), zaehler - ueberhang, nenner);
        } else {
            return new Bruch(ganzzahl - (ueberhang / nenner), zaehler + ueberhang, nenner);
        }
    }

  public Bruch zuBruchUmwandeln() {
    if (!ganzzahlIstNull()) {
      return new Bruch(0, (ganzzahl * nenner) + zaehler, nenner);
    } else {
      return this;
    }
  }
}
