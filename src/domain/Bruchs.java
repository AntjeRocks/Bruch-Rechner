package domain;

public final class Bruchs {

  public static int betragVon(int zahl) {
    if (zahl < 0) {
      zahl *= (-1);
    }
    return zahl;
  }

  public static int testGGT(final int zahl1, final int zahl2) {
    int ggt = 0;
    if (betragVon(zahl1) == betragVon(zahl2)) {
      ggt = zahl1;
    } else if (betragVon(zahl1) < betragVon(zahl2)) {
      for (int i = betragVon(zahl1); i > 0; i--) {
        if (zahl1 % i == 0 && zahl2 % i == 0 && ggt == 0) {
          ggt = i;
        }
      }
    } else if (betragVon(zahl2) < betragVon(zahl1)) {
      for (int i = betragVon(zahl2); i > 0; i--) {
        if (zahl1 % i == 0 && zahl2 % i == 0 && ggt == 0) {
          ggt = i;
        }
      }
    }
    return ggt;
  }

  public static int testKGV(final int zahl1, final int zahl2) {
    final int kgv = (zahl1 * zahl2) / testGGT(zahl1, zahl2);
    if (kgv > 0) {
      return kgv;
    } else {
      return kgv * (-1);
    }
  }
}
