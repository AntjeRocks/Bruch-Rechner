package domain;

public final class Bruchs {

  private Bruchs() {
    super();
  }

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
        ggt = getGgt(zahl1, zahl2, ggt);
    } else if (betragVon(zahl2) < betragVon(zahl1)) {
        ggt = getGgt(zahl2, zahl1, ggt);
    }
      return ggt;
  }
    private static int getGgt(int zahl1, int zahl2, int ggt) {
        for (int i = betragVon(zahl1); i > 0; i--) {
          if (zahl1 % i == 0 && zahl2 % i == 0 && ggt == 0) {
            ggt = i;
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
