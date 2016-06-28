package domain;

public final class Bruch {

  private final int ganzzahl;
  private final int zaehler;
  private final int nenner;

  public Bruch() {
    this(0,0,1);
  }

  public Bruch (final int ganzzahl, final int zaehler, final int nenner) {
    this.ganzzahl = ganzzahl;
    this.zaehler = zaehler;
    this.nenner = nenner;
      if (nenner == 0) {
          throw new IllegalArgumentException("Der Nenner ist Null");
      }
      if (nenner < 0) {
          throw new IllegalArgumentException("Der Nenner ist negativ");
      }
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

  // In der Aufgabe sollen setter erstellt werden. Diese sind immutable (not changeable)

  public String anzeigen() {
    if (!ganzzahlIstNull() && !zaehlerIstNull()) {
      return ganzzahl + " " + zaehler + "/" + nenner;
    } else if (ganzzahlIstNull() && !zaehlerIstNull()) {
      return zaehler + "/" + nenner;
    } else {
      return String.valueOf(ganzzahl);
    }
  }

  public String toString() {
        return "Ganzzahl: " + ganzzahl + ", Zähler: " + zaehler + ", Nenner: " + nenner;
    }

  public Bruch mul(Bruch bruch2) {
    final Bruch faktor1 = zuBruchUmwandeln();
    final Bruch faktor2 = bruch2.zuBruchUmwandeln();
    final int zaehlerMultipliziert = faktor1.getZaehler() * faktor2.getZaehler();
    final int nennerMultipliziert = faktor1.getNenner() * faktor2.getNenner();
    final Bruch produkt = new Bruch(0, zaehlerMultipliziert, nennerMultipliziert);
    return produkt.kuerzen();
  }

  public Bruch div(final Bruch divisor) {
    return mul(divisor.kehrwert());
  }

  private Bruch kehrwert() {
    return new Bruch(ganzzahl,nenner,zaehler);
  }

  public Bruch add(final Bruch bruch2) {
    final Bruch summand1 = zuBruchUmwandeln().erweitern(bruch2);
    final Bruch summand2 = bruch2.zuBruchUmwandeln().erweitern(this);
    final int zaehlerAddiert = summand1.getZaehler() + summand2.getZaehler();
    final Bruch summe = new Bruch(0, zaehlerAddiert, summand1.getNenner());
    return summe.kuerzen();
  }

  public Bruch sub(final Bruch subtrahend) {
    final Bruch differenz = add(subtrahend.zaehlerNegieren());
    return differenz.kuerzen();
  }

  private Bruch zaehlerNegieren() {
    return new Bruch(ganzzahl, zaehler*(-1), nenner);
  }

  public Bruch kuerzen() {
      int zaehlerErweitert = (ganzzahl * nenner) + (zaehler);
      int gekZaehler = zaehlerErweitert / Bruchs.ggtBerechnen(zaehlerErweitert, nenner);
      int gekNenner = nenner / Bruchs.ggtBerechnen(zaehlerErweitert, nenner);
      return new Bruch(0, gekZaehler, gekNenner);
  }

  public Bruch erweitern(final Bruch bruch2) {
    final int kgv = Bruchs.kgvBerechnen(nenner, bruch2.getNenner());
    return new Bruch(ganzzahl, (kgv / nenner) * zaehler, kgv);
  }

  public Bruch zuGanzzahlUmwandeln() {
    final Bruch dieser = zuBruchUmwandeln();

    if (Bruchs.betragVon(dieser.getZaehler()) < Bruchs.betragVon(dieser.getNenner())) {
      return this;
    } else {
      int gekuerzterAnteil = kuerzbarenAnteilBerechnen(Bruchs.betragVon(dieser.getZaehler()));
      if (dieser.getZaehler() >= 0) {
        return new Bruch(gekuerzterAnteil / dieser.getNenner(), dieser.getZaehler() - gekuerzterAnteil, dieser.getNenner());
      } else {
        return new Bruch(gekuerzterAnteil / dieser.getNenner() * -1, dieser.getZaehler() + gekuerzterAnteil, dieser.getNenner());
      }
    }
  }

  private int kuerzbarenAnteilBerechnen(int betragVonZaehler) {
    int kuerzbarerAnteil = 0;
    for (int i = Bruchs.betragVon(betragVonZaehler); i > 0; i--) {
      if (i % nenner == 0 && kuerzbarerAnteil == 0) {
        kuerzbarerAnteil = i;
      }
    }
    return kuerzbarerAnteil;
  }

  public Bruch zuBruchUmwandeln() {
      return new Bruch(0, (ganzzahl * nenner) + zaehler, nenner);
  }

  private boolean ganzzahlIstNull() {
      return ganzzahl == 0;
  }

  private boolean zaehlerIstNull() {
      return zaehler == 0;
  }
}
