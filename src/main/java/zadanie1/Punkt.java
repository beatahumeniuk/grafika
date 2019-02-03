package zadanie1;

class Punkt {
    private static final int ROZMIAR = 4;
    private float[] marcierzPunktow = new float[ROZMIAR];

    Punkt(float x, float y, float z) {
        marcierzPunktow[0] = x;
        marcierzPunktow[1] = y;
        marcierzPunktow[2] = z;
        marcierzPunktow[3] = 1;
    }

    void przesun(float tx, float ty, float tz) {
        float[][] macierz = Macierze.przesuniecie(tx, ty, tz);
        marcierzPunktow = Macierze.wykonajTransformacje(macierz, marcierzPunktow);
    }

    void obroc(float katX, float katY, float katZ) {
        float[][] macierz = null;

        if (katX != 0)
            macierz = Macierze.obrotOX(katX);

        else if (katY != 0)
            macierz = Macierze.obrotOY(katY);

        else if (katZ != 0)
            macierz = Macierze.obrotOZ(katZ);

        if (macierz != null) {
            marcierzPunktow = Macierze.wykonajTransformacje(macierz, marcierzPunktow);
        }
    }

    void zmienPerspektywe(float wektor) {
        float[][] mecierz = Macierze.przeksztalceniePerspektywiczne(wektor);
        marcierzPunktow = Macierze.wykonajTransformacje(mecierz, marcierzPunktow);
    }

    float obliczOdlegloscOdKamery() {
        float odleglosc = 0.0f;
        for (int i = 0; i < marcierzPunktow.length - 1; i++) {
            odleglosc += (marcierzPunktow[i] * marcierzPunktow[i]);
        }
        return (float) Math.sqrt(odleglosc);
    }

    float obliczOdlegloscOdPunktu(Punkt drugiPunkt) {
        float odleglosc = 0.0f;
        for (int i = 0; i < marcierzPunktow.length - 1; i++) {
            odleglosc += (drugiPunkt.marcierzPunktow[i] - this.marcierzPunktow[i]) * (drugiPunkt.marcierzPunktow[i] - this.marcierzPunktow[i]);
        }
        return (float) Math.sqrt(odleglosc);
    }

    float x() {
        return marcierzPunktow[0];
    }

    float y() {
        return marcierzPunktow[1];
    }

    float z() {
        return marcierzPunktow[2];
    }

}
