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
    final Bruch summand2 = bruch2.zuBruchUmwandeln().erweitern(this);
    final int zaehlerAddiert = summand1.getZaehler() + summand2.getZaehler();
    final Bruch summe = new Bruch(0, zaehlerAddiert, summand1.getNenner());
    return summe.kuerzen();
  }

  public Bruch sub(final Bruch bruch2) {
    final Bruch minuend = zuBruchUmwandeln().erweitern(bruch2);
    final Bruch subtrahend = bruch2.zuBruchUmwandeln().erweitern(this);
    final int ganzzahlSubtrahiert = minuend.getGanzzahl() - subtrahend.getGanzzahl();
    final int zaehlerSubtrahiert = minuend.getZaehler() - subtrahend.getZaehler();
    final Bruch differenz = new Bruch(ganzzahlSubtrahiert, zaehlerSubtrahiert, minuend.getNenner());
    return differenz.kuerzen();
  }

  public Bruch kuerzen() {
      int gekGanzzahl = 0;
      int gekZaehler = 0;
      int zaehlerErweitert = (ganzzahl * nenner) + (zaehler);

      if (Bruchs.betragVon(zaehlerErweitert) > nenner) {
          gekGanzzahl = zaehlerErweitert / nenner;
          gekZaehler = zaehlerErweitert - gekGanzzahl * nenner;

      } else if (Bruchs.betragVon(zaehlerErweitert) == nenner) {

      }
            
      int gekZaehler = zaehlerErweitert / Bruchs.ggtBerechnen(zaehlerErweitert, nenner);
      int gekNenner = nenner / Bruchs.ggtBerechnen(zaehlerErweitert, nenner);
      return new Bruch(0, gekZaehler, gekNenner);



  }

  private Bruch getKuerzung(final int ganzMul, final int zaehMul) {
        int gekGanzzahl = ganzzahl*(ganzMul);
        int gekZaehler = (zaehler*(zaehMul) / Bruchs.ggtBerechnen(zaehler, nenner));
        int gekNenner = nenner / Bruchs.ggtBerechnen(zaehler, nenner);
        return new Bruch(gekGanzzahl, gekZaehler, gekNenner);
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
        if (ganzzahl >= 0 && zaehler > 0) {
            return new Bruch(ganzzahl + (ueberhang / nenner), zaehler - ueberhang, nenner);
        } else if (ganzzahl >= 0 && zaehler < 0) {
            return new Bruch((ganzzahl + (ueberhang / nenner)*(-1)), ((zaehler + ueberhang)*(-1)), nenner);
        } else if (ganzzahl < 0 && zaehler > 0) {
            return new Bruch(ganzzahl - (ueberhang / nenner), zaehler - ueberhang, nenner);
        } else {
            return new Bruch(ganzzahl - (ueberhang / nenner), zaehler + ueberhang, nenner);
        }
    }

  public Bruch zuBruchUmwandeln() {
    if ((ganzzahl > 0 && zaehler > 0) || (ganzzahl < 0 && zaehler < 0)) {
      return new Bruch(0, (ganzzahl * nenner) + zaehler, nenner);
    } else if (ganzzahl > 0 && zaehler < 0) {
        return new Bruch(0, (ganzzahl * nenner * (-1)) - zaehler, nenner);
    } else if (ganzzahl < 0 && zaehler > 0) {
        return new Bruch(0, (ganzzahl * nenner) - zaehler, nenner);
    } else {
      return this;
    }
  }
}
